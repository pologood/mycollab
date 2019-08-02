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
package com.mycollab.db.query

import com.mycollab.common.i18n.QueryI18nEnum.*
import com.mycollab.core.MyCollabException
import com.mycollab.db.arguments.OneValueSearchField
import com.mycollab.db.arguments.SearchField

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class PropertyParam(id: String, table: String, column: String) : ColumnParam(id, table, column) {

    fun buildSearchField(prefixOper: String, compareOper: String, value: Any): SearchField {
        val compareValue = valueOf(compareOper)
        return when (compareValue) {
            IS -> buildPropertyIs(prefixOper, value)
            IS_NOT -> buildPropertyIsNot(prefixOper, value)
            else -> throw MyCollabException("Not support")
        }
    }

    private fun buildPropertyIs(oper: String, value: Any): OneValueSearchField =
            OneValueSearchField(oper, "$table.$column = ", value)

    private fun buildPropertyIsNot(oper: String, value: Any): OneValueSearchField =
            OneValueSearchField(oper, "$table.$column <> ", value)

    companion object {

        @JvmField
        val OPTIONS = arrayOf(IS, IS_NOT)
    }
}
