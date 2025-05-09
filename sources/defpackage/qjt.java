package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class qjt implements IChartStorageHelper {
    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        d(j, j2, dataInfos, responseCallback);
    }

    private void d(long j, long j2, final DataInfos dataInfos, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        kor.a().g(j, j2, new IBaseResponseCallback() { // from class: qjt.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof List) {
                    qjt.this.e((List) obj, dataInfos, responseCallback);
                } else {
                    responseCallback.onResult(0, new HashMap());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Vo2maxDetail> list, DataInfos dataInfos, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        long g;
        Map<Long, IStorageModel> hashMap = new HashMap<>();
        if (bkz.e(list)) {
            responseCallback.onResult(0, hashMap);
            LogUtil.d("Track_RqLineVo2maxChartStorageHelper", "list is empty");
            return;
        }
        if (dataInfos == DataInfos.Vo2maxYearDetail) {
            list = c(list);
        }
        for (Vo2maxDetail vo2maxDetail : list) {
            if (vo2maxDetail != null) {
                nnw nnwVar = new nnw(1.0f);
                nnwVar.d(vo2maxDetail.getVo2maxValue());
                if (dataInfos == DataInfos.Vo2maxYearDetail) {
                    g = ruf.a(new Date(vo2maxDetail.getTimestamp())).getTime();
                } else {
                    g = jec.g(vo2maxDetail.getTimestamp());
                }
                hashMap.put(Long.valueOf(g), nnwVar);
            }
        }
        responseCallback.onResult(0, hashMap);
    }

    private List<Vo2maxDetail> c(List<Vo2maxDetail> list) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Vo2maxDetail vo2maxDetail : list) {
            if (vo2maxDetail != null) {
                String a2 = ggl.a(new Date(vo2maxDetail.getTimestamp()), "yyyyMM");
                if (hashMap.containsKey(a2) && hashMap.get(a2) != null) {
                    if (vo2maxDetail.getVo2maxValue() > ((Vo2maxDetail) hashMap.get(a2)).getVo2maxValue()) {
                        hashMap.put(a2, vo2maxDetail);
                    }
                } else {
                    hashMap.put(a2, vo2maxDetail);
                }
            }
        }
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add((Vo2maxDetail) ((Map.Entry) it.next()).getValue());
        }
        return arrayList;
    }
}
