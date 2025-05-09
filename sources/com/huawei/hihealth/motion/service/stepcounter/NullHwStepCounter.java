package com.huawei.hihealth.motion.service.stepcounter;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.motion.RealStepCallback;

/* loaded from: classes.dex */
public class NullHwStepCounter implements IHwStepCounter {
    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void flushCacheToDB(IFlushResult iFlushResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getDebugInfo(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public int getDeviceOriginalClass() {
        return 3;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getGoalNotifiState(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getNotificationSupport(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getSleepData(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getStandSteps(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public int getStepCounterClass() {
        return 3;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public boolean getStepCounterSwitchStatus() {
        return false;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getStepsNotifiState(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getTodaySportData(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void init(Context context) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void isNeedPromptKeepAlive(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void makePromptNoSense() {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void notifyUserInfoChanged(IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void registerRealTimeReport(RealStepCallback realStepCallback, int i) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void registerStepReport(ICommonReport iCommonReport, IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.Releasable
    public void release() {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setGoalNotifiEnable(boolean z, IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setStepCounterSwitchStatus(boolean z) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setStepsNotifiEnable(boolean z, IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setUserInfo(Bundle bundle, IExecuteResult iExecuteResult) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void switchTrackMonitor(boolean z) {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void tickTrackDog() {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void unRegisterRealTimeReport() {
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void unRegisterStepReport(ICommonReport iCommonReport) {
    }
}
