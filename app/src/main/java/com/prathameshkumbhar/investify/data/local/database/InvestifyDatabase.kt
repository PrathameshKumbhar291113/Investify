package com.prathameshkumbhar.investify.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prathameshkumbhar.investify.data.local.dao.InvestifyDao
import com.prathameshkumbhar.investify.data.local.entity.CompanyListingEntity

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class InvestifyDatabase: RoomDatabase() {

    abstract val dao : InvestifyDao

}