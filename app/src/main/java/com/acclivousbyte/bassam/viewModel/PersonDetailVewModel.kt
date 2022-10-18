package com.acclivousbyte.bassam.viewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acclivousbyte.bassam.models.responseModel.PersonDataResponse
import com.acclivousbyte.bassam.utils.BassamViewUtil
import com.acclivousbyte.bassam.utils.Repository
import com.acclivousbyte.bassam.utils.wrapWithEvent
import kotlinx.coroutines.launch


class PersonDetailVewModel(private val repository: Repository): BaseViewModel() {
    companion object {
        private const val VALID_STATUS_CODE = 200
    }
    var personDetailData: MutableLiveData<PersonDataResponse> = MutableLiveData()


    fun personDetailData(id: String) {
        viewModelScope.launch {

            repository.personDetailData(id).run {
                onSuccess {
                    if (it.Status == VALID_STATUS_CODE) {
                        if (it.Data != null) {
                            personDetailData.value = it
                        } else {
                            showSnackbarMessage(BassamViewUtil.NO_PERSON_DATA_FOUND_MESSAGE)
                        }
                    } else if (it.Status == 500) {
                        showSnackbarMessage(BassamViewUtil.SERVER_NOT_RESPONDING_MESSAGE)

                    } else {
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