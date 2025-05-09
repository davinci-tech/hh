package defpackage;

import android.os.Build;
import com.huawei.devicepair.api.UpdateDeviceApi;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

@ApiDefine(uri = UpdateDeviceApi.class)
@Singleton
/* loaded from: classes6.dex */
public class oaa implements UpdateDeviceApi {
    @Override // com.huawei.devicepair.api.UpdateDeviceApi
    public Map<String, Boolean> getUpdatePageContent(String str) {
        boolean isOtaUpdate;
        boolean isSupportAutoUpdate;
        String str2;
        DeviceCapability e = cvs.e(str);
        if (e == null) {
            LogUtil.h("UpdateDeviceApiImpl", "getUpdatePageContent, capability is null");
            str2 = str;
            isSupportAutoUpdate = false;
            isOtaUpdate = false;
        } else {
            isOtaUpdate = e.isOtaUpdate();
            isSupportAutoUpdate = e.getIsSupportAutoUpdate();
            str2 = str;
        }
        DeviceInfo e2 = jpt.e(str2, "UpdateDeviceApiImpl");
        boolean z = cwi.c(e2, 108) && !Utils.l();
        int productType = e2 == null ? -1 : e2.getProductType();
        boolean isSupportPairCheckedOtaAutoUpdateSwitch = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isSupportPairCheckedOtaAutoUpdateSwitch();
        LogUtil.a("UpdateDeviceApiImpl", "getUpdatePageContent, isOtaUpdate is ", Boolean.valueOf(isOtaUpdate), " isSupportAutoUpdate is ", Boolean.valueOf(isSupportAutoUpdate), " mDeviceProductType is ", Integer.valueOf(productType), ", isSupportOtaUpgrade:", Boolean.valueOf(z), " isOverseaNoCloudVersion ", Boolean.valueOf(Utils.l()), ", isCheckedSwitch:", Boolean.valueOf(isSupportPairCheckedOtaAutoUpdateSwitch));
        HashMap hashMap = new HashMap(16);
        if (cwc.e(productType, isOtaUpdate, false) || z) {
            ReleaseLogUtil.e("DEVMGR_UpdateDeviceApiImpl", "support wlan auto update, isCheckedSwitch: " + isSupportPairCheckedOtaAutoUpdateSwitch);
            hashMap.put("wlan_auto_update", Boolean.valueOf(isSupportPairCheckedOtaAutoUpdateSwitch));
        }
        if (isSupportAutoUpdate) {
            ReleaseLogUtil.e("DEVMGR_UpdateDeviceApiImpl", "support auto update status, isCheckedSwitch: " + isSupportPairCheckedOtaAutoUpdateSwitch);
            hashMap.put("auto_update_status", Boolean.valueOf(isSupportPairCheckedOtaAutoUpdateSwitch));
        }
        boolean z2 = cwi.c(e2, 35) || cwi.c(e2, 76);
        if (Build.VERSION.SDK_INT > 30 && CommonUtil.bh() && z2) {
            ReleaseLogUtil.e("DEVMGR_UpdateDeviceApiImpl", "support auto open wlan status, isCheckedSwitch: " + isSupportPairCheckedOtaAutoUpdateSwitch);
            hashMap.put("auto_open_wlan_status", Boolean.valueOf(isSupportPairCheckedOtaAutoUpdateSwitch));
        }
        return hashMap;
    }

    @Override // com.huawei.devicepair.api.UpdateDeviceApi
    public void setUpdateStatus(Map<String, Boolean> map, String str, IBaseResponseCallback iBaseResponseCallback) {
        char c;
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateDeviceApiImpl", "setUpdateStatus callback is null");
            return;
        }
        if (map == null || map.isEmpty()) {
            ReleaseLogUtil.d("DEVMGR_UpdateDeviceApiImpl", "setUpdateStatus param is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HashMap hashMap = new HashMap(map);
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            key.hashCode();
            int hashCode = key.hashCode();
            if (hashCode == 105734552) {
                if (key.equals("auto_update_status")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 312087338) {
                if (hashCode == 1760488028 && key.equals("wlan_auto_update")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (key.equals("auto_open_wlan_status")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c == 1) {
                    jqi.a().setSwitchSetting("auto_open_wlan_status", entry.getValue().booleanValue() ? "1" : "2", str, null);
                    hashMap.put("auto_open_wlan_status", true);
                    LogUtil.a("UpdateDeviceApiImpl", "setUpdateStatus AUTO_OPEN_WLAN_STATUS");
                } else if (c == 2) {
                    jqi.a().setSwitchSetting("wlan_auto_update", entry.getValue().booleanValue() ? "1" : "2", null);
                    hashMap.put("wlan_auto_update", true);
                    LogUtil.a("UpdateDeviceApiImpl", "setUpdateStatus WLAN_AUTO_UPDATE");
                }
            } else {
                DeviceInfo e = jpt.e(str, "UpdateDeviceApiImpl");
                if (e != null) {
                    jqi.a().setSwitchSetting("auto_update_status", entry.getValue().booleanValue() ? "true" : "false", e.getDeviceUdid(), null);
                    hashMap.put("auto_update_status", true);
                    LogUtil.a("UpdateDeviceApiImpl", "setUpdateStatus AUTO_UPDATE_STATUS");
                } else {
                    hashMap.put("auto_update_status", false);
                }
            }
        }
        iBaseResponseCallback.d(0, hashMap);
    }

    @Override // com.huawei.devicepair.api.UpdateDeviceApi
    public boolean isShowOtaPrivacy(String str) {
        boolean isOtaUpdate;
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        LogUtil.a("UpdateDeviceApiImpl", "initPairResultOtaPrivacyView siteId:", Integer.valueOf(m));
        if (m != 7) {
            LogUtil.h("UpdateDeviceApiImpl", "initPairResultOtaPrivacyView is not EUROPE_LOGIN_SITE_ID");
            return false;
        }
        DeviceCapability e = cvs.e(str);
        if (e == null) {
            LogUtil.h("UpdateDeviceApiImpl", "getUpdatePageContent, capability is null");
            isOtaUpdate = false;
        } else {
            isOtaUpdate = e.isOtaUpdate();
        }
        DeviceInfo e2 = jpt.e(str, "UpdateDeviceApiImpl");
        boolean c = cwi.c(e2, 58);
        boolean c2 = cwi.c(e2, 108);
        LogUtil.a("UpdateDeviceApiImpl", "isOtaUpdate is ", Boolean.valueOf(isOtaUpdate), "isSupportDetect is ", Boolean.valueOf(c), "isSupportOtaUpgrade is ", Boolean.valueOf(c2));
        return isOtaUpdate || c || c2;
    }
}
