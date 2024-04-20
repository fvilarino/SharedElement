package com.francesc.sharedelement

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

private val Lorem = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sed magna elit.
    Sed maximus, ante a sodales pulvinar, ante ligula laoreet quam, vitae fringilla erat tortor at 
    justo. Nam interdum lacus quam, a finibus dui eleifend at. Fusce eu pulvinar magna, sit amet 
    vehicula lacus. Sed at mauris vel nibh sodales tempor malesuada quis augue. Integer pretium 
    viverra posuere. Integer eget aliquet elit. Ut id erat sem. Suspendisse id pharetra nunc. 

    Proin lacinia metus a commodo viverra. Aliquam malesuada suscipit sapien. Nulla vitae nunc 
    sed risus pretium porttitor eget et lectus. Quisque sapien arcu, iaculis non semper vel, 
    tincidunt congue tellus. Aenean gravida, metus ut maximus efficitur, erat turpis placerat 
    ipsum, ac ultrices lectus nulla id sapien. Sed velit eros, finibus in pharetra sit amet, 
    venenatis a sapien. Proin varius lorem ut arcu bibendum aliquet. Mauris et leo venenatis, 
    bibendum magna ac, tempor est.
""".trimIndent()

@Composable
fun LoremIpsum(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = Lorem,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
}
