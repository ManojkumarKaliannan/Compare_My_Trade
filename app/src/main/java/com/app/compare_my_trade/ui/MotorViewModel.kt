package com.app.compare_my_trade.ui

import android.app.Application
import android.view.View
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.base.BaseViewModel

import org.koin.core.KoinComponent

class MotorViewModel(application: Application) : BaseViewModel<BaseNavigator>(application),
    KoinComponent
{
    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }

}