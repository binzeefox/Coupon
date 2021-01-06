package com.binzee.server.server.http;

import com.binzee.foxdevframe.FoxCore;
import com.binzee.foxdevframe.ui.tools.popup.ToastUtil;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;

import java.util.concurrent.TimeUnit;

/**
 * 兑换券服务器
 *
 * @author tong.xw
 * 2021/01/04 16:41
 */
public class CouponServer implements Server.ServerListener {
    private static final String TAG = "CouponServer";
    private final Server server;

    private CouponServer() {
        server = AndServer.webServer(FoxCore.getApplication())
                .port(8080)
                .timeout(10, TimeUnit.SECONDS)
                .listener(this)
                .build();
    }

    public static CouponServer get() {
        return Holder.sInstance;
    }

    public void start() {
        server.startup();
    }

    public void shutdown() {
        server.shutdown();
    }

    public boolean isRunning() {
        return server.isRunning();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onException(Exception e) {
        ToastUtil.toast("服务器错误: " + e.getClass().getSimpleName());
    }


    ///////////////////////////////////////////////////////////////////////////
    // 内部类
    ///////////////////////////////////////////////////////////////////////////

    private static class Holder {
        private static final CouponServer sInstance = new CouponServer();
    }
}
