package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.CreateFileUtil;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.a;
import com.huawei.hms.network.embedded.f1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.r1;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.Request;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public final class n1 implements v1 {
    public static final int ENTRY_METADATA = 0;
    public static final String b = "Cache";
    public static final String c = "\n";
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 80001307;

    /* renamed from: a, reason: collision with root package name */
    public com.huawei.hms.network.embedded.a f5383a;

    public final class b implements o1 {
        public static final String f = "CacheBodyImpl";

        /* renamed from: a, reason: collision with root package name */
        public boolean f5384a;
        public a.c b;
        public OutputStream c;
        public OutputStream d;

        /* renamed from: com.huawei.hms.network.embedded.n1$b$b, reason: collision with other inner class name */
        public class C0142b extends OutputStream {

            /* renamed from: a, reason: collision with root package name */
            public final OutputStream f5385a;

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                this.f5385a.write(bArr, i, i2);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr) throws IOException {
                this.f5385a.write(bArr);
            }

            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                this.f5385a.write(i);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                this.f5385a.flush();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                this.f5385a.close();
            }

            public C0142b(OutputStream outputStream) {
                if (outputStream == null) {
                    throw new IllegalArgumentException("outputStream == null");
                }
                this.f5385a = outputStream;
            }
        }

        @Override // com.huawei.hms.network.embedded.o1
        public void write(byte[] bArr) throws IOException {
            OutputStream outputStream = this.d;
            if (outputStream != null) {
                outputStream.write(bArr);
            }
        }

        @Override // com.huawei.hms.network.embedded.o1
        public void close() throws IOException {
            OutputStream outputStream = this.d;
            if (outputStream != null) {
                outputStream.close();
            }
        }

        public class a extends C0142b {
            public final /* synthetic */ n1 c;
            public final /* synthetic */ a.c d;

            @Override // com.huawei.hms.network.embedded.n1.b.C0142b, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                synchronized (n1.this) {
                    if (b.this.f5384a) {
                        return;
                    }
                    b.this.f5384a = true;
                    super.close();
                    this.d.c();
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(OutputStream outputStream, n1 n1Var, a.c cVar) {
                super(outputStream);
                this.c = n1Var;
                this.d = cVar;
            }
        }

        @Override // com.huawei.hms.network.embedded.o1
        public void abort() {
            synchronized (n1.this) {
                if (this.f5384a) {
                    return;
                }
                this.f5384a = true;
                IoUtils.closeSecure(this.c);
                try {
                    this.b.a();
                } catch (IOException unused) {
                    Logger.w(f, "Terminating the cached file failed !");
                }
            }
        }

        public b(a.c cVar) {
            this.b = cVar;
            try {
                this.c = new FileOutputStream(cVar.a(1));
                this.d = new a(this.c, n1.this, cVar);
            } catch (IOException unused) {
                Logger.w(f, "An exception occurred during the commit.");
                try {
                    cVar.a();
                } catch (IOException unused2) {
                    Logger.w(f, "An exception occurred during the commit, Terminating the cached file failed !");
                }
            }
        }
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public final String f5386a;
        public final int b;
        public final String c;
        public final Headers d;
        public final long e;
        public final long f;
        public final String g;

        public r1.c response(Request request, com.huawei.hms.network.embedded.a aVar, a.e eVar) throws IOException {
            String str = this.d.get("Content-Type");
            long stringToLong = u1.stringToLong(this.d.get("Content-Length"), -1L);
            return new r1.c(this.e, this.f, request, new u0.b().url(this.g).code(this.b).message(this.c).headers(this.d.toMultimap()).body(new h1.g(new f1.b().contentLength(stringToLong).contentType(str).inputStream(new t1(aVar, this.f5386a, new FileInputStream(eVar.a(1)))).build())).build());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(Request request) {
            return this.f5386a.equals(s1.key(request.getUrl()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(a.c cVar) throws IOException {
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cVar.a(0)), Charset.forName("UTF-8")));
                try {
                    bufferedWriter.write(this.f5386a + '\n');
                    bufferedWriter.write(this.b + "\n");
                    bufferedWriter.write(this.c + '\n');
                    bufferedWriter.write(this.e + "\n");
                    bufferedWriter.write(this.f + "\n");
                    bufferedWriter.write(this.g + "\n");
                    int size = this.d.size();
                    bufferedWriter.write(size + "\n");
                    for (int i = 0; i < size; i++) {
                        bufferedWriter.write(this.d.name(i) + ":" + this.d.value(i) + '\n');
                    }
                    bufferedWriter.flush();
                    IoUtils.closeSecure((Writer) bufferedWriter);
                } catch (Throwable th) {
                    th = th;
                    IoUtils.closeSecure((Writer) bufferedWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter = null;
            }
        }

        public c(File file) throws IOException {
            Throwable th;
            InputStreamReader inputStreamReader;
            FileInputStream fileInputStream;
            if (file == null) {
                throw new IOException("Cached file is empty");
            }
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                        try {
                            this.f5386a = bufferedReader2.readLine();
                            this.b = u1.stringToInteger(bufferedReader2.readLine(), -1);
                            this.c = bufferedReader2.readLine();
                            this.e = u1.stringToLong(bufferedReader2.readLine(), -1L);
                            this.f = u1.stringToLong(bufferedReader2.readLine(), -1L);
                            this.g = bufferedReader2.readLine();
                            Headers.Builder builder = new Headers.Builder();
                            int stringToInteger = u1.stringToInteger(bufferedReader2.readLine(), -1);
                            for (int i = 0; i < stringToInteger; i++) {
                                String readLine = bufferedReader2.readLine();
                                if (readLine != null) {
                                    builder.addLenient(readLine);
                                }
                            }
                            this.d = builder.build();
                            IoUtils.closeSecure((Reader) bufferedReader2);
                            IoUtils.closeSecure((Reader) inputStreamReader);
                            IoUtils.closeSecure((InputStream) fileInputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            IoUtils.closeSecure((Reader) bufferedReader);
                            IoUtils.closeSecure((Reader) inputStreamReader);
                            IoUtils.closeSecure((InputStream) fileInputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader = null;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader = null;
                fileInputStream = null;
            }
        }

        public c(r1.c cVar) {
            this.f5386a = s1.key(cVar.c().getUrl());
            this.b = cVar.d().getCode();
            this.c = cVar.d().getMessage();
            this.d = Headers.of(cVar.d().getHeaders());
            this.e = cVar.f();
            this.f = cVar.a();
            this.g = cVar.d().getUrl();
        }
    }

    @Override // com.huawei.hms.network.embedded.v1
    public void update(r1.c cVar) {
        a.c cVar2;
        c cVar3 = new c(cVar);
        try {
            a.e c2 = this.f5383a.c(s1.key(cVar.c().getUrl()));
            if (c2 == null) {
                return;
            }
            try {
                cVar2 = c2.a();
                if (cVar2 != null) {
                    try {
                        cVar3.a(cVar2);
                        cVar2.c();
                    } catch (IOException unused) {
                        a(cVar2);
                    }
                }
            } catch (IOException unused2) {
                cVar2 = null;
            }
        } catch (IOException unused3) {
        }
    }

    @Override // com.huawei.hms.network.embedded.v1
    public void remove(c1 c1Var) {
        if (c1Var == null) {
            return;
        }
        String key = s1.key(c1Var.getUrl());
        if (TextUtils.isEmpty(key)) {
            return;
        }
        try {
            this.f5383a.d(key);
        } catch (IOException unused) {
            Logger.e(b, "remove cached files of the request failed.");
        }
    }

    @Override // com.huawei.hms.network.embedded.v1
    public o1 put(r1.c cVar) {
        a.c cVar2;
        String key = s1.key(cVar.c().getUrl());
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        c cVar3 = new c(cVar);
        try {
            cVar2 = this.f5383a.b(key);
            if (cVar2 == null) {
                return null;
            }
            try {
                cVar3.a(cVar2);
                return new b(cVar2);
            } catch (IOException unused) {
                a(cVar2);
                return null;
            }
        } catch (IOException unused2) {
            cVar2 = null;
        }
    }

    public boolean isInvalid() {
        return this.f5383a == null;
    }

    @Override // com.huawei.hms.network.embedded.v1
    public r1.c get(Request request) {
        String key = s1.key(request.getUrl());
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        try {
            a.e c2 = this.f5383a.c(key);
            if (c2 == null) {
                return null;
            }
            c cVar = new c(c2.a(0));
            r1.c response = cVar.response(request, this.f5383a, c2);
            if (cVar.a(request)) {
                return response;
            }
            IoUtils.closeSecure(response.d().getBody());
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    private void a(a.c cVar) {
        if (cVar != null) {
            try {
                cVar.a();
            } catch (IOException unused) {
                Logger.w(b, "Terminating the cached file failed !");
            }
        }
    }

    public n1(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File newFile = CreateFileUtil.newFile(ContextHolder.getAppContext().getCacheDir().getPath() + File.separator + str);
        try {
            this.f5383a = com.huawei.hms.network.embedded.a.a(newFile.getCanonicalFile(), 80001307, 2, j);
        } catch (IOException unused) {
            CreateFileUtil.deleteSecure(newFile);
            Logger.w(b, "DiskLruCache failed to create.");
            this.f5383a = null;
        }
    }
}
