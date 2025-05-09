package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes5.dex */
public class en implements fp {
    private static fp d;
    private static final byte[] e = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private Context f6844a;
    private final SharedPreferences b;
    private final SharedPreferences c;
    private final byte[] f = new byte[0];

    @Override // com.huawei.openalliance.ad.fp
    public void b() {
        synchronized (this.f) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putLong("lastSyncTime", com.huawei.openalliance.ad.utils.ao.c()).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fp
    public long a() {
        synchronized (this.f) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong("lastSyncTime", 0L);
        }
    }

    private static fp b(Context context) {
        fp fpVar;
        synchronized (e) {
            if (d == null) {
                d = new en(context);
            }
            fpVar = d;
        }
        return fpVar;
    }

    public static fp a(Context context) {
        return b(context);
    }

    private en(Context context) {
        SharedPreferences sharedPreferences;
        this.f6844a = context.getApplicationContext();
        try {
            sharedPreferences = context.getSharedPreferences("AppDataSharedPreferences", 0);
            try {
                SharedPreferences sharedPreferences2 = context.getSharedPreferences("AppDataSharedPreferences_sec", 0);
                this.b = sharedPreferences;
                this.c = sharedPreferences2;
            } catch (Throwable th) {
                th = th;
                try {
                    ho.c("AppDataSpHandler", "get SharedPreference exception: %s", th.getClass().getSimpleName());
                } finally {
                    this.b = sharedPreferences;
                    this.c = null;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            sharedPreferences = null;
        }
    }
}
