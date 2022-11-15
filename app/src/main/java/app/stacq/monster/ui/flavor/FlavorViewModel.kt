package app.stacq.monster.ui.flavor

import androidx.lifecycle.ViewModel
import app.stacq.monster.data.repository.flavors.FlavorsRepository

class FlavorViewModel(flavorsRepository: FlavorsRepository, flavorName: String) : ViewModel() {

    val flavor = flavorsRepository.getFlavor(flavorName)

}
