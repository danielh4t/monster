package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import app.stacq.monster.data.repository.flavors.DefaultFlavorsRepository

class FlavorsViewModel : ViewModel() {

    val flavors = DefaultFlavorsRepository().getFlavors()
}
