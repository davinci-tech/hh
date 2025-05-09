package com.huawei.ui.main.stories.health.lactatethreshold.chart;

import android.R;
import android.content.Context;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import com.huawei.health.knit.section.utils.CardSelectedInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser;
import defpackage.ecy;
import defpackage.nmt;
import defpackage.nmv;
import defpackage.qkr;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class LacticChartHolder extends HwHealthLineScrollChartHolder implements HighlightedEntryParser, CardSelectedInterface {
    protected String c;
    private final qkr e;

    public LacticChartHolder(Context context) {
        super(context);
        this.c = "lthrHr";
        this.e = new qkr();
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        ecy ecyVar = new ecy(BaseApplication.getContext(), new ArrayList(16), "", "", "");
        ecyVar.setShowType(this.c);
        int b = b(ecyVar);
        ecyVar.setColor(b);
        ecyVar.b(Color.argb(153, Color.red(b), Color.green(b), Color.blue(b)), ContextCompat.getColor(this.mContext, R.color.transparent), true);
        ecyVar.setLabelCount(5, true);
        hwHealthLineChart.getAxisFirstParty().setAxisMaximum(c(ecyVar));
        hwHealthLineChart.getAxisFirstParty().setAxisMinimum(e(ecyVar));
        ecyVar.setLineWidth(1.5f);
        ecyVar.setShowMaxMinValue(false);
        if (a(ecyVar)) {
            hwHealthLineChart.getAxisFirstParty().setValueFormatter(new nmt());
        } else {
            hwHealthLineChart.getAxisFirstParty().setValueFormatter(new nmv());
        }
        return ecyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HwHealthLineDataSet onFakeDataSet(HwHealthChartHolder.b bVar) {
        return new HwHealthLineDataSet(BaseApplication.getContext(), new ArrayList(16), "", "", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b */
    public void onCustomChartStyle(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos) {
        hwHealthLineChart.setAvoidFirstLastClipping(false);
        int color = ContextCompat.getColor(this.mContext, com.huawei.health.R.color._2131296913_res_0x7f090291);
        hwHealthLineChart.setGridColor(color, color);
        hwHealthLineChart.enableOnlyShowMinutes(true);
        hwHealthLineChart.enableOneMinuteOmit(true);
        hwHealthLineChart.getAxisSecondParty().setEnabled(false);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.HighlightedEntryParser
    public String parse(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return c(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return e(hwHealthBaseBarLineDataSet);
    }

    @Override // com.huawei.health.knit.section.utils.CardSelectedInterface
    public void setShowDataType(String str) {
        this.c = str;
        this.e.b(str);
    }

    protected int b(IHwHealthBarLineDataSet iHwHealthBarLineDataSet) {
        if (a(iHwHealthBarLineDataSet)) {
            return ContextCompat.getColor(this.mContext, com.huawei.health.R.color._2131298895_res_0x7f090a4f);
        }
        return ContextCompat.getColor(this.mContext, com.huawei.health.R.color._2131297883_res_0x7f09065b);
    }

    protected boolean a(IHwHealthBarLineDataSet iHwHealthBarLineDataSet) {
        return "lthrPace".equals(iHwHealthBarLineDataSet.getShowType());
    }

    protected float e(IHwHealthBarLineDataSet iHwHealthBarLineDataSet) {
        return a(iHwHealthBarLineDataSet) ? 0.0f : 100.0f;
    }

    protected float c(IHwHealthBarLineDataSet iHwHealthBarLineDataSet) {
        return a(iHwHealthBarLineDataSet) ? 600.0f : 200.0f;
    }
}
