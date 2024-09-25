package com.example.fitee.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fitee.R
import com.example.fitee.databinding.ActivitySignInUpBinding

class SignInUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignInUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // fragment_type 값 저장
        val fragmentType = intent.getStringExtra("fragment_type")

        //type별 프래그먼트 띄우기
        val fragment: Fragment = when (fragmentType) {
            "signIn" -> SignInFragment()
            "signUp" -> SignUpFragment()
            else -> SignInFragment()  // 기본값
        }

        // 엑티비티 이름 설정
        when (fragmentType) {
            "signIn" -> binding.activityName.text = "로그인"
            "signUp" -> binding.activityName.text = "회원가입"
            else -> binding.activityName.text = "로그인"
        }

        // 프래그먼트 교체
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

    }
}