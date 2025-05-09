package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateListener;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AggregateOptionBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class psl implements IChartStorageHelper {
    private AtomicInteger e = new AtomicInteger(0);
    private final Map<String, ResponseCallback<Map<Long, IStorageModel>>> c = new HashMap();

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        String e = bVar.e();
        if (dataInfos.isDayData()) {
            if (!nom.p(j)) {
                responseCallback.onResult(-1, null);
                return;
            }
            this.e.getAndIncrement();
            this.c.put(e, responseCallback);
            if (this.e.get() == 4) {
                this.e.set(0);
                psb.e(j, j2, this.c);
                return;
            }
            return;
        }
        d(dataInfos, e, c(j, j2, dataInfos, e), responseCallback);
    }

    private List<HiAggregateOption> c(long j, long j2, DataInfos dataInfos, String str) {
        ArrayList arrayList = new ArrayList(16);
        AggregateOptionBuilder c = new AggregateOptionBuilder().c(j, j2);
        if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(str)) {
            arrayList.add(c.a(psg.a(dataInfos), "HR_NORMAL_MAX", 46016));
            arrayList.add(c.a(psg.a(dataInfos), "HR_NORMAL_MIN", 46017));
        } else if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(str)) {
            arrayList.add(c.a(psg.c(dataInfos, "HR_REST"), "HR_REST", 46018));
            arrayList.add(c.a(psg.c(dataInfos, "HR_SLEEP_REST"), "HR_SLEEP_REST", 46020));
        } else if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(str)) {
            arrayList.add(c.a(psg.a(dataInfos), "HR_WARNING_MAX", 47003));
            arrayList.add(c.a(psg.a(dataInfos), "HR_WARNING_MIN", 47002));
            arrayList.add(c.a(psg.a(dataInfos), "HR_WARNING_DETAIL", 2101));
        } else if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(str)) {
            arrayList.add(c.a(psg.a(dataInfos), "BRADYCARDIA_MAX", 47053));
            arrayList.add(c.a(psg.a(dataInfos), "BRADYCARDIA_MIN", 47052));
            arrayList.add(c.a(psg.a(dataInfos), "BRADYCARDIA_DETAIL", 2102));
        } else {
            LogUtil.a("HeartRateStorageHelper", "showModeArg acquireDataLayerId is null");
        }
        return arrayList;
    }

    private Map<String, psj> c(DataInfos dataInfos, String str, List<HiAggregateOption> list) {
        HashMap hashMap = new HashMap(16);
        if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(str)) {
            hashMap.put("HR_NORMAL_MAX", new psj(0, list.get(0).getGroupUnitType()));
            hashMap.put("HR_NORMAL_MIN", new psj(1, list.get(1).getGroupUnitType()));
        } else if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(str)) {
            int groupUnitType = dataInfos.isYearData() ? 5 : list.get(0).getGroupUnitType();
            hashMap.put("HR_REST", new psj(0, groupUnitType));
            hashMap.put("HR_SLEEP_REST", new psj(1, groupUnitType));
        } else if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(str)) {
            hashMap.put("HR_WARNING_MAX", new psj(0, list.get(0).getGroupUnitType()));
            hashMap.put("HR_WARNING_MIN", new psj(1, list.get(1).getGroupUnitType()));
            hashMap.put("HR_WARNING_DETAIL", new psj(2, list.get(2).getGroupUnitType()));
        } else if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(str)) {
            hashMap.put("BRADYCARDIA_MAX", new psj(0, list.get(0).getGroupUnitType()));
            hashMap.put("BRADYCARDIA_MIN", new psj(1, list.get(1).getGroupUnitType()));
            hashMap.put("BRADYCARDIA_DETAIL", new psj(2, list.get(2).getGroupUnitType()));
        } else {
            LogUtil.a("HeartRateStorageHelper", "loadQueryOptionsConstMap is other");
        }
        return hashMap;
    }

    public void d(final DataInfos dataInfos, final String str, List<HiAggregateOption> list, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        final Map<String, psj> c = c(dataInfos, str, list);
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataEx(list, new HeartRateListener(responseCallback) { // from class: psl.4
            @Override // com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateListener
            public void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                LogUtil.a("HeartRateStorageHelper", "queryStatisticsData dataList ", sparseArray, " errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
                if (this.mCallback == null) {
                    LogUtil.h("HeartRateStorageHelper", "mCallback is null");
                    return;
                }
                if (sparseArray == null || sparseArray.size() == 0) {
                    LogUtil.h("HeartRateStorageHelper", "queryStatisticsData data is null type: ", str);
                    this.mCallback.onResult(-1, null);
                    return;
                }
                if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(str)) {
                    psg.dsI_(sparseArray, dataInfos);
                }
                HashMap hashMap = new HashMap(16);
                psl.this.dsO_(hashMap, sparseArray, str, c);
                LogUtil.a("HeartRateStorageHelper", "queryStatisticsData resultMap ", hashMap);
                responseCallback.onResult(0, hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dsO_(Map<Long, IStorageModel> map, SparseArray<List<HiHealthData>> sparseArray, String str, Map<String, psj> map2) {
        if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(str)) {
            psg.dsH_(map, "HR_NORMAL_MAX", sparseArray, map2.get("HR_NORMAL_MAX"));
            psg.dsH_(map, "HR_NORMAL_MIN", sparseArray, map2.get("HR_NORMAL_MIN"));
            psg.d(map, false);
            return;
        }
        if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(str)) {
            psg.dsH_(map, "HR_REST", sparseArray, map2.get("HR_REST"));
            psg.dsH_(map, "HR_REST_DETAIL", sparseArray, map2.get("HR_REST_DETAIL"));
            psg.c(map, false);
        } else {
            if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(str)) {
                psg.dsH_(map, "HR_WARNING_MAX", sparseArray, map2.get("HR_WARNING_MAX"));
                psg.dsH_(map, "HR_WARNING_MIN", sparseArray, map2.get("HR_WARNING_MIN"));
                psg.e(map, "HR_WARNING_DETAIL", sparseArray.get(2), map2.get("HR_WARNING_MAX"));
                psg.b(map, false);
                return;
            }
            if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(str)) {
                psg.dsH_(map, "BRADYCARDIA_MAX", sparseArray, map2.get("BRADYCARDIA_MAX"));
                psg.dsH_(map, "BRADYCARDIA_MIN", sparseArray, map2.get("BRADYCARDIA_MIN"));
                psg.e(map, "BRADYCARDIA_DETAIL", sparseArray.get(2), map2.get("BRADYCARDIA_MAX"));
                psg.a(map, false);
                return;
            }
            LogUtil.h("HeartRateStorageHelper", "showModeArg.acquireDataLayerId(): ", str);
        }
    }
}
