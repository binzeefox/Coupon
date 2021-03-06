package com.binzee.server

import android.app.Application
import androidx.room.Room
import com.binzee.foxdevframe.FoxCore
import com.binzee.server.module.db.CouponDB

/**
 *
 *
 * @author tong.xw
 * 2021/01/05 11:26
 */
class MyApplication : Application() {
    companion object {
        private val DATABASE_LOCK = Object()
        private var database: CouponDB? = null

        /**
         * 获取Database
         */
        fun getDatabase(): CouponDB {
            if (database == null) {
                synchronized(DATABASE_LOCK) {
                    if (database == null) {
                        database = Room.databaseBuilder(
                            FoxCore.getApplication()
                            , CouponDB::class.java
                            , "coupons"
                        ).build()
                    }
                    return database!!
                }
            }
            return database!!
        }
    }
}