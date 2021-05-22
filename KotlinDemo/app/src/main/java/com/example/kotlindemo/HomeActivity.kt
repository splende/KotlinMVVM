package com.example.kotlindemo

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.databinding.HomeActivityBinding
import kotlinx.coroutines.launch

class HomeActivity: AppCompatActivity() {

    // View Binding
    private val binding: HomeActivityBinding by lazy { HomeActivityBinding.inflate(layoutInflater) }

    //ViewModel class
    private val viewModel : HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.homeDescriptionId.text = "Example to fetch list of Users from Retrofit API using coroutine"
        binding.progressBarId.visibility = View.GONE
        viewModel.getUIState().observe(this, Observer { uiState: HomeActivityUIState ->
            if( null != uiState) {
                updateUI(uiState)
            }
        } )
        binding.showListBtn.setOnClickListener {
            viewModel.getListData()
            //getListDataFromAssets()
        }
    }

    private fun updateUI(uiState: HomeActivityUIState) {
        when(uiState) {
            is HomeActivityUIState.loading -> showProgressBar()
            is HomeActivityUIState.onSuccess -> showList(uiState.userModel)
            is HomeActivityUIState.onError -> showError(uiState.errorMsg)
        }
    }

    private fun showProgressBar() {
        binding.progressBarId.visibility = View.VISIBLE
    }

    private fun showList(userModel: UserModel) {
        binding.progressBarId.visibility = View.GONE
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.layoutManager = layoutManager
        binding.recyclerViewId.adapter = UserListAdapter(userModel.userList)
        binding.recyclerViewId.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))
    }

    private fun showError(errorMsg: String) {
        binding.progressBarId.visibility = View.GONE
        binding.homeDescriptionId.text = errorMsg
    }

    private fun getListDataFromAssets() {
        val usersList: List<User> = Repository.getUsersListFromAssets(this)
        showList(UserModel(usersList))
    }

}