package app.stacq.monster.ui.beasts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.stacq.monster.data.model.Beast
import app.stacq.monster.data.repository.beasts.DefaultBeastsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeastsViewModel : ViewModel() {

    val beasts: List<Beast> = listOf()
    val flavors = DefaultBeastsRepository().getFlavors()

}