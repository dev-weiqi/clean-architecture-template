package dev.weiqi.core.model

import android.os.Parcelable
import java.time.Instant
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleTime(
    val date: Instant,
    val scheduleStates: List<ScheduleState>
) : Parcelable

@Parcelize
data class ScheduleState(
    val startTime: Instant,
    val isBooked: Boolean,
) : Parcelable

