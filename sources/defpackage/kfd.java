package defpackage;

import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kfd extends AbstractSyncTask {
    public kfd() {
        super("UPDATE_DEVICE_TASK");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        boolean a2;
        if (iBaseResponseCallback == null) {
            LogUtil.h("UpdateDeviceTask", "callback == null");
            return;
        }
        boolean z = false;
        iBaseResponseCallback.d(0, "");
        boolean d = jrd.d(jrd.b("update_device_task_check_time"));
        boolean d2 = jrd.d();
        LogUtil.a("UpdateDeviceTask", "startTask isCheckInterval ", Boolean.valueOf(d), " isProcessStarted ", Boolean.valueOf(d2));
        if (d || !d2) {
            LogUtil.a("UpdateDeviceTask", "task enter.");
            jrd.d(true);
            DeviceInfo deviceInfo = null;
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "UpdateDeviceTask");
            if (deviceList.size() > 0) {
                for (DeviceInfo deviceInfo2 : deviceList) {
                    if (cwi.c(deviceInfo2, 108)) {
                        a2 = jrd.e(deviceInfo2.getDeviceUdid());
                    } else {
                        a2 = jrd.a(deviceInfo2.getDeviceIdentify());
                    }
                    boolean e = jrd.e(deviceInfo2.getProductType(), deviceInfo2.getDeviceIdentify());
                    LogUtil.a("UpdateDeviceTask", "task enter. isHasNewVersion ", Boolean.valueOf(a2), " isAlreadyCheck ", Boolean.valueOf(e));
                    if (!e || a2) {
                        z = true;
                        deviceInfo = deviceInfo2;
                        break;
                    }
                }
            }
            if (deviceInfo == null) {
                LogUtil.h("UpdateDeviceTask", "task deviceInfo == null");
            } else {
                jrd.c("update_device_task_check_time");
                e(deviceInfo, z);
            }
        }
    }

    private void e(DeviceInfo deviceInfo, boolean z) {
        boolean z2 = true;
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "UpdateDeviceTask");
        DeviceCapability deviceCapability = new DeviceCapability();
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        }
        if (!cwi.c(deviceInfo, 58) && !cwi.c(deviceInfo, 108) && (deviceCapability == null || !deviceCapability.isOtaUpdate())) {
            z2 = false;
        }
        if (z && z2) {
            LogUtil.a("UpdateDeviceTask", "enter autoCheckBandNewVersionService");
            Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
            intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
            intent.setAction("action_band_auto_check_new_version");
            try {
                BaseApplication.getContext().startService(intent);
            } catch (IllegalStateException | SecurityException e) {
                LogUtil.b("autoCheckBandNewVersionService IllegalStateException ", ExceptionUtils.d(e));
            }
        }
    }
}
