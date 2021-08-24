package com.app.compare_my_trade.ui

import android.app.Application
import android.view.View
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.base.BaseViewModel

import org.koin.core.KoinComponent
import androidx.databinding.ObservableField




class MotorViewModel(application: Application) : BaseViewModel<BaseNavigator>(application),
    KoinComponent
{
    var username = ObservableField<String>()
    var password = ObservableField<String>()

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }



}