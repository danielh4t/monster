package app.stacq.monster.data.source.local.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "flavor")
data class FlavorEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val product: String,
    val type: String,
    val profile: String,
    val tag: String,
    val image: String,
    val rating: Int?,
    val unleashed: Int = 0,

)