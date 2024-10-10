package com.rudy.instadiv.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TagViewModel(private val repository: TagRepository): ViewModel() {
    private val _tags = MutableStateFlow<List<String>>(emptyList())
    val tags: StateFlow<List<String>> = _tags

    private val _selectedTag = MutableStateFlow<String?>(null)
    val selectedTag: StateFlow<String?> = _selectedTag

    private val _isLoading = MutableStateFlow(true)  // Loading state
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        if(_tags.value.isEmpty())
        {

            fetchTags()
        }
    }

    private fun fetchTags() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedTags = repository.fetchTags()

            // Log the fetched tags
            Log.d("TagViewModel", "Fetched tags: $fetchedTags")

            // Update the state with the fetched tags
            _tags.value = fetchedTags
            _isLoading.value = false
        }
    }

    fun selectTag(tag: String) {
        _selectedTag.value = tag
    }

    fun clearTag() {
        _selectedTag.value = null
    }
}

