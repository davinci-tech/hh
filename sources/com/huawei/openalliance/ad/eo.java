package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;

/* loaded from: classes5.dex */
public class eo implements fq {
    private static fq b;
    private static final byte[] c = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private final SharedPreferences f6845a;
    private final byte[] d = new byte[0];
    private List<String> e;

    @Override // com.huawei.openalliance.ad.fq
    public List<String> b() {
        synchronized (this.d) {
            SharedPreferences sharedPreferences = this.f6845a;
            if (sharedPreferences == null) {
                return null;
            }
            List<String> list = this.e;
            if (list != null) {
                return list;
            }
            String string = sharedPreferences.getString("aud_id", "");
            if (com.huawei.openalliance.ad.utils.cz.b(string)) {
                return null;
            }
            List<String> list2 = (List) com.huawei.openalliance.ad.utils.be.b(string, List.class, String.class);
            this.e = list2;
            return list2;
        }
    }

    @Override // com.huawei.openalliance.ad.fq
    public void a(String str) {
        synchronized (this.d) {
            if (this.f6845a == null) {
                return;
            }
            this.e = (List) com.huawei.openalliance.ad.utils.be.b(str, List.class, String.class);
            this.f6845a.edit().putString("aud_id", str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fq
    public void a(long j) {
        synchronized (this.d) {
            this.f6845a.edit().putLong("last_query_audid_time", j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fq
    public long a() {
        long j;
        synchronized (this.d) {
            j = this.f6845a.getLong("last_query_audid_time", 0L);
        }
        return j;
    }

    private static fq b(Context context) {
        fq fqVar;
        synchronized (c) {
            if (b == null) {
                b = new eo(context);
            }
            fqVar = b;
        }
        return fqVar;
    }

    public static fq a(Context context) {
        return b(context);
    }

    private eo(Context context) {
        try {
            this.f6845a = context.getApplicationContext().getSharedPreferences("hiad_audids", 0);
        } catch (Throwable th) {
            try {
                ho.c("AudIdSpHandler", "get SharedPreference exception: %s", th.getClass().getSimpleName());
            } finally {
                this.f6845a = null;
            }
        }
    }
}
