package defpackage;

import android.util.SparseArray;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.marketing.utils.EcgDataInquirer;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hihealth.HiHealthData;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class eiu extends EcgDataInquirer {
    private final int b;

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQueryDetailDone(SparseArray<List<HiHealthData>> sparseArray) {
    }

    public eiu(int[] iArr) {
        super(iArr, MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_VASCULAR);
        this.b = iArr[0];
    }

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQuerySummaryDone(SparseArray<List<HiHealthData>> sparseArray) {
        if (sparseArray == null) {
            return;
        }
        LogUtil.e("VascularDataInquirer", "onQuerySummaryDone sparseArray size  = ", Integer.valueOf(sparseArray.size()));
        List<HiHealthData> list = sparseArray.get(this.b, null);
        if (koq.c(list)) {
            HiHealthData hiHealthData = list.get(0);
            this.mMeasureCardBean.a(hiHealthData).a(new ArrayList(Collections.singletonList(new fbl(hiHealthData.getValue()))));
        } else {
            this.mMeasureCardBean.a((HiHealthData) null);
        }
        ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 16);
    }
}
