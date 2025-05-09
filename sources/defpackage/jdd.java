package defpackage;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class jdd {
    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "setMetricUnit".equalsIgnoreCase(str);
    }
}
