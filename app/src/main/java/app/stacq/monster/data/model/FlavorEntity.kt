package app.stacq.monster.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "flavor")
data class FlavorEntity(
    @PrimaryKey
    val name: String,
    val product: String?,
    val type: String?,
    val profile: String?,
    val tag: String?,
    val image: String?,
    val unleashed: Boolean = false,
    val taste: Boolean?
)