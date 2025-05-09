package com.huawei.updatesdk.b.b;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final SharedPreferences f10828a;

    public void b(String str, String str2) {
        try {
            SharedPreferences.Editor edit = this.f10828a.edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("SharedPreferencesWrapper", "putString error!!key:" + str, e);
        }
    }

    public void b(String str, long j) {
        try {
            SharedPreferences.Editor edit = this.f10828a.edit();
            edit.putLong(str, j);
            edit.commit();
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("SharedPreferencesWrapper", "putLong error!!key:" + str, e);
        }
    }

    public void a(String str) {
        try {
            if (this.f10828a.contains(str)) {
                SharedPreferences.Editor edit = this.f10828a.edit();
                edit.remove(str);
                edit.commit();
            }
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("SharedPreferencesWrapper", "remove error!!key:" + str);
        }
    }

    public String a(String str, String str2) {
        try {
            return this.f10828a.getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public long a(String str, long j) {
        try {
            return this.f10828a.getLong(str, j);
        } catch (Exception unused) {
            return j;
        }
    }

    public static b a(String str, Context context) {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = context.createDeviceProtectedStorageContext().getSharedPreferences(str, 0);
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("SharedPreferencesWrapper", "getSharedPreference error");
            sharedPreferences = null;
        }
        return new b(sharedPreferences);
    }

    private b(SharedPreferences sharedPreferences) {
        this.f10828a = sharedPreferences;
    }
}
