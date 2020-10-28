package com.nicoduarte.articles.ui.utils

import android.content.Context
import android.graphics.Color
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min


fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return format.format(this)
}

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }

fun View.invisible() { visibility = View.INVISIBLE }

fun Context.showMessage(container: View?, message: String) {
    container?.let {
        Snackbar.make(
            container,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}

fun convertDate(strDate: String?, formatOrigin: String = "yyyy-MM-dd'T'hh:mm:ss"): Long {
    return try{
        val sdfOrigin = SimpleDateFormat(formatOrigin, Locale.US)
        sdfOrigin.timeZone = TimeZone.getTimeZone("UTC")
        sdfOrigin.parse(strDate).time
    } catch (e: Exception) {
        0
    }
}

fun getRelativeTime(strDate: String): CharSequence {
    return DateUtils.getRelativeTimeSpanString(
            convertDate(strDate),
            System.currentTimeMillis(),
            1,
            DateUtils.FORMAT_ABBREV_RELATIVE)
}

fun colorPalette(color: Int): Int {
    val a: Int = 225
    val r = Color.red(color)
    val g = Color.green(color)
    val b = Color.blue(color)
    return Color.argb(
        a,
        min(r, 255),
        min(g, 255),
        min(b, 255)
    )
}

fun View.show() {
    alpha = 0f
    scaleX = 0f
    scaleY = 0f

    animate()?.cancel()
    animate().apply {
        scaleX(1f)
        scaleY(1f)
        alpha(1f)
        duration = 2000L
        interpolator = AccelerateDecelerateInterpolator()
        withEndAction { visible() }
    }
}