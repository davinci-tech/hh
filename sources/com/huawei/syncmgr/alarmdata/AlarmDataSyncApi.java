package com.huawei.syncmgr.alarmdata;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes6.dex */
public interface AlarmDataSyncApi {
    void startSyncHeartRateAlarm(DeviceInfo deviceInfo);

    void startSyncLowSpo2Alarm(DeviceInfo deviceInfo);
}
