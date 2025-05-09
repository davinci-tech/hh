package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes5.dex */
public class jrm {
    public static void c(boolean z) {
        SharedPreferenceManager.e("screenshot_permission", "key_is_show_permission_dialog", z);
    }

    public static boolean d() {
        return SharedPreferenceManager.a("screenshot_permission", "key_is_show_permission_dialog", false);
    }

    public static void d(long j) {
        SharedPreferenceManager.e("screenshot_permission", "key_permission_time", j);
    }

    public static boolean c() {
        return System.currentTimeMillis() - SharedPreferenceManager.b("screenshot_permission", "key_permission_time", 0L) < 3600000;
    }

    public static void b() {
        c(true);
        d(System.currentTimeMillis());
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.SCREEN_SHOT_PERMISSION_QUEST");
        BaseApplication.getContext().sendBroadcast(intent);
    }

    public static PermissionUtil.PermissionResult e(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return PermissionUtil.PermissionResult.GRANTED;
        }
        return PermissionUtil.b(context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"});
    }

    public static void e() {
        c(false);
        d(0L);
    }
}
