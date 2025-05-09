package defpackage;

import android.os.SystemClock;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.rmq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class rmq extends BaseOperate {
    private final int[] c = {2018};
    private final int[] b = {DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value(), DicDataTypeUtil.DataType.BLOOD_PRESSURE_ABNORMAL.value()};

    /* renamed from: a, reason: collision with root package name */
    private final String[] f16820a = {BleConstants.BLOODPRESSURE_SYSTOLIC, BleConstants.BLOODPRESSURE_DIASTOLIC, BleConstants.BLOODPRESSURE_SPHYGMUS, "measureAbnormal"};

    public rmq() {
        this.pageType = 107;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readGroupDataBySource */
    public void m848xdec3f9b0(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        b(rkbVar, new DataSourceCallback() { // from class: rmz
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rmq.this.d(dataSourceCallback, rkbVar, i, (List) obj);
            }
        });
    }

    /* synthetic */ void d(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, List list) {
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rmv
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<rsg> groupByMonth = groupByMonth(convertToDataModels(list, rkbVar));
            this.mHandler.post(new Runnable() { // from class: rmw
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, groupByMonth);
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDoubleGroupDataNoSource */
    public void m846x5b1923e2(final rkb rkbVar, final DataSourceCallback<List<rsb>> dataSourceCallback) {
        b(rkbVar, new DataSourceCallback() { // from class: rmy
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rmq.this.a(dataSourceCallback, rkbVar, i, (List) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* synthetic */ void a(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, List list) {
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rnd
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        if (rkbVar.a("Subpage") == 2) {
            list = b(list);
        }
        a(list);
        final List<rsg> groupByDay = groupByDay(convertToDataModels(list, rkbVar));
        this.mHandler.post(new Runnable() { // from class: rni
            @Override // java.lang.Runnable
            public final void run() {
                rmq.this.e(dataSourceCallback, i, groupByDay);
            }
        });
    }

    /* synthetic */ void e(DataSourceCallback dataSourceCallback, int i, List list) {
        dataSourceCallback.onResponse(i, doubleGroupSort(list));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readCateDouGroupDataNoSource */
    public void m841x17777686(final rkb rkbVar, final DataSourceCallback<Map<Integer, List<rsb>>> dataSourceCallback) {
        b(rkbVar, new DataSourceCallback() { // from class: rmx
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rmq.this.e(dataSourceCallback, rkbVar, i, (List) obj);
            }
        });
    }

    /* synthetic */ void e(final DataSourceCallback dataSourceCallback, final rkb rkbVar, final int i, final List list) {
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rnb
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new HashMap());
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: rnc
                @Override // java.lang.Runnable
                public final void run() {
                    rmq.this.a(dataSourceCallback, list, rkbVar);
                }
            });
        }
    }

    /* synthetic */ void a(DataSourceCallback dataSourceCallback, List list, rkb rkbVar) {
        dataSourceCallback.onResponse(0, a((List<HiHealthData>) list, rkbVar));
    }

    private void b(rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        final ArrayList arrayList = new ArrayList(10);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ReleaseLogUtil.e("R_BloodPressureOperateImp", "readData readHeartRateData start");
        c(rkbVar, new DataSourceCallback() { // from class: rne
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rmq.c(arrayList, countDownLatch, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            ReleaseLogUtil.e("R_BloodPressureOperateImp", "readData latch await exception");
        }
        ReleaseLogUtil.e("R_BloodPressureOperateImp", "readData readBloodPressureData start");
        e(rkbVar, new DataSourceCallback() { // from class: rna
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rmq.this.c(dataSourceCallback, arrayList, i, (List) obj);
            }
        });
    }

    static /* synthetic */ void c(List list, CountDownLatch countDownLatch, int i, List list2) {
        ReleaseLogUtil.e("R_BloodPressureOperateImp", "readData readHeartRateData end", ", resultCode = ", Integer.valueOf(i));
        if (i == 0 && koq.c(list2)) {
            list.addAll(list2);
        }
        countDownLatch.countDown();
    }

    /* synthetic */ void c(DataSourceCallback dataSourceCallback, List list, int i, List list2) {
        ReleaseLogUtil.e("R_BloodPressureOperateImp", "readData readBloodPressureData end", ", resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list2)) {
            dataSourceCallback.onResponse(i, new ArrayList());
        } else {
            dataSourceCallback.onResponse(i, a((List<HiHealthData>) list2, (List<HiHealthData>) list));
        }
    }

    private void e(rkb rkbVar, DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList(10);
        HiAggregateOption b = rrc.b(rkbVar, this.b, this.f16820a, 1, 0);
        b.setCount(500);
        HiDataAggregateProOption a2 = rrc.a(b, rkbVar.j());
        aggregateHiHealthDataPro(a2, new AnonymousClass2(dataSourceCallback, arrayList, b, a2));
    }

    /* renamed from: rmq$2, reason: invalid class name */
    class AnonymousClass2 implements DataSourceCallback<List<HiHealthData>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ HiDataAggregateProOption f16822a;
        final /* synthetic */ HiAggregateOption b;
        final /* synthetic */ List d;
        final /* synthetic */ DataSourceCallback e;

        AnonymousClass2(DataSourceCallback dataSourceCallback, List list, HiAggregateOption hiAggregateOption, HiDataAggregateProOption hiDataAggregateProOption) {
            this.e = dataSourceCallback;
            this.d = list;
            this.b = hiAggregateOption;
            this.f16822a = hiDataAggregateProOption;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HiHealthData> list) {
            if (i != 0) {
                this.e.onResponse(i, new ArrayList());
            } else {
                if (koq.b(list)) {
                    this.e.onResponse(i, this.d);
                    return;
                }
                this.d.addAll(list);
                this.b.setEndTime(list.get(list.size() - 1).getStartTime() - 1);
                rmq.this.aggregateHiHealthDataPro(this.f16822a, new DataSourceCallback() { // from class: rng
                    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                    public final void onResponse(int i2, Object obj) {
                        rmq.AnonymousClass2.this.onResponse(i2, (List) obj);
                    }
                });
            }
        }
    }

    private void c(rkb rkbVar, DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList(10);
        HiDataReadOption b = rrc.b(rkbVar, this.c);
        b.setCount(500);
        HiDataReadProOption b2 = rrc.b(b, rkbVar.j());
        readHiHealthDataPro(b2, new AnonymousClass1(dataSourceCallback, arrayList, b, b2));
    }

    /* renamed from: rmq$1, reason: invalid class name */
    class AnonymousClass1 implements DataSourceCallback<SparseArray<List<HiHealthData>>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DataSourceCallback f16821a;
        final /* synthetic */ HiDataReadProOption b;
        final /* synthetic */ HiDataReadOption c;
        final /* synthetic */ List d;

        AnonymousClass1(DataSourceCallback dataSourceCallback, List list, HiDataReadOption hiDataReadOption, HiDataReadProOption hiDataReadProOption) {
            this.f16821a = dataSourceCallback;
            this.d = list;
            this.c = hiDataReadOption;
            this.b = hiDataReadProOption;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: dPK_, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, SparseArray<List<HiHealthData>> sparseArray) {
            if (i != 0) {
                this.f16821a.onResponse(i, new ArrayList());
                return;
            }
            if (sparseArray != null && sparseArray.size() != 0) {
                List<HiHealthData> list = sparseArray.get(rmq.this.c[0]);
                if (koq.b(list)) {
                    this.f16821a.onResponse(i, this.d);
                    return;
                }
                this.d.addAll(list);
                this.c.setEndTime(list.get(list.size() - 1).getStartTime() - 1);
                rmq.this.readHiHealthDataPro(this.b, new DataSourceCallback() { // from class: rnf
                    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                    public final void onResponse(int i2, Object obj) {
                        rmq.AnonymousClass1.this.onResponse(i2, (SparseArray) obj);
                    }
                });
                return;
            }
            this.f16821a.onResponse(i, this.d);
        }
    }

    private List<HiHealthData> a(List<HiHealthData> list, List<HiHealthData> list2) {
        if (koq.b(list)) {
            return new ArrayList();
        }
        if (koq.b(list2)) {
            return list;
        }
        Map<Long, HiHealthData> listToMap = listToMap(list2);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS) <= 0 && listToMap.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                hiHealthData.putInt(BleConstants.BLOODPRESSURE_SPHYGMUS, listToMap.get(Long.valueOf(hiHealthData.getStartTime())).getIntValue());
            }
        }
        return list;
    }

    private List<HiHealthData> b(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            if (eeu.c(hiHealthData.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC), hiHealthData.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC)) == 1) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private Map<Integer, List<rsb>> a(List<HiHealthData> list, rkb rkbVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HashMap hashMap = new HashMap(16);
        hashMap.put(100, new ArrayList());
        for (int i : eeu.a()) {
            if (i != 1) {
                hashMap.put(Integer.valueOf(i), new ArrayList());
            }
        }
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            int c = eeu.c(hiHealthData.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC), hiHealthData.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC));
            if (hashMap.get(Integer.valueOf(c)) != null) {
                arrayList.add(Integer.valueOf(c));
                arrayList2.add(hiHealthData);
            }
        }
        a(arrayList2);
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            PrivacyDataModel convertToDataModel = convertToDataModel(arrayList2.get(i2), rkbVar);
            ((List) hashMap.get(arrayList.get(i2))).add(convertToDataModel);
            ((List) hashMap.get(100)).add(convertToDataModel);
        }
        HashMap hashMap2 = new HashMap(16);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (rkbVar.o() == 0) {
                hashMap2.put((Integer) entry.getKey(), doubleGroupSort(groupByDay((List) entry.getValue())));
            } else {
                hashMap2.put((Integer) entry.getKey(), doubleGroupForCache(groupByDay((List) entry.getValue()), rkbVar));
            }
        }
        LogUtil.a("BloodPressureOperateImp", "getAbnormalDataMap totalCost=", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime), "ms");
        return hashMap2;
    }

    private void a(List<HiHealthData> list) {
        LogUtil.a("BloodPressureOperateImp", "start setSourceType");
        if (list.size() == 0) {
            LogUtil.a("BloodPressureOperateImp", "mergeList size 0");
            return;
        }
        HashSet hashSet = new HashSet(16);
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().getClientId()));
        }
        HashMap<Integer, String> iconMap = getIconMap(rrb.a((HashSet<Integer>) hashSet));
        for (HiHealthData hiHealthData : list) {
            int clientId = hiHealthData.getClientId();
            if (iconMap.containsKey(Integer.valueOf(clientId))) {
                String str = iconMap.get(Integer.valueOf(clientId));
                hiHealthData.putString("iconResource", str);
                d(hiHealthData, str);
            }
        }
        LogUtil.a("BloodPressureOperateImp", "end setSourceType");
    }

    private void d(HiHealthData hiHealthData, String str) {
        if (String.valueOf(rrf.d()).equals(str)) {
            hiHealthData.putInt("source_type", 3);
        } else {
            hiHealthData.putInt("source_type", 2);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return HiHealthDataType.d(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
    }
}
