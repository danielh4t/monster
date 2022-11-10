package app.stacq.monster.data.model

import androidx.annotation.Keep

@Keep
data class Flavor(
    val name: String? = null,
    val type: String? = null,
    val profile: String? = null,
    val product: String? = null,
    val tag: String? = null,
    val image: String? = null
)