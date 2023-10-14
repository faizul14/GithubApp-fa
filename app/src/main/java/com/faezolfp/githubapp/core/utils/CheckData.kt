package com.faezolfp.githubapp.core.utils

import com.faezolfp.githubapp.core.data.source.local.entity.DataUserEntity

object CheckData {

    fun dataIsAvailable(dataList: List<DataUserEntity>, dataId: String): Boolean{
        var resultBool: Boolean = false
        dataList.map {
            if (it.id.toString().equals(dataId.toString())){
                resultBool = true
            }
        }
        return resultBool
    }
}