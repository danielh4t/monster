package app.stacq.monster.data.source.local

import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.model.FlavorEntity
import app.stacq.monster.data.model.toFlavor
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

    fun getFlavors(): Flow<List<Flavor>> {
        return database.getFlavors()
            .map { values -> values.map { value -> value.toFlavor() } }
            .flowOn(ioDispatcher)
    }

    suspend fun storeFlavor(flavorEntity: FlavorEntity) = withContext(ioDispatcher) {
        database.storeFlavor(flavorEntity)
    }
}