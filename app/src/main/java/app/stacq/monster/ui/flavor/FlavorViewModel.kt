package app.stacq.monster.ui.flavor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.stacq.monster.domain.model.Flavor
import app.stacq.monster.domain.model.toFlavorEntity
import app.stacq.monster.data.repository.flavors.FlavorRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class FlavorViewModel(private val flavorRepository: FlavorRepository, flavorName: String) :
    ViewModel() {

    val flavor = flavorRepository.getFlavor(flavorName)

    fun rate(rating: Float) {
        viewModelScope.launch {
            flavor.first()?.let {
                it.rating = rating.toInt()
                updateFlavor(it)
            }
        }
    }

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
            flavorRepository.updateFlavor(flavor.toFlavorEntity())
        }
    }
}
