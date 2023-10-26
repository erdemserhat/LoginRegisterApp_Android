package com.erdemserhat.loginregisterapp_android
import android.graphics.Color
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import com.erdemserhat.loginregisterapp_android.exceptions.IllegalArgumentByUserException

class FormValidator {
    companion object {
        fun validateForm(viewArray: Array<TextView>):Boolean? {
            var isValid:Boolean?=null
            try {
                for (element in viewArray) {
                    if (element.text == null ||
                        element.text?.length == 0 ||
                        element.text?.trim().toString().isEmpty() ||
                        element.text.contains(" ")
                    ) {
                        element.setTextColor(Color.RED)
                        element.setLinkTextColor(Color.RED)
                        isValid=false

                        val handler = Handler()
                        handler.postDelayed({
                            element.setTextColor(Color.WHITE)
                        }, 700)
                        throw IllegalArgumentByUserException(element.resources.getString(R.string.illegal_argument_exception_message))
                    }else{
                        //When there is no error
                        isValid=true

                    }
                }
            } catch (ex: IllegalArgumentByUserException) {
                Toast.makeText(viewArray.get(0)?.context,ex.message, Toast.LENGTH_SHORT).show()
            }
            return isValid
        }
    }
}
