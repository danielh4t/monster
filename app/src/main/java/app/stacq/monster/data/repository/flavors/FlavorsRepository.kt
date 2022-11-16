package app.stacq.monster.data.repository.flavors

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.local.model.FlavorEntity
import app.stacq.monster.data.source.local.model.toFlavor
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map


class FlavorsRepository(
    private val localFlavorsDataSource: LocalFlavorsDataSource,
    private val remoteFlavorsDataSource: RemoteFlavorsDataSource
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getFlavors(): Flow<PagingData<FlavorEntity>> {

        return Pager(
            config = PagingConfig(pageSize = 100),
            remoteMediator = FlavorsRemoteMediator(localFlavorsDataSource, remoteFlavorsDataSource)
        ) {
            localFlavorsDataSource.getFlavors()
        }.flow
    }

    fun getFlavor(name: String): Flow<Flavor?> {
        return localFlavorsDataSource.getFlavor(name)
            .map { flavorEntity -> flavorEntity?.toFlavor() }
            .catch { emptyFlow<Flavor>() }
    }

}