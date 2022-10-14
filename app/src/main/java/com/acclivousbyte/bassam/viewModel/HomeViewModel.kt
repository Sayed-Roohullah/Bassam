package com.acclivousbyte.bassam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acclivousbyte.bassam.models.responseModel.HomeDetailResponse
import com.acclivousbyte.bassam.utils.BassamViewUtil
import com.acclivousbyte.bassam.utils.Repository
import com.acclivousbyte.bassam.utils.wrapWithEvent
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository ): BaseViewModel() {
    companion object {
        private const val VALID_STATUS_CODE = 200
    }
    var maindetailData: MutableLiveData<HomeDetailResponse> = MutableLiveData()

    fun DetailData() {
        viewModelScope.launch {

            repository.homedetaildata().run {
                onSuccess {
                    if (it.Status == VALID_STATUS_CODE) {
                        if (it.Data != null) {
                            maindetailData.value = it
                        } else {
                            showSnackbarMessage("No data found")
                        }
                    } else if (it.Status == 500) {
                        showSnackbarMessage(BassamViewUtil.SERVER_NOT_RESPONDING_MESSAGE)
                    }
                    else {
                        _showHideProgressDialog.value = false.wrapWithEvent()
                        showSnackbarMessage(it.Message)
                    }
                }
                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(BassamViewUtil.INTERNET_CONNECTION_ERROR_MESSAGE)
                }
            }
        }
    }

}