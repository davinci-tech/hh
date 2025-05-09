package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;

/* loaded from: classes6.dex */
public class mef {
    public static <T> String e(T t) {
        return (t == null || t.toString().equals(Constants.NULL)) ? "" : t.toString().trim();
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.b("DBInteractor", "string2Int exception=", e.getMessage());
            return 0;
        }
    }

    public static long e(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            LogUtil.b("DBInteractor", "parseLong e=", e.getMessage());
            return 0L;
        }
    }

    public static double d(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            LogUtil.b("DBInteractor", "parseDouble e=", e.getMessage());
            return 0.0d;
        }
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static String d(String str, String str2, int i) {
        return TextUtils.isEmpty(str2) ? str : mlg.d(str, str2, i);
    }
}
