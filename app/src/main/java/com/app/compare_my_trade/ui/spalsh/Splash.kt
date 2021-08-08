package com.app.compare_my_trade.ui.spalsh

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.app.compare_my_trade.R
import com.app.compare_my_trade.BR
import com.app.compare_my_trade.databinding.ActivitySplashBinding
import com.app.compare_my_trade.ui.MotorViewModel
import com.app.compare_my_trade.ui.base.BaseActivity
import com.app.compare_my_trade.ui.base.BaseNavigator
import com.app.compare_my_trade.ui.home.HomeActivity
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.github.ybq.android.spinkit.style.DoubleBounce
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject


class Splash : BaseActivity<ActivitySplashBinding, MotorViewModel>(), BaseNavigator {
    private  val movieViewModel: MotorViewModel by inject()
    override fun getBindingVariable(): Int {
        return BR.motorVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): MotorViewModel {
        return movieViewModel
    }

    override fun onClickView(v: View?) {
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val doubleBounce: Sprite = Circle()
        spin_kit.setIndeterminateDrawable(doubleBounce)
        Handler(Looper.getMainLooper()).postDelayed({
            goTo(HomeActivity::class.java,null)
        }, 2000)
    }


}