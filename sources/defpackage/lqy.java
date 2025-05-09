package defpackage;

import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes5.dex */
public final class lqy implements Closeable, Flushable {
    static final Pattern d = Pattern.compile("[a-z0-9_-]{1,120}");

    /* renamed from: a, reason: collision with root package name */
    final File f14842a;
    final int b;
    BufferedSink c;
    boolean e;
    final lqw f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    private final File k;
    int m;
    private final int n;
    private final File p;
    private final Executor q;
    private final long r;
    private final File x;
    private long t = 0;
    final LinkedHashMap<String, e> l = new LinkedHashMap<>(0, 0.75f, true);
    private long s = 0;
    private final Runnable o = new c();

    lqy(lqw lqwVar, File file, int i, int i2, long j, Executor executor) {
        this.p = new File(file, "health-journal");
        this.f = lqwVar;
        this.x = new File(file, "health-journal.tmp");
        this.f14842a = file;
        this.k = new File(file, "health-journal.bkp");
        this.n = i;
        this.b = i2;
        this.r = j;
        this.q = executor;
    }

    public static lqy c(lqw lqwVar, File file, int i, int i2, long j) {
        if (j <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0 or valueCount <= 0");
        }
        return new lqy(lqwVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: lqx
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return lqy.c(runnable);
            }
        }));
    }

    static /* synthetic */ Thread c(Runnable runnable) {
        Thread thread = new Thread(runnable, "HealthDiskLruCache");
        thread.setDaemon(true);
        return thread;
    }

    private BufferedSink h() throws FileNotFoundException {
        return Okio.buffer(new lqv(b(this.p)) { // from class: lqy.5
            @Override // defpackage.lqv
            protected void a(IOException iOException) {
                lqy.this.i = true;
            }
        });
    }

    private void f() throws IOException {
        this.f.a(this.x);
        Iterator<e> it = this.l.values().iterator();
        while (it.hasNext()) {
            e next = it.next();
            int i = 0;
            if (next.b == null) {
                while (i < this.b) {
                    this.t += next.c[i];
                    i++;
                }
            } else {
                next.b = null;
                while (i < this.b) {
                    this.f.a(next.f14844a[i]);
                    this.f.a(next.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    public d d(String str) throws IOException {
        synchronized (this) {
            j();
            g();
            b(str);
            e eVar = this.l.get(str);
            if (eVar != null && eVar.h) {
                d c2 = eVar.c();
                if (c2 == null) {
                    return null;
                }
                this.m++;
                this.c.writeUtf8("READ").writeByte(32).writeUtf8(str).writeByte(10);
                if (a()) {
                    this.q.execute(this.o);
                }
                return c2;
            }
            return null;
        }
    }

    public b a(String str) throws IOException {
        return a(str, -1L);
    }

    void e() throws IOException {
        synchronized (this) {
            BufferedSink bufferedSink = this.c;
            if (bufferedSink != null) {
                bufferedSink.close();
            }
            BufferedSink buffer = Okio.buffer(this.f.d(this.x));
            try {
                buffer.writeUtf8("libcore.io.HealthDiskLruCache").writeByte(10);
                buffer.writeUtf8("1").writeByte(10);
                buffer.writeDecimalLong(this.n).writeByte(10);
                buffer.writeDecimalLong(this.b).writeByte(10);
                buffer.writeByte(10);
                for (e eVar : this.l.values()) {
                    if (eVar.b != null) {
                        buffer.writeUtf8("DIRTY").writeByte(32);
                        buffer.writeUtf8(eVar.e);
                        buffer.writeByte(10);
                    } else {
                        buffer.writeUtf8("CLEAN").writeByte(32);
                        buffer.writeUtf8(eVar.e);
                        eVar.a(buffer);
                        buffer.writeByte(10);
                    }
                }
                if (buffer != null) {
                    buffer.close();
                }
                if (this.p.exists()) {
                    this.f.c(this.p, this.k);
                }
                this.f.c(this.x, this.p);
                this.f.a(this.k);
                this.c = h();
                this.i = false;
                this.g = false;
            } finally {
            }
        }
    }

    b a(String str, long j) throws IOException {
        synchronized (this) {
            j();
            g();
            b(str);
            e eVar = this.l.get(str);
            if (j != -1 && (eVar == null || eVar.j != j)) {
                return null;
            }
            if (eVar != null && eVar.b != null) {
                return null;
            }
            if (!this.j && !this.g) {
                this.c.writeUtf8("DIRTY").writeByte(32).writeUtf8(str).writeByte(10);
                this.c.flush();
                if (this.i) {
                    return null;
                }
                if (eVar == null) {
                    eVar = new e(str);
                    this.l.put(str, eVar);
                }
                b bVar = new b(eVar);
                eVar.b = bVar;
                return bVar;
            }
            this.q.execute(this.o);
            return null;
        }
    }

    void e(b bVar, boolean z) throws IOException {
        synchronized (this) {
            e eVar = bVar.e;
            if (eVar.b != bVar) {
                throw new IllegalStateException();
            }
            if (z && !eVar.h) {
                for (int i = 0; i < this.b; i++) {
                    if (!bVar.c[i]) {
                        bVar.d();
                        throw new IllegalStateException("Newly created innerEntry didn't create value for index " + i);
                    }
                    if (!eVar.d[i].exists()) {
                        bVar.d();
                        return;
                    }
                }
            }
            for (int i2 = 0; i2 < this.b; i2++) {
                File file = eVar.d[i2];
                if (z) {
                    if (file.exists()) {
                        File file2 = eVar.f14844a[i2];
                        this.f.c(file, file2);
                        long j = eVar.c[i2];
                        long length = file2.length();
                        eVar.c[i2] = length;
                        this.t = (this.t - j) + length;
                    }
                } else {
                    this.f.a(file);
                }
            }
            this.m++;
            eVar.b = null;
            if (eVar.h | z) {
                eVar.h = true;
                this.c.writeUtf8("CLEAN").writeByte(32);
                this.c.writeUtf8(eVar.e);
                eVar.a(this.c);
                this.c.writeByte(10);
                if (z) {
                    long j2 = this.s;
                    this.s = 1 + j2;
                    eVar.j = j2;
                }
            } else {
                this.l.remove(eVar.e);
                this.c.writeUtf8("REMOVE").writeByte(32);
                this.c.writeUtf8(eVar.e);
                this.c.writeByte(10);
            }
            this.c.flush();
            if (this.t > this.r || a()) {
                this.q.execute(this.o);
            }
        }
    }

    boolean a() {
        int i = this.m;
        return i >= 2000 && i >= this.l.size();
    }

    public boolean e(String str) throws IOException {
        synchronized (this) {
            j();
            g();
            b(str);
            e eVar = this.l.get(str);
            if (eVar == null) {
                return false;
            }
            boolean b2 = b(eVar);
            if (b2 && this.t <= this.r) {
                this.j = false;
            }
            return b2;
        }
    }

    boolean b(e eVar) throws IOException {
        if (eVar.b != null) {
            eVar.b.a();
        }
        for (int i = 0; i < this.b; i++) {
            this.f.a(eVar.f14844a[i]);
            this.t -= eVar.c[i];
            eVar.c[i] = 0;
        }
        this.m++;
        this.c.writeUtf8("REMOVE").writeByte(32).writeUtf8(eVar.e).writeByte(10);
        this.l.remove(eVar.e);
        if (a()) {
            this.q.execute(this.o);
        }
        return true;
    }

    private void g() {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("cache is closed");
            }
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        synchronized (this) {
            if (this.h) {
                g();
                d();
                this.c.flush();
            }
        }
    }

    public boolean c() {
        boolean z;
        synchronized (this) {
            z = this.e;
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            if (this.h && !this.e) {
                for (e eVar : (e[]) this.l.values().toArray(new e[this.l.size()])) {
                    if (eVar.b != null) {
                        eVar.b.d();
                    }
                }
                d();
                this.c.close();
                this.c = null;
                this.e = true;
                return;
            }
            this.e = true;
        }
    }

    void d() throws IOException {
        while (this.t > this.r) {
            b(this.l.values().iterator().next());
        }
        this.j = false;
    }

    public void b() throws IOException {
        close();
        this.f.e(this.f14842a);
    }

    private void j() throws IOException {
        synchronized (this) {
            if (this.h) {
                return;
            }
            if (this.k.exists()) {
                if (this.p.exists()) {
                    this.f.a(this.k);
                } else {
                    this.f.c(this.k, this.p);
                }
            }
            if (this.p.exists()) {
                try {
                    i();
                    f();
                    this.h = true;
                    return;
                } catch (IOException e2) {
                    LogUtil.a("HealthDiskLruCache", "initialize failed, e: " + e2);
                    try {
                        b();
                        this.e = false;
                    } catch (Throwable th) {
                        this.e = false;
                        throw th;
                    }
                }
            }
            e();
            this.h = true;
        }
    }

    private void b(String str) {
        if (d.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    private void i() throws IOException {
        BufferedSource buffer = Okio.buffer(this.f.b(this.p));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if (!"libcore.io.HealthDiskLruCache".equals(readUtf8LineStrict) || !"1".equals(readUtf8LineStrict2) || !Integer.toString(this.n).equals(readUtf8LineStrict3) || !Integer.toString(this.b).equals(readUtf8LineStrict4) || !"".equals(readUtf8LineStrict5)) {
                throw new IOException("wrong journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    c(buffer.readUtf8LineStrict());
                    i++;
                } catch (EOFException unused) {
                    this.m = i - this.l.size();
                    if (!buffer.exhausted()) {
                        e();
                    } else {
                        this.c = h();
                    }
                    if (buffer != null) {
                        buffer.close();
                        return;
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public final class d implements Closeable {
        private final long[] b;
        private final Source[] d;

        d(String str, long j, Source[] sourceArr, long[] jArr) {
            this.d = sourceArr;
            this.b = jArr;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.d) {
                lqy.d(source);
            }
        }

        public Source a(int i) {
            return this.d[i];
        }
    }

    public final class b {
        private boolean b;
        final boolean[] c;
        final e e;

        b(e eVar) {
            this.e = eVar;
            this.c = eVar.h ? null : new boolean[lqy.this.b];
        }

        void a() {
            if (this.e.b == this) {
                for (int i = 0; i < lqy.this.b; i++) {
                    try {
                        lqy.this.f.a(this.e.d[i]);
                    } catch (IOException unused) {
                    }
                }
                this.e.b = null;
            }
        }

        public void b() throws IOException {
            synchronized (lqy.this) {
                if (this.b) {
                    throw new IllegalStateException();
                }
                if (this.e.b == this) {
                    lqy.this.e(this, true);
                }
                this.b = true;
            }
        }

        public void d() throws IOException {
            synchronized (lqy.this) {
                if (this.b) {
                    throw new IllegalStateException();
                }
                if (this.e.b == this) {
                    lqy.this.e(this, false);
                }
                this.b = true;
            }
        }

        public Sink b(int i) {
            synchronized (lqy.this) {
                if (this.b) {
                    throw new IllegalStateException();
                }
                if (this.e.b != this) {
                    return Okio.blackhole();
                }
                if (!this.e.h) {
                    this.c[i] = true;
                }
                try {
                    return new lqv(lqy.this.f.d(this.e.d[i])) { // from class: lqy.b.1
                        @Override // defpackage.lqv
                        protected void a(IOException iOException) {
                            synchronized (lqy.this) {
                                b.this.a();
                            }
                        }
                    };
                } catch (FileNotFoundException unused) {
                    return Okio.blackhole();
                }
            }
        }
    }

    private void c(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            LogUtil.e("HealthDiskLruCache", "readJournalInLine failed, no space in line ");
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            LogUtil.c("HealthDiskLruCache", "only one key found");
            substring = str.substring(i);
            if (indexOf == 6 && str.startsWith("REMOVE")) {
                this.l.remove(substring);
                return;
            }
        } else {
            substring = str.substring(i, indexOf2);
        }
        e eVar = this.l.get(substring);
        if (eVar == null) {
            eVar = new e(substring);
            this.l.put(substring, eVar);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            eVar.h = true;
            eVar.b = null;
            eVar.e(split);
            return;
        }
        if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
            eVar.b = new b(eVar);
            return;
        }
        if (indexOf2 == -1 && indexOf == 4 && str.startsWith("READ")) {
            LogUtil.c("HealthDiskLruCache", "");
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    final class e {

        /* renamed from: a, reason: collision with root package name */
        final File[] f14844a;
        b b;
        final long[] c;
        final File[] d;
        final String e;
        boolean h;
        long j;

        e(String str) {
            this.e = str;
            this.c = new long[lqy.this.b];
            this.f14844a = new File[lqy.this.b];
            this.d = new File[lqy.this.b];
            StringBuilder sb = new StringBuilder(str);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            int length = sb.length();
            for (int i = 0; i < lqy.this.b; i++) {
                sb.append(i);
                this.f14844a[i] = new File(lqy.this.f14842a, sb.toString());
                sb.append(".tmp");
                this.d[i] = new File(lqy.this.f14842a, sb.toString());
                sb.setLength(length);
            }
        }

        void a(BufferedSink bufferedSink) throws IOException {
            for (long j : this.c) {
                bufferedSink.writeByte(32).writeDecimalLong(j);
            }
        }

        private IOException c(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        d c() {
            Source source;
            if (!Thread.holdsLock(lqy.this)) {
                throw new AssertionError();
            }
            Source[] sourceArr = new Source[lqy.this.b];
            long[] jArr = (long[]) this.c.clone();
            for (int i = 0; i < lqy.this.b; i++) {
                try {
                    sourceArr[i] = lqy.this.f.b(this.f14844a[i]);
                } catch (FileNotFoundException unused) {
                    for (int i2 = 0; i2 < lqy.this.b && (source = sourceArr[i2]) != null; i2++) {
                        lqy.d(source);
                    }
                    try {
                        lqy.this.b(this);
                        return null;
                    } catch (IOException e) {
                        LogUtil.e("HealthDiskLruCache", "snapshot() failed, e:" + e);
                        return null;
                    }
                }
            }
            return lqy.this.new d(this.e, this.j, sourceArr, jArr);
        }

        void e(String[] strArr) throws IOException {
            if (strArr.length != lqy.this.b) {
                LogUtil.e("HealthDiskLruCache", "count not equals");
                throw c(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    LogUtil.e("HealthDiskLruCache", "NumberFormatException, s:" + strArr);
                    throw c(strArr);
                }
            }
        }
    }

    static void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (lqy.this) {
                if ((!lqy.this.h) || lqy.this.e) {
                    return;
                }
                try {
                    lqy.this.d();
                } catch (IOException unused) {
                    lqy.this.j = true;
                }
                try {
                    if (lqy.this.a()) {
                        lqy.this.e();
                        lqy.this.m = 0;
                    }
                } catch (IOException unused2) {
                    lqy.this.g = true;
                    lqy.this.c = Okio.buffer(Okio.blackhole());
                }
            }
        }
    }

    Sink b(File file) throws FileNotFoundException {
        try {
            return Okio.appendingSink(file);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return Okio.appendingSink(file);
        }
    }
}
