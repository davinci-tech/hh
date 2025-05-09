package com.huawei.openalliance.ad;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class dh {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, dk> f6699a = new HashMap();

    public static void b() {
        synchronized (dh.class) {
            Iterator<String> it = f6699a.keySet().iterator();
            while (it.hasNext()) {
                dk dkVar = f6699a.get(it.next());
                if (dkVar != null) {
                    dkVar.d();
                }
            }
        }
    }

    public static void a() {
        synchronized (dh.class) {
            Iterator<String> it = f6699a.keySet().iterator();
            while (it.hasNext()) {
                dk dkVar = f6699a.get(it.next());
                if (dkVar != null) {
                    dkVar.a();
                }
            }
            f6699a.clear();
        }
    }

    public static dk a(Context context, String str) {
        dk dkVar;
        synchronized (dh.class) {
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                str = "normal";
            }
            dkVar = f6699a.get(str);
            if (dkVar == null) {
                dkVar = new dk(context, str);
            }
            f6699a.put(str, dkVar);
        }
        return dkVar;
    }
}
