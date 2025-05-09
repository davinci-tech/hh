package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class im {
    public static b d(Context context) {
        String str = Build.BRAND;
        ja.e("Device", "Brand", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor") || str.equalsIgnoreCase("华为")) {
            return new in();
        }
        if (str.equalsIgnoreCase("xiaomi") || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("meitu") || str.equalsIgnoreCase("小米") || str.equalsIgnoreCase("blackshark")) {
            return new iz();
        }
        if (str.equalsIgnoreCase("vivo")) {
            return new ir();
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("oneplus") || str.equalsIgnoreCase("realme")) {
            return new iu();
        }
        if (str.equalsIgnoreCase("lenovo") || str.equalsIgnoreCase("zuk")) {
            return new io();
        }
        if (str.equalsIgnoreCase("nubia")) {
            return new is();
        }
        if (str.equalsIgnoreCase("samsung")) {
            return new iq();
        }
        if (a()) {
            return new in();
        }
        if (str.equalsIgnoreCase("meizu") || str.equalsIgnoreCase("mblu")) {
            return new it();
        }
        return null;
    }

    public static boolean a() {
        return (TextUtils.isEmpty(e("ro.build.version.emui")) && TextUtils.isEmpty(e("hw_sc.build.platform.version"))) ? false : true;
    }

    public static String e(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
        } catch (Throwable unused) {
            return "";
        }
    }
}
