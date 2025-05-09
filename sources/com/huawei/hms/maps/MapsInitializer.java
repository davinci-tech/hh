package com.huawei.hms.maps;

import android.content.Context;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.utils.MapClientUtil;
import com.huawei.hms.maps.utils.MapsAdvUtil;

/* loaded from: classes4.dex */
public final class MapsInitializer {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4940a = false;
    private static String b;

    public static void setGrsCountryCode(String str) {
        synchronized (MapsInitializer.class) {
            if (MapClientUtil.validCountryCodeWithoutRegions(str)) {
                b = str;
                MapClientIdentify.setCountryCode(str);
            }
        }
    }

    public static void setAppId(String str) {
        MapClientIdentify.setAppId(str);
    }

    public static void setApiKey(String str) {
        MapClientIdentify.setApiKey(str);
    }

    public static void setAccessToken(String str) {
        MapsAdvUtil.setAccessToken(str);
    }

    public static void initialize(Context context, String str) {
        synchronized (MapsInitializer.class) {
            b = str;
            initialize(context);
        }
    }

    public static void initialize(Context context) {
        synchronized (MapsInitializer.class) {
            BitmapDescriptorFactory.setContext(context);
            if (context != null) {
                if (f4940a) {
                    return;
                }
                com.huawei.hms.maps.common.util.maa.a(context, b);
                f4940a = true;
            }
        }
    }

    protected static boolean a() {
        return f4940a;
    }
}
