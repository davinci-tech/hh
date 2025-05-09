package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import java.util.List;

/* loaded from: classes8.dex */
public class edd extends npb {
    public edd(List<HwHealthBaseEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(BaseApplication.getContext(), list, str, str2, str3, dataInfos);
    }

    @Override // defpackage.npb, com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
        return super.getEntriesForXValue(f);
    }
}
