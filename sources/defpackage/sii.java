package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class sii {
    private static boolean a(DeviceInfo deviceInfo, sjy sjyVar) {
        String d = HEXUtils.d(deviceInfo.getUuid());
        String uniqueId = sjyVar.getUniqueId();
        StringBuilder sb = new StringBuilder();
        sb.append(d);
        sb.append("#ANDROID21");
        return uniqueId.equalsIgnoreCase(sb.toString()) || sjyVar.getUniqueId().equals(d);
    }

    public static boolean a(sjy sjyVar) {
        Iterator<DeviceInfo> it = sid.a().iterator();
        while (it.hasNext()) {
            if (a(it.next(), sjyVar)) {
                LogUtil.a("WX_DeviceUtils", "device is exist at local :", sjyVar.getDeviceName());
                return true;
            }
        }
        return false;
    }

    public static sjy e(DeviceInfo deviceInfo, List<sjy> list) {
        for (sjy sjyVar : list) {
            if (a(deviceInfo, sjyVar)) {
                LogUtil.a("WX_DeviceUtils", "Contain device = ", deviceInfo.getDeviceName());
                return sjyVar;
            }
        }
        return null;
    }

    public static boolean d(DeviceInfo deviceInfo, com.huawei.hwcloudmodel.model.userprofile.DeviceInfo deviceInfo2) {
        if (deviceInfo == null || deviceInfo2 == null) {
            return false;
        }
        if (!deviceInfo2.getUniqueId().equalsIgnoreCase(HEXUtils.d(deviceInfo.getUuid()) + "#ANDROID21")) {
            return false;
        }
        if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId()) || TextUtils.isEmpty(deviceInfo2.getProdId()) || deviceInfo.getHiLinkDeviceId().equalsIgnoreCase(deviceInfo2.getProdId())) {
            return true;
        }
        LogUtil.h("WX_DeviceUtils", "prodid not same");
        return false;
    }

    public static DeviceInfo a(String str) {
        LogUtil.c("WX_DeviceUtils", "findDeviceAtLocal identify  = ", CommonUtil.l(str));
        for (DeviceInfo deviceInfo : sid.a()) {
            if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(str)) {
                LogUtil.c("WX_DeviceUtils", "findDeviceAtLocal return  = ", deviceInfo.getDeviceName());
                return deviceInfo;
            }
        }
        LogUtil.b("WX_DeviceUtils", "findDeviceAtLocal failed , return empty");
        return new DeviceInfo();
    }
}
