package com.huawei.health.device.api;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface HonourDeviceConstantsApi {
    Bundle getAm16Bundle();

    boolean getAm16PermissionTip();

    String getDeviceHelpH5Url(String str);

    String getDeviceIdentify(String str);

    String getDeviceModel(String str);

    String getDeviceNameByProductId(String str);

    String getDeviceSerialNumberByUniqueId(String str);

    boolean getUserSureDownload();

    boolean isCorrectProductId(String str);

    boolean isDualModeProduct(String str);

    boolean isHonourDevice(String str);

    boolean isHonourNewDevice(String str);

    boolean isHonourWeightDevice(String str);

    boolean isHuaweiOrHonourDevice(String str);

    boolean isHuaweiOrHonourScaleProdId(String str);

    boolean isHuaweiWspDataType(int i);

    boolean isHuaweiWspScaleProduct(String str);

    boolean isNoneHonourAm16BondedDevice();

    boolean isNoneHuaWeiWeightBondedDevice(String str);

    boolean isShowWiFiDevice(String str);

    boolean isSupportWifiDevice();

    boolean isTlvScaleProduct(String str, String str2);

    boolean isWiFiDevice(String str);

    String judgeProductIdByDeviceName(String str);

    void setAm16PermissionTip();

    void setDevicesUseTime(String str);

    void setUserSureDownload();
}
