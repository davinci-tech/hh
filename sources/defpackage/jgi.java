package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwdevicemodemgr.callback.WearPlaceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jgi implements DataReceiveCallback {
    private static jgi b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private WearPlaceCallback f13818a;

    private jgi() {
    }

    public static jgi e() {
        jgi jgiVar;
        synchronized (e) {
            if (b == null) {
                b = new jgi();
            }
            jgiVar = b;
        }
        return jgiVar;
    }

    public void d(String str) {
        cuk.b().registerDeviceSampleListener(str, this);
    }

    public void c(DeviceInfo deviceInfo, WearPlaceCallback wearPlaceCallback, String str) {
        this.f13818a = wearPlaceCallback;
        d("hw.unitedevice.querywearplace");
        if (deviceInfo == null || !cwi.c(deviceInfo, 78) || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwQueryWearPlaceMgr", "no send command");
            return;
        }
        if (cuk.b().sendSampleConfigCommand(deviceInfo, c(), "HwQueryWearPlaceMgr " + str)) {
            return;
        }
        LogUtil.h("HwQueryWearPlaceMgr", "sendQueryWearPlaceCommand fail");
    }

    public void b(WearPlaceCallback wearPlaceCallback, String str) {
        this.f13818a = wearPlaceCallback;
        d("hw.unitedevice.querywearplace");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwQueryWearPlaceMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null && cwi.c(deviceInfo, 78)) {
                cuk.b().sendSampleConfigCommand(deviceInfo, c(), "HwQueryWearPlaceMgr " + str);
            }
        }
    }

    private cvq c() {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.querywearplace");
        cvqVar.setWearPkgName("queryWearPlace");
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900100001L);
        cvnVar.d(2);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null || c(cvrVar)) {
            LogUtil.h("HwQueryWearPlaceMgr", "device == null or isCheckSampleError");
            return;
        }
        if (cvrVar instanceof cvq) {
            LogUtil.a("HwQueryWearPlaceMgr", "onDataReceived");
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("HwQueryWearPlaceMgr", "onDataReceived sampleConfigInfos empty");
                return;
            }
            cvn cvnVar = configInfoList.get(0);
            if (cvnVar.e() == 2) {
                LogUtil.a("HwQueryWearPlaceMgr", deviceInfo.getDeviceName(), " sampleConfigInfo.getConfigData() = ", Integer.valueOf(CommonUtil.w(cvx.d(cvnVar.b()))));
                int w = CommonUtil.w(cvx.d(cvnVar.b()));
                jgq.d().a(deviceInfo.getDeviceIdentify(), w);
                WearPlaceCallback wearPlaceCallback = this.f13818a;
                if (wearPlaceCallback != null) {
                    wearPlaceCallback.onResponse(deviceInfo, w);
                }
            }
        }
    }

    private boolean c(cvr cvrVar) {
        if (cvrVar == null) {
            LogUtil.a("HwQueryWearPlaceMgr", "sampleBase == null");
            return true;
        }
        if (cvrVar.getCommandId() == 1 && "queryWearPlace".equals(cvrVar.getSrcPkgName()) && "hw.unitedevice.querywearplace".equals(cvrVar.getWearPkgName())) {
            return false;
        }
        LogUtil.h("HwQueryWearPlaceMgr", "message: ", cvrVar.toString());
        return true;
    }
}
