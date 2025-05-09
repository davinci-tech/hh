package com.huawei.openalliance.ad;

import android.os.Bundle;
import android.os.IBinder;

/* loaded from: classes5.dex */
public class gk {

    /* renamed from: a, reason: collision with root package name */
    private final Bundle f6881a;

    public String toString() {
        try {
            return this.f6881a.toString();
        } catch (Throwable unused) {
            ho.d("SafeBundle", "toString exception.");
            return null;
        }
    }

    public IBinder e(String str) {
        try {
            return this.f6881a.getBinder(str);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getBinder exception ex.");
            return null;
        }
    }

    public String d(String str) {
        try {
            return this.f6881a.getString(str);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getString exception ex.");
            return "";
        }
    }

    public long c(String str) {
        return a(str, 0L);
    }

    public gk b(String str, boolean z) {
        try {
            this.f6881a.putBoolean(str, z);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "putBoolean exception ex.");
        }
        return this;
    }

    public gk b(String str, String str2) {
        try {
            this.f6881a.putString(str, str2);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "putString exception ex.");
        }
        return this;
    }

    public gk b(String str, long j) {
        try {
            this.f6881a.putLong(str, j);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "putLong exception ex.");
        }
        return this;
    }

    public gk b(String str, int i) {
        try {
            this.f6881a.putInt(str, i);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "putInt exception ex.");
        }
        return this;
    }

    public int b(String str) {
        return a(str, 0);
    }

    public boolean a(String str, boolean z) {
        try {
            return this.f6881a.getBoolean(str, z);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getBoolean exception  ex.");
            return z;
        }
    }

    public boolean a(String str) {
        return a(str, false);
    }

    public String a(String str, String str2) {
        try {
            return this.f6881a.getString(str, str2);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getString exception ex.");
            return str2;
        }
    }

    public gk a(String str, IBinder iBinder) {
        try {
            this.f6881a.putBinder(str, iBinder);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "putBundle exception ex.");
        }
        return this;
    }

    public Bundle a() {
        return this.f6881a;
    }

    public long a(String str, long j) {
        try {
            return this.f6881a.getLong(str, j);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getLong exception ex.");
            return j;
        }
    }

    public int a(String str, int i) {
        try {
            return this.f6881a.getInt(str, i);
        } catch (Throwable unused) {
            ho.d("SafeBundle", "getInt exception ex.");
            return i;
        }
    }

    public gk(Bundle bundle) {
        this.f6881a = bundle == null ? new Bundle() : bundle;
    }

    public gk() {
        this(new Bundle());
    }
}
