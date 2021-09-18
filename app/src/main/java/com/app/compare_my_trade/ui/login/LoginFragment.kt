package com.app.compare_my_trade.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentLoginBinding
import com.app.compare_my_trade.network.model.Resource
import com.app.compare_my_trade.network.model.Status
import com.app.compare_my_trade.repo.beforelogin.BeforeLoginRepoList
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.postauthenticationui.HomeActivity
import com.app.compare_my_trade.utills.Constants
import com.app.compare_my_trade.utills.Singleton.isValidEmail
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.prograssbar.*
import org.koin.android.ext.android.inject
import org.koin.core.inject

class LoginFragment : BaseFragment<FragmentLoginBinding,MotorViewModel>(), BaseNavigator {

    private  val motorViewModel: MotorViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
        observeFields()
    }

    private fun observeFields() {
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            viewModel.isLoading.value=false
            it?.let {
                when(it.status){
                    Status.SUCCESS->{
                        goTo(HomeActivity::class.java,null)
                    }
                    Status.ERROR->{
                        putToast(it.message)
                    }
                    Status.FAILURE->{
                        putToast(it.message)
                    }
                    else -> {

                    }
                }
                viewModel.loginResponse.value=null
            }


        })
        viewModel.isLoading.observe(viewLifecycleOwner,{
            it?.let {
                when(it){
                    true->{
                        frame.isClickable=true
                        spin_kit.visibility=View.VISIBLE
                    }
                    false->{
                        frame.isClickable=false
                        spin_kit.visibility=View.GONE
                    }
                }
            }

        })
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
                     viewModel.isLoading.value=true
                     viewModel.postLoginResponse()
                 }

            }

        }
        binding.createTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginfragment_to_createAccountFragment)
        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)

    }
}