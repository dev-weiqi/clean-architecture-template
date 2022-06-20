package dev.weiqi.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.weiqi.core.common.extensions.launchAndCollectIn
import dev.weiqi.core.common.utils.viewBinding
import dev.weiqi.ui.schedule.databinding.FragmentScheduleBinding
import dev.weiqi.ui.schedule.formatter.ScheduleTimeFormatter
import javax.inject.Inject
import logcat.logcat

@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private val binding by viewBinding(FragmentScheduleBinding::bind)
    private val viewModel by viewModels<ScheduleViewModel>()

    private lateinit var pagerAdapter: SchedulePagerAdapter

    @Inject
    lateinit var timeFormatter: ScheduleTimeFormatter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.uiState.launchAndCollectIn(viewLifecycleOwner) { render(it) }
        viewModel.fetchCurrentWeekSchedule()
    }

    private fun setupView() = with(binding) {
        viewPager.adapter = SchedulePagerAdapter(this@ScheduleFragment).also {
            pagerAdapter = it
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val scheduleTab = pagerAdapter.getCurrentList()[position]
            tab.text = timeFormatter.formatDayOfWeekAndDayOfMonth(scheduleTab.date)
        }.attach()

        prev.setOnClickListener {
            viewModel.fetchPreviousWeekSchedule()
        }
        next.setOnClickListener {
            viewModel.fetchNextWeekSchedule()
        }
    }

    private fun render(uiState: ScheduleUiState) {
        logcat { "uiState $uiState" }
        if (uiState.isLoading) {
            binding.loading.show()
        } else {
            binding.loading.hide()
        }
        binding.title.text = uiState.weekPeriod
        pagerAdapter.submitList(uiState.scheduleTimes)
        binding.prev.isEnabled = uiState.hasPreviousSchedule
    }
}