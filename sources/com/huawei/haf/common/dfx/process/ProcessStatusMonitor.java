package com.huawei.haf.common.dfx.process;

import android.content.Intent;
import android.os.FileObserver;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.process.ProcessCallback;
import com.huawei.haf.common.os.FileLockHelper;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class ProcessStatusMonitor implements Closeable {
    private final MyFileObserver c;

    ProcessStatusMonitor(List<String> list, ProcessCallback processCallback) {
        File dir = BaseApplication.e().getDir("proc_status", 0);
        this.c = new MyFileObserver(FileUtils.a(new File(dir, "proc_status")), FileUtils.a(new File(dir, "proc_flag")), processCallback, list);
    }

    void b() {
        this.c.d();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.c.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str, InnerProcessStatus innerProcessStatus, InnerProcessStatus innerProcessStatus2) {
        if (innerProcessStatus.f2089a == innerProcessStatus2.f2089a || !innerProcessStatus2.c) {
            return;
        }
        innerProcessStatus.e(innerProcessStatus2);
        d(str, true, innerProcessStatus.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
    
        if (health.compact.a.ProcessUtil.b().equals(r13.get(0)) == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.util.List<java.lang.String> r13, java.lang.String r14, com.huawei.haf.common.dfx.process.ProcessStatusMonitor.InnerProcessStatus r15, com.huawei.haf.common.dfx.process.ProcessStatusMonitor.InnerProcessStatus r16, com.huawei.haf.common.dfx.process.ProcessCallback r17) {
        /*
            r9 = r14
            r10 = r15
            r11 = r16
            int r0 = r10.f2089a
            int r1 = r11.f2089a
            if (r0 != r1) goto L42
            boolean r0 = r11.c
            if (r0 != 0) goto L42
            int r0 = r13.size()
            r1 = 1
            r12 = 0
            if (r0 <= r1) goto L26
            java.lang.String r0 = health.compact.a.ProcessUtil.b()
            r1 = r13
            java.lang.Object r2 = r13.get(r12)
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L3a
            goto L27
        L26:
            r1 = r13
        L27:
            long r2 = r11.d
            a(r14, r2)
            int r0 = r10.f2089a
            long r3 = (long) r0
            long r5 = r10.e
            long r7 = r11.d
            r0 = r17
            r1 = r13
            r2 = r14
            r0.processStop(r1, r2, r3, r5, r7)
        L3a:
            r15.e(r16)
            long r0 = r10.d
            d(r14, r12, r0)
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.common.dfx.process.ProcessStatusMonitor.a(java.util.List, java.lang.String, com.huawei.haf.common.dfx.process.ProcessStatusMonitor$InnerProcessStatus, com.huawei.haf.common.dfx.process.ProcessStatusMonitor$InnerProcessStatus, com.huawei.haf.common.dfx.process.ProcessCallback):void");
    }

    private static void a(String str, long j) {
        if (BaseApplication.d().equals(str) && BaseApplication.j()) {
            Intent intent = new Intent(HwWatchFaceApi.ACTION_FOREGROUND_STATUS);
            intent.setPackage(BaseApplication.d());
            intent.putExtra("isForeground", false);
            intent.putExtra("time", j);
            BaseApplication.e().sendBroadcast(intent, SecurityConstant.d);
        }
    }

    private static void d(String str, boolean z, long j) {
        Intent intent = new Intent("com.huawei.haf.intent.action.PROCESS_STATUS_CHANGE");
        intent.putExtra("processName", str);
        intent.putExtra("isRunning", z);
        intent.putExtra("time", j);
        LocalBroadcastManager.getInstance(BaseApplication.e()).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(FileLockHelper fileLockHelper, long j) {
        if (fileLockHelper == null || !fileLockHelper.c()) {
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(12);
        allocate.putInt(Process.myPid());
        allocate.putLong(j);
        try {
            fileLockHelper.c(allocate.array());
        } catch (IOException e) {
            LogUtil.e("HAF_ProcessStatus", "writeProcessStatus, ex=", LogUtil.a(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(File file, String str, InnerProcessStatus innerProcessStatus) throws IOException {
        if (innerProcessStatus != null && innerProcessStatus.d == LocationRequestCompat.PASSIVE_INTERVAL) {
            innerProcessStatus.d = file.lastModified();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            if (innerProcessStatus == null) {
                boolean d = d(str, randomAccessFile);
                randomAccessFile.close();
                return d;
            }
            if (file.length() == 12) {
                innerProcessStatus.f2089a = randomAccessFile.readInt();
                innerProcessStatus.e = randomAccessFile.readLong();
            }
            innerProcessStatus.c = d(str, randomAccessFile);
            boolean z = innerProcessStatus.c;
            randomAccessFile.close();
            return z;
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static boolean d(String str, RandomAccessFile randomAccessFile) throws IOException {
        if (ProcessUtil.b().equals(str)) {
            return false;
        }
        return b(randomAccessFile);
    }

    private static boolean b(RandomAccessFile randomAccessFile) throws IOException {
        boolean z;
        synchronized (ProcessStatusMonitor.class) {
            try {
                FileLock tryLock = randomAccessFile.getChannel().tryLock(0L, LocationRequestCompat.PASSIVE_INTERVAL, true);
                z = tryLock == null;
                if (tryLock != null) {
                    try {
                        tryLock.close();
                    } catch (OverlappingFileLockException e) {
                        e = e;
                        LogUtil.a("HAF_ProcessStatus", "isOtherProcessAlive, ex=", LogUtil.a(e));
                        return z;
                    }
                }
            } catch (OverlappingFileLockException e2) {
                e = e2;
                z = true;
            }
        }
        return z;
    }

    static class MyFileObserver extends FileObserver {

        /* renamed from: a, reason: collision with root package name */
        private FileLockHelper f2091a;
        private final File b;
        private final Map<String, InnerProcessStatus> c;
        private final File d;
        private final ProcessCallback e;

        MyFileObserver(File file, File file2, ProcessCallback processCallback, List<String> list) {
            super(file.getPath(), 10);
            this.c = new HashMap();
            this.d = file;
            this.b = file2;
            this.e = processCallback;
            DfxMonitorCenter.b(new MonitorRunnable(this, list));
        }

        void d() {
            if (this.f2091a != null) {
                e(System.currentTimeMillis());
            }
        }

        void c() {
            d();
            DfxMonitorCenter.b(new MonitorRunnable(this, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(List<String> list) {
            long currentTimeMillis = System.currentTimeMillis() - (SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime());
            FileLockHelper fileLockHelper = null;
            try {
                b(list, currentTimeMillis);
                fileLockHelper = FileLockHelper.c(b(ProcessUtil.b()));
                ProcessStatusMonitor.a(fileLockHelper, currentTimeMillis);
                startWatching();
                this.f2091a = fileLockHelper;
            } catch (IOException e) {
                LogUtil.e("HAF_ProcessStatus", "init process status monitor, ex=", LogUtil.a(e));
                FileUtils.d(fileLockHelper);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            FileLockHelper fileLockHelper = this.f2091a;
            this.f2091a = null;
            if (fileLockHelper != null) {
                stopWatching();
                fileLockHelper.close();
            }
        }

        private List<String> e(String str, long j, InnerProcessStatus innerProcessStatus) {
            File file;
            ArrayList arrayList = new ArrayList(this.c.size() + 1);
            arrayList.add(e(j));
            for (Map.Entry<String, InnerProcessStatus> entry : this.c.entrySet()) {
                String key = entry.getKey();
                InnerProcessStatus value = entry.getValue();
                File b = b(key);
                if (innerProcessStatus == null || !key.equals(str)) {
                    file = b;
                    value.c = b(file, key, value.f2089a, j, null);
                } else {
                    file = b;
                    value.c = b(b, key, value.f2089a, j, innerProcessStatus);
                }
                if (value.c) {
                    arrayList.add(key);
                }
                if (key.equals(str)) {
                    long c = c(key, file, value.c, null);
                    if (c != LocationRequestCompat.PASSIVE_INTERVAL && innerProcessStatus != null && innerProcessStatus.d == LocationRequestCompat.PASSIVE_INTERVAL) {
                        innerProcessStatus.d = c;
                    }
                }
            }
            return d(arrayList);
        }

        private List<String> d(List<String> list) {
            if (list.size() > 1) {
                Collections.sort(list);
            }
            return list;
        }

        private String e(long j) {
            String b = ProcessUtil.b();
            File b2 = b(b);
            if (!b2.exists() || b2.length() != 12) {
                b2.getParentFile().mkdirs();
                ProcessStatusMonitor.a(this.f2091a, j);
            } else {
                b2.setLastModified(j);
            }
            c(b, b2, true, null);
            return b;
        }

        private boolean b(File file, String str, int i, long j, InnerProcessStatus innerProcessStatus) {
            try {
                if (file.exists()) {
                    return ProcessStatusMonitor.b(file, str, innerProcessStatus);
                }
            } catch (IOException e) {
                LogUtil.c("HAF_ProcessStatus", "updateOtherStatus fail, ex=", LogUtil.a(e));
            }
            boolean equals = i > 0 ? str.equals(ProcessUtil.a(i)) : false;
            if (innerProcessStatus != null) {
                innerProcessStatus.c = equals;
                if (innerProcessStatus.d == LocationRequestCompat.PASSIVE_INTERVAL) {
                    innerProcessStatus.d = j;
                }
            }
            return equals;
        }

        private File b(String str) {
            return new File(this.d, str);
        }

        private long c(String str, File file, boolean z, File file2) {
            if (file2 == null) {
                file2 = a(str);
            }
            boolean exists = file2.exists();
            long lastModified = exists ? file2.lastModified() : LocationRequestCompat.PASSIVE_INTERVAL;
            if (z) {
                if (exists) {
                    FileUtils.d(file2);
                }
                return lastModified;
            }
            if (!exists) {
                try {
                    file2.getParentFile().mkdirs();
                    file2.createNewFile();
                    lastModified = file2.lastModified();
                    if (file.exists()) {
                        file.setLastModified(lastModified);
                    }
                } catch (IOException e) {
                    LogUtil.a("HAF_ProcessStatus", "updateDieFile fail, ex=", LogUtil.a(e));
                }
            }
            return lastModified;
        }

        private File a(String str) {
            return new File(this.b, str);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            InnerProcessStatus innerProcessStatus = this.c.get(str);
            if (innerProcessStatus == null) {
                e(str, currentTimeMillis, null);
                return;
            }
            int i2 = i & 4095;
            if (i2 == 2) {
                InnerProcessStatus e = new InnerProcessStatus().e(innerProcessStatus);
                e.d = LocationRequestCompat.PASSIVE_INTERVAL;
                e(str, currentTimeMillis, e);
                ProcessStatusMonitor.a(str, innerProcessStatus, e);
                return;
            }
            if (i2 == 8) {
                InnerProcessStatus e2 = new InnerProcessStatus().e(innerProcessStatus);
                e2.d = currentTimeMillis;
                ProcessStatusMonitor.a(e(str, currentTimeMillis, e2), str, innerProcessStatus, e2, this.e);
                return;
            }
            e(str, currentTimeMillis, null);
        }

        private void b(List<String> list, long j) {
            int i;
            InnerProcessStatus innerProcessStatus;
            ArrayList arrayList;
            String str;
            String b = ProcessUtil.b();
            InnerProcessStatus innerProcessStatus2 = new InnerProcessStatus();
            ArrayList arrayList2 = new ArrayList(list.size());
            ArrayList arrayList3 = new ArrayList(list.size());
            for (String str2 : list) {
                File b2 = b(str2);
                File a2 = a(str2);
                InnerProcessStatus innerProcessStatus3 = new InnerProcessStatus();
                boolean b3 = b(b2, str2, -1, j, innerProcessStatus3);
                if (b.equals(str2)) {
                    arrayList3.add(str2);
                    innerProcessStatus2.e(innerProcessStatus3);
                    if (!a2.exists()) {
                        if (b2.exists()) {
                            arrayList2.add(str2);
                        }
                    } else {
                        innerProcessStatus2.d = c(str2, b2, true, a2);
                    }
                } else {
                    if (b3) {
                        arrayList3.add(str2);
                    } else if (!a2.exists()) {
                        arrayList2.add(str2);
                    }
                    this.c.put(str2, innerProcessStatus3);
                }
            }
            List<String> d = d(arrayList3);
            if (innerProcessStatus2.f2089a <= 0 || !arrayList2.contains(b)) {
                i = 1;
                innerProcessStatus = innerProcessStatus2;
                arrayList = arrayList2;
                str = b;
            } else {
                i = 1;
                innerProcessStatus = innerProcessStatus2;
                arrayList = arrayList2;
                str = b;
                this.e.processStop(d, b, innerProcessStatus2.f2089a, innerProcessStatus2.e, innerProcessStatus2.d);
            }
            if (d.size() > i) {
                arrayList.clear();
            }
            this.e.processStart(d, str, new InnerStartInfo(innerProcessStatus.f2089a, innerProcessStatus.d, Process.myPid(), j), arrayList);
        }
    }

    static class MonitorRunnable implements Runnable {
        private final MyFileObserver c;
        private final List<String> d;

        MonitorRunnable(MyFileObserver myFileObserver, List<String> list) {
            this.c = myFileObserver;
            this.d = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<String> list = this.d;
            if (list != null) {
                this.c.b(list);
            } else {
                this.c.e();
            }
        }
    }

    static class InnerStartInfo implements ProcessCallback.StartInfo {

        /* renamed from: a, reason: collision with root package name */
        private long f2090a;
        private int b;
        private int d;
        private long e;

        InnerStartInfo(int i, long j, int i2, long j2) {
            this.b = i;
            this.e = j;
            this.d = i2;
            this.f2090a = j2;
        }

        @Override // com.huawei.haf.common.dfx.process.ProcessCallback.StartInfo
        public long getLastPid() {
            return this.b;
        }

        @Override // com.huawei.haf.common.dfx.process.ProcessCallback.StartInfo
        public long getLastDieTime() {
            return this.e;
        }

        @Override // com.huawei.haf.common.dfx.process.ProcessCallback.StartInfo
        public long getPid() {
            return this.d;
        }

        @Override // com.huawei.haf.common.dfx.process.ProcessCallback.StartInfo
        public long getStartTime() {
            return this.f2090a;
        }
    }

    static class InnerProcessStatus {

        /* renamed from: a, reason: collision with root package name */
        int f2089a;
        boolean c;
        long d;
        long e;

        private InnerProcessStatus() {
            this.d = LocationRequestCompat.PASSIVE_INTERVAL;
            this.c = true;
        }

        InnerProcessStatus e(InnerProcessStatus innerProcessStatus) {
            this.f2089a = innerProcessStatus.f2089a;
            this.e = innerProcessStatus.e;
            this.d = innerProcessStatus.d;
            this.c = innerProcessStatus.c;
            return this;
        }
    }
}
