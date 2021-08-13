package com.app.compare_my_trade.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentLoginBinding
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.home.HomeActivity
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding,MotorViewModel>() {

    private  val movieViewModel: MotorViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding?.resetTv?.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewDataBinding?.createTv?.setOnClickListener {
            findNavController().navigate(R.id.action_loginfragment_to_createAccountFragment)
        }
        viewDataBinding?.loginBtn?.setOnClickListener {
            val intent = Intent (activity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override val bindingVariable: Int
        get() = BR.motorVM
    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: MotorViewModel
        get() = movieViewModel
}