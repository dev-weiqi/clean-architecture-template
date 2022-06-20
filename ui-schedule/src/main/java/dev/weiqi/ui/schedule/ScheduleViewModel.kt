package dev.weiqi.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.weiqi.core.common.TimeProcessor
import dev.weiqi.core.domain.GetWeekScheduleTimesUseCase
import dev.weiqi.core.domain.GetWeekScheduleTimesUseCaseParams
import dev.weiqi.core.model.ScheduleTime
import dev.weiqi.ui.schedule.formatter.ScheduleTimeFormatter
import java.time.Instant
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import logcat.asLog
import logcat.logcat

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getWeekScheduleTimesUseCase: GetWeekScheduleTimesUseCase,
    private val timeFormatter: ScheduleTimeFormatter,
    private val timeProcessor: TimeProcessor,
    private val timeNow: Instant,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState(currentTime = timeNow))
    val uiState = _uiState.asStateFlow()

    fun fetchCurrentWeekSchedule() {
        fetchWeekSchedule(ScheduleType.CURRENT)
    }

    fun fetchNextWeekSchedule() {
        fetchWeekSchedule(ScheduleType.NEXT)
    }

    fun fetchPreviousWeekSchedule() {
        fetchWeekSchedule(ScheduleType.PREVIOUS)
    }

    private fun fetchWeekSchedule(type: ScheduleType) {
        viewModelScope.launch {
            val currentTime = _uiState.value.currentTime
            val pivotTime = when (type) {
                ScheduleType.CURRENT -> currentTime
                ScheduleType.PREVIOUS -> timeProcessor.previousWeek(currentTime)
                ScheduleType.NEXT -> timeProcessor.nextWeek(currentTime)
            }
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                currentTime = pivotTime,
                hasPreviousSchedule = timeProcessor.firstDayOfWeek(pivotTime).isAfter(timeNow),
                weekPeriod = timeFormatter.formatWeekPeriod(pivotTime),
                scheduleTimes = timeProcessor.daysOfWeek(pivotTime).map { ScheduleTime(it, emptyList()) }
            )
            getWeekScheduleTimesUseCase(
                params = GetWeekScheduleTimesUseCaseParams(
                    teacherName = _uiState.value.teacherName,
                    startAt = pivotTime,
                )
            ).onSuccess {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    scheduleTimes = it,
                )
            }.onFailure {
                logcat { it.asLog() }
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    private enum class ScheduleType {
        CURRENT, PREVIOUS, NEXT
    }
}

data class ScheduleUiState(
    val isLoading: Boolean = false,
    val hasPreviousSchedule: Boolean = false,
    val teacherName: String = "jamie-coleman",
    val currentTime: Instant,
    val weekPeriod: String = "",
    val scheduleTimes: List<ScheduleTime> = emptyList()
)
