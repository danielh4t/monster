package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import app.stacq.monster.data.repository.flavors.FlavorRepository
import app.stacq.monster.domain.model.Flavor
import kotlinx.coroutines.flow.Flow


class FlavorsViewModel(flavorRepository: FlavorRepository) : ViewModel() {

    val flavors: Flow<List<Flavor>> = flavorRepository.getFlavors()
}