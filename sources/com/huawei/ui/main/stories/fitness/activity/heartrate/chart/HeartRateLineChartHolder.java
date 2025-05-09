package com.huawei.ui.main.stories.fitness.activity.heartrate.chart;

import android.content.Context;
import android.graphics.Color;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor;
import defpackage.nrn;
import defpackage.psk;
import defpackage.psl;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class HeartRateLineChartHolder extends HwHealthLineScrollChartHolder implements HighlightedEntryParser, IScrollChartVisitor {

    /* renamed from: a, reason: collision with root package name */
    private IChartStorageHelper f9852a;
    private HwHealthBaseBarLineDataSet b;
    private ICustomCalculator c;
    private ICustomCalculator d;
    private ICustomCalculator e;
    private HwHealthBaseBarLineDataSet f;
    private HwHealthBaseBarLineDataSet g;
    private ICustomCalculator h;
    private ICustomCalculator i;
    private ICustomCalculator j;

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 220.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 40.0f;
    }

    public HeartRateLineChartHolder(Context context) {
        super(context);
        this.f9852a = new psl();
        this.j = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.1
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.f == null) {
                    return 0.0f;
                }
                return HeartRateLineChartHolder.this.f.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.1.5
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
        this.d = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.5
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.f == null) {
                    return 0.0f;
                }
                return HeartRateLineChartHolder.this.f.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.5.2
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
        this.h = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.3
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.g != null) {
                    return HeartRateLineChartHolder.this.g.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.3.5
                        @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                        public float calculate(List<? extends HwHealthBaseEntry> list) {
                            if (list == null || list.size() == 0) {
                                return Float.MIN_VALUE;
                            }
                            if (!(list.get(0) instanceof HwHealthLineEntry)) {
                                throw new RuntimeException("calculateMax not instanceof HwHealthLineEntry! logic error!!!");
                            }
                            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                            float f = -3.4028235E38f;
                            while (it.hasNext()) {
                                for (Object obj : ((StorageGenericModel) ((HwHealthLineEntry) it.next()).acquireModel()).e("HR_WARNING_DETAIL")) {
                                    if (obj instanceof psk) {
                                        psk pskVar = (psk) obj;
                                        if (pskVar.e() > f) {
                                            f = pskVar.e();
                                        }
                                    }
                                }
                            }
                            return f;
                        }
                    });
                }
                throw new RuntimeException("calculateMax can't find dataSet");
            }
        };
        this.i = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.2
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.g != null) {
                    return HeartRateLineChartHolder.this.g.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.2.3
                        @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                        public float calculate(List<? extends HwHealthBaseEntry> list) {
                            float f = Float.MAX_VALUE;
                            if (list == null || list.size() == 0) {
                                return Float.MAX_VALUE;
                            }
                            if (!(list.get(0) instanceof HwHealthLineEntry)) {
                                throw new RuntimeException("calculateMin not instanceof HwHealthLineEntry! logic error!!!");
                            }
                            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                            while (it.hasNext()) {
                                for (Object obj : ((StorageGenericModel) ((HwHealthLineEntry) it.next()).acquireModel()).e("HR_WARNING_DETAIL")) {
                                    if (obj instanceof psk) {
                                        psk pskVar = (psk) obj;
                                        if (pskVar.c() < f) {
                                            f = pskVar.c();
                                        }
                                    }
                                }
                            }
                            return f;
                        }
                    });
                }
                throw new RuntimeException("calculateMax can't find dataSet");
            }
        };
        this.e = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.10
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.b != null) {
                    return HeartRateLineChartHolder.this.b.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.10.3
                        @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                        public float calculate(List<? extends HwHealthBaseEntry> list) {
                            if (list == null || list.size() == 0) {
                                return Float.MIN_VALUE;
                            }
                            if (!(list.get(0) instanceof HwHealthLineEntry)) {
                                throw new RuntimeException("calculateMax not instanceof HwHealthLineEntry! logic error!!!");
                            }
                            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                            float f = -3.4028235E38f;
                            while (it.hasNext()) {
                                Iterator<Object> it2 = ((StorageGenericModel) ((HwHealthLineEntry) it.next()).acquireModel()).e("BRADYCARDIA_DETAIL").iterator();
                                while (it2.hasNext()) {
                                    psk pskVar = (psk) it2.next();
                                    if (pskVar.e() > f) {
                                        f = pskVar.e();
                                    }
                                }
                            }
                            return f;
                        }
                    });
                }
                throw new RuntimeException("calculateMax can't find dataSet");
            }
        };
        this.c = new ICustomCalculator() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.7
            @Override // com.huawei.health.knit.api.ICustomCalculator
            public float calculate(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
                if (HeartRateLineChartHolder.this.b != null) {
                    return HeartRateLineChartHolder.this.b.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.7.1
                        @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
                        public float calculate(List<? extends HwHealthBaseEntry> list) {
                            float f = Float.MAX_VALUE;
                            if (list == null || list.size() == 0) {
                                return Float.MAX_VALUE;
                            }
                            if (!(list.get(0) instanceof HwHealthLineEntry)) {
                                throw new RuntimeException("calculateMin not instanceof HwHealthLineEntry! logic error!!!");
                            }
                            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
                            while (it.hasNext()) {
                                Iterator<Object> it2 = ((StorageGenericModel) ((HwHealthLineEntry) it.next()).acquireModel()).e("BRADYCARDIA_DETAIL").iterator();
                                while (it2.hasNext()) {
                                    psk pskVar = (psk) it2.next();
                                    if (pskVar.c() < f) {
                                        f = pskVar.c();
                                    }
                                }
                            }
                            return f;
                        }
                    });
                }
                throw new RuntimeException("calculateMax can't find dataSet");
            }
        };
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        HwHealthLineDataSet onCreateDataSet = super.onCreateDataSet(hwHealthLineChart, dataInfos, bVar);
        onCreateDataSet.setColor(Color.argb(255, 252, 49, 89));
        onCreateDataSet.d(Color.argb(229, a.L, 70, 94));
        onCreateDataSet.b(Color.argb(127, 252, 49, 89), Color.argb(0, 252, 49, 89), true);
        onCreateDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.9
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i, int i2, int i3) {
                return i2 - i <= 35;
            }
        });
        onCreateDataSet.setLineWidth(1.5f);
        onCreateDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.8
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return false;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return Utils.convertDpToPixel(4.0f);
            }
        });
        onCreateDataSet.setLabelCount(5, true);
        hwHealthLineChart.getAxisFirstParty().setAxisMinimum(40.0f);
        hwHealthLineChart.getAxisFirstParty().setAxisMaximum(220.0f);
        b(onCreateDataSet, bVar);
        return onCreateDataSet;
    }

    private void b(HwHealthLineDataSet hwHealthLineDataSet, HwHealthChartHolder.b bVar) {
        if (HwHealthChartHolder.LAYER_ID_REST_HR.equals(bVar.e())) {
            this.f = hwHealthLineDataSet;
            return;
        }
        if (HwHealthChartHolder.LAYER_ID_WARNING_HR.equals(bVar.e())) {
            this.g = hwHealthLineDataSet;
        } else if (HwHealthChartHolder.LAYER_ID_BRADYCARDIA.equals(bVar.e())) {
            this.b = hwHealthLineDataSet;
        } else {
            LogUtil.a("HeartRateLineChartHolder", "recordDataSet is null?");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwHealthLineDataSet onFakeDataSet(HwHealthChartHolder.b bVar) {
        ArrayList arrayList = new ArrayList(16);
        DataInfos d = bVar.d();
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, getChartBrief(d), getChartLabel(d), getChartUnit(d));
        hwHealthLineDataSet.setColor(Color.argb(255, 252, 49, 89));
        hwHealthLineDataSet.d(Color.argb(229, a.L, 70, 94));
        hwHealthLineDataSet.b(Color.argb(127, 252, 49, 89), Color.argb(0, 252, 49, 89), true);
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.6
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i, int i2, int i3) {
                return i2 - i <= 35;
            }
        });
        hwHealthLineDataSet.setLineWidth(2.0f);
        hwHealthLineDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.chart.HeartRateLineChartHolder.4
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return false;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return Utils.convertDpToPixel(4.0f);
            }
        });
        b(hwHealthLineDataSet, bVar);
        return hwHealthLineDataSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos) {
        hwHealthLineChart.makeReverse(true);
        hwHealthLineChart.setGridColor(nrn.d(BaseApplication.getContext(), R.color._2131297796_res_0x7f090604), nrn.d(BaseApplication.getContext(), R.color._2131297796_res_0x7f090604));
        hwHealthLineChart.setLabelColor(nrn.d(BaseApplication.getContext(), R.color._2131299241_res_0x7f090ba9));
        hwHealthLineChart.setAvoidFirstLastClipping(false);
        hwHealthLineChart.enableOnlyShowMinutes(true);
        hwHealthLineChart.enableOneMinuteOmit(true);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.f9852a;
    }

    public ICustomCalculator b() {
        return this.j;
    }

    public ICustomCalculator h() {
        return this.d;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser
    public String parse(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    public ICustomCalculator d() {
        return this.h;
    }

    public ICustomCalculator c() {
        return this.i;
    }

    public ICustomCalculator a() {
        return this.e;
    }

    public ICustomCalculator e() {
        return this.c;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor
    public void visitShowModels(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, String str, DataInfos dataInfos, LogicalUnit logicalUnit) {
        HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet;
        if (HwHealthChartHolder.HIGH_WARN.equals(str)) {
            hwHealthBaseBarLineDataSet = this.g;
        } else {
            hwHealthBaseBarLineDataSet = this.b;
        }
        hwHealthBaseBarLineDataSet.calculateLogicByShowRange(hwHealthBaseScrollBarLineChart, logicalUnit);
    }
}
