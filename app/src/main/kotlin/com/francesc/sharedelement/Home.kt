package com.francesc.sharedelement

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

@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list", modifier = modifier) {
        composable(
            route = "list"
        ) {
            ListScreen(
                onItemClick = { item ->
                    val encoded = URLEncoder.encode(item, "UTF-8")
                    navController.navigate("details/$encoded")
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(
            route = "details/{item}"
        ) { backstackEntry ->
            val encoded = backstackEntry.arguments?.getString("item") ?: error("No URL")
            val url = URLDecoder.decode(encoded, "UTF-8")
            DetailsScreen(
                url = url,
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
