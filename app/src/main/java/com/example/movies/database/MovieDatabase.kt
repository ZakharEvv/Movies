package com.example.moviescourse.Database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviescourse.Model.Movie.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {

        private var instance : MovieDatabase? = null

        fun getInstance(application: Application) : MovieDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    application,
                    MovieDatabase::class.java,
                    "movie.db").build()
            return instance as MovieDatabase
        }
    }
}