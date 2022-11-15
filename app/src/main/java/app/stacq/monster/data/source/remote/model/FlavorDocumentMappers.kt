package app.stacq.monster.data.source.remote.model

import app.stacq.monster.data.model.Flavor

fun FlavorDocument.toFlavor() = Flavor(
    name = name ?: "",
    type = type  ?: "",
    profile = profile  ?: "",
    product = product  ?: "",
    tag = tag  ?: "",
    image = image  ?: ""
)