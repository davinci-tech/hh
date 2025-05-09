package defpackage;

import android.os.Parcelable;
import android.os.RemoteException;
import android.util.SparseArray;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class kbv {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DeviceInfo f14265a;
    private IBaseResponseCallback b;
    private ITransferSleepAndDFXFileCallback.Stub c;
    private boolean e;
    private IBaseResponseCallback f;
    private List<kbs> g;
    private long i;
    private List<Integer> j;

    private kbv() {
        this.e = false;
        this.i = 0L;
        this.j = new CopyOnWriteArrayList();
        this.g = new CopyOnWriteArrayList();
        this.b = null;
        this.f = null;
        this.c = new ITransferSleepAndDFXFileCallback.Stub() { // from class: kbv.2
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_DataDictionarySequenceManager", "onSuccess errorCode: ", Integer.valueOf(i));
                kbv.this.c(str);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.d("R_DataDictionarySequenceManager", "onFailure errorCode:", Integer.valueOf(i));
                kbv.this.e(i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a("DataDictionarySequenceManager", "processReceiverData path: ", str);
        kbu kbuVar = (kbu) a(5, str);
        if (kbuVar == null) {
            d();
        } else {
            a(kbuVar);
        }
    }

    private void a(kbu kbuVar) {
        LogUtil.h("DataDictionarySequenceManager", "processDataSync enter");
        if (e(kbuVar)) {
            c(true, kbuVar);
        } else {
            kbuVar.a(this.f14265a);
            d(kbuVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, kbu kbuVar) {
        LogUtil.a("DataDictionarySequenceManager", "enter processNextData isFinish:", Boolean.valueOf(z));
        if (z) {
            if (this.j.contains(Integer.valueOf(kbuVar.b()))) {
                a();
            } else {
                LogUtil.a("DataDictionarySequenceManager", "processNextData sequenceFileData DigitTypeId Not in the current queue");
            }
            d();
            return;
        }
        f(kbuVar);
        b(e());
    }

    private void d(final kbu kbuVar) {
        LogUtil.a("DataDictionarySequenceManager", "enter processSaveToHiHealth");
        a(3, kbuVar, new HiDataOperateListener() { // from class: kbv.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("R_DataDictionarySequenceManager", "saveDataToHiHealth onResult type:", Integer.valueOf(i));
                if (i == 0) {
                    kbv.this.c(kbuVar);
                    kbv kbvVar = kbv.this;
                    Boolean bool = (Boolean) kbvVar.a(6, kbuVar, kbvVar.e());
                    if (bool == null) {
                        return;
                    }
                    kbv.this.c(bool.booleanValue(), kbuVar);
                    return;
                }
                LogUtil.h("DataDictionarySequenceManager", "processInsertToHiHealth error:", Integer.valueOf(i));
                kbv.this.c(true, kbuVar);
            }
        });
        LogUtil.a("DataDictionarySequenceManager", "insertDicData healthData:", Integer.valueOf(kbuVar.b()));
    }

    private void a(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(kbu kbuVar) {
        if (kbuVar == null || kbuVar.e() == null || kbuVar.e().isEmpty()) {
            return;
        }
        if (kbuVar.e().get(kbuVar.e().size() - 1).c() == 0) {
            return;
        }
        if (r0.c() > kcb.b(this.f14265a.getDeviceIdentify(), kbuVar.b(), 4000000, "DataDictionarySequenceManager")) {
            kcb.e(kbuVar.d().getDeviceIdentify(), kbuVar.b(), r0.c(), 4000000, "DataDictionarySequenceManager");
        }
    }

    public void b(DeviceInfo deviceInfo, keu keuVar, IBaseResponseCallback iBaseResponseCallback, IBaseResponseCallback iBaseResponseCallback2) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("DataDictionarySequenceManager", "startSequenceFileTask callback is null");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("DataDictionarySequenceManager", "startSequenceFileTask deviceInfo is null");
            a(-1, iBaseResponseCallback2);
            iBaseResponseCallback.d(-1, null);
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("DataDictionarySequenceManager", "startSequenceFileTask device is not connected");
            a(-1, iBaseResponseCallback2);
            iBaseResponseCallback.d(-1, null);
        }
        if (this.e) {
            LogUtil.h("DataDictionarySequenceManager", "startSequenceFileTask is DetailSyncing");
            return;
        }
        synchronized (d) {
            this.j.clear();
            this.g.clear();
        }
        if (keuVar == null) {
            return;
        }
        int b = keuVar.b();
        LogUtil.a("DataDictionarySequenceManager", "initSyncData deviceInfo:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " syncType:", Integer.valueOf(b));
        kbq.c();
        if (b == -1) {
            c(deviceInfo);
        } else {
            b(deviceInfo, keuVar);
        }
        Iterator<Integer> it = this.j.iterator();
        while (it.hasNext()) {
            LogUtil.a("DataDictionarySequenceManager", "to be synchronized:", Integer.valueOf(it.next().intValue()));
        }
        if (this.j.size() == 0) {
            a(0, iBaseResponseCallback2);
            iBaseResponseCallback.d(0, null);
        } else {
            this.b = iBaseResponseCallback;
            this.f = iBaseResponseCallback2;
            this.f14265a = deviceInfo;
            d();
        }
    }

    private void c(DeviceInfo deviceInfo) {
        for (Map.Entry<Integer, Integer> entry : kbq.d().entrySet()) {
            int intValue = entry.getValue().intValue();
            if (cwi.c(deviceInfo, intValue)) {
                this.j.add(entry.getKey());
            } else {
                LogUtil.a("DataDictionarySequenceManager", "initSyncData capability:", Integer.valueOf(intValue), " not support");
            }
        }
    }

    private void b(DeviceInfo deviceInfo, keu keuVar) {
        Iterator<kbm> it = keuVar.c().iterator();
        while (it.hasNext()) {
            int d2 = it.next().d();
            int d3 = kbq.d(d2);
            if (cwi.c(deviceInfo, d3)) {
                this.j.add(Integer.valueOf(d2));
            } else {
                LogUtil.a("DataDictionarySequenceManager", "initSyncData capability:", Integer.valueOf(d3), " not support");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int c2 = c();
        if (c2 == -1) {
            LogUtil.h("DataDictionarySequenceManager", "syncId is invalid, No data needs to be synchronized");
            if (CommonUtil.as()) {
                iyv.c("DataSyncSpeed", "Sequence data-dict sync duration: " + (System.currentTimeMillis() - this.i));
            }
            h();
            IBaseResponseCallback iBaseResponseCallback = this.b;
            this.b = null;
            if (iBaseResponseCallback != null) {
                a(0, this.f);
                iBaseResponseCallback.d(0, null);
                return;
            } else {
                ReleaseLogUtil.d("R_DataDictionarySequenceManager", "callback is null.");
                sqo.h("DataDictionarySequenceManagercallback is null.");
                return;
            }
        }
        d(c2);
    }

    private void d(final int i) {
        jrp.c();
        a(2, Integer.valueOf(i), this.f14265a, new HiDataReadResultListener() { // from class: kbv.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a("DataDictionarySequenceManager", "onResult errorCode:", Integer.valueOf(i2), ", object:", obj);
                if (obj == null) {
                    LogUtil.h("DataDictionarySequenceManager", "object is null");
                    kbv.this.a();
                    kbv.this.d();
                } else {
                    if (obj instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        if (sparseArray.size() > 0) {
                            kbv.this.bMV_(i, sparseArray);
                            return;
                        } else {
                            LogUtil.h("DataDictionarySequenceManager", "sparseArray size is zero");
                            kbv.this.bMV_(i, sparseArray);
                            return;
                        }
                    }
                    LogUtil.h("DataDictionarySequenceManager", "sparseArray !instanceof");
                    kbv.this.bMV_(i, null);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a("DataDictionarySequenceManager", "onResultIntent intentType:", Integer.valueOf(i2), ", object:", obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bMV_(int i, SparseArray<HiHealthData> sparseArray) {
        long max;
        long b = kcb.b(this.f14265a.getDeviceIdentify(), i, 4000000, "DataDictionarySequenceManager");
        boolean e = kcb.e(this.f14265a.getDeviceIdentify(), 4000000, "DataDictionarySequenceManager");
        long j = b * 1000;
        LogUtil.a("DataDictionarySequenceManager", "processReadHiHealthData modifyTime:", Long.valueOf(b), " ,statTime: ", Long.valueOf(j));
        long currentTimeMillis = System.currentTimeMillis();
        if (sparseArray == null) {
            LogUtil.h("DataDictionarySequenceManager", "processReadHiHealthData readHiHeathList is null");
            b(i, j == 0 ? 0L : j + 1000, currentTimeMillis);
            return;
        }
        if (sparseArray.size() == 0) {
            LogUtil.h("DataDictionarySequenceManager", "processReadData readData is empty");
            b(i, j == 0 ? 0L : j + 1000, currentTimeMillis);
            return;
        }
        Parcelable parcelable = sparseArray.get(i);
        if (parcelable instanceof List) {
            List list = (List) parcelable;
            kcb.a(i, list);
            HiHealthData hiHealthData = (HiHealthData) list.get(list.size() - 1);
            if (i == DicDataTypeUtil.DataType.SLEEP_DETAILS.value() && j > 0 && j < hiHealthData.getEndTime() && !e) {
                max = Math.min(j, hiHealthData.getEndTime());
            } else {
                max = Math.max(j, hiHealthData.getEndTime());
            }
            if (i == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
                kcb.e(this.f14265a.getDeviceIdentify(), false, 4000000, "DataDictionarySequenceManager");
            }
            b(i, max + 1000, currentTimeMillis);
        }
    }

    private void b(int i, long j, long j2) {
        int i2 = (int) (j / 1000);
        int i3 = (int) (j2 / 1000);
        ReleaseLogUtil.e("R_DataDictionarySequenceManager", "syncData requestStartTime:", Integer.valueOf(i2), ",requestEndTime:", Integer.valueOf(i3), " ,digitTypeId: ", Integer.valueOf(i));
        if (j > j2) {
            ReleaseLogUtil.d("R_DataDictionarySequenceManager", "syncData cancel,valid time");
            a();
            d();
        } else {
            kbs kbsVar = (kbs) a(4, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            if (kbsVar == null) {
                return;
            }
            d(kbsVar);
        }
    }

    private void d(kbs kbsVar) {
        synchronized (d) {
            this.g.add(kbsVar);
        }
        b(kbsVar);
    }

    private void b(kbs kbsVar) {
        int c2;
        if (kbsVar == null || (c2 = kbsVar.c()) == -1) {
            return;
        }
        LogUtil.a("DataDictionarySequenceManager", " send command start dicTypeId:", Integer.valueOf(c2));
        int[] iArr = {kbsVar.d(), kbsVar.b()};
        LogUtil.a("DataDictionarySequenceManager", "send Time start:", Integer.valueOf(iArr[0]), ",end:", Integer.valueOf(iArr[1]));
        jqj jqjVar = new jqj();
        jqjVar.a("sequence_data");
        jqjVar.d(22);
        jqjVar.c((TransferFileReqType) null);
        jqjVar.c(this.f14265a.getDeviceIdentify());
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.b(c2);
        LogUtil.a("DataDictionarySequenceManager", " send command transFileInfo:", jqjVar);
        jyp.c().b(jqjVar, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.j.isEmpty()) {
            return;
        }
        this.j.remove(0);
        synchronized (d) {
            List<kbs> list = this.g;
            if (list.isEmpty()) {
                return;
            }
            list.clear();
        }
    }

    private int c() {
        if (this.j.size() <= 0) {
            return -1;
        }
        int intValue = this.j.get(0).intValue();
        ReleaseLogUtil.e("R_DataDictionarySequenceManager", "checkSyncDataIdList validId:", Integer.valueOf(intValue));
        return intValue;
    }

    private boolean e(kbu kbuVar) {
        if (kbuVar == null) {
            return true;
        }
        if (kbuVar.b() != -1) {
            return kbuVar.e() == null || kbuVar.e().size() <= 0;
        }
        LogUtil.a("DataDictionarySequenceManager", "parsePointData data is null or valid");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public kbs e() {
        synchronized (d) {
            if (this.g.isEmpty()) {
                return null;
            }
            LogUtil.a("DataDictionarySequenceManager", "getCacheInfo mSyncDataIdList:", Integer.valueOf(this.g.size()));
            return this.g.get(0);
        }
    }

    private void f(kbu kbuVar) {
        int b = b(kbuVar);
        synchronized (d) {
            if (this.g.isEmpty()) {
                return;
            }
            LogUtil.a("DataDictionarySequenceManager", "updateCacheInfo nextStartTime:", Integer.valueOf(b));
            this.g.get(0).e(b + 1);
        }
    }

    private int b(kbu kbuVar) {
        if (kbuVar == null || kbuVar.e() == null) {
            return 0;
        }
        kbt kbtVar = kbuVar.e().get(kbuVar.e().size() - 1);
        if (kbtVar == null) {
            return 0;
        }
        int c2 = kbtVar.c();
        return c2 == 0 ? kbtVar.a() : c2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i == 144001) {
            LogUtil.h("DataDictionarySequenceManager", "onFailure no file to sync next");
            a();
            d();
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = this.b;
        this.b = null;
        if (iBaseResponseCallback != null) {
            a(i, this.f);
            iBaseResponseCallback.d(i, "");
        } else {
            LogUtil.h("DataDictionarySequenceManager", "callback is null.");
            sqo.h("callback is null.");
        }
    }

    private void h() {
        this.e = false;
        synchronized (d) {
            this.j.clear();
            this.g.clear();
        }
    }

    public void d(long j) {
        this.i = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> T a(int i, Object... objArr) {
        return (T) kbx.d().execute(i, objArr);
    }

    public static kbv b() {
        return c.c;
    }

    static class c {
        private static kbv c = new kbv();
    }
}
