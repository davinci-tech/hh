package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qkr implements IChartStorageHelper {
    private String d = "lthrHr";

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos.isMonthData()) {
            a(j, j2, responseCallback);
        } else {
            b(j, j2, responseCallback);
        }
    }

    private void b(long j, long j2, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        final HashMap hashMap = new HashMap(16);
        qks.c(j, j2, new IBaseResponseCallback() { // from class: qkq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qkr.this.e(responseCallback, hashMap, i, obj);
            }
        });
    }

    /* synthetic */ void e(ResponseCallback responseCallback, Map map, int i, Object obj) {
        if (i != 0) {
            responseCallback.onResult(100001, map);
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("LacticStorageHelper", "readYearDatas type error");
            responseCallback.onResult(100001, map);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("LacticStorageHelper", "readYearDatas map is empty");
            responseCallback.onResult(100001, map);
            return;
        }
        Object obj2 = sparseArray.get(42112);
        List<HiHealthData> list = koq.e(obj2, HiHealthData.class) ? (List) obj2 : null;
        Object obj3 = sparseArray.get(42113);
        List<HiHealthData> list2 = koq.e(obj3, HiHealthData.class) ? (List) obj3 : null;
        if (list != null && list2 != null) {
            e(map, list, list2);
        }
        responseCallback.onResult(0, map);
    }

    private void a(long j, long j2, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        final HashMap hashMap = new HashMap(16);
        qks.a(j, j2, new IBaseResponseCallback() { // from class: qkp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qkr.this.c(responseCallback, hashMap, i, obj);
            }
        });
    }

    /* synthetic */ void c(ResponseCallback responseCallback, Map map, int i, Object obj) {
        if (i != 0) {
            responseCallback.onResult(100001, map);
            return;
        }
        if (!(obj instanceof List)) {
            LogUtil.h("LacticStorageHelper", "readData type error");
            responseCallback.onResult(100001, map);
            return;
        }
        List<HiHealthData> list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("LacticStorageHelper", "readData list is empty");
            responseCallback.onResult(100001, map);
        } else {
            c(map, list);
            responseCallback.onResult(0, map);
        }
    }

    private void c(Map<Long, IStorageModel> map, List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            long g = jec.g(hiHealthData.getStartTime());
            map.put(Long.valueOf(g), new edw(hiHealthData.getFloat("lthrHr"), hiHealthData.getFloat("lthrPace"), this.d));
        }
    }

    private void e(Map<Long, IStorageModel> map, List<HiHealthData> list, List<HiHealthData> list2) {
        if (list.size() != list2.size()) {
            return;
        }
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            long g = nom.g(hiHealthData.getStartTime());
            map.put(Long.valueOf(g), new edw(hiHealthData.getFloat("point_value"), list2.get(i).getFloat("point_value"), this.d));
            i++;
        }
    }

    public void b(String str) {
        this.d = str;
    }
}
