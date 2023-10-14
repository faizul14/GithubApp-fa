package com.faezolfp.githubapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DataUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uniqid")
    var uniqid: Int = 0,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String? = null,
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "login")
    val login: String? = null
): Parcelable