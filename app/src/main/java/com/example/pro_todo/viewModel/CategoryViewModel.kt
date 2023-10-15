package com.example.pro_todo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pro_todo.model.Category
import com.example.pro_todo.model.CategoryWithTask
import com.example.pro_todo.repository.TodoRepository
import kotlinx.coroutines.launch

class CateViewModel(
    private val repository: TodoRepository
): ViewModel() {

    fun insertCate(category: Category) = viewModelScope.launch {
        repository.insertCate(category)
    }

    fun deleteCate(category: Category) = viewModelScope.launch {
        repository.deleteCate(category)
    }

    fun updateCate(category: Category) = viewModelScope.launch {
        repository.updateCate(category)
    }

    fun getAllCate():LiveData<List<Category>> = repository.getAllCate()
    fun getTasksOfCategory(categoryId: Int):LiveData<List<CategoryWithTask>> = repository.getTasksOfCategory(categoryId)
}
class CateViewModelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

