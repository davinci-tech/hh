package defpackage;

import android.content.Context;
import android.text.format.DateFormat;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import health.compact.a.HiCommonUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/* loaded from: classes6.dex */
public class qkh {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<qkg> f16455a;

    private qkh() {
        this.f16455a = new ArrayList<>(31);
    }

    public static qkh c() {
        return e.c;
    }

    public void b() {
        ArrayList<qkg> arrayList = this.f16455a;
        if (arrayList != null) {
            arrayList.clear();
        } else {
            this.f16455a = new ArrayList<>(31);
        }
    }

    public void b(final long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        kor.a().b(j, j2, new IBaseResponseCallback() { // from class: qke
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qkh.b(IBaseResponseCallback.this, j, i, obj);
            }
        });
    }

    static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, long j, int i, Object obj) {
        if (!(obj instanceof List)) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List<HiHealthData> list = (List) obj;
        LogUtil.d("UIHLH_HealthDataInteractor", "getSportRecords hasData, total count = ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(16);
        for (HiHealthData hiHealthData : list) {
            qab qabVar = new qab();
            try {
                qabVar = (qab) kps.e(hiHealthData.getMetaData(), qab.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.c("UIHLH_HealthDataInteractor", "getSportRecord JsonUtil fromJson fail");
            }
            qabVar.setStartTime(hiHealthData.getStartTime());
            qabVar.setEndTime(hiHealthData.getEndTime());
            qabVar.d(j);
            arrayList.add(qabVar);
        }
        iBaseResponseCallback.d(0, arrayList);
    }

    private void a(Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        synchronized (this) {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleWeightData enter");
            List<HiHealthData> list = (List) obj;
            ArrayList arrayList = new ArrayList(31);
            if (HiCommonUtil.d(list)) {
                com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleWeightData enter datas is null");
                if (commonUiBaseResponse != null) {
                    commonUiBaseResponse.onResponse(0, arrayList);
                }
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleWeightData datas.size = ", Integer.valueOf(list.size()));
            ArrayList<qkg> arrayList2 = new ArrayList<>(31);
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getDouble("bodyWeight") > 0.0d) {
                    arrayList2.add(qkl.e(hiHealthData));
                } else {
                    com.huawei.hwlogsmodel.LogUtil.h("UIHLH_HealthDataInteractor", "illegal weight, timeStamp ", Long.valueOf(hiHealthData.getStartTime()));
                }
            }
            a(arrayList2, arrayList);
            if (commonUiBaseResponse != null) {
                commonUiBaseResponse.onResponse(0, arrayList);
            }
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleWeightData end");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, CommonUiBaseResponse commonUiBaseResponse) {
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleBloodsugarData enter");
        long currentTimeMillis = System.currentTimeMillis();
        List<ArrayList<qkg>> arrayList = new ArrayList<>(31);
        ArrayList<qkg> arrayList2 = new ArrayList<>(31);
        if (list != null && list.size() > 0) {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleBloodSugarData data.size = ", Integer.valueOf(list.size()));
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getEndTime() <= currentTimeMillis) {
                    arrayList2.add(qkl.c(hiHealthData));
                }
            }
            arrayList = c(arrayList2);
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleBloodSugarData data.size1 = ", Integer.valueOf(arrayList.size()));
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "datas null or empty");
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, arrayList);
        }
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleBloodsugarData time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleWeightData end");
    }

    private void b(List<HiHealthData> list, CommonUiBaseResponse commonUiBaseResponse) {
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleTemperatureData enter");
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(31);
        if (list != null && list.size() > 0) {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleTemperatureData data.size = ", Integer.valueOf(list.size()));
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData.getEndTime() <= currentTimeMillis) {
                    double value = hiHealthData.getValue();
                    long startTime = hiHealthData.getStartTime();
                    int i = hiHealthData.getInt("trackdata_deviceType");
                    int type = hiHealthData.getType();
                    qkg qkgVar = new qkg();
                    qkgVar.c(value);
                    qkgVar.a(startTime);
                    qkgVar.c(i);
                    qkgVar.f(type);
                    if (Math.abs(qkgVar.o()) >= 0.0d) {
                        arrayList.add(qkgVar);
                    } else {
                        com.huawei.hwlogsmodel.LogUtil.h("UIHLH_HealthDataInteractor", "invalid temperature value");
                    }
                }
            }
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleTemperatureData data is null or empty");
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, arrayList);
        }
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handleTemperatureData cost of time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public void a(Context context, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("UIHLH_HealthDataInteractor", "deleteWeightData ");
        kor.a().d(context, j, j2, iBaseResponseCallback);
    }

    public void c(Context context, List<HiTimeInterval> list, IBaseResponseCallback iBaseResponseCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("UIHLH_HealthDataInteractor", "deleteWeightData ");
        kor.a().c(context, list, iBaseResponseCallback);
    }

    public void b(long j, IBaseResponseCallback iBaseResponseCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("UIHLH_HealthDataInteractor", "deleteBloodPressureData ");
        ArrayList arrayList = new ArrayList();
        HiTimeInterval hiTimeInterval = new HiTimeInterval();
        hiTimeInterval.setStartTime(j);
        hiTimeInterval.setEndTime(j);
        arrayList.add(hiTimeInterval);
        c(arrayList, iBaseResponseCallback);
    }

    public void c(List<HiTimeInterval> list, IBaseResponseCallback iBaseResponseCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("UIHLH_HealthDataInteractor", "deleteBloodPressureData ");
        kor.a().e(list, iBaseResponseCallback);
    }

    public void e(Context context, int i, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        com.huawei.hwlogsmodel.LogUtil.c("UIHLH_HealthDataInteractor", "deleteBloodSuagrData ");
        kor.a().a(context, i, j, j2, iBaseResponseCallback);
    }

    public void d(final CommonUiBaseResponse commonUiBaseResponse) {
        if (commonUiBaseResponse == null) {
            com.huawei.hwlogsmodel.LogUtil.h("UIHLH_HealthDataInteractor", "readData callback is null");
            return;
        }
        HiAggregateOption e2 = e();
        e2.setCount(1);
        e2.setTimeRange(0L, System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(e2, new HiAggregateListener() { // from class: qkh.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    com.huawei.hwlogsmodel.LogUtil.h("UIHLH_HealthDataInteractor", "datas is empty");
                    commonUiBaseResponse.onResponse(100001, new ArrayList());
                } else {
                    com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "readData data size = ", Integer.valueOf(list.size()));
                    commonUiBaseResponse.onResponse(0, list);
                }
            }
        });
    }

    private static HiAggregateOption e() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    public void b(final CommonUiBaseResponse commonUiBaseResponse) {
        qpk d = qpk.d();
        int[] iArr = {d.b(), 2104, d.s()};
        if (Utils.o()) {
            iArr = new int[]{d.o()};
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        d.a(iArr, hiDataReadOption, new IBaseResponseCallback() { // from class: qkf
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qkh.this.a(commonUiBaseResponse, i, obj);
            }
        });
    }

    /* synthetic */ void a(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        b(obj instanceof List ? (List) obj : null, commonUiBaseResponse);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        kor.a().e(iBaseResponseCallback);
    }

    public void c(Context context, long[] jArr, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (jArr == null || jArr.length <= 1) {
            return;
        }
        kot.a().b(context, jArr[0], jArr[1], i, new IBaseResponseCallback() { // from class: qkj
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                qkh.this.c(commonUiBaseResponse, i2, obj);
            }
        });
    }

    /* synthetic */ void c(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        a(obj, commonUiBaseResponse);
    }

    public void b(Context context, long j, long j2, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        final long currentTimeMillis = System.currentTimeMillis();
        kor.a().d(context, j, j2, i, new IBaseResponseCallback() { // from class: qkh.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "readBloodSugarData time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                qkh.this.a((List<HiHealthData>) obj, commonUiBaseResponse);
            }
        });
    }

    public void c(Context context, long j, long j2, final CommonUiBaseResponse commonUiBaseResponse) {
        kor.a().b(context, j, j2, new IBaseResponseCallback() { // from class: qkh.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                commonUiBaseResponse.onResponse(0, obj);
            }
        });
    }

    public void a(Context context, long j, long j2, CommonUiBaseResponse commonUiBaseResponse) {
        kor.a().e(context, j, j2, new c(this, System.currentTimeMillis(), commonUiBaseResponse));
    }

    public void d(Context context, long j, long j2, CommonUiBaseResponse commonUiBaseResponse) {
        kor.a().c(context, j, j2, new c(this, System.currentTimeMillis(), commonUiBaseResponse));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "handlerLastTimeHeartRateData enter");
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(0, obj);
        }
    }

    public void e(long j, double[] dArr, List<String> list, String str, IBaseResponseCallback iBaseResponseCallback) {
        long[] jArr = {j, j};
        if (list == null) {
            list = new ArrayList<>(0);
        }
        List<String> list2 = list;
        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "customActions: ", list2.toString());
        kor.a().e(jArr, dArr, list2, str, iBaseResponseCallback);
    }

    public void d(Context context, long[] jArr, int i, double d, IBaseResponseCallback iBaseResponseCallback) {
        kor.a().b(context, jArr, new double[]{i, d, 0.0d}, iBaseResponseCallback);
    }

    public void a(ArrayList<qkg> arrayList, List<ArrayList<qkg>> list) {
        list.clear();
        b(arrayList, list);
        Iterator<ArrayList<qkg>> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i).get(0).h() < list.get(i3).get(0).h()) {
                    ArrayList<qkg> arrayList2 = list.get(i);
                    list.set(i, list.get(i3));
                    list.set(i3, arrayList2);
                }
            }
            i = i2;
        }
    }

    private List<ArrayList<qkg>> c(ArrayList<qkg> arrayList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        TreeMap treeMap = new TreeMap(new Comparator() { // from class: qki
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Long) obj2).compareTo((Long) obj);
                return compareTo;
            }
        });
        Iterator<qkg> it = arrayList.iterator();
        while (it.hasNext()) {
            qkg next = it.next();
            try {
                long time = simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(next.h()))).getTime();
                ArrayList arrayList2 = (ArrayList) treeMap.get(Long.valueOf(time));
                if (arrayList2 == null) {
                    ArrayList arrayList3 = new ArrayList();
                    treeMap.put(Long.valueOf(time), arrayList3);
                    arrayList3.add(next);
                } else {
                    e(arrayList2, next);
                }
            } catch (ParseException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("UIHLH_HealthDataInteractor", e2);
            }
        }
        return new ArrayList(treeMap.values());
    }

    private void e(List<qkg> list, qkg qkgVar) {
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (list.get(i).o() > qkgVar.o()) {
                size = i;
                break;
            }
            i++;
        }
        list.add(size, qkgVar);
    }

    private void b(ArrayList<qkg> arrayList, List<ArrayList<qkg>> list) {
        ArrayList arrayList2 = new ArrayList(31);
        Iterator<qkg> it = arrayList.iterator();
        while (it.hasNext()) {
            qkg next = it.next();
            if (arrayList2.contains(d(next.h()))) {
                list.get(arrayList2.indexOf(d(next.h()))).add(next);
            } else {
                ArrayList<qkg> arrayList3 = new ArrayList<>(31);
                arrayList3.add(next);
                arrayList2.add(d(next.h()));
                list.add(arrayList3);
            }
        }
    }

    private void a(ArrayList<qkg> arrayList) {
        int size = arrayList.size();
        int i = 0;
        while (i < size - 1) {
            int i2 = i + 1;
            int size2 = arrayList.size();
            for (int i3 = i2; i3 < size2; i3++) {
                if (arrayList.get(i).h() < arrayList.get(i3).h()) {
                    qkg qkgVar = arrayList.get(i);
                    arrayList.set(i, arrayList.get(i3));
                    arrayList.set(i3, qkgVar);
                }
            }
            i = i2;
        }
    }

    public String d(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(date);
    }

    static class e {
        private static final qkh c = new qkh();
    }

    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<qkh> f16459a;
        private CommonUiBaseResponse c;
        private long e;

        protected c(qkh qkhVar, long j, CommonUiBaseResponse commonUiBaseResponse) {
            this.e = j;
            this.c = commonUiBaseResponse;
            this.f16459a = new WeakReference<>(qkhVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_HealthDataInteractor", "readHeartRateData time = ", Long.valueOf(System.currentTimeMillis() - this.e));
            qkh qkhVar = this.f16459a.get();
            if (qkhVar != null) {
                qkhVar.e(obj, this.c);
            }
        }
    }
}
