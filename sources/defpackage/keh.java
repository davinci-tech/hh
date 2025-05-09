package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class keh {
    public static void b(long j, long j2, boolean z) {
        LogUtil.a("BasicDataSendCommandUtil", "getHeartRateRaise enter.supportDownRate: ", Boolean.valueOf(z));
        jsz.b(BaseApplication.getContext()).sendDeviceData(kef.b(j, j2, z));
    }

    public static void e(long j, long j2) {
        LogUtil.a("BasicDataSendCommandUtil", "sendBloodOxygenDownRemindCount enter");
        jsz.b(BaseApplication.getContext()).sendDeviceData(kef.a(j, j2));
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        LogUtil.a("BasicDataSendCommandUtil", "sendBloodOxygenDownRemindDetail enter");
        jsz.b(BaseApplication.getContext()).sendDeviceData(kef.c(i, deviceInfo));
    }
}
