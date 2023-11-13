package com.example.mapsdemoapp.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas

fun Int.toBitmap(
    context: Context,
): Bitmap {
    val drawable = context.getDrawable(this)
    drawable?.let {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    } ?: throw IllegalStateException("Cannot get drawable: $this")
}
