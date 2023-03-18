package com.example.test.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test.model.Photo
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    retrofit: Retrofit,
    private val photoPagingSource: PhotoPagingSource
) : Repository {
    private val apiService by lazy {
        retrofit.create(ApiServices::class.java)
    }
    override suspend fun fetchPhotos(): Flow<PagingData<Photo>> = Pager(
        pagingSourceFactory = {
            photoPagingSource
        },
        config = PagingConfig(pageSize = 20)
    ).flow
}