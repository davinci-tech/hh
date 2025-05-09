package com.huawei.hwcoresleepmgr;

import com.huawei.health.algorithm.callback.IBluetoothResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.Map;

/* loaded from: classes5.dex */
public interface SyncBaseFunction {
    void addOtaCoreSleepLog(int i, long j);

    void dicSyncCoreSleep(String str, IBaseResponseCallback iBaseResponseCallback);

    DeviceCapability getDeviceCapability(DeviceInfo deviceInfo);

    DeviceInfo getDeviceInfo();

    void getSleepDataFromDevice(boolean z, boolean z2, int i, int i2, IBluetoothResponseCallback iBluetoothResponseCallback);

    void registerHiHealthDataClient(DeviceInfo deviceInfo);

    void sendCommandToDevice(int i, Map<Integer, String> map, DeviceInfo deviceInfo);
}
