package com.faezolfp.githubapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity

@Database(entities = [DataUserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}