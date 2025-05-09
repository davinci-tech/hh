package com.huawei.hms.scankit.p;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;

/* loaded from: classes4.dex */
final class b implements v2 {
    public int a() {
        return 0;
    }

    @Override // com.huawei.hms.scankit.p.v2
    public void a(y2 y2Var) {
        if (d4.a(y2Var.d(), y2Var.f) >= 2) {
            y2Var.a(a(y2Var.d().charAt(y2Var.f), y2Var.d().charAt(y2Var.f + 1)));
            y2Var.f += 2;
            return;
        }
        char c = y2Var.c();
        int a2 = d4.a(y2Var.d(), y2Var.f, a());
        if (a2 == a()) {
            if (!d4.c(c)) {
                y2Var.a((char) (c + 1));
                y2Var.f++;
                return;
            } else {
                y2Var.a((char) 235);
                y2Var.a((char) (c - 127));
                y2Var.f++;
                return;
            }
        }
        if (a2 == 1) {
            y2Var.a((char) 230);
            y2Var.b(1);
            return;
        }
        if (a2 == 2) {
            y2Var.a((char) 239);
            y2Var.b(2);
            return;
        }
        if (a2 == 3) {
            y2Var.a((char) 238);
            y2Var.b(3);
        } else if (a2 == 4) {
            y2Var.a((char) 240);
            y2Var.b(4);
        } else if (a2 == 5) {
            y2Var.a((char) 231);
            y2Var.b(5);
        } else {
            throw new IllegalStateException("Illegal mode: " + a2);
        }
    }

    private static char a(char c, char c2) {
        if (d4.b(c) && d4.b(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + OldToNewMotionPath.SPORT_TYPE_TENNIS);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }

    b() {
    }
}
