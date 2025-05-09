package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.k8;
import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class i8 implements n7 {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    public final n8 f5309a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        n8 n8Var = this.f5309a;
        v7 a2 = n8Var != null ? n8Var.a(aVar.request()) : null;
        k8 a3 = new k8.a(System.currentTimeMillis(), aVar.request(), a2).a();
        t7 t7Var = a3.f5347a;
        v7 v7Var = a3.b;
        n8 n8Var2 = this.f5309a;
        if (n8Var2 != null) {
            n8Var2.a(a3);
        }
        if (a2 != null && v7Var == null) {
            f8.a(a2.s());
        }
        if (t7Var == null && v7Var == null) {
            return new v7.a().a(aVar.request()).a(r7.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(f8.d).b(-1L).a(System.currentTimeMillis()).a();
        }
        if (t7Var == null) {
            return v7Var.D().a(a(v7Var)).a();
        }
        try {
            v7 a4 = aVar.a(t7Var);
            if (a4 == null && a2 != null) {
            }
            if (v7Var != null) {
                if (a4.w() == 304) {
                    v7 a5 = v7Var.D().a(a(v7Var.y(), a4.y())).b(a4.I()).a(a4.G()).a(a(v7Var)).b(a(a4)).a();
                    a4.s().close();
                    this.f5309a.a();
                    this.f5309a.a(v7Var, a5);
                    return a5;
                }
                f8.a(v7Var.s());
            }
            v7 a6 = a4.D().a(a(v7Var)).b(a(a4)).a();
            if (this.f5309a != null) {
                if (i9.b(a6) && k8.a(a6, t7Var)) {
                    return a(this.f5309a.a(a6), a6);
                }
                if (j9.a(t7Var.h())) {
                    try {
                        this.f5309a.b(t7Var);
                    } catch (IOException unused) {
                    }
                }
            }
            return a6;
        } finally {
            if (a2 != null) {
                f8.a(a2.s());
            }
        }
    }

    public static boolean b(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public class a implements zb {

        /* renamed from: a, reason: collision with root package name */
        public boolean f5310a;
        public final /* synthetic */ db b;
        public final /* synthetic */ j8 c;
        public final /* synthetic */ cb d;

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.b.timeout();
        }

        @Override // com.huawei.hms.network.embedded.zb
        public long read(bb bbVar, long j) throws IOException {
            try {
                long read = this.b.read(bbVar, j);
                if (read != -1) {
                    bbVar.a(this.d.a(), bbVar.B() - read, read);
                    this.d.d();
                    return read;
                }
                if (!this.f5310a) {
                    this.f5310a = true;
                    this.d.close();
                }
                return -1L;
            } catch (IOException e) {
                if (!this.f5310a) {
                    this.f5310a = true;
                    this.c.abort();
                }
                throw e;
            }
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (!this.f5310a && !f8.a(this, 100, TimeUnit.MILLISECONDS)) {
                this.f5310a = true;
                this.c.abort();
            }
            this.b.close();
        }

        public a(db dbVar, j8 j8Var, cb cbVar) {
            this.b = dbVar;
            this.c = j8Var;
            this.d = cbVar;
        }
    }

    public static boolean a(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }

    public static v7 a(v7 v7Var) {
        return (v7Var == null || v7Var.s() == null) ? v7Var : v7Var.D().a((w7) null).a();
    }

    private v7 a(j8 j8Var, v7 v7Var) throws IOException {
        yb a2;
        if (j8Var == null || (a2 = j8Var.a()) == null) {
            return v7Var;
        }
        return v7Var.D().a(new l9(v7Var.b("Content-Type"), v7Var.s().v(), ob.a(new a(v7Var.s().x(), j8Var, ob.a(a2))))).a();
    }

    public static j7 a(j7 j7Var, j7 j7Var2) {
        j7.a aVar = new j7.a();
        int d = j7Var.d();
        for (int i = 0; i < d; i++) {
            String a2 = j7Var.a(i);
            String b = j7Var.b(i);
            if ((!"Warning".equalsIgnoreCase(a2) || !b.startsWith("1")) && (a(a2) || !b(a2) || j7Var2.a(a2) == null)) {
                c8.f5203a.a(aVar, a2, b);
            }
        }
        int d2 = j7Var2.d();
        for (int i2 = 0; i2 < d2; i2++) {
            String a3 = j7Var2.a(i2);
            if (!a(a3) && b(a3)) {
                c8.f5203a.a(aVar, a3, j7Var2.b(i2));
            }
        }
        return aVar.a();
    }

    public i8(@Nullable n8 n8Var) {
        this.f5309a = n8Var;
    }
}
