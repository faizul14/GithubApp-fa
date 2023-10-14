package com.faezolfp.githubapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.faezolfp.githubapp.core.domain.model.ModelDataUser
import com.faezolfp.githubapp.core.utils.TransactionDbFor
import com.faezolfp.gomovieapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    fun getDetailUser(username: String) = useCase.getDetailUser(username).asLiveData()
    fun transaction(transactionDbFor: TransactionDbFor, dataUser: ModelDataUser) = viewModelScope.launch {
        useCase.transactionDb(transactionDbFor, dataUser)
    }
    fun dataIsAvailable(dataId: String) = useCase.checkDataIsAvailable(dataId).asLiveData()
}