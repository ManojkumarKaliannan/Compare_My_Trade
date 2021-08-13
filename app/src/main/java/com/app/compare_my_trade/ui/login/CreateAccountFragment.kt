package com.app.compare_my_trade.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentCreateAccountBinding
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login_control.*
import org.koin.android.ext.android.inject

class CreateAccountFragment: BaseFragment<FragmentCreateAccountBinding, MotorViewModel>() {
    private  val movieViewModel: MotorViewModel by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginControlActivity).let {
            it.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        }
        viewDataBinding?.let {
            it.createTv.setOnClickListener {
                findNavController().navigate(R.id.action_createAccountFragment_to_loginfragment)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

    }

    override val bindingVariable: Int
        get() = BR.motorVM
    override val layoutId: Int
        get() = R.layout.fragment_create_account
    override val viewModel: MotorViewModel
        get() = movieViewModel
}