package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class qmg extends HwHealthLineScrollChartHolder implements HighlightedEntryParser {

    /* renamed from: a, reason: collision with root package name */
    private qmk f16482a;
    private HwHealthLineDataSet.NodeDrawStyle b;
    private DataInfos d;
    private HwHealthLineDataSet.LineLinkerFilter e;

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 99.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return 0.0f;
    }

    public qmg(Context context, DataInfos dataInfos) {
        super(context);
        this.f16482a = new qmk();
        this.e = new HwHealthLineDataSet.LineLinkerFilter() { // from class: qmg.4
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i, int i2, int i3) {
                return true;
            }
        };
        this.b = new HwHealthLineDataSet.NodeDrawStyle() { // from class: qmg.3
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
        };
        this.d = dataInfos;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.f16482a;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser
    public String parse(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos) {
        hwHealthLineChart.makeReverse(true);
        hwHealthLineChart.setGridColor(nrn.d(BaseApplication.getContext(), R.color._2131297796_res_0x7f090604), nrn.d(BaseApplication.getContext(), R.color._2131297796_res_0x7f090604));
        hwHealthLineChart.setLabelColor(nrn.d(BaseApplication.getContext(), R.color._2131297798_res_0x7f090606));
        hwHealthLineChart.setAvoidFirstLastClipping(false);
        hwHealthLineChart.enableOnlyShowMinutes(true);
        hwHealthLineChart.enableOneMinuteOmit(true);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        edd eddVar = new edd(new ArrayList(10), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), this.d);
        eddVar.setColor(nrn.d(BaseApplication.getContext(), R.color._2131297865_res_0x7f090649));
        eddVar.d(Color.argb(229, 178, 178, 178));
        eddVar.a(this.e);
        eddVar.setLineWidth(1.5f);
        eddVar.d(this.b);
        eddVar.setLabelCount(5, true);
        hwHealthLineChart.getAxisFirstParty().setAxisMinimum(0.0f);
        hwHealthLineChart.getAxisFirstParty().setAxisMaximum(99.0f);
        hwHealthLineChart.getAxisSecondParty().setEnabled(false);
        return eddVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HwHealthLineDataSet onFakeDataSet(HwHealthChartHolder.b bVar) {
        ArrayList arrayList = new ArrayList(10);
        DataInfos d = bVar.d();
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, getChartBrief(d), getChartLabel(d), getChartUnit(d));
        hwHealthLineDataSet.setColor(nrn.d(BaseApplication.getContext(), R.color._2131297865_res_0x7f090649));
        hwHealthLineDataSet.d(Color.argb(229, a.L, 70, 94));
        hwHealthLineDataSet.b(Color.argb(127, 178, 178, 178), Color.argb(0, 178, 178, 178), true);
        hwHealthLineDataSet.a(this.e);
        hwHealthLineDataSet.setLineWidth(2.0f);
        hwHealthLineDataSet.d(this.b);
        return hwHealthLineDataSet;
    }
}
