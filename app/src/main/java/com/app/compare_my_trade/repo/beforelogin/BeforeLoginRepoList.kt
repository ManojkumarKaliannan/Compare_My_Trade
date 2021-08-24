package com.app.compare_my_trade.repo.beforelogin

import androidx.lifecycle.MutableLiveData
import com.app.compare_my_trade.data.model.LoginResponse
import com.app.compare_my_trade.network.clientbuilder.BaseRepository
import com.app.compare_my_trade.network.clientbuilder.MotorClientBuilder
import com.app.compare_my_trade.network.model.BaseResponse
import com.app.compare_my_trade.network.model.Resource
import com.app.compare_my_trade.repo.AllMotorAPIRepo
import okhttp3.RequestBody
import retrofit2.Call

class BeforeLoginRepoList(private val allMotorAPIRepo:AllMotorAPIRepo):BaseRepository(){
    fun getLoginResponse(email:String,password:String, response: MutableLiveData<Resource<BaseResponse<LoginResponse>>>){
        allMotorAPIRepo.getBeforeLoginControllerRepo().
        getLoginResponse(createPlainTextRequestBody(email),createPlainTextRequestBody(password)).enqueue(getCallback(response))
    }

}