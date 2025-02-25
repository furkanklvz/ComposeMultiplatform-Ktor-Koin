package org.klavs.demo.ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path
import org.klavs.demo.getPlatform


class KtorService (private val client: HttpClient) {
    private val hostUrl = "streaming-availability.p.rapidapi.com"
    suspend fun getSearchResults(keyword: String): HttpResponse {
        val encodedQuery = keyword.trim()
        val response = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = hostUrl
                path("shows","search","title")
                parameters {
                    parameter("country", "tr")
                    parameter("title", encodedQuery)
                    parameter("series_granularity", "show")
                    parameter("output_language", "tr")
                }
            }
            header("x-rapidapi-key", RAPID_KEY)
        }
        getPlatform().log("ktorService", "requested url: ${response.request.url}")
        return response
    }

    suspend fun getMostRatedNetflixShows(): HttpResponse {
        val response = client.get{
            url {
                protocol = URLProtocol.HTTPS
                host = hostUrl
                path("shows","search","filters")
                parameters {
                    parameter("country", "tr")
                    parameter("series_granularity", "show")
                    parameter("order_direction", "desc")
                    parameter("order_by", "rating")
                    parameter("year_min", 1980)
                    parameter("genres_relation", "or")
                    parameter("output_language", "tr")
                    parameter("catalogs", "netflix")
                }
            }
            header("x-rapidapi-key", RAPID_KEY)
        }
        return response
    }

    suspend fun getLatestPrimeShows(): HttpResponse {
        val response = client.get{
            url {
                protocol = URLProtocol.HTTPS
                host = hostUrl
                path("shows","search","filters")
                parameters {
                    parameter("country", "tr")
                    parameter("series_granularity", "show")
                    parameter("year_min", 2023)
                    parameter("output_language", "tr")
                    parameter("catalogs", "prime.subscription")
                    parameter("rating_min", 60)
                }
            }
            header("x-rapidapi-key", RAPID_KEY)
        }
        return response
    }
}