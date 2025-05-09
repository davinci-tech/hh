package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.MainActivityHandlerMsg;
import com.huawei.openalliance.ad.constant.Action;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class AccountChangeReceiver extends BroadcastReceiver {
    private static AccountChangeReceiver d;

    /* renamed from: a, reason: collision with root package name */
    private Handler f2949a;

    public AccountChangeReceiver(Handler handler) {
        this.f2949a = handler;
    }

    public static void auf_(Handler handler) {
        d = new AccountChangeReceiver(handler);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
        intentFilter.addAction("com.huawei.plugin.account.login");
        BroadcastManagerUtil.bFB_(BaseApplication.e(), d, intentFilter);
    }

    public static void c() {
        if (d != null) {
            try {
                BaseApplication.e().unregisterReceiver(d);
            } catch (IllegalArgumentException e) {
                ReleaseLogUtil.c("R_AccountChangeReceiver", "unregisterAccountLogoutBroadcast，IllegalArgumentException e = ", e.getMessage());
            } catch (RuntimeException e2) {
                ReleaseLogUtil.c("R_AccountChangeReceiver", "unregisterAccountLogoutBroadcast，RuntimeException e = ", e2.getMessage());
            }
            d = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            LogUtil.c("AccountChangeReceiver", "Broadcast triggered, but intent is null...");
            return;
        }
        Message obtain = Message.obtain(this.f2949a);
        obtain.what = MainActivityHandlerMsg.ON_ACCOUNT_CHANGE_BROADCAST;
        obtain.obj = intent;
        obtain.sendToTarget();
    }
}
