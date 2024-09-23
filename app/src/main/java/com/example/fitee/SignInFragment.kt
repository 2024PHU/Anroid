package com.example.fitee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fitee.databinding.FragmentSignInBinding
import com.example.fitee.signUp.viewmodel.SignUpViewModel

class SignInFragment : Fragment() {
    lateinit var binding :FragmentSignInBinding
    lateinit var viewModel : SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignInBinding.inflate(inflater,container,false)


        return binding.root
    }

}