package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import java.util.Map;

/* loaded from: classes3.dex */
public class cji {
    public static boolean a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_ScaleCapabilityUtils", "uniqueId = null");
            return false;
        }
        Map<String, UniteDevice> e = cjg.d().e();
        if (e == null || e.size() <= 0) {
            LogUtil.h("PluginDevice_ScaleCapabilityUtils", "deviceList = null");
            return false;
        }
        UniteDevice uniteDevice = e.get(str);
        if (uniteDevice == null || uniteDevice.getCapability() == null) {
            LogUtil.h("PluginDevice_ScaleCapabilityUtils", "device = null or device.getCapability = null");
            return false;
        }
        String capacity = uniteDevice.getCapability().getCapacity();
        LogUtil.a("PluginDevice_ScaleCapabilityUtils", "isSupport hexString = ", capacity);
        if (TextUtils.isEmpty(capacity)) {
            return false;
        }
        boolean a2 = CommonUtil.a(cvx.a(capacity), i);
        LogUtil.a("PluginDevice_ScaleCapabilityUtils", "CommonUtil.isSupport = ", Boolean.valueOf(a2));
        return a2;
    }
}
