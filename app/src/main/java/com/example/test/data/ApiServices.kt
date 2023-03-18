package com.example.test.data

import com.example.test.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("photos")
    suspend fun getPhotos(
        @Query("albumId") albumId : String
    ) : List<Photo>
}