package com.example.fitee.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitee.dto.SignInModel
import com.example.fitee.dto.SignInResponse
import com.example.fitee.dto.SignUpModel
import com.example.fitee.repository.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(context : Context) : ViewModel() {
    private val _signInResult = MutableLiveData<Result<SignInResponse>>()
    val signInResult: LiveData<Result<SignInResponse>> = _signInResult

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    private val repository = SignUpRepository()

    //sign_In retrofit
    fun postSignIn(signInModel: SignInModel) {
        if (!validateSignInModel(signInModel)) {
            _signInResult.value = Result.failure(Exception("입력값을 확인해주세요"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postLogin(signInModel)
                val token = response.token
                Log.d("`SignInViewModel`",token)
                saveAccessToken(token)
                val signInResponse = SignInResponse(token)
                _signInResult.postValue(Result.success(signInResponse))
            } catch (e: Exception) {
                _signInResult.postValue(Result.failure(e))
            }
        }
    }

    // 로그인 유효성 검사 로직
    private fun validateSignInModel(model: SignInModel): Boolean {
        return model.email.isNotBlank() &&
                model.password.isNotBlank()
    }

    // 어세스 토큰 저장
   private fun saveAccessToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("access_token", token)
        editor.apply()
    }
}