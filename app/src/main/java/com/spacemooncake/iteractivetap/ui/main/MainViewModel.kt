package com.spacemooncake.iteractivetap.ui.main

import androidx.lifecycle.ViewModel
import com.spacemooncake.iteractivetap.data.repositopy.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    fun getVideoFromLocalStorage() = repository.getVideoFromLocalStorage()
}