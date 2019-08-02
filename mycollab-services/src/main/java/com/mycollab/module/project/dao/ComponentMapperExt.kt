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
package com.mycollab.module.project.dao

import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.module.project.domain.SimpleComponent
import com.mycollab.module.project.domain.criteria.ComponentSearchCriteria
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
@Mapper
interface ComponentMapperExt : ISearchableDAO<ComponentSearchCriteria> {

    fun findComponentById(componentId: Int): SimpleComponent?

    fun getTotalBillableHours(@Param("componentid") componentId: Int): Double

    fun getTotalNonBillableHours(@Param("componentid") componentId: Int): Double

    fun getRemainHours(@Param("componentid") componentId: Int): Double
}
