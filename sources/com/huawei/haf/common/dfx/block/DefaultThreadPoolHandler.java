package com.huawei.haf.common.dfx.block;

import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.threadpool.ThreadPoolCallback;
import com.huawei.haf.threadpool.ThreadPoolStateInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.File;

/* loaded from: classes.dex */
public class DefaultThreadPoolHandler extends DfxBaseHandler implements ThreadPoolCallback {
    private static volatile int e;

    protected void d(String str, String str2) {
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolCallback
    public boolean isMonitorTask() {
        return true;
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolCallback
    public long minStatisticsTimeInterval() {
        return 5000L;
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolCallback
    public long statisticsTimeInterval() {
        return 120000L;
    }

    static /* synthetic */ int a() {
        int i = e + 1;
        e = i;
        return i;
    }

    public DefaultThreadPoolHandler(String str) {
        super(str, "_threadpoolLog_%d");
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolCallback
    public ThreadMonitorTask createBlockMonitorTask(Thread thread) {
        return new ThreadMonitorTask(thread, new ThreadTaskMonitorHandler(this.mTag));
    }

    @Override // com.huawei.haf.threadpool.ThreadPoolCallback
    public void threadPoolStateInfo(String str, ThreadPoolStateInfo threadPoolStateInfo, long j) {
        String e2 = e(str, threadPoolStateInfo);
        ReleaseLogUtil.b(this.mTag, e2);
        if (threadPoolStateInfo.d() < threadPoolStateInfo.i()) {
            return;
        }
        StringBuilder sb = new StringBuilder(512);
        fillSaveBaseInfo(sb, null, null, j, null);
        sb.append(e2);
        sb.append(System.lineSeparator());
        String sb2 = sb.toString();
        d(str, sb2);
        b(sb2);
    }

    private void b(String str) {
        if (saveInfo(str, getSaveFile())) {
            return;
        }
        LogUtil.a(this.mTag, "saveThreadPoolInfo fail.");
    }

    private String e(String str, ThreadPoolStateInfo threadPoolStateInfo) {
        StringBuilder sb = new StringBuilder(256);
        sb.append(str);
        sb.append(", state{[");
        sb.append(threadPoolStateInfo.i());
        sb.append(", ");
        sb.append(threadPoolStateInfo.a());
        sb.append(", ");
        sb.append(threadPoolStateInfo.b());
        sb.append("], poolSize=");
        sb.append(threadPoolStateInfo.f());
        sb.append(", activeCount=");
        sb.append(threadPoolStateInfo.d());
        sb.append(", runNum=");
        sb.append(threadPoolStateInfo.k());
        sb.append(", waitNum=(");
        sb.append(threadPoolStateInfo.m());
        sb.append(", ");
        sb.append(threadPoolStateInfo.c());
        sb.append(", ");
        sb.append(threadPoolStateInfo.g());
        sb.append(Constants.RIGHT_BRACKET_ONLY);
        if (threadPoolStateInfo.h() > 0) {
            sb.append("}, stage{interval=");
            sb.append(threadPoolStateInfo.o());
            sb.append(", cmpNum=");
            sb.append(threadPoolStateInfo.h());
            sb.append(", avgRunTime=");
            sb.append(threadPoolStateInfo.j() / threadPoolStateInfo.h());
            sb.append(", maxRunTime=");
            sb.append(threadPoolStateInfo.l());
        }
        if (threadPoolStateInfo.n() > 0) {
            sb.append("}, total{taskCmpNum=");
            sb.append(threadPoolStateInfo.n());
            sb.append(", avgRunTime=");
            sb.append(threadPoolStateInfo.q() / threadPoolStateInfo.n());
            sb.append(", maxRunTime=");
            sb.append(threadPoolStateInfo.e());
        }
        sb.append('}');
        return sb.toString();
    }

    public class ThreadTaskMonitorHandler extends AbstractMonitorHandler {
        private boolean c;

        protected long b() {
            return 5000L;
        }

        public ThreadTaskMonitorHandler(String str) {
            super(str, "_threadpoolLog_%d");
        }

        @Override // com.huawei.haf.common.dfx.DfxBaseHandler
        public File getSaveFile() {
            return DefaultThreadPoolHandler.this.getSaveFile();
        }

        @Override // com.huawei.haf.common.dfx.block.AbstractMonitorHandler, com.huawei.haf.common.dfx.block.MonitorCallback
        public long monitorTimeInterval() {
            return this.c ? 300000L : 5000L;
        }

        @Override // com.huawei.haf.common.dfx.block.AbstractMonitorHandler, com.huawei.haf.common.dfx.block.MonitorCallback
        public void begin(Thread thread, String str, long j) {
            super.begin(thread, str, j);
            this.c = false;
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public boolean check(Thread thread, long j, long j2) {
            if (j2 <= 0) {
                return false;
            }
            if (!this.c) {
                if (DefaultThreadPoolHandler.e < 10 && j - getBeginTime() >= b()) {
                    saveDumpStackTraceInfo(thread, true);
                    DefaultThreadPoolHandler.a();
                }
                this.c = true;
                return true;
            }
            saveDumpStackTraceInfo(thread, true);
            return false;
        }

        @Override // com.huawei.haf.common.dfx.block.MonitorCallback
        public void end(Thread thread, Throwable th, long j, long j2) {
            long beginTime = j - getBeginTime();
            if (th != null) {
                ReleaseLogUtil.a(this.mTag, e(getMessageInfo(), thread, th, beginTime));
                return;
            }
            if (beginTime < 3000 || j2 <= 0) {
                return;
            }
            if (beginTime >= PreConnectManager.CONNECT_INTERNAL) {
                ReleaseLogUtil.a(this.mTag, e(getMessageInfo(), thread, null, beginTime));
            } else if (beginTime >= 5000) {
                ReleaseLogUtil.b(this.mTag, e(getMessageInfo(), thread, null, beginTime));
            } else if (LogUtil.e()) {
                LogUtil.d(this.mTag, e(getMessageInfo(), thread, null, beginTime));
            }
        }

        private String e(String str, Thread thread, Throwable th, long j) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Time-consuming task(");
            sb.append(j);
            sb.append("ms): thread(");
            sb.append(thread.getName());
            sb.append(", ");
            sb.append(thread.getId());
            sb.append("), ");
            sb.append(str);
            if (th != null) {
                sb.append(", throwable=");
                sb.append(LogUtil.a(th));
            }
            return sb.toString();
        }
    }
}
