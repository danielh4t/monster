package app.stacq.monster.data.model

data class Beast(
    val id: Int,
    val name: String,
    val product: String,
    val flavor: String,
    val flavorProfile: String
) {

    var rating: Int = 1
    var unleashed: Boolean = false
}