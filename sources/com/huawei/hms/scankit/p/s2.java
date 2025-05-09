package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
final class s2 implements v2 {
    public int a() {
        return 4;
    }

    @Override // com.huawei.hms.scankit.p.v2
    public void a(y2 y2Var) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!y2Var.i()) {
                break;
            }
            a(y2Var.c(), sb);
            y2Var.f++;
            if (sb.length() >= 4) {
                y2Var.a(a(sb, 0));
                sb.delete(0, 4);
                if (d4.a(y2Var.d(), y2Var.f, a()) != a()) {
                    y2Var.b(0);
                    break;
                }
            }
        }
        sb.append((char) 31);
        a(y2Var, sb);
    }

    private static void a(y2 y2Var, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length == 0) {
                return;
            }
            boolean z = true;
            if (length == 1) {
                y2Var.l();
                int a2 = y2Var.g().a() - y2Var.a();
                int f = y2Var.f();
                if (f > a2) {
                    y2Var.c(y2Var.a() + 1);
                    a2 = y2Var.g().a() - y2Var.a();
                }
                if (f <= a2 && a2 <= 2) {
                    return;
                }
            }
            if (length <= 4) {
                int i = length - 1;
                String a3 = a(charSequence, 0);
                if (!(!y2Var.i()) || i > 2) {
                    z = false;
                }
                if (i <= 2) {
                    y2Var.c(y2Var.a() + i);
                    if (y2Var.g().a() - y2Var.a() >= 3) {
                        y2Var.c(y2Var.a() + a3.length());
                        y2Var.a(a3);
                        return;
                    }
                }
                if (z) {
                    y2Var.k();
                    y2Var.f -= i;
                    return;
                }
                y2Var.a(a3);
                return;
            }
            throw new IllegalStateException("Count must not exceed 4");
        } finally {
            y2Var.b(0);
        }
    }

    private static void a(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c >= '@' && c <= '^') {
            sb.append((char) (c - '@'));
        } else {
            d4.a(c);
        }
    }

    private static String a(CharSequence charSequence, int i) {
        int length = charSequence.length() - i;
        if (length != 0) {
            int charAt = (charSequence.charAt(i) << 18) + ((length >= 2 ? charSequence.charAt(i + 1) : (char) 0) << '\f') + ((length >= 3 ? charSequence.charAt(i + 2) : (char) 0) << 6) + (length >= 4 ? charSequence.charAt(i + 3) : (char) 0);
            char c = (char) ((charAt >> 16) & 255);
            char c2 = (char) ((charAt >> 8) & 255);
            char c3 = (char) (charAt & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append(c);
            if (length >= 2) {
                sb.append(c2);
            }
            if (length >= 3) {
                sb.append(c3);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }

    s2() {
    }
}
