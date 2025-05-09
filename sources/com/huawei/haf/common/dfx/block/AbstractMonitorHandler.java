package com.huawei.haf.common.dfx.block;

import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public abstract class AbstractMonitorHandler extends DfxBaseHandler implements MonitorCallback {
    private long mBeginTime;
    private String mMessageInfo;

    protected void dumpMessagesInfo(PrintWriter printWriter, long j, boolean z, boolean z2) {
    }

    @Override // com.huawei.haf.common.dfx.block.MonitorCallback
    public long monitorTimeInterval() {
        return 1000L;
    }

    protected void onSendBlockInfo(String str, String str2) {
    }

    public AbstractMonitorHandler(String str, String str2) {
        super(str, str2);
    }

    @Override // com.huawei.haf.common.dfx.block.MonitorCallback
    public void begin(Thread thread, String str, long j) {
        this.mMessageInfo = str;
        this.mBeginTime = j;
    }

    @Override // com.huawei.haf.common.dfx.block.MonitorCallback
    public final long getBeginTime() {
        return this.mBeginTime;
    }

    protected final String getMessageInfo() {
        return this.mMessageInfo;
    }

    protected final void saveDumpStackTraceInfo(Thread thread, boolean z) {
        saveDumpStackTraceInfo(thread, z, false, false);
    }

    protected final void saveDumpStackTraceInfo(Thread thread, boolean z, boolean z2, boolean z3) {
        String dumpStackTraceInfo = dumpStackTraceInfo(thread, z2, z3);
        if (z) {
            onSendBlockInfo(getMessageInfo(), dumpStackTraceInfo);
        }
        saveBlockInfo(dumpStackTraceInfo);
    }

    private String dumpStackTraceInfo(Thread thread, boolean z, boolean z2) {
        long currentTimeMillis = System.currentTimeMillis();
        StringWriter stringWriter = new StringWriter(4096);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.append((CharSequence) getSaveBaseInfo(thread, null, currentTimeMillis, getMessageInfo()));
        dumpStackTraceInfo(printWriter, thread, getBeginTime(), currentTimeMillis);
        if (z || z2) {
            dumpMessagesInfo(printWriter, currentTimeMillis, z, z2);
        }
        printWriter.close();
        return stringWriter.toString();
    }

    private static void dumpStackTraceInfo(PrintWriter printWriter, Thread thread, long j, long j2) {
        printWriter.append("DUMP: From ").append((CharSequence) DfxUtils.a(j)).append(", Task running for more than ").append((CharSequence) String.valueOf(j2 - j)).println("ms");
        ExceptionUtils.b(thread, (Throwable) null, printWriter);
    }

    private void saveBlockInfo(String str) {
        if (saveInfo(str, getSaveFile())) {
            return;
        }
        LogUtil.a(this.mTag, "saveBlockInfo fail.");
    }
}
