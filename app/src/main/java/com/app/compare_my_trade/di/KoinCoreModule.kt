package com.app.compare_my_trade.di

import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.postauthenticationui.ui.home.HomeViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
class KoinCoreModule {
    val koinCoreModule = module {
        viewModel<MotorViewModel>()
        viewModel<HomeViewModel>()
}
}