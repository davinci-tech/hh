package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bjk {
    public static boolean d(int i) {
        return 4 == i;
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return bjl.e().a(str);
    }

    public static boolean a(String str) {
        if (!c(str)) {
            return false;
        }
        LogUtil.c("HiChain3CommandUtil", "start isHiChain3DeviceOn");
        return bjl.e().e(str);
    }

    public static void e(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.a("HiChain3CommandUtil", "setIsHiChain3DeviceOn btDeviceInfo is null");
        } else if (c(deviceInfo.getDeviceMac())) {
            LogUtil.c("HiChain3CommandUtil", "start setIsHiChain3DeviceOn");
            bjl.e().d(deviceInfo.getDeviceMac(), z);
        }
    }
}
