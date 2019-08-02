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

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class AbstractToggleSummaryField extends MCssLayout {
    protected Label titleLinkLbl;
    protected MHorizontalLayout buttonControls;

    public void addLabelStyleNames(String... styleNames) {
        titleLinkLbl.addStyleNames(styleNames);
    }

    public void removeLabelStyleName(String styleName) {
        titleLinkLbl.removeStyleName(styleName);
    }

    public void addControl(Component control) {
        buttonControls.addComponent(control);
    }
}
