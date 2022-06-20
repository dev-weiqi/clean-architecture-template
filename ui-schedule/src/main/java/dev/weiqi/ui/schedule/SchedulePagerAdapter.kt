package dev.weiqi.ui.schedule

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.weiqi.core.model.ScheduleTime
import dev.weiqi.ui.schedule.time.ScheduleTimeFragment

class SchedulePagerAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    private val list = mutableListOf<ScheduleTime>()

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun createFragment(position: Int): Fragment {
        return ScheduleTimeFragment.newInstance(
            list[position].scheduleStates
        )
    }

    fun getCurrentList() = list

    fun submitList(newList: List<ScheduleTime>) {
        val diff = DiffUtil.calculateDiff(PagerDiffUtil(list, newList))
        list.clear()
        list += newList
        diff.dispatchUpdatesTo(this)
    }

    private class PagerDiffUtil(
        private val oldList: List<ScheduleTime>,
        private val newList: List<ScheduleTime>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}