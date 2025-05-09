package com.huawei.hms.network.embedded;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes9.dex */
public final class p8 {
    public static final int k = 1;
    public static final int l = 2;
    public static final eb m = eb.d("OkHttp cache v1\n");
    public static final eb n = eb.d("OkHttp DIRTY :(\n");
    public static final long o = 32;

    /* renamed from: a, reason: collision with root package name */
    public RandomAccessFile f5417a;
    public Thread b;
    public zb c;
    public long e;
    public boolean f;
    public final eb g;
    public final long i;
    public int j;
    public final bb d = new bb();
    public final bb h = new bb();

    public zb c() {
        synchronized (this) {
            if (this.f5417a == null) {
                return null;
            }
            this.j++;
            return new a();
        }
    }

    public eb b() {
        return this.g;
    }

    public boolean a() {
        return this.f5417a == null;
    }

    public void a(long j) throws IOException {
        b(j);
        this.f5417a.getChannel().force(false);
        a(m, j, this.g.j());
        this.f5417a.getChannel().force(false);
        synchronized (this) {
            this.f = true;
        }
        f8.a(this.c);
        this.c = null;
    }

    private void b(long j) throws IOException {
        bb bbVar = new bb();
        bbVar.a(this.g);
        new o8(this.f5417a.getChannel()).b(32 + j, bbVar, this.g.j());
    }

    public class a implements zb {

        /* renamed from: a, reason: collision with root package name */
        public final ac f5418a = new ac();
        public o8 b;
        public long c;

        @Override // com.huawei.hms.network.embedded.zb, com.huawei.hms.network.embedded.yb
        public ac timeout() {
            return this.f5418a;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
        
            if (r0 != 2) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        
            r2 = java.lang.Math.min(r23, r7 - r21.c);
            r21.b.a(r21.c + 32, r22, r2);
            r21.c += r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
        
            return r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
        
            r5 = r21.d.c.read(r21.d.d, r21.d.i);
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
        
            if (r5 != (-1)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
        
            r21.d.a(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0082, code lost:
        
            r2 = r21.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0084, code lost:
        
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0085, code lost:
        
            r21.d.b = null;
            r21.d.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x008e, code lost:
        
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x008f, code lost:
        
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0093, code lost:
        
            r2 = java.lang.Math.min(r5, r23);
            r21.d.d.a(r22, 0, r2);
            r21.c += r2;
            r21.b.b(r7 + 32, r21.d.d.m628clone(), r5);
            r7 = r21.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00bc, code lost:
        
            monitor-enter(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00bd, code lost:
        
            r21.d.h.write(r21.d.d, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00d6, code lost:
        
            if (r21.d.h.B() <= r21.d.i) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00d8, code lost:
        
            r21.d.h.skip(r21.d.h.B() - r21.d.i);
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00ec, code lost:
        
            r21.d.e += r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00f3, code lost:
        
            monitor-exit(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00f4, code lost:
        
            r5 = r21.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00f6, code lost:
        
            monitor-enter(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00f7, code lost:
        
            r21.d.b = null;
            r21.d.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0100, code lost:
        
            monitor-exit(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0101, code lost:
        
            return r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0108, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x010b, code lost:
        
            monitor-enter(r21.d);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x010c, code lost:
        
            r21.d.b = null;
            r21.d.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0116, code lost:
        
            throw r0;
         */
        @Override // com.huawei.hms.network.embedded.zb
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(com.huawei.hms.network.embedded.bb r22, long r23) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 320
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.p8.a.read(com.huawei.hms.network.embedded.bb, long):long");
        }

        @Override // com.huawei.hms.network.embedded.zb, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, com.huawei.hms.network.embedded.yb
        public void close() throws IOException {
            if (this.b == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.b = null;
            synchronized (p8.this) {
                p8 p8Var = p8.this;
                p8Var.j--;
                if (p8.this.j == 0) {
                    RandomAccessFile randomAccessFile2 = p8.this.f5417a;
                    p8.this.f5417a = null;
                    randomAccessFile = randomAccessFile2;
                }
            }
            if (randomAccessFile != null) {
                f8.a(randomAccessFile);
            }
        }

        public a() {
            this.b = new o8(p8.this.f5417a.getChannel());
        }
    }

    private void a(eb ebVar, long j, long j2) throws IOException {
        bb bbVar = new bb();
        bbVar.a(ebVar);
        bbVar.writeLong(j);
        bbVar.writeLong(j2);
        if (bbVar.B() != 32) {
            throw new IllegalArgumentException();
        }
        new o8(this.f5417a.getChannel()).b(0L, bbVar, 32L);
    }

    public static p8 a(File file, zb zbVar, eb ebVar, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        p8 p8Var = new p8(randomAccessFile, zbVar, 0L, ebVar, j);
        randomAccessFile.setLength(0L);
        p8Var.a(n, -1L, -1L);
        return p8Var;
    }

    public static p8 a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        o8 o8Var = new o8(randomAccessFile.getChannel());
        bb bbVar = new bb();
        o8Var.a(0L, bbVar, 32L);
        if (!bbVar.d(r2.j()).equals(m)) {
            throw new IOException("unreadable cache file");
        }
        long readLong = bbVar.readLong();
        long readLong2 = bbVar.readLong();
        bb bbVar2 = new bb();
        o8Var.a(readLong + 32, bbVar2, readLong2);
        return new p8(randomAccessFile, null, readLong, bbVar2.r(), 0L);
    }

    public p8(RandomAccessFile randomAccessFile, zb zbVar, long j, eb ebVar, long j2) {
        this.f5417a = randomAccessFile;
        this.c = zbVar;
        this.f = zbVar == null;
        this.e = j;
        this.g = ebVar;
        this.i = j2;
    }
}
