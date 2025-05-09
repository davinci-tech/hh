package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.devicesdk.callback.BtDevicePairCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwearphonepairmgr.EarPhoneCallBack;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bjf;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes5.dex */
public class kcf implements DataReceiveCallback {
    private static volatile kcf c;
    private DeviceInfo f;
    private volatile EarPhoneCallBack g;
    private ExtendHandler m;
    private String o;
    private static final Object e = new Object();
    private static Map<String, cuz> d = new HashMap(16);
    private static final String[] b = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14271a = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
    private int n = -1;
    private int h = -1;
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: kcf.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                kcf.this.bMY_(intent);
            } else {
                LogUtil.h("EarPhonePairGuideManager", "context or intent is null");
            }
        }
    };
    private BtDevicePairCallback j = new BtDevicePairCallback() { // from class: kcf.2
        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairing(BluetoothDevice bluetoothDevice) {
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePaired(BluetoothDevice bluetoothDevice) {
            if (kcf.this.m.hasMessages(1)) {
                kcf.this.d(bluetoothDevice.getAddress());
                if (kcf.this.g(bluetoothDevice.getAddress())) {
                    kcf.this.m.removeMessages(1);
                    kcf.this.c();
                    return;
                }
                return;
            }
            LogUtil.h("EarPhonePairGuideManager", "onDevicePaired, time out or not start pair");
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairNone(BluetoothDevice bluetoothDevice) {
            LogUtil.h("EarPhonePairGuideManager", "onDevicePairNone");
            if (kcf.this.m == null || !kcf.this.m.hasMessages(2)) {
                if (kcf.this.m != null) {
                    kcf.this.m.removeMessages(1);
                }
                sqo.l("earphone is onDevicePairNone");
                kcf kcfVar = kcf.this;
                kcfVar.i(kcfVar.f);
                return;
            }
            LogUtil.h("EarPhonePairGuideManager", "earphone is remove");
        }
    };

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("EarPhonePairGuideManager", "message.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                sqo.l("earphone connect timeout");
                kcf kcfVar = kcf.this;
                kcfVar.i(kcfVar.f);
                return true;
            }
            if (i == 2) {
                if (message.obj instanceof String) {
                    kcf.this.i((String) message.obj);
                }
                return true;
            }
            LogUtil.h("EarPhonePairGuideManager", "other messgae");
            return false;
        }
    }

    private kcf() {
        this.m = null;
        LogUtil.a("EarPhonePairGuideManager", "create EarPhonePairGuideManager.");
        f();
        bkt.e();
        this.m = HandlerCenter.yt_(new c(), "EarPhonePairGuideManager");
    }

    public static kcf b() {
        if (c == null) {
            synchronized (kcf.class) {
                if (c == null) {
                    c = new kcf();
                }
            }
        }
        return c;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void e(DeviceInfo deviceInfo, String str) {
        char c2;
        if (deviceInfo == null || TextUtils.isEmpty(str)) {
            LogUtil.h("EarPhonePairGuideManager", "updateEarPhoneMessage, device info or message is null");
            return;
        }
        LogUtil.a("EarPhonePairGuideManager", "deal message from main process: ", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -1859993275:
                if (str.equals("start_pair_info_back_device")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1264327325:
                if (str.equals("pair_fail")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1140383611:
                if (str.equals("cancel_pair_ear_phone")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -818495437:
                if (str.equals("skip_pair_info")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 510591735:
                if (str.equals("pull_up_earphone_pair")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 865509884:
                if (str.equals("get_cached_ear_phone_info")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 899651953:
                if (str.equals("get_ear_phone_info")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1448648534:
                if (str.equals("start_pair_info")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1554996764:
                if (str.equals("retry_pair_info")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                e(deviceInfo, 0);
                break;
            case 1:
                f(deviceInfo);
                break;
            case 2:
                j(deviceInfo);
                break;
            case 3:
                n(deviceInfo);
                break;
            case 4:
                h(deviceInfo);
                break;
            case 5:
                a(deviceInfo);
                break;
            case 6:
                b(deviceInfo);
                break;
            case 7:
                e(deviceInfo, 9);
                break;
            case '\b':
                k(deviceInfo);
                break;
            default:
                LogUtil.h("EarPhonePairGuideManager", "updateEarPhoneMessage, other message: ", str);
                break;
        }
    }

    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("EarPhonePairGuideManager", "unPairEarphone in.");
        Map<String, cuz> map = d;
        if (map == null) {
            LogUtil.a("EarPhonePairGuideManager", "unPairEarphone sEarPhoneMap is null");
            return;
        }
        if (!map.containsKey(deviceInfo.getDeviceIdentify())) {
            o();
        }
        cuz cuzVar = d.get(deviceInfo.getDeviceIdentify());
        if (cuzVar != null && !TextUtils.isEmpty(cuzVar.c())) {
            if (iyl.d().c(cuzVar.c())) {
                return;
            }
            LogUtil.h("EarPhonePairGuideManager", "unPairEarphone fail.");
            return;
        }
        c(deviceInfo, new EarPhoneCallBack() { // from class: kcf.4
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwearphonepairmgr.EarPhoneCallBack
            public void onResponse(cuz cuzVar2, DeviceInfo deviceInfo2) {
                LogUtil.a("EarPhonePairGuideManager", "start unPairEarphone");
                if (iyl.d().c(cuzVar2.c())) {
                    return;
                }
                LogUtil.h("EarPhonePairGuideManager", "unPairEarphone fail.");
            }
        });
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("EarPhonePairGuideManager", "onDataReceived: ", Integer.valueOf(i));
        if (cvrVar instanceof cvq) {
            cvq cvqVar = (cvq) cvrVar;
            if (cvrVar.getCommandId() != 1) {
                LogUtil.h("EarPhonePairGuideManager", "onDataReceived, message commandId", Integer.valueOf(cvrVar.getCommandId()));
                return;
            }
            List<cvn> configInfoList = cvqVar.getConfigInfoList();
            if (configInfoList == null || configInfoList.size() == 0) {
                LogUtil.h("EarPhonePairGuideManager", "onDataReceived sampleConfigInfos empty");
                return;
            }
            String d2 = cvx.d(configInfoList.get(0).b());
            LogUtil.a("EarPhonePairGuideManager", "configData: ", d2);
            cuz cuzVar = new cuz();
            b(d2, cuzVar);
            if (this.g != null) {
                this.g.onResponse(cuzVar, deviceInfo);
            }
            bmw.e(100067, nrv.a(cuzVar), "", "");
            return;
        }
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            if (cvrVar.getCommandId() != 2) {
                LogUtil.h("EarPhonePairGuideManager", "onDataReceived, message commandId", Integer.valueOf(cvrVar.getCommandId()));
                return;
            }
            String d3 = cvx.d(cvpVar.c());
            int w = CommonUtil.w(d3);
            LogUtil.a("EarPhonePairGuideManager", "eventData: ", d3, " status: ", Integer.valueOf(w));
            d(w, deviceInfo);
        }
    }

    private void d(int i, DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            bmw.e(100069, deviceInfo.getDeviceName(), String.valueOf(i), "");
        }
        if (i == 0) {
            c(deviceInfo);
            return;
        }
        if (i == 1 || i == 2) {
            LogUtil.a("EarPhonePairGuideManager", "DEVICE_IGNORE_PAIR");
            a(2, deviceInfo);
            return;
        }
        if (i == 5) {
            a(5, deviceInfo);
            return;
        }
        if (i == 6) {
            a(6, deviceInfo);
            return;
        }
        if (i == 8) {
            h();
            return;
        }
        if (i == 9) {
            d(deviceInfo);
            return;
        }
        if (i == 11) {
            a(11, deviceInfo);
        } else if (i == 12) {
            a(12, deviceInfo);
        } else {
            LogUtil.a("EarPhonePairGuideManager", "other status");
        }
    }

    private void b(String str, cuz cuzVar) {
        try {
            List<cwd> e2 = new cwl().a(str).e();
            if (e2 != null && e2.size() != 0) {
                for (cwd cwdVar : e2) {
                    switch (CommonUtil.w(cwdVar.e())) {
                        case 1:
                            cuzVar.e(cvx.e(cwdVar.c()).trim());
                            break;
                        case 2:
                            cuzVar.a(cvx.e(cwdVar.c()).trim());
                            break;
                        case 3:
                            cuzVar.b(CommonUtil.w(cwdVar.c()));
                            break;
                        case 4:
                            cuzVar.d(CommonUtil.w(cwdVar.c()));
                            break;
                        case 5:
                            cuzVar.a(CommonUtil.w(cwdVar.c()));
                            break;
                        case 6:
                            cuzVar.e(CommonUtil.w(cwdVar.c()));
                            break;
                        default:
                            Object[] objArr = new Object[1];
                            objArr[0] = "other info";
                            LogUtil.a("EarPhonePairGuideManager", objArr);
                            break;
                    }
                }
                return;
            }
            LogUtil.h("EarPhonePairGuideManager", "tlvList is empty");
        } catch (cwg unused) {
            LogUtil.b("EarPhonePairGuideManager", "parseData is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bMY_(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            LogUtil.a("EarPhonePairGuideManager", "action is null");
            return;
        }
        LogUtil.a("EarPhonePairGuideManager", "action: ", action);
        if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            this.n = intExtra;
            LogUtil.a("EarPhonePairGuideManager", "mHfpStats: ", Integer.valueOf(intExtra));
            if (!bMX_(intent)) {
                LogUtil.h("EarPhonePairGuideManager", "not current ear phone");
                return;
            } else if (this.n == 2) {
                c();
                return;
            }
        }
        if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            this.h = intExtra2;
            LogUtil.a("EarPhonePairGuideManager", "mA2dpStats: ", Integer.valueOf(intExtra2));
            if (!bMX_(intent)) {
                LogUtil.h("EarPhonePairGuideManager", "not current ear phone");
                return;
            } else if (this.h == 2) {
                c();
                return;
            }
        }
        sqo.l("a2dp or hfp connect error");
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.m.removeMessages(1);
        this.n = -1;
        this.h = -1;
        a(1, this.f);
        g(this.f);
        m();
    }

    private void e() {
        if (this.n == 0 && this.h == 0) {
            this.n = -1;
            this.h = -1;
            i(this.f);
        }
    }

    private boolean bMX_(Intent intent) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            LogUtil.h("EarPhonePairGuideManager", "hfp btDevice is null");
            return false;
        }
        String address = bluetoothDevice.getAddress();
        return (TextUtils.isEmpty(address) || TextUtils.isEmpty(this.o) || !TextUtils.equals(address, this.o)) ? false : true;
    }

    private void c(DeviceInfo deviceInfo) {
        c(deviceInfo, new EarPhoneCallBack() { // from class: kcf.1
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwearphonepairmgr.EarPhoneCallBack
            public void onResponse(cuz cuzVar, DeviceInfo deviceInfo2) {
                LogUtil.a("EarPhonePairGuideManager", "deviceStartPair");
                kcf.this.a(deviceInfo2.getDeviceIdentify(), cuzVar);
                kcf.this.a(4, deviceInfo2);
                kcf.this.e(deviceInfo2, 9);
            }
        });
    }

    private void b(DeviceInfo deviceInfo) {
        c(deviceInfo, new EarPhoneCallBack() { // from class: kcf.5
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwearphonepairmgr.EarPhoneCallBack
            public void onResponse(cuz cuzVar, DeviceInfo deviceInfo2) {
                LogUtil.a("EarPhonePairGuideManager", "getEarPhoneInfo");
                kcf.this.a(deviceInfo2.getDeviceIdentify(), cuzVar);
                kcf.this.a(4, deviceInfo2);
            }
        });
    }

    private void a(DeviceInfo deviceInfo) {
        c(deviceInfo, new EarPhoneCallBack() { // from class: kcf.9
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwearphonepairmgr.EarPhoneCallBack
            public void onResponse(cuz cuzVar, DeviceInfo deviceInfo2) {
                LogUtil.a("EarPhonePairGuideManager", "getCachedEarPhoneInfo");
                if (cuzVar != null && !TextUtils.isEmpty(cuzVar.c())) {
                    kcf.this.a(deviceInfo2.getDeviceIdentify(), cuzVar);
                }
                kcf.this.a(4, deviceInfo2);
            }
        });
    }

    private void f() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.earphoneManager", this);
    }

    private void j() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            BaseApplication.getContext().registerReceiver(this.i, intentFilter);
        } catch (IllegalArgumentException | SecurityException e2) {
            LogUtil.b("EarPhonePairGuideManager", "registerConnectedBroadcast is error:", ExceptionUtils.d(e2));
        }
    }

    private void m() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.i);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("EarPhonePairGuideManager", "unRegisterBroadCast is error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, int i) {
        Map<String, cuz> map;
        if (deviceInfo == null || (map = d) == null) {
            LogUtil.h("EarPhonePairGuideManager", "pairEarPhone, deviceInfo is null");
            return;
        }
        cuz cuzVar = map.get(deviceInfo.getDeviceIdentify());
        if (cuzVar == null) {
            LogUtil.h("EarPhonePairGuideManager", "pairEarPhone, earPhoneInfo is null");
            return;
        }
        String c2 = cuzVar.c();
        if (TextUtils.isEmpty(c2) || !BluetoothAdapter.checkBluetoothAddress(c2)) {
            LogUtil.h("EarPhonePairGuideManager", "pairEarPhone, earPhoneId is null or earPhoneId is wrong");
            sqo.l("earphone id is error");
            i(deviceInfo);
        } else {
            if ((cuzVar.d() & 1) == 0) {
                a(0, deviceInfo);
                return;
            }
            if (cuzVar.a() == 0) {
                LogUtil.h("EarPhonePairGuideManager", "earphone is outside");
                a(0, deviceInfo);
            } else {
                if (cuzVar.e() == 0) {
                    LogUtil.h("EarPhonePairGuideManager", "earphone is not close");
                    sqo.l("earphone is not close");
                    i(deviceInfo);
                    return;
                }
                a(deviceInfo, i);
            }
        }
    }

    private void c(DeviceInfo deviceInfo, EarPhoneCallBack earPhoneCallBack) {
        this.g = earPhoneCallBack;
        m(deviceInfo);
        this.f = deviceInfo;
        cuk.b().sendSampleConfigCommand(deviceInfo, d(), "EarPhonePairGuideManager");
        bmw.e(100066, "", "", "");
    }

    private cvq d() {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.earphoneManager");
        cvqVar.setWearPkgName("earphoneManager");
        cvqVar.setCommandId(1);
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900100008L);
        cvnVar.d(2);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    private void j(DeviceInfo deviceInfo) {
        Map<String, cuz> map = d;
        if (map != null) {
            map.remove(deviceInfo.getDeviceIdentify());
        }
        n();
        this.o = null;
        m();
        ExtendHandler extendHandler = this.m;
        if (extendHandler != null) {
            extendHandler.removeMessages(1);
        }
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(7), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(7), "");
    }

    private void n() {
        if (this.o == null) {
            LogUtil.h("EarPhonePairGuideManager", "removeEarPhone, mEarPhoneIdentify is null");
            return;
        }
        try {
            BluetoothDevice bDg_ = iyl.d().bDg_(this.o);
            if (bDg_ == null) {
                LogUtil.h("EarPhonePairGuideManager", "btDevice is null");
                return;
            }
            bkw.d().sL_(bDg_);
            if (bDg_.getBondState() == 12) {
                iyl.d().bDk_(bDg_);
            } else {
                iyl.d().b(this.o);
            }
        } catch (SecurityException e2) {
            LogUtil.b("EarPhonePairGuideManager", "removeEarPhone SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void n(DeviceInfo deviceInfo) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(3), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(3), "");
    }

    private void k(DeviceInfo deviceInfo) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(4), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(4), "");
    }

    private void f(DeviceInfo deviceInfo) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(8), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(8), "");
    }

    private void g(DeviceInfo deviceInfo) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(13), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(13), "");
    }

    private void h(DeviceInfo deviceInfo) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(11), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(11), "");
    }

    private void a(DeviceInfo deviceInfo, int i) {
        m(deviceInfo);
        cuk.b().sendSampleEventCommand(deviceInfo, a(i), "EarPhonePairGuideManager");
        bmw.e(100068, "", String.valueOf(i), "");
    }

    private cvp a(int i) {
        LogUtil.a("EarPhonePairGuideManager", "getSampleEvent: ", Integer.valueOf(i));
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.earphoneManager");
        cvpVar.setWearPkgName("earphoneManager");
        cvpVar.setCommandId(2);
        cvpVar.a(800100005L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(cvx.e(i)));
        return cvpVar;
    }

    private void m(DeviceInfo deviceInfo) {
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(deviceInfo.getDeviceIdentify());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "EarPhonePairGuideManager");
        if (bkz.e(deviceList)) {
            return;
        }
        DeviceInfo deviceInfo2 = deviceList.get(0);
        if (!TextUtils.isEmpty(deviceInfo.getExpandCapability()) || deviceInfo2 == null || TextUtils.isEmpty(deviceInfo2.getExpandCapability())) {
            return;
        }
        deviceInfo.setExpandCapability(deviceInfo2.getExpandCapability());
    }

    private void d(DeviceInfo deviceInfo) {
        LogUtil.a("EarPhonePairGuideManager", "checkEarPhone: ");
        Map<String, cuz> map = d;
        if (map == null) {
            return;
        }
        cuz cuzVar = map.get(deviceInfo.getDeviceIdentify());
        if (cuzVar == null) {
            LogUtil.h("EarPhonePairGuideManager", "checkEarPhone, earPhoneInfo is null");
            return;
        }
        String c2 = cuzVar.c();
        if (TextUtils.isEmpty(c2)) {
            i(deviceInfo);
            return;
        }
        this.o = c2;
        Vector<String> b2 = bkw.d().b();
        if (!bkz.e(b2) && b2.contains(c2)) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = c2;
            this.m.sendMessage(obtain, 1000L);
            return;
        }
        i(c2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final String str) {
        if (e(str)) {
            d(str);
            if (g(str)) {
                c();
                return;
            }
            return;
        }
        if ((a() && g()) || !i()) {
            LogUtil.h("EarPhonePairGuideManager", "startScanEarPhone, app is not has permission");
            c(str);
            return;
        }
        final boolean[] zArr = {false};
        bjf.a aVar = new bjf.a();
        aVar.e(5);
        aVar.a(str);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(aVar.a());
        snq.c().scanDevice(ScanMode.BR_BLE, arrayList, new DeviceScanCallback() { // from class: kcf.7
            @Override // com.huawei.devicesdk.callback.DeviceScanCallback
            public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str2, int i) {
                if (i == 22) {
                    LogUtil.a("EarPhonePairGuideManager", "startScan finish");
                    boolean[] zArr2 = zArr;
                    if (zArr2[0]) {
                        return;
                    }
                    zArr2[0] = true;
                    kcf.this.c(str);
                    return;
                }
                if (uniteDevice == null || uniteDevice.getDeviceInfo() == null) {
                    LogUtil.h("EarPhonePairGuideManager", "device is null");
                    return;
                }
                LogUtil.h("EarPhonePairGuideManager", "startScan,result: ", uniteDevice.getDeviceInfo().getDeviceName(), " mac: ", blt.a(uniteDevice.getDeviceInfo().getDeviceMac()), " addition: ", str2);
                if (TextUtils.equals(uniteDevice.getDeviceInfo().getDeviceMac(), str)) {
                    boolean[] zArr3 = zArr;
                    if (zArr3[0]) {
                        LogUtil.h("EarPhonePairGuideManager", "scan aready finish");
                    } else {
                        zArr3[0] = true;
                        kcf.this.c(str);
                    }
                }
            }
        });
    }

    private boolean a() {
        return Build.VERSION.SDK_INT >= 29;
    }

    private boolean g() {
        return CommonUtil.x(BaseApplication.getContext()) && !jdi.c(BaseApplication.getContext(), f14271a);
    }

    private boolean i() {
        return jdi.c(BaseApplication.getContext(), b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (e(str)) {
            d(str);
            if (g(str)) {
                c();
                return;
            }
            return;
        }
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        if (remoteDevice == null) {
            LogUtil.h("EarPhonePairGuideManager", "connectedEarPhone error. mBluetoothDevice is null.");
            return;
        }
        this.m.sendEmptyMessage(1, 35000L);
        b(str);
        LogUtil.a("EarPhonePairGuideManager", "connectedEarPhone pair device. ", blt.a(str), " result:", Boolean.valueOf(bkw.d().sJ_(remoteDevice, this.j)));
    }

    private void b(final String str) {
        if (CommonUtil.bh()) {
            ThreadPoolManager.d().d("EarPhonePairGuideManager", new Runnable() { // from class: kcd
                @Override // java.lang.Runnable
                public final void run() {
                    kcf.a(str);
                }
            });
        }
    }

    static /* synthetic */ void a(String str) {
        if (bks.d(str)) {
            return;
        }
        bks.e(str);
    }

    private boolean e(String str) {
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (remoteDevice != null) {
                return remoteDevice.getBondState() == 12;
            }
            LogUtil.h("EarPhonePairGuideManager", "btDevicePairHfpAndA2dp error. mBluetoothDevice is null.");
            return false;
        } catch (IllegalArgumentException | SecurityException e2) {
            LogUtil.b("EarPhonePairGuideManager", "isConnected Exception:", ExceptionUtils.d(e2));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(String str) {
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        if (remoteDevice == null) {
            LogUtil.h("EarPhonePairGuideManager", "isConnectedHfpOrA2dp error. mBluetoothDevice is null.");
            return false;
        }
        boolean sx_ = bkt.e().sx_(remoteDevice);
        boolean sw_ = bkt.e().sw_(remoteDevice);
        LogUtil.a("EarPhonePairGuideManager", "isConnectedHfpOrA2dp", " isConnectedHfp: ", Boolean.valueOf(sx_), " isConnectedA2dp: ", Boolean.valueOf(sw_));
        return sw_ || sx_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        if (remoteDevice == null) {
            LogUtil.h("EarPhonePairGuideManager", "btDevicePairHfpAndA2dp error. mBluetoothDevice is null.");
            return;
        }
        boolean sx_ = bkt.e().sx_(remoteDevice);
        boolean sw_ = bkt.e().sw_(remoteDevice);
        LogUtil.a("EarPhonePairGuideManager", "btDevicePairHfpAndA2dp", " isConnectedHfp: ", Boolean.valueOf(sx_), " isConnectedA2dp: ", Boolean.valueOf(sw_));
        j();
        if (!sw_) {
            bkt.e().su_(remoteDevice);
        }
        if (sx_) {
            return;
        }
        bkt.e().sv_(remoteDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("EarPhonePairGuideManager", "notifyMessageResult, deviceInfo is null");
            return;
        }
        if (i == 1) {
            bmw.e(100070, deviceInfo.getDeviceName(), "", "");
        }
        try {
            Map<String, cuz> map = d;
            String a2 = map != null ? nrv.a(map.get(deviceInfo.getDeviceIdentify())) : "";
            LogUtil.a("EarPhonePairGuideManager", "notifyMessageResult: ", a2);
            INoitifyPhoneServiceCallback b2 = jso.d().b("earphoneManager");
            if (b2 != null) {
                b2.executeResponse("earphoneManager", i, deviceInfo, 0, a2);
            } else {
                LogUtil.h("EarPhonePairGuideManager", "mCommonCallback is null");
            }
        } catch (RemoteException unused) {
            LogUtil.b("EarPhonePairGuideManager", "notifyMessageResult, exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(DeviceInfo deviceInfo) {
        f(deviceInfo);
        h();
    }

    private void h() {
        Map<String, cuz> map;
        cuz cuzVar;
        a(-1, this.f);
        DeviceInfo deviceInfo = this.f;
        if (deviceInfo != null && (map = d) != null && (cuzVar = map.get(deviceInfo.getDeviceIdentify())) != null) {
            LogUtil.a("EarPhonePairGuideManager", "pairFail, isUnPair: ", Boolean.valueOf(iyl.d().c(cuzVar.c())));
        }
        this.m.removeMessages(1);
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, cuz cuzVar) {
        LogUtil.a("EarPhonePairGuideManager", "storeEarphoneMap. ");
        Map<String, cuz> map = d;
        if (map == null) {
            LogUtil.h("EarPhonePairGuideManager", "storeEarphoneMap sEarPhoneMap is null");
            return;
        }
        map.put(str, cuzVar);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), "cached_earphone_key", new Gson().toJson(d), new StorageParams(1));
    }

    private void o() {
        LogUtil.a("EarPhonePairGuideManager", "restoreEarphoneMapFromSp. ");
        try {
            Map<String, cuz> map = (Map) new Gson().fromJson(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), "cached_earphone_key"), new TypeToken<Map<String, cuz>>() { // from class: kcf.6
            }.getType());
            if (map != null) {
                d = map;
            } else {
                LogUtil.h("EarPhonePairGuideManager", "cachedEarPhoneInfoMap is null.");
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.b("EarPhonePairGuideManager", "restoreEarphoneMapFromSp jsonSyntaxException.");
        } catch (NumberFormatException unused2) {
            LogUtil.b("EarPhonePairGuideManager", "restoreEarphoneMapFromSp NumberFormatException.");
        }
    }
}
