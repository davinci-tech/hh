package defpackage;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import java.util.List;

/* loaded from: classes3.dex */
public class eco extends ecv {
    @Override // defpackage.ecv, com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
        return super.getEntriesForXValue(f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        HwHealthBaseEntry constructEntry = super.constructEntry(f, iStorageModel);
        ecm ecmVar = new ecm(0.0f);
        if (iStorageModel instanceof ecm) {
            ecmVar = (ecm) iStorageModel;
        }
        if (ecmVar.a() == 0.0f) {
            return null;
        }
        constructEntry.setData(iStorageModel);
        return constructEntry;
    }
}
