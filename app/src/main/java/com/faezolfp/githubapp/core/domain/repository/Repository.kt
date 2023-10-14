package com.faezolfp.gomovieapp.core.domain.repository

import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun example(): String
    fun getDataUser(): Flow<Resource<List<ModelDataUser>>>
    fun getSearch(username: String): Flow<Resource<List<ModelDataUser>>>

    fun getDetailUser(username: String): Flow<Resource<ModelDetailUser>>
    fun getRepoUser(username: String): Flow<List<ModelRepoUser>>
    suspend fun addUser(dataUser: ModelDataUser)
    suspend fun deleUser(dataUser: ModelDataUser)
    fun getListUserFormDb() : Flow<Resource<List<ModelDataUser>>>
    fun checkDataIsAvailable(dataId: String): Flow<Boolean>

}