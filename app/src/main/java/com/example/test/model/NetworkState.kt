package com.example.test.model

sealed class NetworkState{
    data class Success(val photos : List<Photo>): NetworkState()
    object Failure : NetworkState()
    object Error : NetworkState()
}
