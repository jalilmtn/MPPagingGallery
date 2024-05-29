package com.example.mppaginggallery.ui.components


import android.content.Context
import android.graphics.drawable.Drawable
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.example.mppaginggallery.R

val THUMBNAIL_SIZE = Size(1000, 1000)

fun coilImageRequest(
    context: Context,
    data: Any?,
    placeHolder: Int = R.drawable.gray_square,
    error: Int = R.drawable.ic_error,
    size: Size = Size.ORIGINAL,
    memoryCachePolicy: CachePolicy = CachePolicy.ENABLED,
    retryHash: Int? = null,
    memoryCacheKey: String? = null,
    onStart: (request: ImageRequest) -> Unit = {},
    onCancel: (request: ImageRequest) -> Unit = {},
    onError: (request: ImageRequest, result: ErrorResult) -> Unit = { _, _ -> },
    onSuccess: (request: ImageRequest, result: SuccessResult) -> Unit = { _, _ -> },
): ImageRequest {
    val imageRequestBuilder = ImageRequest.Builder(context)
        .size(size)
        .placeholder(placeHolder)
        .setParameter("retry_hash", retryHash)
        .data(data = data)
        .error(error)
        .memoryCachePolicy(memoryCachePolicy)
        .memoryCacheKey(memoryCacheKey)
        .listener(onStart, onCancel, onError, onSuccess)
    return imageRequestBuilder.build()
}
