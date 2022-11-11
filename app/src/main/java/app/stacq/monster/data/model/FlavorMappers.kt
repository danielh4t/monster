package app.stacq.monster.data.model

import app.stacq.monster.data.source.local.model.FlavorEntity
import app.stacq.monster.data.source.remote.model.Flavor

fun FlavorEntity.toFlavor() = Flavor(
    name = name,
    type = type,
    profile = profile,
    product = product,
    tag = tag,
    image = image
)

fun Flavor.toFlavorEntity() = FlavorEntity(
    name = name ?: "",
    type = type,
    profile = profile,
    product = product,
    tag = tag,
    image = image,
    taste = null
)