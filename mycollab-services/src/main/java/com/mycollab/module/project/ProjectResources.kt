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
import org.slf4j.LoggerFactory
import java.lang.reflect.Method

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object ProjectResources {
    private val LOG = LoggerFactory.getLogger(ProjectResources::class.java)
    private var toHtmlMethod: Method? = null

    init {
        try {
            val resourceCls = Class.forName("com.mycollab.module.project.ui.ProjectAssetsManager")
            toHtmlMethod = resourceCls.getMethod("toHtml", String::class.java)
        } catch (e: Exception) {
            throw MyCollabException("Can not reload resource", e)
        }
    }

    fun getFontIconHtml(type: String): String = try {
        toHtmlMethod!!.invoke(null, type) as String
    } catch (e: Exception) {
        LOG.error("Can not get resource type $type")
        ""
    }
}
