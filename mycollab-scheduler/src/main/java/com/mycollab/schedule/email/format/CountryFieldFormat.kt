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
package com.mycollab.schedule.email.format

import com.hp.gagawa.java.elements.Span
import com.mycollab.core.utils.StringUtils
import com.mycollab.schedule.email.MailContext
import org.apache.commons.beanutils.PropertyUtils
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CountryFieldFormat(fieldName: String, displayName: Enum<*>) : FieldFormat(fieldName, displayName) {
    override fun formatField(context: MailContext<*>): String {
        val wrappedBean = context.wrappedBean
        return try {
            val countryCode = PropertyUtils.getProperty(wrappedBean, fieldName) as String
            val locale = Locale("", countryCode)
            Span().appendText(locale.getDisplayCountry(locale)).write()
        } catch (e: Exception) {
            Span().write()
        }
    }

    override fun formatField(context: MailContext<*>, value: String): String {
        return when {
            StringUtils.isBlank(value) -> Span().write()
            else -> {
                val locale = Locale("", value)
                Span().appendText(locale.getDisplayCountry(context.locale)).write()
            }
        }
    }
}