package com.huawei.health.ui.notification;

import health.compact.a.StepsRecord;

/* loaded from: classes.dex */
public interface IUiManager {
    void changeGoalNotificationStateAsUser(boolean z);

    void changeStepsNotificationStateAsUser(boolean z);

    boolean isGetGoalNotificationState();

    boolean isGetNotificationSupport();

    boolean isGetStepsNotificationState();

    void localChanged();

    void release();

    void updateStepRecord(StepsRecord stepsRecord);
}
