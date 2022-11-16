package app.stacq.monster.data.source.local.model

import app.stacq.monster.data.model.Flavor

fun FlavorEntity.toFlavor() = Flavor(
    id = id,
    name = name,
    type = type,
    profile = profile,
    product = product,
    tag = tag,
    image = image
)