package com.prathameshkumbhar.investify.domain.repository

import com.prathameshkumbhar.investify.domain.model.CompanyListing
import com.prathameshkumbhar.investify.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface InvestifyRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<NetworkResult<List<CompanyListing>>>

}