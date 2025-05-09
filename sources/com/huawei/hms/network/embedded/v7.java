package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class v7 implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final t7 f5543a;
    public final r7 b;
    public final int c;
    public final String d;

    @Nullable
    public final i7 e;
    public final j7 f;

    @Nullable
    public final w7 g;

    @Nullable
    public final v7 h;

    @Nullable
    public final v7 i;

    @Nullable
    public final v7 j;
    public final long k;
    public final long l;

    @Nullable
    public final v8 m;

    @Nullable
    public volatile s6 n;

    public boolean z() {
        int i = this.c;
        if (i == 307 || i == 308) {
            return true;
        }
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    public j7 y() {
        return this.f;
    }

    @Nullable
    public i7 x() {
        return this.e;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        public t7 f5544a;

        @Nullable
        public r7 b;
        public int c;
        public String d;

        @Nullable
        public i7 e;
        public j7.a f;

        @Nullable
        public w7 g;

        @Nullable
        public v7 h;

        @Nullable
        public v7 i;

        @Nullable
        public v7 j;
        public long k;
        public long l;

        @Nullable
        public v8 m;

        public a c(@Nullable v7 v7Var) {
            if (v7Var != null) {
                d(v7Var);
            }
            this.j = v7Var;
            return this;
        }

        public a b(String str, String str2) {
            this.f.d(str, str2);
            return this;
        }

        public a b(String str) {
            this.f.d(str);
            return this;
        }

        public a b(@Nullable v7 v7Var) {
            if (v7Var != null) {
                a("networkResponse", v7Var);
            }
            this.h = v7Var;
            return this;
        }

        public a b(long j) {
            this.k = j;
            return this;
        }

        public void a(v8 v8Var) {
            this.m = v8Var;
        }

        public v7 a() {
            if (this.f5544a == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.c >= 0) {
                if (this.d != null) {
                    return new v7(this);
                }
                throw new IllegalStateException("message == null");
            }
            throw new IllegalStateException("code < 0: " + this.c);
        }

        public a a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(@Nullable w7 w7Var) {
            this.g = w7Var;
            return this;
        }

        public a a(@Nullable v7 v7Var) {
            if (v7Var != null) {
                a("cacheResponse", v7Var);
            }
            this.i = v7Var;
            return this;
        }

        public a a(t7 t7Var) {
            this.f5544a = t7Var;
            return this;
        }

        public a a(r7 r7Var) {
            this.b = r7Var;
            return this;
        }

        public a a(j7 j7Var) {
            this.f = j7Var.c();
            return this;
        }

        public a a(@Nullable i7 i7Var) {
            this.e = i7Var;
            return this;
        }

        public a a(long j) {
            this.l = j;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        private void d(v7 v7Var) {
            if (v7Var.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        private void a(String str, v7 v7Var) {
            if (v7Var.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            }
            if (v7Var.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            if (v7Var.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            }
            if (v7Var.j == null) {
                return;
            }
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }

        public a(v7 v7Var) {
            this.c = -1;
            this.f5544a = v7Var.f5543a;
            this.b = v7Var.b;
            this.c = v7Var.c;
            this.d = v7Var.d;
            this.e = v7Var.e;
            this.f = v7Var.f.c();
            this.g = v7Var.g;
            this.h = v7Var.h;
            this.i = v7Var.i;
            this.j = v7Var.j;
            this.k = v7Var.k;
            this.l = v7Var.l;
            this.m = v7Var.m;
        }

        public a() {
            this.c = -1;
            this.f = new j7.a();
        }
    }

    public int w() {
        return this.c;
    }

    public List<w6> v() {
        String str;
        int i = this.c;
        if (i == 401) {
            str = "WWW-Authenticate";
        } else {
            if (i != 407) {
                return Collections.emptyList();
            }
            str = "Proxy-Authenticate";
        }
        return i9.a(y(), str);
    }

    @Nullable
    public v7 u() {
        return this.i;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.f5543a.k() + '}';
    }

    public s6 t() {
        s6 s6Var = this.n;
        if (s6Var != null) {
            return s6Var;
        }
        s6 a2 = s6.a(this.f);
        this.n = a2;
        return a2;
    }

    @Nullable
    public w7 s() {
        return this.g;
    }

    public w7 j(long j) throws IOException {
        db peek = this.g.x().peek();
        bb bbVar = new bb();
        peek.h(j);
        bbVar.write(peek, Math.min(j, peek.g().B()));
        return w7.a(this.g.w(), bbVar.B(), bbVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        w7 w7Var = this.g;
        if (w7Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        w7Var.close();
    }

    public List<String> c(String str) {
        return this.f.d(str);
    }

    @Nullable
    public String b(String str) {
        return a(str, null);
    }

    @Nullable
    public String a(String str, @Nullable String str2) {
        String a2 = this.f.a(str);
        return a2 != null ? a2 : str2;
    }

    public j7 J() throws IOException {
        v8 v8Var = this.m;
        if (v8Var != null) {
            return v8Var.l();
        }
        throw new IllegalStateException("trailers not available");
    }

    public long I() {
        return this.k;
    }

    public t7 H() {
        return this.f5543a;
    }

    public long G() {
        return this.l;
    }

    public r7 F() {
        return this.b;
    }

    @Nullable
    public v7 E() {
        return this.j;
    }

    public a D() {
        return new a(this);
    }

    @Nullable
    public v7 C() {
        return this.h;
    }

    public String B() {
        return this.d;
    }

    public boolean A() {
        int i = this.c;
        return i >= 200 && i < 300;
    }

    public v7(a aVar) {
        this.f5543a = aVar.f5544a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
        this.m = aVar.m;
    }
}
