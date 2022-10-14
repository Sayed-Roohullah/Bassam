package com.acclivousbyte.bassam.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertMessageDialog: DialogFragment() {

    var message: String = ""
    var btnText: String = "Ok"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            arguments?.let {
                message = it.getString(MESSAGE, "")
            }
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, id ->
                    dialog.dismiss()
                    positiveButtonClick?.clickInvoke()
                }
                .setCancelable(false)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        val TAG: String = this.javaClass.simpleName
        const val MESSAGE: String = "message"
        var positiveButtonClick : PositiveButtonCallback? = null
        fun newInstance(message: String, positiveBtnClick: PositiveButtonCallback? = null): AlertMessageDialog {
            val dialogAlert = AlertMessageDialog()
            val bundle = Bundle()
            bundle.putString(MESSAGE, message)
            dialogAlert.arguments = bundle
            positiveButtonClick = positiveBtnClick
            return dialogAlert
        }
    }

    interface PositiveButtonCallback{
        fun clickInvoke()
    }
}