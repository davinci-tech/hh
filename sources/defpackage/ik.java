package defpackage;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class ik {
    public static void c(Context context, String str, String str2, String str3) {
        synchronized (ik.class) {
            if (mq.e(str) || mq.e(str2) || context == null) {
                return;
            }
            try {
                String c = mm.c(mm.b(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, c);
                ip.b(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    public static String c(Context context, String str, String str2) {
        String e;
        synchronized (ik.class) {
            String str3 = null;
            if (context != null) {
                if (!mq.e(str) && !mq.e(str2)) {
                    try {
                        e = ip.e(context, str, str2, "");
                    } catch (Throwable unused) {
                    }
                    if (mq.e(e)) {
                        return null;
                    }
                    str3 = mm.a(mm.b(), e);
                    return str3;
                }
            }
            return null;
        }
    }
}
