package com.example.mygarage.dependencyinjection

import com.example.mygarage.network.articles.ArticlesApi
import com.example.mygarage.repository.Repository
import com.example.mygarage.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

private val apiModule : Module = module {
    single<ArticlesApi> {}
}

private val repositoryModule : Module = module {
    single { Repository(get()) }
}
private val viewModelModule: Module = module {
    viewModel { HomeViewModel(get()) }
}

val modules =
    listOf(repositoryModule, viewModelModule)
