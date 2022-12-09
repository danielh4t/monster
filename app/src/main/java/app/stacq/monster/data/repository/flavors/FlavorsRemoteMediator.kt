package app.stacq.monster.data.repository.flavors

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.local.model.FlavorEntity
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import app.stacq.monster.data.source.remote.model.toFlavorEntity
import coil.network.HttpException
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class FlavorsRemoteMediator(
    private val localFlavorsDataSource: LocalFlavorsDataSource,
    private val remoteFlavorsDataSource: RemoteFlavorsDataSource
) : RemoteMediator<Int, FlavorEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FlavorEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                // load the first page
                LoadType.REFRESH -> 0
                // Load data at the end of the currently loaded data set
                LoadType.APPEND -> {
                    // If lastItem is `null` end reached
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
                // never prepend, since REFRESH will always load the first page in the list.
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
            }

            val flavors = remoteFlavorsDataSource.getFlavorsQuery(loadKey, state.config.pageSize)
                .map { flavors -> flavors.map { flavorDocument -> flavorDocument.toFlavorEntity() } }
                .map { flavorEntities: List<FlavorEntity> ->
                    localFlavorsDataSource.insertFlavors(
                        flavorEntities
                    )
                }
                .toList()

            val endOfPaginationReached = flavors.isEmpty()
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

}