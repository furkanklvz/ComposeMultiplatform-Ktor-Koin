package org.klavs.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.data.repository.wciwservice.WCIWServiceRepository
import org.klavs.demo.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val wciwServiceRepo: WCIWServiceRepository): ViewModel() {
    private val _searchResult = MutableStateFlow<Resource<List<ShowData>>>(Resource.Idle)
    val productsResult = _searchResult.asStateFlow()

    private val _mostRatedNetflixShowsResource = MutableStateFlow<Resource<List<ShowData>>>(Resource.Idle)
    val mostRatedNetflixShowsResource = _mostRatedNetflixShowsResource.asStateFlow()

    private val _latestPrimeShowsResource = MutableStateFlow<Resource<List<ShowData>>>(Resource.Idle)
    val latestPrimeShowsResource = _latestPrimeShowsResource.asStateFlow()

    init {
        getMostRatedNetflixShows()
        getLatestPrimeShows()
    }

    fun getMovies(keywords: String){
        _searchResult.value = Resource.Loading
        viewModelScope.launch(Dispatchers.Main) {
            _searchResult.value = wciwServiceRepo.getSearchResults(keywords)
        }
    }

    fun resetResult() {
        _searchResult.value = Resource.Idle
    }

    private fun getMostRatedNetflixShows(){
        _mostRatedNetflixShowsResource.value = Resource.Loading
        viewModelScope.launch(Dispatchers.Main) {
            _mostRatedNetflixShowsResource.value = wciwServiceRepo.getMostRatedNetflixShows()
        }
    }

    private fun getLatestPrimeShows(){
        _latestPrimeShowsResource.value = Resource.Loading
        viewModelScope.launch(Dispatchers.Main) {
            _latestPrimeShowsResource.value = wciwServiceRepo.getLatestPrimeShows()
        }
    }
}