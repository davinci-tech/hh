package com.huawei.hms.network.embedded;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public abstract class w7 implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    public Reader f5558a;

    public abstract long v();

    @Nullable
    public abstract o7 w();

    public abstract db x();

    public final String y() throws IOException {
        db x = x();
        try {
            String a2 = x.a(f8.a(x, z()));
            if (x != null) {
                x.close();
            }
            return a2;
        } catch (Throwable th) {
            if (x != null) {
                try {
                    x.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public final Reader u() {
        Reader reader = this.f5558a;
        if (reader != null) {
            return reader;
        }
        b bVar = new b(x(), z());
        this.f5558a = bVar;
        return bVar;
    }

    public final byte[] t() throws IOException {
        long v = v();
        if (v > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + v);
        }
        db x = x();
        try {
            byte[] q = x.q();
            if (x != null) {
                x.close();
            }
            if (v == -1 || v == q.length) {
                return q;
            }
            throw new IOException("Content-Length (" + v + ") and stream length (" + q.length + ") disagree");
        } catch (Throwable th) {
            if (x != null) {
                try {
                    x.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public final InputStream s() {
        return x().m();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        f8.a(x());
    }

    private Charset z() {
        o7 w = w();
        return w != null ? w.a(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;
    }

    public class a extends w7 {
        public final /* synthetic */ o7 b;
        public final /* synthetic */ long c;
        public final /* synthetic */ db d;

        @Override // com.huawei.hms.network.embedded.w7
        public db x() {
            return this.d;
        }

        @Override // com.huawei.hms.network.embedded.w7
        @Nullable
        public o7 w() {
            return this.b;
        }

        @Override // com.huawei.hms.network.embedded.w7
        public long v() {
            return this.c;
        }

        public a(o7 o7Var, long j, db dbVar) {
            this.b = o7Var;
            this.c = j;
            this.d = dbVar;
        }
    }

    public static w7 a(@Nullable o7 o7Var, byte[] bArr) {
        return a(o7Var, bArr.length, new bb().write(bArr));
    }

    public static final class b extends Reader {

        /* renamed from: a, reason: collision with root package name */
        public final db f5559a;
        public final Charset b;
        public boolean c;

        @Nullable
        public Reader d;

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.d;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.f5559a.m(), f8.a(this.f5559a, this.b));
                this.d = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.c = true;
            Reader reader = this.d;
            if (reader != null) {
                reader.close();
            } else {
                this.f5559a.close();
            }
        }

        public b(db dbVar, Charset charset) {
            this.f5559a = dbVar;
            this.b = charset;
        }
    }

    public static w7 a(@Nullable o7 o7Var, String str) {
        Charset charset = StandardCharsets.UTF_8;
        if (o7Var != null && (charset = o7Var.a()) == null) {
            charset = StandardCharsets.UTF_8;
            o7Var = o7.b(o7Var + "; charset=utf-8");
        }
        bb a2 = new bb().a(str, charset);
        return a(o7Var, a2.B(), a2);
    }

    public static w7 a(@Nullable o7 o7Var, eb ebVar) {
        return a(o7Var, ebVar.j(), new bb().a(ebVar));
    }

    public static w7 a(@Nullable o7 o7Var, long j, db dbVar) {
        if (dbVar != null) {
            return new a(o7Var, j, dbVar);
        }
        throw new NullPointerException("source == null");
    }
}
