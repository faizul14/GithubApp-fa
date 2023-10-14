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

    @GET("search/users")
    @Headers("Authorization: token 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")
    suspend fun searchUser(
        @Query("q") login: String
    ): ResponseDataUser
}