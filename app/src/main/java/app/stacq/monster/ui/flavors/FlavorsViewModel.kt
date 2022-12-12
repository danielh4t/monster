package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import app.stacq.monster.data.model.toFlavor
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import kotlinx.coroutines.flow.map

class FlavorsViewModel(flavorsRepository: FlavorsRepository) : ViewModel() {

    val flavors = flavorsRepository.getFlavors()
        .map { pagingData ->
            pagingData.map { flavorEntity -> flavorEntity.toFlavor() }
        }
        .cachedIn(viewModelScope)

}