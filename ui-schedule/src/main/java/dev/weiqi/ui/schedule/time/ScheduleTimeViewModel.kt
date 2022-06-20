package dev.weiqi.ui.schedule.time

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.weiqi.core.model.ScheduleState
import dev.weiqi.ui.schedule.formatter.ScheduleTimeFormatter
import kotlinx.coroutines.flow.flow

class ScheduleTimeViewModel @AssistedInject constructor(
    @Assisted val scheduleStates: List<ScheduleState>,
    private val timeFormatter: ScheduleTimeFormatter,
) : ViewModel() {

    val itemList = flow { emit(getScheduleTimeItemList()) }

    private fun getScheduleTimeItemList() = buildList {
        scheduleStates.forEachIndexed { index, current ->
            val prev = scheduleStates.getOrNull(index - 1)
            val timeSeparator = timeFormatter.createTimeSeparator(prev = prev?.startTime, next = current.startTime)
            if (timeSeparator != null) {
                add(ScheduleItem.TimeSeparator(timeSeparator))
            }
            add(ScheduleItem.Time(time = timeFormatter.format24HourAndMinute(current.startTime), isBooked = current.isBooked))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(scheduleStates: List<ScheduleState>): ScheduleTimeViewModel
    }
}
