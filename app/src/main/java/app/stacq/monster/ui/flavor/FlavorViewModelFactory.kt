@file:Suppress("UNCHECKED_CAST")

package app.stacq.monster.ui.flavor


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.stacq.monster.data.repository.flavors.FlavorRepository


class FlavorViewModelFactory(private val flavorRepository: FlavorRepository, private val flavorName: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        with(modelClass) {
            when {
                isAssignableFrom(FlavorViewModel::class.java) ->
                    return FlavorViewModel(flavorRepository, flavorName) as T
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
