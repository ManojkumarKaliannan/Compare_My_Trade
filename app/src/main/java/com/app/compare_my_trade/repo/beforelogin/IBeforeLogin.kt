package com.app.compare_my_trade.repo.beforelogin

import com.app.compare_my_trade.data.model.LoginResponse
import com.app.compare_my_trade.network.model.BaseResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IBeforeLogin {

    @Multipart
    @POST("/login")
    fun getLoginResponse( @Part("email")email: RequestBody , @Part("password")password :RequestBody):Call<BaseResponse<LoginResponse>>
}