package app.stacq.monster.data.repository.beasts

import app.stacq.monster.data.model.Beast
import app.stacq.monster.data.repository.BeastsRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultBeastsRepository(
    private val database: FirebaseDatabase = Firebase.database,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BeastsRepository {


    override suspend fun getBeasts() = withContext(ioDispatcher) {
        return@withContext emptyList<Beast>()
    }
}