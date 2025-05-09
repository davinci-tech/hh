package com.huawei.agconnect.common.api;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.agconnect.config.impl.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes2.dex */
public class PackageUtils {
    private static final String TAG = "PackageUtils";

    public static boolean isSystemUpdateApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 128) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isSystemApplication(android.content.Context r2, java.lang.String r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L41
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto La
            goto L41
        La:
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            if (r2 != 0) goto L11
            return r0
        L11:
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r3, r0)     // Catch: java.lang.Exception -> L16 android.content.pm.PackageManager.NameNotFoundException -> L1e
            goto L32
        L16:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "getApplicationInfo Exception: "
            r2.<init>(r1)
            goto L25
        L1e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "not found: "
            r2.<init>(r1)
        L25:
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "PackageUtils"
            com.huawei.agconnect.common.api.Logger.e(r3, r2)
            r2 = 0
        L32:
            if (r2 == 0) goto L41
            boolean r3 = isSystemUpdateApp(r2)
            if (r3 != 0) goto L40
            boolean r2 = isSystemApp(r2)
            if (r2 == 0) goto L41
        L40:
            r0 = 1
        L41:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.common.api.PackageUtils.isSystemApplication(android.content.Context, java.lang.String):boolean");
    }

    public static boolean isSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 1) != 0;
    }

    public static String getInstalledAppSign256(Context context, String str) {
        PackageManager packageManager;
        String str2;
        if (context != null && !TextUtils.isEmpty(str) && (packageManager = context.getPackageManager()) != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
                if (packageInfo != null) {
                    byte[] byteArray = packageInfo.signatures[0].toByteArray();
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    if (messageDigest != null) {
                        return Hex.encodeHexString(messageDigest.digest(byteArray));
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
                str2 = "not found: " + str;
                Logger.e(TAG, str2);
                return null;
            } catch (RuntimeException unused2) {
                str2 = "get packageInfo runtimeException";
                Logger.e(TAG, str2);
                return null;
            } catch (NoSuchAlgorithmException unused3) {
                str2 = "not found sha256 digest";
                Logger.e(TAG, str2);
                return null;
            }
        }
        return null;
    }
}
