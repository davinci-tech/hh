package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public class ife {

    /* renamed from: a, reason: collision with root package name */
    private static String f13332a = "com.huawei.health";

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("CheckAppUtil", "Health application does not exist");
            return false;
        }
    }

    public static int d(Context context, String str) {
        PackageInfo bwW_ = bwW_(context, str);
        if (bwW_ != null) {
            return bwW_.versionCode;
        }
        return 0;
    }

    private static PackageInfo bwW_(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("CheckAppUtil", "Health application does not exist");
            return null;
        }
    }

    public static String e() {
        return TextUtils.isEmpty(f13332a) ? "com.huawei.health" : f13332a;
    }

    public static void d(String str) {
        f13332a = str;
    }
}
