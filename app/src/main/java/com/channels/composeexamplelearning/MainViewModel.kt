package com.channels.composeexamplelearning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000)
            _isLoading.value = false
        }
    }
}