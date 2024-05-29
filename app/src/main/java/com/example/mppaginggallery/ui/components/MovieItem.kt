package com.example.mppaginggallery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.mppaginggallery.common.Constants
import com.example.mppaginggallery.domain.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current

        val request = remember(context, Constants.POSTER_BASE_URL + movie.posterPath) {
            coilImageRequest(
                context = context,
                data = Constants.POSTER_BASE_URL + movie.posterPath,
                size = THUMBNAIL_SIZE,
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = request),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(.7f)
                .clip(RoundedCornerShape(CornerSize(8.dp))),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.title, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)

    }

}