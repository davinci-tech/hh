package com.huawei.appgallery.coreservice;

import android.content.Context;
import android.content.SharedPreferences;
import defpackage.afu;

/* loaded from: classes2.dex */
public abstract class i {

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f1870a;

    public void b(String str, String str2) {
        try {
            SharedPreferences.Editor edit = this.f1870a.edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Exception e) {
            afu.e("BaseSharedPreference", "putString error!!key:" + str, e);
        }
    }

    public String a(String str, String str2) {
        try {
            return this.f1870a.getString(str, str2);
        } catch (Exception unused) {
            this.f1870a.edit().remove(str).commit();
            return str2;
        }
    }

    public i(Context context, String str) {
        this.f1870a = context.getSharedPreferences(str, 0);
    }
}
