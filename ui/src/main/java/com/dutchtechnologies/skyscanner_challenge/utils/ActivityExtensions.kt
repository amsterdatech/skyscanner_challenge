package com.dutchtechnologies.skyscanner_challenge.utils

import android.app.Activity
import com.dutchtechnologies.skyscanner_challenge.model.SearchRequestForm

inline fun <reified T : Any> Activity.extra(key: String, defaultValue: T): T {
    return when (T::class) {
        String::class -> if (intent != null && intent.hasExtra(key)) intent.getStringExtra(key) as T else defaultValue
        Int::class -> intent?.getIntExtra(key, defaultValue as Int) as T
        Boolean::class -> intent?.getBooleanExtra(key, defaultValue as Boolean) as T
        Float::class -> intent?.getFloatExtra(key, defaultValue as Float) as T
        Long::class -> intent?.getLongExtra(key, defaultValue as Long) as T
        SearchRequestForm::class -> if (intent != null && intent.hasExtra(key)) intent?.getParcelableExtra<SearchRequestForm>(
            key
        ) as T else defaultValue
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}