package com.huawei.hms.network.embedded;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public abstract class u7 {
    public long contentLength() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract o7 contentType();

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public abstract void writeTo(cb cbVar) throws IOException;

    public static u7 create(@Nullable o7 o7Var, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        f8.a(bArr.length, i, i2);
        return new b(o7Var, i2, bArr, i);
    }

    public class a extends u7 {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o7 f5519a;
        public final /* synthetic */ eb b;

        @Override // com.huawei.hms.network.embedded.u7
        public void writeTo(cb cbVar) throws IOException {
            cbVar.a(this.b);
        }

        @Override // com.huawei.hms.network.embedded.u7
        @Nullable
        public o7 contentType() {
            return this.f5519a;
        }

        @Override // com.huawei.hms.network.embedded.u7
        public long contentLength() throws IOException {
            return this.b.j();
        }

        public a(o7 o7Var, eb ebVar) {
            this.f5519a = o7Var;
            this.b = ebVar;
        }
    }

    public class b extends u7 {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o7 f5520a;
        public final /* synthetic */ int b;
        public final /* synthetic */ byte[] c;
        public final /* synthetic */ int d;

        @Override // com.huawei.hms.network.embedded.u7
        public void writeTo(cb cbVar) throws IOException {
            cbVar.write(this.c, this.d, this.b);
        }

        @Override // com.huawei.hms.network.embedded.u7
        @Nullable
        public o7 contentType() {
            return this.f5520a;
        }

        @Override // com.huawei.hms.network.embedded.u7
        public long contentLength() {
            return this.b;
        }

        public b(o7 o7Var, int i, byte[] bArr, int i2) {
            this.f5520a = o7Var;
            this.b = i;
            this.c = bArr;
            this.d = i2;
        }
    }

    public class c extends u7 {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o7 f5521a;
        public final /* synthetic */ File b;

        @Override // com.huawei.hms.network.embedded.u7
        public void writeTo(cb cbVar) throws IOException {
            zb c = ob.c(this.b);
            try {
                cbVar.a(c);
                if (c != null) {
                    c.close();
                }
            } catch (Throwable th) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // com.huawei.hms.network.embedded.u7
        @Nullable
        public o7 contentType() {
            return this.f5521a;
        }

        @Override // com.huawei.hms.network.embedded.u7
        public long contentLength() {
            return this.b.length();
        }

        public c(o7 o7Var, File file) {
            this.f5521a = o7Var;
            this.b = file;
        }
    }

    public static u7 create(@Nullable o7 o7Var, byte[] bArr) {
        return create(o7Var, bArr, 0, bArr.length);
    }

    public static u7 create(@Nullable o7 o7Var, String str) {
        Charset charset = StandardCharsets.UTF_8;
        if (o7Var != null && (charset = o7Var.a()) == null) {
            charset = StandardCharsets.UTF_8;
            o7Var = o7.b(o7Var + "; charset=utf-8");
        }
        return create(o7Var, str.getBytes(charset));
    }

    public static u7 create(@Nullable o7 o7Var, File file) {
        if (file != null) {
            return new c(o7Var, file);
        }
        throw new NullPointerException("file == null");
    }

    public static u7 create(@Nullable o7 o7Var, eb ebVar) {
        return new a(o7Var, ebVar);
    }
}
