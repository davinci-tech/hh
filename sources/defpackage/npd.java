package defpackage;

import android.content.Context;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;

/* loaded from: classes6.dex */
public class npd extends npa {
    public npd(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
    }

    @Override // defpackage.npa, defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public now getDataset() {
        return this.mChart.getPointData();
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        now pointData = this.mChart.getPointData();
        return (pointData == null || pointData.getDataSets() == null || pointData.getDataSets().size() == 0) ? false : true;
    }
}
