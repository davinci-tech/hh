package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class kfc extends AbstractSyncTask {
    private ThreadPoolManager e;

    public kfc(String str) {
        super("ECG_ANALY_SYNC_TASK" + str);
        this.e = ThreadPoolManager.a(1, 1);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.synctask.AbstractSyncTask
    public void startTask(final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("DEVMGR_EcgAnalySyncTask", "ecg analysis sync task enter.");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "EcgAnalySyncTask");
        boolean c = cwi.c(deviceList.size() == 1 ? deviceList.get(0) : null, 106);
        LogUtil.a("EcgAnalySyncTask", "device isSupportEcgAnalysis: ", Boolean.valueOf(c));
        if (!c) {
            ReleaseLogUtil.d("DEVMGR_EcgAnalySyncTask", "device not support ecg analysis.");
            iBaseResponseCallback.d(-3, "");
        } else {
            this.e.d("EcgAnalySyncTask", new Runnable() { // from class: kfc.2
                @Override // java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("DEVMGR_EcgAnalySyncTask", "start ecgAnalysis file.");
                    kcy.e(BaseApplication.getContext()).f(iBaseResponseCallback);
                }
            });
        }
    }
}
