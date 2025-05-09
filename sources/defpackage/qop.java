package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qop implements IChartStorageHelper {
    private static long e;

    /* renamed from: a, reason: collision with root package name */
    private long f16524a;
    private boolean d;
    private long g;
    private final LineChartViewPresenter j;
    private String i = "TEMPERATURE_MIN_MAX";
    private final boolean b = UnitUtil.d();
    private final qpk c = qpk.d();

    public qop(LineChartViewPresenter lineChartViewPresenter) {
        this.j = lineChartViewPresenter;
    }

    public void e(String str) {
        LogUtil.c("TemperatureChartStorageHelper", "setShowDataType is ", str);
        this.i = str;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (responseCallback == null) {
            return;
        }
        this.g = j;
        this.f16524a = j2 - 1;
        d(dataInfos, responseCallback);
    }

    private void d(DataInfos dataInfos, ResponseCallback responseCallback) {
        if (dataInfos.isDayData()) {
            c(responseCallback);
            a();
            this.d = true;
        }
        b(responseCallback);
    }

    private void b(ResponseCallback responseCallback) {
        int[] iArr = {2104};
        this.c.a(iArr, a(iArr), new a(responseCallback));
    }

    private void a() {
        if (!jdl.d(this.g, this.f16524a)) {
            LogUtil.h("TemperatureChartStorageHelper", "getDayBodyAlarmData requested data is not the same day");
            return;
        }
        if (jdl.d(this.g, e) && nsn.a(500)) {
            return;
        }
        e = this.g;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.i)) {
            return;
        }
        int[] iArr = {this.c.j(), this.c.h()};
        this.c.a(iArr, c(iArr, this.g), new e());
    }

    private HiDataReadOption c(int[] iArr, long j) {
        long t = jdl.t(j);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(2);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(t);
        hiDataReadOption.setEndTime(jdl.e(t));
        return hiDataReadOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ResponseCallback responseCallback, List<HiHealthData> list) {
        HiAggregateOption d = d();
        d.setType(new int[]{this.c.f(), this.c.i(), this.c.l(), this.c.n()});
        d.setConstantsKey(new String[]{"body_min_key", "body_max_key", "skin_min_key", "skin_max_key"});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(d, new b(responseCallback, list));
    }

    private HiAggregateOption d() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(this.g);
        hiAggregateOption.setEndTime(this.f16524a);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private HiDataReadOption a(int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(this.g);
        hiDataReadOption.setEndTime(this.f16524a);
        return hiDataReadOption;
    }

    private void c(ResponseCallback responseCallback) {
        int[] iArr;
        if ("TEMPERATURE_MIN_MAX".equals(this.i)) {
            iArr = new int[]{this.c.b(), 2104};
        } else {
            iArr = new int[]{this.c.o()};
        }
        this.c.a(iArr, a(iArr), new c(responseCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> d(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.b(list2)) {
            LogUtil.h("TemperatureChartStorageHelper", "addOldData oldList is null!");
            return list;
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData != null) {
                long t = jdl.t(hiHealthData.getStartTime());
                float floatValue = hiHealthData.getFloatValue();
                HiHealthData hiHealthData2 = hashMap.get(Long.valueOf(t));
                if (hiHealthData2 == null) {
                    hiHealthData2 = new HiHealthData();
                    hiHealthData2.setTimeInterval(t, t);
                    hiHealthData2.putFloat("body_min_key", floatValue);
                    hiHealthData2.putFloat("body_max_key", floatValue);
                } else {
                    float f = hiHealthData2.getFloat("body_max_key");
                    float f2 = hiHealthData2.getFloat("body_min_key");
                    float max = Math.max(floatValue, f);
                    hiHealthData2.putFloat("body_min_key", Math.min(floatValue, f2));
                    hiHealthData2.putFloat("body_max_key", max);
                }
                hashMap.put(Long.valueOf(t), hiHealthData2);
            }
        }
        if (koq.b(list)) {
            LogUtil.h("TemperatureChartStorageHelper", "addOldData list is null");
            b(arrayList, hashMap);
            return arrayList;
        }
        return b(list, hashMap, arrayList);
    }

    private List<HiHealthData> b(List<HiHealthData> list, Map<Long, HiHealthData> map, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                HiHealthData hiHealthData2 = map.get(Long.valueOf(hiHealthData.getStartTime()));
                if (hiHealthData2 == null) {
                    list2.add(hiHealthData);
                } else {
                    float f = hiHealthData2.getFloat("body_min_key");
                    float f2 = hiHealthData2.getFloat("body_max_key");
                    float min = Math.min(hiHealthData.getFloat("body_min_key"), f);
                    float max = Math.max(hiHealthData.getFloat("body_max_key"), f2);
                    hiHealthData2.putFloat("body_min_key", min);
                    hiHealthData2.putFloat("body_max_key", max);
                    hiHealthData2.putFloat("skin_min_key", hiHealthData.getFloat("skin_min_key"));
                    hiHealthData2.putFloat("skin_max_key", hiHealthData.getFloat("skin_max_key"));
                    list2.add(hiHealthData2);
                }
            }
        }
        return list2;
    }

    private void b(List<HiHealthData> list, Map<Long, HiHealthData> map) {
        Iterator<Map.Entry<Long, HiHealthData>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        pka pkaVar;
        if (responseCallback == null) {
            return;
        }
        Map<Long, IStorageModel> hashMap = new HashMap<>();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                float floatValue = hiHealthData.getFloatValue();
                if (qpr.b(floatValue, this.i)) {
                    LogUtil.h("TemperatureChartStorageHelper", "responseDayAvgData value ", Float.valueOf(floatValue));
                } else {
                    long e2 = qpr.e(hiHealthData.getStartTime());
                    IStorageModel iStorageModel = hashMap.get(Long.valueOf(e2));
                    if (iStorageModel instanceof pka) {
                        pkaVar = (pka) iStorageModel;
                        int d = pkaVar.d();
                        int i = d + 1;
                        pkaVar.d(((d * pkaVar.a()) + floatValue) / i);
                        pkaVar.e(i);
                    } else {
                        pkaVar = new pka(floatValue);
                        pkaVar.e(1);
                    }
                    hashMap.put(Long.valueOf(e2), pkaVar);
                }
            }
        }
        LogUtil.a("TemperatureChartStorageHelper", "responseDayAvgData resultMap size ", Integer.valueOf(hashMap.size()));
        b(hashMap, responseCallback);
    }

    private void b(Map<Long, IStorageModel> map, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        Map<Long, IStorageModel> hashMap = new HashMap<>(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            IStorageModel value = entry.getValue();
            if (value instanceof pka) {
                pka pkaVar = (pka) value;
                float floatValue = new BigDecimal(String.valueOf(pkaVar.a())).setScale(1, 4).floatValue();
                if (!this.b) {
                    floatValue = qpr.c(floatValue);
                }
                pkaVar.d(floatValue);
                hashMap.put(entry.getKey(), pkaVar);
            }
        }
        LogUtil.a("TemperatureChartStorageHelper", "dealHourAvgCallback resultMap size ", Integer.valueOf(hashMap.size()));
        responseCallback.onResult(0, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        d(list, responseCallback);
        e(list);
    }

    private void d(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        String str;
        String str2;
        if (this.d || responseCallback == null) {
            return;
        }
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.i)) {
            str = "skin_min_key";
            str2 = "skin_max_key";
        } else {
            str = "body_min_key";
            str2 = "body_max_key";
        }
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                float f = hiHealthData.getFloat(str2);
                float f2 = hiHealthData.getFloat(str);
                float floatValue = new BigDecimal(f).setScale(1, 4).floatValue();
                float floatValue2 = new BigDecimal(f2).setScale(1, 4).floatValue();
                if (qpr.b(floatValue, this.i) || qpr.b(floatValue2, this.i)) {
                    LogUtil.h("TemperatureChartStorageHelper", "responseWeekMonthData max ", Float.valueOf(floatValue), " min ", Float.valueOf(floatValue2));
                } else {
                    if (!this.b) {
                        floatValue = qpr.c(floatValue);
                        floatValue2 = qpr.c(floatValue2);
                    }
                    pyl pylVar = new pyl();
                    pylVar.d(floatValue2);
                    pylVar.e(floatValue);
                    hashMap.put(Long.valueOf(hiHealthData.getStartTime()), new pyg(40.0f, pylVar));
                }
            }
        }
        LogUtil.a("TemperatureChartStorageHelper", "responseWeekMonthChart map size ", Integer.valueOf(hashMap.size()));
        responseCallback.onResult(0, hashMap);
    }

    private void e(List<HiHealthData> list) {
        this.j.notifyMaxAndMin(0, list);
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<qop> b;
        private final ResponseCallback<Map<Long, IStorageModel>> d;

        private c(qop qopVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.b = new WeakReference<>(qopVar);
            this.d = responseCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qop qopVar = this.b.get();
            if (qopVar == null || this.d == null) {
                return;
            }
            HashMap hashMap = new HashMap(16);
            if (qopVar.j == null) {
                LogUtil.h("TemperatureChartStorageHelper", "DayPointDataResponse mPresenter is null");
                this.d.onResult(0, hashMap);
                return;
            }
            if (i != 0) {
                LogUtil.h("TemperatureChartStorageHelper", "DayPointDataResponse errorCode ", Integer.valueOf(i));
                this.d.onResult(0, hashMap);
                return;
            }
            List list = obj instanceof List ? (List) obj : null;
            if (koq.b(list)) {
                LogUtil.h("TemperatureChartStorageHelper", "DayPointDataResponse list is empty");
                this.d.onResult(0, hashMap);
            } else {
                LogUtil.a("TemperatureChartStorageHelper", "DayPointDataResponse list size ", Integer.valueOf(list.size()));
                qopVar.b((List<HiHealthData>) list, this.d);
            }
        }
    }

    static class e implements IBaseResponseCallback {
        private final WeakReference<qop> c;

        private e(qop qopVar) {
            this.c = new WeakReference<>(qopVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qop qopVar = this.c.get();
            if (qopVar == null) {
                return;
            }
            if (qopVar.j == null) {
                LogUtil.h("TemperatureChartStorageHelper", "DayBodyAlarmDataResponse mPresenter is null");
                return;
            }
            if (i != 0) {
                LogUtil.h("TemperatureChartStorageHelper", "DayBodyAlarmDataResponse errorCode ", Integer.valueOf(i));
                qopVar.j.notifyRemindData(-1, null);
                return;
            }
            List<HiHealthData> list = obj instanceof List ? (List) obj : null;
            if (koq.b(list)) {
                LogUtil.h("TemperatureChartStorageHelper", "DayBodyAlarmDataResponse list is empty");
                qopVar.j.notifyRemindData(-1, null);
            } else {
                LogUtil.a("TemperatureChartStorageHelper", "DayBodyAlarmDataResponse list size ", Integer.valueOf(list.size()));
                qopVar.j.notifyRemindData(0, list);
            }
        }
    }

    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<qop> f16525a;
        private final ResponseCallback<Map<Long, IStorageModel>> d;

        private a(qop qopVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.f16525a = new WeakReference<>(qopVar);
            this.d = responseCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qop qopVar = this.f16525a.get();
            if (qopVar == null) {
                LogUtil.h("TemperatureChartStorageHelper", "OldTypeMaxMinResponse helper is null");
                return;
            }
            ResponseCallback<Map<Long, IStorageModel>> responseCallback = this.d;
            if (responseCallback != null) {
                qopVar.e(responseCallback, obj instanceof List ? (List) obj : null);
            } else {
                LogUtil.h("TemperatureChartStorageHelper", "OldTypeMaxMinResponse callback is null");
            }
        }
    }

    static class b implements HiAggregateListener {
        private final WeakReference<qop> b;
        private ResponseCallback<Map<Long, IStorageModel>> c;
        private final List<HiHealthData> e;

        private b(qop qopVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiHealthData> list) {
            this.b = new WeakReference<>(qopVar);
            this.c = responseCallback;
            this.e = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            if (this.c == null) {
                LogUtil.h("TemperatureChartStorageHelper", "MaxMinDataReadListener callback is null");
                return;
            }
            qop qopVar = this.b.get();
            if (qopVar != null) {
                List d = qopVar.d(list, this.e);
                if (koq.b(d)) {
                    LogUtil.h("TemperatureChartStorageHelper", "MaxMinDataReadListener data is null!");
                    if (!qopVar.d) {
                        this.c.onResult(0, new HashMap());
                    }
                    qopVar.j.notifyMaxAndMin(-1, null);
                    return;
                }
                LogUtil.a("TemperatureChartStorageHelper", "MaxMinDataReadListener data size ", Integer.valueOf(d.size()));
                qopVar.c((List<HiHealthData>) d, this.c);
                this.c = null;
                return;
            }
            LogUtil.h("TemperatureChartStorageHelper", "MaxMinDataReadListener helper is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.c("TemperatureChartStorageHelper", "DataReadListener onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }
}
