package com.acclivousbyte.bassam.models.genrelModels

import com.acclivousbyte.bassam.models.utilityModels.BaseResponse

data class PersonDetail(
    val Data: DataX,
    val Exceptions: String,
    val Message: String,
    val ResultType: Int,
    val Status: Int
)