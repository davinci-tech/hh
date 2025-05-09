package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.huawei.health.knit.section.chart.InteractorLineChartHolder;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class pkb extends InteractorLineChartHolder {
    private IChartStorageHelper d;
    private DataInfos e;

    private float d() {
        return 101.0f;
    }

    public pkb(Context context, DataInfos dataInfos, BloodOxygenDayDetailFragmentPresenter bloodOxygenDayDetailFragmentPresenter) {
        super(context);
        this.e = dataInfos;
        this.d = new pkh(bloodOxygenDayDetailFragmentPresenter);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pkc pkcVar = new pkc(new ArrayList(16), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), this.e);
        pkcVar.setColor(Color.argb(255, 178, 178, 178));
        pkcVar.d(Color.argb(229, 178, 178, 178));
        pkcVar.a(this.mLineLinkerFilter);
        pkcVar.setLineWidth(1.5f);
        pkcVar.d(this.mNodeDrawStyle);
        pkcVar.setLabelCount(5, true);
        hwHealthLineChart.getAxisFirstParty().setAxisMinimum(85.0f);
        hwHealthLineChart.getAxisFirstParty().setAxisMaximum(d());
        hwHealthLineChart.getAxisSecondParty().setEnabled(false);
        return pkcVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HwHealthLineDataSet onFakeDataSet(HwHealthChartHolder.b bVar) {
        ArrayList arrayList = new ArrayList(16);
        DataInfos d = bVar.d();
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, getChartBrief(d), getChartLabel(d), getChartUnit(d));
        hwHealthLineDataSet.setColor(Color.argb(255, 178, 178, 178));
        hwHealthLineDataSet.d(Color.argb(229, a.L, 70, 94));
        hwHealthLineDataSet.b(Color.argb(127, 178, 178, 178), Color.argb(0, 178, 178, 178), true);
        hwHealthLineDataSet.a(this.mLineLinkerFilter);
        hwHealthLineDataSet.setLineWidth(2.0f);
        hwHealthLineDataSet.d(this.mNodeDrawStyle);
        return hwHealthLineDataSet;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return d();
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return pjm.c(f2);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public void initChartData(HwHealthLineChart hwHealthLineChart) {
        if (((now) hwHealthLineChart.getData()) == null) {
            hwHealthLineChart.setData(new pki(new ArrayList()));
        }
    }
}
