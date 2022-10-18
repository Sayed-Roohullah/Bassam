package com.acclivousbyte.bassam.utils

import com.acclivousbyte.bassam.backend.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository(private val apiServices: ApiServices) {

    private val dispatcher = Dispatchers.IO

    suspend fun homeDetailData() = withContext(dispatcher) {

        safeApiCall {
            Result.success(apiServices.getDetailData())
        }

    }
    suspend fun personDetailData(id:String) = withContext(dispatcher) {
         safeApiCall {
            Result.success(apiServices.getPesonDetail(id))
        }

    }

}