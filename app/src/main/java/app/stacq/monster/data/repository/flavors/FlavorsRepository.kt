package app.stacq.monster.data.repository.flavors

import app.stacq.monster.data.source.remote.model.Flavor
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


class FlavorsRepository(
    private val localFlavorsDataSource: LocalFlavorsDataSource,
    private val remoteFlavorsDataSource: RemoteFlavorsDataSource
) {

    fun getFlavors(): Flow<List<Flavor>> {
        return remoteFlavorsDataSource.getFlavors().catch { emit(emptyList()) }
    }

    fun getFlavor(name: String): Flow<Flavor?> {
        return remoteFlavorsDataSource.getFlavor(name)
    }

}