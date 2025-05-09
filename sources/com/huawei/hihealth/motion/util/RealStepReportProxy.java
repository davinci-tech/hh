package com.huawei.hihealth.motion.util;

import android.os.RemoteException;
import com.huawei.health.IRealStepDataReport;
import com.huawei.hihealth.motion.RealStepCallback;

/* loaded from: classes.dex */
public class RealStepReportProxy extends IRealStepDataReport.Stub {

    /* renamed from: a, reason: collision with root package name */
    private RealStepCallback f4138a;

    public RealStepReportProxy(RealStepCallback realStepCallback) {
        this.f4138a = realStepCallback;
    }

    @Override // com.huawei.health.IRealStepDataReport
    public void report(int i, int i2) throws RemoteException {
        RealStepCallback realStepCallback = this.f4138a;
        if (realStepCallback != null) {
            realStepCallback.onReport(i, i2);
        }
    }
}
