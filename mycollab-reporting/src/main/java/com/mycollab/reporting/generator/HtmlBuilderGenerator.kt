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
package com.mycollab.reporting.generator

import com.mycollab.reporting.ReportStyles
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression
import net.sf.dynamicreports.report.builder.component.ComponentBuilder

import net.sf.dynamicreports.report.builder.DynamicReports.cmp

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
class HtmlBuilderGenerator(private val expression: AbstractSimpleExpression<*>) : ComponentBuilderGenerator {

    override fun getCompBuilder(reportStyles: ReportStyles): ComponentBuilder<*, *> =
            cmp.text(expression).setStyle(reportStyles.htmlStyle)
}
