package com.huawei.haf.common.dfx.crash;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DefaultCrashHandler extends DfxBaseHandler implements CrashCallback {
    private final Object b;
    private volatile boolean e;

    protected void d(String str, String str2, String str3) {
    }

    protected boolean d(Throwable th, String str, String str2, StringBuilder sb) {
        return true;
    }

    @Override // com.huawei.haf.common.dfx.crash.CrashCallback
    public boolean isAllowRethrow() {
        return true;
    }

    @Override // com.huawei.haf.common.dfx.crash.CrashCallback
    public boolean isAllowSelfKill() {
        return true;
    }

    public DefaultCrashHandler(String str) {
        super(str, "_crashLog_%d");
        this.b = new Object();
        this.e = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (dumpHprofData(r5.mTag, r0) == false) goto L9;
     */
    @Override // com.huawei.haf.common.dfx.crash.CrashCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleCrash(java.lang.Thread r6, java.lang.Throwable r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof java.lang.OutOfMemoryError
            r1 = 1
            if (r0 == 0) goto L19
            boolean r0 = r5.e
            if (r0 == 0) goto La
            return
        La:
            r5.e = r1
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r0.<init>(r1)
            java.lang.String r2 = r5.mTag
            boolean r2 = dumpHprofData(r2, r0)
            if (r2 != 0) goto L1a
        L19:
            r0 = 0
        L1a:
            java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch
            r2.<init>(r1)
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r7)
            r5.a(r6, r7, r1, r2)
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L2e
            r3 = 1000(0x3e8, double:4.94E-321)
            r2.await(r3, r6)     // Catch: java.lang.InterruptedException -> L2e
            goto L3c
        L2e:
            r6 = move-exception
            java.lang.String r7 = r5.mTag
            java.lang.String r6 = health.compact.a.LogUtil.a(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.e(r7, r6)
        L3c:
            if (r0 == 0) goto L55
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L47
            r1 = 60000(0xea60, double:2.9644E-319)
            r0.await(r1, r6)     // Catch: java.lang.InterruptedException -> L47
            goto L55
        L47:
            r6 = move-exception
            java.lang.String r7 = r5.mTag
            java.lang.String r6 = health.compact.a.LogUtil.a(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.e(r7, r6)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.common.dfx.crash.DefaultCrashHandler.handleCrash(java.lang.Thread, java.lang.Throwable):void");
    }

    private void a(Thread thread, Throwable th, String str, CountDownLatch countDownLatch) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = null;
        try {
            StringBuilder sb = new StringBuilder(64);
            boolean d = d(th, str, sb);
            String saveBaseInfo = getSaveBaseInfo(thread, th, currentTimeMillis, sb.toString());
            try {
                str3 = DfxUtils.d(thread, th);
                if (d) {
                    d(str, saveBaseInfo, str3);
                }
                a(thread, th, currentTimeMillis, saveBaseInfo + str3, countDownLatch);
            } catch (Throwable th2) {
                th = th2;
                str2 = str3;
                str3 = saveBaseInfo;
                a(thread, th, currentTimeMillis, str3 + str2, countDownLatch);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            str2 = null;
        }
    }

    private boolean d(Throwable th, String str, StringBuilder sb) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return d(th, DfxUtils.a("_crash"), str, sb);
    }

    protected void a(final Thread thread, final Throwable th, final long j, final String str, final CountDownLatch countDownLatch) {
        final File saveFile = getSaveFile();
        if (saveFile == null || !saveFile.getParentFile().exists()) {
            LogUtil.a(this.mTag, "saveCrashInfo fail.");
            if (countDownLatch != null) {
                countDownLatch.countDown();
                return;
            }
            return;
        }
        if (countDownLatch == null) {
            c(thread, th, j, saveFile, str);
            return;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.submit(new Runnable() { // from class: com.huawei.haf.common.dfx.crash.DefaultCrashHandler.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultCrashHandler.this.c(thread, th, j, saveFile, str);
                countDownLatch.countDown();
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Thread thread, Throwable th, long j, File file, String str) {
        ReleaseLogUtil.b(this.mTag, "saveCrashInfo()");
        if (TextUtils.isEmpty(str)) {
            b(thread, th, j, file);
        } else {
            if (saveInfo(str, file)) {
                return;
            }
            LogUtil.a(this.mTag, "saveCrashInfo fail.");
        }
    }

    private void b(Thread thread, Throwable th, long j, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            try {
                PrintStream printStream = new PrintStream((OutputStream) fileOutputStream, false, "UTF-8");
                try {
                    printStream.append((CharSequence) getSaveBaseInfo(thread, th, j, null));
                    ExceptionUtils.c(thread, th, printStream);
                    printStream.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            LogUtil.e(this.mTag, "saveCrashInfo ex=", LogUtil.a(e));
        }
    }

    protected final boolean a(String str, String str2, String str3, StringBuilder sb) {
        long j;
        int i;
        if (str3.indexOf(str) < 0) {
            return true;
        }
        String d = SecurityUtils.d(str3);
        String str4 = d + "_crash_count";
        String str5 = d + "_crash_time";
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(str2, 0);
        synchronized (this.b) {
            try {
                try {
                    if (str3.equals(sharedPreferences.getString(d, ""))) {
                        i = sharedPreferences.getInt(str4, 0);
                        j = sharedPreferences.getLong(str5, 0L);
                        long abs = Math.abs(currentTimeMillis - j);
                        if (abs < 86400000) {
                            if (abs > 5000) {
                                sharedPreferences.edit().putInt(str4, i + 1).commit();
                            }
                            return false;
                        }
                    } else {
                        j = currentTimeMillis;
                        i = 0;
                    }
                    c(sb, str2, j, currentTimeMillis, i + 1);
                    sharedPreferences.edit().putString(d, str3).putInt(str4, 0).putLong(str5, currentTimeMillis).commit();
                    return true;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static void c(StringBuilder sb, String str, long j, long j2, int i) {
        sb.append(str);
        sb.append(':');
        sb.append(" From '");
        sb.append(DfxUtils.a(j));
        sb.append("' to '");
        sb.append(DfxUtils.a(j2));
        sb.append("', crash ");
        sb.append(i);
        sb.append(" times.");
    }
}
