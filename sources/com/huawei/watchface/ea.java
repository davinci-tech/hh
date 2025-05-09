package com.huawei.watchface;

import com.huawei.watchface.utils.HwLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* loaded from: classes7.dex */
public class ea {

    public interface b {
        boolean a(String str, byte[] bArr);

        void b();

        void c();
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005c, code lost:
    
        r2 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(byte[] r11, com.huawei.watchface.ea.b... r12) throws java.io.IOException {
        /*
            int r0 = r12.length
            r1 = 0
            r2 = r1
        L3:
            if (r2 >= r0) goto Ld
            r3 = r12[r2]
            r3.b()
            int r2 = r2 + 1
            goto L3
        Ld:
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r11)
            r0.<init>(r2)
            r11 = r1
            r2 = r11
        L19:
            java.util.zip.ZipEntry r3 = r0.getNextEntry()     // Catch: java.lang.Throwable -> L6f
            if (r3 == 0) goto L60
            boolean r2 = r3.isDirectory()     // Catch: java.lang.Throwable -> L6f
            r4 = 1
            if (r2 == 0) goto L27
            goto L5e
        L27:
            java.lang.String r2 = r3.getName()     // Catch: java.lang.Throwable -> L6f
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L6f
            r3.<init>()     // Catch: java.lang.Throwable -> L6f
            a(r0, r3, r1)     // Catch: java.lang.Throwable -> L6f
            int r11 = r11 + 1
            r5 = 2048(0x800, float:2.87E-42)
            java.lang.String r6 = "ZipFileUtil"
            if (r11 <= r5) goto L42
            java.lang.String r11 = "unzip  entries over than top size"
            com.huawei.watchface.utils.HwLog.e(r6, r11)     // Catch: java.lang.Throwable -> L6f
            goto L5c
        L42:
            int r5 = r12.length     // Catch: java.lang.Throwable -> L6f
            r7 = r1
            r8 = r4
        L45:
            if (r7 >= r5) goto L55
            r9 = r12[r7]     // Catch: java.lang.Throwable -> L6f
            byte[] r10 = r3.toByteArray()     // Catch: java.lang.Throwable -> L6f
            boolean r9 = r9.a(r2, r10)     // Catch: java.lang.Throwable -> L6f
            r8 = r8 & r9
            int r7 = r7 + 1
            goto L45
        L55:
            if (r8 == 0) goto L5e
            java.lang.String r11 = "finish zip action earlier"
            com.huawei.watchface.utils.HwLog.i(r6, r11)     // Catch: java.lang.Throwable -> L6f
        L5c:
            r2 = r4
            goto L60
        L5e:
            r2 = r4
            goto L19
        L60:
            r0.close()
            int r11 = r12.length
        L64:
            if (r1 >= r11) goto L6e
            r0 = r12[r1]
            r0.c()
            int r1 = r1 + 1
            goto L64
        L6e:
            return r2
        L6f:
            r11 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L74
            goto L78
        L74:
            r12 = move-exception
            r11.addSuppressed(r12)
        L78:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.ea.a(byte[], com.huawei.watchface.ea$b[]):boolean");
    }

    public static abstract class d implements b {

        /* renamed from: a, reason: collision with root package name */
        protected final Set<String> f11000a;

        public d() {
            this.f11000a = new HashSet();
        }

        public d(String str) {
            this();
            this.f11000a.add(str);
        }
    }

    public static class c extends d {
        private Map<String, byte[]> b;

        @Override // com.huawei.watchface.ea.b
        public void c() {
        }

        public Map<String, byte[]> a() {
            return this.b;
        }

        public c(String str) {
            super(str);
        }

        @Override // com.huawei.watchface.ea.b
        public void b() {
            this.b = new HashMap();
        }

        @Override // com.huawei.watchface.ea.b
        public boolean a(String str, byte[] bArr) {
            if (this.f11000a.contains(str)) {
                this.b.put(str, bArr);
            }
            return this.b.size() >= this.f11000a.size();
        }
    }

    public static class a extends d {
        private byte[] b;
        private ByteArrayOutputStream c;
        private ZipOutputStream d;

        public a(String str) {
            super(str);
        }

        public byte[] a() {
            return this.b;
        }

        @Override // com.huawei.watchface.ea.b
        public void b() {
            this.c = new ByteArrayOutputStream();
            this.d = new ZipOutputStream(this.c);
        }

        @Override // com.huawei.watchface.ea.b
        public void c() {
            try {
                this.d.close();
            } catch (IOException unused) {
                HwLog.e("ZipFileUtil", "DeleteAction finish: ignore Exception");
            }
            this.b = this.c.toByteArray();
        }

        @Override // com.huawei.watchface.ea.b
        public boolean a(String str, byte[] bArr) {
            if (this.f11000a.contains(str)) {
                return false;
            }
            try {
                this.d.putNextEntry(new ZipEntry(str));
                this.d.write(bArr);
            } catch (IOException unused) {
                HwLog.e("ZipFileUtil", "DeleteAction accept: ignore Exception");
            }
            return false;
        }
    }

    private static void a(ZipInputStream zipInputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[5120];
        while (true) {
            int read = zipInputStream.read(bArr, 0, 5120);
            if (read <= 0) {
                return;
            }
            i += read;
            if (i > 209715200) {
                HwLog.e("ZipFileUtil", "unzip  over than top size");
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }
}
