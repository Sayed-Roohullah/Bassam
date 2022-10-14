package com.acclivousbyte.bassam.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acclivousbyte.bassam.utils.Event
import kotlinx.coroutines.Dispatchers

open class BaseViewModel(): ViewModel() {
    val dispatcher = Dispatchers.Default

    var _showHideProgressDialog = MutableLiveData<Event<Boolean>>()
    val showHideProgressDialog: LiveData< Event<Boolean>> get()  = _showHideProgressDialog

    val snackbarMessage = MutableLiveData< Event<String>>()
    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value =  Event(message)
    }
}