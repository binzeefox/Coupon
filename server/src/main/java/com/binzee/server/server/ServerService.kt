package com.binzee.server.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.binzee.server.server.http.CouponServer
import com.binzee.server.server.socket.CouponSocketServer

/**
 * 服务器服务
 *
 * @author tong.xw
 * 2021/01/14 10:24
 */
class ServerService: Service() {
    private val socketServer = CouponSocketServer()
    private val httpServer = CouponServer.get()

    override fun onCreate() {
        super.onCreate()
        socketServer.helper.open(false)
        httpServer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        socketServer.helper.close()
        httpServer.shutdown()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}