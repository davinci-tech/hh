package com.huawei.haf.application;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;

/* loaded from: classes.dex */
class ActivityStatusMonitor extends BroadcastReceiver {
    protected long d = System.currentTimeMillis();
    private Boolean e;

    int a(String str) {
        return -1;
    }

    boolean a(long j) {
        return false;
    }

    void c() {
    }

    int d() {
        return 0;
    }

    Activity[] vX_() {
        return null;
    }

    Activity vY_() {
        return null;
    }

    ActivityStatusMonitor(boolean z) {
        if (z) {
            BroadcastManager.wj_(this);
        } else {
            this.e = Boolean.FALSE;
        }
    }

    final boolean j() {
        if (this.e == null) {
            this.e = Boolean.valueOf(ProcessUtil.e());
        }
        return this.e.booleanValue();
    }

    final boolean e(long j) {
        return d() == 0 && System.currentTimeMillis() - this.d > j;
    }

    protected final void c(boolean z, long j) {
        this.e = Boolean.valueOf(z);
        Intent intent = new Intent(HwWatchFaceApi.ACTION_FOREGROUND_STATUS);
        intent.setPackage(BaseApplication.d());
        intent.putExtra("isForeground", z);
        intent.putExtra("time", j);
        BaseApplication.e().sendBroadcast(intent, SecurityConstant.d);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
            Boolean bool = this.e;
            this.e = Boolean.valueOf(vV_(intent, bool == null ? true : bool.booleanValue()));
            this.d = vW_(intent, this.d);
            LogUtil.c("HAF_ActivityMonitor", "action=", intent.getAction(), ", isForeground=", Boolean.valueOf(j()));
        }
    }

    private boolean vV_(Intent intent, boolean z) {
        try {
            return intent.getBooleanExtra("isForeground", z);
        } catch (RuntimeException e) {
            LogUtil.e("HAF_ActivityMonitor", "readIsForeground ex=", LogUtil.a(e));
            return z;
        }
    }

    private long vW_(Intent intent, long j) {
        try {
            return intent.getLongExtra("time", j);
        } catch (RuntimeException e) {
            LogUtil.e("HAF_ActivityMonitor", "readTime ex=", LogUtil.a(e));
            return j;
        }
    }
}
