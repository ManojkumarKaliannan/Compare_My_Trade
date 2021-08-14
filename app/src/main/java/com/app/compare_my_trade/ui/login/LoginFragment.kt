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
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.home.HomeActivity
import com.app.compare_my_trade.utills.Singleton.isValidEmail
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding,MotorViewModel>(), BaseNavigator {

    private  val motorViewModel: MotorViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
    }

    override val bindingVariable: Int
        get() = BR.motorVM
    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: MotorViewModel
        get() = motorViewModel

    override fun onClickView(v: View?) {
        when(v?.id){
            R.id.reset_tv->{
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            R.id.create_tv->{
                findNavController().navigate(R.id.action_loginfragment_to_createAccountFragment)
            }
            R.id.login_btn->{
                 if(!isValidEmail(motorViewModel.username.get())){
                     putToast("Enter Valid Email id")
                 }else if(motorViewModel.password.get()==null){
                     putToast("Enter your password")
                 }else{
                    goTo(HomeActivity::class.java,null)
                 }

            }

        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)

    }
}