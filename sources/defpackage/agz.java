package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* loaded from: classes3.dex */
public class agz {
    private static String b = "";
    private static final Object e = new Object();

    public static String b(Context context) {
        String str;
        String str2;
        synchronized (e) {
            if (TextUtils.isEmpty(b)) {
                String str3 = ("InstallSDK##11.5.1.300##" + a()) + "##" + agq.d();
                try {
                    String packageName = context.getPackageName();
                    str3 = str3 + "##" + packageName;
                    str2 = str3 + "##" + context.getPackageManager().getPackageInfo(packageName, 0).versionName;
                } catch (PackageManager.NameNotFoundException e2) {
                    e = e2;
                    str = "get package error";
                    agr.a("UserAgentUtils", str, e);
                    str2 = str3;
                    b = str2;
                    return b;
                } catch (Exception e3) {
                    e = e3;
                    str = "get package Exception";
                    agr.a("UserAgentUtils", str, e);
                    str2 = str3;
                    b = str2;
                    return b;
                }
                b = str2;
            }
        }
        return b;
    }

    private static String a() {
        String e2 = agq.e();
        return TextUtils.isEmpty(e2) ? "other" : e2;
    }
}
