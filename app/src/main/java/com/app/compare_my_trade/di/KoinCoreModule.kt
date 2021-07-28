package com.app.compare_my_trade.di

import com.app.compare_my_trade.ui.MotorViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
class KoinCoreModule {
    val koinCoreModule = module {
        viewModel<MotorViewModel>()
}
}