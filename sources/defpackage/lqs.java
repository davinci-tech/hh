package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.networkclient.cache.ICacheStrategy;
import com.huawei.networkclient.repository.DataConverterRepository;
import defpackage.lqy;
import health.compact.a.HealthWhiteBoxManager;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okio.BufferedSink;
import okio.Okio;

/* loaded from: classes5.dex */
public class lqs {

    /* renamed from: a, reason: collision with root package name */
    private volatile lqy f14841a;
    private DataConverterRepository d;
    private boolean e = false;

    public static boolean a(int i) {
        return (i & 1) != 0;
    }

    public static boolean d(int i) {
        return (i & 4) != 0;
    }

    public static boolean e(int i) {
        return (i & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File c(java.lang.String r4) {
        /*
            java.lang.String r0 = "CacheCommon"
            java.lang.String r1 = "mounted"
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            boolean r1 = r1.equals(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            if (r1 == 0) goto L4d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            r1.<init>()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            r1.append(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.lang.String r2 = "/Android/data/"
            r1.append(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            r1.append(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.lang.String r2 = "/cache"
            r1.append(r2)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.lang.String r1 = r1.toString()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.io.File r2 = new java.io.File     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            r2.<init>(r1)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            boolean r1 = r2.exists()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            if (r1 != 0) goto L68
            boolean r1 = r2.mkdirs()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            if (r1 != 0) goto L68
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.io.File r2 = r1.getExternalCacheDir()     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            goto L68
        L4d:
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            java.lang.String r2 = "getDiskCacheDir externalStorage not available"
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            health.compact.a.LogUtil.e(r0, r1)     // Catch: java.lang.ArrayIndexOutOfBoundsException -> L59
            goto L67
        L59:
            r1 = move-exception
            java.lang.String r2 = "getDiskCacheDir exception="
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            health.compact.a.LogUtil.e(r0, r1)
        L67:
            r2 = 0
        L68:
            if (r2 != 0) goto L90
            java.lang.String r1 = "getDiskCacheDir cacheFile is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.LogUtil.e(r0, r1)
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            java.io.File r1 = r1.getDataDir()
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "cache"
            r2.<init>(r1, r3)
            boolean r1 = r2.exists()
            if (r1 != 0) goto L90
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            java.io.File r2 = r1.getCacheDir()
        L90:
            java.lang.String r1 = r2.getPath()
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = java.io.File.separator
            r3.append(r1)
            r3.append(r4)
            java.lang.String r4 = r3.toString()
            r2.<init>(r4)
            boolean r4 = r2.exists()
            if (r4 != 0) goto Lc4
            boolean r4 = r2.mkdirs()
            if (r4 != 0) goto Lc4
            java.lang.String r4 = "getDiskCacheDir "
            java.lang.String r1 = "mkdirs error"
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r1}
            health.compact.a.LogUtil.a(r0, r4)
        Lc4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lqs.c(java.lang.String):java.io.File");
    }

    public static String c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        if (!TextUtils.isEmpty(str3)) {
            sb.append(str3);
        }
        return HexUtils.d(SecurityUtils.a(sb.toString())).toLowerCase();
    }

    public lqs(String str, DataConverterRepository dataConverterRepository) {
        this.f14841a = lqy.c(lqw.a(), c(str), BaseApplication.c(), 2, 10485760L);
        this.d = dataConverterRepository;
    }

    public boolean e(String str, ICacheStrategy iCacheStrategy, String str2) {
        boolean z;
        lqy.b a2;
        synchronized (this) {
            if (c(str, iCacheStrategy)) {
                return false;
            }
            LogUtil.c("CacheCommon", "start saveDiskCache");
            long millis = iCacheStrategy.getDiskTimeUnit().toMillis(iCacheStrategy.getDiskTimeOut().longValue());
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f14841a.c()) {
                LogUtil.c("CacheCommon", "saveDiskCache: healthDiskLruCache is closed, no need to save..");
                return false;
            }
            lqy.b bVar = null;
            try {
                try {
                    a2 = this.f14841a.a(str);
                } finally {
                    if (bVar != null) {
                        try {
                        } catch (IOException e) {
                        }
                    }
                }
            } catch (IOException e2) {
                LogUtil.e("CacheCommon", "saveDiskCache fail:", ExceptionUtils.d(e2));
                if (0 != 0) {
                    try {
                        bVar.b();
                    } catch (IOException e3) {
                        LogUtil.e("CacheCommon", "commit or flush fail:", ExceptionUtils.d(e3));
                        z = false;
                        LogUtil.e("CacheCommon", "end saveDiskCache");
                        return z;
                    }
                }
                this.f14841a.flush();
            }
            if (a2 == null) {
                LogUtil.e("CacheCommon", "healthDiskLruCache not ready for edit");
                if (a2 != null) {
                    try {
                        a2.b();
                    } catch (IOException e4) {
                        LogUtil.e("CacheCommon", "commit or flush fail:", ExceptionUtils.d(e4));
                    }
                }
                this.f14841a.flush();
                return false;
            }
            BufferedSink buffer = Okio.buffer(a2.b(1));
            buffer.writeLong(millis + currentTimeMillis);
            buffer.flush();
            BufferedSink buffer2 = Okio.buffer(a2.b(0));
            if (iCacheStrategy.needEncrypt()) {
                buffer2.writeString(new String(HealthWhiteBoxManager.d(BaseApplication.e()).d(str2), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            } else {
                buffer2.writeString(str2, StandardCharsets.UTF_8);
            }
            buffer2.flush();
            if (a2 != null) {
                try {
                    a2.b();
                } catch (IOException e5) {
                    LogUtil.e("CacheCommon", "commit or flush fail:", ExceptionUtils.d(e5));
                    z = false;
                    LogUtil.e("CacheCommon", "end saveDiskCache");
                    return z;
                }
            }
            this.f14841a.flush();
            z = true;
            LogUtil.e("CacheCommon", "end saveDiskCache");
            return z;
        }
    }

    private boolean c(String str, ICacheStrategy iCacheStrategy) {
        if (this.e) {
            LogUtil.e("CacheCommon", "cache dir is deleted, saveDiskCache fail");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("CacheCommon", "saveDiskCache key is null");
            return true;
        }
        if (iCacheStrategy.getDiskTimeOut() != null && iCacheStrategy.getDiskTimeUnit() != null) {
            return false;
        }
        LogUtil.e("CacheCommon", "empty disk timeout, fail to save to disk");
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0099, code lost:
    
        if (r5 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c7, code lost:
    
        if (r5 == null) goto L45;
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x00d7: MOVE (r1 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:43:0x00d7 */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <Data> Data e(java.lang.String r11, java.lang.Class<Data> r12, com.huawei.networkclient.cache.ICacheStrategy r13) {
        /*
            r10 = this;
            boolean r0 = r10.d(r11)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.String r0 = "start getDiskCache"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r2 = "CacheCommon"
            health.compact.a.LogUtil.c(r2, r0)
            r0 = 2
            r3 = 0
            r4 = 1
            lqy r5 = r10.f14841a     // Catch: java.lang.Throwable -> La0 com.google.gson.JsonSyntaxException -> La2 java.io.IOException -> Lb6
            lqy$d r5 = r5.d(r11)     // Catch: java.lang.Throwable -> La0 com.google.gson.JsonSyntaxException -> La2 java.io.IOException -> Lb6
            if (r5 != 0) goto L2e
            java.lang.Object[] r11 = new java.lang.Object[r4]     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            java.lang.String r12 = "no hitDiskCache"
            r11[r3] = r12     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            health.compact.a.LogUtil.a(r2, r11)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            if (r5 == 0) goto L2d
            r5.close()
        L2d:
            return r1
        L2e:
            okio.Source r6 = r5.a(r4)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            okio.BufferedSource r6 = okio.Okio.buffer(r6)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            long r6 = r6.readLong()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            long r8 = java.lang.System.currentTimeMillis()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L65
            java.lang.Object[] r12 = new java.lang.Object[r4]     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            java.lang.String r13 = "hit timeout diskCache"
            r12[r3] = r13     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            health.compact.a.LogUtil.a(r2, r12)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            if (r5 == 0) goto L50
            r5.close()
        L50:
            lqy r12 = r10.f14841a     // Catch: java.io.IOException -> L56
            r12.e(r11)     // Catch: java.io.IOException -> L56
            goto L64
        L56:
            r11 = move-exception
            java.lang.String r12 = "remove expired data fail:"
            java.lang.String r11 = com.huawei.haf.common.exception.ExceptionUtils.d(r11)
            java.lang.Object[] r11 = new java.lang.Object[]{r12, r11}
            health.compact.a.LogUtil.e(r2, r11)
        L64:
            return r1
        L65:
            okio.Source r11 = r5.a(r3)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            okio.BufferedSource r11 = okio.Okio.buffer(r11)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            boolean r13 = r13.needEncrypt()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            if (r13 == 0) goto L8b
            java.lang.String r13 = new java.lang.String     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            health.compact.a.HealthWhiteBoxManager r6 = health.compact.a.HealthWhiteBoxManager.d(r6)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            byte[] r11 = r11.readByteArray()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            byte[] r11 = r6.e(r11)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            r13.<init>(r11, r6)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            goto L8f
        L8b:
            java.lang.String r13 = r11.readUtf8()     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
        L8f:
            com.huawei.networkclient.repository.DataConverterRepository r11 = r10.d     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            com.huawei.networkclient.repository.DataConverterRepository$DataConverter r11 = r11.a(r12)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            java.lang.Object r1 = r11.convert(r13, r12)     // Catch: com.google.gson.JsonSyntaxException -> L9c java.io.IOException -> L9e java.lang.Throwable -> Ld6
            if (r5 == 0) goto Lcc
            goto Lc9
        L9c:
            r11 = move-exception
            goto La4
        L9e:
            r11 = move-exception
            goto Lb8
        La0:
            r11 = move-exception
            goto Ld8
        La2:
            r11 = move-exception
            r5 = r1
        La4:
            java.lang.Object[] r12 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Ld6
            java.lang.String r13 = "getDiskCache Json fail:"
            r12[r3] = r13     // Catch: java.lang.Throwable -> Ld6
            java.lang.String r11 = com.huawei.haf.common.exception.ExceptionUtils.d(r11)     // Catch: java.lang.Throwable -> Ld6
            r12[r4] = r11     // Catch: java.lang.Throwable -> Ld6
            health.compact.a.LogUtil.e(r2, r12)     // Catch: java.lang.Throwable -> Ld6
            if (r5 == 0) goto Lcc
            goto Lc9
        Lb6:
            r11 = move-exception
            r5 = r1
        Lb8:
            java.lang.Object[] r12 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Ld6
            java.lang.String r13 = "getDiskCache IO fail:"
            r12[r3] = r13     // Catch: java.lang.Throwable -> Ld6
            java.lang.String r11 = com.huawei.haf.common.exception.ExceptionUtils.d(r11)     // Catch: java.lang.Throwable -> Ld6
            r12[r4] = r11     // Catch: java.lang.Throwable -> Ld6
            health.compact.a.LogUtil.e(r2, r12)     // Catch: java.lang.Throwable -> Ld6
            if (r5 == 0) goto Lcc
        Lc9:
            r5.close()
        Lcc:
            java.lang.String r11 = "end getDiskCache"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            health.compact.a.LogUtil.c(r2, r11)
            return r1
        Ld6:
            r11 = move-exception
            r1 = r5
        Ld8:
            if (r1 == 0) goto Ldd
            r1.close()
        Ldd:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lqs.e(java.lang.String, java.lang.Class, com.huawei.networkclient.cache.ICacheStrategy):java.lang.Object");
    }

    private boolean d(String str) {
        if (this.e) {
            LogUtil.e("CacheCommon", "cache dir is deleted, getDiskCache fail");
            return true;
        }
        if (!TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.e("CacheCommon", "getDiskCache key is null");
        return true;
    }

    public void a() {
        synchronized (this) {
            this.e = true;
            if (this.f14841a == null) {
                LogUtil.a("CacheCommon", "clearCache healthDiskLruCache is null");
                return;
            }
            try {
                this.f14841a.b();
            } catch (IOException e) {
                LogUtil.e("CacheCommon", "clearCache fail:", ExceptionUtils.d(e));
            }
            this.f14841a = null;
        }
    }
}
