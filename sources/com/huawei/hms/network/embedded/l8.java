package com.huawei.hms.network.embedded;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes9.dex */
public final class l8 implements Closeable, Flushable {
    public static final Pattern A = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final String B = "CLEAN";
    public static final String C = "DIRTY";
    public static final String D = "REMOVE";
    public static final String E = "READ";
    public static final /* synthetic */ boolean F = true;
    public static final String u = "journal";
    public static final String v = "journal.tmp";
    public static final String w = "journal.bkp";
    public static final String x = "libcore.io.DiskLruCache";
    public static final String y = "1";
    public static final long z = -1;

    /* renamed from: a, reason: collision with root package name */
    public final ea f5364a;
    public final File b;
    public final File c;
    public final File d;
    public final File e;
    public final int f;
    public long g;
    public final int h;
    public cb j;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public final Executor s;
    public long i = 0;
    public final LinkedHashMap<String, e> k = new LinkedHashMap<>(0, 0.75f, true);
    public long r = 0;
    public final Runnable t = new a();

    public void z() throws IOException {
        synchronized (this) {
            cb cbVar = this.j;
            if (cbVar != null) {
                cbVar.close();
            }
            cb a2 = ob.a(this.f5364a.e(this.d));
            try {
                a2.a("libcore.io.DiskLruCache").writeByte(10);
                a2.a("1").writeByte(10);
                a2.a(this.f).writeByte(10);
                a2.a(this.h).writeByte(10);
                a2.writeByte(10);
                for (e eVar : this.k.values()) {
                    if (eVar.f != null) {
                        a2.a("DIRTY").writeByte(32);
                        a2.a(eVar.f5368a);
                    } else {
                        a2.a("CLEAN").writeByte(32);
                        a2.a(eVar.f5368a);
                        eVar.a(a2);
                    }
                    a2.writeByte(10);
                }
                if (a2 != null) {
                    a2.close();
                }
                if (this.f5364a.a(this.c)) {
                    this.f5364a.a(this.c, this.e);
                }
                this.f5364a.a(this.d, this.c);
                this.f5364a.b(this.e);
                this.j = E();
                this.m = false;
                this.q = false;
            } finally {
            }
        }
    }

    public boolean y() {
        int i = this.l;
        return i >= 2000 && i >= this.k.size();
    }

    public boolean x() {
        boolean z2;
        synchronized (this) {
            z2 = this.o;
        }
        return z2;
    }

    public void w() throws IOException {
        synchronized (this) {
            if (!F && !Thread.holdsLock(this)) {
                throw new AssertionError();
            }
            if (this.n) {
                return;
            }
            if (this.f5364a.a(this.e)) {
                if (this.f5364a.a(this.c)) {
                    this.f5364a.b(this.e);
                } else {
                    this.f5364a.a(this.e, this.c);
                }
            }
            if (this.f5364a.a(this.c)) {
                try {
                    G();
                    F();
                    this.n = true;
                    return;
                } catch (IOException e2) {
                    ma.f().a(5, "DiskLruCache " + this.b + " is corrupt: " + e2.getMessage() + ", removing", e2);
                    try {
                        s();
                        this.o = false;
                    } catch (Throwable th) {
                        this.o = false;
                        throw th;
                    }
                }
            }
            z();
            this.n = true;
        }
    }

    public long v() {
        long j;
        synchronized (this) {
            j = this.g;
        }
        return j;
    }

    public File u() {
        return this.b;
    }

    public void t() throws IOException {
        synchronized (this) {
            w();
            for (e eVar : (e[]) this.k.values().toArray(new e[this.k.size()])) {
                a(eVar);
            }
            this.p = false;
        }
    }

    public void s() throws IOException {
        close();
        this.f5364a.f(this.b);
    }

    public void j(long j) {
        synchronized (this) {
            this.g = j;
            if (this.n) {
                this.s.execute(this.t);
            }
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        synchronized (this) {
            if (this.n) {
                D();
                C();
                this.j.flush();
            }
        }
    }

    public boolean d(String str) throws IOException {
        synchronized (this) {
            w();
            D();
            f(str);
            e eVar = this.k.get(str);
            if (eVar == null) {
                return false;
            }
            boolean a2 = a(eVar);
            if (a2 && this.i <= this.g) {
                this.p = false;
            }
            return a2;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            if (this.n && !this.o) {
                for (e eVar : (e[]) this.k.values().toArray(new e[this.k.size()])) {
                    d dVar = eVar.f;
                    if (dVar != null) {
                        dVar.a();
                    }
                }
                C();
                this.j.close();
                this.j = null;
                this.o = true;
                return;
            }
            this.o = true;
        }
    }

    public f c(String str) throws IOException {
        synchronized (this) {
            w();
            D();
            f(str);
            e eVar = this.k.get(str);
            if (eVar != null && eVar.e) {
                f a2 = eVar.a();
                if (a2 == null) {
                    return null;
                }
                this.l++;
                this.j.a("READ").writeByte(32).a(str).writeByte(10);
                if (y()) {
                    this.s.execute(this.t);
                }
                return a2;
            }
            return null;
        }
    }

    @Nullable
    public d b(String str) throws IOException {
        return a(str, -1L);
    }

    public boolean a(e eVar) throws IOException {
        d dVar = eVar.f;
        if (dVar != null) {
            dVar.d();
        }
        for (int i = 0; i < this.h; i++) {
            this.f5364a.b(eVar.c[i]);
            long j = this.i;
            long[] jArr = eVar.b;
            this.i = j - jArr[i];
            jArr[i] = 0;
        }
        this.l++;
        this.j.a("REMOVE").writeByte(32).a(eVar.f5368a).writeByte(10);
        this.k.remove(eVar.f5368a);
        if (y()) {
            this.s.execute(this.t);
        }
        return true;
    }

    public void a(d dVar, boolean z2) throws IOException {
        synchronized (this) {
            e eVar = dVar.f5367a;
            if (eVar.f != dVar) {
                throw new IllegalStateException();
            }
            if (z2 && !eVar.e) {
                for (int i = 0; i < this.h; i++) {
                    if (!dVar.b[i]) {
                        dVar.a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                    if (!this.f5364a.a(eVar.d[i])) {
                        dVar.a();
                        return;
                    }
                }
            }
            for (int i2 = 0; i2 < this.h; i2++) {
                File file = eVar.d[i2];
                if (!z2) {
                    this.f5364a.b(file);
                } else if (this.f5364a.a(file)) {
                    File file2 = eVar.c[i2];
                    this.f5364a.a(file, file2);
                    long j = eVar.b[i2];
                    long d2 = this.f5364a.d(file2);
                    eVar.b[i2] = d2;
                    this.i = (this.i - j) + d2;
                }
            }
            this.l++;
            eVar.f = null;
            if (eVar.e || z2) {
                eVar.e = true;
                this.j.a("CLEAN").writeByte(32);
                this.j.a(eVar.f5368a);
                eVar.a(this.j);
                this.j.writeByte(10);
                if (z2) {
                    long j2 = this.r;
                    this.r = 1 + j2;
                    eVar.g = j2;
                }
            } else {
                this.k.remove(eVar.f5368a);
                this.j.a("REMOVE").writeByte(32);
                this.j.a(eVar.f5368a);
                this.j.writeByte(10);
            }
            this.j.flush();
            if (this.i > this.g || y()) {
                this.s.execute(this.t);
            }
        }
    }

    public d a(String str, long j) throws IOException {
        synchronized (this) {
            w();
            D();
            f(str);
            e eVar = this.k.get(str);
            if (j != -1 && (eVar == null || eVar.g != j)) {
                return null;
            }
            if (eVar != null && eVar.f != null) {
                return null;
            }
            if (!this.p && !this.q) {
                this.j.a("DIRTY").writeByte(32).a(str).writeByte(10);
                this.j.flush();
                if (this.m) {
                    return null;
                }
                if (eVar == null) {
                    eVar = new e(str);
                    this.k.put(str, eVar);
                }
                d dVar = new d(eVar);
                eVar.f = dVar;
                return dVar;
            }
            this.s.execute(this.t);
            return null;
        }
    }

    public void C() throws IOException {
        while (this.i > this.g) {
            a(this.k.values().iterator().next());
        }
        this.p = false;
    }

    public Iterator<f> B() throws IOException {
        c cVar;
        synchronized (this) {
            w();
            cVar = new c();
        }
        return cVar;
    }

    public long A() throws IOException {
        long j;
        synchronized (this) {
            w();
            j = this.i;
        }
        return j;
    }

    private void f(String str) {
        if (A.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public final class d {

        /* renamed from: a, reason: collision with root package name */
        public final e f5367a;
        public final boolean[] b;
        public boolean c;

        public void d() {
            if (this.f5367a.f != this) {
                return;
            }
            int i = 0;
            while (true) {
                l8 l8Var = l8.this;
                if (i >= l8Var.h) {
                    this.f5367a.f = null;
                    return;
                } else {
                    try {
                        l8Var.f5364a.b(this.f5367a.d[i]);
                    } catch (IOException unused) {
                    }
                    i++;
                }
            }
        }

        public void c() throws IOException {
            synchronized (l8.this) {
                if (this.c) {
                    throw new IllegalStateException();
                }
                if (this.f5367a.f == this) {
                    l8.this.a(this, true);
                }
                this.c = true;
            }
        }

        public void b() {
            synchronized (l8.this) {
                if (!this.c && this.f5367a.f == this) {
                    try {
                        l8.this.a(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public zb b(int i) {
            synchronized (l8.this) {
                if (this.c) {
                    throw new IllegalStateException();
                }
                if (!this.f5367a.e || this.f5367a.f != this) {
                    return null;
                }
                try {
                    return l8.this.f5364a.c(this.f5367a.c[i]);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        public class a extends m8 {
            @Override // com.huawei.hms.network.embedded.m8
            public void a(IOException iOException) {
                synchronized (l8.this) {
                    d.this.d();
                }
            }

            public a(yb ybVar) {
                super(ybVar);
            }
        }

        public void a() throws IOException {
            synchronized (l8.this) {
                if (this.c) {
                    throw new IllegalStateException();
                }
                if (this.f5367a.f == this) {
                    l8.this.a(this, false);
                }
                this.c = true;
            }
        }

        public yb a(int i) {
            synchronized (l8.this) {
                if (this.c) {
                    throw new IllegalStateException();
                }
                if (this.f5367a.f != this) {
                    return ob.a();
                }
                if (!this.f5367a.e) {
                    this.b[i] = true;
                }
                try {
                    return new a(l8.this.f5364a.e(this.f5367a.d[i]));
                } catch (FileNotFoundException unused) {
                    return ob.a();
                }
            }
        }

        public d(e eVar) {
            this.f5367a = eVar;
            this.b = eVar.e ? null : new boolean[l8.this.h];
        }
    }

    public final class f implements Closeable {

        /* renamed from: a, reason: collision with root package name */
        public final String f5369a;
        public final long b;
        public final zb[] c;
        public final long[] d;

        public String t() {
            return this.f5369a;
        }

        @Nullable
        public d s() throws IOException {
            return l8.this.a(this.f5369a, this.b);
        }

        public zb e(int i) {
            return this.c[i];
        }

        public long d(int i) {
            return this.d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (zb zbVar : this.c) {
                f8.a(zbVar);
            }
        }

        public f(String str, long j, zb[] zbVarArr, long[] jArr) {
            this.f5369a = str;
            this.b = j;
            this.c = zbVarArr;
            this.d = jArr;
        }
    }

    private void e(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == 6 && str.startsWith("REMOVE")) {
                this.k.remove(substring);
                return;
            }
        } else {
            substring = str.substring(i, indexOf2);
        }
        e eVar = this.k.get(substring);
        if (eVar == null) {
            eVar = new e(substring);
            this.k.put(substring, eVar);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            eVar.e = true;
            eVar.f = null;
            eVar.a(split);
            return;
        }
        if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
            eVar.f = new d(eVar);
        } else {
            if (indexOf2 == -1 && indexOf == 4 && str.startsWith("READ")) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
    }

    public static l8 a(ea eaVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 > 0) {
            return new l8(eaVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f8.a("OkHttp DiskLruCache", true)));
        }
        throw new IllegalArgumentException("valueCount <= 0");
    }

    public class c implements Iterator<f> {

        /* renamed from: a, reason: collision with root package name */
        public final Iterator<e> f5366a;
        public f b;
        public f c;

        @Override // java.util.Iterator
        public void remove() {
            f fVar = this.c;
            if (fVar == null) {
                throw new IllegalStateException("remove() before next()");
            }
            try {
                l8.this.d(fVar.f5369a);
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.c = null;
                throw th;
            }
            this.c = null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public f next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            f fVar = this.b;
            this.c = fVar;
            this.b = null;
            return fVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            f a2;
            if (this.b != null) {
                return true;
            }
            synchronized (l8.this) {
                if (l8.this.o) {
                    return false;
                }
                while (this.f5366a.hasNext()) {
                    e next = this.f5366a.next();
                    if (next.e && (a2 = next.a()) != null) {
                        this.b = a2;
                        return true;
                    }
                }
                return false;
            }
        }

        public c() {
            this.f5366a = new ArrayList(l8.this.k.values()).iterator();
        }
    }

    public final class e {

        /* renamed from: a, reason: collision with root package name */
        public final String f5368a;
        public final long[] b;
        public final File[] c;
        public final File[] d;
        public boolean e;
        public d f;
        public long g;

        public void a(String[] strArr) throws IOException {
            if (strArr.length != l8.this.h) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        public void a(cb cbVar) throws IOException {
            for (long j : this.b) {
                cbVar.writeByte(32).a(j);
            }
        }

        public f a() {
            zb zbVar;
            if (!Thread.holdsLock(l8.this)) {
                throw new AssertionError();
            }
            zb[] zbVarArr = new zb[l8.this.h];
            long[] jArr = (long[]) this.b.clone();
            for (int i = 0; i < l8.this.h; i++) {
                try {
                    zbVarArr[i] = l8.this.f5364a.c(this.c[i]);
                } catch (FileNotFoundException unused) {
                    for (int i2 = 0; i2 < l8.this.h && (zbVar = zbVarArr[i2]) != null; i2++) {
                        f8.a(zbVar);
                    }
                    try {
                        l8.this.a(this);
                        return null;
                    } catch (IOException unused2) {
                        return null;
                    }
                }
            }
            return l8.this.new f(this.f5368a, this.g, zbVarArr, jArr);
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public e(String str) {
            this.f5368a = str;
            int i = l8.this.h;
            this.b = new long[i];
            this.c = new File[i];
            this.d = new File[i];
            StringBuilder sb = new StringBuilder(str);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            int length = sb.length();
            for (int i2 = 0; i2 < l8.this.h; i2++) {
                sb.append(i2);
                this.c[i2] = new File(l8.this.b, sb.toString());
                sb.append(".tmp");
                this.d[i2] = new File(l8.this.b, sb.toString());
                sb.setLength(length);
            }
        }
    }

    private void G() throws IOException {
        db a2 = ob.a(this.f5364a.c(this.c));
        try {
            String n = a2.n();
            String n2 = a2.n();
            String n3 = a2.n();
            String n4 = a2.n();
            String n5 = a2.n();
            if (!"libcore.io.DiskLruCache".equals(n) || !"1".equals(n2) || !Integer.toString(this.f).equals(n3) || !Integer.toString(this.h).equals(n4) || !"".equals(n5)) {
                throw new IOException("unexpected journal header: [" + n + ", " + n2 + ", " + n4 + ", " + n5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    e(a2.n());
                    i++;
                } catch (EOFException unused) {
                    this.l = i - this.k.size();
                    if (a2.i()) {
                        this.j = E();
                    } else {
                        z();
                    }
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void F() throws IOException {
        this.f5364a.b(this.d);
        Iterator<e> it = this.k.values().iterator();
        while (it.hasNext()) {
            e next = it.next();
            int i = 0;
            if (next.f == null) {
                while (i < this.h) {
                    this.i += next.b[i];
                    i++;
                }
            } else {
                next.f = null;
                while (i < this.h) {
                    this.f5364a.b(next.c[i]);
                    this.f5364a.b(next.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    public class b extends m8 {
        public static final /* synthetic */ boolean d = true;

        @Override // com.huawei.hms.network.embedded.m8
        public void a(IOException iOException) {
            if (!d && !Thread.holdsLock(l8.this)) {
                throw new AssertionError();
            }
            l8.this.m = true;
        }

        public b(yb ybVar) {
            super(ybVar);
        }
    }

    private cb E() throws FileNotFoundException {
        return ob.a(new b(this.f5364a.g(this.c)));
    }

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            synchronized (l8.this) {
                if ((!l8.this.n) || l8.this.o) {
                    return;
                }
                try {
                    l8.this.C();
                } catch (IOException unused) {
                    l8.this.p = true;
                }
                try {
                    if (l8.this.y()) {
                        l8.this.z();
                        l8.this.l = 0;
                    }
                } catch (IOException unused2) {
                    l8.this.q = true;
                    l8.this.j = ob.a(ob.a());
                }
            }
        }

        public a() {
        }
    }

    private void D() {
        synchronized (this) {
            if (x()) {
                throw new IllegalStateException("cache is closed");
            }
        }
    }

    public l8(ea eaVar, File file, int i, int i2, long j, Executor executor) {
        this.f5364a = eaVar;
        this.b = file;
        this.f = i;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
        this.s = executor;
    }
}
