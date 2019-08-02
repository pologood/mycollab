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

import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
public class DefaultReadViewLayout extends ReadViewLayout {
    private ELabel titleLbl;

    public DefaultReadViewLayout(String title) {
        this.setSizeFull();
        this.addHeader(buildHeader(title));
    }

    private ComponentContainer buildHeader(String title) {
        MHorizontalLayout header = new MHorizontalLayout().withFullWidth();
        titleLbl = ELabel.h3(title);
        header.with(titleLbl).expand(titleLbl);
        return header;
    }

    @Override
    public void addTitleStyleName(final String styleName) {
        titleLbl.addStyleName(styleName);
    }

    @Override
    public void removeTitleStyleName(final String styleName) {
        titleLbl.removeStyleName(styleName);
    }

    public void setTitle(final String title) {
        titleLbl.setValue(title);
    }
}
