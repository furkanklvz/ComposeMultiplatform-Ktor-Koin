package org.klavs.demo.data.repository.wciwservice

import org.klavs.demo.data.datasource.wciwservice.WCIWServiceDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.utils.Resource

class WCIWServiceRepositoryImpl(private val ds: WCIWServiceDatasource) : WCIWServiceRepository {
    override suspend fun getSearchResults(keywords: String) =
        withContext(Dispatchers.IO) { ds.getSearchResults(keywords) }

    override suspend fun getMostRatedNetflixShows() =
        withContext(Dispatchers.IO) { ds.getMostRatedNetflixShows() }

    override suspend fun getLatestPrimeShows() =
        withContext(Dispatchers.IO){ ds.getLatestPrimeShows() }
}