package com.faezolfp.githubapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faezolfp.gomovieapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    fun getDetailUser(username: String) = useCase.getDetailUser(username).asLiveData()
}