package app.stacq.monster.ui.flavors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import com.google.firebase.storage.FirebaseStorage

@BindingAdapter("loadImage")
fun ImageView.setImage(image: String?) {
    // Create a storage reference from our app
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = image?.let { storageRef.child(it) }
    imageRef?.downloadUrl?.addOnSuccessListener {
        load(it) {
            scale(Scale.FIT)
        }
    }

}
