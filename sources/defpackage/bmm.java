package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.model.CommonUtil;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bmm {
    public static boolean a(DeviceInfo deviceInfo, int i) {
        LogUtil.c(CommonUtil.TAG, "checkSupportCapability");
        if (deviceInfo == null) {
            LogUtil.a(CommonUtil.TAG, "checkSupportCapability deviceInfo is null");
            return false;
        }
        String expandCapability = deviceInfo.getExpandCapability();
        LogUtil.c(CommonUtil.TAG, "checkSupportCapability hexString: ", expandCapability);
        if (expandCapability != null) {
            return d(blq.a(expandCapability), i);
        }
        return false;
    }

    private static boolean d(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return false;
        }
        if (i < 0) {
            LogUtil.a(CommonUtil.TAG, "isSupport target : ", Integer.valueOf(i));
            return false;
        }
        int i2 = bArr[i / 8];
        int i3 = 1 << (i % 8);
        return (i2 & i3) == i3;
    }
}
