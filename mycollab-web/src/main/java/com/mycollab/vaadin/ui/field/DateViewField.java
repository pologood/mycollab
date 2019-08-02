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
package com.mycollab.vaadin.ui.field;

import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

import java.time.LocalDate;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class DateViewField extends CustomField<LocalDate> {
    private Label label;

    public DateViewField() {
        label = new Label();
    }

    @Override
    protected Component initContent() {
        return label;
    }

    @Override
    protected void doSetValue(LocalDate value) {
        String dateValue = (value == null) ? "" : UserUIContext.formatDate(value);
        label.setValue(dateValue);
    }

    @Override
    public LocalDate getValue() {
        return null;
    }
}
