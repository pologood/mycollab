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
package com.mycollab.db.arguments

import com.mycollab.core.utils.BeanUtility

import java.io.Serializable

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
open class SearchField(var operation: String = AND) : Serializable {

    override fun toString(): String = BeanUtility.printBeanObj(this)

    companion object {
        private const val serialVersionUID = 1L

        const val OR = "OR"

        const val AND = "AND"
    }
}
