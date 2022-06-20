package dev.weiqi.ui.schedule.time

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.weiqi.ui.schedule.databinding.ItemScheduleTimeBinding
import dev.weiqi.ui.schedule.databinding.ItemScheduleTimeSeparatorBinding

class ScheduleTimeAdapter : ListAdapter<ScheduleItem, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ScheduleItem.ViewType.TimeSeparator.value -> TimeSeparatorViewHolder(
                binding = ItemScheduleTimeSeparatorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
            )
            ScheduleItem.ViewType.Time.value -> TimeViewHolder(
                binding = ItemScheduleTimeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
            )
            else -> error("viewType: $viewType not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        when (holder) {
            is TimeSeparatorViewHolder -> holder.bind(item as ScheduleItem.TimeSeparator)
            is TimeViewHolder -> holder.bind(item as ScheduleItem.Time)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.value
    }

    private class TimeSeparatorViewHolder(
        private val binding: ItemScheduleTimeSeparatorBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ScheduleItem.TimeSeparator) {
            binding.name.text = model.name
        }

    }

    private class TimeViewHolder(
        private val binding: ItemScheduleTimeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ScheduleItem.Time) {
            binding.time.text = model.time
            binding.card.isEnabled = !model.isBooked
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {

        override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
            return oldItem == newItem
        }
    }
}