package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtils;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SystemProperties;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class bky extends CommonUtils {
    private static Boolean e;

    public static boolean c(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || i >= bArr.length * 8) {
            return false;
        }
        if (i < 0) {
            LogUtil.a(CommonUtil.TAG, "isSupport target : ", Integer.valueOf(i));
            return false;
        }
        int i2 = bArr[i / 8];
        int i3 = 1 << (i % 8);
        return (i2 & i3) == i3;
    }

    public static boolean h() {
        return "StoreDemo".equals(BuildConfigProperties.b());
    }

    public static String d(String str) {
        String c = c(str);
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        try {
            return new File(c).getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.a(CommonUtil.TAG, "filterFilePath IOException :", e2.getMessage());
            return null;
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(String.valueOf(str.charAt(i)))) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public static boolean a() {
        return EmuiBuild.d || HarmonyBuild.d;
    }

    public static boolean b() {
        return HarmonyBuild.d;
    }

    public static boolean e() {
        String b = BuildConfigProperties.b();
        return "beta".equals(b) || "BetaPay".equals(b);
    }

    public static boolean i() {
        return "release".equals(BuildConfigProperties.b());
    }

    public static boolean d() {
        return 21 <= EmuiBuild.f13113a;
    }

    public static boolean c() {
        return 25 <= EmuiBuild.f13113a;
    }

    public static boolean g() {
        if (e == null) {
            Boolean valueOf = Boolean.valueOf(Build.VERSION.SDK_INT >= 29 && a() && "3.0.0".compareTo(SystemProperties.b("hw_sc.build.platform.version")) <= 0);
            e = valueOf;
            ReleaseLogUtil.b("DEVMGR_CommonUtil", "sIsSystemSupportFreezing = ", valueOf);
        }
        return e.booleanValue();
    }

    public static boolean f() {
        return BaseApplication.APP_PACKAGE_HEALTH_TV.equals(com.huawei.haf.application.BaseApplication.d());
    }

    public static String b(String str) {
        if (str == null || str.length() < 5) {
            return str;
        }
        if (str.length() > 10) {
            return blt.b(str.substring(0, 9));
        }
        return blt.b(str);
    }

    public static String e(String str) {
        if (str == null || str.length() < 10) {
            return blt.b(str);
        }
        return blt.b(str.substring(0, 9));
    }
}
