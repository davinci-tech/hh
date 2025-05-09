package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ksg {
    public static void c(Context context, int i, int i2, String str, String str2, String str3, String str4) {
    }

    public static String a() {
        String a2 = kti.a();
        if (TextUtils.isEmpty(a2) || "NULL".equals(a2)) {
            ksy.b("BiReportUtils", "TransID get imei is null", true);
            return null;
        }
        return SHA.sha256Encrypt(a2 + e("yyyyMMddHHmmssSSS"));
    }

    private static String e(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(new Date(System.currentTimeMillis()));
    }
}
