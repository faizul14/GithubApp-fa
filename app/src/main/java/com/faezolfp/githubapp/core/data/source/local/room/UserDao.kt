package com.faezolfp.githubapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(dataUserEntity: DataUserEntity)
    @Query("DELETE FROM datauserentity WHERE id = :id")
    suspend fun deleteUser(id: Int)
    @Query("SELECT * FROM datauserentity")
    fun getListUser(): Flow<List<DataUserEntity>>

}