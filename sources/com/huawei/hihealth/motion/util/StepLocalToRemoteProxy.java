package com.huawei.hihealth.motion.util;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.health.IStepDataReport;
import com.huawei.hihealth.motion.ICommonReport;

/* loaded from: classes.dex */
public class StepLocalToRemoteProxy extends IStepDataReport.Stub {

    /* renamed from: a, reason: collision with root package name */
    private ICommonReport f4139a;

    public ICommonReport getSelf() {
        return this.f4139a;
    }

    public StepLocalToRemoteProxy(ICommonReport iCommonReport) {
        this.f4139a = iCommonReport;
    }

    public boolean isScameLocalCallback(ICommonReport iCommonReport) {
        return this.f4139a == iCommonReport;
    }

    @Override // com.huawei.health.IStepDataReport
    public void report(Bundle bundle) throws RemoteException {
        ICommonReport iCommonReport = this.f4139a;
        if (iCommonReport != null) {
            iCommonReport.report(bundle);
        }
    }
}
