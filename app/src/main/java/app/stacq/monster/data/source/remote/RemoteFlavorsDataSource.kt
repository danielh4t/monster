package app.stacq.monster.data.source.remote

import app.stacq.monster.data.source.remote.model.FlavorDocument
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RemoteFlavorsDataSource(
    private val database: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getFlavors(): Flow<List<FlavorDocument>> {
        return database.collection("flavors")
            .snapshots()
            .map { value: QuerySnapshot -> value.toObjects(FlavorDocument::class.java) }
            .flowOn(ioDispatcher)
    }

    fun getFlavor(name: String): Flow<FlavorDocument?> {
        return database.collection("flavors")
            .document(name.lowercase())
            .snapshots()
            .map { value: DocumentSnapshot -> value.toObject(FlavorDocument::class.java) }
            .flowOn(ioDispatcher)
    }

    suspend fun getFlavorsQuery(after: Int?, limit: Int): List<FlavorDocument> {
        return database.collection("flavors")
            .orderBy("id")
            .startAt(after)
            .limit(limit.toLong())
            .get()
            .await()
            .toObjects(FlavorDocument::class.java)

    }

}