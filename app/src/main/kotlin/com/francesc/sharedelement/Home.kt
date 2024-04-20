package com.francesc.sharedelement

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.francesc.sharedelement.details.DetailsScreen
import com.francesc.sharedelement.list.ListScreen
import java.net.URLDecoder
import java.net.URLEncoder

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideInHorizontally { it } + fadeIn() },
            exitTransition = { slideOutHorizontally { -it } + fadeOut() },
            popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
            popExitTransition = { slideOutHorizontally { it } + fadeOut() },
        ) {
            composable(
                route = "list"
            ) {
                ListScreen(
                    animatedVisibilityScope = this@composable,
                    onItemClick = { item ->
                        val encoded = URLEncoder.encode(item, "UTF-8")
                        navController.navigate("details/$encoded")
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable(
                route = "details/{url}"
            ) { backstackEntry ->
                val encoded = backstackEntry.arguments?.getString("url") ?: error("No URL")
                val url = URLDecoder.decode(encoded, "UTF-8")
                DetailsScreen(
                    url = url,
                    animatedVisibilityScope = this@composable,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
