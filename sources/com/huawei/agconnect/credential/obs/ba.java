package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ba {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, az> f1763a = new HashMap();

    static ay a(Context context, bb bbVar) {
        String str;
        synchronized (ba.class) {
            if (Looper.getMainLooper() != Looper.myLooper()) {
                str = "init must be called in the main thread";
            } else if (context == null) {
                str = "context cannot be null to init HiAnalyticsInstanceEx.";
            } else {
                if (!TextUtils.isEmpty(bbVar.f1764a) && bbVar.f1764a.length() <= 256) {
                    if (!TextUtils.isEmpty(bbVar.b) && bbVar.b.length() <= 256) {
                        Map<String, az> map = f1763a;
                        az azVar = map.get(bbVar.f1764a);
                        if (azVar == null) {
                            az azVar2 = new az(context, bbVar);
                            map.put(bbVar.f1764a, azVar2);
                            return azVar2;
                        }
                        if (bbVar.c.equals(azVar.f1760a.c)) {
                            return azVar;
                        }
                        azVar.a(context, bbVar);
                        return azVar;
                    }
                    str = "httpheader check failed";
                }
                str = "serviceTag check failed";
            }
            Log.e("HAInstanceManager", str);
            return null;
        }
    }
}
