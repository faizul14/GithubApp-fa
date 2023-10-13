package com.faezolfp.gomovieapp.core.domain.usecase

import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import com.faezolfp.gomovieapp.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplUseCase @Inject constructor(private val repository: Repository) : UseCase {
    override fun example(): String {
        return repository.example()
    }

    override fun getDataUser(): Flow<List<ModelDataUser>> {
        return repository.getDataUser()
    }

    override fun getDetailUser(username: String): Flow<ModelDetailUser> {
        return repository.getDetailUser(username)
    }

    override fun getRepoUser(username: String): Flow<List<ModelRepoUser>> {
        return repository.getRepoUser(username)
    }
}