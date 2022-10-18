package app.stacq.monster.data.repository

import app.stacq.monster.data.model.Beast

interface BeastsRepository {

    suspend fun getBeasts(): List<Beast>
}