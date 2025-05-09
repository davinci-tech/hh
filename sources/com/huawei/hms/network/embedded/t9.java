package com.huawei.hms.network.embedded;

import androidx.webkit.ProxyConfig;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class t9 {

    /* renamed from: a, reason: collision with root package name */
    public static final int f5503a = 15;
    public static final int b = 31;
    public static final int c = 63;
    public static final int d = 127;
    public static final s9[] e = {new s9(s9.n, ""), new s9(s9.k, "GET"), new s9(s9.k, "POST"), new s9(s9.l, "/"), new s9(s9.l, "/index.html"), new s9(s9.m, "http"), new s9(s9.m, ProxyConfig.MATCH_HTTPS), new s9(s9.j, "200"), new s9(s9.j, "204"), new s9(s9.j, "206"), new s9(s9.j, Constants.WEBVIEW_STATUS_BACK), new s9(s9.j, "400"), new s9(s9.j, "404"), new s9(s9.j, "500"), new s9("accept-charset", ""), new s9("accept-encoding", "gzip, deflate"), new s9("accept-language", ""), new s9("accept-ranges", ""), new s9("accept", ""), new s9("access-control-allow-origin", ""), new s9("age", ""), new s9("allow", ""), new s9("authorization", ""), new s9("cache-control", ""), new s9("content-disposition", ""), new s9("content-encoding", ""), new s9("content-language", ""), new s9(j2.w, ""), new s9("content-location", ""), new s9("content-range", ""), new s9(com.alipay.sdk.m.p.e.f, ""), new s9("cookie", ""), new s9("date", ""), new s9("etag", ""), new s9("expect", ""), new s9("expires", ""), new s9("from", ""), new s9("host", ""), new s9("if-match", ""), new s9("if-modified-since", ""), new s9("if-none-match", ""), new s9("if-range", ""), new s9("if-unmodified-since", ""), new s9("last-modified", ""), new s9("link", ""), new s9("location", ""), new s9("max-forwards", ""), new s9("proxy-authenticate", ""), new s9("proxy-authorization", ""), new s9("range", ""), new s9("referer", ""), new s9("refresh", ""), new s9("retry-after", ""), new s9("server", ""), new s9("set-cookie", ""), new s9("strict-transport-security", ""), new s9(w9.l, ""), new s9(FeedbackWebConstants.USER_AGENT, ""), new s9("vary", ""), new s9("via", ""), new s9("www-authenticate", "")};
    public static final Map<eb, Integer> f = a();

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final List<s9> f5504a;
        public final db b;
        public final int c;
        public int d;
        public s9[] e;
        public int f;
        public int g;
        public int h;

        public void d() throws IOException {
            while (!this.b.i()) {
                byte readByte = this.b.readByte();
                int i = readByte & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((readByte & 128) == 128) {
                    e(a(i, 127) - 1);
                } else if (i == 64) {
                    h();
                } else if ((readByte & 64) == 64) {
                    f(a(i, 63) - 1);
                } else if ((readByte & 32) == 32) {
                    int a2 = a(i, 31);
                    this.d = a2;
                    if (a2 < 0 || a2 > this.c) {
                        throw new IOException("Invalid dynamic table size update " + this.d);
                    }
                    e();
                } else if (i == 16 || i == 0) {
                    i();
                } else {
                    g(a(i, 15) - 1);
                }
            }
        }

        public eb c() throws IOException {
            int g = g();
            boolean z = (g & 128) == 128;
            int a2 = a(g, 127);
            return z ? eb.e(aa.b().a(this.b.g(a2))) : this.b.d(a2);
        }

        public int b() {
            return this.d;
        }

        public List<s9> a() {
            ArrayList arrayList = new ArrayList(this.f5504a);
            this.f5504a.clear();
            return arrayList;
        }

        public int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int g = g();
                if ((g & 128) == 0) {
                    return i2 + (g << i4);
                }
                i2 += (g & 127) << i4;
                i4 += 7;
            }
        }

        private void i() throws IOException {
            this.f5504a.add(new s9(t9.a(c()), c()));
        }

        private void h() throws IOException {
            a(-1, new s9(t9.a(c()), c()));
        }

        private void g(int i) throws IOException {
            this.f5504a.add(new s9(c(i), c()));
        }

        private int g() throws IOException {
            return this.b.readByte() & 255;
        }

        private void f(int i) throws IOException {
            a(-1, new s9(c(i), c()));
        }

        private void f() {
            Arrays.fill(this.e, (Object) null);
            this.f = this.e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        private void e(int i) throws IOException {
            if (d(i)) {
                this.f5504a.add(t9.e[i]);
                return;
            }
            int a2 = a(i - t9.e.length);
            if (a2 >= 0) {
                s9[] s9VarArr = this.e;
                if (a2 < s9VarArr.length) {
                    this.f5504a.add(s9VarArr[a2]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        private void e() {
            int i = this.d;
            int i2 = this.h;
            if (i < i2) {
                if (i == 0) {
                    f();
                } else {
                    b(i2 - i);
                }
            }
        }

        private boolean d(int i) {
            return i >= 0 && i <= t9.e.length - 1;
        }

        private eb c(int i) throws IOException {
            s9 s9Var;
            if (!d(i)) {
                int a2 = a(i - t9.e.length);
                if (a2 >= 0) {
                    s9[] s9VarArr = this.e;
                    if (a2 < s9VarArr.length) {
                        s9Var = s9VarArr[a2];
                    }
                }
                throw new IOException("Header index too large " + (i + 1));
            }
            s9Var = t9.e[i];
            return s9Var.f5479a;
        }

        private int b(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.e.length;
                while (true) {
                    length--;
                    i2 = this.f;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    s9[] s9VarArr = this.e;
                    i -= s9VarArr[length].c;
                    this.h -= s9VarArr[length].c;
                    this.g--;
                    i3++;
                }
                s9[] s9VarArr2 = this.e;
                int i4 = i2 + 1;
                System.arraycopy(s9VarArr2, i4, s9VarArr2, i4 + i3, this.g);
                this.f += i3;
            }
            return i3;
        }

        private void a(int i, s9 s9Var) {
            this.f5504a.add(s9Var);
            int i2 = s9Var.c;
            if (i != -1) {
                i2 -= this.e[a(i)].c;
            }
            int i3 = this.d;
            if (i2 > i3) {
                f();
                return;
            }
            int b = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g;
                s9[] s9VarArr = this.e;
                if (i4 + 1 > s9VarArr.length) {
                    s9[] s9VarArr2 = new s9[s9VarArr.length * 2];
                    System.arraycopy(s9VarArr, 0, s9VarArr2, s9VarArr.length, s9VarArr.length);
                    this.f = this.e.length - 1;
                    this.e = s9VarArr2;
                }
                int i5 = this.f;
                this.f = i5 - 1;
                this.e[i5] = s9Var;
                this.g++;
            } else {
                this.e[i + a(i) + b] = s9Var;
            }
            this.h += i2;
        }

        private int a(int i) {
            return this.f + 1 + i;
        }

        public a(int i, zb zbVar) {
            this(i, i, zbVar);
        }

        public a(int i, int i2, zb zbVar) {
            this.f5504a = new ArrayList();
            this.e = new s9[8];
            this.f = 7;
            this.g = 0;
            this.h = 0;
            this.c = i;
            this.d = i2;
            this.b = ob.a(zbVar);
        }
    }

    public static final class b {
        public static final int k = 4096;
        public static final int l = 16384;

        /* renamed from: a, reason: collision with root package name */
        public final bb f5505a;
        public final boolean b;
        public int c;
        public boolean d;
        public int e;
        public int f;
        public s9[] g;
        public int h;
        public int i;
        public int j;

        /* JADX WARN: Removed duplicated region for block: B:21:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ad  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(java.util.List<com.huawei.hms.network.embedded.s9> r13) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 227
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.t9.b.a(java.util.List):void");
        }

        public void a(eb ebVar) throws IOException {
            int j;
            int i;
            if (!this.b || aa.b().a(ebVar) >= ebVar.j()) {
                j = ebVar.j();
                i = 0;
            } else {
                bb bbVar = new bb();
                aa.b().a(ebVar, bbVar);
                ebVar = bbVar.r();
                j = ebVar.j();
                i = 128;
            }
            a(j, 127, i);
            this.f5505a.a(ebVar);
        }

        public void a(int i, int i2, int i3) {
            int i4;
            bb bbVar;
            if (i < i2) {
                bbVar = this.f5505a;
                i4 = i | i3;
            } else {
                this.f5505a.writeByte(i3 | i2);
                i4 = i - i2;
                while (i4 >= 128) {
                    this.f5505a.writeByte(128 | (i4 & 127));
                    i4 >>>= 7;
                }
                bbVar = this.f5505a;
            }
            bbVar.writeByte(i4);
        }

        public void a(int i) {
            this.e = i;
            int min = Math.min(i, 16384);
            int i2 = this.f;
            if (i2 == min) {
                return;
            }
            if (min < i2) {
                this.c = Math.min(this.c, min);
            }
            this.d = true;
            this.f = min;
            a();
        }

        private void b() {
            Arrays.fill(this.g, (Object) null);
            this.h = this.g.length - 1;
            this.i = 0;
            this.j = 0;
        }

        private int b(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.g.length;
                while (true) {
                    length--;
                    i2 = this.h;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    s9[] s9VarArr = this.g;
                    i -= s9VarArr[length].c;
                    this.j -= s9VarArr[length].c;
                    this.i--;
                    i3++;
                }
                s9[] s9VarArr2 = this.g;
                int i4 = i2 + 1;
                System.arraycopy(s9VarArr2, i4, s9VarArr2, i4 + i3, this.i);
                s9[] s9VarArr3 = this.g;
                int i5 = this.h + 1;
                Arrays.fill(s9VarArr3, i5, i5 + i3, (Object) null);
                this.h += i3;
            }
            return i3;
        }

        private void a(s9 s9Var) {
            int i = s9Var.c;
            int i2 = this.f;
            if (i > i2) {
                b();
                return;
            }
            b((this.j + i) - i2);
            int i3 = this.i;
            s9[] s9VarArr = this.g;
            if (i3 + 1 > s9VarArr.length) {
                s9[] s9VarArr2 = new s9[s9VarArr.length * 2];
                System.arraycopy(s9VarArr, 0, s9VarArr2, s9VarArr.length, s9VarArr.length);
                this.h = this.g.length - 1;
                this.g = s9VarArr2;
            }
            int i4 = this.h;
            this.h = i4 - 1;
            this.g[i4] = s9Var;
            this.i++;
            this.j += i;
        }

        private void a() {
            int i = this.f;
            int i2 = this.j;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    b(i2 - i);
                }
            }
        }

        public b(bb bbVar) {
            this(4096, true, bbVar);
        }

        public b(int i, boolean z, bb bbVar) {
            this.c = Integer.MAX_VALUE;
            this.g = new s9[8];
            this.h = 7;
            this.i = 0;
            this.j = 0;
            this.e = i;
            this.f = i;
            this.b = z;
            this.f5505a = bbVar;
        }
    }

    public static Map<eb, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(e.length);
        int i = 0;
        while (true) {
            s9[] s9VarArr = e;
            if (i >= s9VarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(s9VarArr[i].f5479a)) {
                linkedHashMap.put(s9VarArr[i].f5479a, Integer.valueOf(i));
            }
            i++;
        }
    }

    public static eb a(eb ebVar) throws IOException {
        int j = ebVar.j();
        for (int i = 0; i < j; i++) {
            byte a2 = ebVar.a(i);
            if (a2 >= 65 && a2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + ebVar.n());
            }
        }
        return ebVar;
    }
}
