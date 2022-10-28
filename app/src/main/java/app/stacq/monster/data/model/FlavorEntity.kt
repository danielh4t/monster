package app.stacq.monster.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlavorEntity(
    @PrimaryKey
    val name: String?,
    val type: String?,
    val profile: String?,
    val product: String?,
    val tag: String?,
    val image: String?
)