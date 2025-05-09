package com.huawei.hihealth.motion.service.stepcounter;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.motion.RealStepCallback;

/* loaded from: classes.dex */
public interface IHwStepCounter extends Releasable {
    void flushCacheToDB(IFlushResult iFlushResult);

    void getDebugInfo(IExecuteResult iExecuteResult);

    int getDeviceOriginalClass();

    void getGoalNotifiState(IExecuteResult iExecuteResult);

    void getNotificationSupport(IExecuteResult iExecuteResult);

    void getSleepData(IExecuteResult iExecuteResult);

    void getStandSteps(IExecuteResult iExecuteResult);

    int getStepCounterClass();

    boolean getStepCounterSwitchStatus();

    void getStepsNotifiState(IExecuteResult iExecuteResult);

    void getTodaySportData(IExecuteResult iExecuteResult);

    void init(Context context);

    void isNeedPromptKeepAlive(IExecuteResult iExecuteResult);

    void makePromptNoSense();

    void notifyUserInfoChanged(IExecuteResult iExecuteResult);

    void registerRealTimeReport(RealStepCallback realStepCallback, int i);

    void registerStepReport(ICommonReport iCommonReport, IExecuteResult iExecuteResult);

    void setGoalNotifiEnable(boolean z, IExecuteResult iExecuteResult);

    void setStepCounterSwitchStatus(boolean z);

    void setStepsNotifiEnable(boolean z, IExecuteResult iExecuteResult);

    void setUserInfo(Bundle bundle, IExecuteResult iExecuteResult);

    void switchTrackMonitor(boolean z);

    void tickTrackDog();

    void unRegisterRealTimeReport();

    void unRegisterStepReport(ICommonReport iCommonReport);
}
