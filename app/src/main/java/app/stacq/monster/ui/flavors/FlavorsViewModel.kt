package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import app.stacq.monster.data.repository.flavors.FlavorsRepository
import app.stacq.monster.data.source.local.model.toFlavor
import kotlinx.coroutines.flow.map

class FlavorsViewModel(flavorsRepository: FlavorsRepository) : ViewModel() {

    val flavorsPagingDataFlow = flavorsRepository.getFlavors()
        .map { pagingData ->
            pagingData.map { flavorEntity -> flavorEntity.toFlavor() }
        }
        .cachedIn(viewModelScope)

}