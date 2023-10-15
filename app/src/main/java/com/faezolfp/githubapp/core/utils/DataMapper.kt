package com.faezolfp.githubapp.core.utils

import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity
import com.faezolfp.githubapp.core.data.source.remote.reponse.ItemsItem
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDataUserItem
import com.faezolfp.githubapp.core.data.source.remote.reponse.ResponseDetailUser
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.domain.model.ModelDetailUser

object DataMapper {
    fun mapResponseDataUserToModelDataUser(input: List<ResponseDataUserItem>): List<ModelDataUser> {
        val dataUsertList = ArrayList<ModelDataUser>()
        input.map {
            val dataUser = ModelDataUser(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
            dataUsertList.add(dataUser)
        }
        return dataUsertList
    }

    fun mapResponseDataUserToModelDataUserForSearch(input: List<ItemsItem?>?): List<ModelDataUser> {
        val dataUsertList = ArrayList<ModelDataUser>()
        input?.map {
            val dataUser = ModelDataUser(
                avatarUrl = it?.avatarUrl,
                id = it?.id,
                login = it?.login,
            )
            dataUsertList.add(dataUser)
        }
        return dataUsertList
    }

    fun mapResponseDetailUserToModelDetailUser(input: ResponseDetailUser): ModelDetailUser {
        val dataDetailUser = ModelDetailUser(
            avatarUrl = input.avatarUrl,
            name = input.name,
            bio = input.bio,
            id = input.id,
            login = input.login
        )
        return dataDetailUser
    }

    fun mapDataUserEntityToModelDataUser(input: List<DataUserEntity>): List<ModelDataUser> {
        val dataUsertList = ArrayList<ModelDataUser>()
        input.map {
            val dataUser = ModelDataUser(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
            dataUsertList.add(dataUser)
        }
        return dataUsertList
    }

    fun mapModelDataUserToEntityDataUser(input: ModelDataUser): DataUserEntity {
        return DataUserEntity(
            avatarUrl = input.avatarUrl,
            id = input.id,
            login = input.login,
        )

    }

}