package com.example.fitee.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fitee.databinding.FragmentSignUpBinding
import com.example.fitee.dto.SignUpModel
import com.example.fitee.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    private lateinit var viewModel : SignUpViewModel
    private lateinit var signUpModel: SignUpModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignUpBinding.inflate(inflater, container, false)

        //회원가입 retrofitwork
        setupUI(binding.root)

        //viewmodel observe
        observeViewModel()

        // 버튼 selected 이벤트
        setupToggleButtons(
            binding.trainerBtn to binding.userBtn,
            binding.userBtn to binding.trainerBtn,
            binding.maleBtn to binding.femaleBtn,
            binding.femaleBtn to binding.maleBtn
        )

        return binding.root
    }

    //회원가입 retrofitwork
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

    //model에 값 넣는 함수
    private fun createSignUpModelFromInput(view: View): SignUpModel? {
        // EditText에서 값 가져오기
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val name = binding.editName.text.toString()
        val age = binding.editAge.text.toString().toIntOrNull() ?: return null
        val tel = binding.editTel.text.toString()
        val gender = if(binding.maleBtn.isSelected){
            "MALE"
        }else {"FEMALE"}
        val part = if(binding.trainerBtn.isSelected){
            "TRAINER"
        }else {"MEMBER"}

        return SignUpModel(email, password, name, age, gender, tel, part)
    }

    //viewmodel observe 함수
    private fun observeViewModel() {
        //Viewmodel선언
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        viewModel.signUpResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(requireContext(), "회원가입 성공", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
            }.onFailure { e ->
                Toast.makeText(requireContext(), "회원가입 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    //메인엑티비티로 이동 함수
    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        activity?.finish() // 이전 액티비티 종료
    }

    //버튼 selected 함수
    private fun setupToggleButtons(vararg buttons: Pair<View, View>) {
        buttons.forEach { (button, otherButton) ->
            button.setOnClickListener {
                button.isSelected = !button.isSelected
                otherButton.isSelected = false
            }
        }
    }

}