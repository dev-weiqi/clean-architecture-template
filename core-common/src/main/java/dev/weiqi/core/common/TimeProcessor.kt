package dev.weiqi.core.common

import dev.weiqi.core.common.annotation.CourseDuration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeProcessor @Inject constructor(
    private val zoneId: ZoneId,
    private val locale: Locale,
    @CourseDuration private val courseDuration: Long
) {

    fun toZonedDateTime(instant: Instant): ZonedDateTime {
        return instant.atZone(zoneId)
    }

    fun firstDayOfWeek(instant: Instant): Instant {
        val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        val weekFields = WeekFields.of(locale).firstDayOfWeek
        return localDate.with(TemporalAdjusters.previousOrSame(weekFields))
            .atStartOfDay().toInstant(ZoneOffset.UTC)
    }

    fun lastDayOfWeek(instant: Instant): Instant {
        return firstDayOfWeek(instant).atZone(zoneId).plusDays(6).toInstant()
    }

    fun daysOfWeek(currentTime: Instant): List<Instant> {
        val firstDayOfWeek = firstDayOfWeek(currentTime)
        val lastDayOfWeek = lastDayOfWeek(currentTime)
        return daysOfWeek(firstDayOfWeek, lastDayOfWeek)
    }

    private fun daysOfWeek(start: Instant, end: Instant) = buildList {
        var startDays = start
        while (!startDays.isAfter(end)) {
            add(startDays)
            startDays = startDays.atZone(zoneId).plusDays(1).toInstant()
        }
    }

    fun splitCourseTime(start: Instant, end: Instant) = buildList {
        var startTime = start
        while (!startTime.isAfter(end)) {
            add(startTime)
            startTime = startTime.atZone(zoneId).plusMinutes(courseDuration).toInstant()
        }
    }

    fun previousWeek(current: Instant): Instant = current.atZone(zoneId).minusWeeks(1).toInstant()

    fun nextWeek(current: Instant): Instant = current.atZone(zoneId).plusWeeks(1).toInstant()

}
