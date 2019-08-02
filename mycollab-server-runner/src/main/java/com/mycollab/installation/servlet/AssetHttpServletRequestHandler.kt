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
package com.mycollab.installation.servlet

import com.mycollab.core.utils.MimeTypesUtil
import org.slf4j.LoggerFactory
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class AssetHttpServletRequestHandler : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        val path = request.pathInfo
        var resourcePath = "assets$path"

        var inputStream = AssetHttpServletRequestHandler::class.java.classLoader.getResourceAsStream(resourcePath)

        if (inputStream == null) {
            resourcePath = "VAADIN/themes/mycollab$path"
            inputStream = AssetHttpServletRequestHandler::class.java.classLoader.getResourceAsStream(resourcePath)
        }

        if (inputStream != null) {
            response.setHeader("Content-Type", MimeTypesUtil.detectMimeType(path))
            response.setHeader("Content-Length", inputStream.available().toString())

            BufferedInputStream(inputStream).use { input ->
                BufferedOutputStream(response.outputStream).use { output ->
                    val buffer = ByteArray(8192)
                    var length = input.read(buffer)
                    while (length > 0) {
                        output.write(buffer, 0, length)
                        length = input.read(buffer)
                    }
                }
            }
        } else {
            LOG.error("Can not find resource has path $path")
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AssetHttpServletRequestHandler::class.java)
    }
}
