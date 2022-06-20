package dev.weiqi.core.data.repo

import dev.weiqi.core.data.api.ScheduleApi
import dev.weiqi.core.data.model.NetworkWeekSchedule
import javax.inject.Inject

interface ScheduleRepo {
    suspend fun getWeekSchedule(teacherName: String, startAt: String): NetworkWeekSchedule
}

class ScheduleRepoImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRepo {
    override suspend fun getWeekSchedule(
        teacherName: String,
        startAt: String,
    ): NetworkWeekSchedule {
        return scheduleApi.getWeekSchedule(teacherName = teacherName, startAt = startAt)
    }
}