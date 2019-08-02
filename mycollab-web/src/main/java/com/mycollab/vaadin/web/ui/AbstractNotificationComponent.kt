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
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui

import com.google.common.eventbus.Subscribe
import com.hp.gagawa.java.elements.A
import com.hp.gagawa.java.elements.Span
import com.mycollab.common.i18n.GenericI18Enum
import com.mycollab.common.i18n.ShellI18nEnum
import com.mycollab.common.ui.components.notification.RequestUploadAvatarNotification
import com.mycollab.common.ui.components.notification.SmtpSetupNotification
import com.mycollab.core.AbstractNotification
import com.mycollab.core.NewUpdateAvailableNotification
import com.mycollab.shell.event.ShellEvent
import com.mycollab.vaadin.ApplicationEventListener
import com.mycollab.vaadin.AsyncInvoker
import com.mycollab.vaadin.EventBusFactory
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.ui.ELabel
import com.vaadin.icons.VaadinIcons
import com.vaadin.server.ExternalResource
import com.vaadin.ui.*
import org.vaadin.hene.popupbutton.PopupButton
import org.vaadin.viritin.button.MButton
import org.vaadin.viritin.layouts.MHorizontalLayout
import org.vaadin.viritin.layouts.MVerticalLayout

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
abstract class AbstractNotificationComponent : PopupButton(), PopupButton.PopupVisibilityListener, ApplicationEventListener<ShellEvent.NewNotification> {

    private val notificationItems = mutableSetOf<AbstractNotification>()
    protected var notificationCount = 0L
    protected val notificationContainer = MVerticalLayout().withSpacing(false).withMargin(false).withStyleName(WebThemes.SCROLLABLE_CONTAINER)

    init {
        this.content = notificationContainer
        this.icon = VaadinIcons.BELL
        this.styleName = "notification-button"

        addPopupVisibilityListener(this)
        EventBusFactory.getInstance().register(this)

        // Register to receive broadcasts
        JavaScript.getCurrent().addFunction("com.mycollab.scripts.upgrade"
        ) { jsonArray ->
            val version = jsonArray.getString(0)
            val installerFile = jsonArray.getString(1)
            val manualDownloadLink = jsonArray.getString(2)
            UI.getCurrent().addWindow(UpgradeConfirmWindow(version, manualDownloadLink, installerFile))
        }
    }

    override fun popupVisibilityChange(event: PopupButton.PopupVisibilityEvent) {
        notificationContainer.removeAllComponents()

        if (notificationItems.isNotEmpty()) {
            notificationItems.forEach { addNotificationEntry(it) }
        } else {
            val noItemLbl = ELabel(UserUIContext.getMessage(ShellI18nEnum.OPT_NO_NOTIFICATION)).withFullWidth()
            notificationContainer.with(noItemLbl).withAlign(noItemLbl, Alignment.MIDDLE_CENTER)
        }
    }

    private fun addNotificationEntry(notification: AbstractNotification) {
        val comp = buildComponentFromNotification(notification)
        if (comp != null) {
            comp.styleName = "notification-type"
            comp.addStyleName("notification-type-${notification.kind}")
            notificationContainer.addComponent(comp)
        }
    }

    private fun addNotification(notification: AbstractNotification) {
        notificationCount++
        notificationItems.add(notification)
        updateCaption()
        displayTrayNotification(notification)
    }

    fun removeNotification(notification: AbstractNotification) {
        notificationCount--;
        notificationItems.remove(notification)
        updateCaption()
    }

    private fun updateCaption() {
        if (ui != null && notificationCount > 0) {
            AsyncInvoker.access(ui, object : AsyncInvoker.PageCommand() {
                override fun run() {
                    this@AbstractNotificationComponent.caption = "$notificationCount"
                }
            })
        } else {
            this.caption = null
        }
    }

    @Subscribe
    override fun handle(event: ShellEvent.NewNotification) {
        if (event.data is AbstractNotification) {
            addNotification(event.data)
        }
    }

    private fun displayTrayNotification(item: AbstractNotification) {
        if (item is NewUpdateAvailableNotification) {
            val no = when {
                UserUIContext.isAdmin() -> Notification(UserUIContext.getMessage(GenericI18Enum.WINDOW_INFORMATION_TITLE), UserUIContext.getMessage(ShellI18nEnum.OPT_HAVING_NEW_VERSION, item.version) + " "
                        + A("javascript:com.mycollab.scripts.upgrade('${item.version}','${item.autoDownloadLink}','${item.manualDownloadLink}')")
                        .appendText(UserUIContext.getMessage(ShellI18nEnum.ACTION_UPGRADE)),
                        Notification.Type.TRAY_NOTIFICATION)
                else -> Notification(UserUIContext.getMessage(GenericI18Enum.WINDOW_INFORMATION_TITLE), UserUIContext.getMessage(ShellI18nEnum.OPT_HAVING_NEW_VERSION,
                        item.version), Notification.Type.TRAY_NOTIFICATION)
            }

            no.isHtmlContentAllowed = true
            no.delayMsec = 300000

            AsyncInvoker.access(ui, object : AsyncInvoker.PageCommand() {
                override fun run() {
                    no.show(ui.page)
                }
            })
        } else displayTrayNotificationExclusive(item)
    }

    private fun buildComponentFromNotification(item: AbstractNotification): Component? {
        when (item) {
            is NewUpdateAvailableNotification -> {
                val spanEl = Span().appendText(UserUIContext.getMessage(ShellI18nEnum.OPT_HAVING_NEW_VERSION, item.version))
                val lbl = ELabel.html("${VaadinIcons.INFO_CIRCLE.html} ${spanEl.write()}").withFullWidth()
                val lblWrapper = CssLayout()
                lblWrapper.addComponent(lbl)
                return when {
                    UserUIContext.isAdmin() -> {
                        val upgradeBtn = MButton(UserUIContext.getMessage(ShellI18nEnum.ACTION_UPGRADE)) {
                            UI.getCurrent().addWindow(UpgradeConfirmWindow(item.version, item.manualDownloadLink, item.installerFile))
                            this@AbstractNotificationComponent.isPopupVisible = false
                        }.withStyleName(WebThemes.BLOCK)
                        MHorizontalLayout(lblWrapper, upgradeBtn).expand(lblWrapper).withDefaultComponentAlignment(Alignment.TOP_LEFT)
                    }
                    else -> lblWrapper
                }
            }
            is RequestUploadAvatarNotification -> {
                val avatarUploadLbl = ELabel.html("${VaadinIcons.EXCLAMATION_CIRCLE.html} ${UserUIContext.getMessage(ShellI18nEnum.OPT_REQUEST_UPLOAD_AVATAR)}")
                val uploadAvatarBtn = MButton(UserUIContext.getMessage(ShellI18nEnum.ACTION_UPLOAD_AVATAR)) {
                    EventBusFactory.getInstance().post(ShellEvent.GotoUserAccountModule(this, arrayOf("preview")))
                    this@AbstractNotificationComponent.isPopupVisible = false
                }.withStyleName(WebThemes.BLOCK)
                return MHorizontalLayout(avatarUploadLbl, uploadAvatarBtn).expand(avatarUploadLbl).withDefaultComponentAlignment(Alignment.TOP_LEFT)
                        .withFullWidth()
            }
            is SmtpSetupNotification -> {
                val smtpBtn =  Link("Help", ExternalResource("https://docs.mycollab.com/administration/email-configuration/"))
                smtpBtn.targetName = "_blank"
                smtpBtn.styleName = WebThemes.BLOCK
//                val smtpBtn = MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_SETUP)) {
//                    EventBusFactory.getInstance().post(ShellEvent.GotoUserAccountModule(this, arrayOf("setup")))
//                    this@AbstractNotificationComponent.isPopupVisible = false
//                }.withStyleName(WebThemes.BLOCK)
                val lbl = ELabel.html("${VaadinIcons.EXCLAMATION_CIRCLE.html} ${UserUIContext.getMessage(ShellI18nEnum.ERROR_NO_SMTP_SETTING)}").withFullWidth()
                return MHorizontalLayout(lbl, smtpBtn).expand(lbl).withDefaultComponentAlignment(Alignment.TOP_LEFT)
            }
            else -> return buildComponentFromNotificationExclusive(item)
        }
    }

    protected abstract fun buildComponentFromNotificationExclusive(item: AbstractNotification): Component?

    protected abstract fun displayTrayNotificationExclusive(item: AbstractNotification)
}
