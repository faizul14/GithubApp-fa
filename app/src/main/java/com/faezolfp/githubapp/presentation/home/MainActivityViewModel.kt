package com.faezolfp.githubapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faezolfp.gomovieapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    fun dataListAppGithub() = useCase.getDataUser().asLiveData()
}