package com.acclivousbyte.bassam.backend

import com.acclivousbyte.bassam.models.responseModel.HomeDetailResponse
import com.acclivousbyte.bassam.models.responseModel.PersonDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("familymembers/{id}")
    suspend fun getPesonDetail(@Path("id") id: String ): PersonDataResponse

    @GET("familymembers/v2")
    suspend fun getDetailData(): HomeDetailResponse
}