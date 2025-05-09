package com.huawei.haf.common.dfx;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.os.MemoryUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class DfxBaseHandler {
    private static final String DFX_DUMP_LOG_EXTENSION = ".hprof";
    private static final String DFX_LOG_EXTENSION = ".txt";
    private static final int MAX_LOGFILE_LENGTH = 1048576;
    private final String mFormatFileName;
    private final int mMaxLogFileNum;
    private final int mMaxLogFileSize;
    protected final String mTag;

    public DfxBaseHandler(String str, String str2) {
        this(str, str2, 3, 1048576, false);
    }

    protected DfxBaseHandler(String str, String str2, int i) {
        this(str, str2, i, 1048576, false);
    }

    public DfxBaseHandler(String str, String str2, int i, int i2, boolean z) {
        this.mTag = str;
        String e = DfxUtils.e(str2 + DFX_LOG_EXTENSION, z);
        this.mFormatFileName = e;
        if (e.indexOf("%d") == -1) {
            this.mMaxLogFileNum = 1;
        } else {
            this.mMaxLogFileNum = (i <= 1 || i > 5) ? 3 : i;
        }
        this.mMaxLogFileSize = i2 <= 0 ? 1048576 : i2;
    }

    public Context getContext() {
        return BaseApplication.e();
    }

    public File getSaveFile() {
        return getLogFile(DfxUtils.d());
    }

    public static void setLogRootDir(File file) {
        DfxUtils.b = file;
    }

    public static void deleteOtherAllDfxLogFile(File file) {
        FileUtils.d(file, new DfxLogDirDeleteFilter());
    }

    public static void getAllDfxLogFileToPathList(File file, List<String> list) {
        File a2 = DfxUtils.a(file);
        if (a2.exists() && a2.isDirectory()) {
            File[] listFiles = a2.listFiles(new FileFilterUtils.FileExtensionCollectFilter(DFX_LOG_EXTENSION));
            if (CollectionUtils.b(listFiles)) {
                return;
            }
            for (File file2 : listFiles) {
                list.add(file2.getPath());
            }
        }
    }

    public static boolean dumpHprofData(String str, CountDownLatch countDownLatch) {
        if (LogUtil.a()) {
            return false;
        }
        File a2 = DfxUtils.a(DfxUtils.d());
        if (!a2.exists() && !a2.mkdirs()) {
            LogUtil.a(str, "dumpHprofData fail. dfx log dir not exist.");
            return false;
        }
        DfxMonitorCenter.b(new DumpHprofDataRunnalbe(str, a2, countDownLatch), 0L);
        return true;
    }

    public static void deleteHprofData() {
        FileUtils.d(DfxUtils.a(DfxUtils.d()), new FileFilterUtils.FileExtensionCollectFilter(DFX_DUMP_LOG_EXTENSION, 86400000L));
    }

    protected static String getSaveBaseInfo(Thread thread, Throwable th, long j, String str) {
        StringBuilder sb = new StringBuilder(512);
        fillSaveBaseInfo(sb, thread, th, j, str);
        return sb.toString();
    }

    protected static void fillSaveBaseInfo(StringBuilder sb, Thread thread, Throwable th, long j, String str) {
        sb.append("=====");
        sb.append(DfxUtils.a(j));
        sb.append("=====");
        sb.append(System.lineSeparator());
        sb.append("SystemInfo:");
        SystemInfo.a(sb);
        sb.append(System.lineSeparator());
        if (th instanceof OutOfMemoryError) {
            sb.append("MemoryInfo:");
            sb.append(MemoryUtils.xs_(null, false));
            sb.append(System.lineSeparator());
        }
        sb.append("RunningInfo:");
        sb.append("appVerName=");
        sb.append(BaseApplication.a());
        sb.append(", processName=");
        sb.append(ProcessUtil.b());
        sb.append(", pid=");
        sb.append(Process.myPid());
        if (thread != null) {
            sb.append(", threadName:");
            sb.append(thread.getName());
            sb.append(", tid=");
            sb.append(thread.getId());
        }
        if (ProcessUtil.d()) {
            sb.append(", currentActivity:");
            sb.append(BaseApplication.wa_());
        }
        sb.append(System.lineSeparator());
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(System.lineSeparator());
        }
        sb.append("--------------------------------");
        sb.append(System.lineSeparator());
    }

    public final boolean saveInfo(String str, File file) {
        boolean z = false;
        if (file == null || !file.getParentFile().exists()) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            try {
                PrintStream printStream = new PrintStream((OutputStream) fileOutputStream, false, "UTF-8");
                try {
                    printStream.println(str);
                    printStream.println();
                    try {
                        printStream.close();
                        try {
                            fileOutputStream.close();
                            return true;
                        } catch (IOException e) {
                            e = e;
                            z = true;
                            LogUtil.e(this.mTag, "saveInfo ex=", LogUtil.a(e));
                            return z;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = true;
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } finally {
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream.close();
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    protected final File getLogFile(File file) {
        boolean z = this.mMaxLogFileNum == 1;
        File a2 = DfxUtils.a(file);
        if (a2.exists()) {
            if (z) {
                return getFile(a2, z);
            }
            File[] logFiles = getLogFiles(a2);
            checkAndRenameFiles(logFiles);
            return logFiles[0];
        }
        if (a2.mkdirs() || a2.exists()) {
            return getFile(a2, z);
        }
        LogUtil.a(this.mTag, "log dir mkdirs fail.");
        return null;
    }

    private File[] getLogFiles(File file) {
        int i = this.mMaxLogFileNum;
        File[] fileArr = new File[i];
        for (int i2 = 0; i2 < i; i2++) {
            fileArr[i2] = new File(file, String.format(Locale.ENGLISH, this.mFormatFileName, Integer.valueOf(i2)));
        }
        return fileArr;
    }

    private File getFile(File file, boolean z) {
        if (z) {
            return new File(file, this.mFormatFileName);
        }
        return new File(file, String.format(Locale.ENGLISH, this.mFormatFileName, 0));
    }

    private boolean checkAndRenameFiles(File[] fileArr) {
        if (fileArr[0].exists() && fileArr[0].length() < this.mMaxLogFileSize) {
            return false;
        }
        for (int length = fileArr.length - 1; length > 0; length--) {
            int i = length - 1;
            if (fileArr[i].exists()) {
                LogUtil.d(this.mTag, "move to ", Integer.valueOf(length), " ret ", Boolean.valueOf(fileArr[i].renameTo(fileArr[length])));
            }
        }
        return true;
    }

    static class DumpHprofDataRunnalbe implements Runnable {
        private final File b;
        private final CountDownLatch c;
        private final String d;

        DumpHprofDataRunnalbe(String str, File file, CountDownLatch countDownLatch) {
            this.d = str;
            this.b = file;
            this.c = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            FileUtils.d(this.b, new FileFilterUtils.FileExtensionCollectFilter(DfxBaseHandler.DFX_DUMP_LOG_EXTENSION));
            MemoryUtils.e.run();
            StringBuilder sb = new StringBuilder(128);
            sb.append('_');
            sb.append(Process.myPid());
            sb.append('_');
            sb.append(DfxUtils.b("yyyy-MM-dd_HH-mm-ss.SSS", null));
            sb.append(DfxBaseHandler.DFX_DUMP_LOG_EXTENSION);
            if (!MemoryUtils.a(new File(this.b, DfxUtils.a(sb.toString())))) {
                LogUtil.c(this.d, "dumpHprofData fail.");
            }
            e();
        }

        private void e() {
            CountDownLatch countDownLatch = this.c;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    static class DfxLogDirDeleteFilter implements FilenameFilter {
        private DfxLogDirDeleteFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith("dfx_log_") && !str.endsWith(BaseApplication.a());
        }
    }
}
