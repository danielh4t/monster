package app.stacq.monster.util

import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

fun FirebaseAppCheck.installCheckProviderFactory() {
    installAppCheckProviderFactory(
        DebugAppCheckProviderFactory.getInstance()
    )
}

fun String.storageUri(): Uri {
    val flavors = "https://firebasestorage.googleapis.com/v0/b/monster-stacq-debug.appspot.com/o/${Uri.encode(this)}?alt=media"
    return flavors.toUri()
}