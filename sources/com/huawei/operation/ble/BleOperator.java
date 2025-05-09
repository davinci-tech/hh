package com.huawei.operation.ble;

import android.content.Intent;
import java.util.UUID;

/* loaded from: classes5.dex */
public interface BleOperator {
    void bindDevice(String str);

    String closeBleConnection(String str);

    String closeBluetoothAdapter();

    void closeWeb();

    String createBleConnection(String str);

    void deleteMultipleHealthData(String str, String str2);

    void execQuery(String str, String str2);

    void getAppVersion();

    void getBluetoothAdapterState();

    String getDeviceId();

    void getDeviceInfo(String str);

    String getLocale();

    void getSn(String str);

    void getUserInfo(String str);

    void hasHealthDataQuery(String str, String str2);

    boolean indicationBleCharacteristicValueChange(String str, String str2, String str3, boolean z);

    boolean isDarkMode();

    boolean notifyBleCharacteristicValueChange(String str, String str2, String str3, boolean z);

    void onActivityResult(int i, int i2, Intent intent);

    void onBluetoothAdapterStateChange();

    String openBluetoothAdapter();

    void openBluetoothAdapterWithCallBack();

    void readBleCharacteristicValue(String str, String str2);

    String requestConnectionPriority(int i);

    String requestMtu(int i);

    void save(String str);

    void saveMultipleData(String str, String str2);

    void setCallBackName(String str, String str2, String str3);

    void setTitle(String str);

    String startBluetoothDevicesDiscovery(UUID[] uuidArr, boolean z, int i);

    void startScanCode(String str);

    String stopBluetoothDevicesDiscovery();

    void syncCloud(String str, String str2);

    void toast(String str);

    String writeBleCharacteristicValue(String str, String str2, String str3, String str4);
}
