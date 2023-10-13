package com.faezolfp.githubapp.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class ResponseRepoUser(

    @field:SerializedName("ResponseRepoUser")
    val responseRepoUser: List<ResponseRepoUserItem?>? = null
)

data class ResponseRepoUserItem(

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
