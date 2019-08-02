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
package com.mycollab.module.project

import com.mycollab.core.MyCollabException

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
object ProjectLinkParams {
    private val PATTERN = Pattern.compile("^\\w{1,3}-\\d*$")

    fun isValidParam(param: String): Boolean = PATTERN.matcher(param).find()

    fun getProjectShortName(param: String): String {
        val index = param.indexOf("-")
        return when {
            index > 0 -> param.substring(0, index)
            else -> throw MyCollabException("Invalid param $param")
        }
    }

    fun getItemKey(param: String): Int {
        val index = param.indexOf("-")
        return if (index > 0) {
            Integer.parseInt(param.substring(index + 1))
        } else {
            throw MyCollabException("Invalid param $param")
        }
    }
}
