package com.huawei.health.device.api;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.health.device.base.BaseDeviceManagerApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.device.open.HealthDevice;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface DeviceManagerApi extends BaseDeviceManagerApi {
    void confirmDevUserInfo();

    void deliveringUserInformation(String str, String str2, HiUserInfo hiUserInfo);

    void destroyResourceDownloadTool();

    void downloadBloodSugarDeviceResource(Activity activity);

    MeasurableDevice getBondedDeviceByBroadcastName(String str, String str2);

    ArrayList<ContentValues> getBondedDeviceByProductId(String str);

    MeasurableDevice getBondedDeviceByUniqueId(String str);

    MeasurableDevice getBondedDeviceByUniqueId(String str, boolean z);

    HealthDevice getBondedDeviceUniversal(String str, String str2);

    ArrayList<MeasurableDevice> getBondedDevices(String str, boolean z);

    ArrayList<String> getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind healthDeviceKind);

    ArrayList<String> getBondedWiFiDevices();

    ArrayList<Long> getDeviceCodeFromWiFiDevice();

    ContentValues getDeviceInfoByUniqueId(String str);

    String getH5Path(String str);

    boolean getHeightIsNotSet();

    float getLastestWeight();

    String getMainUserUuid();

    String getProductId();

    boolean getResetWifi();

    String getSubProductId(String str);

    String getUserName(String str);

    void initUserIfEmpty();

    boolean isBondHuaweiBleScaleDevice();

    boolean isBondedDevice(String str);

    boolean isHagridWiFiDevice(String str, String str2);

    boolean isHonourScaleDevice(String str);

    boolean isHonourWeightDevice(String str);

    boolean isMiniScaleDevice(String str);

    boolean isRopeDeviceBtConnected();

    void registerDeviceInfo(String str, com.huawei.health.device.model.HealthDevice healthDevice, String str2);

    void requestDeviceInfoAndState(String str);

    void scaleUpdateJump(Context context, Uri uri);

    void sendEventToKaKa(int i);

    void sendUserInfo(Bundle bundle);

    void sendUserInfo(String str);

    void setBeginMeasureBiAnalytics(String str);

    void setBindStatus(int i);

    void setFitnessRopeConfig(int i, int i2, int[] iArr);

    void setHeightIsNotSet(boolean z);

    void setSkippingTargetMode(SkippingTargetMode skippingTargetMode);

    void setUserGender(int i, HiUserInfo hiUserInfo);

    void setUserGoalWeight(float f);

    void setUserWeight(int i);

    boolean showConfirmUserInfo(String str, boolean z);

    void startMeasureBackground(Context context, int i, HealthDevice.HealthDeviceKind healthDeviceKind, MeasureResult.MeasureResultListener measureResultListener);

    void startSync();

    void stopMeasureBackground(String str, String str2);

    void syncDevice();

    int updateBondedDevice(ContentValues contentValues, String str, String[] strArr);

    void uploadDeviceToCloud(String str, String str2);
}
