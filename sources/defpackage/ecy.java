package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.List;

/* loaded from: classes8.dex */
public class ecy extends HwHealthLineDataSet {
    public ecy(Context context, List<HwHealthBaseEntry> list, String str, String str2, String str3) {
        super(context, list, str, str2, str3);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        HwHealthBaseEntry constructEntry = super.constructEntry(f, iStorageModel);
        constructEntry.setData(iStorageModel);
        return constructEntry;
    }
}
