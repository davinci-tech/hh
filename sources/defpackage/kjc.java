package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class kjc implements SyncStrategy {

    /* renamed from: a, reason: collision with root package name */
    private long f14411a;

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public boolean isNeedSync(DeviceInfo deviceInfo, long j) {
        if (!cwi.c(deviceInfo, 154)) {
            return false;
        }
        this.f14411a = j;
        return System.currentTimeMillis() - kiz.d(c(j)) > j;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public List<HiHealthData> fetchData(List<HiAggregateOption> list) {
        if (CollectionUtils.d(list)) {
            return new ArrayList(0);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthDataEx(list, new HiAggregateListenerEx() { // from class: kjc.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                ReleaseLogUtil.e("DEVMGR_ReconnectSuccessSyncStrategy", "fetchData onResult() errCode=", Integer.valueOf(i));
                if (i != 0) {
                    return;
                }
                for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                    List<HiHealthData> valueAt = sparseArray.valueAt(i3);
                    if (valueAt != null) {
                        arrayList.addAll(valueAt);
                    }
                }
                countDownLatch.countDown();
                LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "fetchData onResult() result.size=", Integer.valueOf(sparseArray.size()));
                LogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "fetchData onResult() result = ", HiJsonUtil.e(sparseArray));
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "fetchData() timeout");
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "fetchData() interruptedException");
        }
        return new ArrayList(arrayList);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public List<cvu> preProcess(List<HiHealthData> list) {
        ReleaseLogUtil.e("DEVMGR_ReconnectSuccessSyncStrategy", "preProcessing rawDatas=", kiz.a(list));
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                arrayList.addAll(transAggregateToRead(hiHealthData));
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        cvu d = d(arrayList, currentTimeMillis);
        cvu d2 = d(arrayList, currentTimeMillis - 86400000);
        ArrayList arrayList2 = new ArrayList(kiz.d(arrayList, SyncStrategy.SRC_PKG_NAME, SyncStrategy.WEAR_PKG_NAME));
        arrayList2.add(d);
        arrayList2.add(d2);
        return arrayList2;
    }

    private cvu d(List<HiHealthData> list, long j) {
        HashSet hashSet = new HashSet(16);
        ArrayList arrayList = new ArrayList(10);
        long b = kiz.b(j, 86400000L);
        a(b, list, hashSet, arrayList);
        c(b, j, hashSet, arrayList);
        ArrayList<cvu> arrayList2 = new ArrayList(kiz.d(arrayList, SyncStrategy.SRC_PKG_NAME, SyncStrategy.WEAR_PKG_NAME));
        Collections.sort(arrayList2, new Comparator() { // from class: kjd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((cvu) obj).d(), ((cvu) obj2).d());
                return compare;
            }
        });
        LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "extractTodayHalfHourDataWithSort() after sort.size=", Integer.valueOf(arrayList2.size()));
        cvu cvuVar = null;
        for (cvu cvuVar2 : arrayList2) {
            if (cvuVar == null) {
                cvuVar = cvuVar2;
            } else {
                List<cvv> a2 = cvuVar2.a();
                if (a2 != null) {
                    cvuVar.a().addAll(a2);
                }
            }
        }
        if (cvuVar == null) {
            return null;
        }
        cvuVar.c(DicDataTypeUtil.DataType.DAILY_ACTIVITY_RECORD.value());
        cvuVar.d(kiz.b(b));
        cvuVar.b(kiz.b(kiz.c(j, 86400000L)));
        Object[] objArr = new Object[2];
        objArr[0] = "extractTodayHalfHourDataWithSort() after construct.size=";
        objArr[1] = Integer.valueOf(cvuVar.a() != null ? cvuVar.a().size() : 0);
        LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", objArr);
        LogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "extractTodayHalfHourDataWithSort() after construct=", HiJsonUtil.e(cvuVar));
        return cvuVar;
    }

    private void a(long j, List<HiHealthData> list, Set<String> set, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(kiz.b().length);
        for (int i : kiz.b()) {
            arrayList.add(Integer.valueOf(i));
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            HiHealthData next = it.next();
            if (next.getEndTime() - next.getStartTime() <= 1800000 && next.getStartTime() >= j && arrayList.contains(Integer.valueOf(next.getType()))) {
                list2.add(next);
                set.add(kiz.b(next.getStartTime(), 1800000L) + "_" + next.getType());
                it.remove();
            }
        }
        LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "extractTodayHalfHourDataWithSort() after removal.size=", Integer.valueOf(list2.size()));
    }

    private void c(long j, long j2, Set<String> set, List<HiHealthData> list) {
        while (j < kiz.c(j2, 86400000L)) {
            for (int i : kiz.b()) {
                if (!set.contains(j + "_" + i)) {
                    HiHealthData hiHealthData = new HiHealthData(i);
                    hiHealthData.setValue(0);
                    hiHealthData.setTimeInterval(j, 1800000 + j);
                    list.add(hiHealthData);
                }
            }
            j += 1800000;
        }
        LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "extractTodayHalfHourDataWithSort() after fill.size=", Integer.valueOf(list.size()));
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.threecircle.strategy.SyncStrategy
    public void syncDataToDevice(DeviceInfo deviceInfo, List<cvu> list) {
        if (CollectionUtils.d(list)) {
            LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "syncDataToDevice() skipped sync by empty list");
            return;
        }
        int i = 0;
        for (cvu cvuVar : list) {
            LogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "syncDataToDevice() onSending=", HiJsonUtil.e(cvuVar));
            if (!cuk.b().sendSamplePointCommand(deviceInfo, cvuVar, "DEVMGR_ReconnectSuccessSyncStrategy")) {
                ReleaseLogUtil.c("DEVMGR_ReconnectSuccessSyncStrategy", "syncDataToDevice() send failed");
                return;
            }
            i++;
        }
        LogUtil.a("DEVMGR_ReconnectSuccessSyncStrategy", "syncDataToDevice() sent (", Integer.valueOf(i), ") samplePoints");
        if (i > 0) {
            kiz.a(c(this.f14411a));
        }
    }

    private String c(long j) {
        return j == 60000 ? SyncStrategy.KEY_CLOUD_LAST_SYNC_TIME : SyncStrategy.SP_KEY_RECONNECT_LAST_SYNC_TIME;
    }
}
