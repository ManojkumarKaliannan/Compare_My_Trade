package com.app.compare_my_trade.network.clientbuilder

import androidx.lifecycle.MutableLiveData
import com.app.compare_my_trade.network.model.BaseResponse
import com.app.compare_my_trade.network.model.Resource
import com.app.compare_my_trade.utills.Singleton
import com.app.compare_my_trade.utills.Singleton.StatusCode
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException



open class BaseRepository{
    private val noNetworkMsg = "No internet connection or network failure"
    fun <T> getCallback(responseData: MutableLiveData<Resource<BaseResponse<T>>>): Callback<BaseResponse<T>> {
        return object : Callback<BaseResponse<T>> {
            override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
                Singleton.StatusCode=""
                if (t is IOException)
                    responseData.value = Resource.failure("Server time out", null)
                else
                    responseData.value = Resource.failure(t.message!!, null)
            }
            override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
                Singleton.StatusCode=""
                if (response.isSuccessful && response.body() != null) {
                    responseData.value = Resource.success(response.body()!!)
                } else {
                    StatusCode=response.code().toString()
                    val errorResponse = getErrorResponse<T>(response.errorBody()!!)
                    responseData.value = Resource.error(response.code().toString(),errorResponse)
                }
            }
        }
    }

    fun <T> getDefaultCallback(responseData: MutableLiveData<Resource<T>>): Callback<T> {
        return object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                val msg = if (t is IOException) noNetworkMsg else t.message!!
                responseData.value = Resource.failure(msg, null)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful && response.body() != null) {
                    responseData.value = Resource.success(response.body()!!)
                } else if (response.errorBody() != null) {
                    val errorResponse = getErrorResponse<T>(response.errorBody()!!)
                    responseData.value = Resource.error(errorResponse.message, errorResponse.data)
                }

            }

        }
    }

    fun <T> getErrorResponse(responseBody: ResponseBody): BaseResponse<T> {
        var truckErrorResponse: BaseResponse<T>
        try {
            val jObjError = JSONObject(responseBody.string())
            truckErrorResponse = BaseResponse(
                success = jObjError.getBoolean("success"),
                // message = Errors(jObjError.getJSONObject("errors").getString("message")),
                message = jObjError.getString("message"),
                data = null
            )
        } catch (e: JSONException) {
            truckErrorResponse = BaseResponse(
                success = false,
                message = "unable to get the response",
                data = null)
        }
        return truckErrorResponse
    }


}