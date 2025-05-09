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
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qom implements IChartStorageHelper {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16521a;
    private long c;
    private long h;
    private String d = "TEMPERATURE_MIN_MAX";
    private final boolean b = UnitUtil.d();
    private final qpk e = qpk.d();

    public void c(String str) {
        LogUtil.c("KnitTemperatureChartStorageHelper", "setShowDataType is ", str);
        this.d = str;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (responseCallback == null) {
            return;
        }
        this.h = j;
        this.c = j2 - 1;
        a(dataInfos, responseCallback);
    }

    private void a(DataInfos dataInfos, ResponseCallback responseCallback) {
        if (dataInfos.isDayData()) {
            c(responseCallback);
            this.f16521a = true;
        }
        a(responseCallback);
    }

    private void a(ResponseCallback responseCallback) {
        int[] iArr = {2104};
        this.e.a(iArr, e(iArr), new d(responseCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ResponseCallback responseCallback, List<HiHealthData> list) {
        HiAggregateOption d2 = d();
        d2.setType(new int[]{this.e.f(), this.e.i(), this.e.l(), this.e.n(), this.e.q(), this.e.r()});
        d2.setConstantsKey(new String[]{"body_min_key", "body_max_key", "skin_min_key", "skin_max_key", "suspected_temperature_high_min_key", "suspected_temperature_high_max_key"});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(d2, new b(responseCallback, list));
    }

    private HiAggregateOption d() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(this.h);
        hiAggregateOption.setEndTime(this.c);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    private HiDataReadOption e(int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(this.h);
        hiDataReadOption.setEndTime(this.c);
        return hiDataReadOption;
    }

    private void c(ResponseCallback responseCallback) {
        int[] iArr;
        if ("TEMPERATURE_MIN_MAX".equals(this.d)) {
            iArr = new int[]{this.e.b(), 2104, this.e.s()};
        } else {
            iArr = new int[]{this.e.o()};
        }
        this.e.a(iArr, e(iArr), new e(responseCallback), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> c(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.b(list2)) {
            LogUtil.h("KnitTemperatureChartStorageHelper", "addOldData oldList is null!");
            return list;
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData != null) {
                long d2 = jec.d(hiHealthData.getStartTime());
                float floatValue = hiHealthData.getFloatValue();
                HiHealthData hiHealthData2 = hashMap.get(Long.valueOf(d2));
                if (hiHealthData2 == null) {
                    hiHealthData2 = new HiHealthData();
                    hiHealthData2.setTimeInterval(d2, d2);
                    hiHealthData2.putFloat("body_min_key", floatValue);
                    hiHealthData2.putFloat("body_max_key", floatValue);
                } else {
                    float f = hiHealthData2.getFloat("body_max_key");
                    float f2 = hiHealthData2.getFloat("body_min_key");
                    float max = Math.max(floatValue, f);
                    hiHealthData2.putFloat("body_min_key", Math.min(floatValue, f2));
                    hiHealthData2.putFloat("body_max_key", max);
                }
                hashMap.put(Long.valueOf(d2), hiHealthData2);
            }
        }
        if (koq.b(list)) {
            LogUtil.h("KnitTemperatureChartStorageHelper", "addOldData list is null");
            a(arrayList, hashMap);
            return arrayList;
        }
        a(list, hashMap, arrayList);
        return arrayList;
    }

    private void a(List<HiHealthData> list, Map<Long, HiHealthData> map, List<HiHealthData> list2) {
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
    }

    private void a(List<HiHealthData> list, Map<Long, HiHealthData> map) {
        Iterator<Map.Entry<Long, HiHealthData>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            list.add(it.next().getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (responseCallback == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                float floatValue = hiHealthData.getFloatValue();
                if (qpr.b(floatValue, this.d)) {
                    LogUtil.h("KnitTemperatureChartStorageHelper", "responseDayAvgData value ", Float.valueOf(floatValue));
                } else {
                    if (!this.b) {
                        floatValue = qpr.c(floatValue);
                    }
                    b(hashMap, hiHealthData, new BigDecimal(String.valueOf(floatValue)).setScale(1, 4).floatValue());
                }
            }
        }
        LogUtil.a("KnitTemperatureChartStorageHelper", "responseDayAvgData resultMap size ", Integer.valueOf(hashMap.size()));
        d(hashMap, responseCallback);
    }

    private void b(Map<Long, IStorageModel> map, HiHealthData hiHealthData, float f) {
        edm edmVar;
        long e2 = qpr.e(hiHealthData.getStartTime());
        IStorageModel iStorageModel = map.get(Long.valueOf(e2));
        if (iStorageModel instanceof edm) {
            edmVar = (edm) iStorageModel;
            if (hiHealthData.getType() == this.e.s()) {
                if (edmVar.d() == 0.0f || edmVar.c() == 0.0f) {
                    edmVar.a(f);
                    edmVar.b(f);
                } else {
                    float min = Math.min(edmVar.d(), f);
                    float max = Math.max(edmVar.c(), f);
                    edmVar.a(min);
                    edmVar.b(max);
                }
            } else if (edmVar.i() == 0.0f || edmVar.a() == 0.0f) {
                edmVar.d(f);
                edmVar.e(f);
            } else {
                float min2 = Math.min(edmVar.i(), f);
                float max2 = Math.max(edmVar.a(), f);
                edmVar.e(min2);
                edmVar.d(max2);
            }
        } else {
            edmVar = new edm(42.0f, new edj());
            if (hiHealthData.getType() == this.e.s()) {
                edmVar.a(f);
                edmVar.b(f);
            } else {
                edmVar.d(f);
                edmVar.e(f);
            }
        }
        map.put(Long.valueOf(e2), edmVar);
    }

    private void d(Map<Long, IStorageModel> map, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        Map<Long, IStorageModel> hashMap = new HashMap<>(16);
        for (Map.Entry<Long, IStorageModel> entry : map.entrySet()) {
            IStorageModel value = entry.getValue();
            if (value instanceof edm) {
                edm edmVar = (edm) value;
                edj edjVar = new edj();
                float c = qpr.c(edmVar.a(), edmVar.i(), edmVar.c(), edmVar.d());
                float d2 = qpr.d(edmVar.a(), edmVar.i(), edmVar.c(), edmVar.d());
                edjVar.e(c);
                edjVar.a(d2);
                edmVar.a(edjVar);
                hashMap.put(entry.getKey(), edmVar);
            }
        }
        LogUtil.a("KnitTemperatureChartStorageHelper", "dealHourAvgCallback resultMap size ", Integer.valueOf(hashMap.size()));
        responseCallback.onResult(0, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        c(list, responseCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.util.List<com.huawei.hihealth.HiHealthData> r26, com.huawei.ui.commonui.linechart.utils.ResponseCallback<java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel>> r27) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qom.c(java.util.List, com.huawei.ui.commonui.linechart.utils.ResponseCallback):void");
    }

    private void d(Map<Long, IStorageModel> map, long j, float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float c = qpr.c(f, f2, f3, f4);
        float d2 = qpr.d(f, f2, f3, f4);
        edj edjVar = new edj();
        edjVar.a(d2);
        edjVar.e(c);
        edm edmVar = new edm(42.0f, edjVar);
        edmVar.d(f);
        edmVar.e(f2);
        edmVar.b(f3);
        edmVar.a(f4);
        map.put(Long.valueOf(j), edmVar);
    }

    static class e implements IBaseResponseCallback {
        private final qom b;
        private final ResponseCallback<Map<Long, IStorageModel>> c;

        private e(qom qomVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.b = qomVar;
            this.c = responseCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qom qomVar = this.b;
            if (qomVar == null || this.c == null) {
                LogUtil.b("KnitTemperatureChartStorageHelper", "mHelper: ", qomVar, " mCallback: ", this.c);
                return;
            }
            HashMap hashMap = new HashMap(16);
            if (i != 0) {
                LogUtil.h("KnitTemperatureChartStorageHelper", "DayPointDataResponse errorCode ", Integer.valueOf(i));
                this.c.onResult(0, hashMap);
                return;
            }
            List list = obj instanceof List ? (List) obj : null;
            if (!koq.b(list)) {
                this.b.a((List<HiHealthData>) list, this.c);
            } else {
                LogUtil.h("KnitTemperatureChartStorageHelper", "DayPointDataResponse list is empty");
                this.c.onResult(0, hashMap);
            }
        }
    }

    static class d implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final qom f16522a;
        private final ResponseCallback<Map<Long, IStorageModel>> d;

        private d(qom qomVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.f16522a = qomVar;
            this.d = responseCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            qom qomVar = this.f16522a;
            if (qomVar == null) {
                LogUtil.h("KnitTemperatureChartStorageHelper", "OldTypeMaxMinResponse helper is null");
                return;
            }
            ResponseCallback<Map<Long, IStorageModel>> responseCallback = this.d;
            if (responseCallback != null) {
                qomVar.e(responseCallback, obj instanceof List ? (List) obj : null);
            } else {
                LogUtil.h("KnitTemperatureChartStorageHelper", "OldTypeMaxMinResponse callback is null");
            }
        }
    }

    static class b implements HiAggregateListener {
        private ResponseCallback<Map<Long, IStorageModel>> b;
        private final List<HiHealthData> c;
        private final qom d;

        private b(qom qomVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiHealthData> list) {
            this.d = qomVar;
            this.b = responseCallback;
            this.c = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            if (this.b == null) {
                LogUtil.h("KnitTemperatureChartStorageHelper", "MaxMinDataReadListener callback is null");
                return;
            }
            qom qomVar = this.d;
            if (qomVar != null) {
                List c = qomVar.c(list, this.c);
                if (koq.b(c)) {
                    LogUtil.h("KnitTemperatureChartStorageHelper", "MaxMinDataReadListener data is null!");
                    if (this.d.f16521a) {
                        return;
                    }
                    this.b.onResult(0, new HashMap());
                    return;
                }
                LogUtil.a("KnitTemperatureChartStorageHelper", "MaxMinDataReadListener data size ", Integer.valueOf(c.size()));
                this.d.b(c, this.b);
                this.b = null;
                return;
            }
            LogUtil.h("KnitTemperatureChartStorageHelper", "MaxMinDataReadListener helper is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.c("KnitTemperatureChartStorageHelper", "DataReadListener onResultIntent errorCode = ", Integer.valueOf(i2));
        }
    }
}
