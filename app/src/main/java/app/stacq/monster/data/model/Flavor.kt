package app.stacq.monster.data.model

import androidx.annotation.Keep

@Keep
data class Flavor(
    val name: String,
    val product: String,
    val type: String,
    val profile: String,
    val tag: String,
    val image: String
)