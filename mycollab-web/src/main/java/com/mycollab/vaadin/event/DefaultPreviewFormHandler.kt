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
package com.mycollab.vaadin.event

import com.mycollab.core.MyCollabException

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class DefaultPreviewFormHandler<T> : PreviewFormHandler<T> {
    override fun gotoNext(data: T) {}

    override fun gotoPrevious(data: T) {}

    override fun onEdit(data: T) {}

    override fun onDelete(data: T) {}

    override fun onPrint(source: Any, data: T) {}

    override fun onClone(data: T) {}

    override fun onCancel() {}

    override fun onAssign(data: T) {}

    override fun onAdd(data: T) {}

    override fun onExtraAction(action: String, data: T) {
        throw MyCollabException("Must be override by sub class")
    }
}