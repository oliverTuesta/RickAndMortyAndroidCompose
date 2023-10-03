package com.spigi.rickandmortyandroidcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spigi.rickandmortyandroidcompose.data.model.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "character_db")
                .allowMainThreadQueries()
                .build()
        }
    }
}