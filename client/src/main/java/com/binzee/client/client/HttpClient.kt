package com.binzee.client.client

import com.binzee.foxdevframe.utils.net.http.ClientInterface
import com.binzee.foxdevframe.utils.net.http.ClientUtil
import com.google.gson.Gson
import java.net.InetAddress

/**
 *
 *
 * @author tong.xw
 * 2021/01/14 11:56
 */
class HttpClient(private val host: InetAddress) {
    private val TAG = "HttpClient"
    private val util = ClientUtil.get()
    private val gson = Gson()

    fun requestCouponList(listener: ClientInterface.OnCallListener) {
        val url = createUrl(CouponMethod.COUPON_LIST_GET)
        util.GET(url).request(listener)
    }

    fun queryCoupon(couponId: String, listener: ClientInterface.OnCallListener) {
        val url = createUrl(CouponMethod.COUPON_QUERY_GET)
        val params = HashMap<String, String>().apply {
            put("couponId", couponId)
        }
        util.GET(url, params).request(listener)
    }

    fun useCoupon(couponId: String, listener: ClientInterface.OnCallListener) {
        val url = createUrl(CouponMethod.COUPON_USE_POST)
        val params = HashMap<String, String>().apply {
            put("couponId", couponId)
        }

        util.POST(url, gson.toJson(params), "utf-8").request(listener)
    }

    fun achieveCoupon(couponId: String, listener: ClientInterface.OnCallListener) {
        val url = createUrl(CouponMethod.COUPON_ACHIEVE_POST)
        val params = HashMap<String, String>().apply {
            put("couponId", couponId)
        }

        util.POST(url, gson.toJson(params), "utf-8").request(listener)
    }

    ///////////////////////////////////////////////////////////////////////////
    // 内部方法
    ///////////////////////////////////////////////////////////////////////////

    private fun createUrl(method: CouponMethod): String =
        CouponApiV1_0.createUrl(host.hostAddress, method)
}