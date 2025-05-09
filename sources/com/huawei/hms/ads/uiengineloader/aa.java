package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class aa {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4364a = "ApkUtil";
    private static final Map<String, List<String>> b;

    private static byte[] f(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return a(packageManager.getPackageInfo(str, 64));
            }
        } catch (Throwable th) {
            af.d(f4364a, "getPackageSignatureBytes RuntimeException:" + th.getClass().getSimpleName());
        }
        return new byte[0];
    }

    private static PackageInfo e(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(str, 128);
            }
            return null;
        } catch (Throwable unused) {
            af.c(f4364a, "getPackageInfo Exception");
            return null;
        }
    }

    private static boolean d(Context context, String str) {
        return e(context, str) != null;
    }

    private static byte[] c(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return a(packageManager.getPackageArchiveInfo(str, 64));
            }
        } catch (Throwable th) {
            af.d(f4364a, "getPackageSignatureBytesByPath RuntimeException:" + th.getClass().getSimpleName());
        }
        return new byte[0];
    }

    public static String b(Context context, String str) {
        byte[] f = f(context, str);
        if (f == null || f.length == 0) {
            return null;
        }
        return ae.a(ai.a(f));
    }

    public static String b(Context context) {
        if (d(context, "com.huawei.hwid")) {
            return "com.huawei.hwid";
        }
        return d(context, "com.huawei.hms") ? "com.huawei.hms" : d(context, "com.huawei.hwid.tv") ? "com.huawei.hwid.tv" : "com.huawei.hwid";
    }

    private static byte[] a(PackageInfo packageInfo) {
        ByteArrayInputStream byteArrayInputStream = null;
        if (packageInfo != null) {
            try {
                if (packageInfo.signatures.length > 0) {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                    try {
                        byte[] encoded = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream2).getEncoded();
                        aj.a(byteArrayInputStream2);
                        return encoded;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        try {
                            af.d(f4364a, "getPackageSignatureBytes Exception:" + th.getClass().getSimpleName());
                            aj.a(byteArrayInputStream);
                            af.b(f4364a, "Failed to get application signature certificate fingerprint.");
                            return new byte[0];
                        } catch (Throwable th2) {
                            aj.a(byteArrayInputStream);
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        aj.a(byteArrayInputStream);
        af.b(f4364a, "Failed to get application signature certificate fingerprint.");
        return new byte[0];
    }

    public static boolean a(List<String> list, String str) {
        if (list == null || TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context) {
        String b2 = b(context);
        return a(b.get(b2), b(context, b2));
    }

    public static String a(Context context, String str) {
        byte[] c = c(context, str);
        if (c == null || c.length == 0) {
            return null;
        }
        return ae.a(ai.a(c));
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put("com.huawei.hwid", Arrays.asList("b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"));
        hashMap.put("com.huawei.hms", Arrays.asList("e49d5c2c0e11b3b1b96ca56c6de2a14ec7dab5ccc3b5f300d03e5b4dba44f539"));
        hashMap.put("com.huawei.hwid.tv", Arrays.asList("3517262215d8d3008cbf888750b6418edc4d562ac33ed6874e0d73aba667bc3c"));
    }
}
