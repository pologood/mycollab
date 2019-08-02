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
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.mycollab.core.utils

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
object ExceptionUtils {
    @JvmStatic
    fun <T> getExceptionType(e: Throwable, exceptionType: Class<T>): T? = when {
        exceptionType.isAssignableFrom(e.javaClass) -> e as T
        e.cause != null -> getExceptionType(e.cause!!, exceptionType)
        else -> null
    }
}
