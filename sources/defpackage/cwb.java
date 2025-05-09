package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.StringUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class cwb implements DataReceiveCallback {
    private static cwb c;
    private static final Object e = new Object();
    private IBaseResponseCallback b;

    private cwb() {
    }

    public static cwb d() {
        cwb cwbVar;
        synchronized (e) {
            if (c == null) {
                c = new cwb();
            }
            cwbVar = c;
        }
        return cwbVar;
    }

    public void c() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.certificateChain", this);
    }

    public void b(DeviceInfo deviceInfo, int[] iArr, String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSecurityCertUtils", "sendGetDeviceCertCommand() in");
        if (iBaseResponseCallback == null) {
            LogUtil.h("DeviceSecurityCertUtils", "sendSettingDeviceCommand, responseCallback is null");
            return;
        }
        this.b = iBaseResponseCallback;
        c();
        if (deviceInfo == null) {
            LogUtil.h("DeviceSecurityCertUtils", "sendSettingDeviceCommand, deviceInfo is null or switchStatus isEmpty.");
            this.b.d(-1, "");
        } else if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("DeviceSecurityCertUtils", "device is not connected.");
            this.b.d(-1, "");
        } else {
            cvq e2 = e(iArr, str);
            LogUtil.c("DeviceSecurityCertUtils", "constructSampleConfig: ", e2);
            cuk.b().sendSampleConfigCommand(deviceInfo, e2, "DeviceSecurityCertUtils");
        }
    }

    private cvq e(int[] iArr, String str) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.certificateChain");
        cvqVar.setWearPkgName("in.huawei.AutDevice");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900100012L);
        cvnVar.d(2);
        cvnVar.c(b(iArr, str));
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    private byte[] b(int[] iArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(b(1, i));
        }
        sb.append(b(2, str));
        return HEXUtils.c(sb.toString());
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("DeviceSecurityCertUtils", "onDataReceived() errorCode = ", Integer.valueOf(i));
        if (this.b == null) {
            LogUtil.h("DeviceSecurityCertUtils", "onDataReceived() response callback is null");
            return;
        }
        if (deviceInfo == null || a(cvrVar)) {
            LogUtil.h("DeviceSecurityCertUtils", "onDataReceived() device == null or isCheckSampleError");
            this.b.d(-1, "");
            return;
        }
        if (cvrVar instanceof cvq) {
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("DeviceSecurityCertUtils", "onDataReceived sampleConfigInfoList empty");
                this.b.d(-1, "");
                return;
            }
            cvn cvnVar = configInfoList.get(0);
            int e2 = cvnVar.e();
            LogUtil.a("DeviceSecurityCertUtils", "onDataReceived configAction", Integer.valueOf(e2));
            if (e2 == 2) {
                e(i, cvnVar);
                return;
            } else {
                this.b.d(-1, "");
                return;
            }
        }
        LogUtil.a("DeviceSecurityCertUtils", "onDataReceived() message not instanceof SampleConfig");
        this.b.d(-1, "");
    }

    private void e(int i, cvn cvnVar) {
        try {
            List<cwd> e2 = new cwl().a(HEXUtils.a(cvnVar.b())).e();
            if (e2 != null && e2.size() != 0) {
                this.b.d(i, StringUtils.b(HEXUtils.c(e2.get(0).c())));
                return;
            }
            LogUtil.h("DeviceSecurityCertUtils", "getCertContent() tlvList is empty");
            this.b.d(-1, "");
        } catch (cwg e3) {
            LogUtil.b("DeviceSecurityCertUtils", "parseConfigData tlvException11: ", ExceptionUtils.d(e3));
            this.b.d(-1, "");
        } catch (NumberFormatException e4) {
            LogUtil.b("DeviceSecurityCertUtils", "parseConfigData numberFormatException: ", ExceptionUtils.d(e4));
            this.b.d(-1, "");
        }
    }

    private boolean a(cvr cvrVar) {
        if (cvrVar == null) {
            LogUtil.a("DeviceSecurityCertUtils", "sampleBase == null");
            return true;
        }
        if (cvrVar.getCommandId() == 1 && "in.huawei.AutDevice".equals(cvrVar.getSrcPkgName()) && "hw.unitedevice.certificateChain".equals(cvrVar.getWearPkgName())) {
            return false;
        }
        LogUtil.h("DeviceSecurityCertUtils", "message: ", cvrVar.toString());
        return true;
    }

    private String b(int i, int i2) {
        return HEXUtils.e(i) + HEXUtils.c(1) + HEXUtils.e(i2);
    }

    private String b(int i, String str) {
        String b = HEXUtils.b(str);
        return HEXUtils.e(i) + HEXUtils.c(b.length() / 2) + b;
    }
}
