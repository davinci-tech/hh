package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes2.dex */
public final class jh {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, String> f1216a = new HashMap<>();

    public static void a(Context context, hz hzVar, String str, String str2) {
        if (hzVar == null || TextUtils.isEmpty(hzVar.a())) {
            return;
        }
        String str3 = str + hzVar.a();
        f1216a.put(hzVar.a() + str, str2);
        if (context == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty("d7afbc6a38848a6801f6e449f3ec8e53") || TextUtils.isEmpty(str2)) {
            return;
        }
        String g = ia.g(hl.a(ia.a(str2)));
        SharedPreferences.Editor edit = context.getSharedPreferences("d7afbc6a38848a6801f6e449f3ec8e53", 0).edit();
        edit.putString(str3, g);
        edit.commit();
    }

    public static String a(Context context, hz hzVar, String str) {
        if (hzVar == null || TextUtils.isEmpty(hzVar.a())) {
            return null;
        }
        String str2 = f1216a.get(hzVar.a() + str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String str3 = str + hzVar.a();
        return (context == null || TextUtils.isEmpty(str3)) ? "" : ia.a(hl.b(ia.d(context.getSharedPreferences("d7afbc6a38848a6801f6e449f3ec8e53", 0).getString(str3, ""))));
    }
}
