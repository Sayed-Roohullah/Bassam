package com.acclivousbyte.bassam.models.utilityModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    val Status: Int = 0
    var Message: String = ""

}