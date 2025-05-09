package defpackage;

import android.content.Context;
import android.os.Build;
import com.huawei.agconnect.apms.Agent;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class ksq {
    public static void c(Context context, boolean z) {
        d(context, 0, z);
    }

    public static void d(Context context, int i, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("uuid", kti.b(context));
        hashMap.put("isHMSCore ", i + "");
        hashMap.put("isSignInByMCP", z + "");
        hashMap.put("os", Agent.OS_NAME);
        hashMap.put("os_ver", Build.VERSION.SDK_INT + "");
        hashMap.put("sdkName", "accountPicker");
        hashMap.put("sdk_ver", "60900100");
        hashMap.put("pub_mfc", Build.MANUFACTURER);
        hashMap.put(JsbMapKeyNames.H5_BRAND, Build.BRAND);
        hashMap.put("service", "AccountPicker");
        hashMap.put("screenResolution", ktb.bQK_(context).toString());
        ksy.b("HiAnalyticsUtils", "HA_params = " + hashMap.toString(), false);
        HiAnalyticsUtil.getInstance().onEvent(context, "accountPicker_deviceinfo", hashMap);
    }
}
