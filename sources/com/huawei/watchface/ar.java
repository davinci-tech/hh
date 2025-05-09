package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class ar {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f10905a = {"client_safe_http_url", "client_safe_url_watchface", "client_safe_url_watchface_sensitive_v2"};
    private static List<String> b = new CopyOnWriteArrayList();
    private static List<String> c = new CopyOnWriteArrayList();
    private static List<String> d = new CopyOnWriteArrayList();
    private static List<String> e = new CopyOnWriteArrayList();
    private static List<String> f = new CopyOnWriteArrayList();
    private static List<String> g = new CopyOnWriteArrayList();

    public static List<String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HwLog.i("ListTypeParamManager", "getSystemParam() name: " + str);
        if ("client_safe_http_url".equalsIgnoreCase(str)) {
            return b;
        }
        if ("client_huawei_h5_host".equals(str)) {
            return g;
        }
        if ("client_watchface_nodark_url".equals(str)) {
            return f;
        }
        if ("client_safe_url_watchface".equals(str)) {
            return c;
        }
        if ("client_safe_url_watchface_sensitive_v2".equals(str)) {
            return d;
        }
        if ("client_auth_error_params".equals(str)) {
            return e;
        }
        return null;
    }

    public static boolean b(String str) {
        return "client_safe_http_url".equals(str) || "client_huawei_h5_host".equals(str) || "client_watchface_nodark_url".equals(str) || "client_safe_url_watchface".equals(str) || "client_safe_url_watchface_sensitive_v2".equals(str);
    }

    public static void a(String str, String str2) {
        HwLog.i("ListTypeParamManager", "addParam() name: " + str);
        if ("client_safe_http_url".equalsIgnoreCase(str)) {
            if (b.contains(str2)) {
                return;
            }
            b.add(str2);
            return;
        }
        if ("client_huawei_h5_host".equals(str)) {
            if (g.contains(str2)) {
                return;
            }
            g.add(str2);
            return;
        }
        if ("client_watchface_nodark_url".equals(str)) {
            if (f.contains(str2)) {
                return;
            }
            f.add(str2);
        } else if ("client_safe_url_watchface".equalsIgnoreCase(str)) {
            if (c.contains(str2)) {
                return;
            }
            c.add(str2);
        } else if ("client_safe_url_watchface_sensitive_v2".equalsIgnoreCase(str)) {
            if (d.contains(str2)) {
                return;
            }
            d.add(str2);
        } else {
            if (!"client_auth_error_params".equalsIgnoreCase(str) || e.contains(str2)) {
                return;
            }
            e.add(str2);
        }
    }
}
