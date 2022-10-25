package app.stacq.monster.ui.flavors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

@BindingAdapter("image", "reference")
fun ImageView.setImage(image: String, reference: StorageReference) {
    // Create a storage reference from our app
    val imageReference = reference.child(image)
    imageReference.downloadUrl.addOnSuccessListener {
        load(it) {
            scale(Scale.FIT)
        }
    }

}
