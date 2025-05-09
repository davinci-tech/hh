package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseDeviceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kdq extends HwBaseManager implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private String f14307a;
    private boolean b;
    private boolean c;
    private e d;
    private long e;
    private List<Integer> f;
    private long g;
    private int h;
    private int i;
    private boolean j;
    private kdp k;
    private List<HiHealthData> l;
    private kdr m;
    private List<kdp> n;
    private int o;
    private List<HiHealthData> p;
    private List<Integer> q;
    private int r;
    private int s;
    private int t;
    private kdw u;
    private List<kdw> w;
    private kdu x;

    static class b {
        static final kdq b = new kdq(BaseApplication.getContext());
    }

    private kdq(Context context) {
        super(context);
        this.e = 0L;
        this.g = 0L;
        this.j = false;
        this.f14307a = "";
        this.x = new kdu();
        this.w = new ArrayList(16);
        this.u = new kdw();
        this.q = new ArrayList(16);
        this.m = new kdr();
        this.n = new ArrayList(16);
        this.k = new kdp();
        this.f = new ArrayList(16);
        this.b = false;
        this.c = false;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "HwStressMgr Constructor context.");
        this.d = new e(this);
        new kdt().e(this);
    }

    public static kdq c() {
        return b.b;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr.length < 2) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "data is invalid");
            return;
        }
        LogUtil.a("HwStressMgr", "getResult :", cvx.d(bArr));
        byte b2 = bArr[1];
        if (b2 == 1) {
            b(bArr, deviceInfo);
            return;
        }
        if (b2 == 2) {
            d(bArr, deviceInfo);
            return;
        }
        if (b2 == 3) {
            e(bArr, deviceInfo);
        } else if (b2 == 4) {
            a(bArr, deviceInfo);
        } else {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "other CommandId");
        }
    }

    class e extends Handler {
        WeakReference<kdq> d;

        e(kdq kdqVar) {
            super(Looper.getMainLooper());
            this.d = new WeakReference<>(kdqVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.d.get() == null) {
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "manager is null.");
                return;
            }
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "message.what :", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                kdq.this.j = false;
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "mStressCallback is null.");
            } else if (i == 1) {
                kdq.this.i();
            } else {
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "handleMessage default.");
            }
        }
    }

    private void g() {
        this.p = new ArrayList(16);
        this.l = new ArrayList(16);
    }

    public void a() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "toSyncStressDetailData enter");
        jrq.b("HwStressMgr", new c());
    }

    class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "SyncStressDetailDataRunnable run enter");
            kdq.this.o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "syncStressDetailDataStart enter");
        LogUtil.a("HwStressMgr", "hiHealthVersionCode :", Integer.valueOf(HiHealthManager.d(BaseApplication.getContext()).getHiHealthVersionCode()));
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        DeviceCapability capability = HwExerciseDeviceUtil.getCapability();
        if (capability != null && capability.isSupportStress()) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("syncStress", "begin sync data.");
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "support stress , begin to sync data.");
            if (currentDeviceInfo == null || currentDeviceInfo.getDeviceConnectState() != 2) {
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "no device is connected.");
                return;
            } else {
                d(currentDeviceInfo);
                return;
            }
        }
        ReleaseLogUtil.d("BTSYNC_HwStressMgr", "Capability is null or don't support stress!");
    }

    private void d(DeviceInfo deviceInfo) {
        if (this.j) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "mIsSyncing,please wait, return.");
            return;
        }
        h();
        this.j = true;
        this.e = System.currentTimeMillis();
        this.g = d();
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mCurrentTime :", Long.valueOf(this.e), ", mLastSyncTime :", Long.valueOf(this.g));
        long j = this.g;
        if (j == 0 || this.e - j > 604800000) {
            this.g = this.e - 604800000;
        }
        int i = (int) (this.g / 1000);
        int i2 = (int) (this.e / 1000);
        this.d.sendEmptyMessageDelayed(0, 180000L);
        kdv.e(i, i2, deviceInfo);
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwStressMgr", "enter processStressFrameIndexList(), data :", cvx.d(bArr));
        if (e(bArr) && bArr[2] == Byte.MAX_VALUE) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "processStressFrameIndexList return errorCode :", Integer.valueOf(ked.c(bArr)));
            e eVar = this.d;
            if (eVar != null) {
                eVar.removeMessages(0);
            }
            this.j = false;
            return;
        }
        kdu a2 = ked.a(bArr);
        this.x = a2;
        LogUtil.a("HwStressMgr", "mStressRecordIndexList :", a2.toString());
        int c2 = this.x.c();
        this.s = c2;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "stress frameCount :", Integer.valueOf(c2));
        List<Integer> e2 = this.x.e();
        this.q = e2;
        if (this.s <= 0 || koq.b(e2)) {
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "else : no stress frame index.start to sync relax data.");
            e(deviceInfo);
            return;
        }
        LogUtil.a("HwStressMgr", "stress indexList :", this.q.toString());
        int size = this.q.size();
        this.t = size;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mStressIndexListSize :", Integer.valueOf(size));
        this.r = 0;
        kdv.b(this.q.get(0).intValue(), deviceInfo);
    }

    private void d(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwStressMgr", "enter processStressRecordDetail(), data :", cvx.d(bArr));
        if (e(bArr) && bArr[2] == Byte.MAX_VALUE) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "processStressRecordDetail return errorCode :", Integer.valueOf(ked.c(bArr)));
            e eVar = this.d;
            if (eVar != null) {
                eVar.removeMessages(0);
            }
            this.j = false;
            return;
        }
        kdw d = ked.d(bArr);
        this.u = d;
        LogUtil.a("HwStressMgr", "mStressRecordDetails :", d.toString());
        this.w.add(this.u);
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mStressIndexProcessed :", Integer.valueOf(this.r));
        int i = this.r + 1;
        this.r = i;
        if (i < this.s && i < this.q.size()) {
            kdv.b(this.q.get(this.r).intValue(), deviceInfo);
        } else {
            LogUtil.a("HwStressMgr", "mStressRecordDetailsList :", this.w.toString());
            e(deviceInfo);
        }
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwStressMgr", "enter processRelaxFrameIndexList(), data :", cvx.d(bArr));
        if (e(bArr) && bArr[2] == Byte.MAX_VALUE) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "processRelaxFrameIndexList return errorCode :", Integer.valueOf(ked.c(bArr)));
            e eVar = this.d;
            if (eVar != null) {
                eVar.removeMessages(0);
            }
            this.j = false;
            return;
        }
        kdr e2 = ked.e(bArr);
        this.m = e2;
        LogUtil.a("HwStressMgr", "mRelaxRecordIndexList :", e2.toString());
        int b2 = this.m.b();
        this.h = b2;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "relax frameCount :", Integer.valueOf(b2));
        List<Integer> e3 = this.m.e();
        this.f = e3;
        if (this.h <= 0 || koq.b(e3)) {
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "no relax frame index.");
            f();
            return;
        }
        LogUtil.a("HwStressMgr", "relax indexList :", this.f.toString());
        int size = this.f.size();
        this.i = size;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mRelaxIndexListSize :", Integer.valueOf(size));
        this.o = 0;
        kdv.d(this.f.get(0).intValue(), deviceInfo);
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwStressMgr", "enter processRelaxRecordDetail(), data :", cvx.d(bArr));
        if (e(bArr) && bArr[2] == Byte.MAX_VALUE) {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "processRelaxRecordDetail return errorCode :", Integer.valueOf(ked.c(bArr)));
            e eVar = this.d;
            if (eVar != null) {
                eVar.removeMessages(0);
            }
            this.j = false;
            return;
        }
        kdp b2 = ked.b(bArr);
        this.k = b2;
        LogUtil.a("HwStressMgr", "mRelaxRecordDetails :", b2.toString());
        this.n.add(this.k);
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mRelaxIndexProcessed :", Integer.valueOf(this.o));
        int i = this.o + 1;
        this.o = i;
        if (i < this.h && i < this.f.size()) {
            kdv.d(this.f.get(this.o).intValue(), deviceInfo);
        } else {
            LogUtil.a("HwStressMgr", "mRelaxRecordDetailsList :", this.n.toString());
            f();
        }
    }

    private void e(DeviceInfo deviceInfo) {
        this.g = d();
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mCurrentTime :", Long.valueOf(this.e), ", mLastSyncTime :", Long.valueOf(this.g));
        long j = this.g;
        if (j == 0 || this.e - j > 604800000) {
            this.g = this.e - 604800000;
        }
        kdv.b((int) (this.g / 1000), (int) (this.e / 1000), deviceInfo);
    }

    public void e() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "hwStressMgr registerDeviceToHiHealth");
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            jpp.i(currentDeviceInfo);
        } else {
            ReleaseLogUtil.d("BTSYNC_HwStressMgr", "hwStressMgr registerDeviceToHiHealth deviceInfo is null");
        }
    }

    private void f() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveAllDataToHiHealth enter");
        e();
        g();
        j();
    }

    private void j() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveStressDetailsListToHiHealth enter");
        List<kdw> list = this.w;
        if (list != null && !list.isEmpty()) {
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mStressRecordDetailsList.size() :", Integer.valueOf(this.w.size()));
            Iterator<kdw> it = this.w.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(this.p);
            HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kdq.5
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i, Object obj) {
                    LogUtil.a("HwStressMgr", "insertStressDetailsListToHiHealth onResult type :", Integer.valueOf(i), ", object :", obj);
                    if (i == 0) {
                        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "insertStressDetailsListToHiHealth success");
                        kdq.this.b = true;
                    } else {
                        LogUtil.h("HwStressMgr", "insertCoreSleepStatusToHiHealth not correct object :", obj);
                        kdq.this.b = false;
                    }
                    kdq.this.d.sendEmptyMessage(1);
                }
            });
            return;
        }
        this.b = true;
        ReleaseLogUtil.d("BTSYNC_HwStressMgr", "saveStressDetailsListToHiHealth mRelaxRecordDetailsList is null.start save relax data.");
        this.d.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveRelaxDetailsListToHiHealth enter");
        List<kdp> list = this.n;
        if (list != null && !list.isEmpty()) {
            ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mRelaxRecordDetailsList.size() :", Integer.valueOf(this.n.size()));
            Iterator<kdp> it = this.n.iterator();
            while (it.hasNext()) {
                c(it.next());
            }
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(this.l);
            e(hiDataInsertOption);
            return;
        }
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveRelaxDetailsListToHiHealth mRelaxRecordDetailsList is null!end saveToHealth.");
        this.c = true;
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "mIsSaveStressSuccess :", Boolean.valueOf(this.b));
        if (this.b && this.c) {
            b(this.e);
        }
        e eVar = this.d;
        if (eVar != null) {
            eVar.removeMessages(0);
        }
        this.j = false;
    }

    private void e(HiDataInsertOption hiDataInsertOption) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: kdq.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("HwStressMgr", "insertRelaxDetailsListToHiHealth onResult type :", Integer.valueOf(i), ",object :", obj);
                if (i != 0) {
                    kdq.this.c = false;
                    LogUtil.h("HwStressMgr", "insertRelaxDetailsListToHiHealth not correct object :", obj);
                } else {
                    ReleaseLogUtil.e("BTSYNC_HwStressMgr", "insertRelaxDetailsListToHiHealth success");
                    kdq.this.c = true;
                    if (kdq.this.b) {
                        kdq kdqVar = kdq.this;
                        kdqVar.b(kdqVar.e);
                    }
                }
                if (kdq.this.d != null) {
                    kdq.this.d.removeMessages(0);
                }
                kdq.this.j = false;
            }
        });
    }

    private void b(kdw kdwVar) {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveStressDetailData enter");
        List<kdx> b2 = kdwVar.b();
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "list size :", Integer.valueOf(b2.size()));
        for (kdx kdxVar : b2) {
            long c2 = kdxVar.c();
            int d = kdxVar.d();
            int b3 = kdxVar.b();
            if (b3 == 1) {
                b(2019, c2, d);
            } else if (b3 == 2) {
                b(2020, c2, d);
            } else {
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "saveStressDetailData default");
            }
        }
    }

    private void b(int i, long j, int i2) {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "enter addToHealthStressList(): stressType :", Integer.valueOf(i), ",stressTime :", Long.valueOf(j), ",stressScore :", Integer.valueOf(i2));
        HiHealthData hiHealthData = new HiHealthData(i);
        long j2 = j * 1000;
        hiHealthData.setTimeInterval(j2, j2);
        hiHealthData.setValue(i2);
        hiHealthData.setDeviceUuid(b());
        this.p.add(hiHealthData);
    }

    private void c(kdp kdpVar) {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "saveRelaxDetailData enter");
        List<kds> d = kdpVar.d();
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "list size :", Integer.valueOf(d.size()));
        for (kds kdsVar : d) {
            int a2 = kdsVar.a();
            long b2 = kdsVar.b();
            int d2 = kdsVar.d();
            int e2 = kdsVar.e();
            if (a2 == 1) {
                b(2021, b2, d2, e2);
            } else {
                ReleaseLogUtil.d("BTSYNC_HwStressMgr", "saveRelaxDetailData default");
            }
        }
    }

    private void b(int i, long j, int i2, int i3) {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "enter addToHealthRelaxList(): relaxType :", Integer.valueOf(i), ",relaxTime :", Long.valueOf(j), "relaxLength :", Integer.valueOf(i2), ",relaxScore :", Integer.valueOf(i3));
        HiHealthData hiHealthData = new HiHealthData(i);
        long j2 = j * 1000;
        hiHealthData.setTimeInterval(j2, (i2 * 60000) + j2);
        hiHealthData.setValue(i3);
        hiHealthData.setDeviceUuid(b());
        this.l.add(hiHealthData);
    }

    private long d() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "getLastSyncTime enter");
        return new kdt().b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "setLastSyncTime time = ", Long.valueOf(j));
        new kdt().d(this, j);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 32;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.j = false;
        h();
    }

    private void h() {
        ReleaseLogUtil.e("BTSYNC_HwStressMgr", "resetAllParams enter");
        e eVar = this.d;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
        this.s = 0;
        this.t = 0;
        this.h = 0;
        this.i = 0;
        this.x = new kdu();
        this.m = new kdr();
        List<kdw> list = this.w;
        if (list != null) {
            list.clear();
        }
        List<kdp> list2 = this.n;
        if (list2 != null) {
            list2.clear();
        }
        this.b = false;
        this.c = false;
    }

    public String b() {
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            this.f14307a = currentDeviceInfo.getSecurityDeviceId();
        }
        return this.f14307a;
    }

    private boolean e(byte[] bArr) {
        return bArr.length >= 3;
    }
}
