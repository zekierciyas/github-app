package com.zekierciyas.github_app.feat_userdetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.presentation.BaseFragment
import com.zekierciyas.github_app.core.util.ifNullDefault
import com.zekierciyas.github_app.databinding.FragmentUserDetailBinding
import com.zekierciyas.github_app.feat_userdetail.domain.model.UserDetailDomainModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment: BaseFragment<UserDetailViewModel>() {

    override val viewModel: UserDetailViewModel by viewModels <UserDetailViewModel>()

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        observeViewAction()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetailFlow.collect {
                    renderUI(state = it)
                }
            }
        }
    }

    private fun renderUI(state: DataState<UserDetailDomainModel>) {
        when(state) {
            is DataState.Success -> {
                binding.profileImage.loadImage(state.data.avatarUrl)
                binding.name.text = state.data.name
                binding.userName.text = state.data.login
                state.data.bio.ifNullDefault("...") {
                    binding.bioDescription.text = it.toString()
                }
                binding.bioDescription.text
                state.data.blog.ifNullDefault("..."){
                    binding.blog.text = it
                }
                state.data.email.ifNullDefault("..."){
                    binding.emailAddress.text = it.toString()
                }
                state.data.location.ifNullDefault("..."){
                    binding.location.text = it
                }
                state.data.followers.ifNullDefault("...."){
                    binding.followersCount.text = it.toString()
                }
                state.data.following.ifNullDefault("..."){
                    binding.followingCount.text = it.toString()
                }
                state.data.publicRepos.ifNullDefault("..."){
                    binding.repositories.text = it.toString()
                }
            }

            is DataState.Error -> {
                showErrorMessage(state.exception.toString(), 4000)
            }

            else -> {

            }
        }
    }

    private fun observeViewAction() {
        binding.backPress.setOnClickListener {
            viewModel.navigateToHomeScreen()
        }
    }
}