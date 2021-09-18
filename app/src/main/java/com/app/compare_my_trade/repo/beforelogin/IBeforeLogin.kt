package com.app.compare_my_trade.repo.beforelogin

import com.app.compare_my_trade.data.model.beforelogin.CreateAccountResponse
import com.app.compare_my_trade.data.model.beforelogin.LoginResponse
import com.app.compare_my_trade.network.model.BaseResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface IBeforeLogin {

    @Multipart
    @POST("api/buyers/login")
    fun getLoginResponse( @Part("email")email: RequestBody , @Part("password")password :RequestBody):Call<BaseResponse<LoginResponse>>

    @Multipart
    @POST("api/buyers/register")
    fun getCreateAccountResponse(
        @Part("first_name")firstName:RequestBody,
        @Part("last_name")lastName:RequestBody,
        @Part("email")email: RequestBody,
        @Part("business_phone")phone:RequestBody,
        @Part("password")password: RequestBody,
        @Part("address_line")addressLine:RequestBody,
        @Part("location_id")locationID:RequestBody,
        @Part("postal_code")postalCode:RequestBody
    ):Call<BaseResponse<CreateAccountResponse>>
}