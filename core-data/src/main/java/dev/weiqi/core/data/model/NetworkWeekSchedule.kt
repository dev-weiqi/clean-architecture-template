package dev.weiqi.core.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.weiqi.core.data.seriallize.InstantType
import java.time.Instant

@JsonClass(generateAdapter = true)
data class NetworkWeekSchedule(
    @Json(name = "available")
    val availableTimes: List<NetworkScheduleTime>,

    @Json(name = "booked")
    val bookedTimes: List<NetworkScheduleTime>,
)

@JsonClass(generateAdapter = true)
data class NetworkScheduleTime(
    @Json(name = "start")
    @InstantType
    val startTime: Instant,

    @InstantType
    @Json(name = "end")
    val endTime: Instant,
)
