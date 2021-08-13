package com.app.compare_my_trade.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentNewPasswordBinding
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login_control.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding, MotorViewModel>() {

    private  val movieViewModel: MotorViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginControlActivity).let {
            it.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        }
        viewDataBinding?.let {
            it.loginBtn.setOnClickListener {
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    override val bindingVariable: Int
        get() = BR.motorVM
    override val layoutId: Int
        get() = R.layout.fragment_new_password
    override val viewModel: MotorViewModel
        get() = movieViewModel
}