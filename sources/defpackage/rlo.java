package defpackage;

import android.os.Handler;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.rlo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class rlo extends BaseOperate {
    private List<HiHealthData> c = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private List<HiHealthData> f16805a = new ArrayList(10);
    private List<HiHealthData> e = new ArrayList(10);
    private List<HiHealthData> b = new ArrayList(10);
    private List<HiHealthData> d = new ArrayList(10);

    public rlo() {
        this.pageType = 105;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1005);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setIntValue(hiHealthData.getIntValue());
        privacyDataModel.setDataTitle(rre.d(hiHealthData.getIntValue()));
        privacyDataModel.putInt("bloodOxygenCardKey", hiHealthData.getInt("bloodOxygenCardKey"));
        privacyDataModel.putInt("altitudeKey", hiHealthData.getInt("altitudeKey"));
        privacyDataModel.putInt("bloodOxygenKey", hiHealthData.getIntValue());
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readGroupDataNoSource */
    public void m849xe5ecdbf1(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        int a2 = rkbVar.a("Subpage");
        if (a2 == 2) {
            d(dataSourceCallback);
            return;
        }
        if (a2 == 4) {
            e(dataSourceCallback);
        } else if (a2 == 3) {
            a(dataSourceCallback);
        } else {
            b(dataSourceCallback);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{"BloodOxygen"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{2103});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(2);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setDeviceUuid(str);
        a(hiAggregateOption, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{"BloodOxygen"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{2103});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setDeviceUuid(str);
        a(hiAggregateOption, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataNoSource */
    public void m843xae5c7554(rkb rkbVar, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(efb.c() ? new int[]{2103, DicDataTypeUtil.DataType.ALTITUDE_TYPE.value(), 2107} : new int[]{2103, 2107});
        LogUtil.a("BloodOxygenOperateImp", "readDayDataNoSource start");
        readHiHealthDataPro(rkbVar, new DataSourceCallback() { // from class: rma
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.dPI_(dataSourceCallback, i, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPI_(final DataSourceCallback dataSourceCallback, final int i, SparseArray sparseArray) {
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            this.mHandler.post(new Runnable() { // from class: rmj
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        List<HiHealthData> list = (List) sparseArray.get(2103);
        List<HiHealthData> list2 = (List) sparseArray.get(DicDataTypeUtil.DataType.ALTITUDE_TYPE.value());
        List<HiHealthData> list3 = (List) sparseArray.get(2107);
        if (!koq.b(list2)) {
            for (HiHealthData hiHealthData : list2) {
                hiHealthData.putInt("altitudeKey", hiHealthData.getIntValue());
            }
        }
        List<HiHealthData> e = e(list3, list);
        sortHiHealthDataToDesc(e);
        scg.b(e, list2);
        final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(e);
        int i2 = 0;
        for (PrivacyDataModel privacyDataModel : proResultOfReadInOneDayNoSource) {
            if (i2 >= 20) {
                break;
            }
            rtv.d(privacyDataModel.getInt("bloodOxygenKey"));
            i2++;
        }
        this.mHandler.post(new Runnable() { // from class: rmh
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
            }
        });
    }

    private List<HiHealthData> e(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list2) && koq.b(list)) {
            return arrayList;
        }
        if (koq.b(list)) {
            Iterator<HiHealthData> it = list2.iterator();
            while (it.hasNext()) {
                it.next().putInt("bloodOxygenCardKey", 1004);
            }
            return list2;
        }
        if (koq.b(list2)) {
            Iterator<HiHealthData> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().putInt("bloodOxygenCardKey", 1002);
            }
            return list;
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list2) {
            hiHealthData.putInt("bloodOxygenCardKey", 1004);
            hashMap.put(Long.valueOf(hiHealthData.getStartTime()), hiHealthData);
        }
        for (HiHealthData hiHealthData2 : list) {
            hiHealthData2.putInt("bloodOxygenCardKey", 1002);
            hashMap.put(Long.valueOf(hiHealthData2.getStartTime()), hiHealthData2);
        }
        Iterator it3 = hashMap.entrySet().iterator();
        while (it3.hasNext()) {
            arrayList.add((HiHealthData) ((Map.Entry) it3.next()).getValue());
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByDeviceSource(long j, String str, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        this.mHiHealthDataListRecursive.clear();
        rkbVar.c(rrb.c(j));
        rkbVar.d(rrb.e(j));
        rkbVar.c(2);
        rkbVar.d(str);
        rkbVar.e(300);
        setVarOfReadInOneDayByAppSource(rkbVar);
        LogUtil.a("BloodOxygenOperateImp", "readHiHealthDataProInOneDay start");
        readHiHealthDataProInOneDay(rkbVar, new DataSourceCallback() { // from class: rmk
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.f(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void f(final DataSourceCallback dataSourceCallback, final int i, List list) {
        LogUtil.b("BloodOxygenOperateImp", "readHiHealthDataProInOneDay end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rmb
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = proResultOfReadInOneDayNoSource(list);
            this.mHandler.post(new Runnable() { // from class: rmi
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, proResultOfReadInOneDayNoSource);
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfReadInOneDayByAppSource(rkb rkbVar) {
        rkbVar.a(new int[]{2103});
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        int i = hiHealthData.getInt("bloodOxygenCardKey");
        privacyDataModel.putInt("bloodOxygenCardKey", i);
        int i2 = hiHealthData.getInt("maxBloodOxygen");
        int i3 = hiHealthData.getInt("minBloodOxygen");
        if (i == 0) {
            privacyDataModel.setDataTitle(rre.a(i2, i3));
            privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
            return;
        }
        if (i == 1003) {
            privacyDataModel.setDataTitle(rre.c(i2, i3));
            privacyDataModel.setDataDesc(scg.d(BaseApplication.getContext(), hiHealthData.getStartTime()));
            return;
        }
        privacyDataModel.putInt("bloodOxygenKey", hiHealthData.getInt("bloodOxygenKey"));
        privacyDataModel.putInt("altitudeKey", hiHealthData.getInt("altitudeKey"));
        privacyDataModel.setDataDesc(scg.a(BaseApplication.getContext(), hiHealthData.getStartTime()));
        privacyDataModel.setDataTitle(rre.d(hiHealthData.getInt("bloodOxygenKey")));
        privacyDataModel.setIntValue(hiHealthData.getInt("bloodOxygenKey"));
        if (i == 1001) {
            privacyDataModel.putInt("lakeLouiseScoreKey", hiHealthData.getInt("lakeLouiseScoreKey"));
        }
    }

    private void a(HiAggregateOption hiAggregateOption, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c((String) null).c();
        LogUtil.a("BloodOxygenOperateImp", "aggregateProMax start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass5(dataSourceCallback, hiAggregateOption));
    }

    /* renamed from: rlo$5, reason: invalid class name */
    class AnonymousClass5 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback c;
        final /* synthetic */ HiAggregateOption e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass5(DataSourceCallback dataSourceCallback, HiAggregateOption hiAggregateOption) {
            this.c = dataSourceCallback;
            this.e = hiAggregateOption;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("BloodOxygenOperateImp", "aggregateProMax end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rlo.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.c;
                handler.post(new Runnable() { // from class: rmn
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else if (koq.b(list)) {
                Handler handler2 = rlo.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.c;
                handler2.post(new Runnable() { // from class: rmp
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
            } else {
                rlo.this.mMaxDataList = list;
                this.e.setAggregateType(5);
                rlo.this.b(this.e, (DataSourceCallback<List<rsg>>) this.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiAggregateOption hiAggregateOption, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c((String) null).c();
        LogUtil.a("BloodOxygenOperateImp", "aggregateProMin start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass1(dataSourceCallback));
    }

    /* renamed from: rlo$1, reason: invalid class name */
    class AnonymousClass1 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback c;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass1(DataSourceCallback dataSourceCallback) {
            this.c = dataSourceCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("BloodOxygenOperateImp", "aggregateProMin end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rlo.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.c;
                handler.post(new Runnable() { // from class: rmo
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else {
                if (koq.b(list)) {
                    Handler handler2 = rlo.this.mHandler;
                    final DataSourceCallback dataSourceCallback2 = this.c;
                    handler2.post(new Runnable() { // from class: rml
                        @Override // java.lang.Runnable
                        public final void run() {
                            DataSourceCallback.this.onResponse(0, new ArrayList());
                        }
                    });
                    return;
                }
                rlo.this.mMinDataList = list;
                rlo rloVar = rlo.this;
                final List<rsg> hiHealthDataProcess = rlo.this.hiHealthDataProcess(rloVar.b(rloVar.mMaxDataList, rlo.this.mMinDataList));
                Handler handler3 = rlo.this.mHandler;
                final DataSourceCallback dataSourceCallback3 = this.c;
                handler3.post(new Runnable() { // from class: rmr
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> b(List<HiHealthData> list, List<HiHealthData> list2) {
        if (list.size() != list2.size()) {
            return Collections.emptyList();
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            HiHealthData hiHealthData = new HiHealthData();
            HiHealthData hiHealthData2 = list.get(i);
            HiHealthData hiHealthData3 = list2.get(i);
            hiHealthData.setStartTime(hiHealthData2.getStartTime());
            hiHealthData.setEndTime(hiHealthData2.getEndTime());
            hiHealthData.setCreateTime(hiHealthData2.getCreateTime());
            hiHealthData.putInt("maxBloodOxygen", hiHealthData2.getInt("BloodOxygen"));
            hiHealthData.putInt("minBloodOxygen", hiHealthData3.getInt("BloodOxygen"));
            arrayList.add(hiHealthData);
        }
        return arrayList;
    }

    private void b(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        rkbVar.c(0L);
        rkbVar.d(System.currentTimeMillis());
        rkbVar.c(0);
        LogUtil.a("BloodOxygenOperateImp", "getAllData start");
        d(rkbVar, new DataSourceCallback() { // from class: rmf
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.j(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void j(final DataSourceCallback dataSourceCallback, final int i, List list) {
        LogUtil.a("BloodOxygenOperateImp", "getAllData end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rls
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(list);
        b(hiHealthDataProcess);
        this.mHandler.post(new Runnable() { // from class: rlt
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
            }
        });
    }

    private void d(rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        final ArrayList arrayList = new ArrayList(10);
        e(rkbVar, new DataSourceCallback() { // from class: rlz
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.a(arrayList, dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void a(final List list, DataSourceCallback dataSourceCallback, int i, List list2) {
        if (i == 0 && koq.c(list2) && efb.c()) {
            list.addAll(list2);
        }
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{47201, 47202});
        rkbVar.c(new String[]{"maxBloodOxygen", "minBloodOxygen"});
        rkbVar.c(new int[]{4, 5});
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        b(rkbVar, new DataSourceCallback() { // from class: rln
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rlo.c(list, countDownLatch, i2, (List) obj);
            }
        });
        try {
            LogUtil.a("BloodOxygenOperateImp", "getAllDataImp getMeasureListImp isCountDownZero = ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.h("BloodOxygenOperateImp", "getAllDataImp getMeasureListImp interruptedException");
        }
        if (koq.b(list)) {
            dataSourceCallback.onResponse(1, new ArrayList());
        } else {
            sortHiHealthDataToDesc(list);
            dataSourceCallback.onResponse(0, list);
        }
    }

    static /* synthetic */ void c(List list, CountDownLatch countDownLatch, int i, List list2) {
        if (i == 0 && koq.c(list2)) {
            list.addAll(list2);
        }
        countDownLatch.countDown();
    }

    private void a(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{47201, 47202});
        rkbVar.c(new String[]{"maxBloodOxygen", "minBloodOxygen"});
        rkbVar.c(new int[]{4, 5});
        LogUtil.a("BloodOxygenOperateImp", "getMeasureList start");
        b(rkbVar, new DataSourceCallback() { // from class: rmc
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.g(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void g(final DataSourceCallback dataSourceCallback, final int i, List list) {
        LogUtil.a("BloodOxygenOperateImp", "getMeasureList end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rly
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<rsg> hiHealthDataProcess = hiHealthDataProcess(list);
            this.mHandler.post(new Runnable() { // from class: rlw
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
                }
            });
        }
    }

    private void b(rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        ArrayList<HiAggregateOption> arrayList = new ArrayList<>(10);
        for (int i : rkbVar.g()) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setReadType(0);
            hiAggregateOption.setStartTime(0L);
            hiAggregateOption.setEndTime(System.currentTimeMillis());
            hiAggregateOption.setGroupUnitType(3);
            hiAggregateOption.setAggregateType(i);
            hiAggregateOption.setConstantsKey(rkbVar.d());
            hiAggregateOption.setType(rkbVar.l());
            hiAggregateOption.setSortOrder(1);
            arrayList.add(hiAggregateOption);
        }
        aggregateHiHealthDataEx(arrayList, new DataSourceCallback() { // from class: rmg
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rlo.dPG_(DataSourceCallback.this, i2, (SparseArray) obj);
            }
        });
    }

    static /* synthetic */ void dPG_(DataSourceCallback dataSourceCallback, int i, SparseArray sparseArray) {
        if (i != 0 || koq.b((Collection) sparseArray.get(0))) {
            dataSourceCallback.onResponse(i, new ArrayList());
            return;
        }
        List list = (List) sparseArray.get(0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((HiHealthData) it.next()).putInt("bloodOxygenCardKey", 1003);
        }
        dataSourceCallback.onResponse(i, list);
    }

    private void e(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        final rkb rkbVar = new rkb();
        rkbVar.a(new int[]{2107});
        rkbVar.c(0L);
        rkbVar.d(System.currentTimeMillis());
        rkbVar.c(0);
        LogUtil.a("BloodOxygenOperateImp", "getAbnormalList getLowOxygenList start");
        a(rkbVar, new DataSourceCallback() { // from class: rlx
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.c(rkbVar, dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void c(rkb rkbVar, final DataSourceCallback dataSourceCallback, int i, List list) {
        LogUtil.a("BloodOxygenOperateImp", "getAbnormalList getLowOxygenList end resultCode = ", Integer.valueOf(i));
        final ArrayList arrayList = new ArrayList(10);
        if (i == 0 && koq.c(list)) {
            arrayList.addAll(list);
        }
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        LogUtil.a("BloodOxygenOperateImp", "getAbnormalList getLakeLouiseCardList start");
        e(rkbVar, new DataSourceCallback() { // from class: rlv
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rlo.this.e(arrayList, dataSourceCallback, i2, (List) obj);
            }
        });
    }

    /* synthetic */ void e(List list, final DataSourceCallback dataSourceCallback, int i, List list2) {
        LogUtil.a("BloodOxygenOperateImp", "getAbnormalList getLakeLouiseCardList end resultCode = ", Integer.valueOf(i));
        if (i == 0 && koq.c(list2)) {
            List<HiHealthData> c = c(list2);
            a((List<HiHealthData>) list, c);
            list.addAll(c);
        }
        sortHiHealthDataToDesc(list);
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(list);
        b(hiHealthDataProcess);
        this.mHandler.post(new Runnable() { // from class: rlp
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
            }
        });
    }

    private List<HiHealthData> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt("lakeLouiseScoreKey") > 2) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private void a(List<HiHealthData> list, List<HiHealthData> list2) {
        if (koq.b(list)) {
            return;
        }
        HashSet hashSet = new HashSet(16);
        Iterator<HiHealthData> it = list2.iterator();
        while (it.hasNext()) {
            hashSet.add(Long.valueOf(it.next().getStartTime()));
        }
        Iterator<HiHealthData> it2 = list.iterator();
        while (it2.hasNext()) {
            if (hashSet.contains(Long.valueOf(it2.next().getStartTime()))) {
                it2.remove();
            }
        }
    }

    private void a(rkb rkbVar, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        this.b.clear();
        this.d.clear();
        readHiHealthDataPro(rkbVar, new DataSourceCallback() { // from class: rme
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.dPH_(dataSourceCallback, i, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPH_(DataSourceCallback dataSourceCallback, int i, SparseArray sparseArray) {
        if (i != 0 || sparseArray == null || sparseArray.get(2107) == null) {
            dataSourceCallback.onResponse(i, new ArrayList());
            return;
        }
        List<HiHealthData> list = (List) sparseArray.get(2107);
        this.b = list;
        for (HiHealthData hiHealthData : list) {
            hiHealthData.putInt("bloodOxygenCardKey", 1002);
            hiHealthData.putInt("bloodOxygenKey", hiHealthData.getIntValue());
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        a(1, this.b, new DataSourceCallback() { // from class: rlu
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rlo.this.b(countDownLatch, i2, (List) obj);
            }
        });
        try {
            LogUtil.a("BloodOxygenOperateImp", "getLowOxygenListImp getAltitude isCountDownZero = ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.h("BloodOxygenOperateImp", "getLowOxygenListImp getAltitude interruptedException");
        }
        scg.b(this.b, this.d);
        dataSourceCallback.onResponse(i, this.b);
    }

    /* synthetic */ void b(CountDownLatch countDownLatch, int i, List list) {
        if (i == 0) {
            this.d = list;
        }
        countDownLatch.countDown();
    }

    private void d(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        rkbVar.c(0L);
        rkbVar.d(System.currentTimeMillis());
        rkbVar.c(0);
        LogUtil.a("BloodOxygenOperateImp", "getLakeLouiseCardList start");
        e(rkbVar, new DataSourceCallback() { // from class: rmm
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rlo.this.i(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void i(final DataSourceCallback dataSourceCallback, final int i, List list) {
        LogUtil.a("BloodOxygenOperateImp", "getLakeLouiseCardList end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rlq
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(list);
        b(hiHealthDataProcess);
        this.mHandler.post(new Runnable() { // from class: rlr
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
            }
        });
    }

    private void e(rkb rkbVar, DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        this.c.clear();
        this.f16805a.clear();
        this.e.clear();
        readHiHealthDataPro(rkbVar, new AnonymousClass3(dataSourceCallback));
    }

    /* renamed from: rlo$3, reason: invalid class name */
    class AnonymousClass3 implements DataSourceCallback<SparseArray<List<HiHealthData>>> {
        final /* synthetic */ DataSourceCallback b;

        AnonymousClass3(DataSourceCallback dataSourceCallback) {
            this.b = dataSourceCallback;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: dPJ_, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, SparseArray<List<HiHealthData>> sparseArray) {
            if (i == 0 && sparseArray != null && sparseArray.get(DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()) != null) {
                rlo.this.c = sparseArray.get(DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value());
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                rlo rloVar = rlo.this;
                rloVar.a(0, rloVar.c, new DataSourceCallback() { // from class: rmu
                    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                    public final void onResponse(int i2, Object obj) {
                        rlo.AnonymousClass3.this.b(countDownLatch, i2, (List) obj);
                    }
                });
                try {
                    LogUtil.a("BloodOxygenOperateImp", "getLakeLouiseCardListImp getOxygen isCountDownZero = ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
                } catch (InterruptedException unused) {
                    LogUtil.h("BloodOxygenOperateImp", "getLakeLouiseCardListImp getOxygen interruptedException");
                }
                final CountDownLatch countDownLatch2 = new CountDownLatch(1);
                rlo rloVar2 = rlo.this;
                rloVar2.a(1, rloVar2.f16805a, new DataSourceCallback() { // from class: rmt
                    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
                    public final void onResponse(int i2, Object obj) {
                        rlo.AnonymousClass3.this.c(countDownLatch2, i2, (List) obj);
                    }
                });
                try {
                    LogUtil.a("BloodOxygenOperateImp", "getLakeLouiseCardListImp getAltitude isCountDownZero = ", Boolean.valueOf(countDownLatch2.await(5000L, TimeUnit.MILLISECONDS)));
                } catch (InterruptedException unused2) {
                    LogUtil.h("BloodOxygenOperateImp", "getLakeLouiseCardListImp getAltitude interruptedException");
                }
                this.b.onResponse(i, scg.d(rlo.this.c, rlo.this.f16805a, rlo.this.e, false));
                return;
            }
            Handler handler = rlo.this.mHandler;
            final DataSourceCallback dataSourceCallback = this.b;
            handler.post(new Runnable() { // from class: rms
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        }

        /* synthetic */ void b(CountDownLatch countDownLatch, int i, List list) {
            if (i == 0) {
                rlo.this.f16805a = list;
            }
            countDownLatch.countDown();
        }

        /* synthetic */ void c(CountDownLatch countDownLatch, int i, List list) {
            if (i == 0) {
                rlo.this.e = list;
            }
            countDownLatch.countDown();
        }
    }

    public void a(int i, List<HiHealthData> list, final DataSourceCallback<List<HiHealthData>> dataSourceCallback) {
        ArrayList<HiAggregateOption> arrayList = new ArrayList<>(10);
        for (HiHealthData hiHealthData : list) {
            HiAggregateOption hiAggregateOption = new HiAggregateOption();
            hiAggregateOption.setReadType(0);
            hiAggregateOption.setGroupUnitType(0);
            hiAggregateOption.setAggregateType(1);
            hiAggregateOption.setSortOrder(1);
            if (i == 0) {
                hiAggregateOption.setStartTime(hiHealthData.getEndTime() - 120000);
                hiAggregateOption.setEndTime(hiHealthData.getEndTime() + 120000);
                hiAggregateOption.setConstantsKey(new String[]{"bloodOxygenKey"});
                hiAggregateOption.setType(new int[]{2103});
            } else {
                if (i != 1) {
                    return;
                }
                hiAggregateOption.setStartTime(hiHealthData.getStartTime() - 60000);
                hiAggregateOption.setEndTime(hiHealthData.getEndTime() + 60000);
                hiAggregateOption.setConstantsKey(new String[]{"altitudeKey"});
                hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.ALTITUDE_TYPE.value()});
            }
            arrayList.add(hiAggregateOption);
        }
        aggregateHiHealthDataEx(arrayList, new DataSourceCallback() { // from class: rmd
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rlo.dPF_(DataSourceCallback.this, i2, (SparseArray) obj);
            }
        });
    }

    static /* synthetic */ void dPF_(DataSourceCallback dataSourceCallback, int i, SparseArray sparseArray) {
        if (i != 0 || sparseArray.size() == 0) {
            dataSourceCallback.onResponse(i, new ArrayList());
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            List list = (List) sparseArray.get(sparseArray.keyAt(i2));
            if (koq.c(list)) {
                arrayList.addAll(list);
            }
        }
        dataSourceCallback.onResponse(i, arrayList);
    }

    private void b(List<rsg> list) {
        ArrayList<PrivacyDataModel> arrayList = new ArrayList(10);
        Iterator<rsg> it = list.iterator();
        loop0: while (it.hasNext()) {
            for (PrivacyDataModel privacyDataModel : it.next().d()) {
                if (arrayList.size() >= 20) {
                    break loop0;
                } else {
                    arrayList.add(privacyDataModel);
                }
            }
        }
        for (PrivacyDataModel privacyDataModel2 : arrayList) {
            int i = privacyDataModel2.getInt("bloodOxygenCardKey");
            if (i == 1001) {
                rtv.b(privacyDataModel2.getInt("lakeLouiseScoreKey"));
            } else if (i == 1002) {
                rtv.d(privacyDataModel2.getInt("bloodOxygenKey"));
            }
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return new int[]{2103};
    }
}
