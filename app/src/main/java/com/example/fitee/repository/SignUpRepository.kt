package com.example.fitee.repository

import com.example.fitee.api.SignUpApi
import com.example.fitee.api.RetrofitWork
import com.example.fitee.dto.SignInModel
import com.example.fitee.dto.SignInResponse
import com.example.fitee.dto.SignUpModel

class SignUpRepository : SignUpApi {
    private val retrofitInstance = RetrofitWork.getInstance().create(SignUpApi::class.java)

    override suspend fun postSignIn(signUpModel: SignUpModel){
        retrofitInstance.postSignIn(signUpModel)
    }

    override suspend fun postLogin(signInModel: SignInModel): SignInResponse {
        return retrofitInstance.postLogin(signInModel)
    }

}