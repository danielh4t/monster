package app.stacq.monster.data.source.local

import androidx.room.*
import app.stacq.monster.data.source.local.model.FlavorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlavorsDao {

    @Query("SELECT * FROM flavor")
    fun getFlavors(): Flow<List<FlavorEntity>>

    @Query("SELECT * FROM flavor WHERE name=:flavorName")
    fun getFlavor(flavorName: String): Flow<FlavorEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlavors(flavorEntities: List<FlavorEntity>)

    @Query("SELECT id FROM flavor ORDER BY id LIMIT 1")
    suspend fun getLastFlavorId(): Int?

    @Update
    suspend fun updateFlavor(flavorEntity: FlavorEntity)
}