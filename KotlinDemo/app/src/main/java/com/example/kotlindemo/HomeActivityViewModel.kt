package com.example.kotlindemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeActivityViewModel: ViewModel() {

    private val homeActivityUIState: MutableLiveData<HomeActivityUIState> = MutableLiveData<HomeActivityUIState>()

    fun getUIState(): LiveData<HomeActivityUIState> {
        return homeActivityUIState
    }

    fun getListData() {
        homeActivityUIState.value = HomeActivityUIState.loading
        viewModelScope.launch {
            try {
                val userList: List<User> = Repository.getUsersListFromServer()
                homeActivityUIState.value = HomeActivityUIState.onSuccess(UserModel(userList))
            }catch (exception: Exception) {
                homeActivityUIState.value = HomeActivityUIState.onError("Error in Fetching List: " + exception.toString())
            }
        }
    }
}