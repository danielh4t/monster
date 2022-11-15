package app.stacq.monster.data.repository.flavors


import android.util.Log
import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.model.toFlavorEntity
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import app.stacq.monster.data.source.remote.model.toFlavor
import kotlinx.coroutines.flow.*


class FlavorsRepository(
    private val localFlavorsDataSource: LocalFlavorsDataSource,
    private val remoteFlavorsDataSource: RemoteFlavorsDataSource
) {

    fun getFlavors(): Flow<List<Flavor>> {
        return remoteFlavorsDataSource.getFlavors()
            .map { flavors -> flavors.map { flavorDocument -> flavorDocument.toFlavor() } }
            .onEach { flavors ->
                flavors.map { flavor: Flavor ->
                    localFlavorsDataSource.storeFlavor(
                        flavor.toFlavorEntity()
                    )
                }
            }
            .catch { e ->
                Log.e("flavors", e.toString())
                emptyFlow<List<Flavor>>()
            }
    }

    fun getFlavor(name: String): Flow<Flavor?> {
        return remoteFlavorsDataSource.getFlavor(name).transform { flavor -> flavor?.toFlavor() }
    }

}