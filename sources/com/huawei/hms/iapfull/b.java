package com.huawei.hms.iapfull;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtils;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes4.dex */
public class b {
    public static void a(Context context, String str, LinkedHashMap<String, String> linkedHashMap) {
        String str2;
        String str3 = "";
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        linkedHashMap.put("machine", Build.MODEL);
        linkedHashMap.put("appVersion", "2.2.0.300");
        linkedHashMap.put("eventTime", (TextUtils.isEmpty("yyyy-MM-dd-HH-mm-ss-SSS") ? new SimpleDateFormat("yyyy-MM-dd", Locale.US) : new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)).format(new Date()));
        linkedHashMap.put("service", "hms_iap_full");
        int i = z0.f4791a;
        linkedHashMap.put("manufacturer", Build.MANUFACTURER);
        linkedHashMap.put("deviceBrand", Build.BRAND);
        linkedHashMap.put("accessMode", "0");
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, "ro.build.2b2c.partner.ext_channel", "");
        } catch (ClassNotFoundException unused) {
            str2 = "getSystemProperties occur ClassNotFoundException.";
            y0.a("BrandUtil", str2);
            y0.b("BrandUtil", "getSystemProperties, key is ro.build.2b2c.partner.ext_channel，value is empty ? " + TextUtils.isEmpty(str3));
            linkedHashMap.put("extChannelCode", str3);
            HiAnalyticsUtils.getInstance().onNewEvent(context.getApplicationContext(), str, linkedHashMap);
        } catch (IllegalAccessException unused2) {
            str2 = "getSystemProperties occur IllegalAccessException.";
            y0.a("BrandUtil", str2);
            y0.b("BrandUtil", "getSystemProperties, key is ro.build.2b2c.partner.ext_channel，value is empty ? " + TextUtils.isEmpty(str3));
            linkedHashMap.put("extChannelCode", str3);
            HiAnalyticsUtils.getInstance().onNewEvent(context.getApplicationContext(), str, linkedHashMap);
        } catch (NoSuchMethodException unused3) {
            str2 = "getSystemProperties occur NoSuchMethodException.";
            y0.a("BrandUtil", str2);
            y0.b("BrandUtil", "getSystemProperties, key is ro.build.2b2c.partner.ext_channel，value is empty ? " + TextUtils.isEmpty(str3));
            linkedHashMap.put("extChannelCode", str3);
            HiAnalyticsUtils.getInstance().onNewEvent(context.getApplicationContext(), str, linkedHashMap);
        } catch (InvocationTargetException unused4) {
            str2 = "getSystemProperties occur InvocationTargetException.";
            y0.a("BrandUtil", str2);
            y0.b("BrandUtil", "getSystemProperties, key is ro.build.2b2c.partner.ext_channel，value is empty ? " + TextUtils.isEmpty(str3));
            linkedHashMap.put("extChannelCode", str3);
            HiAnalyticsUtils.getInstance().onNewEvent(context.getApplicationContext(), str, linkedHashMap);
        }
        y0.b("BrandUtil", "getSystemProperties, key is ro.build.2b2c.partner.ext_channel，value is empty ? " + TextUtils.isEmpty(str3));
        linkedHashMap.put("extChannelCode", str3);
        HiAnalyticsUtils.getInstance().onNewEvent(context.getApplicationContext(), str, linkedHashMap);
    }
}
