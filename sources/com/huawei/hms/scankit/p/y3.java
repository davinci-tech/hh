package com.huawei.hms.scankit.p;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class y3 {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5926a = "y3";
    private static volatile Bundle b = null;
    public static String c = "";
    public static HashSet<String> d = new HashSet<>();

    public static Bundle a(Context context) {
        if (context == null) {
            return new Bundle();
        }
        if (b == null) {
            Bundle bundle = new Bundle();
            try {
                String string = AGConnectServicesConfig.fromContext(context).getString(AgConnectInfo.AgConnectKey.APPLICATION_ID);
                if (string == null) {
                    string = context.getPackageName();
                }
                bundle.putString("appid", string);
            } catch (RuntimeException unused) {
                Log.e(f5926a, "getAppInfo: RuntimeException");
            } catch (Exception unused2) {
                Log.e(f5926a, "getAppInfo: Exception");
            }
            b = bundle;
        }
        return b;
    }

    public static String b(Context context) {
        if (c.length() == 0) {
            c = context.getPackageName();
        }
        return c;
    }

    public static HashSet<String> a() {
        if (d.size() == 0) {
            d.add("com.huawei.scanner");
            d.add("com.huawei.hitouch");
        }
        return d;
    }

    public static boolean a(String str, HashSet<String> hashSet) {
        if (str.length() == 0) {
            return true;
        }
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return false;
            }
        }
        return true;
    }
}
