package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.j7;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class p7 extends u7 {
    public static final o7 f = o7.a("multipart/mixed");
    public static final o7 g = o7.a("multipart/alternative");
    public static final o7 h = o7.a("multipart/digest");
    public static final o7 i = o7.a("multipart/parallel");
    public static final o7 j = o7.a(RequestBody.HEAD_VALUE_CONTENT_TYPE_FORM_DATA);
    public static final byte[] k = {58, 32};
    public static final byte[] l = {13, 10};
    public static final byte[] m = {45, 45};

    /* renamed from: a, reason: collision with root package name */
    public final eb f5414a;
    public final o7 b;
    public final o7 c;
    public final List<b> d;
    public long e = -1;

    @Override // com.huawei.hms.network.embedded.u7
    public void writeTo(cb cbVar) throws IOException {
        a(cbVar, false);
    }

    public o7 d() {
        return this.b;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final eb f5415a;
        public o7 b;
        public final List<b> c;

        public p7 a() {
            if (this.c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new p7(this.f5415a, this.b, this.c);
        }

        public a a(String str, @Nullable String str2, u7 u7Var) {
            return a(b.a(str, str2, u7Var));
        }

        public a a(String str, String str2) {
            return a(b.a(str, str2));
        }

        public a a(u7 u7Var) {
            return a(b.a(u7Var));
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(bVar);
            return this;
        }

        public a a(o7 o7Var) {
            if (o7Var == null) {
                throw new NullPointerException("type == null");
            }
            if (o7Var.c().equals("multipart")) {
                this.b = o7Var;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + o7Var);
        }

        public a a(@Nullable j7 j7Var, u7 u7Var) {
            return a(b.a(j7Var, u7Var));
        }

        public a(String str) {
            this.b = p7.f;
            this.c = new ArrayList();
            this.f5415a = eb.d(str);
        }

        public a() {
            this(UUID.randomUUID().toString());
        }
    }

    @Override // com.huawei.hms.network.embedded.u7
    public o7 contentType() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.u7
    public long contentLength() throws IOException {
        long j2 = this.e;
        if (j2 != -1) {
            return j2;
        }
        long a2 = a((cb) null, true);
        this.e = a2;
        return a2;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        public final j7 f5416a;
        public final u7 b;

        @Nullable
        public j7 b() {
            return this.f5416a;
        }

        public u7 a() {
            return this.b;
        }

        public static b a(String str, @Nullable String str2, u7 u7Var) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            p7.a(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                p7.a(sb, str2);
            }
            return a(new j7.a().c("Content-Disposition", sb.toString()).a(), u7Var);
        }

        public static b a(String str, String str2) {
            return a(str, null, u7.create((o7) null, str2));
        }

        public static b a(u7 u7Var) {
            return a((j7) null, u7Var);
        }

        public static b a(@Nullable j7 j7Var, u7 u7Var) {
            if (u7Var == null) {
                throw new NullPointerException("body == null");
            }
            if (j7Var != null && j7Var.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (j7Var == null || j7Var.a("Content-Length") == null) {
                return new b(j7Var, u7Var);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public b(@Nullable j7 j7Var, u7 u7Var) {
            this.f5416a = j7Var;
            this.b = u7Var;
        }
    }

    public int c() {
        return this.d.size();
    }

    public List<b> b() {
        return this.d;
    }

    public String a() {
        return this.f5414a.n();
    }

    public b a(int i2) {
        return this.d.get(i2);
    }

    public static void a(StringBuilder sb, String str) {
        String str2;
        sb.append('\"');
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\n') {
                str2 = "%0A";
            } else if (charAt == '\r') {
                str2 = "%0D";
            } else if (charAt != '\"') {
                sb.append(charAt);
            } else {
                str2 = "%22";
            }
            sb.append(str2);
        }
        sb.append('\"');
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long a(@Nullable cb cbVar, boolean z) throws IOException {
        bb bbVar;
        if (z) {
            cbVar = new bb();
            bbVar = cbVar;
        } else {
            bbVar = 0;
        }
        int size = this.d.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.d.get(i2);
            j7 j7Var = bVar.f5416a;
            u7 u7Var = bVar.b;
            cbVar.write(m);
            cbVar.a(this.f5414a);
            cbVar.write(l);
            if (j7Var != null) {
                int d = j7Var.d();
                for (int i3 = 0; i3 < d; i3++) {
                    cbVar.a(j7Var.a(i3)).write(k).a(j7Var.b(i3)).write(l);
                }
            }
            o7 contentType = u7Var.contentType();
            if (contentType != null) {
                cbVar.a("Content-Type: ").a(contentType.toString()).write(l);
            }
            long contentLength = u7Var.contentLength();
            if (contentLength != -1) {
                cbVar.a("Content-Length: ").a(contentLength).write(l);
            } else if (z) {
                bbVar.s();
                return -1L;
            }
            byte[] bArr = l;
            cbVar.write(bArr);
            if (z) {
                j2 += contentLength;
            } else {
                u7Var.writeTo(cbVar);
            }
            cbVar.write(bArr);
        }
        byte[] bArr2 = m;
        cbVar.write(bArr2);
        cbVar.a(this.f5414a);
        cbVar.write(bArr2);
        cbVar.write(l);
        if (!z) {
            return j2;
        }
        long B = j2 + bbVar.B();
        bbVar.s();
        return B;
    }

    public p7(eb ebVar, o7 o7Var, List<b> list) {
        this.f5414a = ebVar;
        this.b = o7Var;
        this.c = o7.a(o7Var + "; boundary=" + ebVar.n());
        this.d = f8.a(list);
    }
}
