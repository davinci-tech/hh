package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class bdl extends AbstractSyncTask {
    public bdl() {
        super("RQ_DATA_SYNC_TASK");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("Track_RqDataSyncTask", "RqDataSyncTask start");
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_RqDataSyncTask", "callback is null");
            return;
        }
        if (!a()) {
            ReleaseLogUtil.e("Track_RqDataSyncTask", "device not support");
            iBaseResponseCallback.d(0, "");
        } else {
            eme.b().pushRqData(ggl.a(new Date(System.currentTimeMillis())));
            iBaseResponseCallback.d(0, "");
            LogUtil.a("Track_RqDataSyncTask", "RqDataSyncTask End");
        }
    }

    private boolean a() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "Track_RqDataSyncTask");
        if (deviceList != null && deviceList.size() > 0) {
            deviceInfo = deviceList.get(0);
        }
        return cwi.c(deviceInfo, 53);
    }
}
