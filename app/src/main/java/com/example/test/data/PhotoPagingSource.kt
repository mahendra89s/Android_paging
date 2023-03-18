package com.example.test.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test.model.Photo
import retrofit2.Retrofit
import retrofit2.create
import java.lang.Exception
import javax.inject.Inject

class PhotoPagingSource @Inject constructor(
    retrofit: Retrofit
) : PagingSource<Int, Photo>(){
    private val apiService by lazy {
        retrofit.create(ApiServices::class.java)
    }
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try{
            val page = params.key ?: 1
            val response = apiService.getPhotos(page.toString())
            LoadResult.Page(
                data = response,
                prevKey = if(page == 1 ) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
