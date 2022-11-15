package app.stacq.monster.data.source.local

import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.source.local.model.FlavorEntity
import app.stacq.monster.data.source.local.model.toFlavor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalFlavorsDataSource(
    private val database: FlavorsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getFlavors(): Flow<List<FlavorEntity>> {
        return database.getFlavors()
            .flowOn(ioDispatcher)
    }

    fun getFlavor(flavorName: String): Flow<FlavorEntity?> {
        return database.getFlavor(flavorName)
            .flowOn(ioDispatcher)
    }

    suspend fun storeFlavor(flavorEntity: FlavorEntity) = withContext(ioDispatcher) {
        database.storeFlavor(flavorEntity)
    }
}