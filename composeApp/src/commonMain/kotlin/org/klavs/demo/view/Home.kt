package org.klavs.demo.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material.icons.rounded.ImageSearch
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import kotlinx.coroutines.launch
import org.klavs.demo.data.entities.ShowData
import org.klavs.demo.data.entities.Tr
import org.klavs.demo.utils.Resource
import org.klavs.demo.viewmodel.HomeViewModel
import org.klavs.demo.getPlatform

@Composable
fun Home(viewModel: HomeViewModel) {
    val moviesResultResource by viewModel.productsResult.collectAsStateWithLifecycle()
    val mostRatedNetflixShowsResource by viewModel.mostRatedNetflixShowsResource.collectAsStateWithLifecycle()
    val latestPrimeShowsResource by viewModel.latestPrimeShowsResource.collectAsStateWithLifecycle()

    HomeContent(
        moviesResultResource,
        mostRatedNetflixShowsResource = mostRatedNetflixShowsResource,
        latestPrimeShowsResource = latestPrimeShowsResource,
        onSearchOut = viewModel::getMovies,
        backToTheMain = viewModel::resetResult
    )
}


@Composable
fun HomeContent(
    showsResultResource: Resource<List<ShowData>>,
    mostRatedNetflixShowsResource: Resource<List<ShowData>> = Resource.Idle,
    latestPrimeShowsResource: Resource<List<ShowData>> = Resource.Idle,
    onSearchOut: (String) -> Unit = {},
    backToTheMain: () -> Unit = {}
) {
    var keyword by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
        Scaffold  {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    TextField(keyword, onValueChange = { keyword = it },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                focusManager.clearFocus()
                                onSearchOut(keyword)
                            }
                        ),
                        modifier = Modifier
                            .widthIn(max = 700.dp)
                            .fillMaxWidth(0.8f),
                        singleLine = true,
                        leadingIcon =
                        if (!showsResultResource.isIdle()) {
                            {
                                IconButton(
                                    onClick = { backToTheMain() }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        } else null,
                        label = { Text("Movie or Series Name") },
                        trailingIcon = {
                            IconButton(
                                modifier = Modifier.pointerHoverIcon(
                                    PointerIcon.Hand
                                ),
                                onClick = {
                                    focusManager.clearFocus()
                                    onSearchOut(keyword)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "search"
                                )
                            }
                        }
                    )
                }
                Crossfade(
                    modifier = Modifier.weight(10f),
                    targetState = !showsResultResource.isIdle()
                ) { resultsEnabled ->
                    if (resultsEnabled) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(CardDefaults.shape)
                        ) {
                            Box {
                                when (showsResultResource) {
                                    is Resource.Error -> {
                                        Text(showsResultResource.throwable.toString())
                                    }

                                    Resource.Idle -> {}
                                    Resource.Loading -> {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(100.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator()
                                        }
                                    }

                                    is Resource.Success -> {
                                        val scrollState = rememberLazyListState()
                                        val shows = showsResultResource.data
                                        LazyColumn(
                                            state = scrollState,
                                        ) {
                                            items(shows) {
                                                ShowResultRow(it)
                                            }
                                        }
                                        getPlatform().VerticalScrollBar(
                                            modifier = Modifier.align(Alignment.CenterEnd)
                                                .fillMaxHeight(),
                                            listState = scrollState
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        var popupedShow by remember { mutableStateOf<ShowData?>(null) }
                        popupedShow?.let {
                            Dialog(
                                onDismissRequest = {popupedShow = null}
                            ){
                                DialogContent(it)
                            }
                        }
                        Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                            if (latestPrimeShowsResource.isLoading() && mostRatedNetflixShowsResource.isLoading()){
                                LinearProgressIndicator(
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                        .padding(15.dp)
                                )
                            }else {
                                when (mostRatedNetflixShowsResource) {
                                    is Resource.Error -> {
                                        getPlatform().log(
                                            "Home",
                                            mostRatedNetflixShowsResource.throwable.toString()
                                        )
                                    }
                                    is Resource.Success -> {
                                        CatalogRow(
                                            title = "Most Rated Netflix Shows",
                                            imageUrl = "https://media.movieofthenight.com/services/netflix/logo-light-theme.svg",
                                            mostRatedNetflixShowsResource.data,
                                            popupShow = { popupedShow = it },
                                        )
                                    }
                                    else -> {}
                                }
                                when (latestPrimeShowsResource) {
                                    is Resource.Error -> {
                                        getPlatform().log(
                                            "Home",
                                            latestPrimeShowsResource.throwable.toString()
                                        )
                                    }
                                    is Resource.Success -> {
                                        CatalogRow(
                                            title = "Latest Prime Shows",
                                            imageUrl = "https://media.movieofthenight.com/services/prime/logo-light-theme.svg",
                                            latestPrimeShowsResource.data,
                                            popupShow = { popupedShow = it }
                                        )
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }
                }

            }
        }
}

@Composable
private fun DialogContent(show: ShowData){
    Card {
        ListItem(
            leadingContent = {
                SubcomposeAsyncImage(
                    model = show.imageSet?.verticalPoster?.w360,
                    contentDescription = show.title,
                    contentScale = ContentScale.Crop,
                    error = {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = "error",
                        )
                    },
                    modifier = Modifier
                        .size(
                            height = 100.dp,
                            width = 80.dp
                        )
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                )
            },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            headlineContent = { Text(show.title, fontWeight = FontWeight.Bold) },
            overlineContent = { Text(show.releaseYear.toString() + " • " + show.runtime + " dk") },
            supportingContent = {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.TrendingUp,
                            contentDescription = "rating",
                            modifier = Modifier.size(20.dp)
                        )
                        Text((show.rating.toFloat() / 10f).toString() + "/10")
                    }
                    Text(
                        show.overview,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
        TabContent(show.streamingOptions.tr)
    }

}

@Composable
private fun ColumnScope.CatalogRow(title: String, imageUrl: String, showList: List<ShowData>, popupShow: (ShowData) -> Unit) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier.fillMaxWidth(0.98f)
            .align(Alignment.CenterHorizontally)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Netflix",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(end = 10.dp).size(50.dp)
            )
            Text(
                title, style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(10.dp)
            )
        }
        Box {
            LazyRow(
                state = listState,
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(showList) { show ->
                    ShowPoster(show, {popupShow(show)})
                }
            }

            Box(modifier = Modifier.align(Alignment.CenterEnd).offset(y = (-25).dp)) {
                getPlatform().RightArrowForScrolling {
                    scope.launch {
                        try {
                            listState.animateScrollToItem(listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size - 1)
                        } catch (e: Exception) {
                            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
                        }
                    }
                }
            }
            Box(modifier = Modifier.align(Alignment.CenterStart).offset(y = (-25).dp)) {
                getPlatform().LeftArrowForScrolling {
                    scope.launch {
                        try {
                            listState.animateScrollToItem(listState.firstVisibleItemIndex - listState.layoutInfo.visibleItemsInfo.size + 1)
                        } catch (e: Exception) {
                            listState.animateScrollToItem(0)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowPoster(show: ShowData, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val cardHeight by animateDpAsState(if (isHovered) 250.dp else 200.dp)
    ElevatedCard(
        onClick = {onClick()},
        modifier = Modifier.height(cardHeight)
            .aspectRatio(3 / 4f)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
    ) {
        SubcomposeAsyncImage(
            model = show.imageSet?.verticalPoster?.w480,
            contentDescription = show.title,
            contentScale = ContentScale.Crop,
            error = {
                Image(
                    imageVector = Icons.Rounded.ImageSearch,
                    contentDescription = "error",
                    modifier = Modifier.matchParentSize()
                        .background(Color.LightGray).padding(15.dp)
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ShowResultRow(show: ShowData) {
    var extended by remember { mutableStateOf(false) }
    Column {
        ListItem(
            modifier = Modifier.clickable {
                extended = !extended
            },
            leadingContent = {
                SubcomposeAsyncImage(
                    model = show.imageSet?.verticalPoster?.w360,
                    contentDescription = show.title,
                    contentScale = ContentScale.Crop,
                    error = {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = "error",
                        )
                    },
                    modifier = Modifier
                        .size(
                            height = 100.dp,
                            width = 80.dp
                        )
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                )
            },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            headlineContent = { Text(show.title, fontWeight = FontWeight.Bold) },
            overlineContent = { Text(show.releaseYear.toString() + " • " + show.runtime + " dk") },
            trailingContent = {
                Icon(
                    imageVector = if (extended) {
                        Icons.Rounded.KeyboardArrowDown
                    } else {
                        Icons.Rounded.KeyboardArrowUp
                    }, contentDescription = "expand"
                )

            },
            supportingContent = {
                val maxLines by animateIntAsState(
                    targetValue = if (extended) 10 else 2
                )
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.TrendingUp,
                            contentDescription = "rating",
                            modifier = Modifier.size(20.dp)
                        )
                        Text((show.rating.toFloat() / 10f).toString() + "/10")
                    }
                    Text(
                        show.overview,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = maxLines,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
        AnimatedVisibility(
            extended,
            label = "Movie Tab",
        ) {
            TabContent(show.streamingOptions.tr)
        }
    }

    HorizontalDivider()
}

@Composable
private fun TabContent(optionList: List<Tr>) {
    val uriHandler = LocalUriHandler.current
    Column(Modifier.fillMaxWidth()) {
        if (optionList.isNotEmpty()) {
            optionList.forEach { option ->
                ListItem(
                    leadingContent = {
                        SubcomposeAsyncImage(
                            model = option.service.imageSet.lightThemeImage,
                            contentDescription = option.service.name,
                            contentScale = ContentScale.Fit,
                            error = {
                                Icon(
                                    imageVector = Icons.Rounded.Warning,
                                    contentDescription = "error"
                                )
                            },
                            onError = {
                                getPlatform().log("Home", it.result.throwable.message ?: "")
                            },
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                    headlineContent = {
                        Text(option.service.name)
                    },
                    trailingContent = {
                        OutlinedButton(
                            onClick = {
                                uriHandler.openUri(option.link)
                            },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .size(21.dp),
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = "open in new"
                            )
                            Text("Watch", style = MaterialTheme.typography.labelSmall)
                        }
                    },
                    overlineContent = {
                        Text(option.type.replaceFirstChar { it.uppercase() })
                    },
                    supportingContent = if (option.expiresSoon) {
                        {
                            Text(
                                "Expires Soon",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    } else null
                )
            }

        } else {
            Text(
                "No options available",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

