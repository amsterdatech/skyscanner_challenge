package com.dutchtechnologies.skyscanner_challenge.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.load(url: String?, bitmapConfig: Bitmap.Config = Bitmap.Config.RGB_565) {
    val options = RequestOptions()
        .priority(Priority.NORMAL)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
    Glide.with(context).load(url).apply(options).into(this@load)
}

fun String.primaryTextBold(size: Int, color: Int, alignRight: Boolean = false): SpannableString {
    val span1 = SpannableString(this)
    span1.setSpan(AbsoluteSizeSpan(size), 0, this.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    span1.setSpan(ForegroundColorSpan(color), 0, this.length, 0)
    span1.setSpan(StyleSpan(Typeface.BOLD), 0, this.length, 0)// set color
    span1.setSpan(
        AlignmentSpan.Standard(if (alignRight) Layout.Alignment.ALIGN_OPPOSITE else Layout.Alignment.ALIGN_NORMAL),
        0,
        this.length,
        0
    )
    return span1
}

fun String.secondaryText(size: Int, color: Int, alignRight: Boolean = false): SpannableString {
    val span2 = SpannableString(this)
    span2.setSpan(AbsoluteSizeSpan(size), 0, this.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    span2.setSpan(ForegroundColorSpan(color), 0, this.length, 0)// set color
    span2.setSpan(
        AlignmentSpan.Standard(if (alignRight) Layout.Alignment.ALIGN_OPPOSITE else Layout.Alignment.ALIGN_NORMAL),
        0,
        this.length,
        0
    )
    return span2
}

fun Context.getDimensPixelSize(dimen: Int) = resources.getDimensionPixelSize(dimen)

fun Context.getDimens(dimen: Int) = resources.getDimension(dimen).toInt()

fun Context.getColorRes(colorRes: Int) = ResourcesCompat.getColor(resources, colorRes, null)

fun Context.getQuantityString(plural: Int, quantity: Int) = resources.getQuantityString(plural, quantity, quantity)


fun Date.formatToDayMonthName(): String {
    val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
    return sdf.format(this)
}

fun String.parseIsoDateFormat(): Date {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.parse(this)
}



