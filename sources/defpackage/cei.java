package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.hihealth.HiUserInfo;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class cei extends AppBundlePluginProxy<DeviceManagerApi> implements DeviceManagerApi {
    private static volatile cei c;
    private DeviceManagerApi d;

    private cei() {
        super("DeviceManagerProxy", "PluginDevice", "com.huawei.health.impl.DeviceManagerImpl");
        this.d = createPluginApi();
    }

    public static cei b() {
        cei ceiVar;
        if (c == null) {
            synchronized (cei.class) {
                if (c == null) {
                    c = new cei();
                }
                ceiVar = c;
            }
            return ceiVar;
        }
        return c;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(DeviceManagerApi deviceManagerApi) {
        this.d = deviceManagerApi;
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void init() {
        if (isPluginAvaiable()) {
            this.d.init();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void startMeasureBackground(Context context, int i, HealthDevice.HealthDeviceKind healthDeviceKind, MeasureResult.MeasureResultListener measureResultListener) {
        if (isPluginAvaiable()) {
            this.d.startMeasureBackground(context, i, healthDeviceKind, measureResultListener);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void stopMeasureBackground(String str, String str2) {
        if (isPluginAvaiable()) {
            this.d.stopMeasureBackground(str, str2);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<String> getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (isPluginAvaiable()) {
            return this.d.getBondedProductsForDeviceOnly(healthDeviceKind);
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str, String str2) {
        return this.d.getBondedDeviceUniversal(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void syncDevice() {
        if (isPluginAvaiable()) {
            this.d.syncDevice();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isRopeDeviceBtConnected() {
        if (isPluginAvaiable()) {
            return this.d.isRopeDeviceBtConnected();
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setSkippingTargetMode(SkippingTargetMode skippingTargetMode) {
        if (isPluginAvaiable()) {
            this.d.setSkippingTargetMode(skippingTargetMode);
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int[] iArr) {
        if (isPluginAvaiable()) {
            this.d.setFitnessMachineControl(i, iArr);
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setFitnessMachineControl(int i, int i2, int[] iArr) {
        if (isPluginAvaiable()) {
            this.d.setFitnessMachineControl(i, i2, iArr);
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void setMessageOrStateCallback(String str, MessageOrStateCallback messageOrStateCallback, boolean z) {
        if (isPluginAvaiable()) {
            this.d.setMessageOrStateCallback(str, messageOrStateCallback, z);
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void removeMessageOrStateCallback(String str, boolean z) {
        if (isPluginAvaiable()) {
            this.d.removeMessageOrStateCallback(str, z);
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public void releaseResource() {
        if (isPluginAvaiable()) {
            this.d.releaseResource();
        }
    }

    @Override // com.huawei.health.device.base.BaseDeviceManagerApi
    public String getCurrentMacAddress() {
        return isPluginAvaiable() ? this.d.getCurrentMacAddress() : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void requestDeviceInfoAndState(String str) {
        if (isPluginAvaiable()) {
            this.d.requestDeviceInfoAndState(str);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getProductId() {
        return isPluginAvaiable() ? this.d.getProductId() : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserWeight(int i) {
        if (isPluginAvaiable()) {
            this.d.setUserWeight(i);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setHeightIsNotSet(boolean z) {
        if (isPluginAvaiable()) {
            this.d.setHeightIsNotSet(z);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isMiniScaleDevice(String str) {
        if (isPluginAvaiable()) {
            return this.d.isMiniScaleDevice(str);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean getResetWifi() {
        if (isPluginAvaiable()) {
            return this.d.getResetWifi();
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void deliveringUserInformation(String str, String str2, HiUserInfo hiUserInfo) {
        if (isPluginAvaiable()) {
            this.d.deliveringUserInformation(str, str2, hiUserInfo);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void uploadDeviceToCloud(String str, String str2) {
        if (isPluginAvaiable()) {
            this.d.uploadDeviceToCloud(str, str2);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserGender(int i, HiUserInfo hiUserInfo) {
        if (isPluginAvaiable()) {
            this.d.setUserGender(i, hiUserInfo);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<String> getBondedWiFiDevices() {
        if (isPluginAvaiable()) {
            return this.d.getBondedWiFiDevices();
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isBondHuaweiBleScaleDevice() {
        if (isPluginAvaiable()) {
            return this.d.isBondHuaweiBleScaleDevice();
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean showConfirmUserInfo(String str, boolean z) {
        if (isPluginAvaiable()) {
            return this.d.showConfirmUserInfo(str, z);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendUserInfo(String str) {
        if (isPluginAvaiable()) {
            this.d.sendUserInfo(str);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendUserInfo(Bundle bundle) {
        if (isPluginAvaiable()) {
            this.d.sendUserInfo(bundle);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHonourWeightDevice(String str) {
        if (isPluginAvaiable()) {
            return this.d.isHonourWeightDevice(str);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHagridWiFiDevice(String str, String str2) {
        if (isPluginAvaiable()) {
            return this.d.isHagridWiFiDevice(str, str2);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setBindStatus(int i) {
        if (isPluginAvaiable()) {
            this.d.setBindStatus(i);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isHonourScaleDevice(String str) {
        if (isPluginAvaiable()) {
            return this.d.isHonourScaleDevice(str);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getH5Path(String str) {
        return isPluginAvaiable() ? this.d.getH5Path(str) : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getSubProductId(String str) {
        return isPluginAvaiable() ? this.d.getSubProductId(str) : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void downloadBloodSugarDeviceResource(Activity activity) {
        if (isPluginAvaiable()) {
            this.d.downloadBloodSugarDeviceResource(activity);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void destroyResourceDownloadTool() {
        if (isPluginAvaiable()) {
            this.d.destroyResourceDownloadTool();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setBeginMeasureBiAnalytics(String str) {
        if (isPluginAvaiable()) {
            this.d.setBeginMeasureBiAnalytics(str);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setUserGoalWeight(float f) {
        if (isPluginAvaiable()) {
            this.d.setUserGoalWeight(f);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public float getLastestWeight() {
        if (isPluginAvaiable()) {
            return this.d.getLastestWeight();
        }
        return 0.0f;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean getHeightIsNotSet() {
        if (isPluginAvaiable()) {
            return this.d.getHeightIsNotSet();
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void scaleUpdateJump(Context context, Uri uri) {
        if (isPluginAvaiable()) {
            this.d.scaleUpdateJump(context, uri);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getUserName(String str) {
        return isPluginAvaiable() ? this.d.getUserName(str) : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public String getMainUserUuid() {
        return isPluginAvaiable() ? this.d.getMainUserUuid() : "";
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void initUserIfEmpty() {
        if (isPluginAvaiable()) {
            this.d.initUserIfEmpty();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void startSync() {
        if (isPluginAvaiable()) {
            this.d.startSync();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void sendEventToKaKa(int i) {
        if (isPluginAvaiable()) {
            this.d.sendEventToKaKa(i);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void confirmDevUserInfo() {
        if (isPluginAvaiable()) {
            this.d.confirmDevUserInfo();
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<Long> getDeviceCodeFromWiFiDevice() {
        if (isPluginAvaiable()) {
            return this.d.getDeviceCodeFromWiFiDevice();
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByUniqueId(String str) {
        if (isPluginAvaiable()) {
            return this.d.getBondedDeviceByUniqueId(str);
        }
        return null;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByUniqueId(String str, boolean z) {
        if (isPluginAvaiable()) {
            return this.d.getBondedDeviceByUniqueId(str, z);
        }
        return null;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public MeasurableDevice getBondedDeviceByBroadcastName(String str, String str2) {
        if (isPluginAvaiable()) {
            return this.d.getBondedDeviceByBroadcastName(str, str2);
        }
        return null;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ContentValues getDeviceInfoByUniqueId(String str) {
        if (isPluginAvaiable()) {
            return this.d.getDeviceInfoByUniqueId(str);
        }
        return null;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<ContentValues> getBondedDeviceByProductId(String str) {
        if (isPluginAvaiable()) {
            return this.d.getBondedDeviceByProductId(str);
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public ArrayList<MeasurableDevice> getBondedDevices(String str, boolean z) {
        if (isPluginAvaiable()) {
            return this.d.getBondedDevices(str, z);
        }
        return new ArrayList<>();
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public boolean isBondedDevice(String str) {
        if (isPluginAvaiable()) {
            return this.d.isBondedDevice(str);
        }
        return false;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public int updateBondedDevice(ContentValues contentValues, String str, String[] strArr) {
        if (isPluginAvaiable()) {
            return this.d.updateBondedDevice(contentValues, str, strArr);
        }
        return -1;
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void setFitnessRopeConfig(int i, int i2, int[] iArr) {
        if (isPluginAvaiable()) {
            this.d.setFitnessRopeConfig(i, i2, iArr);
        }
    }

    @Override // com.huawei.health.device.api.DeviceManagerApi
    public void registerDeviceInfo(String str, HealthDevice healthDevice, String str2) {
        if (isPluginAvaiable()) {
            this.d.registerDeviceInfo(str, healthDevice, str2);
        }
    }
}
