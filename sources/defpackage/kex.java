package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.util.GmsVersion;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class kex extends EngineLogicBaseManager {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DeviceInfo f14330a;
    private IBaseResponseCallback b;
    private c d;
    private boolean e;
    private List<kbn> f;
    private AtomicInteger g;
    private List<kbo> h;
    private boolean i;
    private List<Integer> j;
    private cwl k;
    private long m;
    private long n;
    private ExtendHandler o;

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.h("DataDictionarySyncManager", "SyncMgsHandler handleMessage msg: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                kex.this.o.removeMessages(1);
                kex kexVar = kex.this;
                kexVar.b(kexVar.b());
                return true;
            }
            if (i == 3) {
                kex.this.d();
                return true;
            }
            if (i == 4) {
                kex.this.i();
                return true;
            }
            LogUtil.h("DataDictionarySyncManager", "SyncMgsHandler default");
            return false;
        }
    }

    protected kex(Context context) {
        super(context);
        this.k = new cwl();
        this.i = false;
        this.e = false;
        this.n = 0L;
        this.m = 0L;
        this.j = new CopyOnWriteArrayList();
        this.h = new CopyOnWriteArrayList();
        this.f = new CopyOnWriteArrayList();
        this.d = new c();
        this.g = new AtomicInteger(0);
        this.o = HandlerCenter.yt_(this.d, "DataDictionarySyncManager");
    }

    public void a(DeviceInfo deviceInfo, keu keuVar, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("DataDictionarySyncManager", "initSyncData callback is null");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("DataDictionarySyncManager", "initSyncData deviceInfo is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("DataDictionarySyncManager", "initSyncData device is not connected");
            iBaseResponseCallback.d(-1, null);
        }
        if (this.i) {
            return;
        }
        this.j.clear();
        if (keuVar == null) {
            return;
        }
        this.g.set(0);
        int b = keuVar.b();
        LogUtil.a("DataDictionarySyncManager", "initSyncData deviceInfo:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " syncType:", Integer.valueOf(b));
        kbk.c();
        if (b == -1) {
            c(deviceInfo);
            this.e = false;
        } else {
            b(deviceInfo, keuVar);
            this.e = true;
        }
        Iterator<Integer> it = this.j.iterator();
        while (it.hasNext()) {
            LogUtil.a("DataDictionarySyncManager", "to be synchronized:", Integer.valueOf(it.next().intValue()));
        }
        if (this.j.size() == 0) {
            iBaseResponseCallback.d(0, null);
            return;
        }
        this.f14330a = deviceInfo;
        LogUtil.a("DataDictionarySyncManager", "initSyncData id:", this.j.get(0));
        e(iBaseResponseCallback);
    }

    private void c(DeviceInfo deviceInfo) {
        for (Map.Entry<Integer, Integer> entry : kbk.d().entrySet()) {
            int intValue = entry.getValue().intValue();
            if (cwi.c(deviceInfo, intValue)) {
                this.j.add(entry.getKey());
            } else {
                LogUtil.a("DataDictionarySyncManager", "initSyncData capability:", Integer.valueOf(intValue), " not support");
            }
        }
    }

    private void b(DeviceInfo deviceInfo, keu keuVar) {
        Iterator<kbm> it = keuVar.c().iterator();
        while (it.hasNext()) {
            int d = it.next().d();
            LogUtil.a("DataDictionarySyncManager", "addSingleSyncList dictTypeId:", Integer.valueOf(d));
            int d2 = kbk.d(d);
            if (cwi.c(deviceInfo, d2)) {
                this.j.add(Integer.valueOf(d));
            } else {
                LogUtil.a("DataDictionarySyncManager", "initSyncData capability:", Integer.valueOf(d2), " not support");
            }
        }
    }

    public static kex a() {
        return a.e;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        byte[] b = spnVar.b();
        blt.d("DataDictionarySyncManager", b, "start ");
        if (b == null) {
            return;
        }
        byte b2 = b[0];
        if (b2 == 1) {
            LogUtil.a("DataDictionarySyncManager", "tlv ");
            b(b, deviceInfo);
        } else if (b2 == 2) {
            LogUtil.a("DataDictionarySyncManager", "Json ");
        } else if (b2 == 3) {
            LogUtil.a("DataDictionarySyncManager", "PB ");
        } else {
            b(b, deviceInfo);
        }
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        String substring = cvx.d(bArr).substring(2);
        if (TextUtils.isEmpty(substring) || substring.length() <= 4) {
            return;
        }
        LogUtil.a("DataDictionarySyncManager", "TLV:", substring);
        try {
            cwe a2 = this.k.a(substring);
            List<cwd> e = a2.e();
            if (e != null && e.size() >= 2) {
                c(CommonUtil.w(e.get(0).e()), e.get(0).c(), a2, deviceInfo);
                if (CommonUtil.as()) {
                    iyv.c("DataSyncSpeed", "Point DataDict dataSize: " + bArr.length + ", duration: " + (System.currentTimeMillis() - this.m) + ", transferSpeed: " + ((bArr.length * 1.0d) / (r0 * 1024)));
                }
            }
        } catch (cwg unused) {
            LogUtil.b("DataDictionarySyncManager", "processTlvData TlvException");
            sqo.h("processTlvData TlvException");
        }
    }

    private void c(int i, String str, cwe cweVar, DeviceInfo deviceInfo) {
        if (i == 1) {
            if (CommonUtil.w(str) == 1) {
                kbn kbnVar = (kbn) a(1, cweVar);
                if (kbnVar == null) {
                    return;
                }
                kbnVar.e(deviceInfo);
                b(kbnVar);
                return;
            }
            LogUtil.h("DataDictionarySyncManager", "processTlvData unrecognized message type");
            sqo.h("processTlvData unrecognized message type");
        }
    }

    private void b(kbn kbnVar) {
        LogUtil.h("DataDictionarySyncManager", "processDataSync enter");
        if (a(kbnVar)) {
            a(true, kbnVar);
        } else {
            if (d(kbnVar)) {
                return;
            }
            e(kbnVar);
        }
    }

    private boolean d(kbn kbnVar) {
        boolean z;
        Iterator<kbn> it = this.f.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            kbn next = it.next();
            if (next.a() != null && next.a().get(0) != null) {
                if (next.c() == kbnVar.c() && next.a().get(0).e() == kbnVar.a().get(0).e()) {
                    if (kbnVar.c() != DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                        break;
                    }
                    if (next.a().get(0).b() == kbnVar.a().get(0).b()) {
                        LogUtil.h("DataDictionarySyncManager", "checkRepeatMsg Cache and real-time data are equal.");
                        break;
                    }
                }
            } else {
                break;
            }
        }
        z = true;
        if (!z) {
            this.f.add(kbnVar);
            g();
        }
        return z;
    }

    private boolean a(kbn kbnVar) {
        if (kbnVar == null) {
            return true;
        }
        if (kbnVar.c() != -1) {
            return kbnVar.a() == null || kbnVar.a().size() <= 0;
        }
        LogUtil.a("DataDictionarySyncManager", "parsePointData data is null or valid");
        return true;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.DATA_DICTIONARY_SYNC_MODULE;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        if (this.i) {
            return;
        }
        this.b = iBaseResponseCallback;
        this.i = true;
        synchronized (c) {
            this.h.clear();
        }
        this.o.sendEmptyMessage(4);
    }

    private void b(int i, long j, long j2) {
        ReleaseLogUtil.e("R_DataDictionarySyncManager", "syncData startTime:", Long.valueOf(j), ",endTime:", Long.valueOf(j2), " ,digitTypeId: ", Integer.valueOf(i));
        if (j > j2) {
            ReleaseLogUtil.d("R_DataDictionarySyncManager", "syncData cancel,valid time");
            f();
            d();
        } else {
            kbo kboVar = (kbo) a(4, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2));
            if (kboVar == null) {
                return;
            }
            e(kboVar);
        }
    }

    private void e(kbo kboVar) {
        synchronized (c) {
            this.h.add(kboVar);
        }
        c(kboVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        pingComand(new PingCallback() { // from class: kex.2
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i) {
                LogUtil.a("DataDictionarySyncManager", "pingDevice onPingResult:", Integer.valueOf(i));
            }
        }, getWearPkgName());
        this.o.sendEmptyMessage(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int c2 = c();
        if (c2 == -1) {
            Object[] objArr = new Object[2];
            objArr[0] = "syncId is invalid, No data needs to be synchronized:";
            DeviceInfo deviceInfo = this.f14330a;
            objArr[1] = deviceInfo != null ? deviceInfo.getDeviceName() : "";
            LogUtil.h("DataDictionarySyncManager", objArr);
            if (CommonUtil.as()) {
                iyv.c("DataSyncSpeed", "Point Data-Dict sync duration: " + (System.currentTimeMillis() - this.n));
            }
            h();
            IBaseResponseCallback iBaseResponseCallback = this.b;
            this.b = null;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            } else {
                ReleaseLogUtil.d("R_DataDictionarySyncManager", "callback is null.");
                return;
            }
        }
        a(c2);
    }

    private void c(kbo kboVar) {
        if (kboVar == null) {
            return;
        }
        if (!j()) {
            f();
            d();
            return;
        }
        LogUtil.a("DataDictionarySyncManager", " send command start ");
        spn spnVar = (spn) a(7, kboVar);
        if (spnVar == null) {
            return;
        }
        if (CommonUtil.as()) {
            this.m = System.currentTimeMillis();
        }
        sendComand(spnVar, new SendCallback() { // from class: kex.4
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("DataDictionarySyncManager", "sendCommand onSendResult:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("DataDictionarySyncManager", "sendCommand onSendProgress", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("DataDictionarySyncManager", "sendComand onFileTransferReport transferWay: ", str);
            }
        });
    }

    private boolean j() {
        if (e() <= 0) {
            LogUtil.a("DataDictionarySyncManager", "startSyncData retry count is 0");
            return false;
        }
        ExtendHandler extendHandler = this.o;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1, PreConnectManager.CONNECT_INTERNAL);
        }
        return true;
    }

    static class a {
        private static kex e = new kex(BaseApplication.getContext());
    }

    private void e(final kbn kbnVar) {
        LogUtil.a("DataDictionarySyncManager", "enter processSaveToHiHealth");
        a(3, kbnVar, new HiDataOperateListener() { // from class: kex.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("R_DataDictionarySyncManager", "saveDataToHiHealth onResult type:", Integer.valueOf(i));
                if (i == 0) {
                    kex.this.i(kbnVar);
                    kex.this.g(kbnVar);
                    kex kexVar = kex.this;
                    Boolean bool = (Boolean) kexVar.a(6, kbnVar, kexVar.b());
                    if (bool == null) {
                        return;
                    }
                    if (!bool.booleanValue() && kex.this.g.incrementAndGet() >= 3) {
                        jrp.c();
                        kex.this.g.set(0);
                    }
                    kex.this.a(bool.booleanValue(), kbnVar);
                    return;
                }
                LogUtil.h("DataDictionarySyncManager", "processInsertToHiHealth error:", Integer.valueOf(i));
                kex.this.a(true, kbnVar);
            }
        });
        LogUtil.a("DataDictionarySyncManager", "insertDicData healthData:", Integer.valueOf(kbnVar.c()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(kbn kbnVar) {
        LogUtil.a("DataDictionarySyncManager", "syncCloud mIsSampleEvent: ", Boolean.valueOf(this.e));
        if (this.e) {
            n();
        } else {
            if (cwi.c(kbnVar.b(), 127) || kbnVar.c() != DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()) {
                return;
            }
            LogUtil.a("DataDictionarySyncManager", "old device bloodPressure SyncCloud");
            n();
        }
    }

    private void n() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kex.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DataDictionarySyncManager", "syncCloud start");
                HiSyncOption hiSyncOption = new HiSyncOption();
                hiSyncOption.setSyncModel(2);
                hiSyncOption.setSyncAction(0);
                hiSyncOption.setSyncDataType(20000);
                hiSyncOption.setSyncMethod(2);
                hiSyncOption.setSyncScope(1);
                HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
                LogUtil.a("DataDictionarySyncManager", "syncCloud end");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(kbn kbnVar) {
        if (kbnVar == null || kbnVar.a() == null || kbnVar.a().isEmpty()) {
            return;
        }
        kbp kbpVar = kbnVar.a().get(kbnVar.a().size() - 1);
        long b = kcb.b(kbnVar.b().getDeviceIdentify(), kbnVar.c(), GmsVersion.VERSION_LONGHORN, "DataDictionarySyncManager");
        if (kbpVar.a() > b) {
            kcb.e(kbnVar.b().getDeviceIdentify(), kbnVar.c(), kbpVar.a(), GmsVersion.VERSION_LONGHORN, "DataDictionarySyncManager");
        } else if (kbpVar.a() == 0 && kbpVar.e() > b) {
            kcb.e(kbnVar.b().getDeviceIdentify(), kbnVar.c(), kbpVar.e(), GmsVersion.VERSION_LONGHORN, "DataDictionarySyncManager");
        } else {
            LogUtil.h("DataDictionarySyncManager", "Point Sync Do not save any time");
        }
        if (kbpVar.b() == 0) {
            return;
        }
        kcb.e(kbnVar.b().getDeviceIdentify(), kbnVar.c(), kbpVar.b(), 4000000, "DataDictionarySyncManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, kbn kbnVar) {
        LogUtil.a("DataDictionarySyncManager", "enter processNextData isFinish:", Boolean.valueOf(z));
        if (z) {
            if (this.j.contains(Integer.valueOf(kbnVar.c()))) {
                this.g.set(0);
                f();
            } else {
                ReleaseLogUtil.e("R_DataDictionarySyncManager", "processNextData DictionaryInfo DigitTypeId Not in the current queue");
            }
            d();
            return;
        }
        j(kbnVar);
        c(b());
    }

    private int c() {
        List<Integer> list = this.j;
        if (list.size() <= 0) {
            return -1;
        }
        int intValue = list.get(0).intValue();
        ReleaseLogUtil.e("R_DataDictionarySyncManager", "checkSyncDataIdList validId:", Integer.valueOf(intValue));
        return intValue;
    }

    private int e() {
        kbo b = b();
        if (b == null) {
            return -1;
        }
        return b.b();
    }

    private void a(final int i) {
        LogUtil.a("DataDictionarySyncManager", "processReadHiHealth readHiHeathList digitTypeId:", Integer.valueOf(i));
        jrp.c();
        a(2, Integer.valueOf(i), this.f14330a, new HiDataReadResultListener() { // from class: kex.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a("DataDictionarySyncManager", "onResult errorCode:", Integer.valueOf(i2), ", object:", obj);
                if (obj == null) {
                    LogUtil.h("DataDictionarySyncManager", "object is null");
                    kex.this.f();
                    kex.this.d();
                    return;
                }
                LogUtil.a("DataDictionarySyncManager", "appendToExistData onResult() object:", obj);
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        kex.this.bNq_(i, sparseArray);
                        return;
                    } else {
                        LogUtil.h("DataDictionarySyncManager", "sparseArray size is zero");
                        kex.this.bNq_(i, sparseArray);
                        return;
                    }
                }
                LogUtil.h("DataDictionarySyncManager", "sparseArray !instanceof");
                kex.this.bNq_(i, null);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("DataDictionarySyncManager", "onResultIntent intentType:", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bNq_(int i, SparseArray<HiHealthData> sparseArray) {
        long max;
        long b = kcb.b(this.f14330a.getDeviceIdentify(), i, 4000000, "DataDictionarySyncManager");
        long b2 = kcb.b(this.f14330a.getDeviceIdentify(), i, GmsVersion.VERSION_LONGHORN, "DataDictionarySyncManager");
        LogUtil.a("DataDictionarySyncManager", "processReadHiHealthData modifyTime:", Long.valueOf(b), " ,pointEndTime:", Long.valueOf(b2));
        long max2 = Math.max(b, b2);
        long currentTimeMillis = System.currentTimeMillis();
        if (sparseArray == null) {
            LogUtil.h("DataDictionarySyncManager", "processReadHiHealthData readHiHeathList is null");
            b(i, max2 != 0 ? max2 + 1000 : 0L, currentTimeMillis);
            return;
        }
        if (sparseArray.size() == 0) {
            LogUtil.h("DataDictionarySyncManager", "processReadData readData is empty");
            b(i, max2 != 0 ? max2 + 1000 : 0L, currentTimeMillis);
            return;
        }
        Parcelable parcelable = sparseArray.get(i);
        if (parcelable instanceof List) {
            List list = (List) parcelable;
            kcb.a(i, list);
            HiHealthData hiHealthData = (HiHealthData) list.get(list.size() - 1);
            if (i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                max = Math.max(max2, hiHealthData.getStartTime());
            } else {
                max = Math.max(max2, hiHealthData.getEndTime());
            }
            b(i, max + 1000, currentTimeMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(kbo kboVar) {
        kbo b = b();
        if (b != null) {
            b.c(b.b() - 1);
        }
        c(kboVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        synchronized (c) {
            List<Integer> list = this.j;
            if (list != null && !list.isEmpty()) {
                list.remove(0);
                List<kbo> list2 = this.h;
                if (list2.isEmpty()) {
                    return;
                }
                list2.clear();
                this.f.clear();
                g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kbo b() {
        synchronized (c) {
            ArrayList arrayList = new ArrayList(this.h);
            if (arrayList.isEmpty()) {
                return null;
            }
            LogUtil.a("DataDictionarySyncManager", "getCacheInfo mSyncDataIdList:", Integer.valueOf(arrayList.size()), ",mSyncCacheList:", this.h);
            return (kbo) arrayList.get(0);
        }
    }

    private void j(kbn kbnVar) {
        long c2 = c(kbnVar);
        synchronized (c) {
            if (this.h.isEmpty()) {
                return;
            }
            LogUtil.a("DataDictionarySyncManager", "updateCacheInfo nextStartTime:", Long.valueOf(c2));
            this.h.get(0).a(c2 + 1000);
            this.h.get(0).c(3);
        }
    }

    private long c(kbn kbnVar) {
        if (kbnVar == null || kbnVar.a() == null) {
            return 0L;
        }
        kbp kbpVar = kbnVar.a().get(kbnVar.a().size() - 1);
        if (kbpVar == null) {
            return 0L;
        }
        long a2 = kbpVar.a();
        if (a2 == 0) {
            a2 = kbpVar.e();
        }
        long b = kbpVar.b();
        if (b != 0) {
            LogUtil.a("DataDictionarySyncManager", "exist modifyTime:", Long.valueOf(b));
            a2 = Math.max(a2, b);
        }
        LogUtil.a("DataDictionarySyncManager", "getLastTimeFromData lastTime:", Long.valueOf(a2));
        return a2;
    }

    private void g() {
        if (this.o == null) {
            return;
        }
        LogUtil.h("DataDictionarySyncManager", "removeRetryMsg");
        this.o.removeTasksAndMessages();
    }

    private void h() {
        this.i = false;
        this.f.clear();
    }

    public void b(long j) {
        this.n = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T a(int i, Object... objArr) {
        return (T) kby.b().execute(i, objArr);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.health.filesync";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
