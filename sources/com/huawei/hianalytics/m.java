package com.huawei.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DaoManager;
import com.huawei.hianalytics.util.HwSfpPolicyUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public static m f3884a;

    /* renamed from: a, reason: collision with other field name */
    public long f53a;

    /* renamed from: a, reason: collision with other field name */
    public String f54a = null;

    /* renamed from: a, reason: collision with other field name */
    public final ConcurrentHashMap<String, AtomicLong> f55a = new ConcurrentHashMap<>();
    public final ConcurrentHashMap<String, AtomicLong> b = new ConcurrentHashMap<>();

    public final AtomicLong a(String str, String str2) {
        ConcurrentHashMap<String, AtomicLong> concurrentHashMap;
        if (this.f54a == null) {
            this.f54a = j.b(EnvUtils.getAppContext());
        }
        AtomicLong atomicLong = new AtomicLong(DaoManager.getInstance().countBySubCount(str, str2, this.f54a));
        if (!"oper".equals(str2)) {
            if ("maint".equals(str2)) {
                concurrentHashMap = this.b;
            }
            return atomicLong;
        }
        concurrentHashMap = this.f55a;
        concurrentHashMap.put(str, atomicLong);
        return atomicLong;
    }

    public static m a(Context context) {
        if (f3884a == null) {
            synchronized (m.class) {
                if (f3884a == null) {
                    f3884a = new m(context, "haformal_event.db", 6);
                }
            }
        }
        return f3884a;
    }

    public final AtomicLong b(String str, String str2) {
        ConcurrentHashMap<String, AtomicLong> concurrentHashMap;
        if (System.currentTimeMillis() - this.f53a > 3600000) {
            this.f55a.clear();
            this.b.clear();
            this.f53a = System.currentTimeMillis();
        }
        if ("oper".equals(str2)) {
            concurrentHashMap = this.f55a;
        } else {
            if (!"maint".equals(str2)) {
                HiLog.w("DBController", "unknown event type: " + str2);
                return null;
            }
            concurrentHashMap = this.b;
        }
        return concurrentHashMap.get(str);
    }

    /* renamed from: a, reason: collision with other method in class */
    public final void m575a(String str, String str2) {
        ConcurrentHashMap<String, AtomicLong> concurrentHashMap;
        if (TextUtils.isEmpty(str)) {
            this.f55a.clear();
            this.b.clear();
        } else {
            if (TextUtils.isEmpty(str2)) {
                this.f55a.remove(str);
                this.b.remove(str);
                return;
            }
            if ("oper".equals(str2)) {
                concurrentHashMap = this.f55a;
            } else if (!"maint".equals(str2)) {
                return;
            } else {
                concurrentHashMap = this.b;
            }
            concurrentHashMap.remove(str);
        }
    }

    public m(Context context, String str, int i) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("context is null, or dbName is empty");
        }
        try {
            DaoManager.getInstance().init(o.a(context, str, null, i).getWritableDatabase());
            String str2 = context.isDeviceProtectedStorage() ? "S1" : "S2";
            HiLog.d("DBController", "db label value: ".concat(str2));
            HwSfpPolicyUtils.a(context, str, str2, 0);
            this.f53a = System.currentTimeMillis();
        } catch (Exception unused) {
            HiLog.w("DBController", "Exception");
            throw new IllegalStateException("database init error");
        }
    }
}
