package com.huawei.hianalytics.framework;

import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public IStorageHandler f3859a;
    public final Map<String, Long> b = new ConcurrentHashMap();
    public final Map<String, Long> c = new ConcurrentHashMap();
    public final Map<String, Integer> d = new ConcurrentHashMap();
    public final Map<String, Integer> e = new ConcurrentHashMap();

    public void a(String str) {
        Integer num = this.d.get(str);
        int intValue = (num != null ? num.intValue() : 0) + 1;
        this.d.put(str, Integer.valueOf(intValue));
        this.c.put(str, Long.valueOf((intValue <= 0 || intValue > 4) ? 1800000L : intValue * 300000));
    }

    public void b(String str) {
        Integer num = this.d.get(str);
        if (num == null || num.intValue() == 0) {
            return;
        }
        this.c.put(str, Long.valueOf(OpAnalyticsConstants.H5_LOADING_DELAY));
        this.d.put(str, 0);
    }
}
