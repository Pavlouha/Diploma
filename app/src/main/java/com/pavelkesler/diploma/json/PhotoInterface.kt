package com.pavelkesler.diploma.json

import retrofit2.Call
import retrofit2.http.GET

interface PhotoInterface {

    @GET("realestate")
    fun getPhotoList(): Call<MutableList<PhotoModel>>
}