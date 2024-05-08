package com.prathameshkumbhar.investify.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prathameshkumbhar.investify.data.local.entity.CompanyListingEntity

@Dao
interface InvestifyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing (
       companyListingEntities : List<CompanyListingEntity>
    )

    @Query("Delete from companylistingentity")
    suspend fun clearCompanyListing()

}