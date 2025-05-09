package com.huawei.hms.network.api;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.PackageManagerCompat;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.framework.common.StringUtils;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5115a = "DynamicLoadPermit";
    private static final String b = "networkkit_";
    private static final String c = "dynamic_load";
    private static final String d = "networkkit_dynamic_load";
    private static final String e = "share_pre_config";
    private static final String f = "disable";

    private static boolean c(Context context) {
        Logger.d(f5115a, "dynamic load sp:" + new PLSharedPreferences(context, "share_pre_config").getString("dynamic_load"));
        return !f.equalsIgnoreCase(r2);
    }

    private static boolean b(Context context) {
        Logger.d(f5115a, "meta-data networkkit_dynamic_load:" + PackageManagerCompat.getMetaDataFromApp(context, d, ""));
        return !f.equalsIgnoreCase(r2);
    }

    public static boolean a(Context context) {
        try {
            boolean checkCompatible = ReflectionUtils.checkCompatible("com.huawei.hms.network.AdvanceNetworkKitProvider");
            boolean b2 = b(context);
            if (!checkCompatible || b2) {
                boolean c2 = c(context);
                Logger.i(f5115a, "is dynamic load: " + c2 + ", advance: " + checkCompatible);
                return c2;
            }
            Logger.i(f5115a, "is dynamic load: false, advance: true");
            return false;
        } catch (Exception e2) {
            Logger.e(f5115a, "get meta-data or sp error " + StringUtils.anonymizeMessage(e2.getMessage()));
            return true;
        }
    }
}
