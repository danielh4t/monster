package app.stacq.monster.data.source.remote.model

import androidx.annotation.Keep

@Keep
data class FlavorDocument(
    val id: Int? = null,
    val name: String? = null,
    val product: String? = null,
    val type: String? = null,
    val profile: String? = null,
    val tag: String? = null,
    val image: String? = null
)