package defpackage;

import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDeleteListenerEx;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.rpw;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class rpw extends BaseOperate {
    private List<HiHealthData> c = new ArrayList(10);
    private List<HiHealthData> d = new ArrayList(10);
    private List<HiHealthData> j = new ArrayList(10);
    private List<HiHealthData> e = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private List<HiHealthData> f16869a = new ArrayList(10);
    private Map<Integer, List<HiHealthData>> b = new LinkedHashMap(16);

    public rpw() {
        this.pageType = 103;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataNoSource */
    public void m843xae5c7554(rkb rkbVar, final DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        if (!a(rkbVar)) {
            this.mHandler.post(new Runnable() { // from class: rps
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(2, new ArrayList());
                }
            });
            return;
        }
        final int[] iArr = new int[1];
        final ArrayList arrayList = new ArrayList();
        rqt.e().a(this.pageType, rkbVar.o(), rkbVar.h(), new DataSourceCallback() { // from class: rpy
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rpw.c(iArr, arrayList, i, (List) obj);
            }
        });
        if (iArr[0] != 0 || koq.b(arrayList)) {
            this.mHandler.post(new Runnable() { // from class: rpx
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(iArr[0], new ArrayList());
                }
            });
            return;
        }
        List<HiHealthClient> e = e(arrayList);
        int[] iArr2 = {22100, 22000};
        ArrayList arrayList2 = new ArrayList(10);
        for (int i = 0; i < e.size(); i++) {
            arrayList2.add(rrc.b(rrc.b(new rkb(rkbVar.o(), rkbVar.h(), 2, e.get(i).getDeviceUuid(), ""), iArr2), ""));
        }
        ReleaseLogUtil.e("R_SleepOperateImp", "readDayDataNoSource start");
        readHiHealthDataEx(arrayList2, new AnonymousClass4(dataSourceCallback, rkbVar));
    }

    static /* synthetic */ void c(int[] iArr, List list, int i, List list2) {
        Object[] objArr = new Object[4];
        objArr[0] = "readDayDataNoSource getClient resultCode = ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = ", client size = ";
        objArr[3] = Integer.valueOf(list2 == null ? 0 : list2.size());
        ReleaseLogUtil.e("R_SleepOperateImp", objArr);
        iArr[0] = i;
        if (koq.c(list2)) {
            list.addAll(list2);
        }
    }

    /* renamed from: rpw$4, reason: invalid class name */
    class AnonymousClass4 implements DataSourceCallback<SparseArray<List<HiHealthData>>> {
        final /* synthetic */ rkb b;
        final /* synthetic */ DataSourceCallback d;

        AnonymousClass4(DataSourceCallback dataSourceCallback, rkb rkbVar) {
            this.d = dataSourceCallback;
            this.b = rkbVar;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: dPU_, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, SparseArray<List<HiHealthData>> sparseArray) {
            ReleaseLogUtil.e("R_SleepOperateImp", "readDayDataNoSource end resultCode = ", Integer.valueOf(i));
            if (i == 0 && sparseArray != null && sparseArray.size() != 0) {
                rpw.this.dPT_(sparseArray);
                final List<PrivacyDataModel> c = rpw.this.c(this.b);
                if (this.b.k()) {
                    rpw.this.setIconResource(c);
                }
                Handler handler = rpw.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.d;
                handler.post(new Runnable() { // from class: rqk
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(i, c);
                    }
                });
                return;
            }
            Handler handler2 = rpw.this.mHandler;
            final DataSourceCallback dataSourceCallback2 = this.d;
            handler2.post(new Runnable() { // from class: rqg
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataBySource */
    public void m842xa7339313(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        HiDataReadProOption e = HiDataReadProOption.builder().e(rrc.b(rkbVar, new int[]{22100, 22000})).a(rkbVar.j()).e();
        ReleaseLogUtil.e("R_SleepOperateImp", "readDayDataBySource start");
        readHiHealthDataPro(e, new AnonymousClass1(dataSourceCallback, rkbVar));
    }

    /* renamed from: rpw$1, reason: invalid class name */
    class AnonymousClass1 implements DataSourceCallback<SparseArray<List<HiHealthData>>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ rkb f16870a;
        final /* synthetic */ DataSourceCallback e;

        AnonymousClass1(DataSourceCallback dataSourceCallback, rkb rkbVar) {
            this.e = dataSourceCallback;
            this.f16870a = rkbVar;
        }

        @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
        /* renamed from: dPV_, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, SparseArray<List<HiHealthData>> sparseArray) {
            ReleaseLogUtil.e("R_SleepOperateImp", "readDayDataBySource end resultCode = ", Integer.valueOf(i));
            if (i == 0 && sparseArray != null && sparseArray.size() != 0) {
                rpw.this.dPT_(sparseArray);
                final List<PrivacyDataModel> c = rpw.this.c(this.f16870a);
                if (this.f16870a.k()) {
                    rpw.this.setIconResource(c);
                }
                Handler handler = rpw.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.e;
                handler.post(new Runnable() { // from class: rqi
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(i, c);
                    }
                });
                return;
            }
            Handler handler2 = rpw.this.mHandler;
            final DataSourceCallback dataSourceCallback2 = this.e;
            handler2.post(new Runnable() { // from class: rql
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        }
    }

    private boolean a(rkb rkbVar) {
        HiDataReadProOption e = HiDataReadProOption.builder().e(rrc.b(rkbVar, new int[]{22100})).a(rkbVar.j()).e();
        ReleaseLogUtil.e("R_SleepOperateImp", "downLoadDayData start");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        readHiHealthDataPro(e, new DataSourceCallback<SparseArray<List<HiHealthData>>>() { // from class: rpw.2
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            /* renamed from: dPW_, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, SparseArray<List<HiHealthData>> sparseArray) {
                ReleaseLogUtil.e("R_SleepOperateImp", "downLoadDayData end resultCode = ", Integer.valueOf(i));
                countDownLatch.countDown();
            }
        });
        try {
            boolean await = countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            ReleaseLogUtil.e("R_SleepOperateImp", "downLoadDayData isCountDownZero = ", Boolean.valueOf(await));
            return await;
        } catch (InterruptedException unused) {
            ReleaseLogUtil.e("R_SleepOperateImp", "downLoadDayData exception");
            return false;
        }
    }

    private List<HiHealthClient> e(List<HiHealthClient> list) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.addAll(e(list, "06D"));
        arrayList.addAll(e(list, "06E"));
        arrayList.addAll(e(list, "054"));
        arrayList.addAll(e(list, "00E"));
        arrayList.addAll(e(list, "011"));
        arrayList.addAll(e(list, "001"));
        if (koq.c(list)) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    private List<HiHealthClient> e(List<HiHealthClient> list, String str) {
        ArrayList arrayList = new ArrayList(10);
        if (!koq.b(list) && !TextUtils.isEmpty(str)) {
            Iterator<HiHealthClient> it = list.iterator();
            while (it.hasNext()) {
                HiHealthClient next = it.next();
                if (str.equals(rrf.e(String.valueOf(next.getHiDeviceInfo().getDeviceType())))) {
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dPT_(SparseArray<List<HiHealthData>> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            return;
        }
        ArrayList<HiHealthData> arrayList = new ArrayList(10);
        List<HiHealthData> list = sparseArray.get(22100);
        List<HiHealthData> list2 = sparseArray.get(22000);
        if (koq.c(list)) {
            arrayList.addAll(list);
        }
        if (koq.c(list2)) {
            arrayList.addAll(list2);
        }
        for (HiHealthData hiHealthData : arrayList) {
            int clientId = hiHealthData.getClientId();
            int type = hiHealthData.getType();
            if (type != 22001 && type != 22002) {
                switch (type) {
                    case 22101:
                    case 22102:
                    case 22103:
                        break;
                    default:
                        switch (type) {
                            case 22106:
                                this.f16869a.add(hiHealthData);
                                continue;
                            case 22107:
                                this.e.add(hiHealthData);
                                this.f16869a.add(hiHealthData);
                                continue;
                        }
                }
            }
            if (this.b.containsKey(Integer.valueOf(clientId))) {
                this.b.get(Integer.valueOf(clientId)).add(hiHealthData);
            } else {
                ArrayList arrayList2 = new ArrayList(10);
                arrayList2.add(hiHealthData);
                this.b.put(Integer.valueOf(clientId), arrayList2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PrivacyDataModel> c(rkb rkbVar) {
        ArrayList arrayList = new ArrayList(10);
        Iterator<Map.Entry<Integer, List<HiHealthData>>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            List<HiHealthData> value = it.next().getValue();
            sortHiHealthDataToDesc(value);
            arrayList.addAll(a(value));
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(convertToDataModel((HiHealthData) it2.next(), rkbVar));
        }
        if (koq.b(this.e)) {
            arrayList2.addAll(d(rkbVar));
        } else {
            arrayList2.addAll(e(rkbVar));
        }
        this.b.clear();
        this.e.clear();
        this.f16869a.clear();
        return arrayList2;
    }

    private List<PrivacyDataModel> e(rkb rkbVar) {
        List<HiHealthData> a2 = a(this.e);
        ArrayList arrayList = new ArrayList();
        if (koq.b(a2)) {
            return arrayList;
        }
        List<HiHealthData> a3 = a(this.f16869a);
        for (HiHealthData hiHealthData : a2) {
            PrivacyDataModel convertToDataModel = convertToDataModel(hiHealthData, rkbVar);
            convertToDataModel.putLong("sleepStartTime", hiHealthData.getStartTime());
            convertToDataModel.putLong("sleepEndTime", hiHealthData.getEndTime());
            arrayList.add(convertToDataModel);
            Iterator<HiHealthData> it = a3.iterator();
            while (true) {
                if (it.hasNext()) {
                    HiHealthData next = it.next();
                    if (hiHealthData.getStartTime() >= next.getStartTime() && hiHealthData.getEndTime() <= next.getEndTime()) {
                        convertToDataModel.putLong("bedStartTime", next.getStartTime());
                        convertToDataModel.putLong("bedEndTime", next.getEndTime());
                        break;
                    }
                    if (hiHealthData.getEndTime() > next.getEndTime()) {
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    private List<PrivacyDataModel> d(rkb rkbVar) {
        List<HiHealthData> a2 = a(this.f16869a);
        ArrayList arrayList = new ArrayList();
        if (koq.b(a2)) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : a2) {
            PrivacyDataModel convertToDataModel = convertToDataModel(hiHealthData, rkbVar);
            convertToDataModel.putLong("bedStartTime", hiHealthData.getStartTime());
            convertToDataModel.putLong("bedEndTime", hiHealthData.getEndTime());
            arrayList.add(convertToDataModel);
        }
        return arrayList;
    }

    private List<HiHealthData> a(List<HiHealthData> list) {
        List<List> d = rrd.d(list, new DivideConditional() { // from class: rqd
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional
            public final boolean isDivide(Object obj, Object obj2) {
                return rpw.b((HiHealthData) obj, (HiHealthData) obj2);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (List list2 : d) {
            if (!koq.b(list2)) {
                HiHealthData hiHealthData = new HiHealthData();
                HiHealthData hiHealthData2 = (HiHealthData) list2.get(0);
                HiHealthData hiHealthData3 = (HiHealthData) list2.get(list2.size() - 1);
                hiHealthData.setEndTime(hiHealthData2.getEndTime());
                hiHealthData.setModifiedTime(hiHealthData2.getModifiedTime());
                hiHealthData.setType(hiHealthData2.getType());
                hiHealthData.setClientId(hiHealthData2.getClientId());
                hiHealthData.setStartTime(hiHealthData3.getStartTime());
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a("SleepOperateImp", "splitData end, splitDataList list size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    static /* synthetic */ boolean b(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        return hiHealthData.getStartTime() != hiHealthData2.getEndTime();
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readGroupDataNoSource */
    public void m849xe5ecdbf1(rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiDataAggregateProOption a2 = rrc.a(rrc.b(rkbVar, new int[]{44109, 44110, 44105, 44108, 44004}, new String[]{"stat_core_sleep_bed_key", "stat_core_sleep_device_category_key", "stat_core_sleep_night_key", "stat_core_sleep_noon_key", "stat_common_sleep_key"}, 1, 3), rkbVar.j());
        LogUtil.a("SleepOperateImp", "readGroupDataNoSource aggregateHiHealthDataPro start");
        aggregateHiHealthDataPro(a2, new DataSourceCallback() { // from class: rqa
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rpw.this.e(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void e(final DataSourceCallback dataSourceCallback, final int i, List list) {
        LogUtil.a("SleepOperateImp", "readGroupDataNoSource aggregateHiHealthDataPro end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rqc
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        }
        final ArrayList arrayList = new ArrayList(10);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            PrivacyDataModel e = e((HiHealthData) it.next());
            if (e != null) {
                arrayList.add(e);
            }
        }
        this.mHandler.post(new Runnable() { // from class: rqe
            @Override // java.lang.Runnable
            public final void run() {
                rpw.this.a(dataSourceCallback, i, arrayList);
            }
        });
    }

    /* synthetic */ void a(DataSourceCallback dataSourceCallback, int i, List list) {
        dataSourceCallback.onResponse(i, groupByMonth(list));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readGroupDataBySource */
    public void m848xdec3f9b0(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        if (rrb.c(rkbVar.j(), rkbVar.i())) {
            b(rkbVar, dataSourceCallback);
        } else {
            d(rkbVar, dataSourceCallback);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setStartTime(rrb.b(hiHealthData.getStartTime()));
        privacyDataModel.setEndTime(rrb.a(hiHealthData.getStartTime()));
        privacyDataModel.setDataTitle(rre.i(hiHealthData.getInt("sleepNum")));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getEndTime() + 14399999));
        privacyDataModel.putBoolean("hasCoreSleep", hiHealthData.getBoolean("hasCoreSleep"));
        privacyDataModel.putBoolean("hasCommonSleep", hiHealthData.getBoolean("hasCommonSleep"));
    }

    private PrivacyDataModel e(HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("stat_core_sleep_bed_key");
        int i2 = hiHealthData.getInt("stat_core_sleep_device_category_key");
        int i3 = hiHealthData.getInt("stat_core_sleep_night_key");
        int i4 = hiHealthData.getInt("stat_core_sleep_noon_key");
        int i5 = hiHealthData.getInt("stat_common_sleep_key") / 60;
        int i6 = (i3 <= 0 || i2 == 1) ? i5 > 0 ? i5 : i3 > 0 ? i3 : i > 0 ? i : i4 : i3 + i4;
        if (i6 == 0) {
            return null;
        }
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        if (i3 > 0 || i4 > 0 || i > 0) {
            privacyDataModel.putBoolean("hasCoreSleep", true);
        }
        if (i5 > 0) {
            privacyDataModel.putBoolean("hasCommonSleep", true);
        }
        privacyDataModel.setPageType(this.pageType);
        privacyDataModel.setDataTitle(rre.i(i6));
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
        privacyDataModel.setStartTime(rrb.b(hiHealthData.getStartTime()));
        privacyDataModel.setEndTime(rrb.a(hiHealthData.getStartTime()));
        privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
        privacyDataModel.setType(hiHealthData.getType());
        privacyDataModel.setClientId(hiHealthData.getClientId());
        return privacyDataModel;
    }

    private void b(rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption c = rrc.c(rkbVar, new int[]{22107, 22106}, new String[]{"sleep_sum", "bed_sum"}, 1);
        HiDataAggregateProOption c2 = new HiDataAggregateProOption.Builder().c(c).c(rkbVar.j()).c();
        c.setFilter("GET_SESSION_SLEEP_DATA");
        ReleaseLogUtil.e("R_SleepOperateImp", "readGroupDataOfManual start ");
        aggregateHiHealthDataPro(c2, new DataSourceCallback() { // from class: rpt
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rpw.this.c(dataSourceCallback, i, (List) obj);
            }
        });
    }

    /* synthetic */ void c(final DataSourceCallback dataSourceCallback, final int i, final List list) {
        ReleaseLogUtil.e("R_SleepOperateImp", "readGroupDataOfManual end resultCode = ", Integer.valueOf(i));
        if (i != 0 || koq.b(list)) {
            this.mHandler.post(new Runnable() { // from class: rqf
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: rpv
                @Override // java.lang.Runnable
                public final void run() {
                    rpw.this.d(dataSourceCallback, i, list);
                }
            });
        }
    }

    /* synthetic */ void d(DataSourceCallback dataSourceCallback, int i, List list) {
        dataSourceCallback.onResponse(i, hiHealthDataProcess(d((List<HiHealthData>) list)));
    }

    private List<HiHealthData> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        arrayList.addAll(list);
        sortHiHealthDataToDesc(arrayList);
        Iterator<HiHealthData> it = arrayList.iterator();
        HiHealthData hiHealthData = null;
        while (it.hasNext()) {
            if (hiHealthData == null) {
                hiHealthData = it.next();
            } else {
                HiHealthData next = it.next();
                hiHealthData.putBoolean("hasCoreSleep", true);
                hiHealthData.putBoolean("hasCommonSleep", false);
                if (rrb.b(hiHealthData.getStartTime()) == rrb.b(next.getStartTime())) {
                    hiHealthData.putInt("sleepNum", Math.max(hiHealthData.getInt("sleep_sum"), next.getInt("sleep_sum")));
                    it.remove();
                    hiHealthData = null;
                } else {
                    int i = hiHealthData.getInt("sleep_sum");
                    if (i <= 0) {
                        i = hiHealthData.getInt("bed_sum");
                    }
                    hiHealthData.putInt("sleepNum", i);
                    hiHealthData = next;
                }
                if (hiHealthData != null && !it.hasNext()) {
                    hiHealthData.putBoolean("hasCoreSleep", true);
                    hiHealthData.putBoolean("hasCommonSleep", false);
                    int i2 = hiHealthData.getInt("sleep_sum");
                    if (i2 <= 0) {
                        i2 = hiHealthData.getInt("bed_sum");
                    }
                    hiHealthData.putInt("sleepNum", i2);
                }
            }
        }
        HiHealthData hiHealthData2 = arrayList.get(arrayList.size() - 1);
        if (!hiHealthData2.getBoolean("hasCoreSleep")) {
            hiHealthData2.putBoolean("hasCoreSleep", true);
            hiHealthData2.putBoolean("hasCommonSleep", false);
            int i3 = hiHealthData2.getInt("sleep_sum");
            if (i3 <= 0) {
                i3 = hiHealthData2.getInt("bed_sum");
            }
            hiHealthData2.putInt("sleepNum", i3);
        }
        return arrayList;
    }

    private void d(rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{"sleepNum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setType(new int[]{22100});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(rkbVar.m());
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("GET_SESSION_SLEEP_DATA");
        hiAggregateOption.setDeviceUuid(rkbVar.i());
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        ReleaseLogUtil.e("R_SleepOperateImp", "querySleepSource start");
        HiHealthNativeApi.a(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass5(dataSourceCallback, hiAggregateOption, rkbVar));
    }

    /* renamed from: rpw$5, reason: invalid class name */
    class AnonymousClass5 implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ rkb f16872a;
        final /* synthetic */ DataSourceCallback d;
        final /* synthetic */ HiAggregateOption e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass5(DataSourceCallback dataSourceCallback, HiAggregateOption hiAggregateOption, rkb rkbVar) {
            this.d = dataSourceCallback;
            this.e = hiAggregateOption;
            this.f16872a = rkbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.e("R_SleepOperateImp", "querySleepSource end errorCode = " + i);
            if (i != 0) {
                Handler handler = rpw.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.d;
                handler.post(new Runnable() { // from class: rqh
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else {
                if (koq.c(list)) {
                    rpw.this.c.addAll(list);
                }
                rpw.this.c(this.e, this.f16872a, (DataSourceCallback<List<rsg>>) this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HiAggregateOption hiAggregateOption, rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        hiAggregateOption.setType(new int[]{22105});
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        ReleaseLogUtil.e("R_SleepOperateImp", "queryNoonSleepSource start");
        HiHealthNativeApi.a(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass3(dataSourceCallback, hiAggregateOption, rkbVar));
    }

    /* renamed from: rpw$3, reason: invalid class name */
    class AnonymousClass3 implements HiAggregateListener {
        final /* synthetic */ rkb b;
        final /* synthetic */ HiAggregateOption c;
        final /* synthetic */ DataSourceCallback d;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass3(DataSourceCallback dataSourceCallback, HiAggregateOption hiAggregateOption, rkb rkbVar) {
            this.d = dataSourceCallback;
            this.c = hiAggregateOption;
            this.b = rkbVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.e("R_SleepOperateImp", "queryNoonSleepSource end errorCode = " + i);
            if (i != 0) {
                Handler handler = rpw.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.d;
                handler.post(new Runnable() { // from class: rqj
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
            } else {
                if (koq.c(list)) {
                    rpw.this.j.addAll(list);
                }
                rpw.this.b(this.c, this.b, this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiAggregateOption hiAggregateOption, rkb rkbVar, DataSourceCallback<List<rsg>> dataSourceCallback) {
        hiAggregateOption.setType(new int[]{22000});
        HiDataAggregateProOption c = HiDataAggregateProOption.builder().c(hiAggregateOption).c(rkbVar.j()).c();
        ReleaseLogUtil.e("R_SleepOperateImp", "queryCommonSleepBySource start");
        HiHealthNativeApi.a(this.mContext).aggregateHiHealthDataPro(c, new AnonymousClass9(dataSourceCallback));
    }

    /* renamed from: rpw$9, reason: invalid class name */
    class AnonymousClass9 implements HiAggregateListener {
        final /* synthetic */ DataSourceCallback b;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass9(DataSourceCallback dataSourceCallback) {
            this.b = dataSourceCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.e("R_SleepOperateImp", "queryCommonSleepBySource end errorCode = " + i);
            if (i != 0) {
                Handler handler = rpw.this.mHandler;
                final DataSourceCallback dataSourceCallback = this.b;
                handler.post(new Runnable() { // from class: rqq
                    @Override // java.lang.Runnable
                    public final void run() {
                        DataSourceCallback.this.onResponse(1, new ArrayList());
                    }
                });
                return;
            }
            if (koq.c(list)) {
                rpw.this.d.addAll(list);
            }
            rpw rpwVar = rpw.this;
            final List<HiHealthData> c = rpwVar.c((List<HiHealthData>) rpwVar.c, (List<HiHealthData>) rpw.this.d, (List<HiHealthData>) rpw.this.j);
            rpw.this.sortHiHealthDataToDesc(c);
            Handler handler2 = rpw.this.mHandler;
            final DataSourceCallback dataSourceCallback2 = this.b;
            handler2.post(new Runnable() { // from class: rqm
                @Override // java.lang.Runnable
                public final void run() {
                    rpw.AnonymousClass9.this.e(dataSourceCallback2, c);
                }
            });
        }

        /* synthetic */ void e(DataSourceCallback dataSourceCallback, List list) {
            dataSourceCallback.onResponse(0, rpw.this.hiHealthDataProcess(list));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> c(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3) {
        HashMap hashMap = new HashMap(16);
        Map<String, HiHealthData> b = b(list);
        Map<String, HiHealthData> b2 = b(list3);
        Map<String, HiHealthData> b3 = b(list2);
        int i = 0;
        for (Map.Entry<String, HiHealthData> entry : b.entrySet()) {
            String key = entry.getKey();
            HiHealthData value = entry.getValue();
            int i2 = value.getInt("sleepNum");
            if (b2.containsKey(key)) {
                i = b2.get(key).getInt("sleepNum");
            }
            if (i2 == i && b3.containsKey(key)) {
                value.putInt("sleepNum", b3.get(key).getInt("sleepNum"));
            }
            hashMap.put(key, value);
        }
        for (Map.Entry<String, HiHealthData> entry2 : b3.entrySet()) {
            String key2 = entry2.getKey();
            if (!hashMap.containsKey(key2)) {
                hashMap.put(key2, entry2.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(10);
        for (Map.Entry entry3 : hashMap.entrySet()) {
            String str = (String) entry3.getKey();
            HiHealthData hiHealthData = (HiHealthData) entry3.getValue();
            if (b.containsKey(str) || b2.containsKey(str)) {
                hiHealthData.putBoolean("hasCoreSleep", true);
            }
            if (b3.containsKey(str)) {
                hiHealthData.putBoolean("hasCommonSleep", true);
            }
            arrayList.add((HiHealthData) entry3.getValue());
        }
        return arrayList;
    }

    private Map<String, HiHealthData> b(List<HiHealthData> list) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            return hashMap;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (HiHealthData hiHealthData : list) {
            hashMap.put(simpleDateFormat.format(Long.valueOf(hiHealthData.getStartTime() + 14400000)), hiHealthData);
        }
        return hashMap;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void deleteDatas(List<PrivacyDataModel> list, rkb rkbVar, final DataSourceCallback<Boolean> dataSourceCallback) {
        ArrayList<PrivacyDataModel> arrayList = new ArrayList(10);
        ArrayList<PrivacyDataModel> arrayList2 = new ArrayList(10);
        ArrayList arrayList3 = new ArrayList(10);
        for (PrivacyDataModel privacyDataModel : list) {
            if (privacyDataModel.getBoolean("hasCoreSleep")) {
                arrayList.add(privacyDataModel);
            }
            if (privacyDataModel.getBoolean("hasCommonSleep")) {
                arrayList2.add(privacyDataModel);
            }
            arrayList3.add(privacyDataModel);
        }
        ArrayList arrayList4 = new ArrayList(10);
        d(rkbVar, arrayList3, arrayList4);
        if (koq.c(arrayList)) {
            HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
            hiDataDeleteOption.setTypes(qmx.c);
            ArrayList arrayList5 = new ArrayList(10);
            for (PrivacyDataModel privacyDataModel2 : arrayList) {
                arrayList5.add(new HiTimeInterval(privacyDataModel2.getStartTime(), privacyDataModel2.getEndTime()));
            }
            hiDataDeleteOption.setTimes(arrayList5);
            arrayList4.add(rrc.a(hiDataDeleteOption, rkbVar));
        }
        if (koq.c(arrayList2)) {
            HiDataDeleteOption hiDataDeleteOption2 = new HiDataDeleteOption();
            hiDataDeleteOption2.setTypes(qmx.e);
            ArrayList arrayList6 = new ArrayList(10);
            for (PrivacyDataModel privacyDataModel3 : arrayList2) {
                arrayList6.add(new HiTimeInterval(privacyDataModel3.getStartTime(), privacyDataModel3.getEndTime()));
            }
            hiDataDeleteOption2.setTimes(arrayList6);
            arrayList4.add(rrc.a(hiDataDeleteOption2, rkbVar));
        }
        ReleaseLogUtil.e("R_SleepOperateImp", "deleteHiHealthDataProEx start");
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthDataProEx(arrayList4, new HiDeleteListenerEx() { // from class: rpz
            @Override // com.huawei.hihealth.data.listener.HiDeleteListenerEx
            public final void onResult(Map map) {
                rpw.this.e(dataSourceCallback, map);
            }
        });
    }

    /* synthetic */ void e(final DataSourceCallback dataSourceCallback, Map map) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("deleteHiHealthDataProEx end callbackMap size = ");
        sb.append(map);
        objArr[0] = Integer.valueOf(sb.toString() == null ? 0 : map.size());
        ReleaseLogUtil.e("R_SleepOperateImp", objArr);
        if (map == null || map.size() == 0) {
            this.mHandler.post(new Runnable() { // from class: rqb
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, true);
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: rpu
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(1, false);
                }
            });
        }
    }

    private void d(rkb rkbVar, List<PrivacyDataModel> list, List<HiDataDeleteProOption> list2) {
        if (koq.c(list)) {
            HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
            hiDataDeleteOption.setTypes(qmx.d);
            ArrayList arrayList = new ArrayList(10);
            for (PrivacyDataModel privacyDataModel : list) {
                arrayList.add(new HiTimeInterval(privacyDataModel.getStartTime(), privacyDataModel.getEndTime()));
            }
            hiDataDeleteOption.setTimes(arrayList);
            list2.add(rrc.a(hiDataDeleteOption, rkbVar));
        }
    }
}
