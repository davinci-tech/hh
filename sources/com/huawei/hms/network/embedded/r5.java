package com.huawei.hms.network.embedded;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.check.ProviderCheckUtil;
import com.huawei.hms.network.conf.api.ConfigAPI;
import com.huawei.hms.network.embedded.k;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class r5 {
    public static final String h = "QoeManager";
    public static final String i = "content://com.huawei.hms.contentprovider";
    public static final String j = "/com.huawei.hms.wireless/qoe";
    public static final int k = 3;
    public static final long l = 500;

    /* renamed from: a, reason: collision with root package name */
    public long f5452a;
    public long b;
    public AtomicInteger c;
    public long d;
    public long e;
    public long f;
    public boolean g;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final r5 f5453a = new r5();
    }

    public t5 a(boolean z) {
        StringBuilder sb;
        long j2;
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("status", 0);
        if (System.currentTimeMillis() - this.d < 500) {
            Logger.d(h, "call in retry suppress time, the suppress time is 500");
            return new t5(hashMap);
        }
        if (this.g || this.c.get() >= 3) {
            Logger.d(h, "call wireless kit failed, this process is suppressing");
            return new t5(hashMap);
        }
        if (!z && System.currentTimeMillis() - this.e < this.b) {
            Logger.d(h, "api call suppress, the suppress time is " + this.b);
            return new t5(hashMap);
        }
        if (z && System.currentTimeMillis() - this.f < this.f5452a) {
            Logger.d(h, "Ha report suppress, the suppress time is " + this.f5452a);
            return new t5(hashMap);
        }
        Bundle a2 = a(ContextHolder.getAppContext(), "Qoe", ContextHolder.getAppContext().getPackageName(), null);
        if (a2 == null || a2.getInt(s5.d) == 0) {
            this.c.addAndGet(1);
            this.d = System.currentTimeMillis();
            Logger.d(h, "update last RetryTime " + this.d);
        } else {
            Logger.d(h, "qoe info not null");
            hashMap = a(hashMap, a2);
            hashMap.put("status", 1);
            long currentTimeMillis = System.currentTimeMillis();
            if (z) {
                this.f = currentTimeMillis;
                sb = new StringBuilder("update last ReportTime ");
                j2 = this.f;
            } else {
                this.e = currentTimeMillis;
                sb = new StringBuilder("update last CallTime ");
                j2 = this.e;
            }
            sb.append(j2);
            Logger.d(h, sb.toString());
            if (this.c.get() != 0) {
                this.c.set(0);
            }
        }
        return new t5(hashMap);
    }

    private void b() {
        Object value = ConfigAPI.getValue(k.i.b);
        Object value2 = ConfigAPI.getValue(k.i.c);
        if (value != null && value2 != null) {
            long stringToLong = StringUtils.stringToLong(String.valueOf(value), 120000L);
            if (stringToLong <= 60000) {
                stringToLong = 60000;
            }
            this.f5452a = stringToLong;
            long stringToLong2 = StringUtils.stringToLong(String.valueOf(value2), 1000L);
            this.b = stringToLong2 > 1000 ? stringToLong2 : 1000L;
        }
        Logger.d(h, "qoeReportSuppressTime is " + this.f5452a + " and apiCallSuppressTime is " + this.b);
    }

    private Map<String, Integer> a(Map<String, Integer> map, Bundle bundle) {
        for (String str : bundle.keySet()) {
            map.put(str, Integer.valueOf(bundle.getInt(str)));
            Logger.d(h, "key : " + str + " value : " + bundle.getInt(str));
        }
        return map;
    }

    public static r5 a() {
        return b.f5453a;
    }

    private Bundle a(Context context, String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Uri parse = Uri.parse(i);
        if (!ProviderCheckUtil.isValid(parse)) {
            this.g = true;
            return null;
        }
        bundle.putString(u3.p, u3.q);
        try {
            Bundle call = context.getContentResolver().call(parse, str, str2, bundle);
            if (call == null) {
                Logger.d(h, "call provider success : but res is null");
            }
            return call;
        } catch (Throwable th) {
            this.g = true;
            Logger.v(h, "call QoeProvider fail, error is " + th.getMessage());
            return null;
        }
    }

    public r5() {
        this.f5452a = 120000L;
        this.b = 1000L;
        this.c = new AtomicInteger(0);
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
        this.g = false;
        b();
    }
}
