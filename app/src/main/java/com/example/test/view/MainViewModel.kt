package com.example.test.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.Repository
import com.example.test.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : Repository
) : ViewModel() {
    private val _photos : MutableLiveData<List<Photo>> = MutableLiveData()
    val photos : LiveData<List<Photo>> = _photos

    //private val tempPhotos : MutableList<Photo> = mutableListOf()

    init {
        getPhotos("1")
    }

    fun getPhotos(id : String){
        viewModelScope.launch {
            val response = repo.getPhotos(id)

            if(!response.isNullOrEmpty()){
                _photos.value = response
            }
        }
    }

}