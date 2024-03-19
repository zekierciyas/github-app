package com.zekierciyas.github_app.core.util

fun <T> T?.ifNullDefault(default: T, result : (T) -> Unit) {
    this?.let {
        result.invoke(it)
    }?: let {
        result.invoke(default)
    }
}