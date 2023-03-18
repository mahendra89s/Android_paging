package com.example.test.data

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.test.model.NetworkState
import com.example.test.model.Photo
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchPhotos() : Flow<PagingData<Photo>>
}