package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import com.huawei.agconnect.common.api.Logger;

/* loaded from: classes8.dex */
public class t<T> implements q<T> {
    private static final String c = "SharePreferenceDataStore";

    /* renamed from: a, reason: collision with root package name */
    final SharedPreferences f1779a;
    final Class<T> b;

    @Override // com.huawei.agconnect.credential.obs.q
    public void c(String str) {
        this.f1779a.edit().remove(str).apply();
    }

    void b(String str, Object obj, Class<?> cls) {
        String a2;
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putString;
        if (Integer.class.equals(cls)) {
            putString = this.f1779a.edit().putInt(str, ((Integer) obj).intValue());
        } else if (Long.class.equals(cls)) {
            putString = this.f1779a.edit().putLong(str, ((Long) obj).longValue());
        } else if (Float.class.equals(cls)) {
            putString = this.f1779a.edit().putFloat(str, ((Float) obj).floatValue());
        } else if (Boolean.class.equals(cls)) {
            putString = this.f1779a.edit().putBoolean(str, ((Boolean) obj).booleanValue());
        } else {
            if (String.class.equals(cls)) {
                edit = this.f1779a.edit();
                a2 = (String) obj;
            } else if (!Parcelable.class.isAssignableFrom(cls)) {
                Logger.w(c, "write not support object type");
                return;
            } else {
                a2 = s.a((Parcelable) obj);
                edit = this.f1779a.edit();
            }
            putString = edit.putString(str, a2);
        }
        putString.apply();
    }

    @Override // com.huawei.agconnect.credential.obs.q
    public void b(String str, T t) {
        b(str, t, this.b);
    }

    @Override // com.huawei.agconnect.credential.obs.q
    public T b(String str) {
        return a(str, null);
    }

    @Override // com.huawei.agconnect.credential.obs.q
    public boolean a(String str) {
        return this.f1779a.contains(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    Object a(String str, T t, Class<?> cls) {
        if (Integer.class.equals(cls)) {
            return Integer.valueOf(this.f1779a.getInt(str, ((Integer) t).intValue()));
        }
        if (Long.class.equals(cls)) {
            return Long.valueOf(this.f1779a.getLong(str, ((Long) t).longValue()));
        }
        if (Float.class.equals(cls)) {
            return Float.valueOf(this.f1779a.getFloat(str, ((Float) t).floatValue()));
        }
        if (Boolean.class.equals(cls)) {
            return Boolean.valueOf(this.f1779a.getBoolean(str, ((Boolean) t).booleanValue()));
        }
        if (String.class.equals(cls)) {
            return this.f1779a.getString(str, (String) t);
        }
        if (Parcelable.class.isAssignableFrom(cls)) {
            return s.a(this.f1779a.getString(str, null), cls);
        }
        Logger.w(c, "read not support object type");
        return t;
    }

    @Override // com.huawei.agconnect.credential.obs.q
    public T a(String str, T t) {
        return (T) a(str, t, this.b);
    }

    public t(Context context, String str, Class<T> cls) {
        this.f1779a = context.getSharedPreferences(str, 0);
        this.b = cls;
    }
}
