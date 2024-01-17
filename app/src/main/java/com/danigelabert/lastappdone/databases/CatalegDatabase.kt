package com.danigelabert.lastappdone.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danigelabert.lastappdone.model.Moble


@Database(
    entities = [Moble::class],
    version = 1,
    exportSchema = false
)


abstract class CatalegDatabase  : RoomDatabase(){
    abstract fun catalegDao() : CatalegDao

    companion object {

        @Volatile
        private var INSTANCE: CatalegDatabase? = null

        fun getDatabase(context: Context): CatalegDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CatalegDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CatalegDatabase::class.java,
                "cataleg_database"
            )
                .build()
        }
    }
}