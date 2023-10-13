package com.faezolfp.githubapp.core.data

import com.faezolfp.githubapp.core.data.source.remote.RemoteDataSource
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import com.faezolfp.gomovieapp.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override fun example(): String {
        TODO("Not yet implemented")
    }

    override fun getDataUser(): Flow<List<ModelDataUser>> {
        TODO("Not yet implemented")
    }

    override fun getDetailUser(username: String): Flow<ModelDetailUser> {
        TODO("Not yet implemented")
    }

    override fun getRepoUser(username: String): Flow<List<ModelRepoUser>> {
        TODO("Not yet implemented")
    }
}