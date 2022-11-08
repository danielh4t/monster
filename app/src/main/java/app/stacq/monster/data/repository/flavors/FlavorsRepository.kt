package app.stacq.monster.data.repository.flavors

import app.stacq.monster.data.model.Flavor
import app.stacq.monster.data.source.local.LocalFlavorsDataSource
import app.stacq.monster.data.source.remote.RemoteFlavorsDataSource
import kotlinx.coroutines.flow.Flow


class FlavorsRepository(
    private val localFlavorsDataSource: LocalFlavorsDataSource,
    private val remoteFlavorsDataSource: RemoteFlavorsDataSource
) {

    fun getFlavors() : Flow<List<Flavor>> {
        // fetch
       return remoteFlavorsDataSource.getFlavors()
//        // store
//        localFlavorsDataSource.
//        // execute
//        return localFlavorsDataSource.getFlavors()
    }
}