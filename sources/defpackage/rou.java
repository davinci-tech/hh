package defpackage;

import android.os.Handler;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class rou extends BaseOperate {
    private List<HiHealthData> e = new ArrayList(10);
    private List<HiHealthData> d = new ArrayList(10);

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        return new int[]{2002, 2105, 2018};
    }

    public rou() {
        this.pageType = 102;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthNoSource(final DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.a(new int[]{46016, 46017});
        rkbVar.c(new String[]{"maxHeartRate", "minHeartRate"});
        rkbVar.c(new int[]{4, 5});
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
        aggregateHiHealthDataEx(arrayList, new DataSourceCallback() { // from class: roz
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rou.this.dPQ_(dataSourceCallback, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPQ_(final DataSourceCallback dataSourceCallback, final int i, SparseArray sparseArray) {
        if (i != 0 || koq.b((Collection) sparseArray.get(0))) {
            LogUtil.b("HeartRateOperateImp", "aggregateHiHealthDataEx fail");
            this.mHandler.post(new Runnable() { // from class: rox
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            final List<rsg> hiHealthDataProcess = hiHealthDataProcess((List) sparseArray.get(0));
            this.mHandler.post(new Runnable() { // from class: rpa
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{"DATA_POINT_HEARTRATE", "DATA_POINT_REST_HEARTRATE"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{2002, 2018});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(i);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setDeviceUuid(str);
        rkb rkbVar = new rkb();
        rkbVar.e(str2);
        e(rkbVar, hiAggregateOption, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{"DATA_POINT_HEARTRATE", "DATA_POINT_REST_HEARTRATE"});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{2002, 2018});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(1);
        hiAggregateOption.setSortOrder(1);
        rkb rkbVar = new rkb();
        rkbVar.e(str);
        e(rkbVar, hiAggregateOption, dataSourceCallback);
    }

    private void e(rkb rkbVar, HiAggregateOption hiAggregateOption, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        LogUtil.a("HeartRateOperateImp", "aggregateProMax start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass2(dataSourceCallback, hiAggregateOption, rkbVar));
    }

    /* renamed from: rou$2, reason: invalid class name */
    class AnonymousClass2 implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ rkb f16854a;
        final /* synthetic */ HiAggregateOption b;
        final /* synthetic */ DataSourceCallback d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass2(DataSourceCallback dataSourceCallback, HiAggregateOption hiAggregateOption, rkb rkbVar) {
            this.d = dataSourceCallback;
            this.b = hiAggregateOption;
            this.f16854a = rkbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("HeartRateOperateImp", "aggregateProMax end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.d;
                handler.post(new Runnable() { // from class: rpc
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else if (!koq.b(list)) {
                rou.this.e = list;
                this.b.setAggregateType(5);
                rou.this.a(this.f16854a, this.b, this.d);
            } else {
                Handler handler2 = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.d;
                handler2.post(new Runnable() { // from class: rpb
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(rkb rkbVar, HiAggregateOption hiAggregateOption, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        LogUtil.a("HeartRateOperateImp", "aggregateProMin start");
        HiHealthManager.d(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass5(dataSourceCallback));
    }

    /* renamed from: rou$5, reason: invalid class name */
    class AnonymousClass5 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback c;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass5(DataSourceCallback dataSourceCallback) {
            this.c = dataSourceCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("HeartRateOperateImp", "aggregateProMin end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.c;
                handler.post(new Runnable() { // from class: roy
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else {
                if (!koq.b(list)) {
                    rou.this.d = list;
                    rou rouVar = rou.this;
                    final List<rsg> hiHealthDataProcess = rou.this.hiHealthDataProcess(rouVar.a(rouVar.e, rou.this.d));
                    Handler handler2 = rou.this.mHandler;
                    final DataSourceCallback dataSourceCallback2 = this.c;
                    handler2.post(new Runnable() { // from class: rpd
                        @Override // java.lang.Runnable
                        public final void run() {
                            DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
                        }
                    });
                    return;
                }
                Handler handler3 = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback3 = this.c;
                handler3.post(new Runnable() { // from class: rph
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
            }
        }
    }

    protected List<HiHealthData> a(List<HiHealthData> list, List<HiHealthData> list2) {
        if (list.size() != list2.size()) {
            return Collections.emptyList();
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            HiHealthData hiHealthData = new HiHealthData();
            HiHealthData hiHealthData2 = list.get(i);
            hiHealthData.setStartTime(hiHealthData2.getStartTime());
            hiHealthData.setEndTime(hiHealthData2.getEndTime());
            hiHealthData.setCreateTime(hiHealthData2.getCreateTime());
            hiHealthData.putInt("maxHeartRate", e(hiHealthData2));
            HiHealthData hiHealthData3 = list2.get(i);
            if (a(hiHealthData3) == 0) {
                hiHealthData.putInt("minHeartRate", a(hiHealthData2));
            } else {
                hiHealthData.putInt("minHeartRate", a(hiHealthData3));
            }
            arrayList.add(hiHealthData);
        }
        return arrayList;
    }

    private int e(HiHealthData hiHealthData) {
        return (int) Math.max(hiHealthData.getDouble("DATA_POINT_HEARTRATE"), hiHealthData.getDouble("DATA_POINT_REST_HEARTRATE"));
    }

    private int a(HiHealthData hiHealthData) {
        ArrayList arrayList = new ArrayList(3);
        if (hiHealthData.getDouble("DATA_POINT_HEARTRATE") > 0.0d) {
            arrayList.add(Double.valueOf(hiHealthData.getDouble("DATA_POINT_HEARTRATE")));
        }
        if (hiHealthData.getDouble("DATA_POINT_REST_HEARTRATE") > 0.0d) {
            arrayList.add(Double.valueOf(hiHealthData.getDouble("DATA_POINT_REST_HEARTRATE")));
        }
        int size = arrayList.size();
        if (size == 1) {
            return ((Double) arrayList.get(0)).intValue();
        }
        if (size != 2) {
            return 0;
        }
        return (int) Math.min(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue());
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setStartTime(rrb.c(hiHealthData.getStartTime()));
        privacyDataModel.setEndTime(rrb.e(hiHealthData.getStartTime()));
        privacyDataModel.setDataTitle(rre.b(hiHealthData.getInt("maxHeartRate"), hiHealthData.getInt("minHeartRate")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataNoSource */
    public void m843xae5c7554(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{2002, 2018});
        rkbVar.b(true);
        e(rkbVar.o(), rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataBySource */
    public void m842xa7339313(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{2002, 2018});
        rkbVar.b(true);
        e(rkbVar.o(), rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByDeviceSource(long j, String str, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.c(2);
        rkbVar.d(str);
        rkbVar.a(new int[]{2002, 2018});
        e(j, rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByAppSource(long j, String str, String str2, int i, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb rkbVar = new rkb();
        rkbVar.c(i);
        rkbVar.e(str2);
        rkbVar.d(str);
        rkbVar.a(new int[]{2002, 2018});
        e(j, rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1005);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setIntValue(hiHealthData.getIntValue());
        privacyDataModel.setDataTitle(rre.a(hiHealthData.getIntValue()));
    }

    private void e(long j, rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(rrb.c(j));
        hiDataReadOption.setEndTime(rrb.e(j));
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(rkbVar.l());
        hiDataReadOption.setReadType(rkbVar.m());
        hiDataReadOption.setDeviceUuid(rkbVar.i());
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).a(rkbVar.j()).e();
        LogUtil.a("HeartRateOperateImp", "readHiHealthDataProPageQuery start");
        HiHealthManager.d(this.mContext).readHiHealthDataPro(e, new AnonymousClass1(dataSourceCallback, rkbVar));
    }

    /* renamed from: rou$1, reason: invalid class name */
    class AnonymousClass1 implements HiDataReadResultListener {
        final /* synthetic */ DataSourceCallback b;
        final /* synthetic */ rkb d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass1(DataSourceCallback dataSourceCallback, rkb rkbVar) {
            this.b = dataSourceCallback;
            this.d = rkbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("HeartRateOperateImp", "readHiHealthDataProPageQuery end errorCode = ", Integer.valueOf(i));
            if (i != 0) {
                Handler handler = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.b;
                handler.post(new Runnable() { // from class: rpg
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
                return;
            }
            SparseArray sparseArray = new SparseArray();
            if (obj instanceof SparseArray) {
                sparseArray = (SparseArray) obj;
            }
            if (sparseArray.size() == 0) {
                Handler handler2 = rou.this.mHandler;
                final DataSourceCallback dataSourceCallback2 = this.b;
                handler2.post(new Runnable() { // from class: rpe
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(0, new ArrayList());
                    }
                });
                return;
            }
            ArrayList arrayList = new ArrayList(10);
            for (int i3 : this.d.l()) {
                if (sparseArray.get(i3) != null) {
                    arrayList.addAll((Collection) sparseArray.get(i3));
                }
            }
            List<HiHealthData> c = rou.this.c(arrayList);
            rou.this.sortHiHealthDataToDesc(c);
            final List<PrivacyDataModel> proResultOfReadInOneDayNoSource = rou.this.proResultOfReadInOneDayNoSource(c);
            if (this.d.k()) {
                rou.this.setIconResource(proResultOfReadInOneDayNoSource);
            }
            Handler handler3 = rou.this.mHandler;
            final DataSourceCallback dataSourceCallback3 = this.b;
            handler3.post(new Runnable() { // from class: rpf
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, proResultOfReadInOneDayNoSource);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> c(List<HiHealthData> list) {
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list) {
            if (!hashMap.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                hashMap.put(Long.valueOf(hiHealthData.getStartTime()), hiHealthData);
            }
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add((HiHealthData) ((Map.Entry) it.next()).getValue());
        }
        return arrayList;
    }
}
