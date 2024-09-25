package com.example.fitee.api

import com.example.fitee.dto.SignInModel
import com.example.fitee.dto.SignInResponse
import com.example.fitee.dto.SignUpModel
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    //회원가입
    @POST("sign-in")
    suspend fun postSignIn(
        @Body signUpModel : SignUpModel
    )

    //로그인
    @POST("login")
    suspend fun postLogin(
        @Body signInModel : SignInModel
    ) : SignInResponse
}