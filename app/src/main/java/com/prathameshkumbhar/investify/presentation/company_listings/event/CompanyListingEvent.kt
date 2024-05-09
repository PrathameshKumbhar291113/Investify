package com.prathameshkumbhar.investify.presentation.company_listings.event

sealed class CompanyListingEvent {
    data object Refresh: CompanyListingEvent()
    data class OnSearchQueryChange(val query: String): CompanyListingEvent()
}