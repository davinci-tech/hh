package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
class lpb {
    private char[] d = null;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f14823a = 0;
    private int c = 0;
    private int b = 0;

    lpb() {
    }

    private boolean b() {
        return this.e < this.f14823a;
    }

    private String b(boolean z) {
        while (true) {
            int i = this.c;
            if (i >= this.b || !Character.isWhitespace(this.d[i])) {
                break;
            }
            this.c++;
        }
        while (true) {
            int i2 = this.b;
            if (i2 <= this.c || !Character.isWhitespace(this.d[i2 - 1])) {
                break;
            }
            this.b--;
        }
        if (z) {
            int i3 = this.b;
            int i4 = this.c;
            if (i3 - i4 >= 2) {
                char[] cArr = this.d;
                if (cArr[i4] == '\"') {
                    int i5 = i3 - 1;
                    if (cArr[i5] == '\"') {
                        this.c = i4 + 1;
                        this.b = i5;
                    }
                }
            }
        }
        int i6 = this.b;
        int i7 = this.c;
        if (i6 >= i7) {
            return new String(this.d, i7, i6 - i7);
        }
        return null;
    }

    private boolean a(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String d(char[] cArr) {
        int i = this.e;
        this.c = i;
        this.b = i;
        while (b() && !a(this.d[this.e], cArr)) {
            this.b++;
            this.e++;
        }
        return b(false);
    }

    private String a(char[] cArr) {
        int i = this.e;
        this.c = i;
        this.b = i;
        boolean z = false;
        boolean z2 = false;
        while (b()) {
            char c = this.d[this.e];
            if (!z && a(c, cArr)) {
                break;
            }
            if (!z2 && c == '\"') {
                z = !z;
            }
            z2 = !z2 && c == '\\';
            this.b++;
            this.e++;
        }
        return b(true);
    }

    List c(String str, char c) {
        if (str == null) {
            return new ArrayList();
        }
        return c(str.toCharArray(), c);
    }

    private List c(char[] cArr, char c) {
        if (cArr == null) {
            return new ArrayList();
        }
        return e(cArr, 0, cArr.length, c);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List e(char[] r6, int r7, int r8, char r9) {
        /*
            r5 = this;
            if (r6 != 0) goto L8
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            return r6
        L8:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r5.d = r6
            r5.e = r7
            r5.f14823a = r8
        L13:
            boolean r7 = r5.b()
            if (r7 == 0) goto L67
            r7 = 2
            char[] r7 = new char[r7]
            r8 = 0
            r1 = 61
            r7[r8] = r1
            r2 = 1
            r7[r2] = r9
            java.lang.String r7 = r5.d(r7)
            boolean r3 = r5.b()
            if (r3 == 0) goto L41
            int r3 = r5.e
            char r4 = r6[r3]
            if (r4 != r1) goto L41
            int r3 = r3 + 1
            r5.e = r3
            char[] r1 = new char[r2]
            r1[r8] = r9
            java.lang.String r8 = r5.a(r1)
            goto L42
        L41:
            r8 = 0
        L42:
            boolean r1 = r5.b()
            if (r1 == 0) goto L52
            int r1 = r5.e
            char r2 = r6[r1]
            if (r2 != r9) goto L52
            int r1 = r1 + 1
            r5.e = r1
        L52:
            if (r7 == 0) goto L13
            java.lang.String r1 = ""
            boolean r1 = r7.equals(r1)
            if (r1 == 0) goto L5e
            if (r8 == 0) goto L13
        L5e:
            lov r1 = new lov
            r1.<init>(r7, r8)
            r0.add(r1)
            goto L13
        L67:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lpb.e(char[], int, int, char):java.util.List");
    }
}
