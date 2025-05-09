package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: classes9.dex */
public final class o9 {
    public static final int d = 307;
    public static final int e = 308;
    public static final int f = 100;

    /* renamed from: a, reason: collision with root package name */
    public final r7 f5401a;
    public final int b;
    public final String c;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5401a == r7.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.b);
        if (this.c != null) {
            sb.append(' ');
            sb.append(this.c);
        }
        return sb.toString();
    }

    public static o9 a(String str) throws IOException {
        r7 r7Var;
        int i;
        String str2;
        if (str.startsWith("HTTP/1.")) {
            i = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                r7Var = r7.HTTP_1_0;
            } else {
                if (charAt != 1) {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                r7Var = r7.HTTP_1_1;
            }
        } else {
            if (!str.startsWith("ICY ")) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            r7Var = r7.HTTP_1_0;
            i = 4;
        }
        int i2 = i + 3;
        if (str.length() < i2) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int parseInt = Integer.parseInt(str.substring(i, i2));
            if (str.length() <= i2) {
                str2 = "";
            } else {
                if (str.charAt(i2) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                str2 = str.substring(i + 4);
            }
            return new o9(r7Var, parseInt, str2);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public static o9 a(v7 v7Var) {
        return new o9(v7Var.F(), v7Var.w(), v7Var.B());
    }

    public o9(r7 r7Var, int i, String str) {
        this.f5401a = r7Var;
        this.b = i;
        this.c = str;
    }
}
