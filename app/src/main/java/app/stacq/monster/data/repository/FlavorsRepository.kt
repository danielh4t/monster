package app.stacq.monster.data.repository

import app.stacq.monster.data.model.Flavor
import kotlinx.coroutines.flow.Flow

interface FlavorsRepository {

    fun getFlavors(): Flow<List<Flavor>>
}