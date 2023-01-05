package app.stacq.monster.data.repository.flavors

import app.stacq.monster.data.source.local.LocalFlavorDataSource
import app.stacq.monster.data.source.local.model.FlavorEntity
import app.stacq.monster.data.source.remote.RemoteFlavorDataSource
import app.stacq.monster.data.source.remote.model.FlavorDocument
import app.stacq.monster.domain.model.Flavor
import app.stacq.monster.domain.model.toFlavor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


class FlavorRepository(
    private val localFlavorDataSource: LocalFlavorDataSource,
    private val remoteFlavorDataSource: RemoteFlavorDataSource
) {

    fun getFlavors(): Flow<List<Flavor>> {
        return remoteFlavorDataSource.getFlavors().map { value: List<FlavorDocument> ->
            value.map {
                it.toFlavor()
            }
        }
    }

    fun getFlavor(name: String): Flow<Flavor?> {
        return remoteFlavorDataSource.getFlavor(name)
            .map { it?.toFlavor() }
            .catch { emptyFlow<Flavor>() }
    }

    suspend fun updateFlavor(flavorEntity: FlavorEntity) {
        localFlavorDataSource.updateFlavor(flavorEntity)
    }
}