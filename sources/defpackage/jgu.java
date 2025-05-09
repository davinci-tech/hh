package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class jgu implements DataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static jgu f13831a;
    private static final Object d = new Object();
    private final int b = 2;
    private IBaseResponseCallback c;

    private jgu() {
    }

    public static jgu a() {
        jgu jguVar;
        synchronized (d) {
            if (f13831a == null) {
                LogUtil.a("DynamicBpManager", "getInstance");
                jgu jguVar2 = new jgu();
                f13831a = jguVar2;
                jguVar2.c();
            }
            jguVar = f13831a;
        }
        return jguVar;
    }

    private void c() {
        cuk.b().registerDeviceSampleListener("hw.health.dynamicBp", this);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("R_DynamicBpManager", "onDataReceived");
        if (this.c == null) {
            LogUtil.h("DynamicBpManager", "onDataReceived mDynamicResponseCallback is null");
            return;
        }
        if (!(cvrVar instanceof cvq)) {
            LogUtil.h("DynamicBpManager", "message not instanceof SampleConfig");
            this.c.d(-1, null);
            return;
        }
        List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
        if (configInfoList == null || configInfoList.size() == 0) {
            LogUtil.h("DynamicBpManager", "onDataReceived sampleConfigInfoList empty");
            this.c.d(-1, null);
            return;
        }
        cvn cvnVar = configInfoList.get(0);
        if (cvnVar == null) {
            LogUtil.h("DynamicBpManager", "onDataReceived configInfo is null");
            this.c.d(-1, null);
            return;
        }
        String d2 = HEXUtils.d(HEXUtils.a(cvnVar.b()));
        LogUtil.a("DynamicBpManager", "onDataReceived configData: ", d2);
        sqp.d("900300039", d2, new HiDataOperateListener() { // from class: jgu.4
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                ReleaseLogUtil.e("R_DynamicBpManager", "setConfig errorCode: ", Integer.valueOf(i2), " object: ", obj);
            }
        });
        this.c.d(i, (cbh) HiJsonUtil.e(d2, cbh.class));
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DynamicBpManager", "sendDynamicBpCommand enter");
        this.c = iBaseResponseCallback;
        DeviceInfo d2 = jpt.d("DynamicBpManager");
        if (d2 != null) {
            ReleaseLogUtil.e("R_DynamicBpManager", "sendDynamicBpCommand isSuccess: ", Boolean.valueOf(cuk.b().sendSampleConfigCommand(d2, e(), "DynamicBpManager")));
        } else {
            iBaseResponseCallback.d(-1, null);
        }
    }

    private cvq e() {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.health.dynamicBp");
        cvqVar.setWearPkgName("hw.watch.health.dynamicbp");
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900300039L);
        cvnVar.d(2);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    public static void d() {
        ReleaseLogUtil.e("R_DynamicBpManager", "releaseMgr");
        synchronized (d) {
            if (f13831a == null) {
                ReleaseLogUtil.e("R_DynamicBpManager", "sInstance is null");
            } else {
                cuk.b().unRegisterDeviceSampleListener("hw.health.dynamicBp");
                f13831a = null;
            }
        }
    }
}
