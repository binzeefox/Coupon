package com.binzee.client.client

import android.util.Log
import com.binzee.foxdevframe.utils.LogUtil
import com.binzee.foxdevframe.utils.device.DeviceStatusUtil
import com.binzee.foxdevframe.utils.net.LanScanner
import com.binzee.foxdevframe.utils.net.socket.SocketHelper
import java.net.InetAddress
import java.net.Socket

/**
 * 客户端
 *
 * @author tong.xw
 * 2021/01/14 10:35
 */
object CouponClient {
    private const val TAG = "CouponClient"
    private const val HOST_PORT = 60001
    private const val PASSWORD_OUT = "Coupon-Client"
    private const val PASSWORD_IN = "Coupon-Host"

    var hostAddress: InetAddress? = null    //主机地址

    private val lanScanner = LanScanner(DeviceStatusUtil.get().localIPAddress)

    fun scanForHost(listener: OnScanResultListener?) {
        lanScanner.setOnResponseListener {
            var helper: SocketHelper? = null
            try {
                helper = SocketHelper.create(it.hostAddress, HOST_PORT)
                if (!helper.isConnected) {
                    helper.close()
                    return@setOnResponseListener
                }
                helper.listen(object : SocketHelper.ListenCallback {
                    override fun onListen(socket: Socket?, text: String?) {
                        if (PASSWORD_IN == text) {
                            hostAddress = socket?.localAddress
                            hostAddress?.let { it1 -> listener?.onResult(it1) }
                        }
                    }

                    override fun onDisconnect(socket: Socket?) {

                    }

                    override fun onError(e: Throwable?) {
                        LogUtil.w(TAG, "onError: ", e)
                    }

                })
                helper.send(PASSWORD_OUT) { _, t ->
                    LogUtil.w(TAG, "lanScanner: send Error", t)
                }
            } catch (e: Exception) {
                helper?.close()
                LogUtil.v(TAG, "lanScanner#onResponse: ", e)
            }
        }
        lanScanner.scan()
    }

    fun interface OnScanResultListener {
        fun onResult(hostAddress: InetAddress)
    }
}