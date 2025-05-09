package org.json.alipay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: classes8.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public int f15917a;
    public Reader b;
    public char c;
    public boolean d;

    public final String toString() {
        return " at character " + this.f15917a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:125:0x0146, code lost:
    
        throw a("Unterminated string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object d() {
        /*
            Method dump skipped, instructions count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.c.d():java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final char c() {
        /*
            r5 = this;
        L0:
            char r0 = r5.b()
            r1 = 13
            r2 = 10
            r3 = 47
            if (r0 != r3) goto L3e
            char r0 = r5.b()
            r4 = 42
            if (r0 == r4) goto L25
            if (r0 == r3) goto L1a
            r5.a()
            return r3
        L1a:
            char r0 = r5.b()
            if (r0 == r2) goto L0
            if (r0 == r1) goto L0
            if (r0 != 0) goto L1a
            goto L0
        L25:
            char r0 = r5.b()
            if (r0 == 0) goto L37
            if (r0 != r4) goto L25
            char r0 = r5.b()
            if (r0 == r3) goto L0
            r5.a()
            goto L25
        L37:
            java.lang.String r0 = "Unclosed comment"
            org.json.alipay.JSONException r0 = r5.a(r0)
            throw r0
        L3e:
            r3 = 35
            if (r0 != r3) goto L4d
        L42:
            char r0 = r5.b()
            if (r0 == r2) goto L0
            if (r0 == r1) goto L0
            if (r0 != 0) goto L42
            goto L0
        L4d:
            if (r0 == 0) goto L53
            r1 = 32
            if (r0 <= r1) goto L0
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.c.c():char");
    }

    public final char b() {
        if (this.d) {
            this.d = false;
            char c = this.c;
            if (c != 0) {
                this.f15917a++;
            }
            return c;
        }
        try {
            int read = this.b.read();
            if (read <= 0) {
                this.c = (char) 0;
                return (char) 0;
            }
            this.f15917a++;
            char c2 = (char) read;
            this.c = c2;
            return c2;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public final void a() {
        int i;
        if (this.d || (i = this.f15917a) <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f15917a = i - 1;
        this.d = true;
    }

    public final JSONException a(String str) {
        return new JSONException(str + toString());
    }

    private String a(int i) {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        if (this.d) {
            this.d = false;
            cArr[0] = this.c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.f15917a += i2;
        if (i2 < i) {
            throw a("Substring bounds error");
        }
        this.c = cArr[i - 1];
        return new String(cArr);
    }

    public c(String str) {
        this(new StringReader(str));
    }

    public c(Reader reader) {
        this.b = reader.markSupported() ? reader : new BufferedReader(reader);
        this.d = false;
        this.f15917a = 0;
    }
}
