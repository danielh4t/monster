package app.stacq.monster.data.source.remote

import app.stacq.monster.data.model.Flavor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class RemoteFlavorsDataSource(
    private val database: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getFlavors(): Flow<List<Flavor>> {
        return database.collection("flavors")
            .whereNotEqualTo("name", "")
            .snapshots()
            .map { value: QuerySnapshot -> value.toObjects(Flavor::class.java) }
            .flowOn(ioDispatcher)
    }
}