package com.example.test.data

import com.example.test.model.Photo
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    retrofit: Retrofit
) : Repository {
    private val apiService by lazy {
        retrofit.create(ApiServices::class.java)
    }

    override suspend  fun getPhotos(id: String): List<Photo>? = kotlin.runCatching {
        return@runCatching apiService.getPhotos(id)
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}