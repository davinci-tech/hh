package defpackage;

import java.io.File;

/* loaded from: classes7.dex */
public final class ig {
    public static String e(String str) {
        String str2;
        try {
            str2 = il.a(str);
        } catch (Throwable unused) {
            str2 = "";
        }
        if (!mq.e(str2)) {
            return str2;
        }
        return ij.b(".SystemConfig" + File.separator + str);
    }
}
