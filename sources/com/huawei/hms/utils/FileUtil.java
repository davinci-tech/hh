package com.huawei.hms.utils;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes9.dex */
public abstract class FileUtil {
    public static final String LOCAL_REPORT_FILE = "hms/HwMobileServiceReport.txt";
    public static final String LOCAL_REPORT_FILE_CONFIG = "hms/config.txt";
    public static final long LOCAL_REPORT_FILE_MAX_SIZE = 10240;

    /* renamed from: a, reason: collision with root package name */
    private static boolean f6137a = false;
    private static ScheduledExecutorService b = Executors.newSingleThreadScheduledExecutor();

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ File f6138a;
        final /* synthetic */ long b;
        final /* synthetic */ String c;

        a(File file, long j, String str) {
            this.f6138a = file;
            this.b = j;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            RandomAccessFile randomAccessFile;
            Throwable th;
            File file = this.f6138a;
            if (file == null) {
                HMSLog.e(com.huawei.android.hicloud.sync.util.FileUtil.TAG, "In writeFile Failed to get local file.");
                return;
            }
            File parentFile = file.getParentFile();
            if (parentFile == null || !(parentFile.mkdirs() || parentFile.isDirectory())) {
                HMSLog.e(com.huawei.android.hicloud.sync.util.FileUtil.TAG, "In writeFile, Failed to create directory.");
                return;
            }
            RandomAccessFile randomAccessFile2 = null;
            try {
                try {
                    long length = this.f6138a.length();
                    if (length > this.b) {
                        String canonicalPath = this.f6138a.getCanonicalPath();
                        if (!this.f6138a.delete()) {
                            HMSLog.e(com.huawei.android.hicloud.sync.util.FileUtil.TAG, "last file delete failed.");
                        }
                        randomAccessFile2 = new RandomAccessFile(new File(canonicalPath), "rw");
                    } else {
                        randomAccessFile = new RandomAccessFile(this.f6138a, "rw");
                        try {
                            randomAccessFile.seek(length);
                            randomAccessFile2 = randomAccessFile;
                        } catch (IOException e) {
                            e = e;
                            randomAccessFile2 = randomAccessFile;
                            HMSLog.e(com.huawei.android.hicloud.sync.util.FileUtil.TAG, "writeFile exception:", e);
                            IOUtils.closeQuietly(randomAccessFile2);
                        } catch (Throwable th2) {
                            th = th2;
                            IOUtils.closeQuietly(randomAccessFile);
                            throw th;
                        }
                    }
                    randomAccessFile2.writeBytes(this.c + System.getProperty("line.separator"));
                } catch (Throwable th3) {
                    randomAccessFile = null;
                    th = th3;
                }
            } catch (IOException e2) {
                e = e2;
            }
            IOUtils.closeQuietly(randomAccessFile2);
        }
    }

    public static boolean verifyHash(String str, File file) {
        byte[] digest = SHA256.digest(file);
        return digest != null && HEX.encodeHexString(digest, true).equalsIgnoreCase(str);
    }

    public static void writeFile(File file, String str, long j) {
        b.execute(new a(file, j, str));
    }

    public static void writeFileReport(Context context, File file, File file2, String str, long j, int i) {
        if (file != null && file.isFile() && file.exists()) {
            if (!f6137a) {
                if (file2 != null && file2.exists() && !file2.delete()) {
                    HMSLog.e(com.huawei.android.hicloud.sync.util.FileUtil.TAG, "file delete failed.");
                }
                f6137a = true;
            }
            writeFile(file2, str + "|" + j + "|" + i, LOCAL_REPORT_FILE_MAX_SIZE);
        }
    }
}
