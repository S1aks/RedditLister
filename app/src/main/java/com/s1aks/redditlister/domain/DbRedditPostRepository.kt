package com.s1aks.redditlister.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.s1aks.redditlister.api.RedditApi
import com.s1aks.redditlister.data.RedditPost
import com.s1aks.redditlister.db.RedditDb
import kotlinx.coroutines.flow.Flow

class DbRedditPostRepository(private val db: RedditDb, private val redditApi: RedditApi) :
    Repository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRedditData(pageSize: Int): Flow<PagingData<RedditPost>> = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PageKeyedRemoteMediator(db, redditApi)
    ) {
        db.posts().getAllPosts()
    }.flow
}