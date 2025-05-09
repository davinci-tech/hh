package com.huawei.openalliance.ad;

import android.content.Context;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class sc {

    /* renamed from: a, reason: collision with root package name */
    private static List<Integer> f7526a = new ArrayList();
    private static ConcurrentHashMap<Integer, SoftReference<sl>> b = new ConcurrentHashMap<>();

    private static sl b(Context context, int i) {
        if (i == -1) {
            return new rv(context);
        }
        if (i == 7) {
            return new sh(context);
        }
        if (i != 18) {
            if (i == 60) {
                return new sg(context);
            }
            if (i != 1) {
                return i != 2 ? new sf(context, i) : new se(context);
            }
        }
        return new si(context, i);
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        Iterator<Integer> it = f7526a.iterator();
        while (it.hasNext()) {
            a(context, it.next().intValue()).b();
        }
    }

    public static sl a(Context context, int i) {
        SoftReference<sl> softReference = b.get(Integer.valueOf(i));
        sl slVar = softReference != null ? softReference.get() : null;
        if (slVar != null) {
            return slVar;
        }
        sl b2 = b(context, i);
        b.put(Integer.valueOf(i), new SoftReference<>(b2));
        return b2;
    }

    static {
        f7526a.add(1);
        f7526a.add(-1);
        f7526a.add(60);
        f7526a.add(7);
        f7526a.add(3);
        f7526a.add(8);
        f7526a.add(9);
        f7526a.add(12);
        f7526a.add(13);
        f7526a.add(18);
        f7526a.add(2);
    }
}
