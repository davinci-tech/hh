package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gwb {
    public static final boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("DevicesInfoUtils", "deviceInfo is null");
            return false;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a("DevicesInfoUtils", "productType:", Integer.valueOf(productType));
        if (productType == 63) {
            return true;
        }
        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
        return productType == 36 && e != null && e.isSupportWorkoutCapabilicy();
    }
}
