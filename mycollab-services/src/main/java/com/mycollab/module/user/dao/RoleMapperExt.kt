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
package com.mycollab.module.user.dao

import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.module.user.domain.SimpleRole
import com.mycollab.module.user.domain.criteria.RoleSearchCriteria
import org.apache.ibatis.annotations.Mapper

@Mapper
interface RoleMapperExt : ISearchableDAO<RoleSearchCriteria> {
    fun findById(roleId: Int): SimpleRole?
}
