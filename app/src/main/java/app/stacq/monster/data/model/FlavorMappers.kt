package app.stacq.monster.data.model

import app.stacq.monster.data.source.local.model.FlavorEntity

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