package com.huawei.featureuserprofile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import defpackage.bqi;
import defpackage.bwc;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class UserInfoBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.huawei.plugin.account.login".equals(action)) {
            LogUtil.d("UserInfoBroadcastReceiver", "receive account login broadcast");
            LoginInit.getInstance(context);
            if (LoginInit.shouldLogin()) {
                LogUtil.d("UserInfoBroadcastReceiver", "allow login area");
                bqi.c(context).i();
                bqi.c(context).a();
                return;
            } else {
                LogUtil.d("UserInfoBroadcastReceiver", "not allow login area");
                bqi.c(context).a(context);
                return;
            }
        }
        if ("com.huawei.plugin.account.logout".equals(action)) {
            LogUtil.d("UserInfoBroadcastReceiver", "receive account logout broadcast");
            bqi.c(context).j();
            bqi.c(context).h();
            bwc.b().b(BaseApplication.e());
            return;
        }
        LogUtil.d("UserInfoBroadcastReceiver", "not expect action");
    }
}
