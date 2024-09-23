package com.example.fitee.signUp.api

import com.example.fitee.signUp.model.SignUpModel
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("sign-in")
    suspend fun postSignIn(
        @Body signUpModel : SignUpModel
    ) : Unit
}