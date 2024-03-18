package com.zekierciyas.github_app.core.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.zekierciyas.github_app.core.presentation.widget.CustomErrorDialog
import com.zekierciyas.github_app.core.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseFragment<VM : BaseViewModel>  : Fragment() {

    protected abstract val viewModel: VM

    private var customErrorMessage: CustomErrorDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenNavigation()
        initErrorDialog()
    }

    protected fun showErrorMessage(message: String?, autoDismiss: Long? = null) {
        hideKeyboard()
        message?.let {
            customErrorMessage?.show(message = it)
        }
        autoDismiss?.let {
            lifecycleScope.launch(Dispatchers.Main) {
                delay(autoDismiss)
                customErrorMessage?.dismiss()
            }
        }
    }

    protected fun dismissErrorMessage() {
        customErrorMessage?.dismiss()
    }

    private fun listenNavigation() {
        lifecycleScope.launch {
            viewModel.navigateTo.collectLatest {
                when(it) {
                    is NavigationCommands.NavigateDirections -> {
                        navigate(it.direction)
                    }

                    is NavigationCommands.NavigateToHomeScreen -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun initErrorDialog() {
        customErrorMessage = CustomErrorDialog.create(requireContext())
    }

    private fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)
            ?.let { navigate(destination) }
    }
}