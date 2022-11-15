@file:Suppress("UNCHECKED_CAST")

package app.stacq.monster.ui.flavor


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.stacq.monster.data.repository.flavors.FlavorsRepository


class FlavorViewModelFactory(private val flavorsRepository: FlavorsRepository, private val flavorName: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        with(modelClass) {
            when {
                isAssignableFrom(FlavorViewModel::class.java) ->
                    return FlavorViewModel(flavorsRepository, flavorName) as T
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
