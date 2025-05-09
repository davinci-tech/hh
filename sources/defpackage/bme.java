package defpackage;

import android.text.TextUtils;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bme {
    private static final int[][] e = {new int[]{1, 61}, new int[]{1, 62}, new int[]{1, 5}, new int[]{1, 50}, new int[]{1, 53}, new int[]{1, 63}, new int[]{1, 59}};

    public static boolean b() {
        return false;
    }

    public static boolean c(int i, int i2, String str, String str2) {
        if (b() || !"assistant_relationship".equalsIgnoreCase(e(str)) || a(str) != 2) {
            return false;
        }
        for (int[] iArr : e) {
            if (i == iArr[0] && i2 == iArr[1]) {
                return false;
            }
        }
        LogUtil.a("ShieldAssistantDeviceUtil", "isShieldCommand serviceId: ", Integer.valueOf(i), " commandId: ", Integer.valueOf(i2), " tag: ", str2, blt.a(str));
        return true;
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ShieldAssistantDeviceUtil", "getUnderConnectState identify is empty");
            return 0;
        }
        for (UniteDevice uniteDevice : bgl.c().getDeviceList().values()) {
            if (uniteDevice != null && uniteDevice.getDeviceInfo() != null && str.equals(uniteDevice.getDeviceInfo().getDeviceMac())) {
                return uniteDevice.getDeviceInfo().getDeviceConnectState();
            }
        }
        return 0;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ShieldAssistantDeviceUtil", "identify is empty");
            return null;
        }
        String deviceRelation = snu.e().getDeviceRelation(str);
        if (TextUtils.isEmpty(deviceRelation)) {
            LogUtil.a("ShieldAssistantDeviceUtil", "getRelation failed relation is empty: ", blt.a(str));
        }
        LogUtil.a("ShieldAssistantDeviceUtil", "getRelation relation is ", deviceRelation);
        return deviceRelation;
    }
}
