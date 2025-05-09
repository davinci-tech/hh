package com.huawei.ui.homewear21.home;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cun;
import defpackage.jad;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HealthMonitorPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        String str = "";
        String queryParameter = zN_ != null ? zN_.getQueryParameter("device") : "";
        LogUtil.a("HealthMonitorPretreatmentService", "startHealthMonitoring deviceParam:", queryParameter);
        if (TextUtils.isEmpty(queryParameter) || "main".equals(queryParameter)) {
            DeviceInfo a2 = a("DEVMGR_HealthMonitorPretreatmentService");
            if (a2 == null) {
                ReleaseLogUtil.d("DEVMGR_HealthMonitorPretreatmentService", "onPretreatment currentDeviceInfo is null");
                return false;
            }
            str = a2.getDeviceIdentify();
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorPretreatmentService", "onPretreatment deviceMac is empty");
            return false;
        }
        if (!d(str)) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorPretreatmentService", "onPretreatment isSupportHealthMonitoring is false");
            return false;
        }
        guidepost.e("device_id", str);
        LogUtil.a("HealthMonitorPretreatmentService", "startHealthMonitoring start");
        return true;
    }

    private boolean d(String str) {
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.e()).e(str);
        if (e == null) {
            ReleaseLogUtil.d("DEVMGR_HealthMonitorPretreatmentService", "isSupportHealthMonitoring deviceCapability is null");
            return false;
        }
        if ((e.isSupportCoreSleep() && jad.d(58)) || e.isActivityReminder() || e.isSupportContinueHeartRate()) {
            return true;
        }
        if ((e.isSupportHeartRateEnable() && !e.isSupportContinueHeartRate()) || e.isSupportPressAutoMonitor()) {
            return true;
        }
        ReleaseLogUtil.d("DEVMGR_HealthMonitorPretreatmentService", "isSupportHealthMonitoring is false");
        return false;
    }

    public static DeviceInfo a(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            if (it.hasNext()) {
                deviceInfo = it.next();
            }
        }
        LogUtil.a("HealthMonitorPretreatmentService", "getConnectMainDevice() deviceInfo ", deviceInfo, ", tag:", str);
        return deviceInfo;
    }
}
