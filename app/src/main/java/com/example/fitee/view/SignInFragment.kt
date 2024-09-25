package com.example.fitee.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fitee.databinding.FragmentSignInBinding
import com.example.fitee.dto.SignInModel
import com.example.fitee.dto.SignUpModel
import com.example.fitee.viewmodel.SignInViewModel
import com.example.fitee.viewmodel.SignInViewModelFactory
import com.example.fitee.viewmodel.SignUpViewModel

class SignInFragment : Fragment() {
    lateinit var binding :FragmentSignInBinding
    lateinit var viewModel : SignInViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignInBinding.inflate(inflater,container,false)

        setupUI(binding.root)

        observeViewModel()

        return binding.root
    }

    //model에 값 넣는 함수
    private fun createSignInModelFromInput(view: View): SignInModel? {
        // EditText에서 값 가져오기
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        return SignInModel(email, password)
    }

    //viewmodel observe 함수
    private fun observeViewModel() {
        //Viewmodel선언
        viewModel = ViewModelProvider(this, SignInViewModelFactory(requireContext()))[SignInViewModel::class.java]
        viewModel.signInResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(requireContext(), "로그인 완료", Toast.LENGTH_SHORT).show()
            }.onFailure { e ->
                Toast.makeText(requireContext(), "로그인 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    //로그인 함수
    private fun setupUI(view: View) {
        //Viewmodel선언
        viewModel = ViewModelProvider(this, SignInViewModelFactory(requireContext()))[SignInViewModel::class.java]
        // View 초기화 및 버튼 클릭 리스너 설정
        binding.loginBtn.setOnClickListener {
            val signInModel = createSignInModelFromInput(view)
            if (signInModel != null) {
                viewModel.postSignIn(signInModel)
            } else {
                Toast.makeText(requireContext(), "입력을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}