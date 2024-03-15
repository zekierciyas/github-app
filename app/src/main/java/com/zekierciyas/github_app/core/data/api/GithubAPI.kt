package com.zekierciyas.github_app.core.data.api

import com.zekierciyas.github_app.feat_userdetail.data.model.UserDetailEntity
import com.zekierciyas.github_app.feat_userlist.data.model.UserListEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Github API requests
 *
 *  @see <a href="https://api.github.com/search/users?q=example">Example curl for query search</a>
 *  @see <a href="https://api.github.com/users/zekierciyas">Example curl for login</a>
 *
 */
interface GithubAPI {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): UserListEntity

    @GET("users/{login}")
    suspend fun getUserDetails(@Path("login") login: String): UserDetailEntity
}