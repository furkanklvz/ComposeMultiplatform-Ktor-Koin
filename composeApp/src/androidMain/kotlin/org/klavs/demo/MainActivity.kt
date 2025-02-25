package org.klavs.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.klavs.demo.data.entities.Service
import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.data.entities.StreamingOptions
import org.klavs.demo.data.entities.Tr
import org.klavs.demo.ui.theme.ComposeDemoTheme
import org.klavs.demo.utils.Resource
import org.klavs.demo.view.HomeContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                App()
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    val movies = listOf(
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9,
            releaseYear = 2005,
            runtime = 120,
            showType = "Movie",
            streamingOptions = StreamingOptions(
                tr = listOf(
                    Tr(
                        service = Service(
                            name = "Netflix"
                        ),
                        link = "netflix.com"
                    )
                )
            ),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        ),
        ShowData(
            cast = listOf(),
            directors = listOf(),
            genres = listOf(),
            id = "Elijah",
            imdbId = "Neel",
            itemType = "Terrence",
            originalTitle = "Yves",
            overview = "Meleah",
            rating = 9476,
            releaseYear = 7863,
            runtime = 5142,
            showType = "Marli",
            streamingOptions = StreamingOptions(tr = listOf()),
            title = "Dawson",
            tmdbId = "Lashay"
        )
    )

    var resource by remember { mutableStateOf<Resource<List<ShowData>>>(Resource.Idle) }
    var resource2 by remember { mutableStateOf(Resource.Success(movies)) }

    MaterialTheme {
        HomeContent(showsResultResource = resource,
            latestPrimeShowsResource = resource2,
            mostRatedNetflixShowsResource = resource2,
            onSearchOut = {
                resource = Resource.Success(data = movies)
            })
    }
}