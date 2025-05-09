package com.huawei.ui.main.stories.fitness.util.chart;

import android.content.Context;
import android.graphics.Color;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper;
import com.huawei.ui.main.stories.fitness.views.base.chart.ICustomMotionTypeCalculator;
import defpackage.nip;
import defpackage.nnc;
import defpackage.nne;
import defpackage.nnz;
import defpackage.noy;
import defpackage.nrn;
import defpackage.pyh;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class StepModuleBarChartHolder extends HwHealthBarScrollChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private IChartStorageHelper f9921a;
    private ICustomCalculator b;
    private long c;
    private Map<DataInfos, HwHealthBarDataSet> d;
    private ICustomMotionTypeCalculator e;
    private ICustomCalculator i;

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 0.0f;
    }

    public StepModuleBarChartHolder(Context context) {
        super(context);
        this.d = new HashMap();
        this.f9921a = new StepModuleChartStorageHelper();
        this.b = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.3
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                HwHealthBarDataSet hwHealthBarDataSet = (HwHealthBarDataSet) StepModuleBarChartHolder.this.d.get(dataInfos);
                if (hwHealthBarDataSet == null) {
                    throw new RuntimeException("mAvgCalculator can't find dataSet");
                }
                return hwHealthBarDataSet.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.3.5
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        HwHealthBaseEntry hwHealthBaseEntry = list.get(0);
                        if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
                            if (hwHealthBarEntry.acquireModel() instanceof nnc) {
                                return StepModuleBarChartHolder.this.e(list, (nnc) hwHealthBarEntry.acquireModel());
                            }
                        }
                        throw new RuntimeException("mAvgCalculator not instanceof HwHealthBarEntry! logic error!!!");
                    }
                });
            }
        };
        this.i = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.4
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                HwHealthBarDataSet hwHealthBarDataSet = (HwHealthBarDataSet) StepModuleBarChartHolder.this.d.get(dataInfos);
                if (hwHealthBarDataSet == null) {
                    throw new RuntimeException("calculateSum not find dataSet! logic error!!!");
                }
                return hwHealthBarDataSet.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.4.3
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        float f = 0.0f;
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        HwHealthBaseEntry hwHealthBaseEntry = list.get(0);
                        if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
                            if (hwHealthBarEntry.acquireModel() instanceof nnc) {
                                if (!(((nnc) hwHealthBarEntry.acquireModel()) instanceof nne)) {
                                    for (HwHealthBaseEntry hwHealthBaseEntry2 : list) {
                                        if (hwHealthBaseEntry2 != null) {
                                            f += noy.c(((HwHealthBarEntry) hwHealthBaseEntry2).acquireModel());
                                        }
                                    }
                                    return f;
                                }
                                for (HwHealthBaseEntry hwHealthBaseEntry3 : list) {
                                    if (hwHealthBaseEntry3 != null) {
                                        f += ((nne) ((HwHealthBarEntry) hwHealthBaseEntry3).acquireModel()).d();
                                    }
                                }
                                return f;
                            }
                        }
                        throw new RuntimeException("calculateSum not instanceof HwHealthBarEntry! logic error!!!");
                    }
                });
            }
        };
        this.e = new ICustomMotionTypeCalculator() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.2
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ICustomMotionTypeCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos, final MotionType motionType) {
                HwHealthBarDataSet hwHealthBarDataSet = (HwHealthBarDataSet) StepModuleBarChartHolder.this.d.get(dataInfos);
                if (hwHealthBarDataSet == null) {
                    throw new RuntimeException("mMotionTypeCalculator can't find dataSet");
                }
                return hwHealthBarDataSet.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.2.3
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        float f = 0.0f;
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("mMotionTypeCalculator not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                            if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
                                if (hwHealthBarEntry.acquireModel() instanceof nnz) {
                                    f += ((nnz) hwHealthBarEntry.acquireModel()).getMotionType(motionType);
                                }
                            }
                        }
                        return f;
                    }
                });
            }
        };
    }

    public void d(long j) {
        this.c = j;
    }

    public long d() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return pyh.c((int) Math.ceil(f), 5);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.f9921a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
        if (dataInfos == null) {
            LogUtil.h("StepModuleBarChartHolder", "onCustomChartStyle dataType == null");
            return;
        }
        if (dataInfos.isStepData() && !dataInfos.isDayData()) {
            nip.d("900200006", new a(this, hwHealthBarChart, "900200006"));
            return;
        }
        if (dataInfos.isActiveHoursData() && dataInfos.isDayData()) {
            HwHealthYAxis axisFirstParty = hwHealthBarChart.getAxisFirstParty();
            if (axisFirstParty == null) {
                LogUtil.h("StepModuleBarChartHolder", "onCustomChartStyle axisFirstParty == null");
                return;
            } else {
                axisFirstParty.setDrawLabels(false);
                axisFirstParty.setDrawGridLines(true);
                return;
            }
        }
        if (!dataInfos.isTimeStrengthData() || dataInfos.isDayData()) {
            return;
        }
        nip.d("900200008", new a(this, hwHealthBarChart, "900200008"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final HwHealthBarChart hwHealthBarChart, final int i, final int i2) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder.5
                @Override // java.lang.Runnable
                public void run() {
                    StepModuleBarChartHolder.this.c(hwHealthBarChart, i, i2);
                }
            });
            return;
        }
        hwHealthBarChart.enableManualReferenceLine(i, i2);
        hwHealthBarChart.reCalculateDynamicBoardForManualRefLine();
        hwHealthBarChart.refresh();
    }

    public ICustomCalculator b() {
        return this.i;
    }

    public ICustomCalculator a() {
        return this.b;
    }

    public ICustomMotionTypeCalculator c() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        HwHealthBarDataSet onCreateDataSet = super.onCreateDataSet(hwHealthBarChart, dataInfos, bVar);
        this.d.put(dataInfos, onCreateDataSet);
        if (dataInfos.isStepData()) {
            onCreateDataSet.setColor(nrn.d(R.color._2131299133_res_0x7f090b3d));
            onCreateDataSet.e(nrn.d(R.color._2131299134_res_0x7f090b3e));
        } else if (dataInfos.isDistanceData()) {
            onCreateDataSet.setColor(Color.argb(255, MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT, 229, MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT));
            onCreateDataSet.e(Color.argb(255, 71, 204, 71));
        } else if (dataInfos.isCaloriesData()) {
            onCreateDataSet.setColor(Color.argb(255, 249, OldToNewMotionPath.SPORT_TYPE_AEROBICS, 121));
            onCreateDataSet.e(Color.argb(255, 245, 62, 31));
        } else if (dataInfos.isClimbData()) {
            onCreateDataSet.setColor(Color.argb(255, 127, 223, 228));
            onCreateDataSet.e(Color.argb(255, 0, 191, 201));
        } else if (dataInfos.isTimeStrengthData()) {
            onCreateDataSet.setColor(Color.argb(255, 255, 205, 112));
            onCreateDataSet.e(Color.argb(255, 255, 171, 17));
        } else if (dataInfos.isActiveHoursData()) {
            onCreateDataSet.setColor(nrn.d(R.color._2131296438_res_0x7f0900b6));
            onCreateDataSet.e(nrn.d(R.color._2131296445_res_0x7f0900bd));
            if (dataInfos.isDayData()) {
                onCreateDataSet.setBarDrawWidth(25.0f);
                onCreateDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
            }
        }
        return onCreateDataSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float e(List<? extends HwHealthBaseEntry> list, nnc nncVar) {
        float f;
        int i = 0;
        if (!(nncVar instanceof nne)) {
            f = 0.0f;
            for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                if (hwHealthBaseEntry != null) {
                    f += noy.c(((HwHealthBarEntry) hwHealthBaseEntry).acquireModel());
                    i++;
                }
            }
            if (i == 0) {
                LogUtil.h("StepModuleBarChartHolder", "Division by zero attempted!");
                return 0.0f;
            }
        } else {
            f = 0.0f;
            for (HwHealthBaseEntry hwHealthBaseEntry2 : list) {
                if (hwHealthBaseEntry2 instanceof HwHealthBarEntry) {
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry2;
                    if (hwHealthBarEntry.acquireModel() instanceof nne) {
                        nne nneVar = (nne) hwHealthBarEntry.acquireModel();
                        f += nneVar.d();
                        i += nneVar.c();
                    }
                }
            }
            if (i == 0) {
                LogUtil.h("StepModuleBarChartHolder", "Division by zero attempted!");
                return 0.0f;
            }
        }
        return f / i;
    }

    public static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private String f9926a;
        private WeakReference<StepModuleBarChartHolder> b;
        private WeakReference<HwHealthBarChart> d;

        public a(StepModuleBarChartHolder stepModuleBarChartHolder, HwHealthBarChart hwHealthBarChart, String str) {
            this.b = new WeakReference<>(stepModuleBarChartHolder);
            this.d = new WeakReference<>(hwHealthBarChart);
            this.f9926a = str;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof Integer)) {
                LogUtil.h("StepModuleBarChartHolder", "!CollectionUtils.isListTypeMatch(data, HiGoalInfo.class)");
                return;
            }
            int intValue = ((Integer) obj).intValue();
            WeakReference<HwHealthBarChart> weakReference = this.d;
            if (weakReference == null || this.b == null) {
                LogUtil.h("StepModuleBarChartHolder", "mChart or mChartHolder == null");
                return;
            }
            HwHealthBarChart hwHealthBarChart = weakReference.get();
            StepModuleBarChartHolder stepModuleBarChartHolder = this.b.get();
            if (hwHealthBarChart == null || stepModuleBarChartHolder == null) {
                LogUtil.h("StepModuleBarChartHolder", "mChart.get() == null");
            } else if ("900200006".equals(this.f9926a)) {
                stepModuleBarChartHolder.c(hwHealthBarChart, intValue, nrn.d(R.color._2131299141_res_0x7f090b45));
            } else if ("900200008".equals(this.f9926a)) {
                stepModuleBarChartHolder.c(hwHealthBarChart, intValue, nrn.d(R.color._2131296453_res_0x7f0900c5));
            }
        }
    }
}
