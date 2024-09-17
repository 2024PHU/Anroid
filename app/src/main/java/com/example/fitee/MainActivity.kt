package com.example.fitee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.fitee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,SignInUpActivity::class.java)

        //glide 이미지 적용
        Glide.with(this).load(R.drawable.mic_animaition).into(binding.gifImage)

        //로그인 화면으로 이동
        binding.signInBtn.setOnClickListener {
            intent.putExtra("fragment_type","signIn")
            startActivity(intent)
        }

        //회원가입 화면으로 이동
        binding.signUpBtn.setOnClickListener {
            intent.putExtra("fragment_type","signUp")
            startActivity(intent)
        }
    }
}