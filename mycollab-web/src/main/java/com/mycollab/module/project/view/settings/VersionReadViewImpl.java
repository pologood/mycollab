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
package com.mycollab.module.project.view.settings;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.VersionI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.DateInfoComp;
import com.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.mycollab.module.project.ui.components.ProjectPreviewFormControlsGenerator;
import com.mycollab.module.project.ui.components.TagViewComponent;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.domain.Version;
import com.mycollab.module.project.service.VersionService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.AbstractPreviewItemComp;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class VersionReadViewImpl extends AbstractPreviewItemComp<Version> implements VersionReadView {
    private static final long serialVersionUID = 1L;

    private MButton quickActionStatusBtn;
    private TagViewComponent tagViewComponent;
    private ProjectActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private VersionTimeLogComp versionTimeLogComp;

    public VersionReadViewImpl() {
        super(UserUIContext.getMessage(VersionI18nEnum.DETAIL),
                ProjectAssetsManager.getAsset(ProjectTypeConstants.VERSION));
    }

    @Override
    public HasPreviewFormHandlers<Version> getPreviewFormHandlers() {
        return this.previewForm;
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.VERSION,
                CurrentProjectVariables.getProjectId());

        ProjectView projectView = UIUtils.getRoot(this, ProjectView.class);
        MVerticalLayout detailLayout = new MVerticalLayout().withMargin(new MarginInfo(false, true, true, true));

        dateInfoComp = new DateInfoComp();
        if (SiteConfiguration.isCommunityEdition()) {
            detailLayout.with(dateInfoComp);
        } else {
            versionTimeLogComp = new VersionTimeLogComp();
            detailLayout.with(dateInfoComp, versionTimeLogComp);
        }

        Panel detailPanel = new Panel(UserUIContext.getMessage(GenericI18Enum.OPT_DETAILS), detailLayout);
        UIUtils.makeStackPanel(detailPanel);
        projectView.addComponentToRightBar(detailPanel);
    }

    @Override
    protected void onPreviewItem() {
        activityComponent.loadActivities("" + beanItem.getId());
        dateInfoComp.displayEntryDateTime(beanItem);

        if (tagViewComponent != null) {
            tagViewComponent.display(ProjectTypeConstants.VERSION, beanItem.getId());
        }

        if (versionTimeLogComp != null) {
            versionTimeLogComp.displayTime(beanItem);
        }

        if (StatusI18nEnum.Open.name().equals(beanItem.getStatus())) {
            removeLayoutStyleName(WebThemes.LINK_COMPLETED);
            quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE));
            quickActionStatusBtn.setIcon(VaadinIcons.ARCHIVE);
        } else {
            addLayoutStyleName(WebThemes.LINK_COMPLETED);
            quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN));
            quickActionStatusBtn.setIcon(VaadinIcons.CLIPBOARD);
        }
    }

    @Override
    protected ComponentContainer createExtraControls() {
        if (SiteConfiguration.isCommunityEdition()) {
            return null;
        } else {
            tagViewComponent = new TagViewComponent(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.VERSIONS));
            return tagViewComponent;
        }
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getName();
    }

    @Override
    protected AdvancedPreviewBeanForm<Version> initPreviewForm() {
        return new VersionPreviewForm();
    }

    @Override
    protected HorizontalLayout createButtonControls() {
        ProjectPreviewFormControlsGenerator<Version> versionPreviewForm = new ProjectPreviewFormControlsGenerator<>(previewForm);
        HorizontalLayout topPanel = versionPreviewForm.createButtonControls(ProjectRolePermissionCollections.VERSIONS);

        quickActionStatusBtn = new MButton("", clickEvent -> {
            if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
                beanItem.setStatus(StatusI18nEnum.Open.name());
                VersionReadViewImpl.this.removeLayoutStyleName(WebThemes.LINK_COMPLETED);
                quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE));
                quickActionStatusBtn.setIcon(VaadinIcons.ARCHIVE);
            } else {
                beanItem.setStatus(StatusI18nEnum.Closed.name());
                VersionReadViewImpl.this.addLayoutStyleName(WebThemes.LINK_COMPLETED);
                quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN));
                quickActionStatusBtn.setIcon(VaadinIcons.CLIPBOARD);
            }

            VersionService service = AppContextUtil.getSpringBean(VersionService.class);
            service.updateSelectiveWithSession(beanItem, UserUIContext.getUsername());
        }).withStyleName(WebThemes.BUTTON_ACTION)
                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.VERSIONS));
        versionPreviewForm.insertToControlBlock(quickActionStatusBtn);
        return topPanel;
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    public Version getItem() {
        return beanItem;
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.VERSION;
    }

}