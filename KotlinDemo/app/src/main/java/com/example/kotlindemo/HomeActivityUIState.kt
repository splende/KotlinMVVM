package com.example.kotlindemo

sealed class HomeActivityUIState {
    object loading: HomeActivityUIState()
    data class onSuccess(val userModel: UserModel) : HomeActivityUIState()
    data class onError(val errorMsg: String) : HomeActivityUIState()
}