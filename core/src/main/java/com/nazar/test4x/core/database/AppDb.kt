package com.nazar.test4x.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazar.test4x.core.database.character.CharacterDao
import com.nazar.test4x.core.model.character.Character

@Database(entities = [Character::class],version = 1,exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun characterDao(): CharacterDao


    companion object {
        @Volatile private var instance: AppDb? = null

        fun getDatabase(context: Context): AppDb =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDb::class.java, "mydb")
                .build()
    }
}