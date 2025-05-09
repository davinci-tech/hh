package com.huawei.hms.network.embedded;

import java.net.Proxy;

/* loaded from: classes9.dex */
public final class m9 {
    public static boolean b(t7 t7Var, Proxy.Type type) {
        return !t7Var.g() && type == Proxy.Type.HTTP;
    }

    public static String a(t7 t7Var, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(t7Var.h());
        sb.append(' ');
        boolean b = b(t7Var, type);
        m7 k = t7Var.k();
        if (b) {
            sb.append(k);
        } else {
            sb.append(a(k));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static String a(m7 m7Var) {
        String c = m7Var.c();
        String e = m7Var.e();
        if (e == null) {
            return c;
        }
        return c + '?' + e;
    }
}
