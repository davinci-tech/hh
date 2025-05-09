package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.huawei.agconnect.common.api.RandomWrapper;
import com.huawei.agconnect.common.appinfo.SafeAppInfo;
import java.util.UUID;

/* loaded from: classes2.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1765a = "aaid";
    public static final String b = "creationTime";

    private static long d(String str) {
        return (((((Long.decode("0x" + str.substring(0, 8)).longValue() << 16) | Long.decode("0x" + str.substring(8, 16)).longValue()) << 16) | Long.decode("0x" + str.substring(16, 24)).longValue()) << 16) | Long.decode("0x" + str.substring(24, 32)).longValue();
    }

    private static UUID c(String str) {
        return new UUID(d(str.substring(0, 32)), d(str.substring(32, 64)));
    }

    private static String b(String str) {
        String a2 = f.a(str, "SHA-256");
        return ((TextUtils.isEmpty(a2) || a2.length() != 64) ? UUID.randomUUID() : c(a2)).toString();
    }

    static String a(String str) {
        String b2;
        synchronized (c.class) {
            b2 = b(str + a(32));
        }
        return b2;
    }

    public static String a(e eVar) {
        String str;
        synchronized (c.class) {
            if (eVar.e(f1765a)) {
                str = eVar.b(f1765a);
            } else {
                String uuid = UUID.randomUUID().toString();
                eVar.a(f1765a, uuid);
                eVar.a(b, Long.valueOf(System.currentTimeMillis()));
                str = uuid;
            }
        }
        return str;
    }

    static String a(Context context) {
        PackageInfo safeGetPackageInfo;
        if (context == null || (safeGetPackageInfo = SafeAppInfo.safeGetPackageInfo(context.getPackageManager(), context.getPackageName(), 64)) == null) {
            return null;
        }
        return safeGetPackageInfo.signatures[0].toCharsString();
    }

    private static String a(int i) {
        return f.b(RandomWrapper.generateSecureRandom(i));
    }
}
