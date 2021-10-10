package com.app.compare_my_trade.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.R
import com.app.compare_my_trade.data.model.beforelogin.state.StateListResponseItem
import com.app.compare_my_trade.databinding.FragmentCreateAccountBinding
import com.app.compare_my_trade.network.model.Status
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseFragment
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.postauthenticationui.HomeActivity
import com.app.compare_my_trade.utills.Singleton
import kotlinx.android.synthetic.main.activity_login_control.*
import kotlinx.android.synthetic.main.prograssbar.*
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
        observeFields()
        getStateList()
    }

    private fun getStateList() {
        viewModel.isLoading.value=true
        viewModel.getStateListResponse()
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
                       viewModel.isLoading.value=true
                       viewModel.postRegistrationResponse()
                    }

                }
            }
        }
    }
    private fun observeFields() {
        viewModel.stateListResponse.observe(viewLifecycleOwner,{
            viewModel.isLoading.value=false
            it?.let {
                viewModel.stateList.set(null)
                val listModel = it as ArrayList<StateListResponseItem>
                val defaultStateItem= StateListResponseItem("","","",-1,"State","","")
                listModel.add(0,defaultStateItem)
                viewModel.stateArrayValue= listModel

                val list=ArrayList<String>()
                for (i in listModel.indices) {
                    list.add(listModel[i].name)
                }
                viewModel.stateList.set(list)
                viewModel.stateListResponse.value=null
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
                viewModel.isLoading.value=null
            }


        })
        viewModel.createAccountApiResponse.observe(viewLifecycleOwner, {
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
                viewModel.createAccountApiResponse.value=null
            }


        })
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
        activity?.finish()
    }


}