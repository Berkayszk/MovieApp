package com.example.movieapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.movieapp.api.RetrofitAPI
import com.example.movieapp.roomdb.MovieDatabase
import com.example.movieapp.util.Util.BASE_URL
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(context,MovieDatabase::class.java,"MovieDatabase")
        .build()
    @Singleton
    @Provides
    fun injectDao(database: MovieDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun injectRetrofitBuilder() : RetrofitAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }
}