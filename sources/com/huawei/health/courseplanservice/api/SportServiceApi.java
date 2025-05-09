package com.huawei.health.courseplanservice.api;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.mof;

/* loaded from: classes3.dex */
public interface SportServiceApi {
    boolean addUpdateFeedback(String str, mof mofVar);

    void getFilterTab(String str, UiCallback<Boolean> uiCallback);

    FitWorkout getWorkout(String str);

    void setLastUseOrWatchCourse(String str, long j, FitWorkout fitWorkout);

    void startDeviceRecordSyncService(OnStateListener onStateListener);

    void syncData();

    void updateAll();
}
