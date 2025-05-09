package com.huawei.ui.device.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cun;
import defpackage.gnm;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HeartRatePretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        DeviceInfo a2 = a("DEVMGR_HeartRatePretreatmentService");
        if (a2 == null) {
            ReleaseLogUtil.d("DEVMGR_HeartRatePretreatmentService", "onPretreatment currentDeviceInfo is null");
            return false;
        }
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.e()).e(a2.getDeviceIdentify());
        if (e == null) {
            ReleaseLogUtil.d("DEVMGR_HeartRatePretreatmentService", "onPretreatment deviceCapability is null");
            return false;
        }
        if (e.isSupportContinueHeartRate()) {
            LogUtil.a("HeartRatePretreatmentService", "start continue heart rate item");
            return true;
        }
        if (e.isSupportHeartRateEnable()) {
            LogUtil.a("HeartRatePretreatmentService", "start cycle heart rate item");
            gnm.aPB_(context, new Intent(context, (Class<?>) HeartRateSettingsActivity.class));
            return false;
        }
        LogUtil.a("HeartRatePretreatmentService", "start continue heart rate return false");
        return false;
    }

    private static DeviceInfo a(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            if (it.hasNext()) {
                deviceInfo = it.next();
            }
        }
        LogUtil.a("HeartRatePretreatmentService", "getConnectMainDevice() deviceInfo ", deviceInfo, ", tag:", str);
        return deviceInfo;
    }
}
