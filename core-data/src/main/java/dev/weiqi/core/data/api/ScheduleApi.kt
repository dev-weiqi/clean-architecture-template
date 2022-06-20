package dev.weiqi.core.data.api

import dev.weiqi.core.data.model.NetworkWeekSchedule
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {
    @GET("v1/guest/teachers/{teacherName}/schedule")
    suspend fun getWeekSchedule(
        @Path("teacherName") teacherName: String,
        @Query("started_at") startAt: String
    ): NetworkWeekSchedule
}