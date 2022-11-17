package app.stacq.monster.data.source.remote.model

import app.stacq.monster.data.source.local.model.FlavorEntity

fun FlavorDocument.toFlavorEntity() = FlavorEntity(
    id = id ?: 0,
    name = name ?: "",
    type = type ?: "",
    profile = profile ?: "",
    product = product ?: "",
    tag = tag ?: "",
    image = image ?: "",
    rating = null,
    unleashed = 0
)