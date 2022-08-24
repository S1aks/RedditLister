package com.s1aks.redditlister.di

import com.s1aks.redditlister.api.RedditApi
import com.s1aks.redditlister.db.RedditDb
import com.s1aks.redditlister.domain.DbRedditPostRepository
import com.s1aks.redditlister.domain.Repository
import com.s1aks.redditlister.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RedditApi.create() }
    single { RedditDb.create(get(), false) }
    single<Repository> { DbRedditPostRepository(get(), get()) }
    viewModel { MainViewModel(get()) }
}