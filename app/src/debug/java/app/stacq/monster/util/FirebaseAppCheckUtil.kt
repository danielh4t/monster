package app.stacq.monster.util

import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

fun FirebaseAppCheck.installCheckProviderFactory() {
    installAppCheckProviderFactory(
        DebugAppCheckProviderFactory.getInstance()
    )
}