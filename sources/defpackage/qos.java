package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class qos extends HwHealthBarScrollChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private String f16527a;
    private final qop b;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.commonui.linechart.HwHealthChartHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onCustomChartStyle(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos) {
    }

    public qos(Context context, LineChartViewPresenter lineChartViewPresenter) {
        super(context);
        this.f16527a = "TEMPERATURE_MIN_MAX";
        this.b = new qop(lineChartViewPresenter);
    }

    public void a(String str) {
        this.b.e(str);
        this.f16527a = str;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.a(f2, f, "TEMPERATURE_MIN_MAX".equals(this.f16527a));
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        return qpr.e(f2, f, "TEMPERATURE_MIN_MAX".equals(this.f16527a));
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthBarDataSet onCreateDataSet(HwHealthBarChart hwHealthBarChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        pyp pypVar = new pyp(new ArrayList(), getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos), dataInfos);
        if (dataInfos.isBloodOxygenData()) {
            pypVar.setColor(Color.argb(255, 253, 178, 144));
            pypVar.e(Color.argb(255, 255, 64, 101));
        }
        pypVar.setLabelCount(5, true);
        ((nmz) hwHealthBarChart.getData()).d(dataInfos);
        return pypVar;
    }
}
