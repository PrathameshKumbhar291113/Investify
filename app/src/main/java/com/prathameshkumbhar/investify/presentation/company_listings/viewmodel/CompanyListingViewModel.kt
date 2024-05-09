package com.prathameshkumbhar.investify.presentation.company_listings.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.investify.domain.repository.InvestifyRepository
import com.prathameshkumbhar.investify.presentation.company_listings.event.CompanyListingEvent
import com.prathameshkumbhar.investify.presentation.company_listings.state.CompanyListingState
import com.prathameshkumbhar.investify.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingViewModel @Inject constructor(
    private val repository: InvestifyRepository
) : ViewModel() {

    var state by mutableStateOf(CompanyListingState())

    private var searchJob: Job? = null

    init{
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingEvent) {
        when (event) {
            is CompanyListingEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }

            is CompanyListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote, query)
                .collect { result ->
                    when(result){

                        is NetworkResult.Loading ->{

                        }

                        is NetworkResult.Success ->{
                            result?.data?.let { listing ->
                                state = state.copy(
                                    companies = listing
                                )
                            }
                        }

                        is NetworkResult.Error ->{

                        }
                    }
                }
        }
    }

}