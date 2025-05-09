package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dum {
    private static final List<DeviceInfo> d = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private List<DeviceInfo> f11856a;
    private int b;
    private IBaseResponseCallback c;
    private IBaseResponseCallback e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private IntentFilter j;
    private final Object k;
    private boolean l;
    private IBaseResponseCallback m;
    private boolean n;
    private boolean o;
    private IBaseResponseCallback q;
    private IBaseResponseCallback s;
    private BroadcastReceiver t;

    static class a {
        private static final dum b = new dum();
    }

    private dum() {
        this.j = new IntentFilter();
        this.f = false;
        this.h = false;
        this.i = false;
        this.n = false;
        this.l = false;
        this.o = false;
        this.b = 0;
        this.f11856a = new ArrayList();
        this.g = false;
        this.k = new Object();
        this.e = new IBaseResponseCallback() { // from class: dum.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "already binder");
                    dum.this.c();
                }
            }
        };
        this.s = new IBaseResponseCallback() { // from class: dum.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "syncFitnessDetailData err_code is " + i);
                LogUtil.c("HWhealthLinkage_", "syncFitnessDetailData objData is " + obj);
                if (i == 100000) {
                    LogUtil.a("HWhealthLinkage_", "syncFitnessDetailData succeed");
                    return;
                }
                LogUtil.a("HWhealthLinkage_", "syncFitnessDetailData return error");
                if (dum.this.q != null) {
                    dum.this.q.d(-1, Long.valueOf(System.currentTimeMillis()));
                }
            }
        };
        this.c = new IBaseResponseCallback() { // from class: dum.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "WearApp and HealthApp connected");
                    if (dum.this.m != null) {
                        dum.this.m.d(6, new Object());
                    }
                }
            }
        };
        dwo.d().m(new IBaseResponseCallback() { // from class: dum.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "err_code is " + i);
                synchronized (dum.this.k) {
                    if (!dum.this.g) {
                        dum.this.n();
                    }
                }
            }
        });
    }

    public static final dum d() {
        return a.b;
    }

    public boolean e() {
        boolean z;
        synchronized (this.k) {
            z = this.g;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ReleaseLogUtil.e("HWhealthLinkage_", "initMediator");
        this.g = true;
        dul.c().f();
        due.a().d();
        dty.b().d();
        dty.b().a();
        dub.a().b();
        dul.c().h();
        dul.c().k();
        dul.c().m();
        dul.c().l();
        dul.c().n();
        dul.c().o();
        dul.c().g();
        duj.b().registerStatusListener();
        dwo.d().j(this.e);
        int h = cjx.e().h();
        int d2 = h > 2 ? cjx.e().d() : 0;
        c();
        ReleaseLogUtil.e("HWhealthLinkage_", "Mediator initialized completed wearSize:", h + "  deleteNum:" + d2);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        this.m = iBaseResponseCallback;
        dwo.d().m(this.c);
    }

    public void l() {
        this.m = null;
        dwo.d().p(this.c);
    }

    public void e(int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HWhealthLinkage_", "notifyWearSDKRefreshData");
        if (this.t == null) {
            ReleaseLogUtil.e("HWhealthLinkage_", "register Receiver");
            this.j.addAction("com.huawei.health.fitness_detail_sync_success");
            this.j.addAction("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_START");
            this.j.addAction("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_SUCCESS");
            this.j.addAction("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_FAILED");
            this.j.addAction(HwExerciseConstants.WORKOUT_RECORD_SAVE_FINISH);
            this.j.addAction("com.huawei.health.fitness_detail_sync_fail");
            this.t = new BroadcastReceiver() { // from class: dum.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive broadcastReceiver");
                    if (intent != null) {
                        String action = intent.getAction();
                        if (action != null) {
                            dum.this.d(action);
                            dum.this.aai_(intent);
                            dum.this.e(action);
                            return;
                        }
                        ReleaseLogUtil.d("HWhealthLinkage_", "Mediator action is null");
                    }
                }
            };
            BroadcastManagerUtil.bFA_(cpp.a(), this.t, this.j, LocalBroadcast.c, null);
        }
        this.q = iBaseResponseCallback;
        dwo.d().t(this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (str.equals("com.huawei.health.fitness_detail_sync_success")) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive DETAIL_DATA_SYNC_SUCCEED");
            IBaseResponseCallback iBaseResponseCallback = this.q;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(1, new Object());
            }
            bnl.a().h();
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "refreshCommonData other condition");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (str.equals(HwExerciseConstants.WORKOUT_RECORD_SAVE_FINISH)) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive WORKOUT_RECORD_SAVE_FINISH");
            IBaseResponseCallback iBaseResponseCallback = this.q;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(5, new Object());
                return;
            }
            return;
        }
        if (str.equals("com.huawei.health.fitness_detail_sync_fail")) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive FITNESS_DETAIL_SYNC_FAIL");
            IBaseResponseCallback iBaseResponseCallback2 = this.q;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(-1, new Object());
                return;
            }
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "refreshOtherData other condition");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aai_(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            ReleaseLogUtil.e("HWhealthLinkage_", "refreshSleepData: action is null");
            return;
        }
        if (action.equals("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_START")) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive CORE_SLEEP_DATA_SYNC_START");
            IBaseResponseCallback iBaseResponseCallback = this.q;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(2, new Object());
                return;
            }
            return;
        }
        if (action.equals("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_SUCCESS")) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive CORE_SLEEP_DATA_SYNC_SUCCEED");
            IBaseResponseCallback iBaseResponseCallback2 = this.q;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(3, new Object());
                return;
            }
            return;
        }
        if (action.equals("com.huawei.bone.action.CORE_SLEEP_DATA_SYNC_FAILED")) {
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator receive CORE_SLEEP_DATA_SYNC_FAILED");
            if (this.q != null) {
                int i = -1;
                try {
                    i = intent.getIntExtra(OpAnalyticsConstants.ERROR_CODE, -1);
                    ReleaseLogUtil.e("HWhealthLinkage_", "refreshSleepData() errorCode:" + i);
                } catch (Exception unused) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "refreshSleepData() Exception");
                }
                this.q.d(4, Integer.valueOf(i));
                return;
            }
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "Mediator refreshSleepData other condition");
    }

    public boolean h() {
        return this.f;
    }

    public void c(boolean z) {
        this.f = z;
        bnl.a().b(this.f);
    }

    public void e(int i) {
        this.b = i;
    }

    public boolean i() {
        return this.h;
    }

    public void e(boolean z) {
        this.h = z;
    }

    public boolean j() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public boolean g() {
        return this.n;
    }

    public void d(boolean z) {
        this.n = z;
    }

    public boolean a() {
        return this.o;
    }

    public boolean f() {
        return this.l;
    }

    public List<DeviceInfo> b() {
        return this.f11856a;
    }

    public boolean c() {
        LogUtil.a("HWhealthLinkage_", "getHeartRateDeviceFromWearAPP");
        List<DeviceInfo> list = d;
        list.clear();
        boolean o = o();
        m();
        k();
        if (list.size() > 0 && this.n) {
            ReleaseLogUtil.d("HWhealthLinkage_", "getHeartRateDeviceFromWearAPP delete num;" + cjx.e().d() + "  TEMP_DEVICE_INFO_LIST:" + list.size());
            Iterator<DeviceInfo> it = list.iterator();
            while (it.hasNext()) {
                dub.a().c(it.next());
            }
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "getHeartRateDeviceFromWearAPP heartRateDevices size is " + this.f11856a.size());
        Iterator<DeviceInfo> it2 = this.f11856a.iterator();
        while (it2.hasNext()) {
            LogUtil.a("HWhealthLinkage_", "getHeartRateDeviceFromWearAPP heartRateDevices aar " + it2.next().toString());
        }
        if (this.f11856a.size() == 0) {
            ReleaseLogUtil.e("HWhealthLinkage_", "getHeartRateDeviceFromWearAPP setLinkStatus is false");
            this.f = false;
        }
        return o;
    }

    private void k() {
        DeviceInfo a2 = dwo.d().a(false);
        if (a2 != null) {
            if (!c(a2) && a2.getDeviceName() != null && a2.getSecurityUuid() != null) {
                boolean f = dwo.d().f();
                ReleaseLogUtil.e("HWhealthLinkage_", "acquireHealthDeviceInfo isSupportHeartRate = ", Boolean.valueOf(f));
                if (f) {
                    e(a2);
                    return;
                }
                return;
            }
            ReleaseLogUtil.e("HWhealthLinkage_", "device " + a2.getDeviceName() + " has already been added ");
        }
    }

    private void m() {
        DeviceInfo a2 = dwo.d().a(true);
        if (a2 != null) {
            if (!c(a2) && a2.getDeviceName() != null && a2.getSecurityUuid() != null) {
                boolean n = dwo.d().n();
                ReleaseLogUtil.e("HWhealthLinkage_", "acquireWearDeviceInfo isSupportHeartRate = ", Boolean.valueOf(n));
                if (n) {
                    e(a2);
                    return;
                }
                return;
            }
            ReleaseLogUtil.e("HWhealthLinkage_", "device " + a2.getDeviceName() + " has already been added ");
        }
    }

    private void e(DeviceInfo deviceInfo) {
        this.f11856a.add(deviceInfo);
        if (b(deviceInfo.getSecurityUuid())) {
            dub.a().a(deviceInfo.getSecurityUuid());
        } else {
            d.add(deviceInfo);
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HWhealthLinkage_", "isWearableDevice uniqueId is null.");
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HWhealthLinkage_");
        if (koq.b(deviceList)) {
            ReleaseLogUtil.d("HWhealthLinkage_", "isWearableDevice deviceInfoList is empty.");
            return false;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getSecurityUuid())) {
                return true;
            }
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "This is not the wearable device.");
        return false;
    }

    private boolean o() {
        LogUtil.a("HWhealthLinkage_", "getDeviceCapability");
        JSONObject a2 = dwo.d().a();
        if (a2 == null) {
            return false;
        }
        LogUtil.c("HWhealthLinkage_", "DeviceCapability" + a2.toString());
        try {
            boolean z = a2.has("isSupportExerciseAdvice") ? a2.getBoolean("isSupportExerciseAdvice") : false;
            boolean z2 = a2.has("isSupportWorkoutExerciseDisplayLink") ? a2.getBoolean("isSupportWorkoutExerciseDisplayLink") : false;
            if (a2.has("isSupportHeartRateInfo")) {
                this.n = a2.getBoolean("isSupportHeartRateInfo");
                ReleaseLogUtil.e("HWhealthLinkage_", "SupportHeartRateInfo is " + this.n);
            }
            if (a2.has("isSupportStressInfo")) {
                this.o = a2.getBoolean("isSupportStressInfo");
                ReleaseLogUtil.e("HWhealthLinkage_", "SupportStressInfo is " + this.o);
            }
            if (a2.has("isSupportInformCloseOrOpen")) {
                boolean z3 = a2.getBoolean("isSupportInformCloseOrOpen");
                this.l = z3;
                ReleaseLogUtil.e("HWhealthLinkage_", "isSupportInformCloseOrOpen is ", Boolean.valueOf(z3));
            }
            if (z && z2) {
                ReleaseLogUtil.e("HWhealthLinkage_", "setNewLinkStrategy is true");
                this.i = true;
                this.h = false;
            } else if (z) {
                ReleaseLogUtil.e("HWhealthLinkage_", "setOldLinkStrategy is true");
                this.h = true;
                this.i = false;
            } else {
                this.i = false;
                this.h = false;
                ReleaseLogUtil.e("HWhealthLinkage_", "both are false");
            }
            ReleaseLogUtil.e("HWhealthLinkage_", "Mediator isNewLinkStrategy:" + this.i + "  isOldLinkStrategy:" + this.h + "  isSupportHeartReport:" + this.n);
        } catch (JSONException e) {
            LogUtil.b("HWhealthLinkage_", e.getMessage());
            sqo.w("getDeviceCapability exception：" + e.getMessage());
        }
        return true;
    }

    private boolean c(DeviceInfo deviceInfo) {
        if (this.f11856a.isEmpty()) {
            return false;
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        if (TextUtils.isEmpty(securityDeviceId)) {
            return false;
        }
        Iterator<DeviceInfo> it = this.f11856a.iterator();
        while (it.hasNext()) {
            if (securityDeviceId.equals(it.next().getSecurityDeviceId())) {
                return true;
            }
        }
        return false;
    }
}
