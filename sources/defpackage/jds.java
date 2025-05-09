package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jds {
    public static int c(String str, int i) {
        if (str == null) {
            LogUtil.b("parseIntCheck", "parseIntCheck value is null");
            return -1;
        }
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            LogUtil.b("parseIntCheck", "parseIntCheck value is invalid :", str);
            return -1;
        }
    }

    public static long c(String str) {
        if (str == null) {
            LogUtil.b("parseIntCheck", "parseLongCheck value is null");
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("parseIntCheck", "parseLongCheck value is invalid :", str);
            return -1L;
        }
    }
}
