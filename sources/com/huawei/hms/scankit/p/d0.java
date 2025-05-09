package com.huawei.hms.scankit.p;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;

/* loaded from: classes4.dex */
class d0 implements v2 {
    static void b(y2 y2Var, StringBuilder sb) {
        y2Var.a(a(sb, 0));
        sb.delete(0, 3);
    }

    public int a() {
        return 1;
    }

    @Override // com.huawei.hms.scankit.p.v2
    public void a(y2 y2Var) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!y2Var.i()) {
                break;
            }
            char c = y2Var.c();
            y2Var.f++;
            int a2 = a(c, sb);
            int a3 = y2Var.a() + ((sb.length() / 3) * 2);
            y2Var.c(a3);
            int a4 = y2Var.g().a() - a3;
            if (!y2Var.i()) {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (a4 < 2 || a4 > 2)) {
                    a2 = a(y2Var, sb, sb2, a2);
                }
                while (sb.length() % 3 == 1 && ((a2 <= 3 && a4 != 1) || a2 > 3)) {
                    a2 = a(y2Var, sb, sb2, a2);
                }
            } else if (sb.length() % 3 == 0 && d4.a(y2Var.d(), y2Var.f, a()) != a()) {
                y2Var.b(0);
                break;
            }
        }
        a(y2Var, sb);
    }

    private int a(y2 y2Var, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        y2Var.f--;
        int a2 = a(y2Var.c(), sb2);
        y2Var.k();
        return a2;
    }

    void a(y2 y2Var, StringBuilder sb) {
        int length = sb.length() / 3;
        int length2 = sb.length() % 3;
        int a2 = y2Var.a() + (length * 2);
        y2Var.c(a2);
        int a3 = y2Var.g().a() - a2;
        if (length2 == 2) {
            sb.append((char) 0);
            while (sb.length() >= 3) {
                b(y2Var, sb);
            }
            if (y2Var.i()) {
                y2Var.a((char) 254);
            }
        } else if (a3 == 1 && length2 == 1) {
            while (sb.length() >= 3) {
                b(y2Var, sb);
            }
            if (y2Var.i()) {
                y2Var.a((char) 254);
            }
            y2Var.f--;
        } else if (length2 == 0) {
            while (sb.length() >= 3) {
                b(y2Var, sb);
            }
            if (a3 > 0 || y2Var.i()) {
                y2Var.a((char) 254);
            }
        } else {
            try {
                throw new IllegalStateException("Unexpected case. Please report!");
            } catch (Exception unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "Exception");
            }
        }
        y2Var.b(0);
    }

    int a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        }
        if (c >= '0' && c <= '9') {
            sb.append((char) (c - ','));
            return 1;
        }
        if (c >= 'A' && c <= 'Z') {
            sb.append((char) (c - '3'));
            return 1;
        }
        if (c < ' ') {
            sb.append((char) 0);
            sb.append(c);
            return 2;
        }
        if (c >= '!' && c <= '/') {
            sb.append((char) 1);
            sb.append((char) (c - '!'));
            return 2;
        }
        if (c >= ':' && c <= '@') {
            sb.append((char) 1);
            sb.append((char) (c - '+'));
            return 2;
        }
        if (c >= '[' && c <= '_') {
            sb.append((char) 1);
            sb.append((char) (c - 'E'));
            return 2;
        }
        if (c >= '`' && c <= 127) {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        }
        sb.append("\u0001\u001e");
        return a((char) (c - 128), sb) + 2;
    }

    private static String a(CharSequence charSequence, int i) {
        int charAt = (charSequence.charAt(i) * 1600) + (charSequence.charAt(i + 1) * com.huawei.hms.network.embedded.g4.k) + charSequence.charAt(i + 2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }

    d0() {
    }
}
