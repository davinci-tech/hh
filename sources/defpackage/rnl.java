package defpackage;

import android.util.SparseArray;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDeleteListenerEx;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback;
import com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class rnl extends BaseOperate {

    /* renamed from: a, reason: collision with root package name */
    private List<HiHealthData> f16833a;
    private List<HiHealthData> b;
    private List<HiHealthData> e;

    public rnl() {
        this.pageType = 106;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readCategoryGroupData(rkb rkbVar, DataSourceCallback<Map<Integer, List<rsg>>> dataSourceCallback) {
        if (rkbVar == null) {
            LogUtil.h("BodyTemperature", "readCategoryGroupData optionBean is null");
            return;
        }
        int m = rkbVar.m();
        if (m == 1 || m == 2 || m == 3) {
            i(rkbVar, dataSourceCallback);
        } else {
            g(rkbVar, dataSourceCallback);
        }
    }

    private void g(final rkb rkbVar, final DataSourceCallback<Map<Integer, List<rsg>>> dataSourceCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: roa
            @Override // java.lang.Runnable
            public final void run() {
                rnl.this.d(rkbVar, dataSourceCallback);
            }
        });
    }

    /* synthetic */ void d(rkb rkbVar, final DataSourceCallback dataSourceCallback) {
        final HashMap hashMap = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        j(rkbVar, new DataSourceCallback() { // from class: rod
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.dPN_(dataSourceCallback, hashMap, countDownLatch, i, (SparseArray) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("BodyTemperature", e.toString());
        }
        o(rkbVar, new DataSourceCallback() { // from class: rob
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.dPO_(dataSourceCallback, hashMap, i, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPN_(final DataSourceCallback dataSourceCallback, final Map map, CountDownLatch countDownLatch, final int i, SparseArray sparseArray) {
        if (i != 0) {
            this.mHandler.post(new Runnable() { // from class: rnp
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, map);
                }
            });
            return;
        }
        if (sparseArray != null) {
            List<HiHealthData> list = (List) sparseArray.get(DicDataTypeUtil.DataType.MAX_BODY_TEMPERATURE.value());
            List<HiHealthData> list2 = (List) sparseArray.get(DicDataTypeUtil.DataType.MIN_BODY_TEMPERATURE.value());
            List<HiHealthData> list3 = (List) sparseArray.get(DicDataTypeUtil.DataType.MAX_SUSPECTED_HIGH_TEMPERATURE.value());
            List<HiHealthData> list4 = (List) sparseArray.get(DicDataTypeUtil.DataType.MIN_SUSPECTED_HIGH_TEMPERATURE.value());
            Object[] objArr = new Object[8];
            objArr[0] = "readCategoryGroupDataNoSource max=";
            objArr[1] = Integer.valueOf(list == null ? 0 : list.size());
            objArr[2] = ", min=";
            objArr[3] = Integer.valueOf(list2 == null ? 0 : list2.size());
            objArr[4] = ", maxSus=";
            objArr[5] = Integer.valueOf(list3 == null ? 0 : list3.size());
            objArr[6] = ", minSus=";
            objArr[7] = Integer.valueOf(list4 != null ? list4.size() : 0);
            ReleaseLogUtil.e("R_BodyTemperature", objArr);
            this.f16833a = mergeMaxMinList(list, list2, list3, list4);
            this.e = mergeMaxMinList((List) sparseArray.get(DicDataTypeUtil.DataType.MAX_SKIN_TEMPERATURE.value()), (List) sparseArray.get(DicDataTypeUtil.DataType.MIN_SKIN_TEMPERATURE.value()));
        }
        countDownLatch.countDown();
    }

    /* synthetic */ void dPO_(final DataSourceCallback dataSourceCallback, final Map map, final int i, SparseArray sparseArray) {
        if (i != 0) {
            this.mHandler.post(new Runnable() { // from class: ron
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, map);
                }
            });
        }
        if (sparseArray != null && sparseArray.size() != 0) {
            this.b = d((List<HiHealthData>) sparseArray.get(0), (List<HiHealthData>) sparseArray.get(1));
        }
        map.put(1, hiHealthDataProcess(e(this.b, this.f16833a)));
        map.put(2, hiHealthDataProcess(this.e));
        this.mHandler.post(new Runnable() { // from class: rom
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, map);
            }
        });
    }

    private List<HiHealthData> d(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList();
        if (!koq.b(list) && !koq.b(list2) && list.size() == list2.size()) {
            for (int i = 0; i < list.size(); i++) {
                HiHealthData hiHealthData = new HiHealthData();
                HiHealthData hiHealthData2 = list.get(i);
                HiHealthData hiHealthData3 = list2.get(i);
                hiHealthData.setStartTime(rrb.c(hiHealthData2.getStartTime()));
                hiHealthData.setEndTime(rrb.e(hiHealthData2.getEndTime()));
                hiHealthData.setModifiedTime(hiHealthData2.getModifiedTime());
                hiHealthData.putDouble("maxTemperature", hiHealthData2.getDouble("maxOldBodyTemperature"));
                hiHealthData.putDouble("minTemperature", hiHealthData3.getDouble("minOldBodyTemperature"));
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private List<HiHealthData> e(List<HiHealthData> list, List<HiHealthData> list2) {
        HiHealthData next;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return list2;
        }
        if (koq.b(list2)) {
            return list;
        }
        arrayList.addAll(list);
        arrayList.addAll(list2);
        sortHiHealthDataToDesc(arrayList);
        Iterator<HiHealthData> it = arrayList.iterator();
        while (true) {
            HiHealthData hiHealthData = null;
            while (it.hasNext()) {
                if (hiHealthData == null) {
                    hiHealthData = it.next();
                } else {
                    next = it.next();
                    if (hiHealthData.getStartTime() == next.getStartTime()) {
                        break;
                    }
                    hiHealthData = next;
                }
            }
            return arrayList;
            hiHealthData.putDouble("maxTemperature", Math.max(hiHealthData.getDouble("maxTemperature"), next.getDouble("maxTemperature")));
            hiHealthData.putDouble("minTemperature", Math.min(hiHealthData.getDouble("minTemperature"), next.getDouble("minTemperature")));
            it.remove();
        }
    }

    private void j(rkb rkbVar, DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiDataReadProOption.Builder().e(rrc.b(rkbVar, new int[]{DicDataTypeUtil.DataType.MAX_BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.MIN_BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.MAX_SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.MIN_SKIN_TEMPERATURE.value(), DicDataTypeUtil.DataType.MAX_SUSPECTED_HIGH_TEMPERATURE.value(), DicDataTypeUtil.DataType.MIN_SUSPECTED_HIGH_TEMPERATURE.value()})).e());
        readHiHealthDataEx(arrayList, dataSourceCallback);
    }

    private void o(rkb rkbVar, DataSourceCallback<SparseArray<List<HiHealthData>>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"maxOldBodyTemperature", "minOldBodyTemperature"};
        int[] iArr = {4, 5};
        for (int i = 0; i < 2; i++) {
            arrayList.add(new HiDataAggregateProOption.Builder().c(rrc.c(rkbVar, new int[]{2104}, new String[]{strArr[i]}, iArr[i])).c());
        }
        aggregateHiHealthDataProEx(arrayList, dataSourceCallback);
    }

    private void i(final rkb rkbVar, final DataSourceCallback<Map<Integer, List<rsg>>> dataSourceCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rnu
            @Override // java.lang.Runnable
            public final void run() {
                rnl.this.a(rkbVar, dataSourceCallback);
            }
        });
    }

    /* synthetic */ void a(rkb rkbVar, final DataSourceCallback dataSourceCallback) {
        final HashMap hashMap = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        h(rkbVar, new DataSourceCallback() { // from class: rok
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.c(hashMap, countDownLatch, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("BodyTemperature", e.toString());
        }
        n(rkbVar, new DataSourceCallback() { // from class: ror
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.b(hashMap, dataSourceCallback, i, (List) obj);
            }
        });
    }

    static /* synthetic */ void c(Map map, CountDownLatch countDownLatch, int i, List list) {
        if (i == 0) {
            map.put(1, list);
        }
        countDownLatch.countDown();
    }

    /* synthetic */ void b(final Map map, final DataSourceCallback dataSourceCallback, int i, List list) {
        if (i == 0) {
            map.put(2, list);
        }
        this.mHandler.post(new Runnable() { // from class: rnv
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(0, map);
            }
        });
    }

    private void h(rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList();
        String[][] strArr = {new String[]{"maxOldBodyTemperature", "maxNewBodyTemperature", "maxSuspectBodyTemperature"}, new String[]{"minOldBodyTemperature", "minNewBodyTemperature", "minSuspectBodyTemperature"}};
        int[] iArr = {4, 5};
        for (int i = 0; i < 2; i++) {
            arrayList.add(new HiDataAggregateProOption.Builder().c(rrc.c(rkbVar, new int[]{2104, DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()}, strArr[i], iArr[i])).c(rkbVar.j()).c());
        }
        LogUtil.a("BodyTemperature", "readBodyGroupDataBySource start");
        aggregateHiHealthDataProEx(arrayList, new DataSourceCallback() { // from class: rnt
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rnl.this.dPM_(dataSourceCallback, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPM_(DataSourceCallback dataSourceCallback, int i, SparseArray sparseArray) {
        LogUtil.a("BodyTemperature", "readBodyGroupDataBySource end resultCode = ", Integer.valueOf(i));
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            dataSourceCallback.onResponse(i, new ArrayList());
        } else {
            dataSourceCallback.onResponse(i, hiHealthDataProcess(a((List<HiHealthData>) sparseArray.get(0), (List<HiHealthData>) sparseArray.get(1))));
        }
    }

    private List<HiHealthData> a(List<HiHealthData> list, List<HiHealthData> list2) {
        double d;
        ArrayList arrayList = new ArrayList();
        if (!koq.b(list) && !koq.b(list2) && list.size() == list2.size()) {
            int i = 0;
            while (i < list.size()) {
                HiHealthData hiHealthData = new HiHealthData();
                HiHealthData hiHealthData2 = list.get(i);
                HiHealthData hiHealthData3 = list2.get(i);
                hiHealthData.setStartTime(rrb.c(hiHealthData2.getStartTime()));
                hiHealthData.setEndTime(rrb.e(hiHealthData2.getEndTime()));
                hiHealthData.setModifiedTime(hiHealthData2.getModifiedTime());
                double d2 = hiHealthData2.getDouble("maxOldBodyTemperature");
                double d3 = hiHealthData2.getDouble("maxNewBodyTemperature");
                double d4 = hiHealthData2.getDouble("maxSuspectBodyTemperature");
                double d5 = hiHealthData3.getDouble("minOldBodyTemperature");
                double d6 = hiHealthData3.getDouble("minNewBodyTemperature");
                double d7 = hiHealthData3.getDouble("minSuspectBodyTemperature");
                int i2 = i;
                if (d3 == 0.0d && d2 == 0.0d && d6 == 0.0d && d5 == 0.0d) {
                    if (d4 != 0.0d && d7 != 0.0d) {
                        if (d4 == d7) {
                            LogUtil.c("BodyTemperature", "mergeBodyMaxMin no exact temp, singleSuspect=", Double.valueOf(d4));
                            hiHealthData.putDouble("singleSuspectBodyTemperature", d4);
                            arrayList.add(hiHealthData);
                        }
                    }
                    i = i2 + 1;
                }
                ArrayList arrayList2 = arrayList;
                String str = "mergeBodyMaxMin max(" + d2 + "," + d3 + "," + d4 + "); min(";
                hiHealthData.putDouble("maxTemperature", Math.max(d4, Math.max(d3, d2)));
                if (d6 <= 0.0d || d5 <= 0.0d) {
                    d = d5 > 0.0d ? d5 : d6;
                } else {
                    d = Math.min(d6, d5);
                }
                LogUtil.c("BodyTemperature", str, Double.valueOf(d5), ",", Double.valueOf(d6), ",", Double.valueOf(d7), Constants.RIGHT_BRACKET_ONLY);
                if (d7 > 0.0d) {
                    if (d > 0.0d) {
                        d7 = Math.min(d, d7);
                    }
                    hiHealthData.putDouble("minTemperature", d7);
                } else if (d <= 0.0d) {
                    arrayList = arrayList2;
                    i = i2 + 1;
                } else {
                    hiHealthData.putDouble("minTemperature", d);
                }
                arrayList = arrayList2;
                arrayList.add(hiHealthData);
                i = i2 + 1;
            }
        }
        return arrayList;
    }

    private void n(rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        ArrayList arrayList = new ArrayList();
        String[][] strArr = {new String[]{"maxSkinTemperature"}, new String[]{"minSkinTemperature"}};
        int[] iArr = {4, 5};
        for (int i = 0; i < 2; i++) {
            arrayList.add(new HiDataAggregateProOption.Builder().c(rrc.c(rkbVar, new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()}, strArr[i], iArr[i])).c(rkbVar.j()).c());
        }
        LogUtil.a("BodyTemperature", "readSkinGroupDataBySource start");
        aggregateHiHealthDataProEx(arrayList, new DataSourceCallback() { // from class: rny
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rnl.this.dPP_(dataSourceCallback, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPP_(DataSourceCallback dataSourceCallback, int i, SparseArray sparseArray) {
        LogUtil.a("BodyTemperature", "readSkinGroupDataBySource end resultCode = ", Integer.valueOf(i));
        if (i != 0 || sparseArray == null || sparseArray.size() == 0) {
            dataSourceCallback.onResponse(i, new ArrayList());
        } else {
            dataSourceCallback.onResponse(i, hiHealthDataProcess(c((List<HiHealthData>) sparseArray.get(0), (List<HiHealthData>) sparseArray.get(1))));
        }
    }

    private List<HiHealthData> c(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list2 != null && list.size() == list2.size()) {
            for (int i = 0; i < list.size(); i++) {
                HiHealthData hiHealthData = new HiHealthData();
                HiHealthData hiHealthData2 = list.get(i);
                HiHealthData hiHealthData3 = list2.get(i);
                hiHealthData.setStartTime(rrb.c(hiHealthData2.getStartTime()));
                hiHealthData.setEndTime(rrb.e(hiHealthData2.getEndTime()));
                hiHealthData.setModifiedTime(hiHealthData2.getModifiedTime());
                hiHealthData.putDouble("maxTemperature", hiHealthData2.getDouble("maxSkinTemperature"));
                hiHealthData.putDouble("minTemperature", hiHealthData3.getDouble("minSkinTemperature"));
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthNoSource(DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb e = e();
        e.c(0);
        ThreadPoolManager.d().execute(f(e, dataSourceCallback));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByDeviceSource(String str, int i, String str2, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb e = e();
        e.c(i);
        e.d(str);
        e.e(str2);
        ThreadPoolManager.d().execute(c(e, dataSourceCallback));
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readByMonthByAppSource(String str, DataSourceCallback<List<rsg>> dataSourceCallback) {
        rkb e = e();
        e.c(1);
        e.e(str);
        ThreadPoolManager.d().execute(c(e, dataSourceCallback));
    }

    private rkb e() {
        rkb rkbVar = new rkb();
        rkbVar.c(0L);
        rkbVar.d(System.currentTimeMillis());
        rkbVar.a(new int[]{2104});
        rkbVar.c(new String[]{"maxTemperature"});
        rkbVar.b(4);
        return rkbVar;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataNoSource */
    public void m843xae5c7554(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), 2104, DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()});
        rkbVar.b(true);
        readForOneDayDatas(rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    /* renamed from: readDayDataBySource */
    public void m842xa7339313(rkb rkbVar, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value(), 2104});
        rkbVar.b(true);
        readForOneDayDatas(rkbVar, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByDeviceSource(long j, String str, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb b = b(j);
        b.c(2);
        b.d(str);
        readForOneDayDatas(b, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void readInOneDayByAppSource(long j, String str, String str2, int i, DataSourceCallback<List<PrivacyDataModel>> dataSourceCallback) {
        rkb b = b(j);
        b.c(i);
        b.d(str);
        b.e(str2);
        readForOneDayDatas(b, dataSourceCallback);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public int[] getDeleteDataTypes(rkb rkbVar) {
        if (rkbVar.a("categoryType") == 2) {
            return new int[]{DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()};
        }
        return new int[]{DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), 2104};
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void deleteDatas(List<PrivacyDataModel> list, rkb rkbVar, final DataSourceCallback<Boolean> dataSourceCallback) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        for (PrivacyDataModel privacyDataModel : list) {
            if (privacyDataModel != null) {
                LogUtil.a("BodyTemperature", "deleteDatas model=", privacyDataModel.toString());
                if (privacyDataModel.getType() == 0) {
                    arrayList2.add(new HiTimeInterval(privacyDataModel.getStartTime(), privacyDataModel.getEndTime()));
                } else if (privacyDataModel.getType() == DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value() || privacyDataModel.getType() == DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_VALUE.value()) {
                    arrayList2.add(new HiTimeInterval(privacyDataModel.getStartTime(), privacyDataModel.getEndTime()));
                }
                arrayList.add(new HiTimeInterval(privacyDataModel.getStartTime(), privacyDataModel.getEndTime()));
            }
        }
        ArrayList arrayList3 = new ArrayList(2);
        if (rkbVar.a("categoryType") == 1) {
            b(arrayList3, rkbVar, new int[]{DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_VALUE.value()}, arrayList2);
        }
        b(arrayList3, rkbVar, getDeleteDataTypes(rkbVar), arrayList);
        if (arrayList3.isEmpty()) {
            return;
        }
        LogUtil.a("BodyTemperature", "deleteData start");
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthDataProEx(arrayList3, new HiDeleteListenerEx() { // from class: rnx
            @Override // com.huawei.hihealth.data.listener.HiDeleteListenerEx
            public final void onResult(Map map) {
                rnl.this.e(dataSourceCallback, map);
            }
        });
    }

    /* synthetic */ void e(final DataSourceCallback dataSourceCallback, Map map) {
        if (map == null || map.isEmpty()) {
            LogUtil.a("BodyTemperature", "deleteData end errorCode = 0");
            this.mHandler.post(new Runnable() { // from class: roq
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(0, true);
                }
            });
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            LogUtil.a("BodyTemperature", "deleteData end errorCode = ", entry.getKey(), ", object = ", ((List) entry.getValue()).toString());
        }
        this.mHandler.post(new Runnable() { // from class: rnr
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(1, false);
            }
        });
    }

    private void b(List<HiDataDeleteProOption> list, rkb rkbVar, int[] iArr, List<HiTimeInterval> list2) {
        if (list2.isEmpty()) {
            return;
        }
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTypes(iArr);
        hiDataDeleteOption.setTimes(list2);
        list.add(HiDataDeleteProOption.builder().d(hiDataDeleteOption).e(Integer.valueOf(rkbVar.e())).c(0).e(rkbVar.i()).d(rkbVar.j()).d());
    }

    private rkb b(long j) {
        rkb rkbVar = new rkb();
        rkbVar.c(rrb.c(j));
        rkbVar.d(rrb.e(j));
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.BODY_TEMPERATURE.value(), 2104, DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()});
        return rkbVar;
    }

    private Runnable f(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        return new Runnable() { // from class: rnz
            @Override // java.lang.Runnable
            public final void run() {
                rnl.this.b(rkbVar, dataSourceCallback);
            }
        };
    }

    /* synthetic */ void b(final rkb rkbVar, final DataSourceCallback dataSourceCallback) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: rol
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.d(rkbVar, countDownLatch, dataSourceCallback, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("BodyTemperature", "CountDownLatch exception");
        }
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.MAX_BODY_TEMPERATURE.value(), DicDataTypeUtil.DataType.MIN_BODY_TEMPERATURE.value()});
        readHiHealthDataPro(rkbVar, new DataSourceCallback() { // from class: roj
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.dPL_(dataSourceCallback, rkbVar, i, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void dPL_(final DataSourceCallback dataSourceCallback, rkb rkbVar, final int i, SparseArray sparseArray) {
        if (i != 0) {
            this.mHandler.post(new Runnable() { // from class: rog
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        if (sparseArray != null && sparseArray.get(rkbVar.l()[0]) != null) {
            this.mMaxDataList.addAll((Collection) sparseArray.get(rkbVar.l()[0]));
        }
        if (sparseArray != null && sparseArray.get(rkbVar.l()[1]) != null) {
            this.mMinDataList.addAll((Collection) sparseArray.get(rkbVar.l()[1]));
        }
        Object[] objArr = new Object[4];
        objArr[0] = "queryMaxAndMinDataNoSource max";
        objArr[1] = Integer.valueOf(this.mMaxDataList == null ? 0 : this.mMaxDataList.size());
        objArr[2] = ", min";
        objArr[3] = Integer.valueOf(this.mMinDataList != null ? this.mMinDataList.size() : 0);
        ReleaseLogUtil.e("R_BodyTemperature", objArr);
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(mergeMaxMinList(d(this.mMaxDataList, "deduplicate_max"), d(this.mMinDataList, "deduplicate_min")));
        this.mHandler.post(new Runnable() { // from class: rof
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(i, hiHealthDataProcess);
            }
        });
    }

    private Runnable c(final rkb rkbVar, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        return new Runnable() { // from class: roh
            @Override // java.lang.Runnable
            public final void run() {
                rnl.this.e(rkbVar, dataSourceCallback);
            }
        };
    }

    /* synthetic */ void e(final rkb rkbVar, final DataSourceCallback dataSourceCallback) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: rnq
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.e(rkbVar, countDownLatch, dataSourceCallback, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.b("BodyTemperature", "CountDownLatch exception");
        }
        rkbVar.c(new String[]{"maxTemperature"});
        rkbVar.b(4);
        rkbVar.a(new int[]{DicDataTypeUtil.DataType.BODY_TEMPERATURE.value()});
        final CountDownLatch countDownLatch2 = new CountDownLatch(1);
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: rns
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i, Object obj) {
                rnl.this.a(rkbVar, countDownLatch2, dataSourceCallback, i, (List) obj);
            }
        });
        try {
            countDownLatch2.await();
        } catch (InterruptedException unused2) {
            LogUtil.b("BodyTemperature", "CountDownLatch exception");
        }
        Object[] objArr = new Object[4];
        objArr[0] = "queryMaxAndMinDataForSource max";
        objArr[1] = Integer.valueOf(this.mMaxDataList == null ? 0 : this.mMaxDataList.size());
        objArr[2] = ", min";
        objArr[3] = Integer.valueOf(this.mMinDataList != null ? this.mMinDataList.size() : 0);
        ReleaseLogUtil.e("R_BodyTemperature", objArr);
        final List<rsg> hiHealthDataProcess = hiHealthDataProcess(mergeMaxMinList(d(this.mMaxDataList, "deduplicate_max"), d(this.mMinDataList, "deduplicate_min")));
        this.mHandler.post(new Runnable() { // from class: roc
            @Override // java.lang.Runnable
            public final void run() {
                DataSourceCallback.this.onResponse(0, hiHealthDataProcess);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void e(final int i, final rkb rkbVar, List<HiHealthData> list, final CountDownLatch countDownLatch, final DataSourceCallback<List<rsg>> dataSourceCallback) {
        if (i != 0) {
            this.mHandler.post(new Runnable() { // from class: roe
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        if (list != null) {
            transKeyToValue(list, this.mMaxDataList, rkbVar);
        }
        rkbVar.c(new String[]{"minTemperature"});
        rkbVar.b(5);
        aggregateProMaxAndMin(rkbVar, new DataSourceCallback() { // from class: roi
            @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.DataSourceCallback
            public final void onResponse(int i2, Object obj) {
                rnl.this.a(dataSourceCallback, rkbVar, countDownLatch, i2, (List) obj);
            }
        });
    }

    /* synthetic */ void a(final DataSourceCallback dataSourceCallback, rkb rkbVar, CountDownLatch countDownLatch, final int i, List list) {
        if (i != 0) {
            this.mHandler.post(new Runnable() { // from class: rnw
                @Override // java.lang.Runnable
                public final void run() {
                    DataSourceCallback.this.onResponse(i, new ArrayList());
                }
            });
            return;
        }
        if (list != null) {
            transKeyToValue(list, this.mMinDataList, rkbVar);
        }
        countDownLatch.countDown();
    }

    private List<HiHealthData> d(List<HiHealthData> list, String str) {
        sortHiHealthDataToDesc(list);
        ArrayList arrayList = new ArrayList(10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(0L);
        hiHealthData.setValue(Integer.MIN_VALUE);
        int size = list.size();
        list.add(hiHealthData);
        int i = 0;
        while (i < size) {
            HiHealthData hiHealthData2 = list.get(i);
            int i2 = i + 1;
            HiHealthData hiHealthData3 = list.get(i2);
            if (!simpleDateFormat.format(Long.valueOf(hiHealthData2.getStartTime())).equals(simpleDateFormat.format(Long.valueOf(hiHealthData3.getStartTime())))) {
                arrayList.add(list.get(i));
                i = i2;
            } else {
                if ("deduplicate_max".equals(str) && hiHealthData2.getValue() > hiHealthData3.getValue()) {
                    arrayList.add(hiHealthData2);
                } else if ("deduplicate_max".equals(str) && hiHealthData2.getValue() < hiHealthData3.getValue()) {
                    arrayList.add(hiHealthData3);
                } else if ("deduplicate_min".equals(str) && hiHealthData2.getValue() < hiHealthData3.getValue()) {
                    arrayList.add(hiHealthData2);
                } else {
                    arrayList.add(hiHealthData3);
                }
                i += 2;
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public List<PrivacyDataModel> proResultOfReadInOneDayNoSource(List<HiHealthData> list) {
        return recordDataProcess(list, 1005);
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfHiHealthDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDataDesc(rsr.a(hiHealthData.getStartTime()));
        if (hiHealthData.containsKey("singleSuspectBodyTemperature")) {
            privacyDataModel.setDataTitle(rre.c(hiHealthData.getDouble("singleSuspectBodyTemperature"), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value()));
        } else {
            privacyDataModel.setDataTitle(rre.e(hiHealthData.getDouble("maxTemperature"), hiHealthData.getDouble("minTemperature")));
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.datasourcemanager.operate.data.BaseOperate
    public void setVarOfRecordDataProcess(HiHealthData hiHealthData, PrivacyDataModel privacyDataModel) {
        privacyDataModel.setDoubleValue(hiHealthData.getValue());
        privacyDataModel.setDataTitle(rre.c(hiHealthData.getValue(), hiHealthData.getType()));
    }
}
