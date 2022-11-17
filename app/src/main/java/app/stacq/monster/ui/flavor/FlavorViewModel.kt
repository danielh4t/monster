package app.stacq.monster.ui.flavor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.model.toFlavorEntity
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import kotlinx.coroutines.launch

class FlavorViewModel(private val flavorsRepository: FlavorsRepository, flavorName: String) :
    ViewModel() {

    val flavor = flavorsRepository.getFlavor(flavorName)

    fun minusUnleashed(flavor: Flavor) {
        if (flavor.unleashed > 0) {
            flavor.unleashed = flavor.unleashed - 1
            updateFlavor(flavor)
        }
    }

    fun plusUnleashed(flavor: Flavor) {
        if (flavor.unleashed < Int.MAX_VALUE) {
            flavor.unleashed = flavor.unleashed + 1
            updateFlavor(flavor)
        }
    }

    private fun updateFlavor(flavor: Flavor) {
        viewModelScope.launch {
            flavorsRepository.updateFlavor(flavor.toFlavorEntity())
        }
    }
}
