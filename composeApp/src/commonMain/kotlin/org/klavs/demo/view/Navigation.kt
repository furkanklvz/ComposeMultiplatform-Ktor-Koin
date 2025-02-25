package org.klavs.demo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import org.klavs.demo.data.entities.Routes
import org.klavs.demo.viewmodel.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.klavs.demo.getPlatform
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    if (getPlatform().name == "desktop"){
        DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp)
                            .padding(top = 15.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        NavigationDrawerItem(
                            label = { Text("Home") },
                            icon = {
                                Icon(imageVector = Icons.Rounded.Home, contentDescription = "home")
                            },
                            selected = true,
                            onClick = {}
                        )
                        Text(
                            "Services", modifier = Modifier.padding(15.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text("Netflix") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/netflix/logo-light-theme.svg",
                                    ),
                                    contentDescription = "netflix",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )
                        NavigationDrawerItem(
                            label = { Text("Amazon Prime") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/prime/logo-light-theme.svg",
                                    ),
                                    contentDescription = "prime",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )
                        NavigationDrawerItem(
                            label = { Text("Disney+") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/disney/logo-light-theme.svg",
                                    ),
                                    contentDescription = "disney plus",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )

                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }

                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "open navigation drawer"
                                )
                            }
                        },
                        title = {
                            Text("Where Can I Watch")
                        }
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                NavHost(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    startDestination = Routes.Home,
                    navController = navController
                ) {
                    composable<Routes.Home> {
                        val viewModel = koinViewModel<HomeViewModel>()
                        Home(viewModel)
                    }
                }

            }
        }
    }else{
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp)
                            .padding(top = 15.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        NavigationDrawerItem(
                            label = { Text("Home") },
                            icon = {
                                Icon(imageVector = Icons.Rounded.Home, contentDescription = "home")
                            },
                            selected = true,
                            onClick = {}
                        )
                        Text(
                            "Services", modifier = Modifier.padding(15.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        HorizontalDivider()
                        NavigationDrawerItem(
                            label = { Text("Netflix") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/netflix/logo-light-theme.svg",
                                    ),
                                    contentDescription = "netflix",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )
                        NavigationDrawerItem(
                            label = { Text("Amazon Prime") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/prime/logo-light-theme.svg",
                                    ),
                                    contentDescription = "prime",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )
                        NavigationDrawerItem(
                            label = { Text("Disney+") },
                            selected = false,
                            icon = {
                                Icon(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://media.movieofthenight.com/services/disney/logo-light-theme.svg",
                                    ),
                                    contentDescription = "disney plus",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            onClick = {}
                        )

                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }

                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "open navigation drawer"
                                )
                            }
                        },
                        title = {
                            Text("Where Can I Watch")
                        }
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                NavHost(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    startDestination = Routes.Home,
                    navController = navController
                ) {
                    composable<Routes.Home> {
                        val viewModel = koinViewModel<HomeViewModel>()
                        Home(viewModel)
                    }
                }

            }
        }
    }
}