package com.huawei.hianalytics.visual;

import android.content.SharedPreferences;
import com.huawei.hianalytics.core.log.HiLog;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public class c0<T> {

    /* renamed from: a, reason: collision with root package name */
    public final Future<SharedPreferences> f3912a;
    public final String b;
    public final a<T> c;
    public T d;

    public interface a<T> {
        T a();

        T a(String str);

        String a(T t);
    }

    public c0(Future<SharedPreferences> future, String str, a<T> aVar) {
        this.f3912a = future;
        this.b = str;
        this.c = aVar;
    }

    public T a() {
        T t = this.d;
        if (t != null) {
            return t;
        }
        synchronized (this.f3912a) {
            String str = null;
            try {
                SharedPreferences sharedPreferences = this.f3912a.get();
                if (sharedPreferences != null) {
                    str = sharedPreferences.getString(this.b, null);
                }
            } catch (Exception unused) {
                HiLog.w("PreferencesHolder", "fail to get sp file");
            }
            if (str != null) {
                return this.c.a(str);
            }
            T a2 = this.c.a();
            this.d = a2;
            a(a2);
            return this.d;
        }
    }

    public void a(T t) {
        SharedPreferences sharedPreferences;
        if (b.a().b()) {
            return;
        }
        this.d = t;
        synchronized (this.f3912a) {
            try {
                sharedPreferences = this.f3912a.get();
            } catch (Exception unused) {
                HiLog.w("PreferencesHolder", "fail to get sp file");
                sharedPreferences = null;
            }
            if (sharedPreferences == null) {
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (this.d == null) {
                this.d = this.c.a();
            }
            edit.putString(this.b, this.c.a((a<T>) this.d));
            edit.apply();
        }
    }
}
