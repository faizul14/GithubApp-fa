package com.faezolfp.githubapp.core.data

import com.faezolfp.githubapp.core.data.source.remote.RemoteDataSource
import com.faezolfp.githubapp.core.data.source.remote.network.ApiResponse
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import com.faezolfp.githubapp.core.utils.DataMapper
import com.faezolfp.gomovieapp.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ImplRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override fun example(): String {
        TODO("Not yet implemented")
    }

    override fun getDataUser(): Flow<Resource<List<ModelDataUser>>> {
        return flow {
            emit(Resource.Loading())
            val dataResponse = remoteDataSource.getDataUser().first()
            when (dataResponse) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapResponseDataUserToModelDataUser(dataResponse.data)
                    val dataResult = flowOf(Resource.Success(data))
                    emitAll(dataResult)
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(dataResponse.errorMessage))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Loading())
                }
            }
        }
    }

    override fun getDetailUser(username: String): Flow<Resource<ModelDetailUser>> {
        return flow {
            emit(Resource.Loading())
            val dataDetailResponse = remoteDataSource.getDetailUser(username).first()
            when(dataDetailResponse) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapResponseDetailUserToModelDetailUser(dataDetailResponse.data)
                    val dataResult = flowOf(Resource.Success(data))
                    emitAll(dataResult)
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(dataDetailResponse.errorMessage))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Loading())
                }
            }
        }
    }


    override fun getRepoUser(username: String): Flow<List<ModelRepoUser>> {
        TODO("Not yet implemented")
    }
}