package com.huawei.health.fwk;

import android.os.Bundle;
import com.hihonor.health.fw.IReportCallback;

/* loaded from: classes.dex */
public abstract class BaseStepCounter {
    public abstract int closeStepCounter();

    public abstract int[] getAbility();

    public abstract boolean getHistoryData(long j, long j2, IReportCallback iReportCallback);

    public abstract boolean getHistoryData(long j, long j2, com.huawei.health.fw.IReportCallback iReportCallback);

    public abstract String getVersion();

    public abstract boolean insertDiffStep(Bundle bundle);

    public abstract int openStepCounter();

    public abstract void registerDataCallback(IReportCallback iReportCallback);

    public abstract void registerDataCallback(com.huawei.health.fw.IReportCallback iReportCallback);

    public abstract void registerLogCallback(IReportCallback iReportCallback);

    public abstract void registerLogCallback(com.huawei.health.fw.IReportCallback iReportCallback);

    public abstract void unRegisterDataCallback(IReportCallback iReportCallback);

    public abstract void unRegisterDataCallback(com.huawei.health.fw.IReportCallback iReportCallback);

    public abstract void unRegisterLogCallback(IReportCallback iReportCallback);

    public abstract void unRegisterLogCallback(com.huawei.health.fw.IReportCallback iReportCallback);
}
