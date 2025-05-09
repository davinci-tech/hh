package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import java.util.List;

/* loaded from: classes9.dex */
public class pkc extends pyd {
    @Override // com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMaxY(float f, float f2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void calcMinMaxY(HwHealthBaseEntry hwHealthBaseEntry) {
    }

    pkc(List<HwHealthBaseEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(BaseApplication.getContext(), list, str, str2, str3, dataInfos);
    }

    @Override // defpackage.pyd, com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
        return super.getEntriesForXValue(f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        HwHealthBaseEntry constructEntry = super.constructEntry(f, iStorageModel);
        constructEntry.setData(iStorageModel);
        return constructEntry;
    }

    @Override // com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public void calcMinMax() {
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (T t : this.mEntries) {
            if (t.getY() != 0.0f) {
                if (t.getY() > i) {
                    i = (int) t.getY();
                }
                if (t.getY() < i2) {
                    i2 = (int) t.getY();
                }
            }
        }
        this.mYMax = i;
        this.mYMin = i2;
    }
}
