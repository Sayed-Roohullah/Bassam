package com.acclivousbyte.bassam.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.acclivousbyte.bassam.view.fragments.BaseFragment
import com.afollestad.materialdialogs.MaterialDialog

fun BaseFragment.setupProgressDialog(
    showHideProgress: LiveData<Event<Boolean>>,
    dialogHelper: MaterialDialogHelper
) {
    var mDialog: MaterialDialog? = null
    showHideProgress.observe(this, Observer {
        if (!it.consumed) it.consume()?.let { flag ->
            if (flag)
                mDialog?.show() ?: dialogHelper.showSimpleProgress(requireContext())
                    .also { dialog ->
                        mDialog = dialog
                    }
            else mDialog?.dismiss()
        }
    })
}
