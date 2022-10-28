package app.stacq.monster.data.source.local.flavor

import androidx.room.Dao
import androidx.room.Query
import app.stacq.monster.data.model.FlavorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlavorDao {

    @Query("SELECT * FROM flavor")
    fun getFlavors(): Flow<FlavorEntity>
}