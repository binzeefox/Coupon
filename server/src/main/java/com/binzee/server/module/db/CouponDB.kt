package com.binzee.server.module.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binzee.server.module.Coupon

/**
 * 兑换券数据库
 *
 * @author tong.xw
 * 2021/01/05 10:39
 */
@Database(entities = [Coupon::class], version = 0)
abstract class CouponDB: RoomDatabase() {
    abstract fun couponDao(): CouponDao
}