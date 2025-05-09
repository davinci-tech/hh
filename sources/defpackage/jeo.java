package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcoresleepmgr.SyncBaseFunction;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class jeo {
    public static String a(SyncBaseFunction syncBaseFunction) {
        DeviceInfo e = e(syncBaseFunction);
        if (e == null) {
            return "";
        }
        return e.getSecurityUuid() + "#ANDROID21";
    }

    public static DeviceInfo e(SyncBaseFunction syncBaseFunction) {
        LogUtil.a("DeviceUtil", "phonservice getDeviceInfo ");
        return syncBaseFunction.getDeviceInfo();
    }

    public static void b(SyncBaseFunction syncBaseFunction) {
        HashMap hashMap = new HashMap();
        hashMap.put(1, Integer.toString(1));
        syncBaseFunction.sendCommandToDevice(24, hashMap, e(syncBaseFunction));
    }

    public static void c(int i, SyncBaseFunction syncBaseFunction) {
        HashMap hashMap = new HashMap();
        hashMap.put(1, Integer.toString(i));
        syncBaseFunction.sendCommandToDevice(25, hashMap, e(syncBaseFunction));
    }

    public static DeviceCapability e(DeviceInfo deviceInfo, SyncBaseFunction syncBaseFunction) {
        return syncBaseFunction.getDeviceCapability(deviceInfo);
    }
}
