package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.p2p.EnumCapabilityItem;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bla {
    public static boolean e(String str, EnumCapabilityItem enumCapabilityItem) {
        if (TextUtils.isEmpty(str) || enumCapabilityItem == null) {
            LogUtil.a("CapabilityUtils", "isSupport deviceId or item is empty.");
            return false;
        }
        UniteDevice d = spi.d().d(str);
        if (d == null) {
            LogUtil.a("CapabilityUtils", "getOtherConnectedDevice is invalid.");
            return false;
        }
        if (!str.equals(d.getIdentify())) {
            LogUtil.a("CapabilityUtils", "deviceId not equal");
            return false;
        }
        if (e(d, enumCapabilityItem.getValue())) {
            return true;
        }
        return e(d, enumCapabilityItem);
    }

    private static boolean e(UniteDevice uniteDevice, EnumCapabilityItem enumCapabilityItem) {
        if (uniteDevice == null) {
            LogUtil.a("CapabilityUtils", "isSupportOldCapability deviceInfo is null");
            return false;
        }
        if (TextUtils.isEmpty(uniteDevice.getIdentify())) {
            LogUtil.a("CapabilityUtils", "isSupportOldCapability deviceIdentify is empty");
            return false;
        }
        ExternalDeviceCapability capability = uniteDevice.getCapability();
        DeviceCapability compatibleCapacity = capability != null ? capability.getCompatibleCapacity() : null;
        if (compatibleCapacity == null) {
            LogUtil.a("CapabilityUtils", "isSupportOldCapability deviceCapability is null");
            return false;
        }
        if (enumCapabilityItem.getValue() == 2) {
            return compatibleCapacity.isSupportHiWear();
        }
        if (enumCapabilityItem.getValue() >= 3 && enumCapabilityItem.getValue() <= 12) {
            return compatibleCapacity.isSupportWearEngine();
        }
        if (enumCapabilityItem.getValue() == 13) {
            return compatibleCapacity.isSupportCheckDeviceSpace();
        }
        return false;
    }

    public static boolean e(UniteDevice uniteDevice, int i) {
        if (uniteDevice == null) {
            LogUtil.a("CapabilityUtils", "checkSupportCapability deviceInfo is null");
            return false;
        }
        if (uniteDevice.getCapability() == null) {
            LogUtil.a("CapabilityUtils", "device Capability is null");
            return false;
        }
        String capacity = uniteDevice.getCapability().getCapacity();
        LogUtil.c("CapabilityUtils", "checkSupportCapability hexString: ", capacity);
        boolean e = capacity != null ? e(blq.a(capacity), i) : false;
        LogUtil.c("CapabilityUtils", "checkSupportCapability isSupport: ", Boolean.valueOf(e));
        return e;
    }

    public static boolean e(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return false;
        }
        if (i < 0) {
            LogUtil.a("CapabilityUtils", "isSupport target: ", Integer.valueOf(i));
            return false;
        }
        int i2 = bArr[i / 8];
        int i3 = 1 << (i % 8);
        return (i2 & i3) == i3;
    }
}
