package app.stacq.monster.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.stacq.monster.data.model.FlavorEntity

@Database(entities = [FlavorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun flavorDao(): FlavorsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "AppDatabase"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}