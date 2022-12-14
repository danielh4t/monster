package app.stacq.monster.ui.flavors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.stacq.monster.databinding.ListItemFlavorBinding
import app.stacq.monster.domain.model.Flavor


class FlavorsAdapter(
    private val viewModel: FlavorsViewModel
) : ListAdapter<Flavor, FlavorsAdapter.ViewHolder>(FLAVOR_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flavor = getItem(position)
        if (flavor != null) {
            holder.bind(flavor, viewModel)
            holder.itemView.setOnClickListener { view ->
                val action = FlavorsFragmentDirections.actionFlavorsToFlavor(flavor.name)
                view.findNavController().navigate(action)
            }
        }
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
            binding.flavor = flavor
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

val FLAVOR_COMPARATOR = object : DiffUtil.ItemCallback<Flavor>() {

    override fun areItemsTheSame(oldItem: Flavor, newItem: Flavor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Flavor, newItem: Flavor): Boolean {
        return oldItem == newItem
    }
}
