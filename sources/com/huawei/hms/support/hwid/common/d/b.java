package com.huawei.hms.support.hwid.common.d;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hms.support.hwid.common.e.g;
import com.huawei.hms.support.hwid.common.e.i;
import com.huawei.hms.support.hwid.common.e.j;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private volatile SharedPreferences f6001a;
    private Context b;
    private String c;

    public b(Context context, String str) {
        this.c = "";
        this.b = context.getApplicationContext();
        this.c = str;
        b();
    }

    private boolean b() {
        if (i.a(this.b)) {
            g.a("HwIdCeSharedPreferences", "Phone Still In Lock Mode", true);
            return false;
        }
        this.f6001a = this.b.getSharedPreferences(this.c, 0);
        boolean z = this.f6001a == null;
        g.a("HwIdCeSharedPreferences", "createSharedPreferences: " + z, true);
        return z;
    }

    private boolean c() {
        if (this.b == null) {
            g.c("HwIdCeSharedPreferences", "mContext is null, can not use SharedPreferences.", true);
            return false;
        }
        if (this.f6001a == null) {
            g.c("HwIdCeSharedPreferences", "mSharedPreferences is null, need init.", true);
            synchronized (b.class) {
                if (this.f6001a == null) {
                    return b();
                }
            }
        }
        return true;
    }

    public String a(String str, String str2) {
        g.a("HwIdCeSharedPreferences", "getStringByRandomEncrypter, key:" + str, false);
        return !c() ? str2 : this.f6001a.getString(str, str2);
    }

    public Map<String, ?> a() {
        g.a("HwIdCeSharedPreferences", "getAll", true);
        if (c()) {
            return this.f6001a.getAll();
        }
        return null;
    }

    public boolean a(HashMap<String, Object> hashMap) {
        SharedPreferences.Editor edit;
        g.a("HwIdCeSharedPreferences", "saveMap", true);
        if (hashMap == null || hashMap.size() == 0) {
            g.c("HwIdCeSharedPreferences", "map is empty", true);
            return false;
        }
        if (!c() || (edit = this.f6001a.edit()) == null) {
            return false;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                edit.putString(key, String.valueOf(value));
            } else if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
                edit.putInt(key, j.a(String.valueOf(value)));
            } else if (value instanceof Long) {
                edit.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                edit.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Double) {
                edit.putFloat(key, (float) ((Double) value).doubleValue());
            } else if (value instanceof Boolean) {
                edit.putBoolean(key, ((Boolean) value).booleanValue());
            }
        }
        return edit.commit();
    }

    public String b(String str, String str2) {
        g.a("HwIdCeSharedPreferences", "getString, key:" + str, false);
        return !c() ? str2 : this.f6001a.getString(str, str2);
    }

    public boolean c(String str, String str2) {
        SharedPreferences.Editor edit;
        g.a("HwIdCeSharedPreferences", "setString, key:" + str, false);
        if (!c() || (edit = this.f6001a.edit()) == null) {
            return false;
        }
        return edit.putString(str, str2).commit();
    }
}
