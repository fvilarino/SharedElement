package com.francesc.sharedelement.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.francesc.sharedelement.LoremIpsum

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 16.dp),
    ) {
        items(50) { item ->
            val width = 340 + item * 20
            val height = width * 4 / 3
            val url = "https://loremflickr.com/$width/$height"
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onItemClick(url)
                    }
                    .fillMaxWidth()) {

                AsyncImage(
                    model = url,
                    modifier = Modifier
                        .size(100.dp)
                        .sharedElement(
                            state = rememberSharedContentState(
                                key = "image-$url"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(Modifier.size(16.dp))
                LoremIpsum(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(
                                key = "text-$url"
                            ),
                            animatedVisibilityScope,
                        ),
                    maxLines = 3,
                )
            }
        }
    }
}
