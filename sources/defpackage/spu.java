package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class spu {
    public static UniteDevice e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("CommonConfigUtil", "getUniteDevice identify is invalid.");
            return null;
        }
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceMac(str);
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(str);
        uniteDevice.setDeviceInfo(deviceInfo);
        return uniteDevice;
    }
}
