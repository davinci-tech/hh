package com.huawei.ui.main.stories.fitness.activity.heartrate.chart;

import android.content.Context;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthCombinedScrollChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor;
import defpackage.noy;
import defpackage.psl;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class HeartRateBarChartHolder extends HwHealthCombinedScrollChartHolder implements HighlightedEntryParser, IScrollChartVisitor {

    /* renamed from: a, reason: collision with root package name */
    private IChartStorageHelper f9849a;
    private ICustomCalculator b;
    private Map<DataInfos, HwHealthBaseBarLineDataSet> c;
    private ICustomCalculator d;
    private ICustomCalculator e;
    private Map<DataInfos, HwHealthBaseBarLineDataSet> f;
    private ICustomCalculator g;
    private ICustomCalculator h;
    private Map<DataInfos, HwHealthBaseBarLineDataSet> i;
    private Map<DataInfos, HwHealthBaseBarLineDataSet> j;
    private ICustomCalculator l;
    private ICustomCalculator o;

    public HeartRateBarChartHolder(Context context) {
        super(context);
        this.j = new HashMap(16);
        this.f = new HashMap(16);
        this.i = new HashMap(16);
        this.c = new HashMap(16);
        this.f9849a = new psl();
        this.d = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.4
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.f == null || HeartRateBarChartHolder.this.f.get(dataInfos) == null) {
                    throw new RuntimeException("mAvgCalculator can't find dataSet");
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.f.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.4.3
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list != null && list.size() != 0) {
                            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                            int i = 0;
                            float f = 0.0f;
                            while (it.hasNext()) {
                                f += it.next().getY();
                                i++;
                            }
                            if (i > 0) {
                                return f / i;
                            }
                        }
                        return 0.0f;
                    }
                });
            }
        };
        this.h = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.2
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.j == null || HeartRateBarChartHolder.this.j.get(dataInfos) == null) {
                    throw new RuntimeException("calculateMax can't find dataSet");
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.j.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.2.4
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMax not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MIN_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (noy.c(acquireModel) > f) {
                                f = noy.c(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
        this.g = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.5
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.j == null || HeartRateBarChartHolder.this.j.get(dataInfos) == null) {
                    throw new RuntimeException("calculateMin not find dataSet! logic error!!!");
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.j.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.5.1
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMin not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MAX_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (f > noy.a(acquireModel)) {
                                f = noy.a(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
        this.o = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.1
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.i == null || HeartRateBarChartHolder.this.i.get(dataInfos) == null) {
                    return 0.0f;
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.i.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.1.1
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMax not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MIN_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (noy.c(acquireModel) > f) {
                                f = noy.c(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
        this.l = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.3
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.i == null || HeartRateBarChartHolder.this.i.get(dataInfos) == null) {
                    return 0.0f;
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.i.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.3.4
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMin not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MAX_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (f > noy.a(acquireModel)) {
                                f = noy.a(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
        this.b = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.9
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.c == null || HeartRateBarChartHolder.this.c.get(dataInfos) == null) {
                    return 0.0f;
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.c.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.9.3
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMax not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MIN_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (noy.c(acquireModel) > f) {
                                f = noy.c(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
        this.e = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.10
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateBarChartHolder.this.c == null || HeartRateBarChartHolder.this.c.get(dataInfos) == null) {
                    return 0.0f;
                }
                return ((HwHealthBaseBarLineDataSet) HeartRateBarChartHolder.this.c.get(dataInfos)).calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateBarChartHolder.10.5
                    @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                    public float calculate(List<? extends HwHealthBaseEntry> list) {
                        if (list == null || list.size() == 0) {
                            return 0.0f;
                        }
                        if (!(list.get(0) instanceof HwHealthBarEntry)) {
                            throw new RuntimeException("calculateMin not instanceof HwHealthBarEntry! logic error!!!");
                        }
                        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                        float f = Float.MAX_VALUE;
                        while (it.hasNext()) {
                            IStorageModel acquireModel = ((HwHealthBarEntry) it.next()).acquireModel();
                            if (f > noy.a(acquireModel)) {
                                f = noy.a(acquireModel);
                            }
                        }
                        return f;
                    }
                });
            }
        };
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        LogUtil.c("HeartRateBarChartHolder", "computeDynamicBorderMax maxValue = ", Float.valueOf(f), "minValue = ", Float.valueOf(f2));
        if (this.f.containsValue(hwHealthBaseBarLineDataSet)) {
            if (f <= 80.0f) {
                return 80.0f;
            }
            float f3 = f - f2;
            float f4 = (f3 / 2.0f) + f2;
            float f5 = (f3 * 1.2f) / 2.0f;
            float f6 = (int) (f4 + f5);
            float f7 = (int) (f4 - f5);
            if (f > f2 && f6 > f7 && f6 <= 220.0f && f6 >= 40.0f) {
                return f6;
            }
        }
        return 220.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        LogUtil.c("HeartRateBarChartHolder", "computeDynamicBorderMin maxValue = ", Float.valueOf(f), "minValue = ", Float.valueOf(f2));
        if (this.f.containsValue(hwHealthBaseBarLineDataSet) && f2 >= 40.0f && f > 80.0f) {
            float f3 = f - f2;
            float f4 = (f3 / 2.0f) + f2;
            float f5 = (f3 * 1.2f) / 2.0f;
            float f6 = (int) (f4 - f5);
            float f7 = (int) (f4 + f5);
            if (f > f2 && f7 > f6 && f6 >= 40.0f && f6 <= 220.0f) {
                return f6;
            }
        }
        return 40.0f;
    }

    private void c(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthChartHolder.b bVar) {
        if (HwHealthChartHolder.LAYER_ID_NORMAL_HR.equals(bVar.e())) {
            this.j.put(bVar.d(), hwHealthBaseBarLineDataSet);
            return;
        }
        if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
            this.f.put(bVar.d(), hwHealthBaseBarLineDataSet);
            return;
        }
        if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
            this.i.put(bVar.d(), hwHealthBaseBarLineDataSet);
        } else if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
            this.c.put(bVar.d(), hwHealthBaseBarLineDataSet);
        } else {
            LogUtil.a("HeartRateBarChartHolder", "recordDataSet is null");
        }
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.f9849a;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthCombinedScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onCreateDataSet(HwHealthCombinedChart hwHealthCombinedChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onCreateDataSet = super.onCreateDataSet(hwHealthCombinedChart, dataInfos, bVar);
        c(onCreateDataSet, bVar);
        return onCreateDataSet;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthCombinedScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onFakeDataSet(HwHealthChartHolder.b bVar) {
        HwHealthBaseBarLineDataSet<? extends HwHealthBaseEntry> onFakeDataSet = super.onFakeDataSet(bVar);
        c(onFakeDataSet, bVar);
        return onFakeDataSet;
    }

    public ICustomCalculator c() {
        return this.h;
    }

    public ICustomCalculator d() {
        return this.g;
    }

    public ICustomCalculator e() {
        return this.d;
    }

    public ICustomCalculator f() {
        return this.o;
    }

    public ICustomCalculator h() {
        return this.l;
    }

    public ICustomCalculator b() {
        return this.b;
    }

    public ICustomCalculator a() {
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser
    public String parse(HwHealthBaseEntry hwHealthBaseEntry) {
        if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
            float a2 = noy.a(hwHealthBarEntry.acquireModel());
            float c = noy.c(hwHealthBarEntry.acquireModel());
            return UnitUtil.e(a2, 1, 0) + " - " + UnitUtil.e(c, 1, 0);
        }
        return UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor
    public void visitShowModels(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, String str, DataInfos dataInfos, LogicalUnit logicalUnit) {
        HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet;
        if (str.equals(HwHealthChartHolder.HIGH_WARN)) {
            Map<DataInfos, HwHealthBaseBarLineDataSet> map = this.i;
            if (map == null || map.get(dataInfos) == null) {
                return;
            } else {
                hwHealthBaseBarLineDataSet = this.i.get(dataInfos);
            }
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "mBradycardiaDataSetMap is null?";
            objArr[1] = Boolean.valueOf(this.c == null);
            LogUtil.a("HeartRateBarChartHolder", objArr);
            Map<DataInfos, HwHealthBaseBarLineDataSet> map2 = this.c;
            if (map2 == null || map2.get(dataInfos) == null) {
                LogUtil.a("HeartRateBarChartHolder", "mBradycardiaDataSetMap.get(dataInfos) null!!!!");
                return;
            }
            hwHealthBaseBarLineDataSet = this.c.get(dataInfos);
        }
        hwHealthBaseBarLineDataSet.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, logicalUnit);
    }
}
