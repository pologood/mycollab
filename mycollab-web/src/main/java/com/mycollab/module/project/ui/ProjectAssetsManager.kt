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
package com.mycollab.module.project.ui

import com.mycollab.core.utils.StringUtils
import com.mycollab.module.project.ProjectTypeConstants
import com.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority
import com.vaadin.icons.VaadinIcons

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
object ProjectAssetsManager {
    private val resources = mapOf(
            ProjectTypeConstants.DASHBOARD to VaadinIcons.DASHBOARD,
            ProjectTypeConstants.MESSAGE to VaadinIcons.COMMENT,
            ProjectTypeConstants.MILESTONE to VaadinIcons.FLAG_CHECKERED,
            ProjectTypeConstants.TASK to VaadinIcons.TASKS,
            ProjectTypeConstants.TICKET to VaadinIcons.TICKET,
            ProjectTypeConstants.FILE to VaadinIcons.BRIEFCASE,
            ProjectTypeConstants.PAGE to VaadinIcons.FILE,
            ProjectTypeConstants.BUG to VaadinIcons.BUG,
            ProjectTypeConstants.COMPONENT to VaadinIcons.CUBE,
            ProjectTypeConstants.VERSION to VaadinIcons.BUG,
            ProjectTypeConstants.RISK to VaadinIcons.SHIELD,
            ProjectTypeConstants.FINANCE to VaadinIcons.MONEY,
            ProjectTypeConstants.TIME to VaadinIcons.CLOCK,
            ProjectTypeConstants.INVOICE to VaadinIcons.CREDIT_CARD,
            ProjectTypeConstants.STANDUP to VaadinIcons.CUBES,
            ProjectTypeConstants.MEMBER to VaadinIcons.USERS,
            ProjectTypeConstants.PROJECT to VaadinIcons.CALENDAR_O,
            ProjectTypeConstants.CLIENT to VaadinIcons.COIN_PILES,
            ProjectTypeConstants.PROJECT_ROLE to VaadinIcons.CLIPBOARD_USER
    )

    @JvmStatic
    fun getAsset(resId: String): VaadinIcons = resources[resId] ?: VaadinIcons.DASHBOARD

    @JvmStatic
    fun getPriority(priority: String?): VaadinIcons =
            if (Priority.Urgent.name == priority || Priority.High.name == priority || Priority.Medium.name == priority || priority == null) {
                VaadinIcons.ARROW_UP
            } else {
                VaadinIcons.ARROW_DOWN
            }

    @JvmStatic
    fun getMilestoneStatus(status: String): VaadinIcons = when (status) {
        MilestoneStatus.Closed.name -> VaadinIcons.MINUS_CIRCLE
        MilestoneStatus.InProgress.name -> VaadinIcons.CLOCK
        else -> VaadinIcons.SPINNER
    }

    @JvmStatic
    fun toHtml(resId: String): String {
        val icon = resources[resId]
        return if (icon != null) {
            "<span class=\"v-icon v-icon-${icon.name.toLowerCase()}\" style=\"font-family: Vaadin-Icons;\">&#x${Integer.toHexString(icon.codepoint)};</span>"
        } else ""
    }

    @JvmStatic
    fun getPriorityHtml(priority: String): String {
        var temp = priority
        if (StringUtils.isBlank(temp)) {
            temp = Priority.Medium.name
        }
        val icon = getPriority(temp)
        return "<span class=\"priority-${temp.toLowerCase()} v-icon v-icon-${icon.name.toLowerCase()}\" style=\"font-family: Vaadin-Icons;\">&#x${Integer.toHexString(icon.codepoint)};</span>"
    }
}
