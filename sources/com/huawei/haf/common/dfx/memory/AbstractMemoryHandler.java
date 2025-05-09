package com.huawei.haf.common.dfx.memory;

import android.content.SharedPreferences;
import android.os.Debug;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.os.MemoryUtils;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public abstract class AbstractMemoryHandler extends DfxBaseHandler implements MemoryCallback {
    private static final long FIVE_MINUTE_DURATION = 300000;
    private static final String LAST_KILL_PROCESS_TIME = "last_kill_process_time";
    private static final int LOG_PRINT_COUNT_THRESHOLD = 5;
    private static final String MEMORY_LOG_FORMAT = "_memoryLog_%d";
    private static final int SHIFT_OPERATION_BITS = 10;
    private static final long TEN_MINUTE_DURATION = 600000;
    private long mLastEnterCheckTime;
    private final MemoryCallback mMemoryCallback;
    private final ProcessMonitorInfo mProcessMonitorInfo;

    protected void onSendLeakInfo(String str, String str2) {
    }

    protected void onSendMemoryInfo(String str, String str2) {
    }

    public AbstractMemoryHandler(String str) {
        super(str, MEMORY_LOG_FORMAT);
        this.mMemoryCallback = this;
        MemoryMonitorConfig monitorConfig = getMonitorConfig();
        if (monitorConfig != null && monitorConfig.b() > 0) {
            this.mProcessMonitorInfo = new ProcessMonitorInfo(monitorConfig);
        } else {
            this.mProcessMonitorInfo = null;
        }
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public void check(boolean z) {
        if (this.mProcessMonitorInfo == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastEnterCheckTime < 60000) {
            return;
        }
        this.mLastEnterCheckTime = currentTimeMillis;
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        long totalPss = memoryInfo.getTotalPss() >> 10;
        ProcessMonitorInfo processMonitorInfo = this.mProcessMonitorInfo;
        if (isReachKillThreshold(totalPss, processMonitorInfo, memoryInfo)) {
            if (processKillThreshold(currentTimeMillis, totalPss, z, memoryInfo)) {
                return;
            }
        } else {
            processBaseThreshold(currentTimeMillis, totalPss, processMonitorInfo);
            processMonitorInfo.b();
        }
        processMonitorInfo.j++;
        if (processMonitorInfo.j > 5 || Math.abs(totalPss - processMonitorInfo.i) >= 5) {
            LogUtil.d(this.mTag, "pssSizeMB from ", Long.valueOf(processMonitorInfo.i), " to ", Long.valueOf(totalPss));
            MemoryUtils.xs_(memoryInfo, true);
            processMonitorInfo.i = totalPss;
            processMonitorInfo.j = 0;
        }
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public void reportLeak(String str, String str2, String str3, long j) {
        StringBuilder sb = new StringBuilder(256);
        fillSaveBaseInfo(sb, null, null, j, str2);
        sb.append(str3);
        sb.append(System.lineSeparator());
        String sb2 = sb.toString();
        onSendLeakInfo(str, sb2);
        saveMemoryInfo(sb2);
    }

    private boolean isReachKillThreshold(long j, ProcessMonitorInfo processMonitorInfo, Debug.MemoryInfo memoryInfo) {
        boolean z = j > ((long) processMonitorInfo.f.c());
        int minKillDexThresholdSize = this.mMemoryCallback.minKillDexThresholdSize();
        if (minKillDexThresholdSize <= 0) {
            return z;
        }
        processMonitorInfo.d = (MemoryUtils.xu_(memoryInfo, 10) >> 10) + (MemoryUtils.xv_(memoryInfo, 10) >> 10);
        if (processMonitorInfo.d <= minKillDexThresholdSize) {
            return z;
        }
        this.mMemoryCallback.reportDexOverThresholdSize(processMonitorInfo.d);
        return true;
    }

    private void processBaseThreshold(long j, long j2, ProcessMonitorInfo processMonitorInfo) {
        if (j2 > processMonitorInfo.f.b()) {
            handleBaseThreshold(j, j2, processMonitorInfo);
        } else {
            processMonitorInfo.d();
        }
    }

    private void handleBaseThreshold(long j, long j2, ProcessMonitorInfo processMonitorInfo) {
        if (processMonitorInfo.h == 0) {
            processMonitorInfo.h = j;
            processMonitorInfo.f2086a = false;
        } else {
            if (processMonitorInfo.f2086a || j2 <= processMonitorInfo.f.d() || j - processMonitorInfo.h <= 300000) {
                return;
            }
            processMonitorInfo.f2086a = true;
            ReleaseLogUtil.b(this.mTag, "memory over base threshold need run gc, pss = ", Long.valueOf(j2));
            DfxMonitorCenter.a();
        }
    }

    private boolean processKillThreshold(long j, long j2, boolean z, Debug.MemoryInfo memoryInfo) {
        ProcessMonitorInfo processMonitorInfo = this.mProcessMonitorInfo;
        if (handleKillThreshold(j, j2, processMonitorInfo, memoryInfo) && z && j - processMonitorInfo.g > 600000) {
            long minKillTimeInterval = this.mMemoryCallback.minKillTimeInterval();
            if (minKillTimeInterval <= 0 || j - processMonitorInfo.c > minKillTimeInterval) {
                ReleaseLogUtil.a(this.mTag, "memory over kill threshold, begin kill", " ", ProcessUtil.b(), ", pss=", Long.valueOf(j2), ", minKillTimeInterval=", Long.valueOf(minKillTimeInterval));
                saveDumpMemoryInfo(memoryInfo, j, "memory over kill threshold, begin kill", true);
                setLastKillProcessTime(j);
                processMonitorInfo.c = j;
                Process.killProcess(Process.myPid());
                return true;
            }
        }
        return false;
    }

    private void saveDumpMemoryInfo(Debug.MemoryInfo memoryInfo, long j, String str, boolean z) {
        StringBuilder sb = new StringBuilder(4096);
        fillSaveBaseInfo(sb, null, null, j, str);
        fillMemoryInfo(sb, memoryInfo);
        String sb2 = sb.toString();
        if (z) {
            onSendMemoryInfo("memory over baseline self kill process", sb2);
        }
        saveMemoryInfo(sb2);
    }

    private void saveMemoryInfo(String str) {
        if (saveInfo(str, getSaveFile())) {
            return;
        }
        LogUtil.a(this.mTag, "saveMemoryInfo fail.");
    }

    private void fillMemoryInfo(StringBuilder sb, Debug.MemoryInfo memoryInfo) {
        ProcessMonitorInfo processMonitorInfo = this.mProcessMonitorInfo;
        if (processMonitorInfo != null) {
            sb.append("BasePssThreshold(MB):");
            sb.append(processMonitorInfo.f.b());
            sb.append(", ReachBaseThresholdStartTime:");
            sb.append(DfxUtils.a(processMonitorInfo.h));
            sb.append(System.lineSeparator());
            sb.append("KillPssThreshold(MB):");
            sb.append(processMonitorInfo.f.c());
            sb.append(", ReachKillThresholdStartTime:");
            sb.append(DfxUtils.a(processMonitorInfo.g));
            sb.append(System.lineSeparator());
            if (processMonitorInfo.d > 0) {
                sb.append("KillDexThresholdSize(MB):");
                sb.append(this.mMemoryCallback.minKillDexThresholdSize());
                sb.append(", CurrentDexSize(MB):");
                sb.append(processMonitorInfo.d);
                sb.append(System.lineSeparator());
            }
        }
        String e = MemoryUtils.e(false);
        if (!TextUtils.isEmpty(e)) {
            sb.append(e);
            sb.append(System.lineSeparator());
        }
        sb.append(MemoryUtils.xs_(memoryInfo, true));
        sb.append(System.lineSeparator());
        if (memoryInfo != null) {
            sb.append("--------------------------------");
            sb.append(System.lineSeparator());
            sb.append(MemoryUtils.xt_(memoryInfo));
            sb.append(System.lineSeparator());
        }
    }

    private boolean handleKillThreshold(long j, long j2, ProcessMonitorInfo processMonitorInfo, Debug.MemoryInfo memoryInfo) {
        if (processMonitorInfo.c == 0) {
            processMonitorInfo.c = getLastKillProcessTime();
            DfxMonitorCenter.a();
            return false;
        }
        if (processMonitorInfo.g == 0) {
            processMonitorInfo.g = j;
            processMonitorInfo.e = false;
            return false;
        }
        if (!processMonitorInfo.e && (j2 > processMonitorInfo.f.e() || j - processMonitorInfo.g > 300000)) {
            processMonitorInfo.e = true;
            ReleaseLogUtil.b(this.mTag, "memory over kill threshold need run gc, pss = ", Long.valueOf(j2));
            DfxMonitorCenter.a();
            return false;
        }
        if (BaseApplication.j()) {
            return false;
        }
        if (processMonitorInfo.b) {
            return true;
        }
        processMonitorInfo.b = true;
        ReleaseLogUtil.b(this.mTag, "memory over kill threshold need dump hprof data", ", pss = ", Long.valueOf(j2));
        saveDumpMemoryInfo(memoryInfo, j, "memory over kill threshold need dump hprof data", false);
        DfxBaseHandler.dumpHprofData(this.mTag, null);
        return false;
    }

    protected final long getLastKillProcessTime() {
        SharedPreferences sharedPreferences = BaseApplication.e().getSharedPreferences(getSaveFileName(), 0);
        long j = sharedPreferences != null ? sharedPreferences.getLong(LAST_KILL_PROCESS_TIME, -1L) : -1L;
        ReleaseLogUtil.b(this.mTag, " getLastKillProcessTime = ", Long.valueOf(j));
        return j;
    }

    protected final void setLastKillProcessTime(long j) {
        ReleaseLogUtil.b(this.mTag, "setLastKillProcessTime = ", Long.valueOf(j));
        SharedPreferences.Editor edit = BaseApplication.e().getSharedPreferences(getSaveFileName(), 0).edit();
        if (edit != null) {
            edit.putLong(LAST_KILL_PROCESS_TIME, j);
            edit.commit();
        }
    }

    private String getSaveFileName() {
        return DfxUtils.a("_crash");
    }

    static class ProcessMonitorInfo {

        /* renamed from: a, reason: collision with root package name */
        boolean f2086a;
        boolean b;
        long c;
        long d;
        boolean e;
        final MemoryMonitorConfig f;
        long g;
        long h;
        long i;
        int j;

        ProcessMonitorInfo(MemoryMonitorConfig memoryMonitorConfig) {
            this.f = memoryMonitorConfig;
        }

        void d() {
            this.h = 0L;
            this.b = false;
        }

        void b() {
            this.g = 0L;
            this.b = false;
        }
    }
}
