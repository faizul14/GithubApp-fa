package com.faezolfp.gomovieapp.core.domain.repository

import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun example(): String
    fun getDataUser(): Flow<List<ModelDataUser>>
    fun getDetailUser(username: String): Flow<ModelDetailUser>
    fun getRepoUser(username: String): Flow<List<ModelRepoUser>>
}