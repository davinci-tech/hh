package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.l8;
import com.huawei.hms.network.embedded.t7;
import com.huawei.hms.network.embedded.v7;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class r6 implements Closeable, Flushable {
    public static final int h = 201105;
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;

    /* renamed from: a, reason: collision with root package name */
    public final n8 f5454a;
    public final l8 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public int z() {
        int i2;
        synchronized (this) {
            i2 = this.e;
        }
        return i2;
    }

    public long y() {
        return this.b.v();
    }

    public boolean x() {
        return this.b.x();
    }

    public void w() throws IOException {
        this.b.w();
    }

    public int v() {
        int i2;
        synchronized (this) {
            i2 = this.f;
        }
        return i2;
    }

    public void u() throws IOException {
        this.b.t();
    }

    public File t() {
        return this.b.u();
    }

    public void s() throws IOException {
        this.b.s();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.b.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.b.close();
    }

    public void b(t7 t7Var) throws IOException {
        this.b.d(a(t7Var.k()));
    }

    public void a(v7 v7Var, v7 v7Var2) {
        l8.d dVar;
        e eVar = new e(v7Var2);
        try {
            dVar = ((d) v7Var.s()).b.s();
            if (dVar != null) {
                try {
                    eVar.a(dVar);
                    dVar.c();
                } catch (IOException unused) {
                    a(dVar);
                }
            }
        } catch (IOException unused2) {
            dVar = null;
        }
    }

    public void a(k8 k8Var) {
        synchronized (this) {
            this.g++;
            if (k8Var.f5347a != null) {
                this.e++;
            } else if (k8Var.b != null) {
                this.f++;
            }
        }
    }

    @Nullable
    public v7 a(t7 t7Var) {
        try {
            l8.f c2 = this.b.c(a(t7Var.k()));
            if (c2 == null) {
                return null;
            }
            try {
                e eVar = new e(c2.e(0));
                v7 a2 = eVar.a(c2);
                if (eVar.a(t7Var, a2)) {
                    return a2;
                }
                f8.a(a2.s());
                return null;
            } catch (IOException unused) {
                f8.a(c2);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    @Nullable
    public j8 a(v7 v7Var) {
        l8.d dVar;
        String h2 = v7Var.H().h();
        if (j9.a(v7Var.H().h())) {
            try {
                b(v7Var.H());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!h2.equals("GET") || i9.c(v7Var)) {
            return null;
        }
        e eVar = new e(v7Var);
        try {
            dVar = this.b.b(a(v7Var.H().k()));
            if (dVar == null) {
                return null;
            }
            try {
                eVar.a(dVar);
                return new c(dVar);
            } catch (IOException unused2) {
                a(dVar);
                return null;
            }
        } catch (IOException unused3) {
            dVar = null;
        }
    }

    public int F() {
        int i2;
        synchronized (this) {
            i2 = this.c;
        }
        return i2;
    }

    public static final class e {
        public static final String k = ma.f().a() + "-Sent-Millis";
        public static final String l = ma.f().a() + "-Received-Millis";

        /* renamed from: a, reason: collision with root package name */
        public final String f5458a;
        public final j7 b;
        public final String c;
        public final r7 d;
        public final int e;
        public final String f;
        public final j7 g;

        @Nullable
        public final i7 h;
        public final long i;
        public final long j;

        public boolean a(t7 t7Var, v7 v7Var) {
            return this.f5458a.equals(t7Var.k().toString()) && this.c.equals(t7Var.h()) && i9.a(v7Var, this.b, t7Var);
        }

        public void a(l8.d dVar) throws IOException {
            cb a2 = ob.a(dVar.a(0));
            a2.a(this.f5458a).writeByte(10);
            a2.a(this.c).writeByte(10);
            a2.a(this.b.d()).writeByte(10);
            int d = this.b.d();
            for (int i = 0; i < d; i++) {
                a2.a(this.b.a(i)).a(": ").a(this.b.b(i)).writeByte(10);
            }
            a2.a(new o9(this.d, this.e, this.f).toString()).writeByte(10);
            a2.a(this.g.d() + 2).writeByte(10);
            int d2 = this.g.d();
            for (int i2 = 0; i2 < d2; i2++) {
                a2.a(this.g.a(i2)).a(": ").a(this.g.b(i2)).writeByte(10);
            }
            a2.a(k).a(": ").a(this.i).writeByte(10);
            a2.a(l).a(": ").a(this.j).writeByte(10);
            if (a()) {
                a2.writeByte(10);
                a2.a(this.h.a().a()).writeByte(10);
                a(a2, this.h.d());
                a(a2, this.h.b());
                a2.a(this.h.f().a()).writeByte(10);
            }
            a2.close();
        }

        public v7 a(l8.f fVar) {
            String a2 = this.g.a("Content-Type");
            String a3 = this.g.a("Content-Length");
            return new v7.a().a(new t7.a().c(this.f5458a).a(this.c, (u7) null).a(this.b).a()).a(this.d).a(this.e).a(this.f).a(this.g).a(new d(fVar, a2, a3)).a(this.h).b(this.i).a(this.j).a();
        }

        private boolean a() {
            return this.f5458a.startsWith("https://");
        }

        private void a(cb cbVar, List<Certificate> list) throws IOException {
            try {
                cbVar.a(list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    cbVar.a(eb.e(list.get(i).getEncoded()).b()).writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        private List<Certificate> a(db dbVar) throws IOException {
            int a2 = r6.a(dbVar);
            if (a2 == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(a2);
                for (int i = 0; i < a2; i++) {
                    String n = dbVar.n();
                    bb bbVar = new bb();
                    bbVar.a(eb.a(n));
                    arrayList.add(certificateFactory.generateCertificate(bbVar.m()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        public e(zb zbVar) throws IOException {
            try {
                db a2 = ob.a(zbVar);
                this.f5458a = a2.n();
                this.c = a2.n();
                j7.a aVar = new j7.a();
                int a3 = r6.a(a2);
                for (int i = 0; i < a3; i++) {
                    aVar.b(a2.n());
                }
                this.b = aVar.a();
                o9 a4 = o9.a(a2.n());
                this.d = a4.f5401a;
                this.e = a4.b;
                this.f = a4.c;
                j7.a aVar2 = new j7.a();
                int a5 = r6.a(a2);
                for (int i2 = 0; i2 < a5; i2++) {
                    aVar2.b(a2.n());
                }
                String str = k;
                String c = aVar2.c(str);
                String str2 = l;
                String c2 = aVar2.c(str2);
                aVar2.d(str);
                aVar2.d(str2);
                this.i = c != null ? Long.parseLong(c) : 0L;
                this.j = c2 != null ? Long.parseLong(c2) : 0L;
                this.g = aVar2.a();
                if (a()) {
                    String n = a2.n();
                    if (n.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + n + "\"");
                    }
                    this.h = i7.a(!a2.i() ? y7.a(a2.n()) : y7.SSL_3_0, x6.a(a2.n()), a(a2), a(a2));
                } else {
                    this.h = null;
                }
            } finally {
                zbVar.close();
            }
        }

        public e(v7 v7Var) {
            this.f5458a = v7Var.H().k().toString();
            this.b = i9.e(v7Var);
            this.c = v7Var.H().h();
            this.d = v7Var.F();
            this.e = v7Var.w();
            this.f = v7Var.B();
            this.g = v7Var.y();
            this.h = v7Var.x();
            this.i = v7Var.I();
            this.j = v7Var.G();
        }
    }

    public int E() {
        int i2;
        synchronized (this) {
            i2 = this.d;
        }
        return i2;
    }

    public Iterator<String> D() throws IOException {
        return new b();
    }

    public class a implements n8 {
        @Override // com.huawei.hms.network.embedded.n8
        public void b(t7 t7Var) throws IOException {
            r6.this.b(t7Var);
        }

        @Override // com.huawei.hms.network.embedded.n8
        public void a(v7 v7Var, v7 v7Var2) {
            r6.this.a(v7Var, v7Var2);
        }

        @Override // com.huawei.hms.network.embedded.n8
        public void a(k8 k8Var) {
            r6.this.a(k8Var);
        }

        @Override // com.huawei.hms.network.embedded.n8
        public void a() {
            r6.this.C();
        }

        @Override // com.huawei.hms.network.embedded.n8
        @Nullable
        public v7 a(t7 t7Var) throws IOException {
            return r6.this.a(t7Var);
        }

        @Override // com.huawei.hms.network.embedded.n8
        @Nullable
        public j8 a(v7 v7Var) throws IOException {
            return r6.this.a(v7Var);
        }

        public a() {
        }
    }

    public void C() {
        synchronized (this) {
            this.f++;
        }
    }

    public long B() throws IOException {
        return this.b.A();
    }

    public class b implements Iterator<String> {

        /* renamed from: a, reason: collision with root package name */
        public final Iterator<l8.f> f5456a;

        @Nullable
        public String b;
        public boolean c;

        @Override // java.util.Iterator
        public void remove() {
            if (!this.c) {
                throw new IllegalStateException("remove() before next()");
            }
            this.f5456a.remove();
        }

        @Override // java.util.Iterator
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String str = this.b;
            this.b = null;
            this.c = true;
            return str;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.b != null) {
                return true;
            }
            this.c = false;
            while (this.f5456a.hasNext()) {
                try {
                    l8.f next = this.f5456a.next();
                    try {
                        continue;
                        this.b = ob.a(next.e(0)).n();
                        if (next != null) {
                            next.close();
                        }
                        return true;
                    } finally {
                    }
                } catch (IOException unused) {
                }
            }
            return false;
        }

        public b() throws IOException {
            this.f5456a = r6.this.b.B();
        }
    }

    public int A() {
        int i2;
        synchronized (this) {
            i2 = this.g;
        }
        return i2;
    }

    public static class d extends w7 {
        public final l8.f b;
        public final db c;

        @Nullable
        public final String d;

        @Nullable
        public final String e;

        @Override // com.huawei.hms.network.embedded.w7
        public db x() {
            return this.c;
        }

        public class a extends hb {
            public final /* synthetic */ l8.f b;

            @Override // com.huawei.hms.network.embedded.hb, com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
            public void close() throws IOException {
                this.b.close();
                super.close();
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(zb zbVar, l8.f fVar) {
                super(zbVar);
                this.b = fVar;
            }
        }

        @Override // com.huawei.hms.network.embedded.w7
        public o7 w() {
            String str = this.d;
            if (str != null) {
                return o7.b(str);
            }
            return null;
        }

        @Override // com.huawei.hms.network.embedded.w7
        public long v() {
            try {
                String str = this.e;
                if (str != null) {
                    return Long.parseLong(str);
                }
            } catch (NumberFormatException unused) {
            }
            return -1L;
        }

        public d(l8.f fVar, String str, String str2) {
            this.b = fVar;
            this.d = str;
            this.e = str2;
            this.c = ob.a(new a(fVar.e(1), fVar));
        }
    }

    private void a(@Nullable l8.d dVar) {
        if (dVar != null) {
            try {
                dVar.a();
            } catch (IOException unused) {
            }
        }
    }

    public final class c implements j8 {

        /* renamed from: a, reason: collision with root package name */
        public final l8.d f5457a;
        public yb b;
        public yb c;
        public boolean d;

        public class a extends gb {
            public final /* synthetic */ r6 b;
            public final /* synthetic */ l8.d c;

            @Override // com.huawei.hms.network.embedded.gb, com.huawei.hms.network.embedded.yb, java.lang.AutoCloseable, java.nio.channels.Channel
            public void close() throws IOException {
                synchronized (r6.this) {
                    if (c.this.d) {
                        return;
                    }
                    c.this.d = true;
                    r6.this.c++;
                    super.close();
                    this.c.c();
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(yb ybVar, r6 r6Var, l8.d dVar) {
                super(ybVar);
                this.b = r6Var;
                this.c = dVar;
            }
        }

        @Override // com.huawei.hms.network.embedded.j8
        public void abort() {
            synchronized (r6.this) {
                if (this.d) {
                    return;
                }
                this.d = true;
                r6.this.d++;
                f8.a(this.b);
                try {
                    this.f5457a.a();
                } catch (IOException unused) {
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.j8
        public yb a() {
            return this.c;
        }

        public c(l8.d dVar) {
            this.f5457a = dVar;
            yb a2 = dVar.a(1);
            this.b = a2;
            this.c = new a(a2, r6.this, dVar);
        }
    }

    public static String a(m7 m7Var) {
        return eb.d(m7Var.toString()).f().d();
    }

    public static int a(db dbVar) throws IOException {
        try {
            long p = dbVar.p();
            String n = dbVar.n();
            if (p >= 0 && p <= 2147483647L && n.isEmpty()) {
                return (int) p;
            }
            throw new IOException("expected an int but was \"" + p + n + "\"");
        } catch (NumberFormatException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public r6(File file, long j2, ea eaVar) {
        this.f5454a = new a();
        this.b = l8.a(eaVar, file, h, 2, j2);
    }

    public r6(File file, long j2) {
        this(file, j2, ea.f5234a);
    }
}
