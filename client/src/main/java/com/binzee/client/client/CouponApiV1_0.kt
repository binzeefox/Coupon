package com.binzee.client.client

/**
 * 兑换券接口v1.0
 *
 * @author tong.xw
 * 2021/01/14 12:02
 */
object CouponApiV1_0 {
    private const val HTTP_PRE = "http://"
    private const val VERSION_CODES = "/api_1_0"

    fun createUrl(host: String, method: CouponMethod): String =
        HTTP_PRE + host + method.method
}

enum class CouponMethod(val method: String) {
    COUPON_LIST_GET("/requestCouponLists"),
    COUPON_QUERY_GET("/queryCoupon"),
    COUPON_USE_POST("/useCoupon"),
    COUPON_ACHIEVE_POST("/achieveCoupon")
}