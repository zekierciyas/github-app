package com.zekierciyas.github_app.core.data.db

import com.zekierciyas.github_app.core.util.TimeStampManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

class CacheExpiryDatePolicy @Inject constructor(
    private val timePicker: TimeStampManager,
    private val calendar: Calendar
){

    companion object {
        /**
         * Represent the data caching lifecycle as minute
         */
        private const val EXPIRY_MINUTE = 1
    }

    suspend fun checkExpiry(createdDate: String?): Flow<Boolean> = channelFlow {
        if (createdDate== null){
            send(true)
            return@channelFlow
        }
        val duration = calculateMinuteDifference(createdDate, timePicker.getCurrentTime())
        val isExpired = duration >= EXPIRY_MINUTE
        send(isExpired)
    }

    private fun calculateMinuteDifference(timeString1: String, timeString2: String): Long {
        val formatter = DateTimeFormatter.ofPattern(timePicker.dateFormat())
        val time1 = LocalDateTime.parse(timeString1, formatter)
        val time2 = LocalDateTime.parse(timeString2, formatter)

        val duration = Duration.between(time1, time2)
        return duration.toMinutes()
    }
}

