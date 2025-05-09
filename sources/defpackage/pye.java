package defpackage;

import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DataLayerType;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.main.stories.fitness.util.chart.DataSetLayerListener;
import com.huawei.ui.main.stories.fitness.util.chart.MultiDataBarEntry;
import java.util.List;

/* loaded from: classes6.dex */
public class pye extends nmy implements DataSetLayerListener {
    private pya e;

    public pye(List<HwHealthBarEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(list, str, str2, str3, dataInfos);
        this.e = new pya();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBarEntry constructEntry(float f, IStorageModel iStorageModel) {
        if (iStorageModel instanceof pxx) {
            ((pxx) iStorageModel).d(this.e);
            return new MultiDataBarEntry(f, iStorageModel, this.e);
        }
        return super.constructEntry(f, iStorageModel);
    }

    @Override // com.huawei.ui.main.stories.fitness.util.chart.DataSetLayerListener
    public void onChange(DataLayerType dataLayerType, noq noqVar) {
        if (dataLayerType == null || dataLayerType.equals(this.e.d())) {
            return;
        }
        this.e.c(dataLayerType);
        if (noqVar != null) {
            setColor(noqVar.b());
            e(noqVar.e());
            d().d(noqVar.a());
            d().c(noqVar.d());
            d().c(noqVar.c());
        }
    }
}
