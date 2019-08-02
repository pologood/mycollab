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

import com.google.common.eventbus.Subscribe
import com.mycollab.db.arguments.NumberSearchField
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.module.billing.RegisterStatusConstants
import com.mycollab.module.user.accountsettings.billing.view.IBillingPresenter
import com.mycollab.module.user.accountsettings.customize.view.GeneralSettingPresenter
import com.mycollab.module.user.accountsettings.customize.view.IThemeCustomizePresenter
import com.mycollab.module.user.accountsettings.profile.view.ProfilePresenter
import com.mycollab.module.user.accountsettings.team.view.*
import com.mycollab.module.user.accountsettings.view.event.AccountBillingEvent
import com.mycollab.module.user.accountsettings.view.event.ProfileEvent
import com.mycollab.module.user.accountsettings.view.event.SettingEvent
import com.mycollab.module.user.accountsettings.view.parameters.BillingScreenData
import com.mycollab.module.user.accountsettings.view.parameters.RoleScreenData
import com.mycollab.module.user.accountsettings.view.parameters.SettingExtScreenData
import com.mycollab.module.user.accountsettings.view.parameters.UserScreenData
import com.mycollab.module.user.domain.Role
import com.mycollab.module.user.domain.SimpleUser
import com.mycollab.module.user.domain.criteria.RoleSearchCriteria
import com.mycollab.module.user.domain.criteria.UserSearchCriteria
import com.mycollab.module.user.event.RoleEvent
import com.mycollab.module.user.event.UserEvent
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.ApplicationEventListener
import com.mycollab.vaadin.mvp.AbstractController
import com.mycollab.vaadin.mvp.PresenterResolver

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class UserAccountController(val container: AccountModule) : AbstractController() {
    init {
        bindProfileEvents()
        bindBillingEvents()
        bindRoleEvents()
        bindUserEvents()
        bingSettingEvents()
    }

    private fun bindBillingEvents() {
        this.register(object : ApplicationEventListener<AccountBillingEvent.CancelAccount> {
            @Subscribe
            override fun handle(event: AccountBillingEvent.CancelAccount) {
                val presenter = PresenterResolver.getPresenter(IBillingPresenter::class.java)
                presenter.go(container, BillingScreenData.CancelAccount())
            }
        })
        this.register(object : ApplicationEventListener<AccountBillingEvent.GotoSummary> {
            @Subscribe
            override fun handle(event: AccountBillingEvent.GotoSummary) {
                val presenter = PresenterResolver.getPresenter(IBillingPresenter::class.java)
                presenter.go(container, BillingScreenData.BillingSummary())
            }
        })
        this.register(object : ApplicationEventListener<AccountBillingEvent.GotoHistory> {
            @Subscribe
            override fun handle(event: AccountBillingEvent.GotoHistory) {
                val presenter = PresenterResolver.getPresenter(IBillingPresenter::class.java)
                presenter.go(container, BillingScreenData.BillingHistory())
            }
        })
    }

    private fun bindProfileEvents() {
        this.register(object : ApplicationEventListener<ProfileEvent.GotoProfileView> {
            @Subscribe
            override fun handle(event: ProfileEvent.GotoProfileView) {
                val presenter = PresenterResolver.getPresenter(ProfilePresenter::class.java)
                presenter.go(container, null)
            }
        })
    }

    private fun bindUserEvents() {
        this.register(object : ApplicationEventListener<UserEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: UserEvent.GotoAdd) {
                val presenter = PresenterResolver.getPresenter(UserAddPresenter::class.java)
                presenter.go(container, UserScreenData.Add(SimpleUser()))
            }
        })

        this.register(object : ApplicationEventListener<UserEvent.GotoBulkInvite> {
            @Subscribe
            override fun handle(event: UserEvent.GotoBulkInvite) {
                val presenter = PresenterResolver.getPresenter(UserBulkInvitePresenter::class.java)
                presenter.go(container, null)
            }
        })

        this.register(object : ApplicationEventListener<UserEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: UserEvent.GotoEdit) {
                val presenter = PresenterResolver.getPresenter(UserAddPresenter::class.java)
                val user = event.data as SimpleUser
                presenter.go(container, UserScreenData.Edit(user))
            }
        })
        this.register(object : ApplicationEventListener<UserEvent.GotoRead> {
            @Subscribe
            override fun handle(event: UserEvent.GotoRead) {
                val presenter = PresenterResolver.getPresenter(UserReadPresenter::class.java)
                presenter.go(container, UserScreenData.Read(event.data as String))
            }
        })
        this.register(object : ApplicationEventListener<UserEvent.GotoList> {
            @Subscribe
            override fun handle(event: UserEvent.GotoList) {
                val presenter = PresenterResolver.getPresenter(UserListPresenter::class.java)
                val criteria = UserSearchCriteria()
                criteria.saccountid = NumberSearchField(AppUI.accountId)
                criteria.registerStatuses = SetSearchField(RegisterStatusConstants.ACTIVE, RegisterStatusConstants.NOT_LOG_IN_YET)
                presenter.go(container, UserScreenData.Search(criteria))
            }
        })
    }

    private fun bindRoleEvents() {
        this.register(object : ApplicationEventListener<RoleEvent.GotoAdd> {
            @Subscribe
            override fun handle(event: RoleEvent.GotoAdd) {
                val presenter = PresenterResolver.getPresenter(RoleAddPresenter::class.java)
                presenter.go(container, RoleScreenData.Add(Role()))
            }
        })
        this.register(object : ApplicationEventListener<RoleEvent.GotoEdit> {
            @Subscribe
            override fun handle(event: RoleEvent.GotoEdit) {
                val presenter = PresenterResolver.getPresenter(RoleAddPresenter::class.java)
                presenter.go(container, RoleScreenData.Edit(event.data as Role))
            }
        })
        this.register(object : ApplicationEventListener<RoleEvent.GotoRead> {
            @Subscribe
            override fun handle(event: RoleEvent.GotoRead) {
                val presenter = PresenterResolver.getPresenter(RoleReadPresenter::class.java)
                presenter.go(container, RoleScreenData.Read(event.data as Int))
            }
        })
        this.register(object : ApplicationEventListener<RoleEvent.GotoList> {
            @Subscribe
            override fun handle(event: RoleEvent.GotoList) {
                val presenter = PresenterResolver.getPresenter(RoleListPresenter::class.java)
                val criteria = RoleSearchCriteria()
                criteria.saccountid = NumberSearchField(AppUI.accountId)
                presenter.go(container, RoleScreenData.Search(criteria))
            }
        })
    }

    private fun bingSettingEvents() {
        this.register(object : ApplicationEventListener<SettingEvent.GotoGeneralSetting> {
            @Subscribe
            override fun handle(event: SettingEvent.GotoGeneralSetting) {
                val presenter = PresenterResolver.getPresenter(GeneralSettingPresenter::class.java)
                presenter.go(container, SettingExtScreenData.GeneralSetting())
            }
        })

        this.register(object : ApplicationEventListener<SettingEvent.GotoTheme> {
            @Subscribe
            override fun handle(event: SettingEvent.GotoTheme) {
                val presenter = PresenterResolver.getPresenter(IThemeCustomizePresenter::class.java)
                presenter.go(container, SettingExtScreenData.ThemeCustomize())
            }
        })
    }
}