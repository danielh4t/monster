package app.stacq.monster.ui.flavors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.stacq.monster.data.model.Flavor
import app.stacq.monster.databinding.ListItemFlavorBinding


class FlavorsAdapter(private val viewModel: FlavorsViewModel) :
    ListAdapter<Flavor, FlavorsAdapter.ViewHolder>(BeastDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel)
    }

    class ViewHolder private constructor(private val binding: ListItemFlavorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFlavorBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(flavor: Flavor, viewModel: FlavorsViewModel) {
            binding.beast = flavor
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }

}

class BeastDiffCallback : DiffUtil.ItemCallback<Flavor>() {

    override fun areItemsTheSame(oldItem: Flavor, newItem: Flavor): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Flavor, newItem: Flavor): Boolean {
        return oldItem == newItem
    }

}