package com.huawei.ui.device.interactors;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jjb;
import defpackage.jrg;

/* loaded from: classes6.dex */
public class NotificationPushInteractor {
    private jjb c = jjb.b();

    public NotificationPushInteractor(Context context) {
    }

    public int d(String str) {
        return this.c.a(str);
    }

    public void a(String str, int i) {
        this.c.b(str, i);
    }

    public boolean b() {
        return this.c.c();
    }

    public void d(int i) {
        this.c.d("notificationStatus", i, true);
    }

    public void e(int i) {
        this.c.d("notificationStatus", i, false);
    }

    public boolean e() {
        return jrg.b();
    }

    public boolean a(String str) {
        return jrg.e(str);
    }

    public boolean c() {
        return this.c.e();
    }

    public void a(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("NotificationPushUtil", "setRotateSwitchScreenSwitchStatus() Status " + z);
        this.c.b(z, iBaseResponseCallback);
    }
}
