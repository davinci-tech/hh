package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes4.dex */
public abstract class e0 {
    public abstract String a();

    public abstract String a(String str);

    public abstract String b();

    public abstract String c();

    public abstract int d();

    public i a(Context context) {
        String c = c();
        if (!TextUtils.isEmpty(c)) {
            return new i(d0.UDID, c);
        }
        String a2 = a();
        if (!TextUtils.isEmpty(a2)) {
            return new i(d0.IMEI, a2);
        }
        boolean e = e();
        String b = b();
        return !TextUtils.isEmpty(b) ? e ? new i(d0.SN, b) : new i(d0.UDID, a(b)) : e ? a(d()) : b(d());
    }

    private String f() {
        g1 b = s.c().b();
        if (TextUtils.isEmpty(b.i())) {
            b.e(x0.c());
        }
        return b.i();
    }

    private boolean e() {
        g1 b = s.c().b();
        if (TextUtils.isEmpty(b.l())) {
            b.h(o.a());
        }
        return !TextUtils.isEmpty(b.l());
    }

    private i b(int i) {
        String str;
        if ((i & 4) != 0) {
            str = f();
            if (!TextUtils.isEmpty(str)) {
                return new i(d0.UDID, str);
            }
        } else {
            str = "";
        }
        return new i(d0.EMPTY, str);
    }

    private i a(int i) {
        String str;
        if (i != 0) {
            str = f();
            if (!TextUtils.isEmpty(str)) {
                return new i(d0.UDID, str);
            }
        } else {
            str = "";
        }
        return new i(d0.EMPTY, str);
    }
}
