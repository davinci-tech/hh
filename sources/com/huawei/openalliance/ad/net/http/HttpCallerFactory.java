package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes5.dex */
public abstract class HttpCallerFactory {
    public static i a() {
        try {
            if (ao.d()) {
                return new d();
            }
            return null;
        } catch (Throwable th) {
            ho.c("HttpCallerFactory", "create http listener exception: " + th.getClass().getSimpleName());
            return null;
        }
    }

    static h a(Context context, int i) {
        h a2;
        if (i == 2) {
            a2 = new k(context);
        } else {
            a2 = i == 1 ? a(context) : null;
        }
        if (a2 != null) {
            return a2;
        }
        ho.b("HttpCallerFactory", "create HttpUrlConnectionCaller");
        return new g(context);
    }

    private static h a(Context context) {
        StringBuilder sb;
        try {
            if (!ao.d()) {
                return null;
            }
            ho.b("HttpCallerFactory", "create OkHttpCaller");
            return new OkHttpCaller(context);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("createOkHttpCaller RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("HttpCallerFactory", sb.toString());
            return null;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("createOkHttpCaller Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("HttpCallerFactory", sb.toString());
            return null;
        }
    }
}
