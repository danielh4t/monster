package app.stacq.monster.ui.flavors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.stacq.monster.R
import coil.load
import coil.size.Scale

import coil.size.ViewSizeResolver
import com.google.firebase.storage.FirebaseStorage

@BindingAdapter("loadImage")
fun ImageView.setImage(image: String) {
    // Create a storage reference from our app
    val storageRef = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child(image)
    imageRef.downloadUrl.addOnSuccessListener {
        load(it) {
            scale(Scale.FIT)
        }
    }

}
