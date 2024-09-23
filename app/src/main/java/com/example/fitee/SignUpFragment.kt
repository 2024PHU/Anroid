package com.example.fitee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fitee.databinding.FragmentSignUpBinding
import com.example.fitee.signUp.model.SignUpModel
import com.example.fitee.signUp.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    private lateinit var viewModel : SignUpViewModel
    private lateinit var signUpModel: SignUpModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignUpBinding.inflate(inflater, container, false)

        setupUI(binding.root)
        observeViewModel()

        return binding.root
    }

    private fun setupUI(view: View) {
        //Viewmodel선언
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        // View 초기화 및 버튼 클릭 리스너 설정
        binding.signUpBtn.setOnClickListener {
            val signUpModel = createSignUpModelFromInput(view)
            if (signUpModel != null) {
                viewModel.postSignUp(signUpModel)
            } else {
                Toast.makeText(requireContext(), "입력을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createSignUpModelFromInput(view: View): SignUpModel? {
        // EditText에서 값 가져오기
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val name = binding.editName.text.toString()
        val age = binding.editAge.text.toString().toIntOrNull() ?: return null
        val tel = binding.editTel.text.toString()
        val gender = "MALE"
        val part = "TRAINER"

        return SignUpModel(email, password, name, age, gender, tel, part)
    }

    private fun observeViewModel() {
        //Viewmodel선언
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        viewModel.signUpResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
            }.onFailure { e ->
                Toast.makeText(requireContext(), "회원가입 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.d("SignFragment,",e.toString())
            }
        }


    }

    //메인엑티비티로 이동 함수
    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        activity?.finish() // 이전 액티비티 종료
    }




}