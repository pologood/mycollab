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

import com.mycollab.web.CustomLayoutExt;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;

import java.util.concurrent.locks.Lock;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ProgressBarIndicator extends CustomLayoutExt {
    private static final long serialVersionUID = 1L;

    private ProgressBar progressIndicator;
    private Label progressStatusLabel;

    public ProgressBarIndicator() {
        super("progressBar");
        this.progressIndicator = new ProgressBar();
        this.progressIndicator.setWidth("100%");
        this.progressStatusLabel = new Label();
        this.progressStatusLabel.setWidth("100%");
        this.progressStatusLabel.setHeight("100%");
        this.addComponent(this.progressIndicator, "progressbar-container");
        this.addComponent(this.progressStatusLabel, "progressbar-status");
    }

    public ProgressBarIndicator(int total, int gaining) {
        this(total, gaining, true);
    }

    public ProgressBarIndicator(int total, int gaining, Boolean displayPercentage) {
        this();
        float value = (total != 0) ? ((float) (gaining) / total) : 0;
        progressIndicator.setValue(value);
        if (displayPercentage) {
            if (total > 0) {
                this.progressStatusLabel.setValue(String.format("%.0f", value * 100) + "%");
            } else {
                this.progressStatusLabel = new Label("100%");
            }
        } else {
            this.progressStatusLabel.setValue((gaining) + " / " + total);
        }
    }
}
