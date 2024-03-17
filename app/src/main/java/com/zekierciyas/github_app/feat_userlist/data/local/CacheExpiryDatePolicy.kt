package com.zekierciyas.github_app.feat_userlist.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class CacheExpiryDatePolicy @Inject constructor(private val calendar: Calendar){

    companion object {
        /**
         * Represent the data caching lifecycle as minute
         */
        private const val EXPIRY_MINUTE = 5 * 1000
    }

    suspend fun checkExpiry(createdDate: Long): Flow<Boolean> = channelFlow {
        val currentTime = calendar.time
        val difference = currentTime.time - createdDate // Difference in milliseconds

        val minutesDifference = difference / 60

        val expired = minutesDifference > EXPIRY_MINUTE
        send(expired)
    }
}