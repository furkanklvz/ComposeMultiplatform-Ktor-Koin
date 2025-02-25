package org.klavs.demo.data.repository.wciwservice

import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.utils.Resource

interface WCIWServiceRepository {
    suspend fun getSearchResults(keywords: String): Resource<List<ShowData>>
    suspend fun getMostRatedNetflixShows(): Resource<List<ShowData>>
    suspend fun getLatestPrimeShows(): Resource<List<ShowData>>
}