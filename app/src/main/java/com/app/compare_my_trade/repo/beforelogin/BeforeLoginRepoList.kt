package com.app.compare_my_trade.repo.beforelogin

import androidx.lifecycle.MutableLiveData
import com.app.compare_my_trade.data.model.beforelogin.CreateAccountResponse
import com.app.compare_my_trade.data.model.beforelogin.LoginResponse
import com.app.compare_my_trade.network.clientbuilder.BaseRepository
import com.app.compare_my_trade.network.model.BaseResponse
import com.app.compare_my_trade.network.model.Resource
import com.app.compare_my_trade.repo.AllMotorAPIRepo
import org.koin.experimental.builder.create
import java.io.File

class BeforeLoginRepoList(private val allMotorAPIRepo:AllMotorAPIRepo):BaseRepository(){
    fun getLoginResponse(email:String,password:String, response: MutableLiveData<Resource<BaseResponse<LoginResponse>>>){
        allMotorAPIRepo.getBeforeLoginControllerRepo().
        getLoginResponse(createPlainTextRequestBody(email),createPlainTextRequestBody(password)).enqueue(getLoginCallback(response))
    }

    fun getCreateAccountResponse(
        firstName: String,
        lastName:String,
        email:String,
        password:String,
        phoneNumber:String,
        addressLine:String,
        locationId:String,
        postalCode:String,
        response: MutableLiveData<Resource<BaseResponse<CreateAccountResponse>>>
    ){
          allMotorAPIRepo.getBeforeLoginControllerRepo().getCreateAccountResponse(
              firstName= createPlainTextRequestBody(firstName),
              lastName = createPlainTextRequestBody(lastName),
              email = createPlainTextRequestBody(email),
              phone=createPlainTextRequestBody(phoneNumber),
              password=createPlainTextRequestBody(password),
              addressLine = createPlainTextRequestBody(addressLine),
              locationID = createPlainTextRequestBody(locationId),
              postalCode = createPlainTextRequestBody(postalCode)
          ).enqueue(getLoginCallback(response))
    }

}