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
package com.mycollab.common.service.impl

import com.mycollab.common.dao.SaveSearchResultMapper
import com.mycollab.common.dao.SaveSearchResultMapperExt
import com.mycollab.common.domain.SaveSearchResult
import com.mycollab.common.domain.SaveSearchResultExample
import com.mycollab.common.domain.criteria.SaveSearchResultCriteria
import com.mycollab.common.service.SaveSearchResultService
import com.mycollab.core.UserInvalidInputException
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
class SaveSearchResultServiceImpl(private val saveSearchResultMapper: SaveSearchResultMapper,
                                  private val saveSearchResultMapperExt: SaveSearchResultMapperExt) : DefaultService<Int, SaveSearchResult, SaveSearchResultCriteria>(), SaveSearchResultService {

    override val crudMapper: ICrudGenericDAO<Int, SaveSearchResult>
        get() = saveSearchResultMapper as ICrudGenericDAO<Int, SaveSearchResult>

    override val searchMapper: ISearchableDAO<SaveSearchResultCriteria>
        get() = saveSearchResultMapperExt

    override fun saveWithSession(record: SaveSearchResult, username: String?): Int {
        checkDuplicateEntryName(record)
        return super.saveWithSession(record, username)
    }

    override fun updateWithSession(record: SaveSearchResult, username: String?): Int {
        return super.updateWithSession(record, username)
    }

    private fun checkDuplicateEntryName(record: SaveSearchResult) {
        val ex = SaveSearchResultExample()
        ex.createCriteria().andSaccountidEqualTo(record.saccountid).andTypeEqualTo(record.type)
                .andQuerynameEqualTo(record.queryname)
        if (saveSearchResultMapper.countByExample(ex) > 0) {
            throw UserInvalidInputException("There is the query name existed")
        }
    }
}
