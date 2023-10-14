package com.faezolfp.githubapp.core.data.source.remote.network

import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDataUser
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDataUserItem
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDetailUser
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseRepoUserItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    suspend fun getDataUsers(): List<ResponseDataUserItem>

    @Headers("Authorization: token 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")
    @GET("users/{login}")
    suspend fun getDetailUser(
        @Path("login") login: String
    ): ResponseDetailUser

    @Headers("Authorization: token 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")
    @GET("users/{login}/repos")
    suspend fun getRepoUser(
        @Path("login") login: String
    ): List<ResponseRepoUserItem>

    @Headers("Authorization: token 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")
    @GET("search/users")
    suspend fun searchUser(
        @Query("q") login: String
    ): List<ResponseDataUserItem>
}