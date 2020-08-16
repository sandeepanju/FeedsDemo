package com.example.feeddemo.di

import android.app.Application
import androidx.room.Room
import com.example.feeddemo.model.api.UserApi
import com.example.feeddemo.model.dao.UserDao
import com.example.feeddemo.model.repository.UserRepository
import com.example.feeddemo.model.room.AppDatabase
import com.example.feeddemo.viewmodel.UserViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val  viewModelModule = module {
    single { UserViewModel(get())}
}

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)

    single { provideUserApi(get()) }
}

val netModule = module{

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().cache(cache)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpBuilder.interceptors().add(httpLoggingInterceptor)
        return httpBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .baseUrl("http://surya-interview.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application)
            = Room.databaseBuilder(application, AppDatabase::class.java, "your.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


    fun provideDao(database: AppDatabase) = database.userDao


    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideUserRepository(api: UserApi, dao: UserDao) = UserRepository(api, dao)

    single { provideUserRepository(get(), get()) }
}