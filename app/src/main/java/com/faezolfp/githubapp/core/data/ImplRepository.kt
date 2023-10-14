package com.faezolfp.githubapp.core.data

import android.util.Log
import com.faezolfp.githubapp.core.data.source.local.LocalDataSource
import com.faezolfp.githubapp.core.data.source.remote.RemoteDataSource
import com.faezolfp.githubapp.core.data.source.remote.network.ApiResponse
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelRepoUser
import com.faezolfp.githubapp.core.utils.CheckData
import com.faezolfp.githubapp.core.utils.DataMapper
import com.faezolfp.gomovieapp.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImplRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
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

    override fun getSearch(username: String): Flow<Resource<List<ModelDataUser>>> {
        return flow {
            emit(Resource.Loading())
            val dataResponse = remoteDataSource.getSearch(username).first()
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

    override suspend fun addUser(dataUser: ModelDataUser) {
        localDataSource.addUser(DataMapper.mapModelDataUserToEntityDataUser(dataUser))
    }

    override suspend fun deleUser(dataUser: ModelDataUser) {
        Log.d("TRACKER", "delete di jalankan ${dataUser.login.toString()}")
        localDataSource.deleteUser(DataMapper.mapModelDataUserToEntityDataUser(dataUser))
    }

    override fun getListUserFormDb(): Flow<Resource<List<ModelDataUser>>> {
        return flow {
            emit(Resource.Loading())
            val dataDbResponse = localDataSource.getListUser().first()
            val dataResult = flowOf(
                Resource.Success(
                    DataMapper.mapDataUserEntityToModelDataUser(dataDbResponse)
                )
            )
            emitAll(dataResult)
        }
    }

    override fun checkDataIsAvailable(dataId: String): Flow<Boolean> {
        return flow {
            val dataIsAvailable = localDataSource.getListUser().first()
            val result = CheckData.dataIsAvailable(dataIsAvailable, dataId)
            emit(result)
        }
    }
}