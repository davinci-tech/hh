package defpackage;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Message;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.GpsParameter;
import com.huawei.datatype.GpsStruct;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class kcg implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f14276a;
    private static kcg d;
    private Context f;
    private String j;
    private GpsParameter m;
    private long o;
    private Intent q;
    private Location t;
    private static final Object e = new Object();
    private static final Object b = new Object();
    private static final Object c = new Object();
    private boolean s = false;
    private long l = 0;
    private List<GpsStruct> k = new ArrayList(10);
    private boolean r = false;
    private List<Long> y = new ArrayList(16);
    private List<Long> w = new ArrayList(16);
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: kcg.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (context == null || intent == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            String securityDeviceId = deviceInfo.getSecurityDeviceId();
            ReleaseLogUtil.e("Track_HwGpsLocationManager", "mConnectStateChangedReceiver() status ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            if (deviceConnectState != 2) {
                if (deviceConnectState == 3) {
                    if (kcg.this.p != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 3;
                        obtain.arg1 = 0;
                        kcg.this.p.sendMessage(obtain);
                        kcg.this.p.sendEmptyMessageDelayed(1, 300000L);
                        return;
                    }
                    return;
                }
                LogUtil.h("HwGpsLocationManager", "mConnectStateChangedReceiver onReceive default");
                return;
            }
            if (kcg.this.p != null) {
                kcg.this.p.removeMessages(1);
            }
            if (!kcc.a(deviceInfo)) {
                kcg.this.j = securityDeviceId;
                return;
            }
            if (kcg.this.j == null) {
                if (kcg.this.p != null) {
                    kcg.this.p.sendEmptyMessage(1);
                }
            } else if ((securityDeviceId == null || !kcg.this.j.equalsIgnoreCase(securityDeviceId)) && kcg.this.p != null) {
                kcg.this.p.sendEmptyMessage(1);
            }
            kcg.this.d(deviceInfo);
            kcg.this.j = deviceInfo.getSecurityDeviceId();
        }
    };
    private BroadcastReceiver g = new BroadcastReceiver() { // from class: kcg.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwGpsLocationManager", "mForegroundStatusChangeReceiver: intent is null");
                return;
            }
            if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                boolean unused = kcg.f14276a = intent.getBooleanExtra("isForeground", false);
                LogUtil.a("HwGpsLocationManager", "mForegroundStatusChangeReceiver() sIsForeground: ", Boolean.valueOf(kcg.f14276a));
                if (kcg.f14276a && kcg.this.s) {
                    kcg kcgVar = kcg.this;
                    kcgVar.a(kch.d(kcgVar.f));
                }
            }
        }
    };
    private IBaseResponseCallback n = new IBaseResponseCallback() { // from class: kcg.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof GpsParameter) {
                kcg.this.m = (GpsParameter) obj;
                ReleaseLogUtil.e("Track_HwGpsLocationManager", "bitmap ", Integer.valueOf(kcg.this.m.getGpsInfoBitmap()), " fomat ", Integer.valueOf(kcg.this.m.getGpsParaFormat()), ",threshold=", Integer.valueOf(kcg.this.m.getGpsThreshold()));
            }
        }
    };
    private IBaseResponseCallback u = new IBaseResponseCallback() { // from class: kcg.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            kcg kcgVar = kcg.this;
            kcgVar.d(kcgVar.n);
        }
    };
    private IBaseResponseCallback i = new IBaseResponseCallback() { // from class: kcg.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof Boolean) {
                kcg.this.s = ((Boolean) obj).booleanValue();
                if (kcg.this.s) {
                    kcg.this.l();
                    return;
                }
                ReleaseLogUtil.e("Track_HwGpsLocationManager", "device disable gps");
                kcg.this.a(100000);
                kkl.a(false);
                ktg.c().a("wearSportTrackLocation");
                kcg.this.k();
            }
        }
    };
    private ILoactionCallback v = new ILoactionCallback() { // from class: kcg.9
        @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
        public void dispatchLocationChanged(Location location) {
            d(kcg.this.y, "mLocationChanged");
            synchronized (kcg.e) {
                if (!kcg.this.s) {
                    ktg.c().a("wearSportTrackLocation");
                    kcg.this.k();
                    return;
                }
                if (location == null || location.getProvider().equals(GeocodeSearch.GPS)) {
                    if (kcg.this.m == null) {
                        return;
                    }
                    GpsStruct gpsStruct = new GpsStruct();
                    String stringBuffer = new StringBuffer(Integer.toBinaryString(kcg.this.m.getGpsInfoBitmap())).reverse().toString();
                    float bQW_ = ktr.bQW_(kcg.this.t, location);
                    if (location != null && bQW_ >= 0.0f && kcg.this.s) {
                        kcg.this.l += (int) (10.0f * bQW_);
                        bNc_(location, gpsStruct, stringBuffer, bQW_);
                    }
                    kcg.this.t = location;
                    return;
                }
                d(kcg.this.w, "mLocationError");
            }
        }

        private void d(List<Long> list, String str) {
            synchronized (kcg.b) {
                long currentTimeMillis = System.currentTimeMillis();
                list.add(Long.valueOf(currentTimeMillis));
                if (list.get(list.size() - 1) != null && list.get(list.size() - 1).longValue() - list.get(0).longValue() >= 60000) {
                    ReleaseLogUtil.e("Track_HwGpsLocationManager", "gpsStatisticsPrint ", str, Integer.valueOf(list.size()));
                    list.clear();
                    list.add(Long.valueOf(currentTimeMillis));
                }
            }
        }

        private void bNc_(Location location, GpsStruct gpsStruct, String str, float f) {
            if (str != null) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '1') {
                        kch.bNd_(i, kcg.this.l, location, f, gpsStruct);
                    }
                }
                long currentTimeMillis = System.currentTimeMillis();
                gpsStruct.setGpsStartTime((currentTimeMillis - ((location == null || kcg.this.t == null) ? 0L : location.getTime() - kcg.this.t.getTime())) / 1000);
                gpsStruct.setGpsEndTime(currentTimeMillis / 1000);
                kcg.this.k.add(gpsStruct);
                if (kcg.this.m.getGpsParaFormat() == 1 || (kcg.this.m.getGpsParaFormat() == 2 && kcg.this.k.size() == kcg.this.m.getGpsParaElementNum())) {
                    kcg kcgVar = kcg.this;
                    kcgVar.c(kcgVar.k, kcg.this.x, location.hasSpeed());
                    kcg.this.k.clear();
                }
            }
        }
    };
    private IBaseResponseCallback x = new IBaseResponseCallback() { // from class: kcg.8
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 100000) {
                ReleaseLogUtil.e("Track_HwGpsLocationManager", "mSetGpsParameterCallback errorCode:", Integer.valueOf(i));
                kkl.a(false);
                ktg.c().a("wearSportTrackLocation");
                kcg.this.k();
            }
        }
    };
    private kcj p = new kcj(this);

    private kcg(Context context) {
        this.j = "";
        this.f = context;
        if (m()) {
            n();
            kkl.a(false);
        }
        BroadcastManagerUtil.bFC_(this.f, this.h, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
        BroadcastManager.wj_(this.g);
        DeviceInfo g = g();
        if (g != null) {
            this.j = g.getSecurityDeviceId();
        }
        e(this.i);
        if (g != null && kcc.a(g) && g.getDeviceConnectState() == 2) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = g;
            this.p.sendMessageDelayed(obtain, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.l = 0L;
        GpsParameter gpsParameter = this.m;
        if (gpsParameter == null) {
            return;
        }
        if (gpsParameter.getGpsInfoBitmap() > 15) {
            ReleaseLogUtil.e("Track_HwGpsLocationManager", "bitmap not support lat,is emui5.0:", Boolean.valueOf(CommonUtil.aw()));
            if (!CommonUtil.aw() && !CommonUtil.bf()) {
                a(124004);
                return;
            }
        }
        if (kch.d(this.f)) {
            ReleaseLogUtil.e("Track_HwGpsLocationManager", "device enable set gps");
            a(100000);
            if (EmuiBuild.f13113a == 21 || ((CommonUtil.be() && CommonUtil.bn()) || EnvironmentInfo.r())) {
                Intent intent = new Intent();
                this.q = intent;
                intent.setClassName(this.f, "com.huawei.healthcloud.plugintrack.service.KeepForegroundService");
                this.q.putExtra("isStop", false);
                this.q.putExtra("stringKey", o());
                this.q.putExtra("id", "HwGpsLocationManager");
                bNb_(this.q);
                this.r = true;
            }
            ktg.c().e(this.v, "wearSportTrackLocation");
            e();
            this.t = null;
            kkl.a(true);
            a(true);
            return;
        }
        ReleaseLogUtil.d("Track_HwGpsLocationManager", "gps not open");
        a(124003);
        a(false);
    }

    private int o() {
        int d2 = kch.d();
        if (d2 == 1) {
            return R$string.IDS_gps_band_location_loading;
        }
        if (d2 == 2) {
            return R$string.IDS_gps_watches_location_loading;
        }
        return R$string.IDS_gps_location_loading;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        f();
        ReleaseLogUtil.e("Track_HwGpsLocationManager", "stopService LocationChanged :", Integer.valueOf(this.y.size()), ", LocationError:", Integer.valueOf(this.w.size()));
        synchronized (b) {
            this.y.clear();
            this.w.clear();
        }
        if (this.r) {
            this.r = false;
            this.q.putExtra("isStop", true);
            bNb_(this.q);
        }
    }

    private void n() {
        if (this.f == null) {
            LogUtil.h("HwGpsLocationManager", "killProcessStopService failed. context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f, "com.huawei.healthcloud.plugintrack.service.KeepForegroundService");
        intent.putExtra("isStop", true);
        intent.putExtra("stringKey", o());
        intent.putExtra("id", "HwGpsLocationManager");
        bNb_(intent);
    }

    private void bNb_(Intent intent) {
        Context context = this.f;
        if (context == null || intent == null) {
            LogUtil.h("HwGpsLocationManager", "startServiceForeground failed. context is null or intent is null");
            return;
        }
        try {
            context.startForegroundService(intent);
        } catch (Exception e2) {
            ReleaseLogUtil.c("Track_HwGpsLocationManager", "startForeground Exception ", ExceptionUtils.d(e2));
        }
    }

    private boolean m() {
        List<ActivityManager.RunningServiceInfo> runningServices;
        Object systemService = BaseApplication.getContext().getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (activityManager != null && (runningServices = activityManager.getRunningServices(30)) != null) {
            Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
            while (it.hasNext()) {
                if ("com.huawei.healthcloud.plugintrack.service.KeepForegroundService".equals(it.next().service.getClassName())) {
                    LogUtil.a("HwGpsLocationManager", "service is running!!!");
                    return true;
                }
            }
        }
        return false;
    }

    public static kcg b() {
        kcg kcgVar;
        synchronized (c) {
            if (d == null) {
                d = new kcg(BaseApplication.getContext());
            }
            kcgVar = d;
        }
        return kcgVar;
    }

    public void j() {
        this.f.unregisterReceiver(this.h);
        BroadcastManager.wx_(this.g);
        kcj kcjVar = this.p;
        if (kcjVar != null) {
            kcjVar.removeCallbacksAndMessages(null);
        }
        h();
    }

    private static void h() {
        synchronized (c) {
            d = null;
        }
    }

    private DeviceInfo g() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwGpsLocationManager");
        if (deviceList.size() != 0) {
            LogUtil.a("HwGpsLocationManager", "getCurrentDeviceInfo() deviceList.size() ", Integer.valueOf(deviceList.size()));
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo.getDeviceActiveState() == 1) {
                    return deviceInfo;
                }
            }
            LogUtil.h("HwGpsLocationManager", "getCurrentDeviceInfo() device's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
            return null;
        }
        LogUtil.h("HwGpsLocationManager", "getCurrentDeviceInfo() deviceInfoList is null");
        return null;
    }

    private void d(DeviceCommand deviceCommand) {
        blt.d("HwGpsLocationManager", deviceCommand.getDataContent(), "sendCommand deviceCommand ");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (kcc.e()) {
            LogUtil.a("HwGpsLocationManager", "getGpsParameter");
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(24);
            deviceCommand.setCommandID(1);
            String d2 = cvx.d(0);
            String e2 = cvx.e(129);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e2);
            sb.append(d2);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            d(deviceCommand);
            kcc.e().add(iBaseResponseCallback);
        }
    }

    public void a(int i) {
        synchronized (kcc.e()) {
            LogUtil.a("HwGpsLocationManager", "setGpsStatus");
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(24);
            deviceCommand.setCommandID(2);
            String b2 = cvx.b(i);
            String e2 = cvx.e(b2.length() / 2);
            String e3 = cvx.e(127);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e3);
            sb.append(e2);
            sb.append(b2);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            d(deviceCommand);
        }
    }

    public void c(List<GpsStruct> list, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        LogUtil.a("HwGpsLocationManager", "gpsStructs.size() ", Integer.valueOf(list.size()));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(24);
        deviceCommand.setCommandID(3);
        byte[] b2 = kcc.b(list, z);
        byte[] c2 = cvx.c(b2.length);
        byte[] f = cvx.f(129);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c2.length + b2.length);
        allocate.put(f).put(c2).put(b2);
        deviceCommand.setDataLen(allocate.capacity());
        deviceCommand.setDataContent(allocate.array());
        d(deviceCommand);
        kcc.b().add(iBaseResponseCallback);
    }

    public void c(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        String e2;
        String e3;
        String e4;
        synchronized (kcc.a()) {
            LogUtil.a("HwGpsLocationManager", "setGpsSupportParameter isEmui: ", Boolean.valueOf(z));
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(24);
            deviceCommand.setCommandID(4);
            if (z) {
                e2 = cvx.e(1);
                e3 = cvx.e(255);
                e4 = cvx.e(1);
            } else {
                e2 = cvx.e(0);
                e3 = cvx.e(15);
                e4 = cvx.e(1);
            }
            String e5 = cvx.e(1);
            String e6 = cvx.e(1);
            String e7 = cvx.e(2);
            StringBuilder sb = new StringBuilder(16);
            sb.append(e5);
            sb.append(e4);
            sb.append(e3);
            sb.append(e7);
            sb.append(e6);
            sb.append(e2);
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            d(deviceCommand);
            kcc.a().add(iBaseResponseCallback);
        }
    }

    private void e(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (kcc.d()) {
            kcc.d().add(iBaseResponseCallback);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("HwGpsLocationManager", bArr, "getResult(): ");
        if (deviceInfo == null) {
            LogUtil.a("HwGpsLocationManager", "getResult dev is null ");
        } else {
            kch.a(bArr, deviceInfo);
        }
    }

    public final void d(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability;
        DeviceCapability deviceCapability = (deviceInfo == null || (queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwGpsLocationManager")) == null || queryDeviceCapability.isEmpty()) ? null : queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        boolean isSupportGpsSetParameter = deviceCapability != null ? deviceCapability.isSupportGpsSetParameter() : false;
        LogUtil.a("HwGpsLocationManager", "isSupportGPSSetParamet:", Boolean.valueOf(isSupportGpsSetParameter), ",is EMUI5.0:", Boolean.valueOf(CommonUtil.aw()));
        if (isSupportGpsSetParameter) {
            if (CommonUtil.aw() || CommonUtil.bf()) {
                c(true, this.u);
                return;
            } else {
                c(false, this.u);
                return;
            }
        }
        d(this.n);
    }

    public void i() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwGpsLocationManager");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        if (this.s) {
            if (deviceInfo == null || !this.j.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                ReleaseLogUtil.d("Track_HwGpsLocationManager", "unregisterLocationCallback");
                kkl.a(false);
                ktg.c().a("wearSportTrackLocation");
                this.s = false;
                k();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0029, code lost:
    
        if (com.huawei.hwcommonmodel.application.BaseApplication.getContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r11) {
        /*
            r10 = this;
            java.lang.String r0 = "is open:"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r11)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "Track_HwGpsLocationManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            java.lang.String r0 = "HwGpsLocationManager"
            if (r11 == 0) goto L4e
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.NoSuchMethodError -> L2d
            java.lang.String r3 = "android.permission.ACCESS_COARSE_LOCATION"
            int r2 = r2.checkSelfPermission(r3)     // Catch: java.lang.NoSuchMethodError -> L2d
            if (r2 != 0) goto L2b
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.NoSuchMethodError -> L2d
            java.lang.String r3 = "android.permission.ACCESS_FINE_LOCATION"
            int r2 = r2.checkSelfPermission(r3)     // Catch: java.lang.NoSuchMethodError -> L2d
            if (r2 == 0) goto L4e
        L2b:
            r2 = 0
            goto L4f
        L2d:
            r2 = move-exception
            java.lang.String r3 = r2.getMessage()
            java.lang.String r4 = "checkPermission:"
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r4)
            java.lang.String r2 = r2.getMessage()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            defpackage.sqo.i(r2)
        L4e:
            r2 = 1
        L4f:
            if (r11 == 0) goto L53
            if (r2 != 0) goto La6
        L53:
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = "currentTime is:"
            java.lang.Long r5 = java.lang.Long.valueOf(r2)
            java.lang.String r6 = ";showTime is:"
            long r7 = r10.o
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            java.lang.String r8 = " ;sIsForeground: "
            boolean r11 = defpackage.kcg.f14276a
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r11)
            java.lang.Object[] r11 = new java.lang.Object[]{r4, r5, r6, r7, r8, r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r11)
            boolean r11 = defpackage.kcg.f14276a
            if (r11 != 0) goto L79
            return
        L79:
            long r4 = r10.o
            long r4 = r2 - r4
            r6 = 7200000(0x6ddd00, double:3.5572727E-317)
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 <= 0) goto La6
            java.lang.String r11 = "is over 2hours"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            r10.o = r2
            android.content.Intent r11 = new android.content.Intent
            java.lang.String r0 = "com.huawei.bone.action.open_gps"
            r11.<init>(r0)
            java.lang.String r0 = com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage()
            r11.setPackage(r0)
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            java.lang.String r1 = health.compact.a.LocalBroadcast.c
            r0.sendOrderedBroadcast(r11, r1)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kcg.a(boolean):void");
    }

    public void e(int i) {
        if (this.p != null) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.arg1 = i;
            this.p.sendMessage(obtain);
        }
    }

    public void e() {
        if (EnvironmentInfo.r()) {
            LogUtil.a("HwGpsLocationManager", "PowerKitManager applyPowerKit connectGps request");
            PowerKitManager.e().d("HwGpsLocationManager", 65535, 600000L, "user-ConnectGps");
            this.p.removeMessages(4);
            this.p.sendEmptyMessageDelayed(4, 480000L);
            LogUtil.a("HwGpsLocationManager", "PowerKitManager applyPowerKit connectGps request end");
        }
    }

    public void f() {
        if (EnvironmentInfo.r()) {
            LogUtil.a("HwGpsLocationManager", "PowerKitManager unApplyPowerKit connectGps request");
            PowerKitManager.e().e("HwGpsLocationManager", 65535, "user-ConnectGps");
            this.p.removeMessages(4);
            LogUtil.a("HwGpsLocationManager", "PowerKitManager unApplyPowerKit connectGps request end");
        }
    }
}
