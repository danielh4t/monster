package app.stacq.monster.data.model

import androidx.annotation.Keep
import app.stacq.monster.data.source.local.model.FlavorEntity

@Keep
data class Flavor(
    val id: Int,
    val name: String,
    val product: String,
    val type: String,
    val profile: String,
    val tag: String,
    val image: String,
    var rating: Int?,
    var unleashed: Int
)

fun Flavor.toFlavorEntity() = FlavorEntity(
    id = id,
    name = name,
    type = type,
    profile = profile,
    product = product,
    tag = tag,
    image = image,
    rating = rating,
    unleashed = unleashed,
)

fun FlavorEntity.toFlavor() = Flavor(
    id = id,
    name = name,
    type = type,
    profile = profile,
    product = product,
    tag = tag,
    image = image,
    rating = rating,
    unleashed = unleashed
)