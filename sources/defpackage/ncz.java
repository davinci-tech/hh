package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/* loaded from: classes6.dex */
public class ncz {
    private static int c;
    private static String d;

    public static boolean b(Context context) {
        PackageInfo ctg_ = ctg_(context);
        if (ctg_ == null) {
            return true;
        }
        String str = ctg_.versionName;
        int i = ctg_.versionCode;
        String a2 = ndg.a(context, "hw_skinner", "LAST_VERSION_NAME", null);
        int b = ndg.b(context, "hw_skinner", "LAST_VERSION_CODE", -1);
        if (str.equals(a2) && i == b) {
            return false;
        }
        d = str;
        c = i;
        return true;
    }

    public static void e(Context context) {
        if (TextUtils.isEmpty(d) || c == 0) {
            return;
        }
        ndg.e(context, "hw_skinner", "LAST_VERSION_NAME", d);
        ndg.d(context, "hw_skinner", "LAST_VERSION_CODE", c);
    }

    private static PackageInfo ctg_(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception unused) {
            ncx.e("Get package info error.");
            return null;
        }
    }
}
