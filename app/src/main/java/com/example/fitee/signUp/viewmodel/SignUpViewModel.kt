package com.example.fitee.signUp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitee.signUp.model.SignUpModel
import com.example.fitee.signUp.repository.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpResult = MutableLiveData<Result<Unit>>()
    val signUpResult: LiveData<Result<Unit>> = _signUpResult
    private val repository = SignUpRepository()

    fun postSignUp(signUpModel: SignUpModel) {
        if (!validateSignUpModel(signUpModel)) {
            _signUpResult.value = Result.failure(Exception("입력값이 올바르지 않습니다."))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.postSignIn(signUpModel)
                _signUpResult.postValue(Result.success(Unit))
            } catch (e: Exception) {
                _signUpResult.postValue(Result.failure(e))
            }
        }
    }

    private fun validateSignUpModel(model: SignUpModel): Boolean {
        // 유효성 검사 로직 예시
        return model.email.isNotBlank() &&
                model.password.isNotBlank() &&
                model.name.isNotBlank() &&
                model.age > 0 &&
                model.tel.isNotBlank()
    }

}