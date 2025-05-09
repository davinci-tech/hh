package com.huawei.agconnect.apms;

import android.util.Log;
import com.huawei.agconnect.apms.log.AgentLog;

/* loaded from: classes2.dex */
public class p implements AgentLog {
    public int abc = 4;

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void debug(String str) {
        if (this.abc == 3) {
            Log.d(AgentLog.TAG, str);
        }
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void error(String str) {
        if (this.abc <= 6) {
            Log.e(AgentLog.TAG, str);
        }
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public int getLevel() {
        return this.abc;
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void info(String str) {
        if (this.abc <= 4) {
            Log.i(AgentLog.TAG, str);
        }
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void setLevel(int i) {
        if (i < 3 || i > 6) {
            throw new IllegalArgumentException("Log level is not between DEBUG and ERROR");
        }
        this.abc = i;
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void warn(String str) {
        if (this.abc <= 5) {
            Log.w(AgentLog.TAG, str);
        }
    }

    @Override // com.huawei.agconnect.apms.log.AgentLog
    public void error(String str, Throwable th) {
        if (this.abc <= 6) {
            Log.e(AgentLog.TAG, str, th);
        }
    }
}
