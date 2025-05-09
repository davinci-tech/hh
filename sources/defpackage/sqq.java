package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class sqq {
    private int b = 0;

    public static sqq b() {
        return c.b;
    }

    static final class c {
        private static final sqq b = new sqq();
    }

    public static String e() {
        PackageInfo ekI_ = ekI_();
        return ekI_ != null ? ekI_.packageName : "";
    }

    public static String c() {
        return Build.MODEL;
    }

    public static PackageInfo ekI_() {
        try {
            return arx.b().getPackageManager().getPackageInfo(arx.b().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("AppManager", e.getMessage());
            return null;
        }
    }

    public static boolean d() {
        return e() != null && e().equals("com.huawei.bone");
    }

    public boolean f() {
        int i = this.b;
        if (i != 0) {
            return i != 2;
        }
        if (ContextCompat.checkSelfPermission(arx.b(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            this.b = 2;
            return false;
        }
        this.b = 1;
        return true;
    }
}
