package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class peg {
    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.endsWith(".calendar");
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.endsWith(".totemweather");
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.endsWith(".contacts");
    }

    public static boolean b() {
        return c() || h() || a() || d() || e();
    }

    public static boolean c() {
        DeviceInfo d = jpt.d("DataSyncSwitchUtils");
        if (d == null) {
            LogUtil.h("DataSyncSwitchUtils", "isSupportCalendarDataSync deviceInfo is null");
            return false;
        }
        return cwi.c(d, 21);
    }

    public static boolean e() {
        DeviceInfo d = jpt.d("DataSyncSwitchUtils");
        if (d == null) {
            LogUtil.h("DataSyncSwitchUtils", "isSupportHealthCalendarSync deviceInfo is null");
            return false;
        }
        boolean a2 = jpp.a(d);
        LogUtil.a("DataSyncSwitchUtils", "getSingleFrameDevice: ", Integer.valueOf(d.getSingleFrameDevice()));
        boolean z = d.getSingleFrameDevice() == 1 && CommonUtil.as();
        if (a2) {
            if (!CommonUtil.bh() || z) {
                return cwi.c(d, 184);
            }
            return false;
        }
        return cwi.c(d, 171);
    }

    public static boolean h() {
        DeviceInfo d = jpt.d("DataSyncSwitchUtils");
        if (d == null) {
            LogUtil.h("DataSyncSwitchUtils", "isSupportWeatherDataSync deviceInfo is null");
            return false;
        }
        return cwi.c(d, 22);
    }

    public static boolean a() {
        DeviceInfo d = jpt.d("DataSyncSwitchUtils");
        if (d == null) {
            LogUtil.h("DataSyncSwitchUtils", "isSupportContactDataSync deviceInfo is null");
            return false;
        }
        return cwi.c(d, 23);
    }

    public static boolean d() {
        return joj.a().c();
    }
}
