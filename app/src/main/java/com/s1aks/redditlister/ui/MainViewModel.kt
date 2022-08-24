package com.s1aks.redditlister.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.s1aks.redditlister.domain.Repository

class MainViewModel(
    repository: Repository
) : ViewModel() {
    companion object {
        const val PAGE_SIZE = 30
    }

    val posts = repository.getRedditData(PAGE_SIZE)
        .cachedIn(viewModelScope)
}