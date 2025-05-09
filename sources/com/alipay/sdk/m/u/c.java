package com.alipay.sdk.m.u;

import android.content.Context;
import android.net.NetworkInfo;
import defpackage.me;

/* loaded from: classes7.dex */
public class c {
    public static c e;

    public c(Context context) {
    }

    public static c b(Context context) {
        if (e == null) {
            e = new c(context);
        }
        return e;
    }

    public static g c(Context context) {
        try {
            NetworkInfo bi_ = me.bi_(null, context);
            return (bi_ == null || bi_.getType() != 0) ? (bi_ == null || bi_.getType() != 1) ? g.NONE : g.WIFI : g.a(bi_.getSubtype());
        } catch (Exception unused) {
            return g.NONE;
        }
    }

    public static String e(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String e() {
        return "00:00:00:00:00:00";
    }

    public String c() {
        return "000000000000000";
    }

    public String b() {
        return "000000000000000";
    }
}
