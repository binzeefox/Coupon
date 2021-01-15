package com.binzee.server

import android.content.Intent
import android.os.Bundle
import com.binzee.common.BaseActivity
import com.binzee.foxdevframe.utils.net.socket.ServerHelper
import com.binzee.server.server.ServerService
import com.binzee.server.server.socket.CouponSocketServer

/**
 * 服务端 主页面
 *
 * @author tong.xw
 * 2021/01/04 16:40
 */
class MainActivity : BaseActivity() {

    override fun getContentViewResource(): Int {
        return R.layout.activity_main
    }

    override fun onCreate() {
        // 开启服务器服务
        Intent(this, ServerService::class.java).apply {
            startService(this)
        }
    }
}