package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Utils;
import com.huawei.watchface.api.HwWatchFaceApi;
import defpackage.jxi;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jwy extends HwBaseManager implements ParserInterface {
    private static String ag = "";
    protected long aa;
    protected jwv ab;
    protected List<jxx> ad;
    private BroadcastReceiver ae;
    private Handler af;
    private Context ah;
    private HandlerThread ai;
    private long aj;
    private boolean ak;
    private boolean al;
    private long am;
    private List<jxo> an;
    protected Map<Integer, List<IBaseResponseCallback>> b;
    protected int d;
    protected jws e;
    protected List<jxr> f;
    protected int g;
    protected long h;
    protected int i;
    protected int j;
    protected jxi.c k;
    protected int l;
    protected jxc m;
    protected int n;
    public jwz o;
    protected boolean p;
    protected boolean q;
    protected int r;
    protected boolean s;
    protected Map<String, Boolean> t;
    protected jxs u;
    protected List<jxw> v;
    protected int w;
    protected List<Integer> x;
    protected long y;
    protected int z;

    /* renamed from: a, reason: collision with root package name */
    protected static final Object f14154a = new Object();
    protected static final Object c = new Object();
    private static final Object ac = new Object();

    public jwy() {
        super(BaseApplication.getContext());
        this.f = new ArrayList(16);
        this.v = new ArrayList(16);
        this.ad = new ArrayList(16);
        this.t = new HashMap(16);
        this.b = new HashMap(16);
        this.j = 0;
        this.d = 0;
        this.l = 0;
        this.n = 0;
        this.w = 0;
        this.i = 0;
        this.z = 0;
        this.g = 0;
        this.q = false;
        this.s = false;
        this.p = true;
        this.r = 1;
        this.y = 0L;
        this.aa = 0L;
        this.h = 0L;
        this.an = new ArrayList(16);
        this.ai = null;
        this.af = null;
        this.ak = false;
        this.ae = new BroadcastReceiver() { // from class: jwy.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.h("HwBasicSyncManager", "mForegroundStatusChangeReceiver: intent is null");
                } else if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(intent.getAction())) {
                    jwy.this.bKL_(intent);
                }
            }
        };
        this.ah = BaseApplication.getContext();
        this.o = new jwz(this);
        this.e = jws.b();
        this.m = jxc.b();
        this.ab = new jwv();
        this.k = new jxi.c(this, BaseApplication.getContext().getMainLooper());
        BroadcastManager.wj_(this.ae);
        HandlerThread handlerThread = new HandlerThread("HwBasicSyncManager");
        this.ai = handlerThread;
        handlerThread.start();
        this.af = new b(this.ai.getLooper());
        jrq.b("HwBasicSyncManager", new Runnable() { // from class: jwy.1
            @Override // java.lang.Runnable
            public void run() {
                jwy.this.e.b(jwy.b());
                DeviceInfo e = jxi.e("HwBasicSyncManager");
                if (e != null && jxd.c(e.getDeviceIdentify()) != 0) {
                    LogUtil.a("HwBasicSyncManager", "deviceInfo auto Sync");
                    jwy.this.a(e);
                }
                DeviceInfo b2 = jxi.b("HwBasicSyncManager");
                if (b2 == null || jxd.c(b2.getDeviceIdentify()) == 0) {
                    return;
                }
                LogUtil.a("HwBasicSyncManager", "deviceAw70Info auto Sync");
                jwy.this.a(b2);
            }
        });
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 7;
    }

    public static String e() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        ag = accountInfo;
        if (accountInfo == null) {
            ag = "";
        }
        return ag;
    }

    public static jwy b() {
        return a.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicSyncManager", "automaticSynchronizeFitnessDetailData deviceInfo is null");
        } else {
            c(deviceInfo);
        }
    }

    public void e(DeviceInfo deviceInfo) {
        if (cvt.c(deviceInfo.getProductType())) {
            jxc jxcVar = this.m;
            if (jxcVar != null) {
                jxcVar.d(deviceInfo);
            }
        } else {
            if (!b(deviceInfo)) {
                return;
            }
            if (!jxf.e(deviceInfo.getDeviceIdentify())) {
                LogUtil.h("HwBasicSyncManager", "removeMessages");
                this.k.sendMessageDelayed(bKK_(deviceInfo), 600000L);
                return;
            }
            jxf.c(deviceInfo.getDeviceIdentify());
        }
        e(false, deviceInfo, (IBaseResponseCallback) null);
    }

    private Message bKK_(DeviceInfo deviceInfo) {
        Message obtain = Message.obtain();
        obtain.obj = deviceInfo;
        obtain.what = 1003;
        this.k.removeMessages(obtain.what);
        return obtain;
    }

    private void c(DeviceInfo deviceInfo) {
        if (!cvt.c(deviceInfo.getProductType())) {
            if (!b(deviceInfo)) {
                deviceInfo = null;
            }
        } else {
            jxc jxcVar = this.m;
            if (jxcVar != null) {
                jxcVar.d(deviceInfo);
            }
        }
        e(false, deviceInfo, (IBaseResponseCallback) null);
    }

    private boolean b(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("HwBasicSyncManager", "handleDeviceConnection connectState :", Integer.valueOf(deviceConnectState));
        if (deviceConnectState == 2) {
            jxi.e(deviceInfo);
            return true;
        }
        if (deviceConnectState != 3) {
            synchronized (f14154a) {
                this.b.clear();
            }
            this.t.remove(deviceInfo.getDeviceIdentify());
            this.s = false;
            a(300004, deviceInfo);
            if (!this.q) {
                LogUtil.a("HwBasicSyncManager", "Data sync bluetooth disconnect.");
                b(300004, deviceInfo);
                jxi.a("com.huawei.health.fitness_detail_sync_fail");
            }
            LogUtil.h("HwBasicSyncManager", "handleDeviceConnection connectState is error.");
        }
        return false;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwBasicSyncManager", "processResult data is null.");
        } else {
            this.o.d(bArr, deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bKL_(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
        boolean c2 = jxi.c();
        LogUtil.a("HwBasicSyncManager", "mForegroundStatusChangeReceiver() sIsForeground: ", Boolean.valueOf(booleanExtra), ",isSyncData:", Boolean.valueOf(c2));
        if (this.af == null) {
            HandlerThread handlerThread = new HandlerThread("HwBasicSyncManager");
            this.ai = handlerThread;
            handlerThread.start();
            this.af = new b(this.ai.getLooper());
        }
        if (booleanExtra && c2) {
            ThermalCallback.getInstance();
            this.af.postDelayed(new Runnable() { // from class: jwy.5
                @Override // java.lang.Runnable
                public void run() {
                    jwy.this.e(true, (DeviceInfo) null, (IBaseResponseCallback) null);
                }
            }, 1000L);
        }
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback, final DeviceInfo deviceInfo) {
        LogUtil.h("HwBasicSyncManager", "syncFitnessTodayData enter.");
        if (keg.b(deviceInfo)) {
            jrq.b("HwBasicSyncManager", new Runnable() { // from class: jwy.6
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (jwy.c) {
                        jwy.this.b(deviceInfo, iBaseResponseCallback);
                    }
                }
            });
            jxc jxcVar = this.m;
            if (jxcVar != null) {
                jxcVar.d(new IBaseResponseCallback() { // from class: jwy.7
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("HwBasicSyncManager", "mAw70BaseResponseCallback errorCode :", Integer.valueOf(i), ", objectData :", obj);
                    }
                });
                return;
            }
            return;
        }
        iBaseResponseCallback.d(-1, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("R_HwBasicSyncManager", "syncFitnessTodayDataRun get device info error.");
            iBaseResponseCallback.d(300004, null);
        } else {
            if (c(deviceInfo.getDeviceIdentify())) {
                ReleaseLogUtil.e("R_HwBasicSyncManager", "syncFitnessTodayDataRun data syncing.");
                iBaseResponseCallback.d(0, null);
                return;
            }
            this.o.a(4, 40000L, deviceInfo);
            this.t.put(deviceInfo.getDeviceIdentify(), true);
            d(10008, iBaseResponseCallback);
            ReleaseLogUtil.e("R_HwBasicSyncManager", "syncFitnessTodayData enter thread.");
            jxk.e(deviceInfo);
        }
    }

    public void e(boolean z, DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        boolean d;
        LogUtil.a("HwBasicSyncManager", "syncFitnessDetailData is enter");
        if (!ThermalCallback.getInstance().isTriggerTask()) {
            ReleaseLogUtil.d("R_HwBasicSyncManager", "syncFitnessDetailData does not have the conditions to trigger the task.");
            jxi.d(true);
            c(300008, iBaseResponseCallback);
            return;
        }
        jxi.d(false);
        if (z) {
            d = e(true, false, iBaseResponseCallback);
        } else {
            d = d(deviceInfo, false, iBaseResponseCallback);
        }
        if (d) {
            return;
        }
        jxi.a("com.huawei.health.fitness_detail_sync_success");
    }

    private boolean e(boolean z, boolean z2, IBaseResponseCallback iBaseResponseCallback) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwBasicSyncManager");
        if (deviceList != null && !deviceList.isEmpty()) {
            for (int i = 0; i < deviceList.size(); i++) {
                DeviceInfo deviceInfo = deviceList.get(i);
                if (deviceInfo == null) {
                    LogUtil.h("HwBasicSyncManager", "currentInfo is null");
                } else {
                    boolean d = d(deviceInfo, z, iBaseResponseCallback);
                    if (!z2) {
                        z2 = d;
                    }
                }
            }
        }
        return z2;
    }

    private boolean d(DeviceInfo deviceInfo, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicSyncManager", "syncDeviceFitnessData deviceInfo is null");
            c(300008, iBaseResponseCallback);
            return false;
        }
        ReleaseLogUtil.e("R_HwBasicSyncManager", "syncDeviceFitnessData isManualSync:", Boolean.valueOf(z));
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceIdentify, "HwBasicSyncManager");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("HwBasicSyncManager", "syncDeviceFitnessData deviceCapabilityHashMaps is null or empty");
            c(300008, iBaseResponseCallback);
            return false;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceIdentify);
        if (deviceCapability == null || !deviceCapability.isSupportSportTotal()) {
            LogUtil.h("HwBasicSyncManager", "syncDeviceFitnessData deviceCapability error");
            sqo.t("syncDeviceFitnessData deviceCapability error");
            c(300008, iBaseResponseCallback);
            c(-1, deviceInfo);
            d(deviceInfo);
            a(0, deviceInfo);
            return false;
        }
        Boolean bool = this.t.get(deviceIdentify);
        if (bool != null && bool.booleanValue()) {
            ReleaseLogUtil.e("R_HwBasicSyncManager", "syncDeviceFitnessData data syncing");
            if (iBaseResponseCallback != null) {
                this.ak = true;
            }
            c(300008, iBaseResponseCallback);
            return false;
        }
        if (cvt.c(deviceInfo.getProductType()) && this.m.d()) {
            ReleaseLogUtil.e("R_HwBasicSyncManager", "syncDeviceFitnessData aw70 data syncing.");
            c(300008, iBaseResponseCallback);
            this.ak = false;
            return false;
        }
        e(deviceInfo, z, iBaseResponseCallback);
        return true;
    }

    private void e(final DeviceInfo deviceInfo, boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwBasicSyncManager", "syncTodayCountData isManualSyn: ", Boolean.valueOf(z));
        if (iBaseResponseCallback != null) {
            this.p = false;
        } else {
            this.p = true;
        }
        final Message obtain = Message.obtain();
        obtain.what = 0;
        e(new IBaseResponseCallback() { // from class: jwy.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_HwBasicSyncManager", "syncFitnessDetailData errorCode:", Integer.valueOf(i), ", mIsSyncCloud:", Boolean.valueOf(jwy.this.ak));
                if (jwy.this.k != null) {
                    LogUtil.a("HwBasicSyncManager", "mHwBasicHandler is not null:");
                    if (jwy.this.k.hasMessages(obtain.what)) {
                        LogUtil.a("HwBasicSyncManager", "mHwBasicHandler removeMessages:", Integer.valueOf(obtain.what));
                        jwy.this.k.removeMessages(obtain.what);
                    }
                }
                if (i != 0) {
                    jwy.this.ak = false;
                    jwy.this.c(300008, iBaseResponseCallback);
                    jwy.this.c(300001, deviceInfo);
                    jwy.this.a(0, deviceInfo);
                    jxi.a("com.huawei.health.fitness_detail_sync_fail");
                } else {
                    jwy.this.c(0, iBaseResponseCallback);
                    if (jwy.this.ak) {
                        Utils.syncCloudAfterInsert();
                        jwy.this.ak = false;
                    }
                }
                ReleaseLogUtil.e("R_HwBasicSyncManager", "syncFitnessDetailData end mIsSyncCloud:", Boolean.valueOf(jwy.this.ak));
            }
        }, z, deviceInfo);
        this.k.sendMessageDelayed(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback, boolean z, final DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            LogUtil.a("HwBasicSyncManager", "syncFitnessDetailData updateSyncStatus");
            jxd.c(deviceInfo.getDeviceIdentify(), 0);
        }
        if (jxi.c(deviceInfo)) {
            if (!keg.b(deviceInfo)) {
                ReleaseLogUtil.d("R_HwBasicSyncManager", "deviceInfo checkHiHealthType fail.");
                sqo.t("deviceInfo checkHiHealthType fail");
                iBaseResponseCallback.d(-1, null);
                return;
            }
            this.al = z;
            ReleaseLogUtil.e("R_HwBasicSyncManager", "begin sync data.");
            LogUtil.a("HwBasicSyncManager", "start syncFitnessDetailData.");
            a(System.currentTimeMillis());
            this.am = System.currentTimeMillis();
            if (this.m == null) {
                this.m = jxc.b();
            }
            if (!cvt.c(deviceInfo.getProductType())) {
                c(new IBaseResponseCallback() { // from class: jwy.9
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        jwy.this.b(i, deviceInfo, iBaseResponseCallback);
                    }
                }, deviceInfo);
            } else {
                LogUtil.h("HwBasicSyncManager", "mHwFitnessAw70Manager syncFitnessDetailData");
                this.m.e(iBaseResponseCallback, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, DeviceInfo deviceInfo, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwBasicSyncManager", " today total finish need detail errorCode :", Integer.valueOf(i));
        if (this.m.e(this.am, jxi.b(b()), this.al)) {
            if (c(deviceInfo.getDeviceIdentify())) {
                this.t.remove(deviceInfo.getDeviceIdentify());
            }
            LogUtil.h("HwBasicSyncManager", "syncTodayDataResult not SyncFitnessDetailData.");
            iBaseResponseCallback.d(-1, "");
            return;
        }
        if (i != 0) {
            LogUtil.h("HwBasicSyncManager", "syncTodayDataResult error");
            iBaseResponseCallback.d(-1, null);
        } else {
            e(iBaseResponseCallback, deviceInfo);
        }
    }

    private void e(final IBaseResponseCallback iBaseResponseCallback, final DeviceInfo deviceInfo) {
        jrq.b("HwBasicSyncManager", new Runnable() { // from class: jwy.10
            @Override // java.lang.Runnable
            public void run() {
                jwy.this.o.a(iBaseResponseCallback, true, deviceInfo);
            }
        });
    }

    protected void c(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicSyncManager", "notifyDetailSyncComplete deviceInfo is null");
            return;
        }
        if (i == 300001) {
            this.t.remove(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("HwBasicSyncManager", "notifyDetailSyncComplete errorCode :", Integer.valueOf(i), ", currentStage :", Integer.valueOf(this.ab.c()));
        if (this.ab.c() == 2) {
            d(0);
            if (i == 0) {
                f(2, deviceInfo);
            } else {
                this.t.remove(deviceInfo.getDeviceIdentify());
                c(10009, i);
            }
        }
        if (this.ab.c() == 1) {
            if (i == 0) {
                f(1, deviceInfo);
                a(100000, deviceInfo);
            } else {
                d(deviceInfo);
            }
        }
    }

    private void d(DeviceInfo deviceInfo) {
        if (CommonUtil.as()) {
            sqo.d("fitnessDataSync Complete,Sync duration: " + (System.currentTimeMillis() - this.am));
        }
        ReleaseLogUtil.e("R_HwBasicSyncManager", "afterFitnessDataSync enter.");
        kee.c().a(deviceInfo);
        kej.e().b(deviceInfo);
        LogUtil.a("HwBasicSyncManager", "afterFitnessDataSync mIsSyncWorkout: ", Boolean.valueOf(this.p));
        if (this.p) {
            jwx.c();
        }
    }

    private void f(final int i, final DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "saveFitnessDate.");
        boolean checkHiHealthServiceExist = HiHealthManager.d(this.ah).checkHiHealthServiceExist();
        LogUtil.a("HwBasicSyncManager", "saveFitnessDate isExistHiHealthService :", Boolean.valueOf(checkHiHealthServiceExist));
        if (checkHiHealthServiceExist) {
            this.o.a(5, 120000L, deviceInfo);
            this.q = true;
            jxi.e(deviceInfo);
            final ArrayList arrayList = new ArrayList(16);
            final ArrayList arrayList2 = new ArrayList(16);
            arrayList.addAll(this.v);
            arrayList2.addAll(this.ad);
            this.v.clear();
            this.ad.clear();
            final ArrayList arrayList3 = new ArrayList(16);
            arrayList3.addAll(this.an);
            this.an.clear();
            final int b2 = jxi.b(deviceInfo);
            jrq.b("HwBasicSyncManager", new Runnable() { // from class: jwy.15
                @Override // java.lang.Runnable
                public void run() {
                    if (b2 == 3) {
                        jwy.this.e.c(jwy.b(), arrayList, arrayList2, i, deviceInfo);
                    } else {
                        jwy.this.e.a(jwy.b(), arrayList3, i, deviceInfo);
                    }
                }
            });
            return;
        }
        b(300001, deviceInfo);
    }

    public void d(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "doDetailSyncComplete errorCode :", Integer.valueOf(i));
        b(i, deviceInfo);
    }

    public void b(final int i, final DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwBasicSyncManager", "processDetailSyncComplete deviceInfo is null");
        } else {
            LogUtil.a("HwBasicSyncManager", "processDetailSyncComplete errorCode :", Integer.valueOf(i));
            jrq.b("HwBasicSyncManager", new Runnable() { // from class: jwy.2
                @Override // java.lang.Runnable
                public void run() {
                    jwy.this.h(i, deviceInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "syncCompleteRun errorCode :", Integer.valueOf(i));
        DeviceCapability c2 = jxi.c(deviceInfo.getDeviceIdentify());
        synchronized (ac) {
            c(10009, i);
            if (this.ab.c() == 1) {
                d(5);
                if (c2 != null && c2.isSupportGetHighAndMiddleSport()) {
                    this.o.d(1, deviceInfo);
                    jxi.a("com.huawei.health.fitness_detail_sync_success");
                    ReleaseLogUtil.e("R_HwBasicSyncManager", "startSyncAlarm");
                    d(deviceInfo);
                }
                LogUtil.h("HwBasicSyncManager", "syncCompleteRun not support highAndMiddleSport.");
                f(deviceInfo);
                jxi.a("com.huawei.health.fitness_detail_sync_success");
                ReleaseLogUtil.e("R_HwBasicSyncManager", "startSyncAlarm");
                d(deviceInfo);
            } else {
                if (c2 != null && c2.isSupportGetHighAndMiddleSport()) {
                    LogUtil.a("HwBasicSyncManager", "syncCompleteRun syncSecondStageFitnessDetail");
                    this.o.d(7, deviceInfo);
                    d(5);
                    this.t.remove(deviceInfo.getDeviceIdentify());
                    this.q = false;
                }
                LogUtil.h("HwBasicSyncManager", "syncCompleteRun not support high and middle sport.");
                d(5);
                this.t.remove(deviceInfo.getDeviceIdentify());
                this.q = false;
            }
        }
    }

    private void f(DeviceInfo deviceInfo) {
        synchronized (c) {
            this.ab.d(2);
            if (jxi.b(deviceInfo) != 3 && deviceInfo != null) {
                LogUtil.a("HwBasicSyncManager", "syncSecondStageFitnessDetail not separated data.");
                this.t.remove(deviceInfo.getDeviceIdentify());
                return;
            }
            long a2 = this.ab.a(b());
            long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            LogUtil.a("HwBasicSyncManager", "lastSecondStageTime:", Long.valueOf(a2), ",endSecondStageTime:", Long.valueOf(currentTimeMillis));
            if (a2 >= jxi.d(currentTimeMillis)) {
                LogUtil.a("HwBasicSyncManager", "syncSecondStageFitnessDetail sync sample second stage already ok.");
                this.o.b(deviceInfo);
            } else {
                if (currentTimeMillis - a2 > k.b.l) {
                    a2 = jxi.d(currentTimeMillis - k.b.l);
                }
                this.y = currentTimeMillis;
                jxk.a(a2, currentTimeMillis);
            }
        }
    }

    protected void e(jxs jxsVar, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "getRealtimeDataByFrame Enter.");
        this.x = jxsVar.c();
        int e = jxsVar.e();
        this.l = e;
        this.n = 0;
        List<Integer> list = this.x;
        if (list != null && e > 0) {
            LogUtil.a("HwBasicSyncManager", "getRealtimeDataByFrame get frame index :", list.get(0));
            jxi.b(this.x.get(this.n).intValue(), deviceInfo);
        } else {
            c(0, deviceInfo);
        }
    }

    private void d(int i) {
        jxi.c cVar = this.k;
        if (cVar == null || !cVar.hasMessages(i)) {
            return;
        }
        this.k.removeMessages(i);
    }

    public void e(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "processIntensiveSyncComplete errorCode: ", Integer.valueOf(i), ", intensiveFlag: ", Integer.valueOf(this.r));
        this.s = false;
        if (this.r == 1) {
            f(deviceInfo);
        }
    }

    protected void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (f14154a) {
            if (iBaseResponseCallback != null) {
                e(i, iBaseResponseCallback);
            }
        }
    }

    private void e(int i, IBaseResponseCallback iBaseResponseCallback) {
        List<IBaseResponseCallback> list = this.b.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>(16);
            this.b.put(Integer.valueOf(i), list);
        } else {
            for (IBaseResponseCallback iBaseResponseCallback2 : list) {
                if (iBaseResponseCallback2 != null && iBaseResponseCallback2.equals(iBaseResponseCallback)) {
                    return;
                }
            }
        }
        list.add(iBaseResponseCallback);
    }

    private void c(int i, int i2) {
        LogUtil.a("HwBasicSyncManager", "processCallback callback commandId :", Integer.valueOf(i), ", errorCode :", Integer.valueOf(i2));
        synchronized (f14154a) {
            d(i, i2);
        }
    }

    private void d(int i, int i2) {
        List<IBaseResponseCallback> list = this.b.get(Integer.valueOf(i));
        if (list != null) {
            int i3 = 0;
            while (list.size() > 0) {
                IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                if (iBaseResponseCallback != null) {
                    list.remove(i3);
                    iBaseResponseCallback.d(i2, null);
                    return;
                } else {
                    list.remove(i3);
                    i3++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, "");
        }
    }

    public boolean d() {
        return this.al;
    }

    public void a(long j) {
        this.aj = j;
    }

    protected void e(jxo jxoVar) {
        LogUtil.a("HwBasicSyncManager", "saveRealtimeDataFrame enter.");
        this.an.add(jxoVar);
    }

    protected void c() {
        this.an.clear();
        this.v.clear();
        this.ad.clear();
    }

    private boolean c(String str) {
        Boolean bool = false;
        if (this.t.containsKey(str)) {
            bool = this.t.get(str);
        }
        return bool != null && bool.booleanValue();
    }

    protected void a(final int i, final DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicSyncManager", "sendFitnessSyncEvent result :", Integer.valueOf(i));
        ThreadPoolManager.d().d("HwBasicSyncManager", new Runnable() { // from class: jwy.4
            @Override // java.lang.Runnable
            public void run() {
                jra.b(i, jwy.this.al, jwy.this.am, deviceInfo);
            }
        });
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.ai.quit();
        this.ai = null;
        BroadcastManager.wx_(this.ae);
    }

    static class a {
        private static final jwy b = new jwy();
    }
}
