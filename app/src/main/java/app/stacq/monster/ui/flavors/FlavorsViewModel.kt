package app.stacq.monster.ui.flavors

import androidx.lifecycle.ViewModel
import app.stacq.monster.data.repository.flavors.DefaultFlavorsRepository
import com.google.firebase.storage.FirebaseStorage

class FlavorsViewModel : ViewModel() {

    val flavors = DefaultFlavorsRepository().getFlavors()

    val storageReference = FirebaseStorage.getInstance().reference
}
