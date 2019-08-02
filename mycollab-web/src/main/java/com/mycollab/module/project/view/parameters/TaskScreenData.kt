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
package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Task
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object TaskScreenData {
    class Read(param: Any) : ScreenData<Any>(param)

    class Edit(param: Task) : ScreenData<Task>(param)

    class Add(param: Task) : ScreenData<Task>(param)
}