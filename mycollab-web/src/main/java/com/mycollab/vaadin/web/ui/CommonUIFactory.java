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
package com.mycollab.vaadin.web.ui;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import org.vaadin.viritin.button.MButton;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class CommonUIFactory {
    public static MButton createButtonTooltip(String caption, String description) {
        return new MButton(caption).withDescription(description).withStyleName(WebThemes.BUTTON_LINK);
    }

    public static MButton createButtonTooltip(String caption, String description, Button.ClickListener listener) {
        return new MButton(caption).withDescription(description).withStyleName(WebThemes.BUTTON_LINK).withListener(listener);
    }
}
