package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pza extends pyd {
    pza(List<HwHealthBaseEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(BaseApplication.getContext(), list, str, str2, str3, dataInfos);
    }

    @Override // defpackage.pyd, com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
        return b(super.getEntriesForXValue(f));
    }

    private List<HwHealthBaseEntry> b(List<HwHealthBaseEntry> list) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.c(list) && koq.c(this.mEntries)) {
            for (T t : this.mEntries) {
                HwHealthBaseEntry hwHealthBaseEntry = list.get(0);
                if (hwHealthBaseEntry != null && hwHealthBaseEntry.getX() == t.getX()) {
                    arrayList.add(t);
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        HwHealthBaseEntry constructEntry = super.constructEntry(f, iStorageModel);
        constructEntry.setData(iStorageModel);
        return constructEntry;
    }
}
