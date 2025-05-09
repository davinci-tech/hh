package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode;
import com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class qoq extends HwHealthLineScrollChartHolder implements HighlightedEntryParser {

    /* renamed from: a, reason: collision with root package name */
    private String f16526a;
    private final float b;
    private npc c;
    private final float d;
    private final qop e;

    static /* synthetic */ boolean b(int i, int i2, int i3) {
        return i2 - i <= 60;
    }

    static /* synthetic */ boolean e(int i, int i2, int i3) {
        return true;
    }

    public qoq(Context context, LineChartViewPresenter lineChartViewPresenter) {
        super(context);
        this.f16526a = "TEMPERATURE_MIN_MAX";
        if (Utils.o()) {
            this.f16526a = "SKIN_TEMPERATURE_MIN_MAX";
        }
        this.e = new qop(lineChartViewPresenter);
        this.b = qpr.d(35.0f);
        this.d = qpr.d(37.2f);
    }

    public void c(String str) {
        this.e.e(str);
        d(str);
    }

    public void e(String str) {
        this.f16526a = str;
    }

    private void d(String str) {
        if (this.c == null) {
            LogUtil.h("TemperatureLineChartHolder", "setColor dataSet is null");
        } else if ("TEMPERATURE_MIN_MAX".equals(str)) {
            this.c.e(new IHealthMultiColorMode() { // from class: qoq.4
                @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
                public int[] getDataColor() {
                    return new int[]{-13404161, -11093057, -301790};
                }

                @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
                public float[] getThreshold() {
                    return new float[]{qoq.this.b, qoq.this.d};
                }
            });
        } else {
            this.c.e(new IHealthMultiColorMode() { // from class: qoq.1
                @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
                public int[] getDataColor() {
                    return new int[]{-11093057, -11093057, -11093057};
                }

                @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
                public float[] getThreshold() {
                    return new float[]{20.0f, 42.0f};
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        this.c = new npc(new ArrayList(16), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos));
        d(this.f16526a);
        this.c.d(Color.argb(229, 178, 178, 178));
        this.c.setLabelCount(5, true);
        this.c.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: qox
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i, int i2, int i3) {
                return qoq.b(i, i2, i3);
            }
        });
        this.c.setLineWidth(1.5f);
        this.c.setShowMaxMinValue(true);
        this.c.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: qoq.5
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
                return com.github.mikephil.charting.utils.Utils.convertDpToPixel(4.0f);
            }
        });
        if ("TEMPERATURE_MIN_MAX".equals(this.f16526a)) {
            hwHealthLineChart.getAxisFirstParty().setAxisMinimum(qpr.d(34.0f));
            hwHealthLineChart.getAxisFirstParty().setAxisMaximum(qpr.d(42.0f));
        } else {
            hwHealthLineChart.getAxisFirstParty().setAxisMinimum(qpr.d(20.0f));
            hwHealthLineChart.getAxisFirstParty().setAxisMaximum(qpr.d(42.0f));
        }
        hwHealthLineChart.getAxisSecondParty().setEnabled(false);
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public HwHealthLineDataSet onFakeDataSet(HwHealthChartHolder.b bVar) {
        ArrayList arrayList = new ArrayList(16);
        DataInfos d = bVar.d();
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, getChartBrief(d), getChartLabel(d), getChartUnit(d));
        hwHealthLineDataSet.setColor(Color.argb(255, 178, 178, 178));
        hwHealthLineDataSet.d(Color.argb(229, a.L, 70, 94));
        hwHealthLineDataSet.b(Color.argb(127, 178, 178, 178), Color.argb(0, 178, 178, 178), true);
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: qot
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i, int i2, int i3) {
                return qoq.e(i, i2, i3);
            }
        });
        hwHealthLineDataSet.setForcedLabels(pjm.a());
        hwHealthLineDataSet.setLineWidth(2.0f);
        hwHealthLineDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: qoq.2
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
                return com.github.mikephil.charting.utils.Utils.convertDpToPixel(4.0f);
            }
        });
        return hwHealthLineDataSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos) {
        if (hwHealthLineChart == null) {
            return;
        }
        hwHealthLineChart.makeReverse(true);
        hwHealthLineChart.setGridColor(nrn.d(BaseApplication.getContext(), R.color._2131297171_res_0x7f090393), nrn.d(BaseApplication.getContext(), R.color._2131296913_res_0x7f090291));
        hwHealthLineChart.setLabelColor(nrn.d(BaseApplication.getContext(), R.color._2131297798_res_0x7f090606));
        hwHealthLineChart.setAvoidFirstLastClipping(false);
        hwHealthLineChart.enableOnlyShowMinutes(true);
        hwHealthLineChart.enableOneMinuteOmit(true);
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.a(f2, f, "TEMPERATURE_MIN_MAX".equals(this.f16526a));
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.e(f2, f, "TEMPERATURE_MIN_MAX".equals(this.f16526a));
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser
    public String parse(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 1);
    }
}
