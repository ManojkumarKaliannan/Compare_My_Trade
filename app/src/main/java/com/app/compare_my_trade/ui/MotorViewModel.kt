package com.app.compare_my_trade.ui

import android.app.Application
import android.view.View
import android.widget.AdapterView
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.base.BaseViewModel

import org.koin.core.KoinComponent
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.compare_my_trade.data.model.beforelogin.CreateAccountResponse
import com.app.compare_my_trade.data.model.beforelogin.LoginResponse
import com.app.compare_my_trade.network.model.BaseResponse
import com.app.compare_my_trade.network.model.Resource
import com.app.compare_my_trade.repo.beforelogin.BeforeLoginRepoList
import org.koin.core.inject


class MotorViewModel(application: Application) : BaseViewModel<BaseNavigator>(application),
    KoinComponent
{
    var username = ObservableField("mano@gmail.com")
    var password = ObservableField("Kshruthi7!")

    var firstName=ObservableField<String>()
    var lastName=ObservableField<String>()
    var email=ObservableField<String>()
    var phoneNumber=ObservableField<String>()
    var address=ObservableField<String>()
    var postCode=ObservableField<String>()
    var setPassword=ObservableField<String>()
    var state=ObservableField<String>()
    private val beforeLoginRepo: BeforeLoginRepoList by inject()
     val isLoading = MutableLiveData<Boolean>()
    var loginResponse = MutableLiveData<Resource<BaseResponse<LoginResponse>>>()
    var stateList = ObservableField<List<String>>()
    var createAccountApiResponse=MutableLiveData<Resource<BaseResponse<CreateAccountResponse>>>()

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }

    fun postLoginResponse() {
        beforeLoginRepo.getLoginResponse(username.get()!!,password.get()!!,loginResponse)
    }

     fun postRegistrationResponse() {
         beforeLoginRepo.getCreateAccountResponse(
             firstName = firstName.get()!!,
             lastName = lastName.get()!!,
             email = email.get()!!,
             password= setPassword.get()!!,
             addressLine = address.get()!!,
             postalCode = postCode.get()!!,
             locationId = "2",
             phoneNumber=phoneNumber.get()!!,
             response = createAccountApiResponse
         )
    }


    fun setStateValues(){
        var list=  ArrayList<String>()
        list.clear()
        list.add("State")
        list.add("TamilNadu")
        list.add("Karnataka")
        stateList.set(list)
    }

    fun onSelectSectionItem(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        stateList.get()?.let {
            state.set(it[pos])
        }

    }


}