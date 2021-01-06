package com.binzee.server.server.http.api;

import androidx.annotation.NonNull;

import com.binzee.server.MyApplication;
import com.binzee.server.module.Coupon;
import com.binzee.server.module.ResultBean;
import com.binzee.server.module.db.CouponDao;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.QueryParam;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换券服务器接口v1.0
 *
 * @author tong.xw
 * 2021/01/04 18:32
 */
@RestController
@RequestMapping(path = "/api_1_0")
public class APIv1_0 {
    private final CouponDao mDao = MyApplication.Companion.getDatabase().couponDao();

    /**
     * 获取全部兑换券
     *
     * @return json
     */
    @NonNull
    @GetMapping("/requestCouponLists")
    public String requestCouponList() {
        ResultBean<List<Coupon>> bean = new ResultBean<>();
        List<Coupon> list = mDao.queryAll();
        bean.setData(list);
        return bean.toJson();
    }

    /**
     * 根据兑换券ID获取兑换券信息
     */
    @GetMapping("/queryCoupon")
    public String queryCoupon(@QueryParam("couponId") String id) {
        ResultBean<Coupon> bean = new ResultBean<>();
        Coupon coupon = mDao.query(id);
        bean.setData(coupon);
        return bean.toJson();
    }

    /**
     * 根据兑换券ID使用兑换券
     */
    @PostMapping("/useCoupon")
    public String useCoupon(@RequestParam("couponId") String id) {
        ResultBean<Boolean> bean = new ResultBean<>();
        Coupon coupon = mDao.query(id);
        if (coupon == null) {
            bean.setData(false);
        } else {
            mDao.updateState(new CouponDao.UpdateStateCondition(coupon.getId(), 1));
            bean.setData(true);
        }

        return bean.toJson();
    }

    /**
     * 兑现兑换券
     */
    @PostMapping("/achieveCoupon")
    public String achieveCoupon(@RequestParam("couponId") String couponId) {
        ResultBean<Boolean> bean = new ResultBean<>();
        Coupon coupon = mDao.query(couponId);
        if (coupon == null) {
            bean.setData(false);
        } else {
            mDao.updateState(new CouponDao.UpdateStateCondition(coupon.getId(), 2));
            bean.setData(true);
        }

        return bean.toJson();
    }
}
