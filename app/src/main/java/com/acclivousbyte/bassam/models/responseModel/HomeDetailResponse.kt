package com.acclivousbyte.bassam.models.responseModel

import com.acclivousbyte.bassam.models.genrelModels.Data
import com.acclivousbyte.bassam.models.utilityModels.BaseResponse

data class HomeDetailResponse(
    val Data: List<Data>,
) : BaseResponse()
