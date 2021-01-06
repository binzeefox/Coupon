package com.binzee.client.db

import androidx.room.*

/**
 * 兑换券数据操作类
 *
 * @author tong.xw
 * 2021/01/05 10:52
 */
@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Coupon::class)
    fun insertAll(list: List<CouponCondition>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Coupon::class)
    fun insert(condition: CouponCondition): Long

    @Delete(entity = Coupon::class)
    fun delete(vararg coupons: Coupon)

    @Update(entity = Coupon::class)
    fun updateState(vararg conditions: UpdateStateCondition)

    @Update(entity = Coupon::class)
    fun update(vararg coupons: Coupon)

    @Query("SELECT * From Coupon where couponUid = :couponUid")
    fun query(couponUid: String): Coupon?

    @Query("SELECT * From Coupon")
    fun queryAll(): List<Coupon>

    /**
     * 查询结束日期在时间区间内的数据
     */
    @Query("SELECT * From Coupon where deadLine >= :startTime and deadLine <= :endTime")
    fun queryByDeadlineRange(startTime: Long, endTime: Long): List<Coupon>

    ///////////////////////////////////////////////////////////////////////////
    // 查询条件
    ///////////////////////////////////////////////////////////////////////////

    data class UpdateStateCondition(
        var id: Long,
        var state: Int
    )

    data class CouponCondition(
        var couponUid: String,  //兑换券UID
        var couponName: String, //兑换券名称
        var describe: String, //兑换券描述
        var deadLine: Long, //过期日期，毫秒
        var state: Int, //状态，0未使用，1使用中，2已使用
        var type: Int   //类别，0一次性使用，1需确认
    ) {
        constructor(
            couponUid: String,  //兑换券UID
            couponName: String, //兑换券名称
            describe: String, //兑换券描述
            deadLine: Long
        ) : this(couponUid, couponName, describe, deadLine, 0, 0)

        constructor(
            couponUid: String,  //兑换券UID
            couponName: String, //兑换券名称
            describe: String, //兑换券描述
            deadLine: Long, //过期日期，毫秒
            type: Int   //类别，0一次性使用，1需确认
        ) : this(couponUid, couponName, describe, deadLine, 0, type)
    }
}

