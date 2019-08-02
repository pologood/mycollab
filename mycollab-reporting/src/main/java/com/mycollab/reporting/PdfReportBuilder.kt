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
package com.mycollab.reporting

import net.sf.dynamicreports.report.constant.PageOrientation
import net.sf.dynamicreports.report.constant.PageType
import net.sf.dynamicreports.report.exception.DRException

import java.io.OutputStream
import java.util.Locale

import net.sf.dynamicreports.report.builder.DynamicReports.cmp
import net.sf.dynamicreports.report.builder.DynamicReports.stl

/**
 * @author MyCollab Ltd
 * @since 5.4.7
 */
class PdfReportBuilder internal constructor(locale: Locale, fieldBuilder: RpFieldsBuilder, classType: Class<*>, parameters: Map<String, Any>) : AbstractReportBuilder(locale, fieldBuilder, classType, parameters) {

    override fun initReport() {
        reportBuilder
                .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
                .setHighlightDetailEvenRows(true)
                .setColumnStyle(stl.style(stl.style().setLeftPadding(4)))
                .setColumnTitleStyle(ReportStyles.instance().columnTitleStyle)
                .pageFooter(cmp.pageXofY())
                .setLocale(locale)
    }

    @Throws(DRException::class)
    override fun toStream(outputStream: OutputStream) {
        reportBuilder.toPdf(outputStream)
    }
}
