package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class jqx {
    private static boolean c = false;

    public static boolean a() {
        return e(jpt.d("CommandDispatchController"));
    }

    public static boolean e(DeviceInfo deviceInfo) {
        int i;
        DeviceCapability deviceCapability;
        if (deviceInfo == null) {
            LogUtil.a("CommandDispatchController", "isWalletOpenCard:", "deviceInfo == null");
            deviceCapability = cvs.d();
            i = 1;
        } else {
            DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
            i = 2;
            if (e == null) {
                Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "CommandDispatchController");
                if (a2 != null && !a2.isEmpty()) {
                    e = a2.get(deviceInfo.getDeviceIdentify());
                    i = 3;
                }
                if (e != null) {
                    cvs.a(deviceInfo.getDeviceIdentify(), e);
                }
            }
            deviceCapability = e;
        }
        if (deviceCapability == null) {
            if (CommonUtil.as() && !c) {
                c = true;
                sqo.ae("isWalletOpenCard deviceCapability is null, riskState: " + i);
            }
            LogUtil.h("CommandDispatchController", "isWalletOpenCard deviceCapability == null");
            return false;
        }
        LogUtil.a("CommandDispatchController", "isWalletOpenCard:", Boolean.valueOf(deviceCapability.isSupportWalletOpenCard()), " isSupportAppId:", Boolean.valueOf(deviceCapability.isSupportAppId()));
        return deviceCapability.isSupportWalletOpenCard() && deviceCapability.isSupportAppId();
    }

    public static boolean e(byte[] bArr) {
        return bArr != null && bArr.length >= 6 && bArr[2] == 123 && bArr[5] == 3;
    }

    public static boolean b(byte[] bArr) {
        if (bArr == null || bArr.length < 6 || bArr[2] != 123) {
            return false;
        }
        byte b = bArr[5];
        return b == 4 || b == 5;
    }

    public static boolean c(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("CommandDispatchController", "data illegal");
            return false;
        }
        byte b = bArr[1];
        return b >= 1 && b <= 9;
    }

    public static boolean b(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("CommandDispatchController", "data illegal");
            return false;
        }
        byte b = bArr[1];
        return b == 1 || b == 5 || b == 8 || b == 9 || (i == 1 && b == 2);
    }
}
