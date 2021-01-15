package com.binzee.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binzee.client.client.CouponClient
import com.binzee.common.BaseActivity
import com.binzee.foxdevframe.utils.net.http.ClientUtil

class MainActivity : BaseActivity() {

    override fun getContentViewResource(): Int {
        return R.layout.activity_main
    }

    override fun onCreate() {
        CouponClient.scanForHost {

        }
    }
}