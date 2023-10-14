package com.faezolfp.githubapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faezolfp.gomovieapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {

    val queryChannel = MutableStateFlow("")

    val trackTextChange2 = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            it
        }.asLiveData()
    fun dataListAppGithub() = useCase.getDataUser().asLiveData()
    fun searchDataUser(login: String) = useCase.getSearch(login).asLiveData()
}