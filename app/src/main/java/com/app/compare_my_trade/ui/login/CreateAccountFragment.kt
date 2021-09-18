package com.app.compare_my_trade.ui.login

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.databinding.FragmentCreateAccountBinding
import com.app.compare_my_trade.network.model.Status
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.postauthenticationui.HomeActivity
import com.app.compare_my_trade.utills.Singleton
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
        viewModel.setStateValues()
        viewModel.setNavigator(this)
        observeFields()
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
                when {
                    viewModel.firstName.get()==null -> {
                      putToast("Enter your FirstName")
                    }
                    viewModel.lastName.get()==null -> {
                        putToast("Enter your LastName")
                    }
                    viewModel.email.get()==null -> {
                        putToast("Enter your Email")
                    }
                    viewModel.phoneNumber.get()==null->{
                        putToast("Enter your PhoneNumber")
                    }
                    viewModel.address.get()==null->{
                        putToast("Enter your Address")
                    }
                    viewModel.postCode.get()==null->{
                        putToast("Enter your Postcode")
                    }
                    viewModel.setPassword.get()==null->{
                        putToast("Enter your Password")
                    }
                    !Singleton.isValidEmail(viewModel.email.get())->{
                        putToast("Enter valid Email")
                    }
                    viewModel.phoneNumber.get()!=null &&
                            !Singleton.isValidPhoneNumber(viewModel.phoneNumber.get())->{
                        putToast("Invalid Mobile Number")
                    }
                    viewModel.state.get()=="State"->{
                        putToast("Select your State")
                    }
                    else->{
                       viewModel.postRegistrationResponse()
                    }

                }
            }
        }
    }
    private fun observeFields() {
        viewModel.createAccountApiResponse.observe(viewLifecycleOwner, {
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

            }
        })
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }


}