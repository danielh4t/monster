package app.stacq.monster.data.source.local


import app.stacq.monster.data.source.local.model.FlavorEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class LocalFlavorDataSource(
    private val database: FlavorsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getFlavors(): Flow<List<FlavorEntity>> {
        return database.getFlavors()
    }

    fun getFlavor(flavorName: String): Flow<FlavorEntity?> {
        return database.getFlavor(flavorName)
            .flowOn(ioDispatcher)
    }

    suspend fun updateFlavor(flavorEntity: FlavorEntity) = withContext(ioDispatcher) {
        database.updateFlavor(flavorEntity)
    }
}