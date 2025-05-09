package com.amap.api.col.p0003sl;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class jq implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    static final Pattern f1220a = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final Charset b = Charset.forName("US-ASCII");
    static final Charset c = Charset.forName("UTF-8");
    static ThreadPoolExecutor d;
    private static final ThreadFactory r;
    private static final OutputStream t;
    private final File e;
    private final File f;
    private final File g;
    private final File h;
    private long j;
    private Writer m;
    private int p;
    private long l = 0;
    private int n = 1000;
    private final LinkedHashMap<String, c> o = new LinkedHashMap<>(0, 0.75f, true);
    private long q = 0;
    private final Callable<Void> s = new Callable<Void>() { // from class: com.amap.api.col.3sl.jq.2
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (jq.this) {
                if (jq.this.m == null) {
                    return null;
                }
                jq.this.l();
                if (jq.this.j()) {
                    jq.this.i();
                    jq.e(jq.this);
                }
                return null;
            }
        }
    };
    private final int i = 1;
    private final int k = 1;

    static /* synthetic */ int e(jq jqVar) {
        jqVar.p = 0;
        return 0;
    }

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.amap.api.col.3sl.jq.1

            /* renamed from: a, reason: collision with root package name */
            private final AtomicInteger f1221a = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, "disklrucache#" + this.f1221a.getAndIncrement());
            }
        };
        r = threadFactory;
        d = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        t = new OutputStream() { // from class: com.amap.api.col.3sl.jq.3
            @Override // java.io.OutputStream
            public final void write(int i) throws IOException {
            }
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0008, code lost:
    
        if (r2 > 10000) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(int r2) {
        /*
            r1 = this;
            r0 = 10
            if (r2 >= r0) goto L6
        L4:
            r2 = r0
            goto Lb
        L6:
            r0 = 10000(0x2710, float:1.4013E-41)
            if (r2 <= r0) goto Lb
            goto L4
        Lb:
            r1.n = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jq.a(int):void");
    }

    public static void a() {
        ThreadPoolExecutor threadPoolExecutor = d;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        d.shutdown();
    }

    private static ThreadPoolExecutor f() {
        try {
            ThreadPoolExecutor threadPoolExecutor = d;
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
                d = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), r);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return d;
    }

    private jq(File file, long j) {
        this.e = file;
        this.f = new File(file, "journal");
        this.g = new File(file, "journal.tmp");
        this.h = new File(file, "journal.bkp");
        this.j = j;
    }

    public static jq a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        jq jqVar = new jq(file, j);
        if (jqVar.f.exists()) {
            try {
                jqVar.g();
                jqVar.h();
                jqVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jqVar.f, true), b));
                return jqVar;
            } catch (Throwable unused) {
                jqVar.d();
            }
        }
        file.mkdirs();
        jq jqVar2 = new jq(file, j);
        jqVar2.i();
        return jqVar2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f1, code lost:
    
        throw new java.io.IOException("unexpected journal line: ".concat(java.lang.String.valueOf(r3)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.jq.g():void");
    }

    private void h() throws IOException {
        a(this.g);
        Iterator<c> it = this.o.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.e == null) {
                while (i < this.k) {
                    this.l += next.c[i];
                    i++;
                }
            } else {
                next.e = null;
                while (i < this.k) {
                    a(next.a(i));
                    a(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() throws IOException {
        synchronized (this) {
            Writer writer = this.m;
            if (writer != null) {
                writer.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.g), b));
            try {
                bufferedWriter.write("libcore.io.DiskLruCache");
                bufferedWriter.write("\n");
                bufferedWriter.write("1");
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.i));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.k));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (c cVar : this.o.values()) {
                    if (cVar.e != null) {
                        bufferedWriter.write("DIRTY " + cVar.b + '\n');
                    } else {
                        bufferedWriter.write("CLEAN " + cVar.b + cVar.a() + '\n');
                    }
                }
                bufferedWriter.close();
                if (this.f.exists()) {
                    a(this.f, this.h, true);
                }
                a(this.g, this.f, false);
                this.h.delete();
                this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f, true), b));
            } catch (Throwable th) {
                bufferedWriter.close();
                throw th;
            }
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public final b a(String str) throws IOException {
        InputStream inputStream;
        synchronized (this) {
            k();
            e(str);
            c cVar = this.o.get(str);
            if (cVar == null) {
                return null;
            }
            if (!cVar.d) {
                return null;
            }
            InputStream[] inputStreamArr = new InputStream[this.k];
            for (int i = 0; i < this.k; i++) {
                try {
                    inputStreamArr[i] = new FileInputStream(cVar.a(i));
                } catch (FileNotFoundException unused) {
                    for (int i2 = 0; i2 < this.k && (inputStream = inputStreamArr[i2]) != null; i2++) {
                        a(inputStream);
                    }
                    return null;
                }
            }
            this.p++;
            this.m.append((CharSequence) ("READ " + str + '\n'));
            if (j()) {
                f().submit(this.s);
            }
            return new b(this, str, cVar.f, inputStreamArr, cVar.c, (byte) 0);
        }
    }

    public final a b(String str) throws IOException {
        return d(str);
    }

    private a d(String str) throws IOException {
        synchronized (this) {
            k();
            e(str);
            c cVar = this.o.get(str);
            byte b2 = 0;
            if (cVar == null) {
                cVar = new c(this, str, b2);
                this.o.put(str, cVar);
            } else if (cVar.e != null) {
                return null;
            }
            a aVar = new a(this, cVar, b2);
            cVar.e = aVar;
            this.m.write("DIRTY " + str + '\n');
            this.m.flush();
            return aVar;
        }
    }

    public final File b() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar, boolean z) throws IOException {
        synchronized (this) {
            c cVar = aVar.b;
            if (cVar.e != aVar) {
                throw new IllegalStateException();
            }
            if (z && !cVar.d) {
                for (int i = 0; i < this.k; i++) {
                    if (!aVar.c[i]) {
                        aVar.c();
                        throw new IllegalStateException("Newly created entry didn't create value for index ".concat(String.valueOf(i)));
                    }
                    if (!cVar.b(i).exists()) {
                        aVar.c();
                        return;
                    }
                }
            }
            for (int i2 = 0; i2 < this.k; i2++) {
                File b2 = cVar.b(i2);
                if (z) {
                    if (b2.exists()) {
                        File a2 = cVar.a(i2);
                        b2.renameTo(a2);
                        long j = cVar.c[i2];
                        long length = a2.length();
                        cVar.c[i2] = length;
                        this.l = (this.l - j) + length;
                    }
                } else {
                    a(b2);
                }
            }
            this.p++;
            cVar.e = null;
            if (cVar.d | z) {
                c.a(cVar);
                this.m.write("CLEAN " + cVar.b + cVar.a() + '\n');
                if (z) {
                    long j2 = this.q;
                    this.q = 1 + j2;
                    cVar.f = j2;
                }
            } else {
                this.o.remove(cVar.b);
                this.m.write("REMOVE " + cVar.b + '\n');
            }
            this.m.flush();
            if (this.l > this.j || j()) {
                f().submit(this.s);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        int i = this.p;
        return i >= 2000 && i >= this.o.size();
    }

    public final boolean c(String str) throws IOException {
        synchronized (this) {
            k();
            e(str);
            c cVar = this.o.get(str);
            if (cVar != null && cVar.e == null) {
                for (int i = 0; i < this.k; i++) {
                    File a2 = cVar.a(i);
                    if (a2.exists() && !a2.delete()) {
                        throw new IOException("failed to delete ".concat(String.valueOf(a2)));
                    }
                    this.l -= cVar.c[i];
                    cVar.c[i] = 0;
                }
                this.p++;
                this.m.append((CharSequence) ("REMOVE " + str + '\n'));
                this.o.remove(str);
                if (j()) {
                    f().submit(this.s);
                }
                return true;
            }
            return false;
        }
    }

    private void k() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final void c() throws IOException {
        synchronized (this) {
            k();
            l();
            this.m.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this) {
            if (this.m == null) {
                return;
            }
            Iterator it = new ArrayList(this.o.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.c();
                }
            }
            l();
            this.m.close();
            this.m = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws IOException {
        while (true) {
            if (this.l <= this.j && this.o.size() <= this.n) {
                return;
            } else {
                c(this.o.entrySet().iterator().next().getKey());
            }
        }
    }

    public final void d() throws IOException {
        close();
        b(this.e);
    }

    private static void e(String str) {
        if (f1220a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        /* synthetic */ b(jq jqVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(str, j, inputStreamArr, jArr);
        }

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public final InputStream a() {
            return this.d[0];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            for (InputStream inputStream : this.d) {
                jq.a(inputStream);
            }
        }
    }

    public final class a {
        private final c b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* synthetic */ a(jq jqVar, c cVar, byte b) {
            this(cVar);
        }

        static /* synthetic */ boolean c(a aVar) {
            aVar.d = true;
            return true;
        }

        private a(c cVar) {
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[jq.this.k];
        }

        public final OutputStream a() throws IOException {
            FileOutputStream fileOutputStream;
            C0023a c0023a;
            if (jq.this.k <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + jq.this.k);
            }
            synchronized (jq.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                byte b = 0;
                if (!this.b.d) {
                    this.c[0] = true;
                }
                File b2 = this.b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(b2);
                } catch (FileNotFoundException unused) {
                    jq.this.e.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(b2);
                    } catch (FileNotFoundException unused2) {
                        return jq.t;
                    }
                }
                c0023a = new C0023a(this, fileOutputStream, b);
            }
            return c0023a;
        }

        public final void b() throws IOException {
            if (this.d) {
                jq.this.a(this, false);
                jq.this.c(this.b.b);
            } else {
                jq.this.a(this, true);
            }
            this.e = true;
        }

        public final void c() throws IOException {
            jq.this.a(this, false);
        }

        /* renamed from: com.amap.api.col.3sl.jq$a$a, reason: collision with other inner class name */
        final class C0023a extends FilterOutputStream {
            /* synthetic */ C0023a(a aVar, OutputStream outputStream, byte b) {
                this(outputStream);
            }

            private C0023a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }
        }
    }

    private static void b(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: ".concat(String.valueOf(file)));
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: ".concat(String.valueOf(file2)));
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    final class c {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        /* synthetic */ c(jq jqVar, String str, byte b) {
            this(str);
        }

        static /* synthetic */ boolean a(c cVar) {
            cVar.d = true;
            return true;
        }

        private c(String str) {
            this.b = str;
            this.c = new long[jq.this.k];
        }

        public final String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        private static IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public final File a(int i) {
            return new File(jq.this.e, this.b + "." + i);
        }

        public final File b(int i) {
            return new File(jq.this.e, this.b + "." + i + ".tmp");
        }

        static /* synthetic */ void a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != jq.this.k) {
                throw a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    cVar.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw a(strArr);
                }
            }
        }
    }
}
