package org.klavs.demo.data.datasource.wciwservice


import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.ktor.KtorService
import org.klavs.demo.utils.Resource
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import org.klavs.demo.data.entities.FilteredShows

class WCIWServiceDatasourceImpl(private val service: KtorService) : WCIWServiceDatasource {
    override suspend fun getSearchResults(keywords: String): Resource<List<ShowData>> {
        return try {
            val response = service.getSearchResults(keywords)
            if (response.status.isSuccess()) {
                runCatching {
                    val shows = response.body<List<ShowData>>()
                    Resource.Success(shows)
                }.getOrElse {
                    // Log.e("Datasource", "parsing error: " + it.message)
                    Resource.Error(Exception("parsing error: " + it.message))
                }
            } else {
                val errorMessage =
                    runCatching { response.body<String>() }.getOrElse { response.status.description }
                Resource.Error(Exception(errorMessage))
            }
        } catch (e: Exception) {
            //Log.e("Datasource", "getProducts: ", e)
            Resource.Error(e)
        }
    }

    override suspend fun getMostRatedNetflixShows(): Resource<List<ShowData>> {
        return try {
            val response = service.getMostRatedNetflixShows()
            if (response.status.isSuccess()) {
                runCatching {
                    val shows = response.body<FilteredShows>()
                    Resource.Success(shows.shows)
                }.getOrElse {
                    Resource.Error(Exception("parsing error: " + it.message))
                }
            } else {
                val errorMessage =
                    runCatching { response.body<String>() }.getOrElse { response.status.description }
                Resource.Error(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getLatestPrimeShows(): Resource<List<ShowData>> {
        return try {
            val response = service.getLatestPrimeShows()
            if (response.status.isSuccess()) {
                runCatching {
                    val shows = response.body<FilteredShows>()
                    Resource.Success(shows.shows)
                }.getOrElse {
                    Resource.Error(Exception("parsing error: " + it.message))
                }
            } else {
                val errorMessage =
                    runCatching { response.body<String>() }.getOrElse { response.status.description }
                Resource.Error(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}