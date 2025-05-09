package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kdj {

    /* renamed from: a, reason: collision with root package name */
    private String f14305a;
    private DataReceiveCallback b;
    private String c;
    private kdf d;
    private final BroadcastReceiver e;
    private String j;

    private kdj() {
        this.j = "HwSeizeDeviceMgr";
        this.f14305a = "hw.unitedevice.seizedevice";
        this.c = "seizedevice";
        this.d = new kdf();
        this.b = new DataReceiveCallback() { // from class: kdj.3
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                LogUtil.a(kdj.this.j, "callback onDataReceived");
                if (deviceInfo == null || cvrVar == null) {
                    LogUtil.a(kdj.this.j, "device or message is null.");
                } else if (cvrVar instanceof cvq) {
                    kdj.this.a(i, deviceInfo, (cvq) cvrVar);
                }
            }
        };
        this.e = new BroadcastReceiver() { // from class: kdj.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.h(kdj.this.j, "mDeviceStatusReceiver onReceive intent is null.");
                    return;
                }
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    LogUtil.h(kdj.this.j, "mDeviceStatusReceiver onReceive action is null");
                    return;
                }
                LogUtil.a(kdj.this.j, "mDeviceStatusReceiver onReceive action :", action);
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                    DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                    if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && cwi.c(deviceInfo, 93)) {
                        kdj.this.d();
                    }
                }
            }
        };
        LogUtil.a(this.j, "create HwSeizeDeviceMgr");
        d();
        c();
    }

    private void c() {
        LogUtil.a(this.j, "enter registerBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.e, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a(this.j, "enter registerDeviceSampleListener");
        cuk.b().registerDeviceSampleListener(this.f14305a, this.b);
    }

    private void c(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h(this.j, "notifyPartsSeize partsDevice is null.");
            return;
        }
        LogUtil.a(this.j, "notifyPartsSeize enter with partsDevice: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", identify: ", CommonUtil.l(str));
        cvq cvqVar = new cvq();
        cvn cvnVar = new cvn();
        b(cvqVar, cvnVar);
        String e = this.d.e(str, deviceInfo.getDeviceIdentify());
        if (TextUtils.isEmpty(e)) {
            LogUtil.h(this.j, "notifyPartsSeize seizeInfo is null.");
        } else {
            cvnVar.c(cvx.a(e));
            cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "notifyPartsSeize");
        }
    }

    private void c(DeviceInfo deviceInfo, int i, String str, String str2) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h(this.j, "replyDeviceResult deviceInfo is null.");
            return;
        }
        LogUtil.a(this.j, "replyDeviceResult enter with deviceInfo: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", partsIdentify: ", CommonUtil.l(str), ", partsName: ", str2);
        cvq cvqVar = new cvq();
        cvn cvnVar = new cvn();
        b(cvqVar, cvnVar);
        String d = this.d.d(i, str, str2);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h(this.j, "replyDeviceResult replyResult is null.");
        } else {
            cvnVar.c(cvx.a(d));
            cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "replyDeviceResult");
        }
    }

    public void a(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null || TextUtils.isEmpty(str)) {
            LogUtil.h(this.j, "requestDeviceSeize input parameter is invalid.");
            return;
        }
        LogUtil.a(this.j, "requestDeviceSeize enter with deviceInfo: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", identify: ", CommonUtil.l(str));
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, this.j);
        if (deviceList.size() != 0) {
            b(deviceInfo, deviceList.get(0));
        }
    }

    private void b(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h(this.j, "requestDeviceSeize deviceInfo is null.");
            return;
        }
        cvq cvqVar = new cvq();
        cvn cvnVar = new cvn();
        b(cvqVar, cvnVar);
        String b = this.d.b(deviceInfo2.getDeviceIdentify(), deviceInfo2.getHiLinkDeviceId(), deviceInfo2.getDeviceName());
        LogUtil.a(this.j, "requestDeviceSeize enter with deviceInfo: ", deviceInfo, ", partsIdentify: ", CommonUtil.l(deviceInfo2.getDeviceIdentify()), ", hiLinkId: ", deviceInfo2.getHiLinkDeviceId(), ", partsName: ", deviceInfo2.getDeviceName(), "requestDeviceSeize requestSeize: ", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h(this.j, "requestDeviceSeize requestSeize is null.");
        } else {
            cvnVar.c(cvx.a(b));
            cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "requestDeviceSeize");
        }
    }

    private void b(cvq cvqVar, cvn cvnVar) {
        cvqVar.setCommandId(1);
        cvqVar.setSrcPkgName(this.f14305a);
        cvqVar.setWearPkgName(this.c);
        cvnVar.e(900100004L);
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, DeviceInfo deviceInfo, cvq cvqVar) {
        String c2 = c(deviceInfo, cvqVar);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h(this.j, "getResult configData is null.");
            return;
        }
        try {
            List<cwd> e = new cwl().a(c2).e();
            if (koq.b(e)) {
                LogUtil.h(this.j, "getResult tlvList is null or size is zero");
                return;
            }
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                LogUtil.h(this.j, "getResult type is: ", Integer.valueOf(w));
                if (w == 8) {
                    b(deviceInfo, e);
                    return;
                }
                if (w == 13) {
                    e(deviceInfo, e);
                    return;
                }
                if (w == 15) {
                    d(deviceInfo, e);
                    return;
                } else if (w == 127) {
                    int w2 = CommonUtil.w(cwdVar.c());
                    LogUtil.h(this.j, "getResult CommonUtil.ERROR_TYPE, errorValue: ", Integer.valueOf(w2));
                    if (w2 != -1) {
                        return;
                    }
                } else {
                    LogUtil.c(this.j, "getResult default: ", Integer.valueOf(w));
                }
            }
        } catch (cwg e2) {
            LogUtil.b(this.j, "getResult Exception ", ExceptionUtils.d(e2));
        }
    }

    private String c(DeviceInfo deviceInfo, cvq cvqVar) {
        if (deviceInfo == null || cvqVar == null) {
            LogUtil.h(this.j, "getResult() getConfigData input parameter is null.");
            return null;
        }
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        cvn cvnVar = configInfoList != null ? configInfoList.get(0) : null;
        String d = cvnVar != null ? cvx.d(cvnVar.b()) : null;
        LogUtil.a(this.j, "getResult() getConfigData configData:", d);
        return d;
    }

    public void b(DeviceInfo deviceInfo, List<cwd> list) {
        if (deviceInfo == null || koq.b(list)) {
            LogUtil.h(this.j, "handleSeizeNotify input parameter is null.");
            return;
        }
        DeviceInfo deviceInfo2 = new DeviceInfo();
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            switch (w) {
                case 8:
                    deviceInfo2.setDeviceIdentify(cvx.e(cwdVar.c()));
                    break;
                case 9:
                    deviceInfo2.setHiLinkDeviceId(cvx.e(cwdVar.c()));
                    break;
                case 10:
                    deviceInfo2.setDeviceName(cvx.e(cwdVar.c()));
                    break;
                default:
                    LogUtil.h(this.j, "handleSeizeNotify default type: ", Integer.valueOf(w));
                    break;
            }
        }
        for (DeviceInfo deviceInfo3 : cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, this.j)) {
            LogUtil.a(this.j, "handleSeizeNotify info.getDeviceIdentify: ", CommonUtil.l(deviceInfo3.getDeviceIdentify()), ", deviceInfo.getDeviceIdentify: ", CommonUtil.l(deviceInfo2.getDeviceIdentify()), ", info.getDeviceName: ", deviceInfo3.getDeviceName(), ", deviceInfo.getDeviceName: ", deviceInfo2.getDeviceName());
            if (deviceInfo3.getDeviceIdentify().contains(deviceInfo2.getDeviceIdentify()) && deviceInfo3.getDeviceName().equalsIgnoreCase(deviceInfo2.getDeviceName())) {
                c(deviceInfo3, deviceInfo.getDeviceIdentify());
                return;
            }
        }
        c(deviceInfo2, 1, deviceInfo2.getDeviceIdentify(), deviceInfo2.getDeviceName());
    }

    public void e(DeviceInfo deviceInfo, List<cwd> list) {
        if (deviceInfo == null || koq.b(list)) {
            LogUtil.h(this.j, "handleSeizeResult input parameter is null.");
            return;
        }
        String str = null;
        int i = 2;
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 13) {
                str = cvx.e(cwdVar.c());
            } else if (w == 14) {
                i = CommonUtil.w(cwdVar.c());
                LogUtil.a(this.j, "handleSeizeResult seizeResult: ", Integer.valueOf(i));
            } else {
                LogUtil.h(this.j, "handleSeizeResult default type: ", Integer.valueOf(w));
            }
        }
        if (i == 1 && !TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.a(this.j, "deviceIdentify start disconnect Device, seizeResult: ", Integer.valueOf(i));
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(deviceInfo.getDeviceIdentify());
            jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, this.j);
        if (koq.b(deviceList)) {
            return;
        }
        c(deviceList.get(0), i, deviceInfo.getDeviceIdentify(), deviceInfo.getDeviceName());
    }

    public void d(DeviceInfo deviceInfo, List<cwd> list) {
        if (deviceInfo == null || koq.b(list)) {
            LogUtil.h(this.j, "replySeizeResult input parameter is null.");
            return;
        }
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            switch (w) {
                case 15:
                    LogUtil.a(this.j, "replySeizeResult result: ", cvx.e(cwdVar.c()));
                    break;
                case 16:
                    LogUtil.a(this.j, "replySeizeResult identify: ", CommonUtil.l(cvx.e(cwdVar.c())));
                    break;
                case 17:
                    LogUtil.a(this.j, "replySeizeResult name: ", cvx.e(cwdVar.c()));
                    break;
                default:
                    LogUtil.h(this.j, "replySeizeResult default type: ", Integer.valueOf(w));
                    break;
            }
        }
    }

    public void a(String str) {
        LogUtil.a(this.j, "init tag: ", str);
    }

    public static kdj e() {
        return c.c;
    }

    static class c {
        private static kdj c = new kdj();
    }
}
