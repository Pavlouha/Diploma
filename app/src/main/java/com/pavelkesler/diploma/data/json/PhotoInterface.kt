package com.pavelkesler.diploma.data.json

import retrofit2.Call
import retrofit2.http.GET

interface PhotoInterface {

    @GET("realestate")
    fun getPhotoList(): Call<MutableList<PhotoModel>>
}