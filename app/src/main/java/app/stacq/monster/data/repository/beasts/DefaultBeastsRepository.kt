package app.stacq.monster.data.repository.beasts

import app.stacq.monster.data.model.Beast
import app.stacq.monster.data.repository.BeastsRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultBeastsRepository(
    private val database: FirebaseFirestore = Firebase.firestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BeastsRepository {


    override suspend fun getBeasts() = withContext(ioDispatcher) {
        return@withContext emptyList<Beast>()
    }

    fun getFlavors(): Flow<List<Beast>> {
        return database.collection("flavors")
            .whereNotEqualTo("name", "")
            .snapshots()
            .map { value: QuerySnapshot -> value.toObjects(Beast::class.java) }
            .flowOn(ioDispatcher)
    }

}