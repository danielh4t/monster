package app.stacq.monster.util

import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory

fun FirebaseAppCheck.installCheckProviderFactory() {
    installAppCheckProviderFactory(
        PlayIntegrityAppCheckProviderFactory.getInstance()
    );
}

fun String.storageUri(): Uri {
    val flavors = "https://firebasestorage.googleapis.com/v0/b/monster-stacq.appspot.com/o/${Uri.encode(this)}?alt=media"
    return flavors.toUri()
}