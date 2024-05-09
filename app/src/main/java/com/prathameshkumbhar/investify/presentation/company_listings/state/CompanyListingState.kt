package com.prathameshkumbhar.investify.presentation.company_listings.state

import com.prathameshkumbhar.investify.domain.model.CompanyListing

data class CompanyListingState(
    val companies: List<CompanyListing> = emptyList(),
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
