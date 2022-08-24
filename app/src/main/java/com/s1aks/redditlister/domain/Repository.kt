package com.s1aks.redditlister.domain

import androidx.paging.PagingData
import com.s1aks.redditlister.data.RedditPost
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRedditData(pageSize: Int): Flow<PagingData<RedditPost>>
}