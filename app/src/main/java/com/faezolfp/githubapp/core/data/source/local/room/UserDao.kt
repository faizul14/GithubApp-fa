package com.faezolfp.githubapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
//import androidx.room.Query
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(productEntity: DataUserEntity)
    @Delete
    suspend fun deleteUser(productEntity: DataUserEntity)
    @Query("SELECT * FROM datauserentity")
    fun getListUser(): Flow<List<DataUserEntity>>

}