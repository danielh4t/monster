package app.stacq.monster.ui.beasts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.stacq.monster.data.model.Beast
import app.stacq.monster.databinding.BeastItemBinding

class BeastAdapter(private val viewModel: BeastsViewModel) :
    ListAdapter<Beast, BeastAdapter.ViewHolder>(BeastDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel)
    }

    class ViewHolder private constructor(private val binding: BeastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BeastItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(beast: Beast, viewModel: BeastsViewModel) {
            binding.beast = beast
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }

}

class BeastDiffCallback : DiffUtil.ItemCallback<Beast>() {

    override fun areItemsTheSame(oldItem: Beast, newItem: Beast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beast, newItem: Beast): Boolean {
        return oldItem == newItem
    }

}