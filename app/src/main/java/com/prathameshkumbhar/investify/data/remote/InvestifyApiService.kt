package com.prathameshkumbhar.investify.data.remote

import com.prathameshkumbhar.investify.util.ApiConstants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface InvestifyApiService {
    @GET(ApiConstants.LIST_OF_STOCK_END_POINT)
    suspend fun getAllStockList(
        @Query("apikey") apikey: String = ApiConstants.API_KEY
    ): ResponseBody
}