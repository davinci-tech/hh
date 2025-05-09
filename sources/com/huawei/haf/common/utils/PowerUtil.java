package com.huawei.haf.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class PowerUtil {
    private static PowerManager c;

    private PowerUtil() {
    }

    public static boolean a(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            r0 = registerReceiver.getIntExtra("plugged", -1) != 0;
            LogUtil.c("PowerUtil", "isCharging = ", Boolean.valueOf(r0));
        }
        return r0;
    }

    static PowerManager xN_() {
        PowerManager powerManager = c;
        if (powerManager != null) {
            return powerManager;
        }
        PowerManager xC_ = CommonUtils.xC_();
        c = xC_;
        return xC_;
    }

    public static int b(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return -1;
        }
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra == -1 || intExtra2 == -1) {
            LogUtil.a("PowerUtil", "getBatteryPercent currentLevel=", Integer.valueOf(intExtra), ", currentScale=", Integer.valueOf(intExtra2));
            return -1;
        }
        if (intExtra2 == 0) {
            LogUtil.a("PowerUtil", "getBatteryPercent currentScale is 0");
            return -1;
        }
        int i = (intExtra * 100) / intExtra2;
        LogUtil.c("PowerUtil", "currentLevel=", Integer.valueOf(intExtra), ", currentScale=", Integer.valueOf(intExtra2), ", percent=", Integer.valueOf(i));
        return i;
    }
}
