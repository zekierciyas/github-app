package com.zekierciyas.github_app.feat_userlist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zekierciyas.github_app.core.data.model.DataState
import com.zekierciyas.github_app.core.presentation.BaseFragment
import com.zekierciyas.github_app.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment: BaseFragment<UserListViewModel>() {

    override val viewModel: UserListViewModel by viewModels <UserListViewModel>()
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userListAdapter: UserListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewAction()
        observeViewModel()
    }

    private fun initRecyclerView() {
        userListAdapter = UserListAdapter()
        binding.rcProfileList.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    when(it) {
                        is DataState.Success -> {
                            userListAdapter.submitList(it.data)
                        }

                        is DataState.Error -> {
                            showErrorMessage(it.exception.toString(), 4000)
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun observeViewAction() {
        binding.customSearchBar.addTextChangedListener(watcher = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.search(s.toString())
            }
        })
    }
}