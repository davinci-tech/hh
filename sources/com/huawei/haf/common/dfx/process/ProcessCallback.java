package com.huawei.haf.common.dfx.process;

import java.util.List;

/* loaded from: classes.dex */
public interface ProcessCallback {

    public interface StartInfo {
        long getLastDieTime();

        long getLastPid();

        long getPid();

        long getStartTime();
    }

    void processStart(List<String> list, String str, StartInfo startInfo, List<String> list2);

    void processStop(List<String> list, String str, long j, long j2, long j3);
}
