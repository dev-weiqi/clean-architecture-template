package dev.weiqi.ui.schedule.time

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.weiqi.core.common.bundle
import dev.weiqi.core.common.extensions.launchAndCollectIn
import dev.weiqi.core.common.extensions.viewModelProviderFactoryOf
import dev.weiqi.core.common.utils.viewBinding
import dev.weiqi.core.model.ScheduleState
import dev.weiqi.ui.schedule.R
import dev.weiqi.ui.schedule.databinding.FragmentScheduleTimeBinding
import javax.inject.Inject
import logcat.logcat

@AndroidEntryPoint
class ScheduleTimeFragment : Fragment(R.layout.fragment_schedule_time) {


    private val binding by viewBinding(FragmentScheduleTimeBinding::bind)

    private var scheduleStates by bundle<List<ScheduleState>>()
    private val adapter = ScheduleTimeAdapter()

    @Inject
    lateinit var vmFactory: ScheduleTimeViewModel.Factory

    private val viewModel by viewModels<ScheduleTimeViewModel> {
        viewModelProviderFactoryOf {
            vmFactory.create(scheduleStates = scheduleStates)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        viewModel.itemList.launchAndCollectIn(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        fun newInstance(scheduleStates: List<ScheduleState>): ScheduleTimeFragment {
            logcat { "scheduleStates $scheduleStates" }
            return ScheduleTimeFragment().apply {
                this.scheduleStates = scheduleStates
            }
        }
    }

}