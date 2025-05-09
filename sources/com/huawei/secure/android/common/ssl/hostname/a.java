package com.huawei.secure.android.common.ssl.hostname;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes6.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final String f8629a;
    private final int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    public a(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f8629a = name;
        this.b = name.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x009b, code lost:
    
        return new java.lang.String(r0, r1, r8.f - r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a() {
        /*
            r8 = this;
            int r0 = r8.c
            r8.d = r0
            r8.e = r0
        L6:
            int r0 = r8.c
            int r1 = r8.b
            if (r0 < r1) goto L19
            char[] r0 = r8.g
            int r1 = r8.d
            java.lang.String r2 = new java.lang.String
            int r3 = r8.e
            int r3 = r3 - r1
            r2.<init>(r0, r1, r3)
            return r2
        L19:
            char[] r1 = r8.g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L5c
            if (r2 == r5) goto L51
            r5 = 92
            if (r2 == r5) goto L3e
            if (r2 == r4) goto L51
            if (r2 == r3) goto L51
            int r3 = r8.e
            int r4 = r3 + 1
            r8.e = r4
            r1[r3] = r2
            int r0 = r0 + 1
            r8.c = r0
            goto L6
        L3e:
            int r0 = r8.e
            int r2 = r0 + 1
            r8.e = r2
            char r2 = r8.b()
            r1[r0] = r2
            int r0 = r8.c
            int r0 = r0 + 1
            r8.c = r0
            goto L6
        L51:
            int r0 = r8.d
            java.lang.String r2 = new java.lang.String
            int r3 = r8.e
            int r3 = r3 - r0
            r2.<init>(r1, r0, r3)
            return r2
        L5c:
            int r2 = r8.e
            r8.f = r2
            int r0 = r0 + 1
            r8.c = r0
            int r0 = r2 + 1
            r8.e = r0
            r1[r2] = r6
        L6a:
            int r0 = r8.c
            int r1 = r8.b
            if (r0 >= r1) goto L83
            char[] r2 = r8.g
            char r7 = r2[r0]
            if (r7 != r6) goto L83
            int r1 = r8.e
            int r7 = r1 + 1
            r8.e = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.c = r0
            goto L6a
        L83:
            if (r0 == r1) goto L8f
            char[] r1 = r8.g
            char r0 = r1[r0]
            if (r0 == r3) goto L8f
            if (r0 == r4) goto L8f
            if (r0 != r5) goto L6
        L8f:
            char[] r0 = r8.g
            int r1 = r8.d
            java.lang.String r2 = new java.lang.String
            int r3 = r8.f
            int r3 = r3 - r1
            r2.<init>(r0, r1, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.secure.android.common.ssl.hostname.a.a():java.lang.String");
    }

    private char b() {
        int i = this.c + 1;
        this.c = i;
        if (i == this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
        }
        char c = this.g[i];
        if (c == ' ' || c == '%' || c == '\\' || c == '_' || c == '\"' || c == '#') {
            return c;
        }
        switch (c) {
            case '*':
            case '+':
            case ',':
                return c;
            default:
                switch (c) {
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                        return c;
                    default:
                        return c();
                }
        }
    }

    private char c() {
        int i;
        int i2;
        int a2 = a(this.c);
        this.c++;
        if (a2 < 128) {
            return (char) a2;
        }
        if (a2 < 192 || a2 > 247) {
            return '?';
        }
        if (a2 <= 223) {
            i = a2 & 31;
            i2 = 1;
        } else if (a2 <= 239) {
            i = a2 & 15;
            i2 = 2;
        } else {
            i = a2 & 7;
            i2 = 3;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.c;
            int i5 = i4 + 1;
            this.c = i5;
            if (i5 == this.b || this.g[i5] != '\\') {
                return '?';
            }
            int i6 = i4 + 2;
            this.c = i6;
            int a3 = a(i6);
            this.c++;
            if ((a3 & 192) != 128) {
                return '?';
            }
            i = (i << 6) + (a3 & 63);
        }
        return (char) i;
    }

    private String d() {
        int i;
        char[] cArr;
        char c;
        int i2 = this.c;
        if (i2 + 4 >= this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
        }
        this.d = i2;
        this.c = i2 + 1;
        while (true) {
            i = this.c;
            if (i == this.b || (c = (cArr = this.g)[i]) == '+' || c == ',' || c == ';') {
                break;
            }
            if (c == ' ') {
                this.e = i;
                this.c = i + 1;
                while (true) {
                    int i3 = this.c;
                    if (i3 >= this.b || this.g[i3] != ' ') {
                        break;
                    }
                    this.c = i3 + 1;
                }
            } else {
                if (c >= 'A' && c <= 'F') {
                    cArr[i] = (char) (c + ' ');
                }
                this.c = i + 1;
            }
        }
        this.e = i;
        int i4 = this.e;
        int i5 = this.d;
        int i6 = i4 - i5;
        if (i6 < 5 || (i6 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
        }
        int i7 = i6 / 2;
        byte[] bArr = new byte[i7];
        int i8 = i5 + 1;
        for (int i9 = 0; i9 < i7; i9++) {
            bArr[i9] = (byte) a(i8);
            i8 += 2;
        }
        return new String(this.g, this.d, i6);
    }

    private String e() {
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        char c2;
        char c3;
        int i5;
        int i6;
        char c4;
        char c5;
        while (true) {
            i = this.c;
            i2 = this.b;
            if (i >= i2 || this.g[i] != ' ') {
                break;
            }
            this.c = i + 1;
        }
        if (i == i2) {
            return null;
        }
        this.d = i;
        this.c = i + 1;
        while (true) {
            i3 = this.c;
            i4 = this.b;
            if (i3 >= i4 || (c5 = this.g[i3]) == '=' || c5 == ' ') {
                break;
            }
            this.c = i3 + 1;
        }
        if (i3 >= i4) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
        }
        this.e = i3;
        if (this.g[i3] == ' ') {
            while (true) {
                i5 = this.c;
                i6 = this.b;
                if (i5 >= i6 || (c4 = this.g[i5]) == '=' || c4 != ' ') {
                    break;
                }
                this.c = i5 + 1;
            }
            if (this.g[i5] != '=' || i5 == i6) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
            }
        }
        this.c++;
        while (true) {
            int i7 = this.c;
            if (i7 >= this.b || this.g[i7] != ' ') {
                break;
            }
            this.c = i7 + 1;
        }
        int i8 = this.e;
        int i9 = this.d;
        if (i8 - i9 > 4) {
            char[] cArr = this.g;
            if (cArr[i9 + 3] == '.' && (((c = cArr[i9]) == 'O' || c == 'o') && (((c2 = cArr[i9 + 1]) == 'I' || c2 == 'i') && ((c3 = cArr[i9 + 2]) == 'D' || c3 == 'd')))) {
                this.d = i9 + 4;
            }
        }
        char[] cArr2 = this.g;
        int i10 = this.d;
        return new String(cArr2, i10, i8 - i10);
    }

    private String f() {
        int i = this.c + 1;
        this.c = i;
        this.d = i;
        this.e = i;
        while (true) {
            int i2 = this.c;
            if (i2 == this.b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f8629a);
            }
            char[] cArr = this.g;
            char c = cArr[i2];
            if (c == '\"') {
                this.c = i2 + 1;
                while (true) {
                    int i3 = this.c;
                    if (i3 >= this.b || this.g[i3] != ' ') {
                        break;
                    }
                    this.c = i3 + 1;
                }
                char[] cArr2 = this.g;
                int i4 = this.d;
                return new String(cArr2, i4, this.e - i4);
            }
            if (c == '\\') {
                cArr[this.e] = b();
            } else {
                cArr[this.e] = c;
            }
            this.c++;
            this.e++;
        }
    }

    public List<String> b(String str) {
        String f;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f8629a.toCharArray();
        List<String> emptyList = Collections.emptyList();
        String e = e();
        if (e == null) {
            return emptyList;
        }
        do {
            int i = this.c;
            if (i < this.b) {
                char c = this.g[i];
                if (c == '\"') {
                    f = f();
                } else if (c != '#') {
                    f = (c == '+' || c == ',' || c == ';') ? "" : a();
                } else {
                    f = d();
                }
                if (str.equalsIgnoreCase(e)) {
                    if (emptyList.isEmpty()) {
                        emptyList = new ArrayList<>();
                    }
                    emptyList.add(f);
                }
                int i2 = this.c;
                if (i2 < this.b) {
                    char c2 = this.g[i2];
                    if (c2 != ',' && c2 != ';' && c2 != '+') {
                        throw new IllegalStateException("Malformed DN: " + this.f8629a);
                    }
                    this.c = i2 + 1;
                    e = e();
                }
            }
            return emptyList;
        } while (e != null);
        throw new IllegalStateException("Malformed DN: " + this.f8629a);
    }

    private int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.b) {
            char[] cArr = this.g;
            char c = cArr[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else {
                if (c < 'A' || c > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.f8629a);
                }
                i2 = c - '7';
            }
            char c2 = cArr[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else {
                if (c2 < 'A' || c2 > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.f8629a);
                }
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f8629a);
    }

    public String a(String str) {
        String f;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.f8629a.toCharArray();
        String e = e();
        if (e == null) {
            return null;
        }
        do {
            int i = this.c;
            if (i == this.b) {
                return null;
            }
            char c = this.g[i];
            if (c == '\"') {
                f = f();
            } else if (c != '#') {
                f = (c == '+' || c == ',' || c == ';') ? "" : a();
            } else {
                f = d();
            }
            if (str.equalsIgnoreCase(e)) {
                return f;
            }
            int i2 = this.c;
            if (i2 >= this.b) {
                return null;
            }
            char c2 = this.g[i2];
            if (c2 != ',' && c2 != ';' && c2 != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f8629a);
            }
            this.c = i2 + 1;
            e = e();
        } while (e != null);
        throw new IllegalStateException("Malformed DN: " + this.f8629a);
    }
}
