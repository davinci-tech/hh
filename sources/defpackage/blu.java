package defpackage;

import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class blu {
    public static int b(String str, int i) {
        if (str == null) {
            LogUtil.e("parseIntCheck", "parseIntCheck value is null");
            return -1;
        }
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            LogUtil.e("parseIntCheck", "parseIntCheck value is invalid :", str);
            return -1;
        }
    }
}
