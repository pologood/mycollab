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
package com.mycollab.vaadin.web.ui;

import com.mycollab.core.MyCollabException;
import com.mycollab.form.service.MasterFormService;
import com.mycollab.form.view.LayoutType;
import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.FormContainer;
import com.mycollab.vaadin.ui.FormSection;
import com.mycollab.vaadin.ui.IDynaFormLayout;
import com.mycollab.vaadin.web.ui.grid.GridCellWrapper;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

import java.util.*;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class DefaultDynaFormLayout implements IDynaFormLayout {

    private DynaForm dynaForm;

    private Map<String, AbstractDynaField> fieldMappings = new HashMap<>();
    private Map<DynaSection, GridFormLayoutHelper> sectionMappings;
    private Set<String> excludeFields;

    public DefaultDynaFormLayout(String moduleName, DynaForm defaultForm, String... excludeField) {
        this.excludeFields = excludeField.length > 0 ? new HashSet<>(Arrays.asList(excludeField)) : new HashSet<>();
        MasterFormService formService = AppContextUtil.getSpringBean(MasterFormService.class);
        DynaForm form = formService.findCustomForm(AppUI.getAccountId(), moduleName);
        this.dynaForm = (form != null) ? form : defaultForm;
    }

    public DefaultDynaFormLayout(DynaForm dynaForm, String... excludeField) {
        this.excludeFields = excludeField.length > 0 ? new HashSet<>(Arrays.asList(excludeField)) : new HashSet<>();
        this.dynaForm = dynaForm;
    }

    @Override
    public AbstractComponent getLayout() {
        FormContainer layout = new FormContainer();
        int sectionCount = dynaForm.getSectionCount();
        sectionMappings = new HashMap<>();

        for (int i = 0; i < sectionCount; i++) {
            DynaSection section = dynaForm.getSection(i);
            if (section.isDeletedSection()) {
                continue;
            }

            HasComponents sectionContainer = null;

            if (section.getHeader() != null) {
                sectionContainer = new FormSection(UserUIContext.getMessage(section.getHeader()));
                layout.addComponent(sectionContainer);
            }

            GridFormLayoutHelper gridLayout;

            if (section.isDeletedSection() || section.getFieldCount() == 0) {
                continue;
            }

            if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
                gridLayout = GridFormLayoutHelper.defaultFormLayoutHelper(section.getLayoutType());
                int rowIndex = 0;
                for (int j = 0; j < section.getFieldCount(); j++) {
                    AbstractDynaField dynaField = section.getField(j);
                    if (!excludeFields.contains(dynaField.getFieldName())) {
                        gridLayout.buildCell(dynaField.getFieldName(), UserUIContext.getMessage(dynaField.getDisplayName()),
                                UserUIContext.getMessage(dynaField.getContextHelp()), 0,
                                rowIndex, 2);
                        if (j < section.getFieldCount() - 1) {
                            rowIndex++;
                        }

                        if (dynaField.isCustom()) {
                            fieldMappings.put("customfield." + dynaField.getFieldName(), dynaField);
                        } else {
                            fieldMappings.put(dynaField.getFieldName(), dynaField);
                        }
                    }
                }
            } else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
                gridLayout = GridFormLayoutHelper.defaultFormLayoutHelper(section.getLayoutType());
                int columnIndex = 0;
                int rowIndex = 0;
                for (int j = 0; j < section.getFieldCount(); j++) {
                    AbstractDynaField dynaField = section.getField(j);
                    if (!excludeFields.contains(dynaField.getFieldName())) {
                        if (dynaField.isColSpan()) {
                            if (columnIndex > 0) {
                                rowIndex++;
                            }
                            gridLayout.buildCell(dynaField.getFieldName(), UserUIContext.getMessage(dynaField.getDisplayName()),
                                    UserUIContext.getMessage(dynaField.getContextHelp()), 0,
                                    rowIndex, 2);
                            columnIndex = 0;
                            if (j < section.getFieldCount() - 1) {
                                rowIndex++;
                            }
                        } else {
                            gridLayout.buildCell(dynaField.getFieldName(), UserUIContext.getMessage(dynaField.getDisplayName()),
                                    UserUIContext.getMessage(dynaField.getContextHelp()), columnIndex, rowIndex);
                            columnIndex++;
                            if (columnIndex == 2) {
                                columnIndex = 0;
                                if (j < section.getFieldCount() - 1) {
                                    rowIndex++;
                                }
                            }
                        }

                        if (dynaField.isCustom()) {
                            fieldMappings.put("customfield." + dynaField.getFieldName(), dynaField);
                        } else {
                            fieldMappings.put(dynaField.getFieldName(), dynaField);
                        }
                    }
                }
            } else {
                throw new MyCollabException("Does not support attachForm layout except 1 or 2 columns");
            }

            if (sectionContainer != null) {
                ((FormSection) sectionContainer).setContent(gridLayout.getLayout());
            } else {
                sectionContainer = gridLayout.getLayout();
            }

            layout.addComponent(sectionContainer);
            sectionMappings.put(section, gridLayout);
        }
        return layout;
    }

    @Override
    public HasValue<?> attachField(Object propertyId, HasValue<?> field) {
        AbstractDynaField dynaField = fieldMappings.get(propertyId);
        if (dynaField != null) {
            DynaSection section = dynaField.getOwnSection();
            GridFormLayoutHelper gridLayout = sectionMappings.get(section);
            GridCellWrapper componentWrapper = gridLayout.getComponentWrapper(dynaField.getFieldName());
            if (componentWrapper != null) {
                componentWrapper.addField((Component) field);
            }
            return field;
        }
        return null;
    }

    @Override
    public Set<String> bindFields() {
        return fieldMappings.keySet();
    }
}
