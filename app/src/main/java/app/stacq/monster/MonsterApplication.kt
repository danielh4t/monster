package app.stacq.monster

import android.app.Application
import app.stacq.monster.data.source.local.AppDatabase

class MonsterApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}