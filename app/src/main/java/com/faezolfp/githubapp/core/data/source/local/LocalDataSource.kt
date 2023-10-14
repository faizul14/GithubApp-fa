package com.faezolfp.githubapp.core.data.source.local

import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity
import com.faezolfp.githubapp.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val userDao: UserDao
) {
    fun getListUser(): Flow<List<DataUserEntity>> = userDao.getListUser()

    suspend fun addUser(dataUser: DataUserEntity) = userDao.addUser(dataUser)

    suspend fun deleteUser(dataUser: DataUserEntity) = userDao.deleteUser(dataUser)
}