package com.app.compare_my_trade.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentCreateAccountBinding
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.postauthenticationui.HomeActivity
import kotlinx.android.synthetic.main.activity_login_control.*
import org.koin.android.ext.android.inject

class CreateAccountFragment: BaseFragment<FragmentCreateAccountBinding, MotorViewModel>(),
    BaseNavigator {
    private  val movieViewModel: MotorViewModel by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginControlActivity).let {
            it.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        }
        viewModel.setNavigator(this)
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


    override fun onClickView(v: View?) {
        when(v?.id){
            R.id.create_tv->{
                findNavController().navigate(R.id.action_createAccountFragment_to_loginfragment)
            }
            R.id.continue_btn->{
               goTo(HomeActivity::class.java,null)
            }
        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }


}