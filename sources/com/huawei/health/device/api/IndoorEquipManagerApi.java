package com.huawei.health.device.api;

import android.os.Bundle;
import com.huawei.health.device.base.BaseDeviceManagerApi;
import com.huawei.health.device.model.DeviceInformation;
import java.util.Map;

/* loaded from: classes3.dex */
public interface IndoorEquipManagerApi extends BaseDeviceManagerApi {
    public static final String KEY_DYNAMIC_ENERGY = "dynamicEnergy";
    public static final String KEY_HEART_RATE = "heartRate";
    public static final String KEY_STEP_COUNT = "stepCount";
    public static final String KEY_SUPPRESS_AUTO_PAUSE = "suppressAutoPause";
    public static final String KEY_TARGETED_EXPENDED_ENERGY = "targetedExpendedEnergy";
    public static final String KEY_TOTAL_ENERGY = "totalEnergy";
    public static final String KEY_UNLOCK_CODE = "unlockCode";

    void cancelPairing();

    void connectByMac(boolean z, String str);

    void connectByName(boolean z, String str);

    void disconnect(boolean z);

    DeviceInformation getDeviceInformation();

    boolean isDeviceBtConnected();

    boolean isFitnessMachineProfile();

    boolean isStartByDevice(Bundle bundle);

    boolean isStopByDevice(Bundle bundle);

    void onlyConnectByMacOrName(String str, String str2);

    boolean reConnect();

    void sendByteToEquip(byte[] bArr);

    void sendExtensionCollectionControl(Map<String, String> map);

    void setDeviceType(String str);

    void setHasExperiencedStateOfStartForFtmp(boolean z);

    void setHeartRateFromWearable(int i);

    void setRealStartWorkout();

    void setSendCalorieToDevice(boolean z);

    void setSuppressPause(boolean z);

    void setUnlockCode(int[] iArr);

    void startDiscoverServices();

    void stopSport();

    void stopThreadsInCsafeController();

    void unPair(String str);
}
