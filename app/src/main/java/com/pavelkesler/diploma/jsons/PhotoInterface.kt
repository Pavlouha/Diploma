package com.pavelkesler.diploma.jsons

import retrofit2.Call
import retrofit2.http.GET

interface PhotoInterface {

    @GET("realestate")
    fun getPhotoList(): Call<MutableList<PhotoModel>>
}