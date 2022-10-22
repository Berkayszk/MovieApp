package com.example.movieapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

}