package com.huawei.haf.common.dfx.process;

import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.dfx.process.ProcessCallback;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hms.network.embedded.g4;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import java.util.List;

/* loaded from: classes.dex */
public class DefaultProcessHandler extends DfxBaseHandler implements ProcessCallback {
    protected void a(String str, String str2, long j, String str3) {
    }

    protected void a(String str, List<String> list, String str2) {
    }

    public DefaultProcessHandler(String str) {
        super(str, "_processLog_%d", 2, 409600, true);
    }

    @Override // com.huawei.haf.common.dfx.process.ProcessCallback
    public void processStart(List<String> list, String str, ProcessCallback.StartInfo startInfo, List<String> list2) {
        if (!CollectionUtils.d(list2)) {
            c(list, list2, startInfo.getStartTime());
        }
        if (startInfo.getLastPid() > 0) {
            c(list, str, startInfo);
            return;
        }
        StringBuilder d = d("process start", str);
        d.append(", pid=");
        d.append(startInfo.getPid());
        d.append(", startTime=");
        d.append(DfxUtils.a(startInfo.getStartTime()));
        String sb = d.toString();
        LogUtil.c(this.mTag, sb);
        b(list, "process start", str, 0L, sb);
    }

    @Override // com.huawei.haf.common.dfx.process.ProcessCallback
    public void processStop(List<String> list, String str, long j, long j2, long j3) {
        long j4 = (j3 - j2) / 1000;
        StringBuilder d = d("process stop", str);
        d.append(", runDuration(s)=");
        d.append(j4);
        d.append(", startTime=");
        d.append(DfxUtils.a(j2));
        d.append(", pid=");
        d.append(j);
        String sb = d.toString();
        LogUtil.c(this.mTag, sb, ", ", ProcessUtil.b());
        b(list, "process stop", str, j4, sb);
    }

    private void c(List<String> list, List<String> list2, long j) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("process kill, names=");
        sb.append(list2);
        sb.append(", checkTime=");
        sb.append(DfxUtils.a(j));
        String sb2 = sb.toString();
        LogUtil.c(this.mTag, sb2, ", ", ProcessUtil.b());
        b(list, "process kill", list2, sb2);
    }

    private void c(List<String> list, String str, ProcessCallback.StartInfo startInfo) {
        long startTime = (startInfo.getStartTime() - startInfo.getLastDieTime()) / 1000;
        StringBuilder d = d("process restart", str);
        d.append(", dieDuration(s)=");
        d.append(startTime);
        d.append(", startTime=");
        d.append(DfxUtils.a(startInfo.getStartTime()));
        d.append(", pid=(");
        d.append(startInfo.getLastPid());
        d.append(" --> ");
        d.append(startInfo.getPid());
        d.append(g4.l);
        String sb = d.toString();
        LogUtil.c(this.mTag, sb);
        b(list, "process restart", str, startTime, sb);
    }

    private void b(List<String> list, String str, List<String> list2, String str2) {
        String c = c(list, str2);
        a(str, list2, c);
        if (saveInfo(c, getSaveFile())) {
            return;
        }
        LogUtil.a(this.mTag, "handleKillProcessInfo fail.");
    }

    private void b(List<String> list, String str, String str2, long j, String str3) {
        String c = c(list, str3);
        a(str, str2, j, c);
        if (saveInfo(c, getSaveFile())) {
            return;
        }
        LogUtil.a(this.mTag, "handleProcessInfo fail.");
    }

    private StringBuilder d(String str, String str2) {
        StringBuilder sb = new StringBuilder(256);
        sb.append(str);
        sb.append(", name=");
        sb.append(str2);
        return sb;
    }

    private String c(List<String> list, String str) {
        StringBuilder sb = new StringBuilder(512);
        fillSaveBaseInfo(sb, null, null, System.currentTimeMillis(), "AliveInfo:" + list);
        sb.append(str);
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
