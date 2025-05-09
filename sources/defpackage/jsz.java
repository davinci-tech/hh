package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceBondStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.wearmgr.outofprocess.datatype.BluetoothType;
import com.huawei.wearmgr.phoneprocess.adapterapi.WearMgrAdapterApi;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class jsz implements HwDeviceMgrInterface {
    protected DeviceStateCallback c;
    private IBaseCallback f;
    private Context k;
    private final BtDeviceBondStateCallback n;
    private izy o;
    private kct p;
    private HandlerThread q;
    private Handler r;
    private BtDeviceStateCallback s;
    private kcw t;
    private BroadcastReceiver y;

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, jsq> f14059a = new ConcurrentHashMap<>(24);
    private static final Object g = new Object();
    private static final Object d = new Object();
    private static final Object e = new Object();
    private static HwDeviceMgrInterface j = null;
    private static jsz i = null;
    private static jsx h = null;
    protected iyl b = iyl.d();
    private List<DeviceInfo> l = new ArrayList(24);
    private BtDeviceResponseCallback m = new BtDeviceResponseCallback() { // from class: jsz.3
        @Override // com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback
        public void onResponse(int i2, Object obj) {
            try {
                if (jsz.this.f == null || !(obj instanceof String)) {
                    return;
                }
                jsz.this.f.onResponse(i2, (String) obj);
            } catch (RemoteException unused) {
                LogUtil.b("HwDeviceMgr", "exception remoteException");
            }
        }
    };

    private jsz() {
        this.o = null;
        BtDeviceBondStateCallback btDeviceBondStateCallback = new BtDeviceBondStateCallback() { // from class: jsz.1
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceBondStateCallback
            public void onBtDeviceBondNone(String str) {
                int b = jsz.h.b(str, jsz.this.l);
                if (b != -1) {
                    DeviceInfo deviceInfo = (DeviceInfo) jsz.this.l.get(b);
                    if (deviceInfo != null) {
                        Intent intent = new Intent("com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE");
                        intent.putExtra("DEVICE_SECURITY_UUID", deviceInfo.getSecurityUuid());
                        jsz.this.k.sendBroadcast(intent, LocalBroadcast.c);
                    }
                    for (DeviceInfo deviceInfo2 : jsz.this.l) {
                        if (deviceInfo2 != null && !TextUtils.isEmpty(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getDeviceIdentify().equalsIgnoreCase(str) && deviceInfo2.getDeviceBluetoothType() == 2) {
                            return;
                        }
                    }
                    LogUtil.a("HwDeviceMgr", "enter onBtDeviceBondNone(). need to remove device.");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(str);
                    jsz.this.unPair(arrayList, true);
                }
            }
        };
        this.n = btDeviceBondStateCallback;
        this.y = new BroadcastReceiver() { // from class: jsz.2
            private String e = "";

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                boolean z;
                if (intent != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                    String format = simpleDateFormat.format(new Date());
                    if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
                        if ("".equals(this.e)) {
                            this.e = format;
                            return;
                        }
                        try {
                            Date parse = simpleDateFormat2.parse(format);
                            Date parse2 = simpleDateFormat2.parse(this.e);
                            long time = parse.getTime() - parse2.getTime();
                            LogUtil.a("HwDeviceMgr", "currentTime:", parse, ",mLaterTime:", parse2, ",timeTap:", Long.valueOf(time));
                            long j2 = (time / 1000) / 60;
                            if (j2 > 25 || j2 < -25) {
                                this.e = format;
                                z = true;
                            } else {
                                this.e = format;
                                return;
                            }
                        } catch (ParseException unused) {
                            this.e = format;
                            return;
                        }
                    } else {
                        this.e = format;
                        z = false;
                    }
                    if (jsz.h != null) {
                        jsz.h.bJm_(intent, z, jsz.this.l);
                    }
                }
            }
        };
        this.s = new BtDeviceStateCallback() { // from class: jsz.4
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
            public void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i2, OperationDeviceInfo operationDeviceInfo) {
                LogUtil.a("HwDeviceMgr", "updateDeviceList btState: ", Integer.valueOf(i2));
                jsu.c().b(deviceInfo, i2, operationDeviceInfo);
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
            public void onDataReceived(DeviceInfo deviceInfo, int i2, byte[] bArr) {
                jsr.c().d(deviceInfo, i2, bArr);
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
            public void onAckReceived(DeviceInfo deviceInfo, int i2, byte[] bArr) {
                if (jsz.this.c != null) {
                    jsz.this.c.onAckReceived(deviceInfo, i2, bArr);
                }
            }

            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback
            public void disconnectBluetooth(DeviceInfo deviceInfo, int i2) {
                jsu.c().c(deviceInfo);
            }
        };
        LogUtil.a("HwDeviceMgr", "Enter HwDeviceMgr().");
        this.k = BaseApplication.getContext();
        h();
        izy b = izy.b(this.k);
        this.o = b;
        b.a(this.s);
        h = jsx.c();
        this.p = kct.c();
        this.t = kcw.a();
        this.c = null;
        HandlerThread handlerThread = new HandlerThread("save_dms_data");
        this.q = handlerThread;
        handlerThread.start();
        this.r = new Handler(this.q.getLooper());
        l();
        o();
        iyl iylVar = this.b;
        if (iylVar != null) {
            iylVar.b(btDeviceBondStateCallback);
        }
    }

    public List<DeviceInfo> d() {
        return this.l;
    }

    private void l() {
        if (this.l != null) {
            LogUtil.a("HwDeviceMgr", "Start to find last active device.");
            m();
            if (!AuthorizationUtils.a(BaseApplication.getContext())) {
                LogUtil.h("HwDeviceMgr", "start active device, but not authorize, so return");
            } else {
                if (h.d(this.l).isEmpty()) {
                    return;
                }
                LogUtil.a("HwDeviceMgr", "Find active device.");
                b(this.l);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0072, code lost:
    
        if (r0 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0074, code lost:
    
        r4 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x007c, code lost:
    
        if (r4.hasNext() == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        r5 = r4.next().getDeviceIdentify();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0088, code lost:
    
        if (r5 == null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008e, code lost:
    
        if (r5.equalsIgnoreCase(r10) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0090, code lost:
    
        r4.remove();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:
    
        if (r2 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0095, code lost:
    
        r9.p.c(r10);
        r9.l.remove(r2);
        defpackage.jsr.c().d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ab, code lost:
    
        if (r1.size() == 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
    
        j();
        r10 = r9.o;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b2, code lost:
    
        if (r10 == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b4, code lost:
    
        r10.a(r1);
        defpackage.jsz.h.e(r1);
        c(r0);
        defpackage.kjs.d().e(r9.l);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c9, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("HwDeviceMgr", "Do not need delete device.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d4, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.lang.String r10) {
        /*
            r9 = this;
            com.huawei.health.devicemgr.api.constant.HwGetDevicesMode r0 = com.huawei.health.devicemgr.api.constant.HwGetDevicesMode.ALL_DEVICES
            java.lang.String r1 = "oldClearDeviceByMac"
            r2 = 0
            java.util.List r0 = r9.getDeviceList(r0, r2, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 24
            r1.<init>(r3)
            java.lang.Object r3 = r9.a()
            monitor-enter(r3)
            java.util.List<com.huawei.health.devicemgr.business.entity.DeviceInfo> r4 = r9.l     // Catch: java.lang.Throwable -> Ld5
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> Ld5
        L1c:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> Ld5
            if (r5 == 0) goto L72
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> Ld5
            com.huawei.health.devicemgr.business.entity.DeviceInfo r5 = (com.huawei.health.devicemgr.business.entity.DeviceInfo) r5     // Catch: java.lang.Throwable -> Ld5
            if (r5 != 0) goto L2c
            monitor-exit(r3)     // Catch: java.lang.Throwable -> Ld5
            return
        L2c:
            java.lang.String r6 = r5.getDeviceIdentify()     // Catch: java.lang.Throwable -> Ld5
            boolean r7 = r6.equalsIgnoreCase(r10)     // Catch: java.lang.Throwable -> Ld5
            if (r7 == 0) goto L1c
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r7 = "wanted remove device."
            r8 = 0
            r4[r8] = r7     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r7 = "HwDeviceMgr"
            com.huawei.hwlogsmodel.LogUtil.a(r7, r4)     // Catch: java.lang.Throwable -> Ld5
            r4 = -1
            defpackage.jst.d(r6, r4)     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r4 = ""
            defpackage.jst.f(r6, r4)     // Catch: java.lang.Throwable -> Ld5
            r4 = 3
            defpackage.jst.e(r6, r4)     // Catch: java.lang.Throwable -> Ld5
            int r4 = r5.getProductType()     // Catch: java.lang.Throwable -> Ld5
            r6 = -2
            if (r6 == r4) goto L66
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r6 = "Add wanted deleted device into list."
            r4[r8] = r6     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r6 = "HwDeviceMgr"
            com.huawei.hwlogsmodel.LogUtil.a(r6, r4)     // Catch: java.lang.Throwable -> Ld5
            r1.add(r5)     // Catch: java.lang.Throwable -> Ld5
        L66:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r4 = "Remove Device from list."
            r2[r8] = r4     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r4 = "HwDeviceMgr"
            com.huawei.hwlogsmodel.LogUtil.a(r4, r2)     // Catch: java.lang.Throwable -> Ld5
            r2 = r5
        L72:
            if (r0 == 0) goto L93
            java.util.Iterator r4 = r0.iterator()     // Catch: java.lang.Throwable -> Ld5
        L78:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> Ld5
            if (r5 == 0) goto L93
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> Ld5
            com.huawei.health.devicemgr.business.entity.DeviceInfo r5 = (com.huawei.health.devicemgr.business.entity.DeviceInfo) r5     // Catch: java.lang.Throwable -> Ld5
            java.lang.String r5 = r5.getDeviceIdentify()     // Catch: java.lang.Throwable -> Ld5
            if (r5 == 0) goto L78
            boolean r5 = r5.equalsIgnoreCase(r10)     // Catch: java.lang.Throwable -> Ld5
            if (r5 == 0) goto L78
            r4.remove()     // Catch: java.lang.Throwable -> Ld5
        L93:
            if (r2 == 0) goto La6
            kct r4 = r9.p     // Catch: java.lang.Throwable -> Ld5
            r4.c(r10)     // Catch: java.lang.Throwable -> Ld5
            java.util.List<com.huawei.health.devicemgr.business.entity.DeviceInfo> r10 = r9.l     // Catch: java.lang.Throwable -> Ld5
            r10.remove(r2)     // Catch: java.lang.Throwable -> Ld5
            jsr r10 = defpackage.jsr.c()     // Catch: java.lang.Throwable -> Ld5
            r10.d()     // Catch: java.lang.Throwable -> Ld5
        La6:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> Ld5
            int r10 = r1.size()
            if (r10 == 0) goto Lc9
            r9.j()
            izy r10 = r9.o
            if (r10 == 0) goto Ld4
            r10.a(r1)
            jsx r10 = defpackage.jsz.h
            r10.e(r1)
            r9.c(r0)
            kjs r10 = defpackage.kjs.d()
            java.util.List<com.huawei.health.devicemgr.business.entity.DeviceInfo> r0 = r9.l
            r10.e(r0)
            goto Ld4
        Lc9:
            java.lang.String r10 = "Do not need delete device."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            java.lang.String r0 = "HwDeviceMgr"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r10)
        Ld4:
            return
        Ld5:
            r10 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> Ld5
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsz.d(java.lang.String):void");
    }

    private void h() {
        if (CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            return;
        }
        String d2 = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d2) || bfh.f349a.equals(d2)) {
            return;
        }
        throw new RuntimeException("DMS must init in PhoneService process." + d2);
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void destroy() {
        Context context = this.k;
        if (context != null) {
            try {
                context.unregisterReceiver(this.y);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("HwDeviceMgr", "destroy IllegalArgumentException");
            }
            izy izyVar = this.o;
            if (izyVar != null) {
                izyVar.f();
            }
            jsx jsxVar = h;
            if (jsxVar != null) {
                jsxVar.e();
            }
        }
        kcw kcwVar = this.t;
        if (kcwVar != null) {
            kcwVar.c();
        }
        kct kctVar = this.p;
        if (kctVar != null) {
            kctVar.b();
        }
        f();
        g();
    }

    private void g() {
        HandlerThread handlerThread = this.q;
        if (handlerThread != null) {
            handlerThread.quit();
            this.q = null;
        }
    }

    private static void f() {
        synchronized (g) {
            j = null;
        }
    }

    private void o() {
        if (this.k != null) {
            this.k.registerReceiver(this.y, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
            IntentFilter intentFilter = new IntentFilter("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            this.k.registerReceiver(this.y, intentFilter);
            this.k.registerReceiver(this.y, new IntentFilter("android.intent.action.DATE_CHANGED"));
        }
    }

    public Object a() {
        return e;
    }

    private void m() {
        LogUtil.a("HwDeviceMgr", "Enter getUsedDevicesFromStorage().");
        Map<String, String> a2 = SharedPreferenceManager.a(this.k, String.valueOf(1000));
        if (a2 == null) {
            LogUtil.h("HwDeviceMgr", "map is null.");
            return;
        }
        synchronized (a()) {
            this.l.clear();
            Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
            while (it.hasNext()) {
                DeviceInfo deviceInfo = (DeviceInfo) new Gson().fromJson(it.next().getValue(), DeviceInfo.class);
                if (deviceInfo != null) {
                    deviceInfo.setDeviceConnectState(3);
                    this.l.add(deviceInfo);
                }
            }
        }
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        LogUtil.a("HwDeviceMgr", "updateDeviceDbInfo mDeviceInfoList size: ", Integer.valueOf(this.l.size()));
        if (this.l.size() == 0) {
            jte.a(false, false);
        } else {
            jte.a(true, false);
        }
    }

    public void e(final List<DeviceInfo> list) {
        LogUtil.a("HwDeviceMgr", "Enter setUsedDevicesToStorage(). sync");
        if (list == null) {
            return;
        }
        this.r.post(new Runnable() { // from class: jsz.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (jsz.this.a()) {
                    jsz.this.l.clear();
                    jsz.this.l.addAll(list);
                    jsz.this.t();
                    jsz.this.p();
                }
            }
        });
    }

    public void j() {
        LogUtil.a("HwDeviceMgr", "Enter setUsedDevicesToStorage().");
        this.r.post(new Runnable() { // from class: jsz.7
            @Override // java.lang.Runnable
            public void run() {
                synchronized (jsz.this.a()) {
                    jsz.this.t();
                    jsz.this.p();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogUtil.a("HwDeviceMgr", "Enter updateStorageDevices.");
        List<DeviceInfo> list = this.l;
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            LogUtil.a("HwDeviceMgr", "updateStorageDevices DEVICE_INFO_LIST is empty");
            SharedPreferenceManager.j(this.k, String.valueOf(1000));
            return;
        }
        Set<String> d2 = SharedPreferenceManager.d(this.k, String.valueOf(1000));
        LogUtil.a("HwDeviceMgr", "updateStorageDevices size: ", Integer.valueOf(d2.size()), Integer.valueOf(this.l.size()));
        for (DeviceInfo deviceInfo : this.l) {
            LogUtil.a("HwDeviceMgr", "updateStorageDevices device: ", deviceInfo.getDeviceName());
            if (TextUtils.isEmpty(deviceInfo.getWearEngineDeviceId())) {
                jsr.c();
                deviceInfo.setWearEngineDeviceId(jsr.b());
            }
            SharedPreferenceManager.e(this.k, String.valueOf(1000), deviceInfo.getDeviceIdentify(), new Gson().toJson(deviceInfo), new StorageParams());
            d2.remove(deviceInfo.getDeviceIdentify());
        }
        if (d2.size() == 0) {
            return;
        }
        LogUtil.a("HwDeviceMgr", "updateStorageDevices start delete devices");
        Iterator<String> it = d2.iterator();
        while (it.hasNext()) {
            SharedPreferenceManager.c(this.k, String.valueOf(1000), it.next());
        }
    }

    public static HwDeviceMgrInterface b(Context context) {
        jtc c;
        synchronized (g) {
            c = jtc.c();
            j = c;
        }
        return c;
    }

    public static jsz b() {
        jsz jszVar;
        synchronized (d) {
            if (i == null) {
                i = new jsz();
            }
            jszVar = i;
        }
        return jszVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void addDevice(DeviceParameter deviceParameter, IAddDeviceStateCallback iAddDeviceStateCallback, String str) {
        ArrayList arrayList;
        LogUtil.a("HwDeviceMgr", "Enter addDevice().");
        kjs.d().b(2);
        if (this.o == null || deviceParameter == null) {
            return;
        }
        if (deviceParameter.getBluetoothType() == 0) {
            Iterator<DeviceInfo> it = this.l.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (next != null && "AndroidWear".equals(next.getDeviceIdentify())) {
                    next.setDeviceActiveState(1);
                    break;
                }
            }
        }
        if (deviceParameter.isSupportHeartRate()) {
            arrayList = new ArrayList(16);
            for (int i2 : cux.b()) {
                arrayList.addAll(juu.e(i2));
            }
        } else {
            arrayList = null;
        }
        this.o.d(deviceParameter, arrayList, iAddDeviceStateCallback);
    }

    public void a(String str) {
        jsx jsxVar = h;
        if (jsxVar == null) {
            LogUtil.h("HwDeviceMgr", "sHwDevicePartMgr is null.");
            return;
        }
        jsxVar.a();
        if (this.o == null || TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("HwDeviceMgr", "addDevice goingDisconnectDevice is not null.");
        this.o.e(false, str);
        this.o.c(str);
        this.o.o(str);
        List<Integer> d2 = h.d(this.l);
        ArrayList arrayList = new ArrayList(24);
        if (d2 != null && !d2.isEmpty()) {
            Iterator<Integer> it = d2.iterator();
            while (it.hasNext()) {
                e(this.l.get(it.next().intValue()), str, arrayList);
            }
        }
        h.e(arrayList);
        e(str);
    }

    private void e(DeviceInfo deviceInfo, String str, List<DeviceInfo> list) {
        if (deviceInfo == null) {
            LogUtil.h("HwDeviceMgr", "addDevice active device info is null");
            return;
        }
        LogUtil.c("HwDeviceMgr", "addDevice active device", deviceInfo);
        if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("HwDeviceMgr", "addDevice find Target device");
            list.add(deviceInfo);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void connectDevice(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, int i2) {
        if (deviceInfo != null) {
            d(deviceInfo);
            return;
        }
        boolean z = false;
        boolean z2 = (BluetoothType.BLE.getValue() & i2) == BluetoothType.BLE.getValue();
        boolean z3 = (BluetoothType.BR.getValue() & i2) == BluetoothType.BR.getValue();
        boolean z4 = (i2 & BluetoothType.AW.getValue()) == BluetoothType.AW.getValue();
        if (z2 || z3 || z4) {
            if (z2 && !z3) {
                z = true;
            }
            e(z);
        }
    }

    private void d(com.huawei.devicesdk.entity.DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2;
        LogUtil.a("HwDeviceMgr", "Enter connectDeviceDirectly().");
        if (deviceInfo.getDeviceMac() == null || this.l == null || this.o == null) {
            return;
        }
        kjs.d().b(2);
        h.a();
        Iterator<DeviceInfo> it = this.l.iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo2 = null;
                break;
            }
            deviceInfo2 = it.next();
            if (deviceInfo2 != null && deviceInfo.getDeviceMac().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                break;
            }
        }
        if (deviceInfo2 == null) {
            deviceInfo2 = new DeviceInfo();
            deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
            deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceMac());
            deviceInfo2.setDeviceProtocol(2);
            deviceInfo2.setProductType(deviceInfo.getDeviceType());
            deviceInfo2.setDeviceActiveState(1);
            deviceInfo2.setDeviceBluetoothType(deviceInfo.getDeviceBtType());
            synchronized (a()) {
                this.l.add(deviceInfo2);
                j();
            }
        }
        h.e(deviceInfo2, this.l);
        int b = h.b(deviceInfo2.getDeviceIdentify(), this.l);
        if (b == -1) {
            LogUtil.h("HwDeviceMgr", "History List do not have active device.");
            return;
        }
        if (this.l.get(b).getDeviceActiveState() != 1) {
            jst.d(deviceInfo.getDeviceMac()).resetDeviceCapability();
            this.l.get(b).setDeviceActiveState(1);
            j();
        }
        izy izyVar = this.o;
        if (izyVar != null) {
            izyVar.e(deviceInfo2, true);
        }
    }

    private void c(String str) {
        boolean z;
        synchronized (a()) {
            z = false;
            LogUtil.a("HwDeviceMgr", "removeCurrentDevice getDeviceInfoListHandle");
            Iterator<DeviceInfo> it = this.l.iterator();
            while (it.hasNext()) {
                if (it.next().getDeviceIdentify().equalsIgnoreCase(str)) {
                    z = true;
                }
            }
        }
        LogUtil.a("HwDeviceMgr", "removeCurrentDevice isAdded:", Boolean.valueOf(z));
        if (z) {
            d(str);
            return;
        }
        izy izyVar = this.o;
        if (izyVar != null) {
            izyVar.h(str);
        }
    }

    private void c(List<DeviceInfo> list) {
        LogUtil.a("HwDeviceMgr", "Enter resetActiveDevice.");
        if (list != null) {
            ArrayList arrayList = new ArrayList(24);
            for (int i2 = 0; i2 < list.size(); i2++) {
                if ("main_relationship".equals(list.get(i2).getRelationship()) && list.get(i2).getDeviceActiveState() == 1) {
                    LogUtil.a("HwDeviceMgr", "active device name: ", list.get(i2).getDeviceName());
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            if (!arrayList.isEmpty()) {
                LogUtil.h("HwDeviceMgr", "resetActiveDevice exist active device.");
                return;
            }
        }
        synchronized (a()) {
            DeviceInfo deviceInfo = null;
            List<DeviceInfo> deviceList = jtc.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwDeviceMgr");
            if (deviceList != null) {
                Iterator<DeviceInfo> it = deviceList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfo next = it.next();
                    if ("main_relationship".equalsIgnoreCase(jta.d().e(next.getDeviceIdentify()))) {
                        deviceInfo = next;
                        break;
                    }
                }
            }
            if (deviceInfo != null) {
                LogUtil.a("HwDeviceMgr", "resetActiveDevice activeMainDevice fuzzData: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
                if (jsn.d(deviceInfo.getProductType())) {
                    LogUtil.h("HwDeviceMgr", "resetActiveDevice mDeviceInfoList is empty.");
                    com.huawei.devicesdk.entity.DeviceInfo deviceInfo2 = new com.huawei.devicesdk.entity.DeviceInfo();
                    deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
                    deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
                    deviceInfo2.setDeviceMac(deviceInfo.getDeviceIdentify());
                    deviceInfo2.setDeviceType(deviceInfo.getProductType());
                    jtc.c().connectDevice(deviceInfo2, 0);
                } else {
                    b(deviceInfo);
                }
            }
        }
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.a("HwDeviceMgr", "resetActiveDevice device name :", CommonUtil.l(deviceInfo.getDeviceName()));
            synchronized (a()) {
                LogUtil.a("HwDeviceMgr", "removeCurrentDevice getDeviceInfoListHandle");
                Iterator<DeviceInfo> it = this.l.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeviceInfo next = it.next();
                    if (next.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                        next.setDeviceActiveState(1);
                        break;
                    }
                }
            }
            j();
            this.o.c(deviceInfo.getDeviceIdentify(), deviceInfo.getNodeId(), deviceInfo.getDeviceName(), deviceInfo.getDeviceBluetoothType());
            izm.a(deviceInfo.getDeviceIdentify());
        }
    }

    private void e(List<DeviceInfo> list, List<DeviceInfo> list2) {
        LogUtil.a("HwDeviceMgr", "enter deleteNotExistDevices");
        for (DeviceInfo deviceInfo : list2) {
            if (deviceInfo != null) {
                Iterator<DeviceInfo> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        LogUtil.a("HwDeviceMgr", "deleteDevice");
                        c(deviceInfo.getDeviceIdentify());
                        break;
                    }
                    DeviceInfo next = it.next();
                    if (next == null) {
                        LogUtil.h("HwDeviceMgr", "currentDeviceInfo is null");
                    } else if (TextUtils.isEmpty(next.getDeviceIdentify()) || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                        LogUtil.h("HwDeviceMgr", "deviceIdentify is null");
                    } else if (next.getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                        break;
                    }
                }
            }
        }
    }

    private void b(List<DeviceInfo> list) {
        LogUtil.a("HwDeviceMgr", "Enter initDeviceList(), ");
        if (list == null) {
            LogUtil.h("HwDeviceMgr", "initDeviceList parameter is incorrect.");
            return;
        }
        ArrayList arrayList = new ArrayList(24);
        ArrayList<DeviceInfo> arrayList2 = new ArrayList(24);
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo.getDeviceActiveState() == 0) {
                arrayList.add(deviceInfo);
            }
            if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() != 2) {
                LogUtil.a("HwDeviceMgr", "findWantedSwitchDevice find ", deviceInfo.getDeviceName());
                arrayList2.add(deviceInfo);
            }
        }
        h.e(arrayList);
        h.a(arrayList);
        if (arrayList2.isEmpty()) {
            LogUtil.a("HwDeviceMgr", "index = -1, not find wanted switch device");
            return;
        }
        izy.b(this.k).b(true);
        for (DeviceInfo deviceInfo2 : arrayList2) {
            if (bml.b(this.k, deviceInfo2)) {
                String deviceIdentify = deviceInfo2.getDeviceIdentify();
                jst.d(deviceIdentify, -1);
                jst.f(deviceIdentify, "");
                jst.e(deviceIdentify, 3);
                jst.a(deviceInfo2.getDeviceIdentify(), new DeviceCapability());
                h.e(deviceInfo2, this.l);
                j();
                izy izyVar = this.o;
                if (izyVar != null) {
                    izyVar.e(deviceInfo2, false);
                }
            }
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void switchDevice(List<DeviceInfo> list, String str) {
        LogUtil.a("HwDeviceMgr", "Enter switchDevice(), ");
        if (list == null) {
            LogUtil.h("HwDeviceMgr", "Parameter is incorrect.");
            return;
        }
        h.a();
        if (list.size() != this.l.size()) {
            e(list, this.l);
            return;
        }
        List<Integer> c = h.c(list, str);
        if (c.isEmpty()) {
            LogUtil.h("HwDeviceMgr", "index = -1, not find wanted switch device");
            return;
        }
        izy.b(this.k).b(false);
        for (Integer num : c) {
            DeviceInfo deviceInfo = list.get(num.intValue());
            if (bml.b(this.k, deviceInfo)) {
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                if (cvt.c(deviceInfo.getProductType())) {
                    this.p.d(deviceInfo);
                } else {
                    this.p.d(deviceInfo, true);
                }
                jst.d(deviceIdentify, -1);
                jst.f(deviceIdentify, "");
                jst.e(deviceIdentify, 3);
                c(deviceInfo, num.intValue());
            }
        }
    }

    private void c(DeviceInfo deviceInfo, int i2) {
        jst.a(deviceInfo.getDeviceIdentify(), new DeviceCapability());
        if (h.b(deviceInfo.getDeviceIdentify(), this.l) == -1) {
            LogUtil.a("HwDeviceMgr", "index = -1, updateUsedDeviceActiveStatus");
        } else {
            h.e(deviceInfo, this.l);
            if (i2 >= 0) {
                this.l.get(i2).setDeviceActiveState(1);
            }
            j();
        }
        if (this.o != null) {
            if (i2 >= 0) {
                this.l.get(i2).setLastConnectedTime(System.currentTimeMillis());
            }
            this.o.e(deviceInfo, true);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void registerDeviceStateCallback(DeviceStateCallback deviceStateCallback) {
        LogUtil.a("HwDeviceMgr", "Enter registerDeviceStateCallback().");
        this.c = deviceStateCallback;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void unRegisterDeviceStateCallback(DeviceStateCallback deviceStateCallback) {
        LogUtil.a("HwDeviceMgr", "enter unRegisterDeviceStateCallback().");
        this.c = null;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void sendDeviceData(DeviceCommand deviceCommand) {
        List<DeviceInfo> b;
        if (deviceCommand == null) {
            LogUtil.h("HwDeviceMgr", "sendDeviceData() deviceCommand is null.");
            return;
        }
        if (!TextUtils.isEmpty(deviceCommand.getmIdentify())) {
            d(deviceCommand, deviceCommand.getmIdentify());
            return;
        }
        synchronized (a()) {
            b = h.b(this.l);
        }
        LogUtil.a("HwDeviceMgr", "sendDeviceData connectedDeviceInfos size: ", Integer.valueOf(b.size()));
        if (b.size() == 2) {
            DeviceInfo deviceInfo = b.get(0);
            DeviceInfo deviceInfo2 = b.get(1);
            if (cvt.c(deviceInfo.getProductType())) {
                d(deviceCommand, deviceInfo.getDeviceIdentify(), deviceInfo2.getDeviceIdentify());
                return;
            } else {
                d(deviceCommand, deviceInfo2.getDeviceIdentify(), deviceInfo.getDeviceIdentify());
                return;
            }
        }
        if (b.size() == 1) {
            DeviceInfo deviceInfo3 = b.get(0);
            if (deviceInfo3.getDeviceActiveState() != 1) {
                LogUtil.a("HwDeviceMgr", "sendDeviceData connected device is not active device");
                return;
            } else {
                d(deviceCommand, deviceInfo3.getDeviceIdentify());
                return;
            }
        }
        LogUtil.c("HwDeviceMgr", "sendDeviceData go to else branch");
    }

    private void d(DeviceCommand deviceCommand, String str, String str2) {
        if (deviceCommand.getServiceID() == 23 && deviceCommand.getCommandID() == 15) {
            d(deviceCommand, str);
        } else {
            d(deviceCommand, str2);
        }
    }

    private void d(DeviceCommand deviceCommand, String str) {
        deviceCommand.setmIdentify(str);
        if (jst.s(deviceCommand.getmIdentify())) {
            LogUtil.h("HwDeviceMgr", "OTA update, other command can't send...");
            return;
        }
        if (deviceCommand.getCommandID() == 13 && deviceCommand.getServiceID() == 1) {
            h.a();
        }
        ReleaseLogUtil.e("R_HwDeviceMgr", "sendDeviceCommand ServiceID:", Integer.valueOf(deviceCommand.getServiceID()), ", CommandID:", Integer.valueOf(deviceCommand.getCommandID()));
        boolean a2 = a(deviceCommand);
        if (i(deviceCommand.getmIdentify()) == 2 || a2) {
            ByteBuffer allocate = ByteBuffer.allocate(deviceCommand.getDataLen() + 2);
            allocate.put(cvx.a(cvx.e(deviceCommand.getServiceID())));
            allocate.put(cvx.a(cvx.e(deviceCommand.getCommandID())));
            if (deviceCommand.getDataContent() != null) {
                allocate.put(deviceCommand.getDataContent());
                LogUtil.a("HwDeviceMgr", "sendDeviceCommand command data:", cvx.d(deviceCommand.getDataContent()));
                ReleaseLogUtil.e("R_HwDeviceMgr", "sendDeviceCommand command data");
            } else {
                LogUtil.h("HwDeviceMgr", "command data is null, if not OTA, data incorrect.");
            }
            allocate.flip();
            izf izfVar = new izf();
            izfVar.e(allocate.array());
            izfVar.e(allocate.array().length);
            izfVar.e(deviceCommand.getNeedAck());
            izfVar.b(deviceCommand.getPriority());
            String str2 = deviceCommand.getmIdentify();
            izfVar.e(str2);
            izfVar.a(deviceCommand.getNeedEncrypt());
            izfVar.i(deviceCommand.getServiceID());
            izfVar.d(deviceCommand.getCommandID());
            izy izyVar = this.o;
            if (izyVar != null) {
                izyVar.c(e(str2, a2), izfVar);
            }
        }
    }

    private boolean a(DeviceCommand deviceCommand) {
        boolean k = jst.k(deviceCommand.getmIdentify());
        DeviceCapability d2 = jst.d(deviceCommand.getmIdentify());
        return k && d2 != null && d2.isSupportUserSetting() && deviceCommand.getCommandID() == 48 && deviceCommand.getServiceID() == 1;
    }

    private int e(String str, boolean z) {
        return z ? this.o.g(str) : h(str);
    }

    public int b(String str) {
        int i2;
        List<DeviceInfo> list = this.l;
        if (list != null && list.size() != 0) {
            i2 = 0;
            while (i2 < this.l.size()) {
                if (this.l.get(i2).getDeviceIdentify().equals(str) && this.l.get(i2).getDeviceActiveState() == 1) {
                    break;
                }
                i2++;
            }
        }
        i2 = -1;
        LogUtil.a("HwDeviceMgr", "getActiveDeviceIndex:", Integer.valueOf(i2));
        return i2;
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("HwDeviceMgr", "is Enter ZoneID");
        if (deviceInfo == null) {
            LogUtil.h("HwDeviceMgr", "btDeviceInfo is null");
            return;
        }
        izf o = iyo.o();
        o.e(deviceInfo.getDeviceIdentify());
        if (this.o != null) {
            LogUtil.a("HwDeviceMgr", "start to get device zoneId type info.");
            this.o.c(deviceInfo.getDeviceBluetoothType(), o);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void unPair(List<String> list, boolean z) {
        LogUtil.h("HwDeviceMgr", "unPair: ", Boolean.valueOf(z));
        if (z) {
            d(list);
        } else {
            a(list);
        }
    }

    private void a(List<String> list) {
        List<DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "disconnectDevice");
        if (list == null) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                jsu.c().c(it.next());
            }
            return;
        }
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwDeviceMgr", "disconnectDevice mac is empty");
            } else {
                LogUtil.a("HwDeviceMgr", "disconnectDevice mac: ", CommonUtil.l(str));
                for (DeviceInfo deviceInfo : deviceList) {
                    if (deviceInfo == null || deviceInfo.getDeviceIdentify() == null) {
                        LogUtil.h("HwDeviceMgr", "disconnectDevice parameter error");
                    } else if (deviceInfo.getDeviceIdentify().equals(str)) {
                        i(Arrays.asList(deviceInfo));
                        jsu.c().c(deviceInfo);
                    }
                }
            }
        }
    }

    private void d(List<String> list) {
        if (list == null) {
            for (DeviceInfo deviceInfo : getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "removeDevice")) {
                if (deviceInfo == null || deviceInfo.getDeviceIdentify() == null) {
                    LogUtil.h("HwDeviceMgr", "removeDevice parameter error");
                } else {
                    c(deviceInfo.getDeviceIdentify());
                }
            }
            return;
        }
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwDeviceMgr", "removeDevice mac is empty");
            } else {
                LogUtil.a("HwDeviceMgr", "removeDevice mac: ", CommonUtil.l(str));
                c(str);
            }
        }
    }

    private void e(boolean z) {
        LogUtil.a("HwDeviceMgr", "forceConnectDevice enter");
        if (this.o == null) {
            LogUtil.h("HwDeviceMgr", "forceConnectDevice mBtSdkApi is null");
            return;
        }
        List<Integer> d2 = h.d(this.l);
        if (d2 != null) {
            LogUtil.a("HwDeviceMgr", "forceConnectDevice activeDeviceList size :", Integer.valueOf(d2.size()));
            for (Integer num : d2) {
                int deviceBluetoothType = this.l.get(num.intValue()).getDeviceBluetoothType();
                if (!z || deviceBluetoothType == 2) {
                    this.o.a(this.l.get(num.intValue()).getDeviceIdentify(), z);
                }
            }
        }
    }

    public Map<String, DeviceCapability> e() {
        ConcurrentHashMap<String, jsq> concurrentHashMap = f14059a;
        LogUtil.a("DEVMGR_SETTING", "HwDeviceMgr", "getDeviceCapabilityMap enter sUsedDeviceStatus size", Integer.valueOf(concurrentHashMap.size()));
        HashMap hashMap = new HashMap(4);
        for (jsq jsqVar : concurrentHashMap.values()) {
            if (jsqVar != null && !TextUtils.isEmpty(jsqVar.j()) && !cvt.c(jsqVar.t()) && b(jsqVar.j()) != -1) {
                DeviceCapability a2 = jsqVar.a();
                LogUtil.a("DEVMGR_SETTING", "HwDeviceMgr", "getDeviceCapability capabilityString", new Gson().toJson(a2, DeviceCapability.class));
                hashMap.put(jsqVar.j(), a2);
                return hashMap;
            }
        }
        LogUtil.a("DEVMGR_SETTING", "HwDeviceMgr", "getDeviceCapabilityMap fail");
        return hashMap;
    }

    public void e(String str) {
        LogUtil.a("HwDeviceMgr", "Enter setTargetDeviceDisable().");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwDeviceMgr", "macAddress is null");
            return;
        }
        synchronized (a()) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.l.size()) {
                    break;
                }
                if (str.equalsIgnoreCase(this.l.get(i2).getDeviceIdentify())) {
                    this.l.get(i2).setDeviceActiveState(0);
                    break;
                }
                i2++;
            }
        }
        j();
    }

    public void b(String str, DeviceInfo deviceInfo) {
        if (this.o != null) {
            LogUtil.a("HwDeviceMgr", "Enter sendBTDeviceData by asset way with filepath:", str);
            if (deviceInfo != null) {
                this.o.c(deviceInfo.getNodeId(), str);
            }
        }
    }

    public void c(BtDeviceResponseCallback btDeviceResponseCallback) {
        if (this.o != null) {
            LogUtil.a("HwDeviceMgr", "Enter setFileCallback callback:", btDeviceResponseCallback);
            this.o.c(btDeviceResponseCallback);
        }
    }

    public void d(IBaseCallback iBaseCallback) {
        if (this.o != null) {
            LogUtil.a("HwDeviceMgr", "Enter setAwFileCallback callback", iBaseCallback);
            this.o.a(this.m);
            this.f = iBaseCallback;
        }
    }

    private int i(String str) {
        int b = b(str);
        if (b != -1) {
            return this.l.get(b).getDeviceConnectState();
        }
        return 3;
    }

    private int h(String str) {
        jsx jsxVar;
        List<DeviceInfo> list;
        DeviceInfo deviceInfo;
        if (TextUtils.isEmpty(str) || (jsxVar = h) == null || (list = this.l) == null) {
            LogUtil.h("HwDeviceMgr", "isConnectedActiveDeviceStatus identify is null or sHwDevicePartMgr is null or mDeviceInfoList is null");
            return -1;
        }
        int b = jsxVar.b(str, list);
        if (b == -1 || b >= this.l.size() || (deviceInfo = this.l.get(b)) == null) {
            return -1;
        }
        return deviceInfo.getDeviceBluetoothType();
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        ArrayList arrayList = new ArrayList(24);
        if (hwGetDevicesMode == null) {
            LogUtil.h("HwDeviceMgr", "getDeviceList mode is null.");
            return arrayList;
        }
        switch (AnonymousClass8.c[hwGetDevicesMode.ordinal()]) {
            case 1:
                return jsy.b().a();
            case 2:
                return jsy.b().i();
            case 3:
                return jsy.b().c();
            case 4:
                return jsy.b().d();
            case 5:
                return jsy.b().b(hwGetDevicesParameter);
            case 6:
                return jsy.b().e();
            case 7:
                return jsy.b().c();
            case 8:
                return jsy.b().c();
            case 9:
                return jsy.b().g();
            default:
                LogUtil.h("HwDeviceMgr", "getDeviceList unsupport mode.");
                return arrayList;
        }
    }

    /* renamed from: jsz$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HwGetDevicesMode.values().length];
            c = iArr;
            try {
                iArr[HwGetDevicesMode.ALL_DEVICES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HwGetDevicesMode.CONNECTED_MAIN_DEVICES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HwGetDevicesMode.ACTIVE_MAIN_DEVICES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[HwGetDevicesMode.CONNECTED_AW70_DEVICES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[HwGetDevicesMode.IDENTIFY_DEVICES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[HwGetDevicesMode.CONNECTED_DEVICES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[HwGetDevicesMode.ACTIVE_DEVICES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[HwGetDevicesMode.MAIN_FOLLOW_DEVICES.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[HwGetDevicesMode.FOLLOWED_DEVICES.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void registerAdapter(WearMgrAdapterApi wearMgrAdapterApi) {
        LogUtil.a("HwDeviceMgr", "The old channel does not implement registerAdapter temporarily.");
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public Map<String, DeviceCapability> queryDeviceCapability(int i2, String str, String str2) {
        LogUtil.a("HwDeviceMgr", "enter queryDeviceCapability queryType：", Integer.valueOf(i2), "; queryContent:", CommonUtil.l(str), "; tag: ", str2);
        HashMap hashMap = new HashMap(4);
        if (i2 != 1) {
            if (i2 == 2) {
                return n();
            }
            if (i2 == 3) {
                return i();
            }
            if (i2 == 4) {
                return e();
            }
            if (i2 == 5) {
                return k();
            }
            LogUtil.h("HwDeviceMgr", "queryDeviceCapability queryType others");
            return hashMap;
        }
        Map<String, DeviceCapability> i3 = i();
        for (Map.Entry<String, DeviceCapability> entry : i3.entrySet()) {
            String key = entry.getKey();
            if (i3.containsKey(key) && TextUtils.equals(key, str)) {
                hashMap.put(key, entry.getValue());
                return hashMap;
            }
        }
        return hashMap;
    }

    private Map<String, DeviceCapability> n() {
        HashMap hashMap = new HashMap(4);
        for (jsq jsqVar : f14059a.values()) {
            if (jsqVar.d() == 2) {
                hashMap.put(jsqVar.j(), jsqVar.a());
            }
        }
        return hashMap;
    }

    private Map<String, DeviceCapability> i() {
        HashMap hashMap = new HashMap(4);
        for (jsq jsqVar : f14059a.values()) {
            LogUtil.a("HwDeviceMgr", "getAllCapability identify:", CommonUtil.l(jsqVar.j()));
            hashMap.put(jsqVar.j(), jsqVar.a());
        }
        return hashMap;
    }

    private Map<String, DeviceCapability> k() {
        HashMap hashMap = new HashMap(4);
        List<DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwDeviceMgr");
        if (deviceList != null && deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                String deviceIdentify = it.next().getDeviceIdentify();
                ConcurrentHashMap<String, jsq> concurrentHashMap = f14059a;
                if (concurrentHashMap.containsKey(deviceIdentify)) {
                    hashMap.put(deviceIdentify, concurrentHashMap.get(deviceIdentify).a());
                }
            }
        }
        return hashMap;
    }

    private void i(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwDeviceMgr", "unPairBtDevices input parameter is null.");
            return;
        }
        LogUtil.a("HwDeviceMgr", "unPairBtDevices :", Integer.valueOf(list.size()));
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && deviceInfo.getDeviceBluetoothType() == 2) {
                boolean c = this.b.c(deviceInfo.getDeviceIdentify());
                LogUtil.a("HwDeviceMgr", "unPairBtDevices device:", deviceInfo.toString());
                if (!c) {
                    LogUtil.h("HwDeviceMgr", "removeBTDeviceInstance Remove bond device fail.");
                }
            }
        }
    }
}
