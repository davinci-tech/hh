package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.List;

/* loaded from: classes5.dex */
public class jwe implements DataReceiveCallback {
    private static volatile jwe e;
    private IBaseResponseCallback d;

    public static jwe e() {
        if (e == null) {
            synchronized (jwe.class) {
                if (e == null) {
                    e = new jwe();
                }
            }
        }
        return e;
    }

    public boolean d(DeviceInfo deviceInfo) {
        if (!LogUtil.c()) {
            return false;
        }
        boolean c = cwi.c(deviceInfo, 233);
        LogUtil.c("SmartGestureManager", "isSupportSmartGesture: ", Boolean.valueOf(c));
        return c;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        this.d = iBaseResponseCallback;
    }

    public void d(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.c("SmartGestureManager", "deviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.c("SmartGestureManager", "device is not connected.");
            return;
        }
        cvp a2 = a(z ? 1 : 0);
        LogUtil.c("SmartGestureManager", "sendGestureCommand sampleEvent is ", a2.toString());
        boolean sendSampleEventCommand = cuk.b().sendSampleEventCommand(deviceInfo, a2, "SmartGestureManager");
        LogUtil.c("SmartGestureManager", "sendGestureTagCommand  isSuccess = ", Boolean.valueOf(sendSampleEventCommand));
        if (sendSampleEventCommand) {
            b(z ? 1 : 0);
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = this.d;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(-1, null);
        }
    }

    private cvp a(int i) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.smartGesture");
        cvpVar.setWearPkgName("hw.watch.smartGesture");
        cvpVar.a(800100017L);
        cvpVar.setCommandId(2);
        cvpVar.b(0);
        cvpVar.e(e(i));
        return cvpVar;
    }

    private byte[] e(int i) {
        bms bmsVar = new bms();
        bmsVar.j(1, 2).j(2, i);
        return bmsVar.d();
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.c("SmartGestureManager", "onDataReceived enter");
        if (deviceInfo == null) {
            LogUtil.a("SmartGestureManager", "onDataReceived deviceInfo is null");
            return;
        }
        if (cvrVar != null) {
            LogUtil.c("SmartGestureManager", "onDataReceived sampleBase is ", cvrVar.toString());
            if (!(cvrVar instanceof cvp)) {
                LogUtil.a("SmartGestureManager", "onDataReceived sampleBase not instanceof SampleEvent");
                return;
            }
            cvp cvpVar = (cvp) cvrVar;
            LogUtil.c("SmartGestureManager", "onDataReceived sampleEvent is ", cvpVar.toString());
            try {
                String srcPkgName = cvpVar.getSrcPkgName();
                String wearPkgName = cvpVar.getWearPkgName();
                long e2 = cvpVar.e();
                int commandId = cvpVar.getCommandId();
                int a2 = cvpVar.a();
                byte[] c = cvpVar.c();
                if (!TextUtils.isEmpty(srcPkgName) && !TextUtils.isEmpty(wearPkgName)) {
                    if ("hw.unitedevice.smartGesture".equals(wearPkgName) && "hw.watch.smartGesture".equals(srcPkgName)) {
                        if (e2 != 800100017) {
                            LogUtil.a("SmartGestureManager", "eventId does not match");
                            return;
                        }
                        if (commandId != 2) {
                            LogUtil.a("SmartGestureManager", "commandId does not match");
                            return;
                        }
                        if (a2 != 0) {
                            LogUtil.a("SmartGestureManager", "eventLevel does not match");
                            return;
                        }
                        if (c != null && c.length != 0) {
                            String d = cvx.d(c);
                            if (TextUtils.isEmpty(d)) {
                                LogUtil.a("SmartGestureManager", "byteToHex is null");
                                return;
                            }
                            cwe a3 = new cwl().a(d);
                            if (a3 == null) {
                                LogUtil.a("SmartGestureManager", "tlvFather is null");
                                return;
                            }
                            List<cwd> e3 = a3.e();
                            if (e3 != null && !e3.isEmpty()) {
                                if (e3.size() != 2) {
                                    LogUtil.a("SmartGestureManager", "tlvs.size does not match");
                                    return;
                                }
                                String c2 = e3.get(0).c();
                                String c3 = e3.get(1).c();
                                LogUtil.c("SmartGestureManager", "operateKey is :", c2, " getstureKey is :", c3);
                                if (!TextUtils.isEmpty(c2) && !TextUtils.isEmpty(c3)) {
                                    int w = CommonUtil.w(c2);
                                    int w2 = CommonUtil.w(c3);
                                    LogUtil.c("SmartGestureManager", "operate is :", Integer.valueOf(w), " smartGestureEnable is :", Integer.valueOf(w2));
                                    if (w == 3) {
                                        if (w2 == 1 || w2 == 0) {
                                            b(w2);
                                            IBaseResponseCallback iBaseResponseCallback = this.d;
                                            if (iBaseResponseCallback != null) {
                                                iBaseResponseCallback.d(0, null);
                                                return;
                                            } else {
                                                LogUtil.a("SmartGestureManager", "onDataReceived mCallback is null");
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            LogUtil.a("SmartGestureManager", "tlvs are Empty");
                            return;
                        }
                        LogUtil.a("SmartGestureManager", "eventData is null");
                        return;
                    }
                    LogUtil.a("SmartGestureManager", "srcPkgName or wearPkgName does not match");
                    return;
                }
                LogUtil.a("SmartGestureManager", "srcPkgName or wearPkgName is empty");
                return;
            } catch (cwg | IndexOutOfBoundsException unused) {
                LogUtil.e("SmartGestureManager", "parseSampleEvent Exception");
                return;
            }
        }
        LogUtil.a("SmartGestureManager", "onDataReceived sampleBase is null");
    }

    private void b(int i) {
        LogUtil.c("SmartGestureManager", "saveSmartGestureStatus is : ", Integer.valueOf(i));
        if (i == 1) {
            SharedPreferenceManager.e("smart_gesture", "key_smart_gesture_switch_status", true);
        } else {
            SharedPreferenceManager.e("smart_gesture", "key_smart_gesture_switch_status", false);
        }
    }

    public boolean d() {
        boolean a2 = SharedPreferenceManager.a("smart_gesture", "key_smart_gesture_switch_status", false);
        LogUtil.c("SmartGestureManager", "isSmartGestureOpen is : ", Boolean.valueOf(a2));
        return a2;
    }

    public void c() {
        LogUtil.c("SmartGestureManager", "registerSmartGestureListener");
        cuk.b().registerDeviceSampleListener("hw.unitedevice.smartGesture", this);
    }

    public void a() {
        LogUtil.c("SmartGestureManager", "registerSmartGestureListener");
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.smartGesture");
    }
}
