package com.faezolfp.gomovieapp.core.domain.usecase

import com.faezolfp.githubapp.core.data.Resource
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import com.faezolfp.githubapp.core.utils.TransactionDbFor
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun example(): String
    fun getDataUser(): Flow<Resource<List<ModelDataUser>>>
    fun getDetailUser(username: String): Flow<Resource<ModelDetailUser>>
    fun getRepoUser(username: String): Flow<List<ModelRepoUser>>

    suspend fun transactionDb(transctionDbFor: TransactionDbFor, dataUser: ModelDataUser)
    fun getListUserFormDb(): Flow<Resource<List<ModelDataUser>>>

    fun checkDataIsAvailable(dataId: String): Flow<Boolean>
}