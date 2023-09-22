package com.example.pro_todo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pro_todo.model.Cate
import com.example.pro_todo.repository.CateRepository
import kotlinx.coroutines.launch

class CateViewModel(
    private val repository: CateRepository
): ViewModel() {

    fun insertCate(cate: Cate) = viewModelScope.launch {
        repository.insertCate(cate)
    }

    fun deleteCate(cate: Cate) = viewModelScope.launch {
        repository.deleteCate(cate)
    }

    fun updateCate(cate: Cate) = viewModelScope.launch {
        repository.updateCate(cate)
    }

    fun getAllCate():LiveData<List<Cate>> = repository.getAllCate()
}
class CateViewModelFactory(private val repository: CateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

