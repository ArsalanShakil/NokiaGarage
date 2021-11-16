package com.example.mygarage.dependencyinjection

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModules : Module = module {
}
private val viewModelModule: Module = module {
    viewModel { (get()) }
}

val modules =
    listOf(appModules, viewModelModule)
