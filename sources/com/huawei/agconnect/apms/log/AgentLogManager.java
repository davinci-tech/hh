package com.huawei.agconnect.apms.log;

import com.huawei.agconnect.apms.q;

/* loaded from: classes.dex */
public class AgentLogManager {
    private static q instance = new q();

    public static AgentLog getAgentLog() {
        return instance;
    }
}
