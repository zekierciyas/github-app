package com.zekierciyas.github_app.core.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = requireActivity().currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}