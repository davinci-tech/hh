package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

/* loaded from: classes5.dex */
public class dg {

    /* renamed from: a, reason: collision with root package name */
    private static String f7697a;

    public static Pair<String, Boolean> a(Context context) {
        String a2 = bw.a(context);
        f7697a = a2;
        if (!TextUtils.isEmpty(a2)) {
            return new Pair<>(f7697a, false);
        }
        String a3 = bk.a(context);
        f7697a = a3;
        if (!TextUtils.isEmpty(a3)) {
            return new Pair<>(f7697a, false);
        }
        String a4 = dm.a(context);
        f7697a = a4;
        return !TextUtils.isEmpty(a4) ? new Pair<>(f7697a, false) : aq.a(context, true);
    }
}
