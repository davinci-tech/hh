package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class cx {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f7670a = new ArrayList();

    public static boolean a(String str) {
        List<String> list = f7670a;
        if (list.isEmpty() || TextUtils.isEmpty(str)) {
            return false;
        }
        return list.contains(str);
    }

    public static void a(Context context) {
        List<String> list = f7670a;
        list.clear();
        list.add(cw.f(context));
        list.add(cw.d(context));
        list.add(cw.g(context));
        list.add(cw.e(context));
    }
}
