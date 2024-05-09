package com.prathameshkumbhar.investify.data.repository

import coil.network.HttpException
import com.prathameshkumbhar.investify.data.csv.CSVParser
import com.prathameshkumbhar.investify.data.local.database.InvestifyDatabase
import com.prathameshkumbhar.investify.data.mapper.toCompanyListing
import com.prathameshkumbhar.investify.data.mapper.toCompanyListingEntity
import com.prathameshkumbhar.investify.data.remote.InvestifyApiService
import com.prathameshkumbhar.investify.domain.model.CompanyListing
import com.prathameshkumbhar.investify.domain.repository.InvestifyRepository
import com.prathameshkumbhar.investify.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvestifyRepositoryImpl @Inject constructor(
    private val apiService: InvestifyApiService,
    private val database: InvestifyDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
): InvestifyRepository{

    private val dao = database.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<NetworkResult<List<CompanyListing>>> {
        return flow {
            emit(NetworkResult.Loading())
            val localListing = dao.searchCompanyListing(query)
            emit(NetworkResult.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache){
                emit(NetworkResult.Loading())
                return@flow
            }
            val remoteListings = try {
                val response = apiService.getAllStockList()
                companyListingParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(NetworkResult.Error("Couldn't Load Data"))
                null
            }catch (e: HttpException){
                emit(NetworkResult.Error("Couldn't Load Data"))
                null
            }

            remoteListings?.let { listing ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listing.map { it.toCompanyListingEntity() }
                )
                emit(NetworkResult.Success(
                    data = dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
            }
        }
    }
}