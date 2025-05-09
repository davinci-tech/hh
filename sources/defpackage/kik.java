package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class kik {
    public static String d() {
        return Build.DISPLAY;
    }

    public static void a(boolean z) {
        SharedPreferenceManager.e("1001", "key_remote_camera_capability", z);
    }

    public static boolean b() {
        return SharedPreferenceManager.a("1001", "key_remote_camera_capability", true);
    }

    private static String a() {
        String d = d("key_phone_system_version_name");
        LogUtil.a("RemoteCameraUtils", "getSystemVersionName: ", d);
        return d;
    }

    public static void b(String str) {
        LogUtil.a("RemoteCameraUtils", "setSystemVersionName: ", str);
        d("key_phone_system_version_name", str);
    }

    private static String d(String str) {
        return SharedPreferenceManager.b(BaseApplication.e(), "1001", str);
    }

    private static int d(String str, String str2) {
        return SharedPreferenceManager.e(BaseApplication.e(), "1001", str, str2, (StorageParams) null);
    }

    public static boolean e() {
        String a2 = a();
        return TextUtils.isEmpty(a2) || TextUtils.isEmpty(d()) || !d().equals(a2);
    }
}
