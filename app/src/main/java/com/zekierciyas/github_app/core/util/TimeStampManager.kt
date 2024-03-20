package com.zekierciyas.github_app.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TimeStampManager @Inject constructor() {

    fun dateFormat(): String {
        return "yyyy-MM-dd HH:mm:ss"
    }

    fun getCurrentTime(): String = SimpleDateFormat(
        dateFormat(),
        Locale.getDefault()).format(Date())
}