package com.example.test.data

import com.example.test.model.NetworkState
import com.example.test.model.Photo

interface Repository {
    suspend fun getPhotos(
        id : String
    ) : List<Photo>?
}