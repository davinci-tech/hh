package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class sdr {
    public static boolean a() {
        DeviceInfo d = jpt.d("ThreeCircleSwitchUtil");
        if (d == null) {
            LogUtil.h("ThreeCircleSwitchUtil", "isSupportNotifyReminderSwitch deviceInfo is null");
            return false;
        }
        return cwi.c(d, 154);
    }
}
