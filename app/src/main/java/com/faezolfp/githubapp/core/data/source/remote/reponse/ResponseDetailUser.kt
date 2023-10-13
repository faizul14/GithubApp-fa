package com.faezolfp.githubapp.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class ResponseDetailUser(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("bio")
    val bio: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val login: String? = null
)
