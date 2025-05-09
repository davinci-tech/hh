package com.huawei.phoneservice.faq.base.util;

import android.util.LruCache;
import android.view.View;
import com.huawei.hms.framework.common.Logger;

/* loaded from: classes5.dex */
public class s {
    private static final LruCache<Integer, Long> e = new LruCache<>(10);

    public static boolean cdx_(View view) {
        boolean z = true;
        if (view != null) {
            Integer valueOf = Integer.valueOf(view.getId());
            LruCache<Integer, Long> lruCache = e;
            Long l = lruCache.get(valueOf);
            long nanoTime = System.nanoTime() / 1000000;
            Logger.d("NoDoubleClickUtil", "isDoubleClick view:%s, lastClickTime:%s,  currentTime:%s", valueOf, l, Long.valueOf(nanoTime));
            if (l != null) {
                long longValue = nanoTime - l.longValue();
                if (longValue > 800) {
                    lruCache.put(valueOf, Long.valueOf(nanoTime));
                    z = false;
                }
                Logger.d("NoDoubleClickUtil", "isDoubleClick gap:%s, BREAK_TIME:%s", Long.valueOf(longValue), 800);
            } else {
                lruCache.put(valueOf, Long.valueOf(nanoTime));
                z = false;
            }
        }
        Logger.d("NoDoubleClickUtil", "isDoubleClick result:%s", Boolean.valueOf(z));
        return z;
    }
}
