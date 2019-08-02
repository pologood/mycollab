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
package com.mycollab.community.module.project.view.settings;

import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.view.settings.ProjectNotificationSettingViewComponent;
import com.mycollab.module.project.view.settings.ProjectCustomView;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
@ViewComponent
public class ProjectSettingViewImpl extends AbstractVerticalPageView implements ProjectCustomView {
    private static final long serialVersionUID = 1L;

    private final MHorizontalLayout mainBody;

    public ProjectSettingViewImpl() {
        this.setWidth("100%");
        this.setSpacing(true);
        this.addStyleName("readview-layout");

        mainBody = new MHorizontalLayout().withMargin(true).withFullWidth();
        this.addComponent(mainBody);
    }

    @Override
    public void showNotificationSettings(ProjectNotificationSetting notification) {
        mainBody.removeAllComponents();
        ProjectNotificationSettingViewComponent component = new ProjectNotificationSettingViewComponent(notification);
        mainBody.addComponent(component);
    }
}
