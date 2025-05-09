package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.devicemgr.api.DeviceMgrApi;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.api.constant.DeviceStateConstants;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.constant.HwGetSetingItemConstants;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class cun extends AppBundlePluginProxy<DeviceMgrApi> implements DeviceMgrApi {
    private static volatile cun d;
    private DeviceMgrApi c;

    private cun() {
        super("DeviceMgrProxy", "DeviceMgrProxy", "com.huawei.hwdevice.outofprocess.impl.DeviceMgrImpl");
        this.c = createPluginApi();
    }

    public static cun c() {
        cun cunVar;
        if (d == null) {
            synchronized (cun.class) {
                if (d == null) {
                    d = new cun();
                }
                cunVar = d;
            }
            return cunVar;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.c != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(DeviceMgrApi deviceMgrApi) {
        this.c = deviceMgrApi;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void registerDataCallback(IBaseResponseCallback iBaseResponseCallback, DataCallbackType dataCallbackType, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.registerDataCallback(iBaseResponseCallback, dataCallbackType, str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void unregisterDataCallback(int i) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.unregisterDataCallback(i);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public DeviceInfo getDeviceInfo(GetDeviceInfoMode getDeviceInfoMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getDeviceInfo(getDeviceInfoMode, hwGetDevicesParameter, str);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    @Deprecated
    public void pushFitRunCourseData(FitWorkout fitWorkout) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.pushFitRunCourseData(fitWorkout);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getDeviceList(hwGetDevicesMode, hwGetDevicesParameter, str);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    @Deprecated
    public Map<String, DeviceCapability> queryDeviceCapability(int i, String str, String str2) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.queryDeviceCapability(i, str, str2);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getDevicesFromCloud(String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getDevicesFromCloud(str, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void sendDeviceData(int i, int i2, Map<Integer, String> map, DeviceInfo deviceInfo, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.sendDeviceData(i, i2, map, deviceInfo, str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public int getDeviceStateById(DeviceStateConstants deviceStateConstants, String str, String str2, String str3) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getDeviceStateById(deviceStateConstants, str, str2, str3);
        }
        return -1;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public cuw getDeviceInfoUx(int i, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getDeviceInfoUx(i, str);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public String getDeviceSettingItemById(HwGetSetingItemConstants hwGetSetingItemConstants, String str, String str2) {
        DeviceMgrApi deviceMgrApi = this.c;
        return deviceMgrApi != null ? deviceMgrApi.getDeviceSettingItemById(hwGetSetingItemConstants, str, str2) : "";
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void downloadDeviceResources(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.downloadDeviceResources(list, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void cancelDownloadDeviceResources() {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.cancelDownloadDeviceResources();
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void executeDeviceRelatedLogic(ExecuteMode executeMode, cul culVar, String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.executeDeviceRelatedLogic(executeMode, culVar, str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void startSleepBreatheDetectionPage() {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.startSleepBreatheDetectionPage();
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setSleepBreatheDetectionState(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.setSleepBreatheDetectionState(z, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getSleepBreatheDetectionState(IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getSleepBreatheDetectionState(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public String getSwitchSetting(String str, String str2) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getSwitchSetting(str, str2);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void unPair(List<String> list, boolean z) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.unPair(list, z);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public int getDeviceTypeId(DeviceInfo deviceInfo) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.getDeviceTypeId(deviceInfo);
        }
        return 1;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public boolean isBindPhoneService() {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            return deviceMgrApi.isBindPhoneService();
        }
        return true;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getHealthSwitch(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getHealthSwitch(list, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setHealthSwitch(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.setHealthSwitch(jSONObject, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getEmotionAutoMonitorSwitch(IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getEmotionAutoMonitorSwitch(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getEmotionRiskWarningSwitch(IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getEmotionRiskWarningSwitch(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getStressAutoMonitorSwitch(IBaseResponseCallback iBaseResponseCallback) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.getStressAutoMonitorSwitch(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setEmotionRiskWarningSwitch(String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.setEmotionRiskWarningSwitch(str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setEmotionAutoMonitorSwitch(String str) {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.setEmotionAutoMonitorSwitch(str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void checkBindPhoneService() {
        DeviceMgrApi deviceMgrApi = this.c;
        if (deviceMgrApi != null) {
            deviceMgrApi.checkBindPhoneService();
        }
    }
}
