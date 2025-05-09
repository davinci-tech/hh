package com.huawei.ui.main.stories.fitness.interactors;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartEnumDetailHelper;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nnc;
import defpackage.nne;
import defpackage.nrn;
import defpackage.nsj;
import defpackage.pxc;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes6.dex */
public class StepModuleChartStorageHelper implements IChartStorageHelper, IChartEnumDetailHelper {
    private static boolean d = false;
    private List<Integer> j = new ArrayList();
    private int e = nrn.d(R.color._2131296438_res_0x7f0900b6);
    private int c = nrn.d(R.color._2131296445_res_0x7f0900bd);
    private int g = nrn.d(R.color._2131299261_res_0x7f090bbd);
    private int f = nrn.d(R.color._2131299260_res_0x7f090bbc);
    private int b = nrn.d(R.color._2131299259_res_0x7f090bbb);

    /* renamed from: a, reason: collision with root package name */
    private int f9913a = nrn.d(R.color._2131299258_res_0x7f090bba);
    private long i = 0;

    private int d(int i) {
        if (i == 2) {
            return 40002;
        }
        if (i == 4) {
            return 40003;
        }
        if (i == 3) {
            return 40004;
        }
        if (i == 5) {
            return SmartMsgConstant.MSG_TYPE_RIDE_USER;
        }
        if (i == 7) {
            return 47101;
        }
        return i;
    }

    public StepModuleChartStorageHelper() {
        this.j.add(2);
        this.j.add(4);
        this.j.add(3);
        this.j.add(5);
    }

    public void e(List<Integer> list) {
        if (list == null) {
            LogUtil.h("StepModuleChartStorageHelper", "specifiedPreloadHiHealthDataTypes preloadHiHealthDataTypes is null  ");
        } else {
            this.j.clear();
            this.j.addAll(list);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartEnumDetailHelper
    public List<Integer> queryDetailDayData() {
        return this.j;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos == null || responseCallback == null) {
            LogUtil.h("StepModuleChartStorageHelper", "queryStepDayData stepDataType and callback == null ");
            return;
        }
        long j3 = j2 - 1;
        int d2 = d(dataInfos);
        if (dataInfos.isTimeStrengthData() || dataInfos.isActiveHoursData()) {
            if (dataInfos.isYearData()) {
                e(context, j, j3, d2, responseCallback);
                LogUtil.c("StepModuleChartStorageHelper", "isYearData start  ", Long.valueOf(j), "endTime", Long.valueOf(j3), "dataType  ", Integer.valueOf(d2));
                return;
            } else if (dataInfos.isDayData()) {
                b(context, j, j3, d2, responseCallback);
                LogUtil.c("StepModuleChartStorageHelper", "isDayData start  ", Long.valueOf(j), "  endTime", Long.valueOf(j3), "  dataType  ", Integer.valueOf(d2));
                return;
            } else {
                c(context, j, j3, d2, responseCallback);
                LogUtil.c("StepModuleChartStorageHelper", "is weekAndMonth start  ", Long.valueOf(j), "  endTime", Long.valueOf(j3), "  dataType  ", Integer.valueOf(d2));
                return;
            }
        }
        float a2 = a(dataInfos);
        a e = e(j, responseCallback, j3, d2, a2);
        if (dataInfos.isYearData()) {
            b(context, e);
        } else if (dataInfos.isDayData()) {
            e(j, dataInfos, responseCallback, a2, e);
        } else {
            d(context, e);
        }
    }

    private void e(long j, DataInfos dataInfos, ResponseCallback<Map<Long, IStorageModel>> responseCallback, float f, a aVar) {
        String str;
        SparseArray<List<HiHealthData>> sparseArray;
        if (dataInfos.isStepData()) {
            str = nsj.b(j);
            sparseArray = pxc.duI_(str);
            if (d && sparseArray != null && sparseArray.size() == 2) {
                LogUtil.a("StepModuleChartStorageHelper", "show step day data use cache");
                duO_(sparseArray, f, responseCallback);
            }
        } else {
            str = "";
            sparseArray = null;
        }
        LogUtil.a("StepModuleChartStorageHelper", "query step day data start");
        this.i = System.currentTimeMillis();
        duN_(aVar, sparseArray, str);
    }

    private a e(long j, ResponseCallback<Map<Long, IStorageModel>> responseCallback, long j2, int i, float f) {
        a aVar = new a();
        aVar.c(j);
        aVar.e(j2);
        aVar.d(i);
        aVar.b(f);
        aVar.a(responseCallback);
        return aVar;
    }

    private float a(DataInfos dataInfos) {
        double e;
        float f = 0.001f;
        if (dataInfos.isDistanceData()) {
            if (!UnitUtil.h()) {
                return 0.001f;
            }
            e = UnitUtil.e(1.0d, 3);
        } else {
            if (!dataInfos.isClimbData()) {
                return dataInfos.isCaloriesData() ? 0.001f : 1.0f;
            }
            f = 0.1f;
            if (!UnitUtil.h()) {
                return 0.1f;
            }
            e = UnitUtil.e(1.0d, 1);
        }
        return f * ((float) e);
    }

    private void c(Context context, long j, long j2, int i, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{i});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new StepModuleReadListener(responseCallback, i) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.4
            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleReadListener
            public void onResultIntentData(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleReadListener
            public void onResultData(Object obj, int i2, int i3) {
                if (obj == null) {
                    return;
                }
                LogUtil.a("StepModuleChartStorageHelper", "requestDayMiddleAndHighStrengthBarData data", obj.toString(), "  errorCode", Integer.valueOf(i2), " anchor", Integer.valueOf(i3));
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("StepModuleChartStorageHelper", "data instance fail");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() == 0) {
                    LogUtil.h("StepModuleChartStorageHelper", "requestDayMiddleAndHighStrengthBarData data is null");
                    this.mCallback.onResult(-1, null);
                    return;
                }
                Object obj2 = sparseArray.get(this.mDataType);
                if (!koq.e(obj2, HiHealthData.class)) {
                    LogUtil.h("StepModuleChartStorageHelper", "stepObj instance fail");
                    return;
                }
                HashMap hashMap = new HashMap();
                for (HiHealthData hiHealthData : (List) obj2) {
                    if (hiHealthData != null) {
                        long startTime = hiHealthData.getStartTime();
                        hashMap.put(Long.valueOf(startTime + 43200000), new nnc(hiHealthData.getFloatValue()));
                    }
                }
                this.mCallback.onResult(0, hashMap);
            }
        });
    }

    private void b(Context context, final long j, long j2, final int i, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{i});
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new StepModuleReadListener(responseCallback, i) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.5
            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleReadListener
            public void onResultIntentData(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleReadListener
            public void onResultData(Object obj, int i2, int i3) {
                Map<Long, IStorageModel> map;
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("StepModuleChartStorageHelper", "data instance fail");
                    return;
                }
                LogUtil.a("StepModuleChartStorageHelper", "requestHalfHourMiddleAndHighStrengthBarData errorCode", Integer.valueOf(i2), " anchor", Integer.valueOf(i3));
                SparseArray sparseArray = (SparseArray) obj;
                int i4 = 0;
                if (sparseArray.size() == 0) {
                    LogUtil.h("StepModuleChartStorageHelper", " data is null");
                    if (i == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()) {
                        map = StepModuleChartStorageHelper.this.a();
                    } else {
                        map = null;
                        i4 = -1;
                    }
                    this.mCallback.onResult(i4, map);
                    return;
                }
                Object obj2 = sparseArray.get(this.mDataType);
                if (koq.e(obj2, HiHealthData.class)) {
                    List list = (List) obj2;
                    Map<Long, IStorageModel> b = StepModuleChartStorageHelper.this.b((List<HiHealthData>) list);
                    if (i == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()) {
                        b = StepModuleChartStorageHelper.this.b((List<HiHealthData>) list, j);
                    }
                    this.mCallback.onResult(0, b);
                    return;
                }
                LogUtil.h("StepModuleChartStorageHelper", "stepObj instance fail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, IStorageModel> b(List<HiHealthData> list) {
        HashMap hashMap = new HashMap();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                LogUtil.c("StepModuleChartStorageHelper", " requestHalfHourMiddleAndHighStrengthBarData   ", Float.valueOf(hiHealthData.getFloatValue()));
                Calendar a2 = a(hiHealthData.getStartTime());
                IStorageModel iStorageModel = (IStorageModel) hashMap.get(Long.valueOf(a2.getTimeInMillis()));
                nnc nncVar = iStorageModel instanceof nnc ? (nnc) iStorageModel : null;
                if (nncVar == null) {
                    hashMap.put(Long.valueOf(a2.getTimeInMillis()), new nnc(1.0f));
                } else {
                    hashMap.put(Long.valueOf(a2.getTimeInMillis()), new nnc(nncVar.b() + 1.0f));
                }
            }
        }
        return hashMap;
    }

    private void e(Context context, long j, long j2, int i, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        c(context, responseCallback, e(i, j, j2));
    }

    private void c(Context context, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiAggregateOption> list) {
        HiHealthManager.d(context).aggregateHiHealthDataEx(list, new StepModuleAggregateListener(responseCallback, 0.0f) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.3
            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleAggregateListener
            public void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                if (sparseArray == null) {
                    this.mCallback.onResult(-1, null);
                    return;
                }
                if (sparseArray.size() == 0) {
                    LogUtil.h("StepModuleChartStorageHelper", " data is null");
                    this.mCallback.onResult(-1, null);
                    return;
                }
                List<HiHealthData> list2 = sparseArray.get(0);
                List<HiHealthData> list3 = sparseArray.get(1);
                HashMap hashMap = new HashMap();
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    try {
                        HiHealthData hiHealthData = list2.get(i3);
                        HiHealthData hiHealthData2 = list3.get(i3);
                        long startTime = hiHealthData.getStartTime();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(startTime);
                        calendar.set(5, 1);
                        calendar.set(11, 0);
                        calendar.set(12, 0);
                        float f = hiHealthData.getFloat("content");
                        float f2 = hiHealthData2.getFloat("sum");
                        if (Math.abs(f) < 1.0E-4d) {
                            hashMap.put(Long.valueOf(calendar.getTimeInMillis() + 1296000000), new nne(f, f2, 0));
                        } else {
                            hashMap.put(Long.valueOf(calendar.getTimeInMillis() + 1296000000), new nne(f, f2, Math.round(f2 / f)));
                        }
                    } catch (Exception e) {
                        LogUtil.b("StepModuleChartStorageHelper", LogAnonymous.b((Throwable) e));
                    }
                }
                this.mCallback.onResult(0, hashMap);
            }
        });
    }

    private int d(DataInfos dataInfos) {
        if (dataInfos.isStepData()) {
            if (!dataInfos.isSumData()) {
                return 2;
            }
        } else {
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
            if (dataInfos.isSportRunData()) {
                return 258;
            }
            if (dataInfos.isSportWalkData()) {
                return 257;
            }
            if (dataInfos.isSportBikeData()) {
                return 259;
            }
            if (dataInfos.isTimeStrengthData()) {
                return dataInfos.isSumData() ? 47101 : 7;
            }
            if (dataInfos.isActiveHoursData()) {
                if (dataInfos.isSumData()) {
                    return DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE_COUNT.value();
                }
                return DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value();
            }
        }
        return 40002;
    }

    private void d(Context context, a aVar) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(aVar.b());
        hiAggregateOption.setEndTime(aVar.a());
        hiAggregateOption.setAggregateType(1);
        int e = aVar.e();
        String[] b = b(e);
        int[] c = c(e);
        hiAggregateOption.setConstantsKey(b);
        hiAggregateOption.setType(c);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        c(context, aVar.c(), aVar.d(), arrayList);
    }

    private void c(Context context, float f, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiAggregateOption> list) {
        if (responseCallback == null) {
            LogUtil.h("StepModuleChartStorageHelper", "aggregateOptionHealthData callback == null");
        } else {
            HiHealthManager.d(context).aggregateHiHealthDataEx(list, new StepModuleAggregateListener(responseCallback, f) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.1
                @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleAggregateListener
                public void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                    if (sparseArray == null || sparseArray.size() == 0) {
                        LogUtil.h("StepModuleChartStorageHelper", "readStepDataAsDayUnit triggered but the data is null");
                        if (this.mCallback != null) {
                            this.mCallback.onResult(-1, null);
                            return;
                        }
                        return;
                    }
                    List<HiHealthData> list2 = sparseArray.get(0);
                    HashMap hashMap = new HashMap();
                    for (HiHealthData hiHealthData : list2) {
                        if (hiHealthData != null && StepModuleChartStorageHelper.this.d(hiHealthData) != 0.0f) {
                            nnc nncVar = new nnc(hiHealthData.getFloat("sum") * this.mTransferRate);
                            hashMap.put(Long.valueOf(hiHealthData.getStartTime() + 43200000), nncVar);
                            nncVar.put(MotionType.SUM, StepModuleChartStorageHelper.this.d(hiHealthData) * this.mTransferRate);
                            nncVar.put(MotionType.WALK, StepModuleChartStorageHelper.this.c(hiHealthData) * this.mTransferRate);
                            nncVar.put(MotionType.RUN, StepModuleChartStorageHelper.this.a(hiHealthData) * this.mTransferRate);
                            nncVar.put(MotionType.BIKE, StepModuleChartStorageHelper.this.e(hiHealthData) * this.mTransferRate);
                            nncVar.put(MotionType.CLIMB, StepModuleChartStorageHelper.this.b(hiHealthData) * this.mTransferRate);
                            float f2 = hiHealthData.getFloat("sum");
                            float f3 = this.mTransferRate;
                            float motionType = nncVar.getMotionType(MotionType.WALK) + nncVar.getMotionType(MotionType.RUN) + nncVar.getMotionType(MotionType.BIKE) + nncVar.getMotionType(MotionType.CLIMB);
                            if (f2 * f3 < motionType) {
                                nncVar.put(MotionType.SUM, motionType);
                            }
                        }
                    }
                    this.mCallback.onResult(0, hashMap);
                }
            });
        }
    }

    private void b(Context context, a aVar) {
        ResponseCallback<Map<Long, IStorageModel>> d2 = aVar.d();
        if (d2 == null) {
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long b = aVar.b();
        hiAggregateOption.setStartTime(b);
        long a2 = aVar.a();
        hiAggregateOption.setEndTime(a2);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setConstantsKey(new String[]{"content"});
        int e = aVar.e();
        hiAggregateOption.setType(new int[]{e});
        hiAggregateOption.setGroupUnitType(5);
        hiAggregateOption.setReadType(0);
        LogUtil.a("StepModuleChartStorageHelper", "getFitnessDataDetail aggregateOption=", hiAggregateOption.toString());
        HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
        hiAggregateOption2.setStartTime(b);
        hiAggregateOption2.setEndTime(a2);
        hiAggregateOption2.setAggregateType(1);
        String[] b2 = b(e);
        int[] c = c(e);
        hiAggregateOption2.setConstantsKey(b2);
        hiAggregateOption2.setType(c);
        hiAggregateOption2.setGroupUnitType(5);
        hiAggregateOption2.setReadType(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        arrayList.add(hiAggregateOption2);
        b(context, aVar.c(), d2, arrayList);
    }

    private void b(Context context, float f, ResponseCallback<Map<Long, IStorageModel>> responseCallback, List<HiAggregateOption> list) {
        HiHealthManager.d(context).aggregateHiHealthDataEx(list, new StepModuleAggregateListener(responseCallback, f) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.2
            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleAggregateListener
            public void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                if (sparseArray == null || sparseArray.size() == 0) {
                    LogUtil.h("StepModuleChartStorageHelper", "triggered but the data is null");
                    if (this.mCallback != null) {
                        this.mCallback.onResult(-1, null);
                        return;
                    }
                    return;
                }
                List<HiHealthData> list2 = sparseArray.get(0);
                List<HiHealthData> list3 = sparseArray.get(1);
                HashMap hashMap = new HashMap();
                if (list2 == null || list3 == null) {
                    return;
                }
                HashMap hashMap2 = new HashMap();
                for (HiHealthData hiHealthData : list3) {
                    String l = Long.toString(hiHealthData.getDay());
                    if (l.length() >= 7) {
                        hashMap2.put(l.substring(0, 7), hiHealthData);
                    }
                }
                try {
                    StepModuleChartStorageHelper.this.c(hashMap, list2, list3, hashMap2, this.mTransferRate, 1296000000L);
                } catch (Exception e) {
                    LogUtil.b("StepModuleChartStorageHelper", LogAnonymous.b((Throwable) e));
                }
                this.mCallback.onResult(0, hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Map<Long, IStorageModel> map, List<HiHealthData> list, List<HiHealthData> list2, Map<String, HiHealthData> map2, float f, long j) {
        HiHealthData hiHealthData;
        HiHealthData hiHealthData2;
        nne nneVar;
        HiHealthData hiHealthData3 = null;
        int i = 0;
        while (i < list.size()) {
            if (list.size() != list2.size()) {
                hiHealthData = list.get(i);
                String l = Long.toString(hiHealthData.getDay());
                if (l.length() >= 7) {
                    hiHealthData3 = map2.get(l.substring(0, 7));
                }
                hiHealthData2 = hiHealthData3;
            } else {
                hiHealthData = list.get(i);
                hiHealthData2 = list2.get(i);
            }
            if (hiHealthData2 != null && d(hiHealthData2) != 0.0f) {
                long startTime = hiHealthData.getStartTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(startTime);
                calendar.set(5, 1);
                calendar.set(11, 0);
                calendar.set(12, 0);
                float f2 = hiHealthData.getFloat("content") * f;
                float f3 = hiHealthData2.getFloat("sum") * f;
                if (Math.abs(f2) < 1.0E-4d) {
                    nneVar = new nne(f2, f3, 0);
                } else {
                    nneVar = new nne(f2, f3, Math.round(f3 / f2));
                }
                map.put(Long.valueOf(calendar.getTimeInMillis() + j), nneVar);
                nneVar.put(MotionType.SUM, d(hiHealthData2) * f);
                nneVar.put(MotionType.WALK, c(hiHealthData2) * f);
                nneVar.put(MotionType.RUN, a(hiHealthData2) * f);
                nneVar.put(MotionType.BIKE, e(hiHealthData2) * f);
                nneVar.put(MotionType.CLIMB, b(hiHealthData2) * f);
                float motionType = nneVar.getMotionType(MotionType.WALK) + nneVar.getMotionType(MotionType.RUN) + nneVar.getMotionType(MotionType.BIKE) + nneVar.getMotionType(MotionType.CLIMB);
                if (f3 < motionType) {
                    nneVar.put(MotionType.SUM, motionType);
                }
            }
            i++;
            hiHealthData3 = hiHealthData2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float d(HiHealthData hiHealthData) {
        return hiHealthData.getFloat("sum");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float c(HiHealthData hiHealthData) {
        return hiHealthData.getFloat("walk");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float a(HiHealthData hiHealthData) {
        return hiHealthData.getFloat("run");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float e(HiHealthData hiHealthData) {
        return hiHealthData.getFloat("bike");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(HiHealthData hiHealthData) {
        return hiHealthData.getFloat("climb");
    }

    private String[] b(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("sum");
        if (e(i, MotionType.WALK)) {
            arrayList.add("walk");
        }
        if (e(i, MotionType.RUN)) {
            arrayList.add("run");
        }
        if (e(i, MotionType.BIKE)) {
            arrayList.add("bike");
        }
        if (e(i, MotionType.CLIMB)) {
            arrayList.add("climb");
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private int[] c(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(b(i, MotionType.SUM)));
        if (e(i, MotionType.WALK)) {
            arrayList.add(Integer.valueOf(b(i, MotionType.WALK)));
        }
        if (e(i, MotionType.RUN)) {
            arrayList.add(Integer.valueOf(b(i, MotionType.RUN)));
        }
        if (e(i, MotionType.BIKE)) {
            arrayList.add(Integer.valueOf(b(i, MotionType.BIKE)));
        }
        if (e(i, MotionType.CLIMB)) {
            arrayList.add(Integer.valueOf(b(i, MotionType.CLIMB)));
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr;
    }

    private boolean e(int i, MotionType motionType) {
        return i == 40002 ? motionType == MotionType.SUM || motionType == MotionType.WALK || motionType == MotionType.RUN || motionType == MotionType.CLIMB : i == 40004 ? motionType == MotionType.SUM || motionType == MotionType.WALK || motionType == MotionType.RUN || motionType == MotionType.BIKE || motionType == MotionType.CLIMB : i == 40003 && (motionType == MotionType.SUM || motionType == MotionType.WALK || motionType == MotionType.RUN || motionType == MotionType.BIKE || motionType == MotionType.CLIMB);
    }

    private int b(int i, MotionType motionType) {
        if (i == 40002) {
            if (motionType == MotionType.SUM) {
                return 40002;
            }
            if (motionType == MotionType.WALK) {
                return 40011;
            }
            if (motionType == MotionType.RUN) {
                return 40012;
            }
            if (motionType == MotionType.CLIMB) {
                return 40013;
            }
            throw new RuntimeException("only support walk,run,climb in steps");
        }
        if (i == 40004) {
            if (motionType == MotionType.SUM) {
                return 40004;
            }
            if (motionType == MotionType.WALK) {
                return 40031;
            }
            if (motionType == MotionType.RUN) {
                return 40032;
            }
            if (motionType == MotionType.BIKE) {
                return 40033;
            }
            if (motionType == MotionType.CLIMB) {
                return 40034;
            }
            throw new RuntimeException("only support walk,run,bike,climb in distance");
        }
        if (i == 40003) {
            if (motionType == MotionType.SUM) {
                return 40003;
            }
            if (motionType == MotionType.WALK) {
                return 40021;
            }
            if (motionType == MotionType.RUN) {
                return 40022;
            }
            if (motionType == MotionType.BIKE) {
                return 40023;
            }
            if (motionType == MotionType.CLIMB) {
                return 40024;
            }
            throw new RuntimeException("only support walk,run,bike,climb in calories");
        }
        if (i == 40005 && motionType == MotionType.SUM) {
            return SmartMsgConstant.MSG_TYPE_RIDE_USER;
        }
        throw new RuntimeException("only support steps,distance,calories");
    }

    private void duN_(a aVar, SparseArray<List<HiHealthData>> sparseArray, String str) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long b = aVar.b();
        hiAggregateOption.setStartTime(b);
        long a2 = aVar.a();
        hiAggregateOption.setEndTime(a2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(new String[]{"content"});
        int e = aVar.e();
        hiAggregateOption.setType(new int[]{e});
        hiAggregateOption.setAlignType(20001);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setReadType(0);
        HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
        hiAggregateOption2.setStartTime(b);
        hiAggregateOption2.setEndTime(a2);
        hiAggregateOption2.setAggregateType(1);
        hiAggregateOption2.setConstantsKey(new String[]{"sum"});
        hiAggregateOption2.setType(new int[]{d(e)});
        hiAggregateOption2.setGroupUnitType(3);
        hiAggregateOption2.setReadType(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        arrayList.add(hiAggregateOption2);
        duP_(aVar, arrayList, sparseArray, str);
    }

    private void duP_(final a aVar, List<HiAggregateOption> list, final SparseArray<List<HiHealthData>> sparseArray, final String str) {
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataEx(list, new StepModuleAggregateListener(aVar.d(), aVar.c()) { // from class: com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.7
            @Override // com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper.StepModuleAggregateListener
            public void onResultData(SparseArray<List<HiHealthData>> sparseArray2, int i, int i2) {
                ReleaseLogUtil.e("StepModuleChartStorageHelper", "query step day data success: ", Long.valueOf(System.currentTimeMillis() - StepModuleChartStorageHelper.this.i), "ms");
                if (sparseArray2 == null || sparseArray2.size() == 0) {
                    LogUtil.h("StepModuleChartStorageHelper", "triggered but the data is null");
                    if (this.mCallback != null) {
                        this.mCallback.onResult(-1, null);
                        return;
                    }
                    return;
                }
                if (koq.b(sparseArray2.get(1))) {
                    LogUtil.h("StepModuleChartStorageHelper", "step stat list is empty");
                    if (this.mCallback != null) {
                        this.mCallback.onResult(-1, null);
                        return;
                    }
                    return;
                }
                if (aVar.e() == 2 && StepModuleChartStorageHelper.d) {
                    boolean unused = StepModuleChartStorageHelper.d = false;
                    if (pxc.duK_(sparseArray, sparseArray2)) {
                        LogUtil.a("StepModuleChartStorageHelper", "query step day data and cache data is same");
                        return;
                    }
                    pxc.duL_(str, sparseArray2);
                }
                StepModuleChartStorageHelper.this.duO_(sparseArray2, this.mTransferRate, this.mCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void duO_(SparseArray<List<HiHealthData>> sparseArray, float f, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        HiHealthData hiHealthData;
        nnc nncVar;
        float f2;
        Map<Long, IStorageModel> hashMap = new HashMap<>();
        List<HiHealthData> list = sparseArray.get(0);
        if (list == null) {
            list = new ArrayList<>();
        }
        ReleaseLogUtil.e("StepModuleChartStorageHelper", "parseStepDayData stepList size is ", Integer.valueOf(list.size()));
        float f3 = 0.0f;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null && hiHealthData2.getFloat("content") != 0.0f) {
                Calendar a2 = a(hiHealthData2.getStartTime());
                IStorageModel iStorageModel = hashMap.get(Long.valueOf(a2.getTimeInMillis()));
                if (iStorageModel instanceof nnc) {
                    nnc nncVar2 = (nnc) iStorageModel;
                    nncVar = nncVar2;
                    f2 = nncVar2.b();
                } else {
                    nncVar = new nnc(0.0f);
                    f2 = 0.0f;
                }
                f3 = b(hashMap, f3, hiHealthData2, a2, nncVar, f2, f);
            }
        }
        List<HiHealthData> list2 = sparseArray.get(1);
        if (koq.c(list2) && (hiHealthData = list2.get(0)) != null) {
            nnc d2 = d(hashMap, hiHealthData);
            float f4 = (hiHealthData.getFloat("sum") * f) - f3;
            if (f4 >= 0.0f) {
                d2.put(MotionType.SUM, d2.getMotionType(MotionType.SUM) + f4);
                hashMap.put(Long.valueOf(jdl.e(hiHealthData.getStartTime())), d2);
            }
        }
        if (responseCallback != null) {
            responseCallback.onResult(0, b(hashMap));
        }
    }

    private Map<Long, IStorageModel> b(Map<Long, IStorageModel> map) {
        if (map == null || map.isEmpty()) {
            return new HashMap();
        }
        TreeMap treeMap = new TreeMap(new Comparator() { // from class: pxg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return StepModuleChartStorageHelper.e((Long) obj, (Long) obj2);
            }
        });
        treeMap.putAll(map);
        return treeMap;
    }

    public static /* synthetic */ int e(Long l, Long l2) {
        return (int) (l.longValue() - l2.longValue());
    }

    private nnc d(Map<Long, IStorageModel> map, HiHealthData hiHealthData) {
        IStorageModel iStorageModel = map.get(Long.valueOf(jdl.t(hiHealthData.getStartTime())));
        if (iStorageModel instanceof nnc) {
            return (nnc) iStorageModel;
        }
        LogUtil.h("StepModuleChartStorageHelper", "!storageModel instanceof BarChartDataStorageModel");
        return new nnc(0.0f);
    }

    private float b(Map<Long, IStorageModel> map, float f, HiHealthData hiHealthData, Calendar calendar, nnc nncVar, float f2, float f3) {
        nncVar.c(f2 + (hiHealthData.getFloat("content") * f3));
        float f4 = hiHealthData.getFloat("content");
        map.put(Long.valueOf(calendar.getTimeInMillis()), nncVar);
        int type = hiHealthData.getType();
        nncVar.put(MotionType.SUM, nncVar.b());
        if (type == 20002) {
            nncVar.put(MotionType.WALK, nncVar.getMotionType(MotionType.WALK) + (hiHealthData.getFloat("content") * f3));
        } else if (type == 20003) {
            nncVar.put(MotionType.RUN, nncVar.getMotionType(MotionType.RUN) + (hiHealthData.getFloat("content") * f3));
        } else if (type == 20005) {
            nncVar.put(MotionType.BIKE, nncVar.getMotionType(MotionType.BIKE) + (hiHealthData.getFloat("content") * f3));
        } else if (type == 20004) {
            nncVar.put(MotionType.CLIMB, nncVar.getMotionType(MotionType.CLIMB) + (hiHealthData.getFloat("content") * f3));
        }
        return f + (f4 * f3);
    }

    private Calendar a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(12) / 30 == 0) {
            calendar.set(12, 14);
            calendar.set(13, 30);
        } else {
            calendar.set(12, 44);
            calendar.set(13, 30);
        }
        return calendar;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f9916a;
        private ResponseCallback<Map<Long, IStorageModel>> b;
        private long c;
        private float d;
        private long e;

        a() {
        }

        public long b() {
            return this.e;
        }

        public void c(long j) {
            this.e = j;
        }

        public long a() {
            return this.c;
        }

        public void e(long j) {
            this.c = j;
        }

        public int e() {
            return this.f9916a;
        }

        public void d(int i) {
            this.f9916a = i;
        }

        public float c() {
            return this.d;
        }

        public void b(float f) {
            this.d = f;
        }

        public ResponseCallback<Map<Long, IStorageModel>> d() {
            return this.b;
        }

        public void a(ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.b = responseCallback;
        }
    }

    public static abstract class StepModuleAggregateListener implements HiAggregateListenerEx {
        protected ResponseCallback<Map<Long, IStorageModel>> mCallback;
        protected float mTransferRate;

        public abstract void onResultData(SparseArray<List<HiHealthData>> sparseArray, int i, int i2);

        public StepModuleAggregateListener(ResponseCallback<Map<Long, IStorageModel>> responseCallback, float f) {
            this.mCallback = responseCallback;
            this.mTransferRate = f;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
        public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
            onResultData(sparseArray, i, i2);
            LogUtil.h("StepModuleChartStorageHelper", "StepModuleAggregateListener onResult ANCHOR", Integer.valueOf(i2));
            if (i2 == 2 || i == 1) {
                this.mCallback = null;
            }
        }
    }

    public static abstract class StepModuleReadListener implements HiDataReadResultListener {
        protected ResponseCallback<Map<Long, IStorageModel>> mCallback;
        protected int mDataType;

        public abstract void onResultData(Object obj, int i, int i2);

        public abstract void onResultIntentData(int i, Object obj, int i2, int i3);

        protected StepModuleReadListener(ResponseCallback<Map<Long, IStorageModel>> responseCallback, int i) {
            this.mCallback = responseCallback;
            this.mDataType = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.h("StepModuleChartStorageHelper", "StepModuleReadListener onResult", "anchor", Integer.valueOf(i2));
            onResultData(obj, i, i2);
            if (i2 == 2 || i == 1) {
                this.mCallback = null;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.h("StepModuleChartStorageHelper", "StepModuleReadListener onResultIntent", "anchor", Integer.valueOf(i3));
            onResultIntentData(i, obj, i2, i3);
            if (i3 == 2 || i2 == 1) {
                this.mCallback = null;
            }
        }
    }

    public static void e(boolean z) {
        d = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, IStorageModel> b(List<HiHealthData> list, long j) {
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                hashMap.put(Long.valueOf(a(hiHealthData.getStartTime()).getTimeInMillis()), new nnc(4.0f, 0.0f, this.e, this.c));
            }
        }
        if (jdl.ac(j)) {
            a(hashMap);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Long, IStorageModel> a() {
        HashMap hashMap = new HashMap(16);
        List<Long> e = e();
        long timeInMillis = a(jdl.r(System.currentTimeMillis())).getTimeInMillis();
        Iterator<Long> it = e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            long longValue = it.next().longValue();
            if (longValue == timeInMillis) {
                hashMap.put(Long.valueOf(longValue), new nnc(3.999f, 0.0f, this.b, this.f9913a));
                break;
            }
            hashMap.put(Long.valueOf(longValue), new nnc(3.999f, 0.0f, this.g, this.f));
        }
        return hashMap;
    }

    private void a(Map<Long, IStorageModel> map) {
        List<Long> e = e();
        long timeInMillis = a(jdl.r(System.currentTimeMillis())).getTimeInMillis();
        if (!map.containsKey(Long.valueOf(timeInMillis))) {
            map.put(Long.valueOf(timeInMillis), new nnc(3.999f, 0.0f, this.b, this.f9913a));
        }
        Iterator<Long> it = e.iterator();
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            if (!map.containsKey(Long.valueOf(longValue))) {
                map.put(Long.valueOf(longValue), new nnc(3.999f, 0.0f, this.g, this.f));
            }
        }
    }

    private List<Long> e() {
        ArrayList arrayList = new ArrayList();
        int c = CardConstants.c(System.currentTimeMillis());
        for (int i = 0; i <= c; i++) {
            arrayList.add(Long.valueOf(a(i)));
        }
        return arrayList;
    }

    private long a(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return a(calendar.getTimeInMillis()).getTimeInMillis();
    }

    private List<HiAggregateOption> e(int i, long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setConstantsKey(new String[]{"content"});
        hiAggregateOption.setType(new int[]{i});
        hiAggregateOption.setGroupUnitType(5);
        hiAggregateOption.setReadType(0);
        LogUtil.a("StepModuleChartStorageHelper", "getFitnessDataDetail aggregateOption=", hiAggregateOption.toString());
        HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
        hiAggregateOption2.setStartTime(j);
        hiAggregateOption2.setEndTime(j2);
        hiAggregateOption2.setAggregateType(1);
        hiAggregateOption2.setConstantsKey(new String[]{"sum"});
        hiAggregateOption2.setType(new int[]{i});
        hiAggregateOption2.setGroupUnitType(5);
        hiAggregateOption2.setReadType(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hiAggregateOption);
        arrayList.add(hiAggregateOption2);
        return arrayList;
    }
}
