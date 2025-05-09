package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class kia implements DataReceiveCallback {
    private static final Object b = new Object();
    private static kia d;

    private kia() {
    }

    public static kia c() {
        kia kiaVar;
        synchronized (b) {
            if (d == null) {
                d = new kia();
            }
            kiaVar = d;
        }
        return kiaVar;
    }

    private void c(String str) {
        cuk.b().registerDeviceSampleListener(str, this);
    }

    public void c(DeviceInfo deviceInfo, String str) {
        c("hw.otadevice.privacyswitch");
        if (deviceInfo == null || !cwi.c(deviceInfo, 97) || deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwPrivacySwitchMgr", "no send command");
        } else {
            if (cuk.b().sendSampleConfigCommand(deviceInfo, b(str), "HwPrivacySwitchMgr")) {
                return;
            }
            LogUtil.a("HwPrivacySwitchMgr", "sendQueryWearPlaceCommand fail");
        }
    }

    private cvq b(String str) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.otadevice.privacyswitch");
        cvqVar.setWearPkgName("privacySwitch");
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(a(str));
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    private cvn a(String str) {
        LogUtil.a("HwPrivacySwitchMgr", "constructSampleConfigInfo enter ");
        cvn cvnVar = new cvn();
        cvnVar.e(900100005L);
        cvnVar.d(1);
        StringBuilder sb = new StringBuilder(10);
        sb.append(cvx.e(jds.c(str, 10)));
        cvnVar.c(cvx.a(sb.toString()));
        return cvnVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("HwPrivacySwitchMgr", "onDataReceived errorCode ", Integer.valueOf(i));
    }
}
