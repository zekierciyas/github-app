package com.zekierciyas.github_app.core.presentation.widget

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.zekierciyas.github_app.R

class CustomErrorDialog private constructor(context: Context) {
    private val dialog: AlertDialog
    private var errorMessage: CustomTextView
    init {
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setPadding(32, 32, 32, 32)

            val imageView = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_error)) // Set your error icon here
            }
            addView(imageView)

             errorMessage = CustomTextView(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(0, 16, 0, 0)
            }
            addView(errorMessage)
        }

        dialog = AlertDialog.Builder(context)
            .setView(layout)
            .setCancelable(false)
            .create()
    }

    fun show(message: String) {
        errorMessage.text = message
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

    companion object {
        fun create(context: Context): CustomErrorDialog {
            return CustomErrorDialog(context)
        }
    }
}
