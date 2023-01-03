package app.stacq.monster.ui.flavors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.stacq.monster.util.storageUri
import coil.load
import coil.size.Scale

@BindingAdapter("loadImage")
fun ImageView.setImage(imageUrl: String?) {
    imageUrl?.let {
        val uri = imageUrl.storageUri()
        load(uri) {
            scale(Scale.FIT)
        }
    }
}
