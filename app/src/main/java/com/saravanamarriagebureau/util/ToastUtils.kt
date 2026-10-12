package com.saravanamarriagebureau.util

import android.content.Context
import android.widget.Toast

object ToastUtils {
    fun show(
        context: Context,
        message: String,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        Toast.makeText(
            context.applicationContext,
            message,
            duration
        ).show()
    }

    fun success(context: Context, message: String) {
        show(context, message)
    }

    fun error(context: Context, message: String) {
        show(context, message, Toast.LENGTH_LONG)
    }
}