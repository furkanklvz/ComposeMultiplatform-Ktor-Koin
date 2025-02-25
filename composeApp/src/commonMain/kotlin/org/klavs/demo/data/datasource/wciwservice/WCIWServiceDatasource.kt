package org.klavs.demo.data.datasource.wciwservice

import org.klavs.demo.data.entities.FilteredShows
import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.utils.Resource

interface WCIWServiceDatasource {
    suspend fun getSearchResults(keywords: String): Resource<List<ShowData>>
    suspend fun getMostRatedNetflixShows(): Resource<List<ShowData>>
    suspend fun getLatestPrimeShows(): Resource<List<ShowData>>
}