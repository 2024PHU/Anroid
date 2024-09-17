package com.example.fitee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
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

        // 프래그먼트 교체
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

    }
}