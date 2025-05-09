package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public final class bs {

    /* renamed from: a, reason: collision with root package name */
    private b f928a;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f930a = false;
    }

    public interface c {
        void a();

        void a(long j);
    }

    public bs(bp bpVar, bo boVar) {
        this.f928a = new b(bpVar, boVar);
    }

    public final void a() {
        b bVar = this.f928a;
        if (bVar != null) {
            bVar.f();
        }
    }

    public final void b() {
        b bVar = this.f928a;
        if (bVar != null) {
            a(bVar);
        }
    }

    private static void a(b bVar) {
        if (bVar == null) {
            return;
        }
        final bo d = bVar.d();
        if (d != null) {
            d.p();
        }
        String a2 = bVar.a();
        String b2 = bVar.b();
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(b2)) {
            if (bVar.e().f930a) {
                if (d != null) {
                    d.r();
                    return;
                }
                return;
            } else {
                if (d != null) {
                    d.q();
                    return;
                }
                return;
            }
        }
        File file = new File(a2);
        if (!file.exists()) {
            if (bVar.e().f930a) {
                if (d != null) {
                    d.r();
                    return;
                }
                return;
            } else {
                if (d != null) {
                    d.q();
                    return;
                }
                return;
            }
        }
        File file2 = new File(b2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        c cVar = new c() { // from class: com.amap.api.col.3sl.bs.1
            @Override // com.amap.api.col.3sl.bs.c
            public final void a(long j) {
                try {
                    bo boVar = bo.this;
                    if (boVar != null) {
                        boVar.a(j);
                    }
                } catch (Exception unused) {
                }
            }

            @Override // com.amap.api.col.3sl.bs.c
            public final void a() {
                bo boVar = bo.this;
                if (boVar != null) {
                    boVar.q();
                }
            }
        };
        try {
            if (bVar.e().f930a && d != null) {
                d.r();
            }
            a(file, file2, cVar, bVar);
            if (bVar.e().f930a) {
                if (d != null) {
                    d.r();
                }
            } else if (d != null) {
                d.b(bVar.c());
            }
        } catch (Throwable unused) {
            if (bVar.e().f930a) {
                if (d != null) {
                    d.r();
                }
            } else if (d != null) {
                d.q();
            }
        }
    }

    private static void a(File file, File file2, c cVar, b bVar) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        a e = bVar.e();
        long j = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
            ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    if (e != null && e.f930a) {
                        zipInputStream.closeEntry();
                        zipInputStream.close();
                        checkedInputStream.close();
                        fileInputStream.close();
                        break;
                    }
                    if (!nextEntry.isDirectory()) {
                        if (!a(nextEntry.getName())) {
                            cVar.a();
                            break;
                        }
                        stringBuffer.append(nextEntry.getName()).append(";");
                    }
                    j += nextEntry.getSize();
                    zipInputStream.closeEntry();
                } else {
                    break;
                }
            }
            bVar.a(stringBuffer.toString());
            zipInputStream.close();
            checkedInputStream.close();
            fileInputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        FileInputStream fileInputStream2 = new FileInputStream(file);
        CheckedInputStream checkedInputStream2 = new CheckedInputStream(fileInputStream2, new CRC32());
        ZipInputStream zipInputStream2 = new ZipInputStream(checkedInputStream2);
        a(file2, zipInputStream2, j, cVar, e);
        zipInputStream2.close();
        checkedInputStream2.close();
        fileInputStream2.close();
    }

    private static void a(File file, ZipInputStream zipInputStream, long j, c cVar, a aVar) throws Exception {
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (aVar != null && aVar.f930a) {
                zipInputStream.closeEntry();
                return;
            }
            String str = file.getPath() + File.separator + nextEntry.getName();
            if (!a(nextEntry.getName())) {
                if (cVar != null) {
                    cVar.a();
                    return;
                }
                return;
            } else {
                File file2 = new File(str);
                a(file2);
                if (nextEntry.isDirectory()) {
                    file2.mkdirs();
                } else {
                    i += a(file2, zipInputStream, i, j, cVar, aVar);
                }
                zipInputStream.closeEntry();
            }
        }
    }

    private static boolean a(String str) {
        return (str.contains("..") || str.contains("/") || str.contains("\\") || str.contains("%")) ? false : true;
    }

    private static int a(File file, ZipInputStream zipInputStream, long j, long j2, c cVar, a aVar) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bArr = new byte[1024];
        int i = 0;
        while (true) {
            int read = zipInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                if (aVar != null && aVar.f930a) {
                    bufferedOutputStream.close();
                    return i;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i += read;
                if (j2 > 0 && cVar != null) {
                    long j3 = ((i + j) * 100) / j2;
                    if (aVar == null || !aVar.f930a) {
                        cVar.a(j3);
                    }
                }
            } else {
                bufferedOutputStream.close();
                return i;
            }
        }
    }

    private static void a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        a(parentFile);
        parentFile.mkdir();
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f931a;
        private String b;
        private bo c;
        private a d = new a();
        private String e;

        public b(bp bpVar, bo boVar) {
            this.c = null;
            this.f931a = bpVar.x();
            this.b = bpVar.y();
            this.c = boVar;
        }

        public final void a(String str) {
            if (str.length() > 1) {
                this.e = str;
            }
        }

        public final String a() {
            return this.f931a;
        }

        public final String b() {
            return this.b;
        }

        public final String c() {
            return this.e;
        }

        public final bo d() {
            return this.c;
        }

        public final a e() {
            return this.d;
        }

        public final void f() {
            this.d.f930a = true;
        }
    }
}
