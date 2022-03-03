package com.android.snotes.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Util {
    companion object {
        fun showMessage(view: View, message: String) {
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }

        fun showToastMessage(context: Context, message: String) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}