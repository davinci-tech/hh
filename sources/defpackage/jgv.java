package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jgv implements DataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13832a = new Object();
    private static jgv e;
    private IBaseResponseCallback c;
    private IBaseResponseCallback d;

    public static jgv c() {
        jgv jgvVar;
        synchronized (f13832a) {
            if (e == null) {
                LogUtil.a("HwFindDeviceMgr", "getInstance");
                jgv jgvVar2 = new jgv();
                e = jgvVar2;
                jgvVar2.e();
            }
            jgvVar = e;
        }
        return jgvVar;
    }

    private jgv() {
    }

    public void e() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.finddevice", this);
    }

    public boolean b(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        this.d = iBaseResponseCallback;
        return cuk.b().sendSampleConfigCommand(deviceInfo, a(2), "HwFindDeviceMgr");
    }

    public boolean e(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        this.c = iBaseResponseCallback;
        return cuk.b().sendSampleConfigCommand(deviceInfo, a(1), "HwFindDeviceMgr");
    }

    private cvq a(int i) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.finddevice");
        cvqVar.setWearPkgName("findDevice");
        cvqVar.setCommandId(1);
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900100002L);
        cvnVar.d(i);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (cvrVar instanceof cvq) {
            cvq cvqVar = (cvq) cvrVar;
            if (cvrVar.getCommandId() != 1) {
                LogUtil.h("HwFindDeviceMgr", "onDataReceived, message commandId", Integer.valueOf(cvrVar.getCommandId()));
                return;
            }
            List<cvn> configInfoList = cvqVar.getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("HwFindDeviceMgr", "onDataReceived sampleConfigInfos empty");
                return;
            }
            ReleaseLogUtil.e("DEVMGR_HwFindDeviceMgr", "onDataReceived configAction", Integer.valueOf(configInfoList.get(0).e()));
            if (configInfoList.get(0).e() == 1) {
                this.c.d(i, configInfoList.get(0));
            }
            if (configInfoList.get(0).e() == 2) {
                this.d.d(i, configInfoList.get(0));
            }
            if (configInfoList.get(0).e() == 4) {
                this.d.d(i, configInfoList.get(0));
            }
        }
    }
}
