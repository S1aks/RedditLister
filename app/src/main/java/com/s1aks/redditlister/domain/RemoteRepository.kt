package com.s1aks.redditlister.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.s1aks.redditlister.api.RedditApi
import com.s1aks.redditlister.data.RedditPost
import kotlinx.coroutines.flow.Flow

class RemoteRepository(
    private val redditApi: RedditApi
) : Repository {
    override fun getRedditData(pageSize: Int): Flow<PagingData<RedditPost>> =
        Pager(config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false,
            initialLoadSize = 20
        ), pagingSourceFactory = { DataPagingSource(redditApi) }
        ).flow
}