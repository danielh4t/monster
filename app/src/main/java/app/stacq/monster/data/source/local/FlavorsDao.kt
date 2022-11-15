package app.stacq.monster.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.stacq.monster.data.source.local.model.FlavorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlavorsDao {

    @Query("SELECT * FROM flavor")
    fun getFlavors(): Flow<List<FlavorEntity>>


    @Query("SELECT * FROM flavor WHERE name=:flavorName")
    fun getFlavor(flavorName: String): Flow<FlavorEntity?>

    /**
     * Store a flavor.
     * If the flavor entity exists, ignore it.
     *
     * @param flavorEntity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun storeFlavor(flavorEntity: FlavorEntity)
}