package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import app.stacq.monster.data.repository.flavors.FlavorsRepository

class FlavorsViewModel(flavorsRepository: FlavorsRepository) : ViewModel() {

    val flavors = flavorsRepository.getFlavors()
}
