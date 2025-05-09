package com.huawei.health.device.api;

import android.view.View;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.cpm;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public interface DeviceInfoUtilsApi {
    View checkProductType(int i);

    void clearUdsClassMap();

    String getBondedDeviceAddress(String str, String str2);

    int getBtType(int i);

    DeviceInfo getConnectionDevice();

    DeviceInfo getCurrentDevice();

    List<DeviceInfo> getCurrentDeviceList();

    String getUUIDForPlugin(int i);

    List<DeviceInfo> getWearDeviceList();

    ArrayList<cpm> getWearInfo();

    ArrayList<cpm> getWearInfoHeartRate(List<String> list);

    boolean idCurrentDeviceActive(String str);

    boolean isBindingHeartRateDeviceWear(List<String> list);

    boolean isDeviceBand(int i);

    boolean isPluginDownloadByType(int i);

    boolean isSupportUdsByProductId();

    boolean isWearInfoListSize();

    boolean isWiFiDevice(String str);

    <T> void putUdsClassMap(String str, T t);

    void resetUpdate();

    void sendDeraultSwitch();

    void setBattery(int i);

    void setCharacteristicNotifyByUds(String str, String str2, String str3, boolean z);
}
