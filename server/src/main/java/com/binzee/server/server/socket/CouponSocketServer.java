package com.binzee.server.server.socket;

import android.text.TextUtils;

import com.binzee.foxdevframe.utils.net.socket.ServerHelper;

import java.io.IOException;
import java.net.Socket;

/**
 * Socket服务器
 *
 * 开启服务器
 * 当接收到“Coupon-Client”时，返回字符串“Coupon-Host”
 * @author tong.xw
 * 2021/01/05 11:22
 */
public class CouponSocketServer implements ServerHelper.ServerCallback {
    private static final String TAG = "CouponSocket";
    private static final int PORT = 60001;
    private static final String PASSWORD_IN = "Coupon-Client";
    private static final String PASSWORD_OUT = "Coupon-Host";
    public final ServerHelper helper;

    public CouponSocketServer() throws IOException {
        helper = ServerHelper.create(PORT);
        helper.setCallback(this);
    }

    @Override
    public void onAccept(Socket client) {

    }

    @Override
    public void onReceive(Socket client, String text) {
        if (TextUtils.equals(PASSWORD_IN, text))
            helper.sendTo(client, PASSWORD_OUT);
    }

    @Override
    public void onLost(Socket client) {

    }

    @Override
    public void onError(int errorCode, Throwable t) {

    }
}
