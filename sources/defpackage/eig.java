package defpackage;

import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.marketing.utils.EcgDataInquirer;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.EcgMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class eig extends EcgDataInquirer {
    private final int b;
    private final int e;

    public eig(int[] iArr) {
        super(iArr, MeasureCardItemHolder.MeasureType.ITEM_TYPE_ECG_NORMAL);
        this.b = iArr[0];
        this.e = iArr[1];
    }

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQuerySummaryDone(SparseArray<List<HiHealthData>> sparseArray) {
        EcgMetaData ecgMetaData;
        List<HiHealthData> list = sparseArray.get(this.b, null);
        if (koq.c(list)) {
            this.mMeasureCardBean.a(list.get(0)).a((List<fbl>) null);
        } else {
            this.mMeasureCardBean.a((HiHealthData) null).a((List<fbl>) null);
            LogUtil.b("EcgNormalDataInquirer", "parseEcgData no collection data found!");
        }
        List<HiHealthData> list2 = sparseArray.get(this.e, null);
        if (koq.c(list2)) {
            for (int i = 0; i < list2.size(); i++) {
                HiHealthData hiHealthData = list2.get(i);
                try {
                    ecgMetaData = (EcgMetaData) nrv.c(hiHealthData.getMetaData(), new TypeToken<EcgMetaData>() { // from class: eig.1
                    }.getType());
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("EcgNormalDataInquirer", "JsonSyntaxException");
                    ecgMetaData = new EcgMetaData();
                }
                if (((int) ecgMetaData.getEcgArrhyType()) != 0) {
                    this.mMeasureCardBean.d(hiHealthData);
                    queryDetail(hiHealthData.getStartTime(), hiHealthData.getEndTime(), this.mDataTypes);
                    break;
                }
            }
        } else {
            LogUtil.b("EcgNormalDataInquirer", "parseEcgData no diagram data found, diagramDataList is empty!");
        }
        LogUtil.b("EcgNormalDataInquirer", "parseEcgData no diagram data found, diagramDataList has no valid data!");
        this.mMeasureCardBean.d((HiHealthData) null).a((List<fbl>) null);
        ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 1);
    }

    @Override // com.huawei.health.marketing.utils.EcgDataInquirer
    public void onQueryDetailDone(SparseArray<List<HiHealthData>> sparseArray) {
        List<HiHealthData> list = sparseArray.get(this.e);
        if (koq.b(list)) {
            LogUtil.b("EcgNormalDataInquirer", "parseEcgDiagramSeqData cause hiSeqDataList is empty");
            return;
        }
        List<fbl> d = eii.d(list);
        if (d == null) {
            return;
        }
        this.mMeasureCardBean.a(d);
        ObserverManagerUtil.c(EcgDataInquirer.ECG_DATA_OBSERVER, 2);
    }
}
