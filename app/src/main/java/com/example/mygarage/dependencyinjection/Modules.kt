package com.example.mygarage.dependencyinjection

import com.example.mygarage.BuildConfig
import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.repository.Repository
import com.example.mygarage.ui.home.ArticleRecyclerViewAdapter
import com.example.mygarage.ui.home.HomeFragment
import com.example.mygarage.ui.home.HomeViewModel
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://users.metropolia.fi/~arsalans/"

private val apiModule: Module = module {
    single(createdAtStart = false) { get<Retrofit>().create(ArticlesApi::class.java) }
}
private val repositoryModule : Module = module {
    factory { Repository(get()) }
}
private val viewModelModule: Module = module {
    viewModel { HomeViewModel(get()) }
}
private val fragmentModule: Module = module {
    factory { HomeFragment() }
}



private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
val retrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
            }.build())
        }
    }
}

private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(get()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) krn sudah pakai --> Coroutines
        .client(get())
        .build()
}


private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.HEADERS
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
    }.build()
}

val modules =
    listOf(repositoryModule, viewModelModule, fragmentModule, apiModule, retrofitModule)
