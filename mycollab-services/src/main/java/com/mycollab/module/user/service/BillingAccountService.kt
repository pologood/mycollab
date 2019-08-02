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
package com.mycollab.module.user.service

import com.mycollab.cache.IgnoreCacheClass
import com.mycollab.core.cache.CacheKey
import com.mycollab.core.cache.Cacheable
import com.mycollab.db.persistence.service.ICrudService
import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.SimpleBillingAccount

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@IgnoreCacheClass
interface BillingAccountService : ICrudService<Int, BillingAccount> {

    @Cacheable
    fun getBillingAccountById(@CacheKey accountId: Int): SimpleBillingAccount?

    fun getAccountByDomain(domain: String): SimpleBillingAccount?

    @Cacheable
    fun getAccountById(@CacheKey accountId: Int): BillingAccount?

    @Cacheable
    fun getTotalActiveUsersInAccount(@CacheKey accountId: Int): Int

    fun createDefaultAccountData(username: String, password: String, timezoneId: String, language: String, isEmailVerified: Boolean,
                                 isCreatedDefaultData: Boolean, sAccountId: Int)
}
