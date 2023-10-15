package com.faezolfp.githubapp.core.data.source.remote

import android.util.Log
import com.faezolfp.githubapp.core.data.source.remote.network.ApiResponse
import com.faezolfp.githubapp.core.data.source.remote.network.ApiService
import com.faezolfp.githubapp.core.data.source.remote.reponse.ItemsItem
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDataSearch
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDataUserItem
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDetailUser
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseRepoUserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getDataUser(): Flow<ApiResponse<List<ResponseDataUserItem>>> {
        return flow {
            try {
                val response = apiService.getDataUsers()
                val data = response
                if (data.isNotEmpty()) {
//                    emit(response)
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                    Log.d(TAG, response.toString())
                }
            } catch (e: Exception) {
                ApiResponse.Error(e.message.toString())
                Log.d(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getDetailUser(username: String): Flow<ApiResponse<ResponseDetailUser>> {
        return flow {
            try {
                val respones = apiService.getDetailUser(username)
                val data = respones
                if (data != null) {
                    emit(ApiResponse.Success(respones))
                } else {
                    emit(ApiResponse.Empty)
                    Log.d(TAG, respones.toString())
                }
            } catch (e: Exception) {
                ApiResponse.Error(e.message.toString())
                Log.d(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSearch(username: String): Flow<ApiResponse<ResponseDataSearch>> {
        return flow {
            try {
                val response = apiService.searchUser(username)
                val data = response
                if (data.items?.isNotEmpty() == true && data.items != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                    Log.d(TAG, response.toString())
                }
            } catch (e: Exception) {
                ApiResponse.Error(e.message.toString())
                Log.d(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getRespoUser(username: String): Flow<List<ResponseRepoUserItem>> {
        return flow {
            try {
                val response = apiService.getRepoUser(username)
                val data = response
                if (data.isNotEmpty()) {
                    emit(response)
                } else {
                    Log.d(TAG, response.toString())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    companion object {
        const val TAG = "RemoteDataSource"
    }
}