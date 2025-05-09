package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class sdg {
    public static String a() {
        DeviceInfo a2 = jpt.a("PressureMeasureUtils");
        if (a2 == null) {
            return "";
        }
        String deviceName = a2.getDeviceName();
        LogUtil.c("PressureMeasureUtils", "deviceName = " + deviceName);
        return deviceName;
    }
}
