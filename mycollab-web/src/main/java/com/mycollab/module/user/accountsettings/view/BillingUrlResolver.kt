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
package com.mycollab.module.user.accountsettings.view

import com.mycollab.vaadin.EventBusFactory
import com.mycollab.module.user.accountsettings.view.event.AccountBillingEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class BillingUrlResolver : AccountSettingUrlResolver() {
    init {
        this.defaultUrlResolver = SummaryUrlResolver()
        this.addSubResolver("history", HistoryUrlResolver())
        this.addSubResolver("cancel", CancelUrlResolver())
    }

    private class SummaryUrlResolver : AccountSettingUrlResolver() {
        override fun handlePage(vararg params: String) =
                EventBusFactory.getInstance().post(AccountBillingEvent.GotoSummary(this, null))
    }

    private class HistoryUrlResolver : AccountSettingUrlResolver() {
        override fun handlePage(vararg params: String) =
                EventBusFactory.getInstance().post(AccountBillingEvent.GotoHistory(this, null))
    }

    private class CancelUrlResolver : AccountSettingUrlResolver() {
        override fun handlePage(vararg params: String) =
                EventBusFactory.getInstance().post(AccountBillingEvent.CancelAccount(this, null))
    }

    override fun defaultPageErrorHandler() = handlePage()
}