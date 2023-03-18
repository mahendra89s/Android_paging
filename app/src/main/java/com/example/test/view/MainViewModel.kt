package com.example.test.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test.data.Repository
import com.example.test.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : Repository
) : ViewModel() {
    init {
        viewModelScope.launch {
            getPhoto()
        }
    }

    suspend fun getPhoto() : Flow<PagingData<Photo>> {
        return repo.fetchPhotos().cachedIn(viewModelScope)
    }
}