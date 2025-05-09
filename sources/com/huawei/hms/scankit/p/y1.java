package com.huawei.hms.scankit.p;

import java.util.Arrays;

/* loaded from: classes4.dex */
public class y1 {

    /* renamed from: a, reason: collision with root package name */
    private final CharSequence f5924a;
    private final int b;
    private final int c;
    private final byte[] d;

    public y1(CharSequence charSequence, int i, int i2) {
        this.f5924a = charSequence;
        this.c = i;
        this.b = i2;
        byte[] bArr = new byte[i * i2];
        this.d = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    private boolean b(int i, int i2) {
        return this.d[(i2 * this.c) + i] >= 0;
    }

    private void c(int i) {
        a(this.b - 3, 0, i, 1);
        a(this.b - 2, 0, i, 2);
        a(this.b - 1, 0, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 1, i, 6);
        a(2, this.c - 1, i, 7);
        a(3, this.c - 1, i, 8);
    }

    private void d(int i) {
        a(this.b - 1, 0, i, 1);
        a(this.b - 1, this.c - 1, i, 2);
        a(0, this.c - 3, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 3, i, 6);
        a(1, this.c - 2, i, 7);
        a(1, this.c - 1, i, 8);
    }

    public final boolean a(int i, int i2) {
        return this.d[(i2 * this.c) + i] == 1;
    }

    private void a(int i, int i2, boolean z) {
        this.d[(i2 * this.c) + i] = z ? (byte) 1 : (byte) 0;
    }

    private void b(int i) {
        a(this.b - 3, 0, i, 1);
        a(this.b - 2, 0, i, 2);
        a(this.b - 1, 0, i, 3);
        a(0, this.c - 4, i, 4);
        a(0, this.c - 3, i, 5);
        a(0, this.c - 2, i, 6);
        a(0, this.c - 1, i, 7);
        a(1, this.c - 1, i, 8);
    }

    public final void a() {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 4;
        while (true) {
            if (i5 == this.b && i4 == 0) {
                a(i3);
                i3++;
            }
            if (i5 == this.b - 2 && i4 == 0 && this.c % 4 != 0) {
                b(i3);
                i3++;
            }
            if (i5 == this.b - 2 && i4 == 0 && this.c % 8 == 4) {
                c(i3);
                i3++;
            }
            if (i5 == this.b + 4 && i4 == 2 && this.c % 8 == 0) {
                d(i3);
                i3++;
            }
            while (true) {
                if (i5 < this.b && i4 >= 0 && !b(i4, i5)) {
                    a(i5, i4, i3);
                    i3++;
                }
                int i6 = i5 - 2;
                int i7 = i4 + 2;
                if (i6 < 0 || i7 >= this.c) {
                    break;
                }
                i5 = i6;
                i4 = i7;
            }
            int i8 = i5 - 1;
            int i9 = i4 + 5;
            while (true) {
                if (i8 >= 0 && i9 < this.c && !b(i9, i8)) {
                    a(i8, i9, i3);
                    i3++;
                }
                int i10 = i8 + 2;
                int i11 = i9 - 2;
                i = this.b;
                if (i10 >= i || i11 < 0) {
                    break;
                }
                i8 = i10;
                i9 = i11;
            }
            i5 = i8 + 5;
            i4 = i9 - 1;
            if (i5 >= i && i4 >= (i2 = this.c)) {
                break;
            }
        }
        if (b(i2 - 1, i - 1)) {
            return;
        }
        a(this.c - 1, this.b - 1, true);
        a(this.c - 2, this.b - 2, true);
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i < 0) {
            int i5 = this.b;
            i += i5;
            i2 += 4 - ((i5 + 4) % 8);
        }
        if (i2 < 0) {
            int i6 = this.c;
            i2 += i6;
            i += 4 - ((i6 + 4) % 8);
        }
        a(i2, i, (this.f5924a.charAt(i3) & (1 << (8 - i4))) != 0);
    }

    private void a(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        a(i4, i5, i3, 1);
        int i6 = i2 - 1;
        a(i4, i6, i3, 2);
        int i7 = i - 1;
        a(i7, i5, i3, 3);
        a(i7, i6, i3, 4);
        a(i7, i2, i3, 5);
        a(i, i5, i3, 6);
        a(i, i6, i3, 7);
        a(i, i2, i3, 8);
    }

    private void a(int i) {
        a(this.b - 1, 0, i, 1);
        a(this.b - 1, 1, i, 2);
        a(this.b - 1, 2, i, 3);
        a(0, this.c - 2, i, 4);
        a(0, this.c - 1, i, 5);
        a(1, this.c - 1, i, 6);
        a(2, this.c - 1, i, 7);
        a(3, this.c - 1, i, 8);
    }
}
