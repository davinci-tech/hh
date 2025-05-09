package com.huawei.health.device.api;

import android.content.ContentValues;
import android.os.Bundle;
import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.open.MeasureKit;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface HealthDeviceEntryApi {
    boolean bindDevice(String str, HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler);

    boolean bindDevice(String str, String str2, HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler);

    void cleanupMeasureUniversal(String str, String str2);

    HealthDevice getBondedDevice(String str);

    ArrayList<ContentValues> getBondedDeviceByDeviceKind(HealthDevice.HealthDeviceKind healthDeviceKind);

    HealthDevice getBondedDeviceByUniqueId(String str);

    com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str);

    com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str, String str2);

    ArrayList<String> getBondedWiFiDevices();

    MeasureKit getMeasureKit(String str);

    boolean isDeviceKitUniversal(String str);

    void sendLocalBroadcast(String str, String str2, String str3);

    void sendLocalBroadcastUniversal(String str, String str2, String str3);

    void startConnectLatestScale();

    boolean startMeasure(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle);

    String startMeasureHeartRate(HealthDevice.HealthDeviceKind healthDeviceKind, IHealthDeviceCallback iHealthDeviceCallback, HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback2, MeasureResult.MeasureResultListener measureResultListener);

    boolean startMeasureReconnect(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, boolean z);

    boolean startMeasureUniversal(String str, String str2, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle);

    void stopMeasure(String str, String str2);

    void stopMeasureBleScale(String str, String str2, int i);

    void stopMeasureUniversal(String str, String str2);

    boolean unbindDevice(String str);

    boolean unbindDeviceByUniqueId(String str);

    boolean unbindDeviceUniversalByUniqueId(String str, String str2);
}
