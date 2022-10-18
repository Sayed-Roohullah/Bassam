package com.acclivousbyte.bassam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acclivousbyte.bassam.models.genrelModels.Data
import com.acclivousbyte.bassam.models.responseModel.HomeDetailResponse
import com.acclivousbyte.bassam.utils.BassamViewUtil
import com.acclivousbyte.bassam.utils.Repository
import com.acclivousbyte.bassam.utils.wrapWithEvent
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository ): BaseViewModel(){
    companion object {
        private const val VALID_STATUS_CODE = 200
    }

    lateinit var mainlist: List<Data>


    var maindetailData: MutableLiveData<HomeDetailResponse> = MutableLiveData()
    var mergefilter: MutableLiveData<List<Data>> = MutableLiveData()
    var searchfilter: MutableLiveData<List<Data>> = MutableLiveData()

    fun detailData() {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            repository.homeDetailData().run {
                onSuccess {
                    _showHideProgressDialog.value = false.wrapWithEvent()

                    if (it.Status == VALID_STATUS_CODE) {
                        if (it.Data != null) {
                            maindetailData.value = it
                            mainlist=it.Data
                        } else {
                            showSnackbarMessage(BassamViewUtil.NO_DATA_FOUND_MESSAGE)
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

    fun filter(text: String) {

        val filteredlist: ArrayList<Data> = ArrayList()

        for (item in mainlist) {
            if (item.name.contains(text, true)) {
                filteredlist.add(item)
            }
        }
        searchfilter.value = filteredlist

    }


       fun listMerge(isMSelect: Boolean,isFSelect: Boolean,isWSelect: Boolean) {
        val templist = ArrayList<Data>()
        if (isMSelect) {

            templist.addAll(mainlist.filter { it.gender.equals("0",true) })

        }
        if (isFSelect) {

            templist.addAll(mainlist.filter { it.gender.equals("1",true) })

        }
        if (isWSelect) {

            templist.addAll(mainlist.filter { it.is_worthy.equals("Yes",true) })

        }
        if (!isMSelect && !isFSelect && !isWSelect) {

            mergefilter.value = mainlist
        }else

            mergefilter.value = templist

    }



}