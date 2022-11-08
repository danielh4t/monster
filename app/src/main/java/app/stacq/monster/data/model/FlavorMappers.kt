package app.stacq.monster.data.model

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