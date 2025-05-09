package com.huawei.hms.scankit.p;

import androidx.core.view.InputDeviceCompat;

/* loaded from: classes4.dex */
final class n implements v2 {
    public int a() {
        return 5;
    }

    @Override // com.huawei.hms.scankit.p.v2
    public void a(y2 y2Var) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (true) {
            if (!y2Var.i()) {
                break;
            }
            sb.append(y2Var.c());
            y2Var.f++;
            if (d4.a(y2Var.d(), y2Var.f, a()) != a()) {
                y2Var.b(0);
                break;
            }
        }
        int length = sb.length() - 1;
        int a2 = y2Var.a() + length + 1;
        y2Var.c(a2);
        boolean z = y2Var.g().a() - a2 > 0;
        if (y2Var.i() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else {
                if (length > 1555) {
                    throw new IllegalStateException("Message length not in valid ranges: " + length);
                }
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            }
        }
        int length2 = sb.length();
        for (int i = 0; i < length2; i++) {
            y2Var.a(a(sb.charAt(i), y2Var.a() + 1));
        }
    }

    private static char a(char c, int i) {
        int i2 = c + ((i * 149) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }

    n() {
    }
}
