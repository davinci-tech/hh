package com.huawei.health.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.utils.LanguageUtils;
import defpackage.ixj;
import defpackage.nbr;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class LanguageUtils {

    public static class SystemLocaleChangeReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                HandlerCenter.d().e(new Runnable() { // from class: goh
                    @Override // java.lang.Runnable
                    public final void run() {
                        LanguageUtils.SystemLocaleChangeReceiver.b();
                    }
                }, 300L);
                ReleaseLogUtil.b("Login_LanguageUtils", "finish allActivity for cause: LanguageChanged reStart");
                BaseApplication.b();
            }
        }

        public static /* synthetic */ void b() {
            ixj.b().e(BaseApplication.e());
            nbr.c(BaseApplication.e());
        }
    }
}
