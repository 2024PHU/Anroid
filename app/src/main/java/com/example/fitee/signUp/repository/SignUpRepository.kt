package com.example.fitee.signUp.repository

import com.example.fitee.signUp.api.SignUpApi
import com.example.fitee.signUp.api.SignUpRetrofitWork
import com.example.fitee.signUp.model.SignUpModel

class SignUpRepository : SignUpApi {
    private val retrofitInstance = SignUpRetrofitWork.getInstance().create(SignUpApi::class.java)

    override suspend fun postSignIn(signUpModel: SignUpModel){
        retrofitInstance.postSignIn(signUpModel)
    }

}