/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.user.accountsettings.team.view;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.billing.RegisterStatusConstants;
import com.mycollab.module.user.accountsettings.view.AccountModule;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.security.AccessPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_USER, impliedPermissionVal = AccessPermissionFlag.READ_ONLY)
public class UserListPresenter extends AbstractPresenter<UserListView> {
    private static final long serialVersionUID = 1L;

    public UserListPresenter() {
        super(UserListView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        AccountModule accountModule = (AccountModule) container;
        accountModule.gotoSubView(SettingUIConstants.USERS, view);

        UserSearchCriteria criteria;
        if (data == null) {
            criteria = new UserSearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
            criteria.setRegisterStatuses(new SetSearchField<>(RegisterStatusConstants.ACTIVE,
                    RegisterStatusConstants.NOT_LOG_IN_YET));
        } else {
            criteria = (UserSearchCriteria) data.getParams();
        }

        view.setSearchCriteria(criteria);

        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoUserList();
    }
}
