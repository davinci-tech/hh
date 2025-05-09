package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
public class mrp {
    private static String e = "";

    public static String b(String str, String str2) {
        e = "";
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("ConfigurationPointUtils", "configurationPoint or deviceType is empty");
            return e;
        }
        e = str + "?";
        a(str2);
        return e;
    }

    public static String e(Context context, String str) {
        e = "";
        if (context == null) {
            LogUtil.h("ConfigurationPointUtils", "context == null");
            return e;
        }
        if (CloudUtils.d()) {
            if (CommonUtil.ag(context)) {
                e(str);
            } else if (CommonUtil.cc() && msr.c.containsKey(str)) {
                e = msr.b.get(str) + "?";
                a(str);
            } else {
                d(str);
            }
        } else if (msr.c.containsKey(str)) {
            if (CommonUtil.ag(context)) {
                e = msr.f15154a.get(str) + "?";
            } else {
                e = msr.b.get(str) + "?";
            }
            a(str);
        }
        return e;
    }

    private static String a(String str) {
        LogUtil.a("ConfigurationPointUtils", "deviceType = " + str);
        if (msr.e.containsKey(str)) {
            e += msr.e.get(str);
        } else {
            LogUtil.h("ConfigurationPointUtils", "No MatchRules");
        }
        return e;
    }

    private static String e(String str) {
        if ("HDK_WEIGHT".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_BLOOD_SUGAR".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_BLOOD_PRESSURE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_HEART_RATE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_BODY_TEMPERATURE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_BLOOD_OXYGEN".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_ROPE_SKIPPING".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig?";
        } else if ("HDK_TREADMILL".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig_20200811?";
        } else if ("HDK_EXERCISE_BIKE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig_20200811?";
        } else if ("HDK_ROWING_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig_20200811?";
        } else if ("HDK_ELLIPTICAL_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig_20200811?";
        } else if ("HDK_WALKING_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfig_20200811?";
        } else {
            LogUtil.h("ConfigurationPointUtils", "release this is other device");
        }
        String str2 = e + msr.a(str);
        e = str2;
        return str2;
    }

    private static String d(String str) {
        if ("HDK_WEIGHT".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_BLOOD_SUGAR".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_BLOOD_PRESSURE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_HEART_RATE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_BODY_TEMPERATURE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_BLOOD_OXYGEN".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_ROPE_SKIPPING".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta?";
        } else if ("HDK_TREADMILL".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta_20200811?";
        } else if ("HDK_EXERCISE_BIKE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta_20200811?";
        } else if ("HDK_ROWING_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta_20200811?";
        } else if ("HDK_ELLIPTICAL_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta_20200811?";
        } else if ("HDK_WALKING_MACHINE".equals(str)) {
            e = "com.huawei.health_ThirdPartyDevice_deviceConfigBeta_20200811?";
        } else {
            LogUtil.h("ConfigurationPointUtils", "beta this is other device");
        }
        String str2 = e + msr.a(str);
        e = str2;
        return str2;
    }
}
