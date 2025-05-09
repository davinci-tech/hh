package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class kfy implements DataReceiveCallback {
    int c = 0;
    private LinkageDeviceCommandListener b = null;
    private IBaseCallback e = null;

    public void c(LinkageDeviceCommandListener linkageDeviceCommandListener) {
        ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "registerDeviceCommandListener :", linkageDeviceCommandListener);
        this.b = linkageDeviceCommandListener;
    }

    public void c(IBaseCallback iBaseCallback) {
        ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "registerLinkageDeviceDataListener :", iBaseCallback);
        this.e = iBaseCallback;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (i != 100000) {
            ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "onDataReceived errCode is error");
            return;
        }
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "onDataReceived deviceInfo or sampleBase is null.");
            return;
        }
        String srcPkgName = cvrVar.getSrcPkgName();
        String wearPkgName = cvrVar.getWearPkgName();
        LogUtil.a("LINKAGE_LinkageDeviceChannel", "onDataReceived srcPkgNam: ", srcPkgName, ", wearPkgName:", wearPkgName);
        if (!a(srcPkgName, wearPkgName)) {
            ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "onDataReceived pkg error srcPkgNam: ", srcPkgName, ", wearPkgName:", wearPkgName);
        } else {
            kgi.b(cvrVar).handle(cvrVar, this.b, this.e);
        }
    }

    public void b(DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a("LINKAGE_LinkageDeviceChannel", "sendSampleEventToDevice operationType=", Integer.valueOf(i));
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "sendSampleEventToDevice deviceInfo null");
            return;
        }
        cuk.b().sendSampleEventCommand(deviceInfo, kgw.b(i).createSampleEvent(i2, null), "LINKAGE_LinkageDeviceChannel");
        if (i == 102 || i == 204) {
            kkl.a(false);
        }
    }

    public void e(DeviceInfo deviceInfo, int i, String str, int i2) {
        LogUtil.a("LINKAGE_LinkageDeviceChannel", "replyDevice operationType=", Integer.valueOf(i), "replyCode: ", str);
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_LINKAGE_LinkageDeviceChannel", "sendSampleEventToDevice deviceInfo null");
            return;
        }
        cvp createSampleEvent = kgw.a(i, str).createSampleEvent(i2, str);
        if (createSampleEvent.e() == LocationRequestCompat.PASSIVE_INTERVAL) {
            ReleaseLogUtil.e("R_LINKAGE_LinkageDeviceChannel", "sendSampleEventToDevice sampleEvent null");
        } else {
            cuk.b().sendSampleEventCommand(deviceInfo, createSampleEvent, "LINKAGE_LinkageDeviceChannel");
        }
    }

    public void b(DeviceInfo deviceInfo, int i, long j) {
        byte[] d = new bms().d(1, j).a(2, i).d();
        LogUtil.a("LINKAGE_LinkageDeviceChannel", "sendConfig, configData = ", cvx.d(d));
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.sport.linkage.app");
        cvqVar.setWearPkgName("hw.sport.linkage.device");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900200033L);
        cvnVar.d(1);
        cvnVar.c(d);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "LINKAGE_LinkageDeviceChannel");
    }

    private boolean a(String str, String str2) {
        return "hw.sport.linkage.device".equals(str) && "hw.sport.linkage.app".equals(str2);
    }
}
