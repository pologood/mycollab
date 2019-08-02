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
package com.mycollab.module.project.view.milestone;

import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.ui.components.TimeLogComp;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class MilestoneTimeLogComp extends TimeLogComp<SimpleMilestone> {
    private ItemTimeLoggingService itemLogService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);

    @Override
    protected Double getTotalBillableHours(SimpleMilestone bean) {
        return itemLogService.getTotalBillableHoursByMilestone(bean.getId(), AppUI.getAccountId());
    }

    @Override
    protected Double getTotalNonBillableHours(SimpleMilestone bean) {
        return itemLogService.getTotalNonBillableHoursByMilestone(bean.getId(), AppUI.getAccountId());
    }

    @Override
    protected Double getRemainedHours(SimpleMilestone bean) {
        return itemLogService.getRemainHoursByMilestone(bean.getId(), AppUI.getAccountId());
    }

    @Override
    protected boolean hasEditPermission() {
        return false;
    }

    @Override
    protected void showEditTimeWindow(SimpleMilestone bean) {

    }
}
