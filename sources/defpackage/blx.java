package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class blx {
    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.e("BTNumberConvertUtil", "stringToInt is error");
            return 0;
        }
    }
}
