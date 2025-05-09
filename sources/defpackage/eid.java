package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.marketing.utils.EcgDataInquirer;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class eid extends EcgDataInquirer {
    private final int d;
    private final int e;

    public eid(int[] iArr) {
        super(iArr, MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_ARRHYTHMIA);
        this.e = iArr[0];
        this.d = iArr[1];
    }

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQuerySummaryDone(SparseArray<List<HiHealthData>> sparseArray) {
        if (sparseArray == null) {
            return;
        }
        List<HiHealthData> list = sparseArray.get(this.d, null);
        if (koq.c(list)) {
            this.mMeasureCardBean.a(list.get(0));
            HiHealthData hiHealthData = list.get(0);
            queryDetail(hiHealthData.getStartTime(), hiHealthData.getEndTime(), this.mDataTypes);
        } else {
            this.mMeasureCardBean.a((HiHealthData) null).a((List<fbl>) null);
            ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 8);
            LogUtil.a("EcgArrhythmiaDataInquirer", "parseEcgData no collection data found!");
        }
        ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 4);
    }

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQueryDetailDone(SparseArray<List<HiHealthData>> sparseArray) {
        List<HiHealthData> list = sparseArray.get(this.e);
        if (koq.b(list)) {
            LogUtil.b("EcgArrhythmiaDataInquirer", "parseEcgDiagramSeqData cause hiSeqDataList is empty");
            this.mMeasureCardBean.a((List<fbl>) null);
            ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 8);
            return;
        }
        HiHealthData hiHealthData = list.get(0);
        String simpleData = hiHealthData.getSimpleData();
        if (TextUtils.isEmpty(simpleData)) {
            LogUtil.a("EcgArrhythmiaDataInquirer", "onQueryDetailDone getDrawDataJsonStrWithAlgo now!");
            simpleData = eii.a(hiHealthData);
        }
        this.mMeasureCardBean.a(eii.d(simpleData));
        ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 8);
    }
}
