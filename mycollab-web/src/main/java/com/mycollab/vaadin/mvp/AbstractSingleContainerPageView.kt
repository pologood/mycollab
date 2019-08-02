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
package com.mycollab.vaadin.mvp

import com.mycollab.vaadin.event.ViewEvent
import com.vaadin.ui.Component
import com.vaadin.ui.CssLayout
import com.vaadin.ui.CustomComponent

/**
 * @author MyCollab Ltd
 * @since 5.4.5
 */
open class AbstractSingleContainerPageView : CustomComponent(), PageView {

    private val contentLayout = CssLayout()

    init {
        contentLayout.setSizeFull()
        compositionRoot = contentLayout
        setSizeFull()
    }

    fun setContent(component: Component) {
        contentLayout.removeAllComponents()
        contentLayout.addComponent(component)
    }

    override fun <E> addViewListener(listener: PageView.ViewListener<E>) {
        addListener(ViewEvent.VIEW_IDENTIFIER, ViewEvent::class.java, listener, PageView.ViewListener.viewInitMethod)
    }
}
