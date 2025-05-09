package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* loaded from: classes3.dex */
public class agc {
    private static final Object c = new Object();
    private static volatile agc e;

    /* renamed from: a, reason: collision with root package name */
    private long f196a;
    private String b;
    private SharedPreferences d;

    public long j() {
        return this.f196a;
    }

    public String b() {
        return this.b;
    }

    public long e() {
        try {
            return this.d.getLong("providerUpdateTime", 0L);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public String d() {
        return this.d.getString("homeCountryInProvider", null);
    }

    public void a(String str) {
        this.b = str;
        this.f196a = str != null ? System.currentTimeMillis() : 0L;
    }

    public long c() {
        try {
            return this.d.getLong("effectiveduration", 47839000L);
        } catch (Exception unused) {
            return 47839000L;
        }
    }

    public void b(String str) {
        try {
            this.d.edit().putString("homeCountryInProvider", str).commit();
            this.d.edit().putLong("providerUpdateTime", System.currentTimeMillis()).commit();
        } catch (Exception unused) {
            Log.e("HomeCountrySharedPreference", "setHomeCountryInProvider, putString error");
        }
    }

    public void d(long j) {
        try {
            this.d.edit().putLong("effectiveduration", j).commit();
        } catch (Exception unused) {
            Log.e("HomeCountrySharedPreference", "setEffectiveDuration, putLong error");
        }
    }

    public void a() {
        try {
            SharedPreferences.Editor edit = this.d.edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
            Log.e("HomeCountrySharedPreference", "clear error");
        }
    }

    public static agc b(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new agc(context);
                }
            }
        }
        return e;
    }

    private agc(Context context) {
        try {
            this.d = context.createDeviceProtectedStorageContext().getSharedPreferences("MarketHomeCountry.DataStorage", 0);
        } catch (Exception unused) {
            Log.e("HomeCountrySharedPreference", "getSharedPreference error");
        }
    }
}
