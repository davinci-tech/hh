package defpackage;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes8.dex */
public final class ii {
    public static void d(Context context, String str, String str2, String str3) {
        synchronized (ii.class) {
            if (!mq.e(str)) {
                if (!mq.e(str2) && context != null) {
                    try {
                        String c = mm.c(mm.b(), str3);
                        HashMap hashMap = new HashMap();
                        hashMap.put(str2, c);
                        ip.b(context, str, hashMap);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }
}
