package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileLockHelper;
import com.huawei.haf.common.os.FileUtils;
import health.compact.a.LogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes8.dex */
final class yf implements Closeable {
    private final FileLockHelper c;
    private final File e;

    yf(File file) throws IOException {
        this.e = new File(file, "version.info");
        this.c = FileLockHelper.b(new File(file, "version.lock"));
    }

    public yj e() {
        if (!this.c.c()) {
            throw new IllegalStateException("ModuleInfoVersionDataStorage was closed");
        }
        return d(this.e);
    }

    public boolean e(yj yjVar) {
        if (!this.c.c()) {
            throw new IllegalStateException("ModuleInfoVersionDataStorage was closed");
        }
        if (yjVar == null) {
            throw new IllegalArgumentException("versionData == null");
        }
        return d(this.e, yjVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.c.close();
    }

    private static yj d(File file) {
        FileInputStream fileInputStream;
        String str;
        String str2;
        FileInputStream fileInputStream2 = null;
        if (!file.exists() || file.length() == 0 || file.canWrite()) {
            return null;
        }
        for (int i = 0; i < 3; i++) {
            Properties properties = new Properties();
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        properties.load(fileInputStream);
                        str = properties.getProperty("oldVersion");
                        try {
                            str2 = properties.getProperty("newVersion");
                            FileUtils.d(fileInputStream);
                        } catch (IOException e) {
                            e = e;
                            LogUtil.e("Bundle_ModuleInfoVersionDataStorage", "read property failed, ex=", LogUtil.a(e));
                            FileUtils.d(fileInputStream);
                            str2 = null;
                            if (TextUtils.isEmpty(str)) {
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        str = null;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    FileUtils.d(fileInputStream2);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                fileInputStream = null;
                str = null;
            } catch (Throwable th2) {
                th = th2;
            }
            if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                return new yj(str, str2);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x008d A[LOOP:0: B:2:0x0018->B:13:0x008d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(java.io.File r9, defpackage.yj r10) {
        /*
            r8 = this;
            java.lang.String r0 = "updateVersionDataProperties: "
            java.lang.String r1 = r10.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "Bundle_ModuleInfoVersionDataStorage"
            health.compact.a.LogUtil.c(r1, r0)
            java.io.File r0 = r9.getParentFile()
            com.huawei.haf.common.os.FileUtils.a(r0)
            r0 = 0
            r2 = r0
        L18:
            r3 = 3
            if (r2 >= r3) goto L9b
            java.util.Properties r3 = new java.util.Properties
            r3.<init>()
            java.lang.String r4 = "oldVersion"
            java.lang.String r5 = r10.e()
            r3.put(r4, r5)
            java.lang.String r4 = "newVersion"
            java.lang.String r5 = r10.b()
            r3.put(r4, r5)
            r4 = 0
            r5 = 1
            com.huawei.haf.common.os.FileUtils.d(r9)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            r6.<init>(r9, r0)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r4.<init>()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r7 = "from old version:"
            r4.append(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r7 = r10.e()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r4.append(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r7 = " to new version:"
            r4.append(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r7 = r10.b()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r4.append(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r3.store(r6, r4)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            com.huawei.haf.common.os.FileUtils.d(r6)
            goto L7f
        L64:
            r10 = move-exception
            goto L94
        L66:
            r3 = move-exception
            r4 = r6
            goto L6c
        L69:
            r10 = move-exception
            goto L93
        L6b:
            r3 = move-exception
        L6c:
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L69
            java.lang.String r7 = "write property failed, ex="
            r6[r0] = r7     // Catch: java.lang.Throwable -> L69
            java.lang.String r3 = health.compact.a.LogUtil.a(r3)     // Catch: java.lang.Throwable -> L69
            r6[r5] = r3     // Catch: java.lang.Throwable -> L69
            health.compact.a.LogUtil.e(r1, r6)     // Catch: java.lang.Throwable -> L69
            com.huawei.haf.common.os.FileUtils.d(r4)
        L7f:
            com.huawei.haf.common.os.FileUtils.f(r9)
            yj r3 = d(r9)
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L8d
            return r5
        L8d:
            com.huawei.haf.common.os.FileUtils.d(r9)
            int r2 = r2 + 1
            goto L18
        L93:
            r6 = r4
        L94:
            com.huawei.haf.common.os.FileUtils.d(r6)
            com.huawei.haf.common.os.FileUtils.f(r9)
            throw r10
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yf.d(java.io.File, yj):boolean");
    }
}
