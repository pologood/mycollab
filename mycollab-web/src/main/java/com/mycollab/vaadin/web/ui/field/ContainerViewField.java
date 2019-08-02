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
package com.mycollab.vaadin.web.ui.field;

import com.mycollab.vaadin.ui.IgnoreBindingField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import org.vaadin.viritin.layouts.MCssLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class ContainerViewField extends IgnoreBindingField<Object> {
    private static final long serialVersionUID = 1L;
    private MCssLayout layout;

    public ContainerViewField() {
        layout = new MCssLayout().withFullWidth();
    }

    public void addComponentField(Component component) {
        layout.withComponent(component);
    }

    @Override
    protected Component initContent() {
        return layout;
    }

    @Override
    protected void doSetValue(Object o) {

    }

    @Override
    public Object getValue() {
        return null;
    }
}
