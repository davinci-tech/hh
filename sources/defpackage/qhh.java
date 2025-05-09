package defpackage;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class qhh implements IChartStorageHelper {
    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, final long j, final long j2, final DataInfos dataInfos, HwHealthChartHolder.b bVar, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        final HashMap hashMap = new HashMap(16);
        new qgx().a(true, j, j2, new IBaseResponseCallback() { // from class: qhk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qhh.this.e(j, j2, responseCallback, hashMap, dataInfos, i, obj);
            }
        });
    }

    /* synthetic */ void e(long j, long j2, ResponseCallback responseCallback, Map map, DataInfos dataInfos, int i, Object obj) {
        LogUtil.a("BloodPressureStorageHelper", "queryStepDayData, errorCode: " + i, ", startTime: " + j + ", endTime: " + j2);
        if (i != 0) {
            responseCallback.onResult(100001, map);
            return;
        }
        if (!(obj instanceof List)) {
            LogUtil.h("BloodPressureStorageHelper", "readData type error");
            responseCallback.onResult(100001, map);
            return;
        }
        List<qkg> list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("BloodPressureStorageHelper", "readData list is empty");
            responseCallback.onResult(100001, map);
            return;
        }
        Iterator<qkg> it = list.iterator();
        while (it.hasNext()) {
            LogUtil.a("BloodPressureStorageHelper", "healthData: " + it.next().toString());
        }
        if (dataInfos.isDayData()) {
            e(map, list);
        } else {
            d(map, list);
        }
        responseCallback.onResult(0, map);
    }

    private void d(Map<Long, IStorageModel> map, List<qkg> list) {
        HashMap hashMap = new HashMap();
        for (qkg qkgVar : list) {
            long g = jec.g(qkgVar.h());
            List list2 = (List) hashMap.get(Long.valueOf(g));
            if (list2 == null) {
                list2 = new ArrayList();
                hashMap.put(Long.valueOf(g), list2);
            }
            list2.add(qkgVar);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            long longValue = ((Long) entry.getKey()).longValue();
            List<qkg> list3 = (List) entry.getValue();
            int size = list3.size();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            for (qkg qkgVar2 : list3) {
                d += qkgVar2.o();
                d2 += qkgVar2.m();
                double n = qkgVar2.n();
                if (n > 0.0d) {
                    i++;
                    d3 += n;
                }
                double k = qkgVar2.k();
                if (k > 0.0d) {
                    i2++;
                    d4 += k;
                }
                double l = qkgVar2.l();
                if (l > 0.0d) {
                    i3++;
                    d5 += l;
                }
            }
            float f = 0.0f;
            float round = i > 0 ? Math.round(d3 / i) : 0.0f;
            float round2 = i2 > 0 ? Math.round(d4 / i2) : 0.0f;
            if (i3 > 0) {
                f = Math.round(d5 / i3);
            }
            double d6 = size;
            map.put(Long.valueOf(longValue), new edq(Math.round(d / d6), Math.round(d2 / d6), round, size, round2, f));
        }
    }

    private void e(Map<Long, IStorageModel> map, List<qkg> list) {
        HashMap hashMap = new HashMap();
        for (qkg qkgVar : list) {
            long e = BloodPressureUtil.e(qkgVar.h());
            List list2 = (List) hashMap.get(Long.valueOf(e));
            if (list2 == null) {
                list2 = new ArrayList();
                hashMap.put(Long.valueOf(e), list2);
            }
            list2.add(qkgVar);
        }
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            b((Map.Entry) it.next(), map);
        }
    }

    private void b(Map.Entry<Long, List<qkg>> entry, Map<Long, IStorageModel> map) {
        int i;
        float f;
        long longValue = entry.getKey().longValue();
        List<qkg> value = entry.getValue();
        int size = value.size();
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (qkg qkgVar : value) {
            d2 += qkgVar.o();
            d3 += qkgVar.m();
            d4 += qkgVar.n();
            if (qkgVar.n() > d) {
                i3++;
            }
            double k = qkgVar.k();
            if (k > d) {
                i2++;
                d5 += k;
            }
            double l = qkgVar.l();
            if (l > d) {
                i4++;
                d6 += l;
            }
            d = 0.0d;
        }
        int i5 = i4;
        if (i3 > 0) {
            i = i5;
            f = Math.round(d4 / i3);
        } else {
            i = i5;
            f = 0.0f;
        }
        float round = i2 > 0 ? Math.round(d5 / i2) : 0.0f;
        float round2 = i > 0 ? Math.round(d6 / i) : 0.0f;
        if (size == 1) {
            longValue = value.get(0).h();
        }
        double d7 = size;
        map.put(Long.valueOf(longValue), new edq(Math.round(d2 / d7), Math.round(d3 / d7), f, size, round, round2));
    }
}
