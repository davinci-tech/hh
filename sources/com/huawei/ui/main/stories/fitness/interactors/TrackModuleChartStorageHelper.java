package com.huawei.ui.main.stories.fitness.interactors;

import android.content.Context;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportDataStaticsInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.gts;
import defpackage.hln;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kwy;
import defpackage.nnc;
import defpackage.pxx;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class TrackModuleChartStorageHelper implements IChartStorageHelper {
    private int b;

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos == null || responseCallback == null) {
            LogUtil.a("TrackModuleChartStorageHelper", "queryStepDayData: stepDataType or callback == null");
            return;
        }
        this.b = c(dataInfos);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String format = simpleDateFormat.format(Long.valueOf(j));
        String format2 = simpleDateFormat.format(Long.valueOf(j2));
        int i = this.b;
        if (i != 10001) {
            if (i == 0) {
                c(context, j, j2, dataInfos, responseCallback);
                return;
            } else {
                e(context, j, j2, dataInfos, responseCallback);
                return;
            }
        }
        LogUtil.a("TrackModuleChartStorageHelper", "startTime:", Long.valueOf(j), "", "--startTime :", Long.valueOf(j), "dataType:", Integer.valueOf(this.b), "-statTimeS:", format, "-endTimeS:", format2);
        if (dataInfos.isAllData()) {
            d(j, j2, 6, responseCallback);
            return;
        }
        if (dataInfos.isYearData()) {
            d(j, j2, 5, responseCallback);
        } else if (dataInfos.isMonthData() || dataInfos.isWeekData()) {
            d(j, j2, 3, responseCallback);
        } else {
            LogUtil.h("TrackModuleChartStorageHelper", "stepDataType error");
        }
    }

    private HiSportStatDataAggregateOption a(long j, long j2, DataInfos dataInfos) {
        int i;
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{30001, 288, ComponentInfo.PluginPay_A_N});
        hiSportStatDataAggregateOption.setType(new int[]{Constants.REBACK_MARKET_ENTRANCE, 42005, Constants.REBACK_MARKET_RESULT_CODE, 42008, 42254, 42255, 42253, 5, 4});
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_Duration_Sum", "Track_Count_Sum", "Track_Calorie_Sum", "Track_Abnormal_Count_Sum", "Track_5124", "Track_5125", "Track_5123", "Track_Diving_Count", "Track_Diving_Time_Sum"});
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setReadType(0);
        if (dataInfos.isAllData()) {
            i = 6;
        } else {
            i = dataInfos.isYearData() ? 5 : 3;
        }
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        return hiSportStatDataAggregateOption;
    }

    private void c(Context context, long j, long j2, DataInfos dataInfos, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        int i;
        HiSportStatDataAggregateOption a2 = a(j, j2, dataInfos);
        if (dataInfos.isAllData()) {
            i = 6;
        } else {
            i = dataInfos.isYearData() ? 5 : 3;
        }
        final ArrayList arrayList = new ArrayList(2);
        final AtomicInteger atomicInteger = new AtomicInteger(2);
        final c cVar = new c(this);
        final int i2 = i;
        HiHealthNativeApi.a(context).aggregateSportStatData(a2, new HiAggregateListener() { // from class: com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                atomicInteger.decrementAndGet();
                if (list == null) {
                    LogUtil.h("TrackModuleChartStorageHelper", "setAllSportBarView track data is null");
                }
                arrayList.add(list);
                if (atomicInteger.intValue() == 0) {
                    cVar.c(arrayList, i2, responseCallback);
                }
            }
        });
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            return;
        }
        final int i3 = i;
        recordApi.acquireSummaryFitnessRecord(new kwy.a().a(j).e(j2).c(i).d(), new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj) {
                atomicInteger.decrementAndGet();
                if (obj == null) {
                    LogUtil.h("TrackModuleChartStorageHelper", "setAllSportBarView fitness data is null");
                }
                arrayList.add(obj);
                if (atomicInteger.intValue() == 0) {
                    cVar.c(arrayList, i3, responseCallback);
                }
            }
        });
    }

    private void e(Context context, long j, long j2, DataInfos dataInfos, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        hln c2 = hln.c(BaseApplication.getContext());
        if (c2.d(dataInfos.getSportType()) == null || c2.d(dataInfos.getSportType()).getSportDataStatics() == null) {
            LogUtil.b("TrackModuleChartStorageHelper", "can not find sport getDataType in json");
            return;
        }
        LogUtil.a("TrackModuleChartStorageHelper", "mSportType:", Integer.valueOf(dataInfos.getSportType()));
        List<HwSportDataStaticsInfo> sportDataStatics = c2.d(dataInfos.getSportType()).getSportDataStatics();
        int[] a2 = c2.a(sportDataStatics);
        String[] b = c2.b(sportDataStatics);
        if (a2 == null || b == null || a2.length < 1 || b.length < 1) {
            LogUtil.b("TrackModuleChartStorageHelper", "typesSum or constantKeySum data is error");
            return;
        }
        int[] iArr = {a2[0]};
        String[] strArr = {b[0]};
        int sportType = dataInfos.getSportType();
        if (sportType == 286) {
            String[] b2 = c2.b(c2.d(HeartRateThresholdConfig.HEART_RATE_LIMIT).getSportDataStatics());
            iArr = new int[]{a2[0], 42405, 42406};
            strArr = new String[]{b[0], b2[0], b2[1]};
        }
        String[] strArr2 = strArr;
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(strArr2);
        hiSportStatDataAggregateOption.setType(iArr);
        if (sportType == 287) {
            hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{sportType, 291});
        } else {
            hiSportStatDataAggregateOption.setHiHealthTypes(gts.a(sportType));
        }
        e(context, strArr2, dataInfos, responseCallback, hiSportStatDataAggregateOption);
    }

    private void d(long j, long j2, final int i, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("TrackModuleChartStorageHelper", "requestFitnessData recordApi is null.");
        } else {
            recordApi.acquireSummaryFitnessRecord(new kwy.a().a(j).e(j2).c(i).d(), new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    responseCallback.onResult(0, TrackModuleChartStorageHelper.this.c(obj, i));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, IStorageModel> c(Object obj, int i) {
        HashMap hashMap = new HashMap();
        if (obj == null || !koq.e(obj, FitnessTrackRecord.class)) {
            LogUtil.h("TrackModuleChartStorageHelper", "objData is null or objData is not List");
            return hashMap;
        }
        long j = i == 7 ? 0L : i == 6 ? com.huawei.openalliance.ad.constant.Constants.TWO_WEEK : 43200000L;
        if (!koq.e(obj, FitnessTrackRecord.class)) {
            LogUtil.h("TrackModuleChartStorageHelper", "objData instance fail!");
            return hashMap;
        }
        for (FitnessTrackRecord fitnessTrackRecord : (List) obj) {
            if (fitnessTrackRecord != null) {
                long acquireMonthZeroTime = fitnessTrackRecord.acquireMonthZeroTime();
                if (i == 7) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(acquireMonthZeroTime);
                    calendar.set(2, 5);
                    calendar.set(5, 30);
                    calendar.set(11, 0);
                    calendar.set(12, 0);
                    acquireMonthZeroTime = calendar.getTimeInMillis();
                }
                float acquireSumExerciseTime = (fitnessTrackRecord.acquireSumExerciseTime() / 1000.0f) / 60.0f;
                LogUtil.a("TrackModuleChartStorageHelper", "fitnessTrackRecords startTime:", Long.valueOf(acquireMonthZeroTime), "-duringMin:", Float.valueOf(acquireSumExerciseTime));
                hashMap.put(Long.valueOf(acquireMonthZeroTime + j), new nnc(acquireSumExerciseTime));
            }
        }
        return hashMap;
    }

    private int c(DataInfos dataInfos) {
        if (dataInfos.isStepData()) {
            return dataInfos.isSumData() ? 40002 : 2;
        }
        if (dataInfos.isCaloriesData()) {
            return dataInfos.isSumData() ? 40003 : 4;
        }
        if (dataInfos.isDistanceData()) {
            return dataInfos.isSumData() ? 40004 : 3;
        }
        if (dataInfos.isClimbData()) {
            if (dataInfos.isSumData()) {
                return SmartMsgConstant.MSG_TYPE_RIDE_USER;
            }
            return 5;
        }
        if (dataInfos.isSportFitnessData()) {
            return 10001;
        }
        return d(dataInfos);
    }

    private int d(DataInfos dataInfos) {
        hln c2 = hln.c(BaseApplication.getContext());
        if (c2.d(dataInfos.getSportType()) == null || c2.d(dataInfos.getSportType()).getSportDataStatics() == null) {
            LogUtil.b("TrackModuleChartStorageHelper", "can not find sport getDataType in json");
            return 258;
        }
        LogUtil.a("TrackModuleChartStorageHelper", "mSportType:", Integer.valueOf(dataInfos.getSportType()));
        if (dataInfos.getSportType() == 0) {
            return 0;
        }
        int[] a2 = c2.a(c2.d(dataInfos.getSportType()).getSportDataStatics());
        if (a2 == null || a2.length < 1) {
            LogUtil.b("TrackModuleChartStorageHelper", "typesId is wrong");
            return 258;
        }
        int i = a2[0];
        int i2 = 2;
        if (i != 2) {
            i2 = 4;
            if (i != 4) {
                i2 = 5;
                if (i != 5) {
                    i2 = 21;
                    if (i != 21) {
                        i2 = 22;
                        if (i != 22) {
                            LogUtil.b("TrackModuleChartStorageHelper", "typesSum is not time or distance", Integer.valueOf(i));
                            return 40002;
                        }
                    }
                }
            }
        }
        return i2;
    }

    private void e(Context context, final String[] strArr, final DataInfos dataInfos, ResponseCallback<Map<Long, IStorageModel>> responseCallback, HiSportStatDataAggregateOption hiSportStatDataAggregateOption) {
        int i;
        if (dataInfos.isAllData()) {
            i = 6;
        } else {
            i = dataInfos.isYearData() ? 5 : 3;
        }
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        HiHealthNativeApi.a(context).aggregateSportStatData(hiSportStatDataAggregateOption, new SportDataModuleReadListener(responseCallback) { // from class: com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.2
            @Override // com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.SportDataModuleReadListener
            public void onResultData(List<HiHealthData> list, int i2, int i3) {
                if (this.mCallback == null) {
                    LogUtil.h("TrackModuleChartStorageHelper", "requestTrackStatData callback is null");
                    return;
                }
                HashMap hashMap = new HashMap();
                if (list != null && list.size() > 0) {
                    TrackModuleChartStorageHelper.this.b(strArr, list, hashMap, dataInfos);
                    this.mCallback.onResult(i2, hashMap);
                } else {
                    LogUtil.h("TrackModuleChartStorageHelper", "aggregateHiHealthDataEx requestTrackBarData onResult is null");
                    this.mCallback.onResult(-1, null);
                }
            }

            @Override // com.huawei.ui.main.stories.fitness.interactors.TrackModuleChartStorageHelper.SportDataModuleReadListener
            public void onResultIntentData(int i2, List<HiHealthData> list, int i3, int i4) {
                if (this.mCallback == null) {
                    LogUtil.h("TrackModuleChartStorageHelper", "requestTrackStatData callback is null");
                } else {
                    LogUtil.a("TrackModuleChartStorageHelper", "onResultIntent");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String[] strArr, List<HiHealthData> list, Map<Long, IStorageModel> map, DataInfos dataInfos) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.b("TrackModuleChartStorageHelper", "hiHealthData is null");
                return;
            }
            int i = hiHealthData.getInt("hihealth_type");
            long e = e(hiHealthData, dataInfos);
            double d = hiHealthData.getDouble(strArr[0]);
            if (i == 286 || i == 30001) {
                double d2 = hiHealthData.getDouble(c(i, strArr));
                if (strArr.length > 2) {
                    double d3 = hiHealthData.getDouble(strArr[2]);
                    d2 -= d3;
                    LogUtil.a("TrackModuleChartStorageHelper", "handleYearTrackBarData, abnormalValue:", Double.valueOf(d3), ", value:", Double.valueOf(d2));
                }
                d(map, e, d2);
            } else if (i == 287 || i == 291) {
                d(map, e, d);
            } else {
                map.put(Long.valueOf(e), new nnc(c(d)));
            }
        }
    }

    private long e(HiHealthData hiHealthData, DataInfos dataInfos) {
        long startTime = hiHealthData.getStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startTime);
        calendar.set(11, 0);
        calendar.set(12, 0);
        if (dataInfos.isAllData()) {
            calendar.set(2, 5);
            calendar.set(5, 30);
            return calendar.getTimeInMillis();
        }
        if (!dataInfos.isYearData()) {
            return 43200000 + startTime;
        }
        calendar.set(5, 1);
        return calendar.getTimeInMillis() + com.huawei.openalliance.ad.constant.Constants.TWO_WEEK;
    }

    private float c(double d) {
        float f;
        float f2;
        int i = this.b;
        if (i != 4) {
            if (i != 5) {
                if (i == 21) {
                    f = (float) d;
                    f2 = 60.0f;
                } else {
                    if (i != 22) {
                        return UnitUtil.h() ? (float) ((d / 1000.0d) * UnitUtil.e(1.0d, 3)) : ((float) d) / 1000.0f;
                    }
                    if (UnitUtil.h()) {
                        d = UnitUtil.a(UnitUtil.e(d / 10.0d, 1), 1);
                    } else {
                        d = UnitUtil.a(d / 10.0d, 1);
                    }
                }
            }
            return (float) d;
        }
        f = (float) d;
        f2 = 60000.0f;
        return f / f2;
    }

    private void d(Map<Long, IStorageModel> map, long j, double d) {
        if (map.containsKey(Long.valueOf(j))) {
            IStorageModel iStorageModel = map.get(Long.valueOf(j));
            if (iStorageModel instanceof nnc) {
                map.put(Long.valueOf(j), new nnc(((nnc) iStorageModel).b() + c(d)));
                return;
            }
            return;
        }
        map.put(Long.valueOf(j), new nnc(c(d)));
    }

    private String c(int i, String[] strArr) {
        String str = strArr[0];
        return (i != 30001 || strArr.length <= 1) ? str : strArr[1];
    }

    static class c {
        WeakReference<TrackModuleChartStorageHelper> c;
        private Map<Long, IStorageModel> b = new HashMap();
        private Map<Long, IStorageModel> d = new HashMap();

        private float b(double d) {
            return (float) (d / 1000.0d);
        }

        private long b(int i) {
            if (i == 6) {
                return 0L;
            }
            if (i == 5) {
                return com.huawei.openalliance.ad.constant.Constants.TWO_WEEK;
            }
            return 43200000L;
        }

        private float e(double d) {
            return (float) ((d / 1000.0d) / 60.0d);
        }

        c(TrackModuleChartStorageHelper trackModuleChartStorageHelper) {
            this.c = new WeakReference<>(trackModuleChartStorageHelper);
        }

        void c(List<Object> list, int i, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            if (list.size() < 2) {
                responseCallback.onResult(-1, null);
                return;
            }
            if (list.get(0) == null && list.get(1) == null) {
                responseCallback.onResult(-1, null);
                return;
            }
            long b = b(i);
            for (Object obj : list) {
                if (koq.e(obj, HiHealthData.class)) {
                    if (obj instanceof List) {
                        c((List<HiHealthData>) obj, i, b);
                    }
                } else if (koq.e(obj, FitnessTrackRecord.class)) {
                    if (obj instanceof List) {
                        b((List) obj, i, b);
                    }
                } else {
                    LogUtil.a("TrackModuleChartStorageHelper", "wrong type");
                }
            }
            d(responseCallback);
        }

        private void c(List<HiHealthData> list, int i, long j) {
            if (list == null) {
                LogUtil.h("TrackModuleChartStorageHelper", "collectTrackSportData trackRecords is null");
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, 5);
            calendar.set(5, 30);
            calendar.set(11, 0);
            calendar.set(12, 0);
            for (int i2 = 0; i2 < list.size(); i2++) {
                long c = c(i, list.get(i2).getStartTime());
                calendar.setTimeInMillis(c);
                pxx b = b(list.get(i2));
                long j2 = c + j;
                if (this.b.containsKey(Long.valueOf(j2))) {
                    pxx pxxVar = (pxx) this.b.get(Long.valueOf(j2));
                    b.put(DataLayerType.DURATION, b.getCeil(DataLayerType.DURATION) + pxxVar.getCeil(DataLayerType.DURATION));
                    b.put(DataLayerType.TIMES, b.getCeil(DataLayerType.TIMES) + pxxVar.getCeil(DataLayerType.TIMES));
                    b.put(DataLayerType.CALORIES, b.getCeil(DataLayerType.CALORIES) + pxxVar.getCeil(DataLayerType.CALORIES));
                }
                this.b.put(Long.valueOf(j2), b);
            }
        }

        private pxx b(HiHealthData hiHealthData) {
            pxx pxxVar = new pxx();
            pxxVar.put(DataLayerType.DURATION, e((hiHealthData.getDouble("Track_Duration_Sum") + hiHealthData.getDouble("Track_5124")) - hiHealthData.getDouble("Track_Diving_Time_Sum")));
            DataLayerType dataLayerType = DataLayerType.TIMES;
            float f = hiHealthData.getFloat("Track_Count_Sum");
            float f2 = hiHealthData.getFloat("Track_5125");
            pxxVar.put(dataLayerType, ((f + f2) - hiHealthData.getFloat("Track_Abnormal_Count_Sum")) - hiHealthData.getFloat("Track_Diving_Count"));
            pxxVar.put(DataLayerType.CALORIES, b(hiHealthData.getDouble("Track_Calorie_Sum") + hiHealthData.getDouble("Track_5123")));
            return pxxVar;
        }

        private void b(List<FitnessTrackRecord> list, int i, long j) {
            if (list == null) {
                LogUtil.h("TrackModuleChartStorageHelper", "collectFitnessSportData fitnessRecords is null");
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(2, 5);
            calendar.set(5, 30);
            calendar.set(11, 0);
            calendar.set(12, 0);
            for (int i2 = 0; i2 < list.size(); i2++) {
                long c = c(i, list.get(i2).acquireMonthZeroTime());
                calendar.setTimeInMillis(c);
                this.d.put(Long.valueOf(c + j), c(list.get(i2)));
            }
        }

        private pxx c(FitnessTrackRecord fitnessTrackRecord) {
            pxx pxxVar = new pxx();
            pxxVar.put(DataLayerType.DURATION, e(fitnessTrackRecord.acquireSumExerciseTime()));
            pxxVar.put(DataLayerType.TIMES, fitnessTrackRecord.acquireSumExerciseTimes());
            pxxVar.put(DataLayerType.CALORIES, b(fitnessTrackRecord.acquireSumCalorie()));
            return pxxVar;
        }

        private long c(int i, long j) {
            if (i == 6) {
                return jdl.p(j);
            }
            return i == 5 ? jdl.s(j) : j;
        }

        private void d(ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            if (this.b.isEmpty()) {
                responseCallback.onResult(0, this.d);
                return;
            }
            if (this.d.isEmpty()) {
                responseCallback.onResult(0, this.b);
                return;
            }
            for (Map.Entry<Long, IStorageModel> entry : this.d.entrySet()) {
                if (this.b.containsKey(entry.getKey())) {
                    if (!(this.b.get(entry.getKey()) instanceof pxx) || !(entry.getValue() instanceof pxx)) {
                        break;
                    }
                    pxx pxxVar = (pxx) this.b.get(entry.getKey());
                    pxx pxxVar2 = (pxx) entry.getValue();
                    pxx pxxVar3 = new pxx();
                    pxxVar3.put(DataLayerType.DURATION, pxxVar.getCeil(DataLayerType.DURATION) + pxxVar2.getCeil(DataLayerType.DURATION));
                    pxxVar3.put(DataLayerType.TIMES, pxxVar.getCeil(DataLayerType.TIMES) + pxxVar2.getCeil(DataLayerType.TIMES));
                    pxxVar3.put(DataLayerType.CALORIES, pxxVar.getCeil(DataLayerType.CALORIES) + pxxVar2.getCeil(DataLayerType.CALORIES));
                    this.b.put(entry.getKey(), pxxVar3);
                } else {
                    this.b.put(entry.getKey(), entry.getValue());
                }
            }
            responseCallback.onResult(0, this.b);
        }
    }

    public static abstract class SportDataModuleReadListener implements HiAggregateListener {
        protected ResponseCallback<Map<Long, IStorageModel>> mCallback;

        public abstract void onResultData(List<HiHealthData> list, int i, int i2);

        public abstract void onResultIntentData(int i, List<HiHealthData> list, int i2, int i3);

        protected SportDataModuleReadListener(ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.mCallback = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.h("TrackModuleChartStorageHelper", "StepModuleReadListener onResult", "anchor", Integer.valueOf(i2));
            onResultData(list, i, i2);
            if (i2 == 2 || i == 1) {
                this.mCallback = null;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.h("TrackModuleChartStorageHelper", "StepModuleReadListener onResultIntent", "anchor", Integer.valueOf(i3));
            onResultIntentData(i, list, i2, i3);
            if (i3 == 2 || i2 == 1) {
                this.mCallback = null;
            }
        }
    }
}
