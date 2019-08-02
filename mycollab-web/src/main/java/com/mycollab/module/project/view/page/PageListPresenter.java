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
package com.mycollab.module.project.view.page;

import com.mycollab.core.SecureAccessException;
import com.mycollab.module.page.domain.PageResource;
import com.mycollab.module.page.service.PageService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.ProjectGenericPresenter;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.vaadin.ui.HasComponents;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class PageListPresenter extends ProjectGenericPresenter<PageListView> {
    private static final long serialVersionUID = 1L;

    private PageService pageService;

    public PageListPresenter() {
        super(PageListView.class);
        pageService = AppContextUtil.getSpringBean(PageService.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.PAGES)) {
            ProjectView pageContainer = (ProjectView) container;
            pageContainer.gotoSubView(ProjectView.PAGE_ENTRY, view);

            String path = (String) data.getParams();
            if (path == null) {
                path = CurrentProjectVariables.getCurrentPagePath();
            } else {
                CurrentProjectVariables.setCurrentPagePath(path);
            }
            List<PageResource> resources = pageService.getResources(path, UserUIContext.getUsername());
            if (!CollectionUtils.isEmpty(resources)) {
                view.displayDefaultPages(resources);
            } else {
                view.showNoItemView();
            }

            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadcrumb.gotoPageList();
        } else {
            throw new SecureAccessException();
        }
    }

}
