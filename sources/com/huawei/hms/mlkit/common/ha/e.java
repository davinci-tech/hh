package com.huawei.hms.mlkit.common.ha;

import java.util.TimerTask;

/* loaded from: classes4.dex */
public class e extends TimerTask {
    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        try {
            c.f5047a.a();
        } catch (Exception unused) {
            b.a("HaLogOnReport", "Failed to report hianalytics data");
        }
    }
}
