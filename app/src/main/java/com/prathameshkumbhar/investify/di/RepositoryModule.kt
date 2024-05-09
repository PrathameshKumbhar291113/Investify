package com.prathameshkumbhar.investify.di

import com.prathameshkumbhar.investify.data.csv.CSVParser
import com.prathameshkumbhar.investify.data.csv.CompanyListingParser
import com.prathameshkumbhar.investify.data.repository.InvestifyRepositoryImpl
import com.prathameshkumbhar.investify.domain.model.CompanyListing
import com.prathameshkumbhar.investify.domain.repository.InvestifyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun  bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindInvestifyRepository(
        investifyRepositoryImpl: InvestifyRepositoryImpl
    ): InvestifyRepository
}