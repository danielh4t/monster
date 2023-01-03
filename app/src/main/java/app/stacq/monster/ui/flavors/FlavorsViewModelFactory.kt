@file:Suppress("UNCHECKED_CAST")

package app.stacq.monster.ui.flavors


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.stacq.monster.data.repository.flavors.FlavorRepository


class FlavorsViewModelFactory(private val flavorRepository: FlavorRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        with(modelClass) {
            when {
                isAssignableFrom(FlavorsViewModel::class.java) ->
                    return FlavorsViewModel(flavorRepository) as T
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
