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
package com.mycollab.module.project.view;

import com.mycollab.module.project.UserNotBelongProjectException;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.vaadin.web.ui.AbstractPresenter;

import static com.mycollab.core.utils.ExceptionUtils.getExceptionType;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public abstract class ProjectGenericPresenter<V extends PageView> extends AbstractPresenter<V> {
    public ProjectGenericPresenter(Class<V> viewClass) {
        super(viewClass);
    }

    @Override
    protected void onErrorStopChain(Throwable throwable) {
        super.onErrorStopChain(throwable);

        if (this instanceof ProjectViewPresenter) {
            if (getExceptionType(throwable, UserNotBelongProjectException.class) != null) {
                EventBusFactory.getInstance().post(new ProjectEvent.GotoUserDashboard(this, null));
            } else {
                EventBusFactory.getInstance().post(new ProjectEvent.GotoDashboard(this, null));
            }
        } else {
            EventBusFactory.getInstance().post(new ProjectEvent.GotoDashboard(this, null));
        }
    }
}
