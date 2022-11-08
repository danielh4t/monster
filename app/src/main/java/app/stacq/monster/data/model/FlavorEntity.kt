package app.stacq.monster.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flavor")
data class FlavorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: String,
    val profile: String,
    val product: String,
    val tag: String,
    val taste: Boolean?
)