package com.careforeyou.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class PrefsUtil {

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences.Editor f1662a;
    private SharedPreferences e;

    public PrefsUtil(Context context) {
        this.e = null;
        this.f1662a = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("chipsea", 0);
        this.e = sharedPreferences;
        this.f1662a = sharedPreferences.edit();
    }
}
