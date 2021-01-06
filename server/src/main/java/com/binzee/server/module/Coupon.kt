package com.binzee.server.module

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 兑换券
 *
 * @author tong.xw
 * 2021/01/05 10:40
 */

@Entity
data class Coupon(
    @PrimaryKey(autoGenerate = true) var id: Long,   //主键
    var couponUid: String,  //兑换券UID
    var couponName: String, //兑换券名称
    var describe: String, //兑换券描述
    var deadLine: Long, //过期日期，毫秒
    var state: Int, //状态，0未使用，1使用中，2已使用
    var type: Int   //类别，0一次性使用，1需确认
)

