package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cwi {
    public static boolean c(DeviceInfo deviceInfo, int i) {
        return b(deviceInfo, i);
    }

    private static boolean c(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return false;
        }
        if (i < 0) {
            LogUtil.h(CommonUtil.TAG, "isSupport target : ", Integer.valueOf(i));
            return false;
        }
        int i2 = bArr[i / 8];
        int i3 = 1 << (i % 8);
        return (i2 & i3) == i3;
    }

    public static boolean b(DeviceInfo deviceInfo, int... iArr) {
        if (deviceInfo == null || iArr == null || iArr.length < 1) {
            LogUtil.h(CommonUtil.TAG, "checkSupportAndCapability deviceInfo is null, or bitIndexs is empty");
            return false;
        }
        String expandCapability = deviceInfo.getExpandCapability();
        LogUtil.a(CommonUtil.TAG, "checkSupportAndCapability hexString: ", expandCapability);
        if (expandCapability == null) {
            return false;
        }
        byte[] a2 = cvx.a(expandCapability);
        for (int i : iArr) {
            if (!c(a2, i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(DeviceInfo deviceInfo, int... iArr) {
        if (deviceInfo == null || iArr == null || iArr.length < 1) {
            LogUtil.h(CommonUtil.TAG, "checkSupportOrCapability deviceInfo is null, or bitIndexs is empty");
            return false;
        }
        String expandCapability = deviceInfo.getExpandCapability();
        LogUtil.a(CommonUtil.TAG, "checkSupportOrCapability hexString: ", expandCapability);
        if (expandCapability != null) {
            byte[] a2 = cvx.a(expandCapability);
            for (int i : iArr) {
                if (c(a2, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}
