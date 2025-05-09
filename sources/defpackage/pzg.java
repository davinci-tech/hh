package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.base.InteractorLineChartHolder;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class pzg extends InteractorLineChartHolder {
    private final pzj d;
    private final DataInfos e;

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        if (f > 34.0f) {
            return 34.0f;
        }
        float f3 = (f % 2.0f != 0.0f ? 3.0f : 2.0f) + f;
        if (f3 < 12.0f) {
            return 12.0f;
        }
        return f3;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return f2 - (f2 % 2.0f != 0.0f ? 3.0f : 2.0f) > 4.0f ? 4.0f : 0.0f;
    }

    public pzg(Context context, DataInfos dataInfos) {
        super(context);
        this.e = dataInfos;
        this.d = new pzj(dataInfos);
    }

    public void dvW_(Context context, Handler handler) {
        this.d.dwh_(context, handler);
    }

    public void b(String str) {
        this.d.c(str);
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pza pzaVar = new pza(new ArrayList(16), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), this.e);
        pzaVar.setColor(Color.argb(255, 178, 178, 178));
        pzaVar.d(Color.argb(229, 178, 178, 178));
        pzaVar.a(this.mLineLinkerFilter);
        pzaVar.setLineWidth(1.5f);
        pzaVar.d(this.mNodeDrawStyle);
        pzaVar.setLabelCount(5, true);
        hwHealthLineChart.getAxisFirstParty().setAxisMinimum(0.0f);
        hwHealthLineChart.getAxisFirstParty().setAxisMaximum(34.0f);
        hwHealthLineChart.getAxisSecondParty().setEnabled(false);
        hwHealthLineChart.getAxisThirdParty().setEnabled(false);
        return pzaVar;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.d;
    }

    public void c() {
        pzj pzjVar = this.d;
        if (pzjVar != null) {
            pzjVar.a();
        }
    }
}
