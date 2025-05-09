package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwbtsdk.btdatatype.callback.BluetoothDialogCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceHfpStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceServiceBase;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import health.compact.a.BuildConfigProperties;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class izy {
    private static b e;
    private static Context g;
    private static izy i;
    private BluetoothDialogCallback ac;
    private final BtDeviceHfpStateCallback m;
    private iyl n;
    private final BtSwitchStateCallback s;
    private List<String> z;
    private static final Object d = new Object();
    private static final Object b = new Object();
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13697a = new Object();
    private static Map<String, izi> f = new HashMap(16);
    private static ConcurrentHashMap<String, izl> j = new ConcurrentHashMap<>(16);
    private BtDeviceStateCallback r = null;
    private IAddDeviceStateCallback o = null;
    private List<BluetoothDeviceNode> k = new ArrayList(16);
    private List<String> v = new ArrayList(16);
    private List<String> y = new ArrayList(16);
    private int q = -1;
    private String h = "";
    private boolean w = false;
    private int ad = -1;
    private boolean u = false;
    private ConcurrentHashMap<String, String> l = new ConcurrentHashMap<>(16);
    private BtDeviceDiscoverCallback x = new BtDeviceDiscoverCallback() { // from class: izy.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i2, String str) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i2, byte[] bArr) {
            if (bluetoothDeviceNode != null) {
                izy.this.b(bluetoothDeviceNode, i2);
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
            LogUtil.c("BTSDKApi", "onDeviceDiscoveryFinished");
            synchronized (izy.b) {
                if (izy.this.ac != null) {
                    izy.this.ac.onScanFinished();
                }
            }
        }
    };
    private BtDeviceStateCallback p = new BtDeviceStateCallback() { // from class: izy.3
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onAckReceived(DeviceInfo deviceInfo, int i2, byte[] bArr) {
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i2, OperationDeviceInfo operationDeviceInfo) {
            if (deviceInfo == null) {
                LogUtil.a("BTSDKApi", "onDeviceConnectionStateChanged btDeviceInfo is null");
            } else {
                LogUtil.c("BTSDKApi", "onDeviceConnectionStateChanged with btState: ", Integer.valueOf(i2));
                izy.this.e(deviceInfo, i2, operationDeviceInfo);
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void onDataReceived(DeviceInfo deviceInfo, int i2, byte[] bArr) {
            if (deviceInfo != null) {
                if (izy.this.r != null) {
                    izy.this.r.onDataReceived(deviceInfo, i2, bArr);
                    return;
                }
                return;
            }
            LogUtil.a("BTSDKApi", "onDataReceived btDeviceInfo is null");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
        public void disconnectBluetooth(DeviceInfo deviceInfo, int i2) {
            if (izy.this.r != null) {
                izy.this.r.disconnectBluetooth(deviceInfo, i2);
            }
        }
    };
    private final BtSwitchStateCallback t = new BtSwitchStateCallback() { // from class: izy.4
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i2) {
            LogUtil.c("BTSDKApi", "During add device then receive BT Switch state: ", Integer.valueOf(i2));
            if (i2 == 1) {
                izy.this.n.e(izy.this.t);
                if (izy.this.o != null) {
                    izy.this.o.onAddDeviceState(2);
                    return;
                }
                return;
            }
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        LogUtil.c("BTSDKApi", "mBtSwitchStateByAddDeviceCallback default");
                    }
                } else {
                    izy.this.n.e(izy.this.t);
                    if (izy.this.q == 0) {
                        izy.e.sendEmptyMessage(5);
                    }
                }
            }
        }
    };

    private izy(Context context) {
        this.n = null;
        BtDeviceHfpStateCallback btDeviceHfpStateCallback = new BtDeviceHfpStateCallback() { // from class: izy.5
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceHfpStateCallback
            public void onBtDeviceHfpConnected(BluetoothDevice bluetoothDevice, String str) {
                if (bluetoothDevice == null) {
                    LogUtil.a("BTSDKApi", "mBtDeviceHfpStateCallback btDevice is null.");
                    return;
                }
                try {
                    LogUtil.c("BTSDKApi", "Enter onBTDeviceHFPConnected() deviceName :", bluetoothDevice.getName());
                    DeviceInfo e2 = izm.e("reconnectDevices", bluetoothDevice.getAddress());
                    if (e2 != null && e2.getDeviceActiveState() == 1 && !"AndroidWear".equalsIgnoreCase(e2.getDeviceIdentify())) {
                        if (bml.b(izy.g, e2)) {
                            LogUtil.c("BTSDKApi", "Start to connect BT Device.");
                            String deviceIdentify = e2.getDeviceIdentify();
                            String address = bluetoothDevice.getAddress();
                            if (deviceIdentify.equalsIgnoreCase(address)) {
                                izi iziVar = (izi) izy.f.get(address);
                                if (iziVar == null) {
                                    LogUtil.a("BTSDKApi", "preDeviceSendCommandUtil is null");
                                    return;
                                }
                                boolean m = iziVar.m();
                                BTDeviceServiceBase i2 = iziVar.i();
                                int bTDeviceConnectState = i2 != null ? i2.getBTDeviceConnectState() : 3;
                                LogUtil.c("BTSDKApi", "The connected hfp is the wanted device, isAvailable : ", Boolean.valueOf(m));
                                if (e2.getDeviceProtocol() == 2 && (e2.getDeviceConnectState() == 5 || !m)) {
                                    LogUtil.c("BTSDKApi", "Current is headset status, so do not need connect BR device.");
                                    return;
                                } else if (bTDeviceConnectState != 2) {
                                    izy.this.bEc_(bluetoothDevice, str, e2);
                                    return;
                                } else {
                                    LogUtil.c("BTSDKApi", "The service layer is connected successfully.");
                                    return;
                                }
                            }
                            LogUtil.c("BTSDKApi", "The connected hfp is not wanted device, so stop to connect spp.");
                            return;
                        }
                        return;
                    }
                    LogUtil.c("BTSDKApi", "Parameter is incorrect.");
                } catch (SecurityException e3) {
                    LogUtil.e("BTSDKApi", "mBtDeviceHfpStateCallback SecurityException:", ExceptionUtils.d(e3));
                }
            }
        };
        this.m = btDeviceHfpStateCallback;
        BtSwitchStateCallback btSwitchStateCallback = new BtSwitchStateCallback() { // from class: izy.2
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
            public void onBtSwitchStateCallback(int i2) {
                LogUtil.c("BTSDKApi", "Receive BT Switch state: ", Integer.valueOf(i2));
                ArrayList<DeviceInfo> c2 = izm.c("reconnectDevices");
                if (i2 == 1) {
                    if (c2 != null && c2.size() > 0) {
                        Iterator<DeviceInfo> it = c2.iterator();
                        while (it.hasNext()) {
                            DeviceInfo next = it.next();
                            if (next.getDeviceBluetoothType() == 2) {
                                LogUtil.c("BTSDKApi", "BT Switch off with identify: ", iyl.d().e(next.getDeviceIdentify()));
                                izi iziVar = (izi) izy.f.get(next.getDeviceIdentify());
                                if (iziVar != null) {
                                    LogUtil.c("BTSDKApi", "Start to tell bt service switch info");
                                    iziVar.e(1);
                                }
                            }
                        }
                    }
                    izy.this.t();
                    return;
                }
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            LogUtil.c("BTSDKApi", "mBtSwitchStateCallback default");
                        }
                    } else if (c2 != null) {
                        Iterator<DeviceInfo> it2 = c2.iterator();
                        while (it2.hasNext()) {
                            DeviceInfo next2 = it2.next();
                            LogUtil.c("BTSDKApi", "Receive BT Switch deviceInfo :", Integer.valueOf(next2.getDeviceBluetoothType()), ";active state:", Integer.valueOf(next2.getDeviceActiveState()));
                            if (next2.getDeviceBluetoothType() == 2 || !izy.this.n.e(next2)) {
                                if (next2.getDeviceActiveState() == 1) {
                                    LogUtil.c("BTSDKApi", "BT switch on so start to force connect BLE or BR device.");
                                    izy.this.a(next2.getDeviceIdentify(), false);
                                }
                            }
                        }
                    }
                }
            }
        };
        this.s = btSwitchStateCallback;
        if (context == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "init BTSDKApi with context is null.");
            return;
        }
        g = context;
        LogUtil.c("BTSDKApi", "init BluetoothProfile.");
        iyl d2 = iyl.d();
        this.n = d2;
        d2.c(context);
        this.n.e(btDeviceHfpStateCallback);
        this.n.c(btSwitchStateCallback);
        izm.a();
        e = new b(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothDeviceNode bluetoothDeviceNode, int i2) {
        bluetoothDeviceNode.setRssi(i2);
        bluetoothDeviceNode.setTimeStamp(System.currentTimeMillis());
        if (this.q == 2 && bluetoothDeviceNode.getDeviceType() == 3) {
            LogUtil.a("BTSDKApi", "Dual Mode device no add.");
        } else {
            c(bluetoothDeviceNode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bEc_(BluetoothDevice bluetoothDevice, String str, DeviceInfo deviceInfo) {
        int deviceBluetoothType = deviceInfo.getDeviceBluetoothType();
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str)) {
            String manufacture = deviceInfo.getManufacture();
            LogUtil.c("BTSDKApi", "getManufacture: ", manufacture);
            if (manufacture != null && manufacture.contains("010303") && deviceBluetoothType == 1) {
                LogUtil.c("BTSDKApi", "The connected hfp action: ", str);
                bDX_(1, bluetoothDevice, null, deviceInfo.getDeviceName(), false);
                return;
            }
            return;
        }
        if (deviceBluetoothType == 1) {
            LogUtil.c("BTSDKApi", "The connected hfp is the wanted device, so start to connect spp.");
            bDX_(1, bluetoothDevice, null, deviceInfo.getDeviceName(), false);
        } else {
            if (deviceBluetoothType == 2) {
                LogUtil.c("BTSDKApi", "The connected hfp is the wanted device, so start to connect gatt.");
                j.get(deviceInfo.getDeviceIdentify()).a(bluetoothDevice.getAddress());
                bDX_(2, bluetoothDevice, null, deviceInfo.getDeviceName(), false);
                return;
            }
            LogUtil.a("BTSDKApi", "The connected hfp is the wanted device,no support this type :", Integer.valueOf(deviceBluetoothType));
        }
    }

    public void d(String str, int i2) {
        if (str == null) {
            LogUtil.a("BTSDKApi", "notifyDeviceDoublePhone id is null");
            return;
        }
        synchronized (c) {
            izi iziVar = f.get(str);
            if (iziVar != null) {
                iziVar.c(i2);
            }
        }
    }

    public static izy b(Context context) {
        izy izyVar;
        synchronized (f13697a) {
            if (i == null) {
                LogUtil.a("BTSDKApi", "sBtSdkApiInstance is null.");
                i = new izy(BaseApplication.e());
            }
            izyVar = i;
        }
        return izyVar;
    }

    public void f() {
        this.n.f();
        this.l.clear();
        r();
    }

    private static void r() {
        synchronized (f13697a) {
            i = null;
        }
    }

    private izi bDZ_(int i2, String str, String str2, BluetoothDevice bluetoothDevice) {
        LogUtil.c("BTSDKApi", "getBTDeviceInstance() Type :", Integer.valueOf(i2), ", nodeId :", str2);
        if (i2 != 0 && i2 != 5 && bluetoothDevice == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "getBtDeviceInstance btDevice is null.");
            return null;
        }
        String bEb_ = bEb_(i2, str2, bluetoothDevice);
        Map<String, izi> map = f;
        if (map == null) {
            return null;
        }
        if (!map.containsKey(bEb_)) {
            return bDY_(i2, str, str2, bluetoothDevice, bEb_);
        }
        LogUtil.c("BTSDKApi", "has this device.");
        izi iziVar = f.get(bEb_);
        if (i2 == -1 || i2 == iziVar.h()) {
            return iziVar;
        }
        f.remove(bEb_);
        return bDY_(i2, str, str2, bluetoothDevice, bEb_);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private defpackage.izi bDY_(int r5, java.lang.String r6, java.lang.String r7, android.bluetooth.BluetoothDevice r8, java.lang.String r9) {
        /*
            r4 = this;
            java.lang.String r0 = "createDeviceSendCommandUtil mDeviceMgrMap do not contain this device."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "BTSDKApi"
            health.compact.a.LogUtil.c(r1, r0)
            if (r5 == 0) goto L37
            r0 = 5
            if (r5 == r0) goto L37
            java.lang.String r0 = r8.getName()     // Catch: java.lang.SecurityException -> L7d
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.SecurityException -> L7d
            if (r0 == 0) goto L37
            iyl r0 = r4.n     // Catch: java.lang.SecurityException -> L7d
            java.util.List<java.lang.String> r1 = r4.v     // Catch: java.lang.SecurityException -> L7d
            int r0 = r0.b(r1)     // Catch: java.lang.SecurityException -> L7d
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.SecurityException -> L7d
            java.lang.String r2 = "createDeviceSendCommandUtil productType :"
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.SecurityException -> L7d
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.SecurityException -> L7d
            r3 = 1
            r1[r3] = r2     // Catch: java.lang.SecurityException -> L7d
            java.lang.String r2 = "BTSDKApi"
            health.compact.a.LogUtil.c(r2, r1)     // Catch: java.lang.SecurityException -> L7d
            goto L38
        L37:
            r0 = -1
        L38:
            android.bluetooth.BluetoothDevice r8 = r4.bEa_(r5, r9, r8)     // Catch: java.lang.SecurityException -> L7d
            com.huawei.health.devicemgr.business.entity.DeviceInfo r1 = new com.huawei.health.devicemgr.business.entity.DeviceInfo     // Catch: java.lang.SecurityException -> L7d
            r1.<init>()     // Catch: java.lang.SecurityException -> L7d
            r1.setDeviceIdentify(r9)     // Catch: java.lang.SecurityException -> L7d
            int r2 = r4.ad     // Catch: java.lang.SecurityException -> L7d
            r1.setProductType(r2)     // Catch: java.lang.SecurityException -> L7d
            izn r2 = defpackage.izn.c()     // Catch: java.lang.SecurityException -> L7d
            r2.d(r1)     // Catch: java.lang.SecurityException -> L7d
            izk r1 = new izk     // Catch: java.lang.SecurityException -> L7d
            r1.<init>()     // Catch: java.lang.SecurityException -> L7d
            r1.bDB_(r8)     // Catch: java.lang.SecurityException -> L7d
            r1.c(r5)     // Catch: java.lang.SecurityException -> L7d
            r1.d(r6)     // Catch: java.lang.SecurityException -> L7d
            r1.b(r7)     // Catch: java.lang.SecurityException -> L7d
            com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback r5 = r4.p     // Catch: java.lang.SecurityException -> L7d
            r1.d(r5)     // Catch: java.lang.SecurityException -> L7d
            r1.b(r0)     // Catch: java.lang.SecurityException -> L7d
            izi r5 = new izi     // Catch: java.lang.SecurityException -> L7d
            android.content.Context r6 = defpackage.izy.g     // Catch: java.lang.SecurityException -> L7d
            r5.<init>(r6, r1)     // Catch: java.lang.SecurityException -> L7d
            java.lang.Object r6 = defpackage.izy.c     // Catch: java.lang.SecurityException -> L7d
            monitor-enter(r6)     // Catch: java.lang.SecurityException -> L7d
            java.util.Map<java.lang.String, izi> r7 = defpackage.izy.f     // Catch: java.lang.Throwable -> L7a
            r7.put(r9, r5)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L7a
            return r5
        L7a:
            r5 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L7a
            throw r5     // Catch: java.lang.SecurityException -> L7d
        L7d:
            r5 = move-exception
            java.lang.String r6 = "createDeviceSendCommandUtil SecurityException:"
            java.lang.String r5 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            java.lang.String r6 = "BTSDKApi"
            health.compact.a.LogUtil.e(r6, r5)
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.izy.bDY_(int, java.lang.String, java.lang.String, android.bluetooth.BluetoothDevice, java.lang.String):izi");
    }

    private String bEb_(int i2, String str, BluetoothDevice bluetoothDevice) {
        return i2 == 5 ? str : i2 == 0 ? "AndroidWear" : bluetoothDevice.getAddress();
    }

    private BluetoothDevice bEa_(int i2, String str, BluetoothDevice bluetoothDevice) {
        return (i2 == 0 || i2 == 5) ? bluetoothDevice : this.n.bDg_(str);
    }

    private void s(String str) {
        LogUtil.c("BTSDKApi", "Enter removeBTDeviceInstance().");
        String a2 = iza.a(str);
        if (!f.containsKey(a2)) {
            LogUtil.c("BTSDKApi", "removeBTDeviceInstance Do not contain delete device : ", iyl.d().e(a2));
            if (a2.equalsIgnoreCase("AndroidWear") || a2.endsWith("smart_watch") || iyl.d().c(a2)) {
                return;
            }
            LogUtil.a("BTSDKApi", "removeBTDeviceInstance Remove bond device fail.");
            return;
        }
        synchronized (d) {
            DeviceInfo e2 = izm.e("reconnectDevices", a2);
            if (e2 != null) {
                izm.d("reconnectDevices", e2);
                LogUtil.c("BTSDKApi", "The wanted remove device is current device so clear reconnect device info.", "Set reconnect device identify is empty.");
                izl izlVar = j.get(a2);
                if (izlVar != null) {
                    izlVar.e("");
                } else {
                    LogUtil.c("BTSDKApi", "the ble reconnect manager not in map");
                }
            }
        }
        d(f.get(a2), a2);
    }

    private void d(izi iziVar, String str) {
        if (iziVar != null) {
            LogUtil.c("BTSDKApi", "Find wanted remove device success.");
            iziVar.e(true);
            iziVar.c(false);
            izl izlVar = j.get(str);
            if (izlVar == null) {
                LogUtil.a("BTSDKApi", "bleReconnectManager is null");
                return;
            }
            String e2 = izlVar.e();
            LogUtil.c("BTSDKApi", "Stop reconnect Device Identify: ", iyl.d().e(e2));
            if (e2.equalsIgnoreCase(str)) {
                LogUtil.c("BTSDKApi", "disconnectDevice Stop reconnect ble for remove device.");
                izlVar.a(str);
            }
            int j2 = iziVar.j();
            if (j2 != 2 && j2 != 1) {
                synchronized (c) {
                    f.remove(iza.a(str));
                    LogUtil.c("BTSDKApi", "Device remove success and device list size :", Integer.valueOf(f.size()));
                }
                if (!str.equalsIgnoreCase("AndroidWear") && !str.endsWith("smart_watch")) {
                    LogUtil.c("BTSDKApi", "Remove bond device isUnPairSuccess :", Boolean.valueOf(iyl.d().c(str)));
                    return;
                } else {
                    LogUtil.c("BTSDKApi", "AW device should disconnect GMS.");
                    iziVar.b();
                    return;
                }
            }
            iziVar.e();
        }
    }

    public void h(String str) {
        LogUtil.c("BTSDKApi", "Enter removeCurrentInstance().");
        if (str == null) {
            LogUtil.a("BTSDKApi", "removeCurrentInstance strIdentify is null");
            return;
        }
        this.l.put(str, str);
        izi iziVar = f.get(iza.a(str));
        if (iziVar != null) {
            iziVar.e();
        }
        iyl.d().b(str);
    }

    public int h() {
        LogUtil.c("BTSDKApi", "Enter getBTSwitchState().");
        return this.n.g();
    }

    private void x() {
        if (!v() && (this.q == 2 || w())) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
            intent.setFlags(268435456);
            intent.putExtra(TemplateStyleRecord.STYLE, 1);
            intent.putExtra("content", 1);
            try {
                g.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.c("showBtSwitchEnableTip startJumpActivity not find JumpActivity", new Object[0]);
            }
        }
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
        intent2.setFlags(268435456);
        intent2.putExtra(TemplateStyleRecord.STYLE, 1);
        intent2.putExtra("content", 3);
        try {
            g.startActivity(intent2);
        } catch (ActivityNotFoundException unused2) {
            LogUtil.c("showBtTip ActivityNotFoundException", new Object[0]);
        }
    }

    public void bEd_(boolean z, Handler handler) {
        LogUtil.c("BTSDKApi", "BT_GPS enableBTSwitch enable is ", Boolean.valueOf(z));
        if (z) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = handler;
            e.sendMessage(obtain);
            if (this.q != 0) {
                LogUtil.c("BTSDKApi", "BT_GPS startActivity BTDialogActivity");
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
                intent.setFlags(268435456);
                intent.putExtra(TemplateStyleRecord.STYLE, 3);
                intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.q);
                try {
                    g.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.c("enableBTSwitch startJumpActivity not find JumpActivity", new Object[0]);
                    return;
                }
            }
            return;
        }
        e.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, int i2, OperationDeviceInfo operationDeviceInfo) {
        BtDeviceStateCallback btDeviceStateCallback = this.r;
        if (btDeviceStateCallback != null) {
            btDeviceStateCallback.onDeviceConnectionStateChanged(deviceInfo, i2, operationDeviceInfo);
            if ((i2 == 3 || i2 == 4) && f.containsKey(iza.a(deviceInfo.getDeviceIdentify())) && c(deviceInfo)) {
                return;
            }
            d(deviceInfo, i2);
            if (i2 == 2) {
                LogUtil.c("BTSDKApi", "Device connected so stop reconnect.");
                izl izlVar = j.get(deviceInfo.getDeviceIdentify());
                if (izlVar != null) {
                    LogUtil.c("BTSDKApi", "handleDeviceConnectStatusChanged Stop reconnect ble for remove device.");
                    izlVar.a(deviceInfo.getDeviceIdentify());
                }
            }
        }
    }

    private boolean c(DeviceInfo deviceInfo) {
        if (!f.get(iza.a(deviceInfo.getDeviceIdentify())).n()) {
            return false;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        izl izlVar = j.get(deviceInfo.getDeviceIdentify());
        if (izlVar != null && izlVar.e().equalsIgnoreCase(deviceIdentify)) {
            LogUtil.c("BTSDKApi", "Stop reconnect ble for remove device.");
            izlVar.a(deviceInfo.getDeviceIdentify());
        }
        LogUtil.c("BTSDKApi", "Need to remove device from device list.");
        synchronized (c) {
            f.remove(iza.a(deviceInfo.getDeviceIdentify()));
            if (!deviceIdentify.equalsIgnoreCase("AndroidWear") && !deviceIdentify.endsWith("smart_watch")) {
                boolean c2 = iyl.d().c(deviceIdentify);
                LogUtil.a("BTSDKApi", "unPairResult :", Boolean.valueOf(c2));
                if (c2) {
                    return true;
                }
                b bVar = e;
                if (bVar != null) {
                    bVar.sendEmptyMessage(7);
                }
            } else {
                LogUtil.c("BTSDKApi", "AW device do not need remove bond info.");
            }
            return true;
        }
    }

    private void d(DeviceInfo deviceInfo, int i2) {
        synchronized (d) {
            DeviceInfo e2 = izm.e("reconnectDevices", deviceInfo.getDeviceIdentify());
            if (e2 != null) {
                String deviceIdentify = e2.getDeviceIdentify();
                LogUtil.c("BTSDKApi", "Device BTType :", Integer.valueOf(e2.getDeviceBluetoothType()), "reconnectDeviceInfo macAddress: ", iyl.d().e(deviceIdentify), "Device ProductType: ", Integer.valueOf(deviceInfo.getProductType()));
                if (e2.getDeviceBluetoothType() == 2 && (i2 == 3 || i2 == 4)) {
                    izi bDZ_ = bDZ_(2, deviceInfo.getDeviceName(), null, this.n.bDg_(e2.getDeviceIdentify()));
                    if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceIdentify)) {
                        LogUtil.a("BTSDKApi", "Start to reconnect BLE device. Device connected so stop reconnect.");
                        e(j.get(deviceInfo.getDeviceIdentify()), bDZ_, e2);
                    }
                }
            }
        }
    }

    private void e(final izl izlVar, izi iziVar, final DeviceInfo deviceInfo) {
        if (izlVar == null) {
            LogUtil.a("BTSDKApi", "stopReconnectCausedConnected bleReconnectManager is null");
            return;
        }
        LogUtil.c("BTSDKApi", "Stop reconnect ble for remove device.");
        izlVar.b(iziVar);
        if (iziVar == null) {
            LogUtil.a("BTSDKApi", "deviceSendCommandUtil is null.");
            return;
        }
        boolean c2 = iziVar.c();
        LogUtil.c("BTSDKApi", "isReconnectEnableFlag info :", Boolean.valueOf(c2));
        if (!c2) {
            izlVar.a(deviceInfo.getDeviceIdentify());
            return;
        }
        if (System.currentTimeMillis() - iyu.a() > 300000) {
            if (deviceInfo.getDeviceActiveState() == 1) {
                izlVar.d(deviceInfo);
                return;
            } else {
                LogUtil.c("BTSDKApi", "stopReconnectCausedConnected reconnectDeviceInfo is not active RETRY_CONNECT_LONG_DELAY");
                return;
            }
        }
        e.postDelayed(new Runnable() { // from class: izy.9
            @Override // java.lang.Runnable
            public void run() {
                if (deviceInfo.getDeviceActiveState() == 1) {
                    LogUtil.c("BTSDKApi", "stopReconnectCausedConnected reconnectDeviceInfo five minutes later");
                    izlVar.d(deviceInfo);
                } else {
                    LogUtil.a("BTSDKApi", "stopReconnectCausedConnected reconnectDeviceInfo is not active else");
                }
            }
        }, 300000L);
        LogUtil.c("BTSDKApi", "occur ble 133 error in five minutes");
    }

    private void c(BluetoothDeviceNode bluetoothDeviceNode) {
        int i2;
        BluetoothDialogCallback bluetoothDialogCallback;
        synchronized (this) {
            String b2 = b(bluetoothDeviceNode);
            if (bky.e() && TextUtils.isEmpty(b2)) {
                return;
            }
            if (!e(bluetoothDeviceNode, b2) && m(b2)) {
                try {
                    i2 = k(b2);
                } catch (SecurityException e2) {
                    LogUtil.e("BTSDKApi", "addScanDeviceToList SecurityException:", ExceptionUtils.d(e2));
                    i2 = 0;
                }
                synchronized (b) {
                    this.k.add(i2, bluetoothDeviceNode);
                    if (Thread.currentThread() == Looper.getMainLooper().getThread() && (bluetoothDialogCallback = this.ac) != null) {
                        bluetoothDialogCallback.onSetList(this.k, true, i2);
                        return;
                    }
                    u();
                }
            }
        }
    }

    private String b(BluetoothDeviceNode bluetoothDeviceNode) {
        SecurityException e2;
        String str;
        BluetoothDevice btDevice;
        String str2 = "";
        try {
            btDevice = bluetoothDeviceNode.getBtDevice();
            if (!TextUtils.isEmpty(bluetoothDeviceNode.getNodeId())) {
                str = bluetoothDeviceNode.getDisplayName();
            } else {
                String name = btDevice != null ? btDevice.getName() : "";
                try {
                    str = TextUtils.isEmpty(name) ? bluetoothDeviceNode.getRecordName() : name;
                } catch (SecurityException e3) {
                    e2 = e3;
                    str = name;
                    LogUtil.e("BTSDKApi", "getScanDeviceName SecurityException:", ExceptionUtils.d(e2));
                    return str;
                }
            }
        } catch (SecurityException e4) {
            e2 = e4;
            str = "";
        }
        try {
            Object[] objArr = new Object[4];
            objArr[0] = "getScanDeviceName deviceName :";
            objArr[1] = str;
            objArr[2] = "，address :";
            if (btDevice != null) {
                str2 = blt.b(btDevice.getAddress());
            }
            objArr[3] = str2;
            LogUtil.c("BTSDKApi", objArr);
        } catch (SecurityException e5) {
            e2 = e5;
            LogUtil.e("BTSDKApi", "getScanDeviceName SecurityException:", ExceptionUtils.d(e2));
            return str;
        }
        return str;
    }

    private boolean e(BluetoothDeviceNode bluetoothDeviceNode, String str) {
        boolean z;
        BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
        Iterator<BluetoothDeviceNode> it = this.k.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            BluetoothDeviceNode next = it.next();
            if (next != null && next.getBtDevice() != null && next.getBtDevice().getAddress() != null && btDevice != null && next.getBtDevice().getAddress().equals(btDevice.getAddress())) {
                d(next, str);
                z = true;
                break;
            }
        }
        LogUtil.c("BTSDKApi", "addScanDeviceToList flag is ", Boolean.valueOf(z));
        return z;
    }

    private void d(BluetoothDeviceNode bluetoothDeviceNode, String str) {
        LogUtil.c("BTSDKApi", "addDeviceToList, updateDeviceList");
        try {
            if (bluetoothDeviceNode.getBtDevice().getName() == null && TextUtils.isEmpty(bluetoothDeviceNode.getRecordName()) && str != null && bky.d()) {
                if (!l(str)) {
                    LogUtil.a("BTSDKApi", "updateDeviceList device name is error.");
                } else {
                    bluetoothDeviceNode.setRecordName(str);
                    u();
                }
            }
        } catch (SecurityException e2) {
            LogUtil.e("BTSDKApi", "updateDeviceList SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private int k(String str) {
        synchronized (b) {
            int size = this.k.size();
            if (TextUtils.isEmpty(str)) {
                return size;
            }
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 < size) {
                    BluetoothDevice btDevice = this.k.get(i2).getBtDevice();
                    String name = btDevice != null ? btDevice.getName() : "";
                    if (TextUtils.isEmpty(name)) {
                        break;
                    }
                    if (!l(name) && l(str)) {
                        break;
                    }
                    i3 = i2 + 1;
                    i2 = i3;
                } else {
                    i2 = i3;
                    break;
                }
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (z) {
            if (!v() && (this.q == 2 || w())) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
                intent.setFlags(268435456);
                intent.putExtra(TemplateStyleRecord.STYLE, 1);
                intent.putExtra("content", 2);
                try {
                    g.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    LogUtil.c("startJumpActivity not find JumpActivity", new Object[0]);
                }
            }
            LogUtil.c("BTSDKApi", "BT_GPS showDiscoverDeviceTip1");
            a(true);
            return;
        }
        if (!v() && this.q == 2) {
            LogUtil.c("BTSDKApi", "gps not enable");
        } else {
            LogUtil.c("BTSDKApi", "BT_GPS showDiscoverDeviceTip2");
            a(true);
        }
    }

    public void n() {
        if (h() != 3) {
            LogUtil.a("BTSDKApi", "startScanDevices bluetooth switch is not on.");
            return;
        }
        LogUtil.c("BTSDKApi", "scanbegin type:", Integer.valueOf(this.q));
        Object obj = b;
        synchronized (obj) {
            List<BluetoothDeviceNode> list = this.k;
            if (list != null) {
                list.clear();
                BluetoothDialogCallback bluetoothDialogCallback = this.ac;
                if (bluetoothDialogCallback != null) {
                    bluetoothDialogCallback.onSetList(this.k, false, 0);
                }
            }
        }
        this.n.e(this.v, this.q, this.x);
        synchronized (obj) {
            BluetoothDialogCallback bluetoothDialogCallback2 = this.ac;
            if (bluetoothDialogCallback2 != null) {
                bluetoothDialogCallback2.onSetNameFilter(this.v);
            }
        }
    }

    public void a(boolean z) {
        LogUtil.c("BTSDKApi", "BT_GPS showDeviceList isBrTip: ", Boolean.valueOf(z));
        if (this.q == 1 && z && this.n.d(this.v)) {
            LogUtil.c("BTSDKApi", "Has wanted BR device.");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
            intent.setFlags(268435456);
            intent.putExtra(TemplateStyleRecord.STYLE, 1);
            intent.putExtra("content", 4);
            try {
                g.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.c("startJumpActivity not find JumpActivity", new Object[0]);
            }
        }
        LogUtil.c("BTSDKApi", "Clear device scan list.");
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
        intent2.setFlags(268435456);
        intent2.putExtra(TemplateStyleRecord.STYLE, 3);
        intent2.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.q);
        try {
            g.startActivity(intent2);
        } catch (ActivityNotFoundException unused2) {
            LogUtil.e("BTSDKApi", "start BtDialogActivity ActivityNotFoundException.");
        } catch (SecurityException unused3) {
            LogUtil.e("BTSDKApi", "start BtDialogActivity SecurityException.");
        }
    }

    public void o() {
        LogUtil.c("BTSDKApi", "Enter showPermissionDialog");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.ui.device.activity.pairing.BtDialogActivity"));
        intent.setFlags(268435456);
        intent.putExtra(TemplateStyleRecord.STYLE, 1);
        intent.putExtra("content", 6);
        try {
            g.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("BTSDKApi", "start showPermissionDialog ActivityNotFoundException.");
        } catch (SecurityException unused2) {
            LogUtil.e("BTSDKApi", "start showPermissionDialog SecurityException.");
        }
    }

    public void e(long j2, int i2) {
        if (j2 < 0) {
            LogUtil.a("BTSDKApi", "connectSelectedDevice id is error");
            return;
        }
        if (j2 < this.k.size()) {
            BluetoothDevice btDevice = this.k.get((int) j2).getBtDevice();
            if (btDevice != null) {
                try {
                    LogUtil.c("BTSDKApi", "connectSelectedDevice id: ", Long.valueOf(j2), ";name is: ", btDevice.getName());
                    a(i2);
                    a(j2);
                    return;
                } catch (SecurityException e2) {
                    LogUtil.e("BTSDKApi", "connectSelectedDevice SecurityException:", ExceptionUtils.d(e2));
                    return;
                }
            }
            LogUtil.a("BTSDKApi", "connectSelectedDevice deal else");
            return;
        }
        LogUtil.e("BTSDKApi", "Please read the pairing guide and try again.");
        snu.e().notification(1);
    }

    private void a(int i2) {
        if (i2 == -1 || i2 == this.q) {
            return;
        }
        this.q = i2;
    }

    public void a(long j2) {
        int i2 = (int) j2;
        if (i2 < 0 || i2 >= this.k.size()) {
            LogUtil.a("BTSDKApi", "connectDevice id is error");
            return;
        }
        BluetoothDevice btDevice = this.k.get(i2).getBtDevice();
        c();
        bDX_(this.q, btDevice, this.k.get(i2).getNodeId(), this.k.get(i2).getRecordName(), true);
    }

    public void d(DeviceParameter deviceParameter, List<String> list, IAddDeviceStateCallback iAddDeviceStateCallback) {
        if (deviceParameter == null) {
            LogUtil.a("BTSDKApi", "addDevice deviceParameter is null");
            return;
        }
        ArrayList<String> nameFilter = deviceParameter.getNameFilter();
        if (nameFilter == null || iAddDeviceStateCallback == null) {
            LogUtil.a("BTSDKApi", "nameFilter or deviceListStateCallback is null");
            return;
        }
        b(deviceParameter, nameFilter, iAddDeviceStateCallback);
        String mac = deviceParameter.getMac();
        boolean isBand = deviceParameter.isBand();
        LogUtil.c("BTSDKApi", "addDevice isBand: ", Boolean.valueOf(isBand));
        if (BuildConfigProperties.e("IS_SUPPORT_NEW_ADD_MODE", false)) {
            this.y.clear();
            this.y.addAll(nameFilter);
            iyg.a(this.ad, this.y, isBand);
        }
        if (this.u) {
            this.z = list;
            LogUtil.c("BTSDKApi", "mIsOnlyShowHeartRate is true");
        }
        if (!TextUtils.isEmpty(mac)) {
            if (mac.endsWith("smart_watch")) {
                bDX_(this.q, null, iza.a(mac), null, true);
                return;
            } else {
                bDX_(this.q, iyl.d().bDg_(mac), null, null, true);
                return;
            }
        }
        int h = h();
        if (h == 1) {
            x();
            return;
        }
        if (h == 3) {
            int i2 = this.q;
            if (i2 == 0) {
                bDX_(i2, null, null, null, true);
                return;
            } else {
                c(true);
                return;
            }
        }
        LogUtil.a("BTSDKApi", "other switch status");
    }

    private void b(DeviceParameter deviceParameter, List<String> list, IAddDeviceStateCallback iAddDeviceStateCallback) {
        this.q = deviceParameter.getBluetoothType();
        this.h = deviceParameter.getDeviceNameInfo();
        this.v.clear();
        this.v.addAll(list);
        this.ad = deviceParameter.getProductType();
        this.o = iAddDeviceStateCallback;
        this.u = deviceParameter.isSupportHeartRate();
    }

    public void a(List<DeviceInfo> list) {
        LogUtil.c("BTSDKApi", "Enter removeDeviceList().");
        if (list == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "Parameter is incorrect.");
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            if (!"".equals(iyq.b(BaseApplication.e()).b(deviceInfo.getDeviceIdentify(), BaseApplication.e()))) {
                iyq.b(BaseApplication.e()).d(deviceInfo.getDeviceIdentify(), blq.b(""), "btsdk_sharedpreferences_bindid", BaseApplication.e());
            }
            s(deviceInfo.getDeviceIdentify());
        }
    }

    private static Map<String, izi> p() {
        LogUtil.c("BTSDKApi", "Enter geActiveDeviceList().");
        HashMap hashMap = new HashMap(16);
        for (Map.Entry<String, izi> entry : f.entrySet()) {
            String key = entry.getKey();
            izi value = entry.getValue();
            if (value != null && value.f() == 1) {
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    private static List<String> s() {
        LogUtil.c("BTSDKApi", "Enter getAllActiveDeviceInfos().");
        ArrayList arrayList = new ArrayList(16);
        Map<String, izi> p = p();
        if (p != null && p.size() > 0) {
            for (Map.Entry<String, izi> entry : p.entrySet()) {
                LogUtil.c("BTSDKApi", "getAllActiveDeviceInfos Active device identify: ", iyl.d().e(entry.getKey()));
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("BTSDKApi", "setCurrentDevice deviceInfo is null");
        } else {
            e(deviceInfo, false);
        }
    }

    public void e(DeviceInfo deviceInfo, boolean z) {
        LogUtil.c("BTSDKApi", "Enter setCurrentDevice() isManual: ", Boolean.valueOf(z));
        izm.e("currentConnectedDevices", deviceInfo);
        izm.e("reconnectDevices", deviceInfo);
        if (deviceInfo == null) {
            LogUtil.a("BTSDKApi", "deviceInfo is null.");
            return;
        }
        this.ad = deviceInfo.getProductType();
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        LogUtil.c("BTSDKApi", "macAddress: ", iyl.d().e(deviceIdentify));
        if (-2 == deviceInfo.getProductType()) {
            LogUtil.a("BTSDKApi", "This func do not support AF500 device.");
        } else {
            d(deviceInfo, z, deviceIdentify);
        }
    }

    private void d(DeviceInfo deviceInfo, boolean z, String str) {
        BluetoothDevice bluetoothDevice;
        int deviceBluetoothType = deviceInfo.getDeviceBluetoothType();
        LogUtil.c("BTSDKApi", "device BTType :", Integer.valueOf(deviceBluetoothType), " ,device getProductType: ", Integer.valueOf(deviceInfo.getProductType()));
        List<String> s = s();
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        a(z, deviceBluetoothType, deviceIdentify);
        if (s.contains(deviceIdentify)) {
            if (deviceBluetoothType != 0 && deviceBluetoothType != 5) {
                a(str, deviceBluetoothType, z, deviceIdentify, deviceInfo.getManufacture());
                return;
            }
            izi iziVar = f.get(iza.a(deviceIdentify));
            if (iziVar == null || iziVar.j() == 2) {
                return;
            }
            LogUtil.c("BTSDKApi", "start to connect aw device.");
            bDX_(deviceBluetoothType, null, deviceInfo.getNodeId(), deviceInfo.getDeviceName(), false);
            return;
        }
        LogUtil.c("BTSDKApi", "The Current device has not active.");
        if (deviceBluetoothType == 0 || deviceBluetoothType == 5) {
            bluetoothDevice = null;
        } else {
            bluetoothDevice = this.n.bDg_(deviceIdentify);
            if (bluetoothDevice == null) {
                LogUtil.a("BTSDKApi", "0xA0200006", " btDevice is null.");
                return;
            }
        }
        bDX_(deviceBluetoothType, bluetoothDevice, deviceInfo.getNodeId(), deviceInfo.getDeviceName(), false);
    }

    private void a(String str, int i2, boolean z, String str2, String str3) {
        LogUtil.c("BTSDKApi", "The Current device has already active.");
        iyl iylVar = this.n;
        if (iylVar != null) {
            BluetoothDevice bDg_ = iylVar.bDg_(str);
            if (bDg_ != null) {
                bDW_(i2, z, str2, bDg_, str3);
            } else {
                LogUtil.a("BTSDKApi", "handleContainsInActiveDevices btDevice is null.");
            }
        }
    }

    private void bDW_(int i2, boolean z, String str, BluetoothDevice bluetoothDevice, String str2) {
        izi iziVar = f.get(str);
        if (i2 == 1 && this.n.bDj_(bluetoothDevice) && iziVar != null && iziVar.j() != 2) {
            bDX_(i2, bluetoothDevice, null, null, false);
            return;
        }
        if (i2 == 1 && z) {
            LogUtil.c("BTSDKApi", "reconnect Need to connect hfp profile.");
            if (str2 != null && str2.contains("010303")) {
                bDX_(i2, bluetoothDevice, null, null, false);
                return;
            } else {
                this.n.bDf_(bluetoothDevice);
                return;
            }
        }
        LogUtil.c("BTSDKApi", "btType is not BR or hfp is not connect.");
        if (i2 == 2) {
            LogUtil.c("BTSDKApi", "Current is ble device so set is disconnect by user flag is false.");
            if (iziVar == null || iziVar.j() == 2) {
                return;
            }
            iziVar.d(true);
            a(str, false);
        }
    }

    public void c(String str) {
        LogUtil.c("BTSDKApi", "Enter disconnectDeviceByMac");
        if (str == null) {
            LogUtil.a("BTSDKApi", "disconnectDeviceByMac macAddress is null");
            return;
        }
        String a2 = iza.a(str);
        izi iziVar = f.get(a2);
        if (iziVar != null) {
            iziVar.d(0);
            LogUtil.c("BTSDKApi", "Start to setBTDeviceActiveState is disable. Set Reconnect device identify is empty");
            izl izlVar = j.get(a2);
            if (izlVar != null) {
                izlVar.e("");
                LogUtil.c("BTSDKApi", "Start to stopReconnectBLE.");
                izlVar.a(a2);
            }
            LogUtil.c("BTSDKApi", "Start to disconnect device.");
            iziVar.e();
        }
    }

    public void c(String str, String str2, String str3, int i2) {
        BluetoothDevice bluetoothDevice;
        LogUtil.c("BTSDKApi", "Enter setActiveDevice(),bluetoothType:", Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTSDKApi", "0xA0200008", " deviceIdentify is null.");
            return;
        }
        int i3 = -1;
        int i4 = str.equalsIgnoreCase("AndroidWear") ? 0 : -1;
        if (str.endsWith("smart_watch")) {
            i4 = 5;
        }
        if (i4 == 0 || i4 == 5) {
            i3 = i4;
            bluetoothDevice = null;
        } else {
            bluetoothDevice = this.n.bDg_(str);
            if (bluetoothDevice == null) {
                LogUtil.a("BTSDKApi", "btDevice is null.");
                return;
            } else if (i2 == 1) {
                i3 = 1;
            } else if (i2 == 2) {
                i3 = 2;
            } else {
                LogUtil.a("BTSDKApi", "The BTType is unknown.");
            }
        }
        izi bDZ_ = bDZ_(i3, str3, str2, bluetoothDevice);
        if (bDZ_ != null) {
            LogUtil.c("BTSDKApi", "Set wanted device active flag enable.");
            bDZ_.d(1);
        }
        LogUtil.c("BTSDKApi", "Set Reconnect device identify: ", iyl.d().e(str));
        izl izlVar = j.get(str);
        if (izlVar != null) {
            izlVar.e(str);
            return;
        }
        LogUtil.c("BTSDKApi", "add new device: ", iyl.d().e(str));
        izl izlVar2 = new izl();
        izlVar2.e(str);
        j.put(str, izlVar2);
    }

    public void c() {
        LogUtil.c("BTSDKApi", "Enter cancelBTDeviceDiscovery().");
        this.n.b();
    }

    public void a(BtDeviceStateCallback btDeviceStateCallback) {
        LogUtil.c("BTSDKApi", "Enter registerBtDeviceStateCallback().");
        if (btDeviceStateCallback == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "btDeviceCallback is null.");
        } else {
            this.r = btDeviceStateCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bDX_(int i2, BluetoothDevice bluetoothDevice, String str, String str2, boolean z) {
        final String address;
        LogUtil.c("BTSDKApi", "Enter connectBTDevice().");
        if (i2 != 0 && i2 != 5) {
            if (bluetoothDevice == null) {
                LogUtil.a("BTSDKApi", "0xA0200008", " parameter is not correct.");
                return;
            }
            this.l.put(bluetoothDevice.getAddress(), "");
        }
        if (i2 == 5) {
            address = str + "smart_watch";
        } else if (i2 == 0) {
            address = "AndroidWear";
        } else {
            address = bluetoothDevice != null ? bluetoothDevice.getAddress() : null;
        }
        if (bky.a()) {
            ThreadPoolManager.d().d("oldCancelBtConfirmDialog", new Runnable() { // from class: izu
                @Override // java.lang.Runnable
                public final void run() {
                    izy.b(address);
                }
            });
        }
        List<String> s = s();
        izi bDZ_ = bDZ_(i2, str2, str, bluetoothDevice);
        boolean d2 = bDZ_ != null ? bDZ_.d() : false;
        if (s.contains(address)) {
            LogUtil.c("BTSDKApi", "hasAddedWantedDevice: ", Boolean.valueOf(d2), "preMacAddress equal strMacAddress.");
            b(bDZ_, address, i2);
            return;
        }
        LogUtil.c("BTSDKApi", "hasAddedWantedDevice: ", Boolean.valueOf(d2), "preMacAddress do not equal strMacAddress.");
        if (!z || d2) {
            LogUtil.c("BTSDKApi", "set current device active state is true.");
            c(address, str, str2, i2);
        }
        c(bDZ_);
    }

    static /* synthetic */ void b(String str) {
        if (bks.d(str)) {
            return;
        }
        bks.e(str);
    }

    private void c(izi iziVar) {
        if (iziVar != null) {
            int j2 = iziVar.j();
            LogUtil.c("BTSDKApi", "device connect state: ", Integer.valueOf(j2));
            if (j2 != 2 && j2 != 1) {
                if (h() == 3) {
                    LogUtil.c("BTSDKApi", "BT switch is on so start connect device.");
                    iziVar.a();
                } else {
                    LogUtil.c("BTSDKApi", "BT switch is not on. btswitchstate:", Integer.valueOf(h()));
                }
            }
            if (j2 == 2 && this.r != null) {
                LogUtil.c("BTSDKApi", "report device connected status to dms.");
                DeviceInfo g2 = iziVar.g();
                if (g2 == null) {
                    return;
                }
                this.r.onDeviceConnectionStateChanged(g2, 2, izn.c().e(g2.getDeviceIdentify()));
            }
            if (j2 != 1 || this.r == null) {
                return;
            }
            DeviceInfo g3 = iziVar.g();
            if (g3 == null) {
                LogUtil.a("BTSDKApi", "handleUnContaisInActivieDevices device info is null.");
            } else {
                this.r.onDeviceConnectionStateChanged(g3, 1, izn.c().e(g3.getDeviceIdentify()));
            }
        }
    }

    private void b(izi iziVar, String str, int i2) {
        if (iziVar != null) {
            int j2 = iziVar.j();
            LogUtil.c("BTSDKApi", "getBTDeviceConnectState:", Integer.valueOf(j2));
            DeviceInfo e2 = izm.e("reconnectDevices", str);
            if (e2 != null && j2 != 2 && j2 != 1) {
                LogUtil.c("BTSDKApi", "Start to connect wanted device.");
                iziVar.a();
            } else {
                LogUtil.c("BTSDKApi", "Do not need to connect wanted device.");
            }
            DeviceInfo g2 = iziVar.g();
            if (g2 == null) {
                g2 = new DeviceInfo();
            }
            g2.setDeviceIdentify(str);
            if (e2 == null) {
                izm.e("reconnectDevices", g2);
            }
            OperationDeviceInfo e3 = izn.c().e(str);
            if (j2 != 2 || i2 == 0 || this.r == null) {
                return;
            }
            LogUtil.c("BTSDKApi", "Start to report connected state with device type: ", Integer.valueOf(g2.getProductType()));
            g2.setDeviceConnectState(2);
            this.r.onDeviceConnectionStateChanged(g2, 2, e3);
        }
    }

    public void i(String str) {
        LogUtil.c("BTSDKApi", "Enter stopReconnectAndDisconnectDevice");
        if (str == null) {
            LogUtil.a("BTSDKApi", "stopReconnectAndDisconnectDevice preMacAddress is null");
            return;
        }
        String a2 = iza.a(str);
        izi iziVar = f.get(a2);
        if (iziVar != null) {
            iziVar.d(false);
            izl izlVar = j.get(a2);
            if (izlVar != null) {
                izlVar.a(a2);
            }
            iziVar.e();
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTSDKApi", "0xA0200008", " btMacAddress is incorrect.");
            return;
        }
        izi iziVar = f.get(iza.a(str));
        if (iziVar != null) {
            int j2 = iziVar.j();
            if (j2 == 1 || j2 == 2) {
                iziVar.e();
                return;
            } else {
                LogUtil.c("BTSDKApi", "disconnectBTDevice Device is already disconnect.");
                return;
            }
        }
        LogUtil.a("BTSDKApi", "0xA0200008", "disconnectBTDevice can not get device manager handle.");
    }

    public void c(String str, String str2) {
        LogUtil.c("BTSDKApi", "Enter sendAWFilePath().");
        if (str2 == null) {
            return;
        }
        izi q = q();
        if (q == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "sendAWFilePath deviceSendCommandUtil is null.");
        } else {
            LogUtil.c("BTSDKApi", "sendAWFilePath path : ", str2);
            q.d(str2);
        }
    }

    public void c(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("BTSDKApi", "Enter setFileCallback().");
        if (btDeviceResponseCallback == null) {
            return;
        }
        izi q = q();
        if (q == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", " deviceSendCommandUtil is null.");
        } else {
            LogUtil.c("BTSDKApi", "setFileCallback path : ", btDeviceResponseCallback);
            q.d(btDeviceResponseCallback);
        }
    }

    public void a(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("BTSDKApi", "Enter setAWFileCallback().");
        if (btDeviceResponseCallback == null) {
            LogUtil.a("BTSDKApi", "setAWFileCallback() callback is null");
            return;
        }
        izi q = q();
        if (q == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", "setAWFileCallback is null.");
        } else {
            LogUtil.c("BTSDKApi", "setAWFileCallback path : ", btDeviceResponseCallback);
            q.b(btDeviceResponseCallback);
        }
    }

    private izi q() {
        DeviceInfo g2;
        LogUtil.c("BTSDKApi", "Enter getCurrentSmartWatchUtil().");
        Iterator<Map.Entry<String, izi>> it = f.entrySet().iterator();
        while (it.hasNext()) {
            izi value = it.next().getValue();
            if (value != null && value.f() == 1 && (g2 = value.g()) != null && (g2.getDeviceBluetoothType() == 0 || g2.getDeviceBluetoothType() == 5)) {
                return value;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(int r7, defpackage.izf r8) {
        /*
            r6 = this;
            if (r8 != 0) goto L3
            return
        L3:
            java.lang.String r0 = r8.h()
            java.lang.String r1 = "smart_watch"
            boolean r1 = r0.endsWith(r1)
            java.lang.String r2 = "BTSDKApi"
            r3 = 5
            r4 = 0
            if (r1 == 0) goto L1c
            r8.c(r3)
            java.lang.String r0 = defpackage.iza.a(r0)
            goto L28
        L1c:
            java.lang.String r1 = "AndroidWear"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L2a
            r7 = 0
            r8.c(r7)
        L28:
            r1 = r4
            goto L48
        L2a:
            iyl r1 = r6.n
            android.bluetooth.BluetoothDevice r1 = r1.bDg_(r0)
            if (r1 != 0) goto L33
            return
        L33:
            r5 = 1
            if (r7 != r5) goto L37
            goto L45
        L37:
            r5 = 2
            if (r7 != r5) goto L3b
            goto L45
        L3b:
            java.lang.String r7 = "The BTType is unknown."
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.LogUtil.a(r2, r7)
            r5 = -1
        L45:
            r8.c(r5)
        L48:
            int r7 = r8.c()
            if (r7 != r3) goto L57
            int r7 = r8.c()
            izi r7 = r6.bDZ_(r7, r4, r0, r1)
            goto L5f
        L57:
            int r7 = r8.c()
            izi r7 = r6.bDZ_(r7, r4, r4, r1)
        L5f:
            if (r7 != 0) goto L6d
            java.lang.String r7 = "0xA0200008"
            java.lang.String r8 = " deviceSendCommandUtil is null."
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8}
            health.compact.a.LogUtil.a(r2, r7)
            return
        L6d:
            r7.a(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.izy.c(int, izf):void");
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
            }
            switch (message.what) {
                case 1:
                    if (izy.this.o != null) {
                        izy.this.o.onAddDeviceState(1);
                        break;
                    }
                    break;
                case 2:
                    Object obj = message.obj;
                    if (obj != null && (obj instanceof Handler)) {
                        izy.this.n.bDe_(izy.this.t, (Handler) message.obj);
                        break;
                    }
                    break;
                case 3:
                    if (izy.this.o != null) {
                        izy.this.o.onAddDeviceState(3);
                        break;
                    }
                    break;
                case 4:
                    izy.this.c(false);
                    break;
                case 5:
                    izy.this.bDX_(0, null, null, null, true);
                    break;
                case 6:
                default:
                    LogUtil.a("BTSDKApi", "AddDeviceHandler msg.what is unknown");
                    break;
                case 7:
                    LogUtil.e("BTSDKApi", "Unable to unpair device here. Unpair it in Bluetooth settings.");
                    snu.e().notification(2);
                    break;
                case 8:
                    LogUtil.e("BTSDKApi", "The device needs to adapt to the 0x15 field in Bluetooth protocol 5.1.7");
                    snu.e().notification(3);
                    break;
            }
        }
    }

    private boolean v() {
        if (!(g.getSystemService("location") instanceof LocationManager)) {
            LogUtil.a("BTSDKApi", "isGpsLocationEnable get Location Service fail");
            return false;
        }
        LocationManager locationManager = (LocationManager) g.getSystemService("location");
        boolean isProviderEnabled = locationManager.isProviderEnabled(GeocodeSearch.GPS);
        LogUtil.c("BTSDKApi", "isGPSLocationEnable：", Boolean.valueOf(isProviderEnabled));
        if (iyg.e() || iyg.b()) {
            return isProviderEnabled;
        }
        boolean isProviderEnabled2 = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        LogUtil.c("BTSDKApi", "isNetWorkLocationEnable：", Boolean.valueOf(isProviderEnabled2));
        return isProviderEnabled || isProviderEnabled2;
    }

    public void a(String str, boolean z) {
        LogUtil.c("BTSDKApi", "Enter forceConnectBTDevice().");
        if (str == null) {
            return;
        }
        DeviceInfo e2 = izm.e("reconnectDevices", str);
        izi iziVar = f.get(iza.a(str));
        if (iziVar == null || e2 == null) {
            return;
        }
        int j2 = iziVar.j();
        int deviceBluetoothType = e2.getDeviceBluetoothType();
        LogUtil.c("BTSDKApi", "Current connect state: ", Integer.valueOf(j2));
        if (j2 != 2) {
            if (deviceBluetoothType != 0 && deviceBluetoothType != 5) {
                d(str, z, e2, iziVar, j2, deviceBluetoothType);
            } else if (j2 != 1) {
                LogUtil.c("BTSDKApi", "Start to connect AW device.");
                iziVar.a();
            }
        }
    }

    private void d(String str, boolean z, DeviceInfo deviceInfo, izi iziVar, int... iArr) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        BluetoothDevice bDg_ = this.n.bDg_(deviceInfo.getDeviceIdentify());
        if (bDg_ != null) {
            boolean c2 = iziVar.c();
            LogUtil.c("BTSDKApi", "reconnectEnableFlag: ", Boolean.valueOf(c2));
            if (i3 == 2 && c2) {
                b(j.get(str), iziVar, z, deviceInfo);
                return;
            }
            if (i3 != 1 || i2 == 1) {
                return;
            }
            if (this.n.bDj_(bDg_) || a(deviceInfo)) {
                LogUtil.c("BTSDKApi", "Start to connect BR device.");
                b(true);
                iziVar.a();
            }
        }
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("BTSDKApi", "isSupportBleAndBr deviceInfo is null");
            return false;
        }
        String manufacture = deviceInfo.getManufacture();
        LogUtil.c("BTSDKApi", "getManufacture: ", manufacture);
        return manufacture != null && manufacture.contains("010303") && deviceInfo.getDeviceBluetoothType() == 1;
    }

    private void b(izl izlVar, izi iziVar, boolean z, DeviceInfo deviceInfo) {
        if (izlVar != null) {
            izlVar.a(deviceInfo.getDeviceIdentify());
            if (izlVar.c() == null) {
                LogUtil.a("BTSDKApi", "btDeviceSendCommandUtil is null so reset it.");
                izlVar.b(iziVar);
            }
            int j2 = iziVar.j();
            if (z) {
                LogUtil.c("BTSDKApi", "iconnect find device so connect device directly.");
                if (j2 == 1 || j2 == 2) {
                    return;
                }
                iziVar.a();
                return;
            }
            if (deviceInfo.getDeviceActiveState() == 1) {
                izlVar.d(deviceInfo);
            } else {
                LogUtil.a("BTSDKApi", "stopReconnectCausedConnected reconnectDeviceInfo is not active");
            }
        }
    }

    public void j() {
        IAddDeviceStateCallback iAddDeviceStateCallback = this.o;
        if (iAddDeviceStateCallback != null) {
            iAddDeviceStateCallback.onAddDeviceState(4);
        }
    }

    public void i() {
        LogUtil.c("BTSDKApi", "Enter connectHFPConnectedDevice().");
        BluetoothDevice bDi_ = this.n.bDi_(this.v);
        if (bDi_ != null) {
            try {
                LogUtil.c("BTSDKApi", "The wanted device name: ", bDi_.getName());
                bDX_(1, bDi_, null, null, true);
            } catch (SecurityException e2) {
                LogUtil.e("BTSDKApi", "connectHFPConnectedDevice SecurityException:", ExceptionUtils.d(e2));
            }
        }
    }

    public String m() {
        LogUtil.c("BTSDKApi", "Enter getHFPConnectedDeviceName().");
        BluetoothDevice bDi_ = this.n.bDi_(this.v);
        if (bDi_ != null) {
            try {
                LogUtil.c("BTSDKApi", "The wanted device name: ", bDi_.getName());
                return bDi_.getName();
            } catch (SecurityException e2) {
                LogUtil.e("BTSDKApi", "getHFPConnectedDeviceName SecurityException:", ExceptionUtils.d(e2));
            }
        }
        return "";
    }

    public String g() {
        return this.h;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9) {
        /*
            r5 = this;
            java.lang.String r0 = "Enter setAddedDeviceSuccess(),bluetoothType:"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "BTSDKApi"
            health.compact.a.LogUtil.c(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto L22
            java.lang.String r6 = "0xA0200008"
            java.lang.String r7 = "strIdentify is null."
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            health.compact.a.LogUtil.a(r1, r6)
            return
        L22:
            java.lang.String r0 = "AndroidWear"
            boolean r0 = r0.equalsIgnoreCase(r7)
            r2 = -1
            if (r0 == 0) goto L2d
            r0 = 0
            goto L2e
        L2d:
            r0 = r2
        L2e:
            java.lang.String r3 = "smart_watch"
            boolean r3 = r7.endsWith(r3)
            r4 = 5
            if (r3 == 0) goto L39
            r0 = r4
        L39:
            r3 = 1
            if (r0 == 0) goto L62
            if (r0 == r4) goto L62
            iyl r0 = r5.n
            android.bluetooth.BluetoothDevice r7 = r0.bDg_(r7)
            if (r7 != 0) goto L50
            java.lang.String r6 = "btDevice is null so return."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.a(r1, r6)
            return
        L50:
            if (r9 != r3) goto L54
            r2 = r3
            goto L64
        L54:
            r0 = 2
            if (r9 != r0) goto L58
            goto L63
        L58:
            java.lang.String r9 = "The btType is unknown."
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.LogUtil.c(r1, r9)
            goto L64
        L62:
            r7 = 0
        L63:
            r2 = r0
        L64:
            izi r6 = r5.bDZ_(r2, r8, r6, r7)
            if (r6 == 0) goto L77
            java.lang.String r7 = "Set wanted device add success flag."
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.LogUtil.c(r1, r7)
            r6.c(r3)
            goto L80
        L77:
            java.lang.String r6 = "deviceSendCommandUtil is null."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.a(r1, r6)
        L80:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.izy.a(java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    public void b(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.c("BTSDKApi", "Enter setDMSHandshakeFail().");
        if (deviceInfo == null) {
            LogUtil.a("BTSDKApi", "0xA0200008", " btDeviceInfo is null.");
            return;
        }
        LogUtil.c("BTSDKApi", "Start to report connect fail.");
        deviceInfo.setDeviceConnectState(4);
        izn c2 = izn.c();
        c2.a(deviceInfo.getDeviceIdentify(), System.currentTimeMillis());
        if (bArr != null && bArr.length >= 2) {
            c2.c(deviceInfo.getDeviceIdentify(), iyg.c(bArr[0], bArr[1]));
        }
        c2.a(deviceInfo.getDeviceIdentify(), 2010000);
        this.p.onDeviceConnectionStateChanged(deviceInfo, 4, c2.e(deviceInfo.getDeviceIdentify()));
    }

    public void e(boolean z, String str) {
        if (str == null) {
            LogUtil.a("BTSDKApi", "setReconnectInfoFlag strIdentify is null");
            return;
        }
        izi iziVar = f.get(str);
        if (iziVar == null) {
            LogUtil.a("BTSDKApi", "setReconnectInfoFlag deviceSendCommandUtil is null");
            return;
        }
        iziVar.d(z);
        if (z) {
            return;
        }
        iziVar.d(0);
    }

    public void f(String str) {
        LogUtil.c("BTSDKApi", "stopReconnectBle enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTSDKApi", "stopReconnectBle macAddress is null");
            return;
        }
        izl izlVar = j.get(str);
        if (izlVar == null) {
            LogUtil.a("BTSDKApi", "stopReconnectBle bleReconnectManager is null");
        } else {
            izlVar.a(str);
        }
    }

    public void j(String str) {
        LogUtil.c("BTSDKApi", "startReconnectBle enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BTSDKApi", "startReconnectBle macAddress is null");
            return;
        }
        izl izlVar = j.get(str);
        if (izlVar == null) {
            LogUtil.a("BTSDKApi", "startReconnectBle bleReconnectManager is null");
            return;
        }
        DeviceInfo e2 = izm.e("reconnectDevices", str);
        if (e2 != null && e2.getDeviceActiveState() == 1) {
            izlVar.d(e2);
        } else {
            LogUtil.a("BTSDKApi", "startReconnectBle reconnectDeviceInfo is not active");
        }
    }

    public void o(String str) {
        if (str == null) {
            LogUtil.a("BTSDKApi", "updateReconnectDeviceInfoActiveStatus identify is null");
        } else {
            izm.b(str);
        }
    }

    public boolean k() {
        return this.w;
    }

    public void b(boolean z) {
        this.w = z;
    }

    public void c(BluetoothDialogCallback bluetoothDialogCallback) {
        synchronized (b) {
            this.ac = bluetoothDialogCallback;
        }
    }

    private boolean m(String str) {
        boolean z = true;
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            List<String> list = this.y;
            if (list == null) {
                return false;
            }
            if (list.size() > 0 && "Blacktip".equalsIgnoreCase(this.y.get(0))) {
                return false;
            }
            LogUtil.a("BTSDKApi", "filterScanDeviceName deviceName is null and return true.");
            return true;
        }
        Iterator<String> it = this.y.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = z2;
                break;
            }
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                z2 = true;
            } else if (bky.e() && !str.contains(Constants.LINK)) {
                LogUtil.d("BTSDKApi", "is beta version and deviceName not contains special char");
            } else if (!str.toUpperCase(Locale.ENGLISH).contains(next.toUpperCase(Locale.ENGLISH))) {
                continue;
            } else {
                if (!this.u) {
                    break;
                }
                z2 = n(str);
            }
        }
        LogUtil.c("BTSDKApi", "filterScanDeviceName isExistDeviceList isAddDeviceList is ", Boolean.valueOf(z));
        return z;
    }

    private boolean l(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        Iterator<String> it = this.v.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                z = true;
            } else if (upperCase.contains(next.toUpperCase(Locale.ENGLISH))) {
                z = true;
                break;
            }
        }
        LogUtil.c("BTSDKApi", "isSelectedDeviceName is ", Boolean.valueOf(z));
        return z;
    }

    private boolean w() {
        return bma.c() && this.q == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogUtil.c("BTSDKApi", "Enter disconnectBoundConnectedDevice.");
        for (Map.Entry<String, izi> entry : f.entrySet()) {
            if (entry != null && (entry.getValue() instanceof izi)) {
                izi value = entry.getValue();
                if (value != null && value.g() != null) {
                    LogUtil.c("BTSDKApi", "disconnectBoundConnectedDevice deviceConnectState :", Integer.valueOf(value.j()));
                    if (value.j() == 2) {
                        value.e();
                    }
                } else {
                    LogUtil.a("BTSDKApi", "disconnectBoundConnectedDevice sendCommandUtil or deviceInfo is null.");
                }
            }
        }
    }

    private boolean n(String str) {
        boolean z = false;
        for (String str2 : this.z) {
            if (TextUtils.isEmpty(str2) || str.toUpperCase(Locale.ENGLISH).contains(str2.toUpperCase(Locale.ENGLISH))) {
                z = true;
            }
        }
        return z;
    }

    public String a(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = this.l;
        return concurrentHashMap != null ? concurrentHashMap.get(str) : "";
    }

    public void e(String str) {
        ConcurrentHashMap<String, String> concurrentHashMap = this.l;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(str, "");
        }
    }

    private void a(boolean z, int i2, String str) {
        if (z && i2 == 1) {
            izi iziVar = f.get(str);
            if (iziVar == null) {
                LogUtil.a("BTSDKApi", "resetDeviceConnectAvailable sendCommandUtil is null.");
            } else {
                if (iziVar.m()) {
                    return;
                }
                LogUtil.c("BTSDKApi", "resetDeviceConnectAvailable has reset.");
                iziVar.b(true);
            }
        }
    }

    private void u() {
        e.post(new Runnable() { // from class: izy.6
            @Override // java.lang.Runnable
            public void run() {
                synchronized (izy.b) {
                    if (izy.this.ac != null) {
                        izy.this.ac.onSetList(izy.this.k, false, 0);
                    }
                }
            }
        });
    }

    public int g(String str) {
        Map<String, izi> map = f;
        if (map == null || str == null) {
            LogUtil.a("BTSDKApi", "mDeviceMgrMap or deviceIdentify is null");
            return -1;
        }
        izi iziVar = map.get(str);
        if (iziVar == null) {
            return -1;
        }
        return iziVar.h();
    }

    public void l() {
        b bVar = e;
        if (bVar == null) {
            LogUtil.a("BTSDKApi", "showMissParametersTip mAddDeviceHandler is null");
        } else {
            bVar.sendEmptyMessage(8);
        }
    }
}
