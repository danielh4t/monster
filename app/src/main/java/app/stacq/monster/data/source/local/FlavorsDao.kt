package app.stacq.monster.data.source.local

import androidx.room.Dao
import androidx.room.Query
import app.stacq.monster.data.model.FlavorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlavorsDao {

    @Query("SELECT * FROM flavor")
    fun getFlavors(): Flow<List<FlavorEntity>>
}