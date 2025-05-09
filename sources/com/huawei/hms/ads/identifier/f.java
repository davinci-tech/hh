package com.huawei.hms.ads.identifier;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;

/* loaded from: classes9.dex */
public class f {
    private static byte[] d(Context context, String str) {
        StringBuilder sb;
        PackageInfo packageInfo;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null && packageInfo.signatures.length > 0) {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                    try {
                        byte[] encoded = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream2).getEncoded();
                        j.a(byteArrayInputStream2);
                        return encoded;
                    } catch (RuntimeException e) {
                        e = e;
                        byteArrayInputStream = byteArrayInputStream2;
                        sb = new StringBuilder("getPackageSignatureBytes RuntimeException:");
                        sb.append(e.getClass().getSimpleName());
                        Log.e("ApkUtil", sb.toString());
                        j.a(byteArrayInputStream);
                        Log.i("ApkUtil", "Failed to get application signature certificate fingerprint.");
                        return new byte[0];
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        sb = new StringBuilder("getPackageSignatureBytes Exception:");
                        sb.append(th.getClass().getSimpleName());
                        Log.e("ApkUtil", sb.toString());
                        j.a(byteArrayInputStream);
                        Log.i("ApkUtil", "Failed to get application signature certificate fingerprint.");
                        return new byte[0];
                    }
                }
            } catch (Throwable th2) {
                j.a(byteArrayInputStream);
                throw th2;
            }
        } catch (RuntimeException e2) {
            e = e2;
        } catch (Throwable th3) {
            th = th3;
        }
        j.a(byteArrayInputStream);
        Log.i("ApkUtil", "Failed to get application signature certificate fingerprint.");
        return new byte[0];
    }

    public static String c(Context context, String str) {
        byte[] d = d(context, str);
        if (d == null || d.length == 0) {
            return null;
        }
        return g.a(i.a(d));
    }

    public static PackageInfo b(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(str, 128);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "getPackageInfo NameNotFoundException";
            Log.w("ApkUtil", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "getPackageInfo Exception";
            Log.w("ApkUtil", str2);
            return null;
        }
    }

    public static boolean a(Context context, String str) {
        return b(context, str) != null;
    }

    public static String a(Context context) {
        if (a(context, "com.huawei.hwid")) {
            return "com.huawei.hwid";
        }
        return a(context, "com.huawei.hms") ? "com.huawei.hms" : a(context, "com.huawei.hwid.tv") ? "com.huawei.hwid.tv" : "com.huawei.hwid";
    }
}
