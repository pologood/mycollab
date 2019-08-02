/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.schedule.email.service

import com.hp.gagawa.java.elements.A
import com.mycollab.common.MonitorTypeConstants
import com.mycollab.core.MyCollabException
import com.mycollab.core.utils.StringUtils
import com.mycollab.html.LinkUtils
import com.mycollab.module.project.ProjectLinkGenerator
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.domain.ProjectRelayEmailNotification
import com.mycollab.module.project.domain.SimpleMessage
import com.mycollab.module.project.i18n.MessageI18nEnum
import com.mycollab.module.project.service.MessageService
import com.mycollab.module.user.AccountLinkGenerator
import com.mycollab.schedule.email.ItemFieldMapper
import com.mycollab.schedule.email.MailContext
import com.mycollab.schedule.email.project.MessageRelayEmailNotificationAction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class MessageRelayEmailNotificationActionImpl : SendMailToAllMembersAction<SimpleMessage>(), MessageRelayEmailNotificationAction {
    @Autowired private lateinit var messageService: MessageService

    override fun getItemName(): String = StringUtils.trim(bean!!.title, 100)

    override fun getProjectName(): String = bean!!.projectName!!

    override fun getCreateSubject(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_CREATE_ITEM_SUBJECT, bean!!.projectName, context.changeByUserFullName, getItemName())

    override fun getCreateSubjectNotification(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_CREATE_ITEM_SUBJECT, projectLink(), userLink(context), messageLink())

    override fun getUpdateSubject(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, bean!!.projectName, context.changeByUserFullName, getItemName())

    override fun getUpdateSubjectNotification(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_UPDATE_ITEM_SUBJECT, projectLink(), userLink(context), messageLink())

    override fun getCommentSubject(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, bean!!.projectName, context.changeByUserFullName, getItemName())

    override fun getCommentSubjectNotification(context: MailContext<SimpleMessage>): String = context.getMessage(
            MessageI18nEnum.MAIL_COMMENT_ITEM_SUBJECT, projectLink(), userLink(context), messageLink())

    private fun projectLink() = A(ProjectLinkGenerator.generateProjectLink(bean!!.projectid)).appendText(bean!!.projectName).write()

    private fun userLink(context: MailContext<SimpleMessage>) = A(AccountLinkGenerator.generateUserLink(context.user.username)).appendText(context.changeByUserFullName).write()

    private fun messageLink() = A(ProjectLinkGenerator.generateMessagePreviewLink(bean!!.projectid, bean!!.id)).appendText(getItemName()).write()

    override fun getItemFieldMapper(): ItemFieldMapper = ItemFieldMapper()

    override fun getBeanInContext(notification: ProjectRelayEmailNotification): SimpleMessage? =
            messageService.findById(notification.typeid.toInt(), notification.saccountid)

    override fun getType(): String = ProjectTypeConstants.MESSAGE

    override fun getTypeId(): String = "${bean!!.id}"

    override fun buildExtraTemplateVariables(context: MailContext<SimpleMessage>) {
        val emailNotification = context.emailNotification

        val summary = bean!!.title
        val summaryLink = ProjectLinkGenerator.generateMessagePreviewFullLink(siteUrl, bean!!.projectid, bean!!.id)

        val avatarId = if (projectMember != null) projectMember!!.memberAvatarId else ""
        val userAvatar = LinkUtils.newAvatar(avatarId)

        val makeChangeUser = "${userAvatar.write()} ${emailNotification.changeByUserFullName}"
        val actionEnum = when (emailNotification.action) {
            MonitorTypeConstants.CREATE_ACTION -> MessageI18nEnum.MAIL_CREATE_ITEM_HEADING
            MonitorTypeConstants.UPDATE_ACTION -> MessageI18nEnum.MAIL_UPDATE_ITEM_HEADING
            MonitorTypeConstants.ADD_COMMENT_ACTION -> MessageI18nEnum.MAIL_COMMENT_ITEM_HEADING
            else -> throw MyCollabException("Not support action ${emailNotification.action}")
        }

        contentGenerator.putVariable("projectName", bean!!.projectName!!)
        contentGenerator.putVariable("projectNotificationUrl", ProjectLinkGenerator.generateProjectSettingFullLink(siteUrl, bean!!.projectid))
        contentGenerator.putVariable("actionHeading", context.getMessage(actionEnum, makeChangeUser))
        contentGenerator.putVariable("name", summary)
        contentGenerator.putVariable("summaryLink", summaryLink)
        contentGenerator.putVariable("message", bean!!.message)
    }
}