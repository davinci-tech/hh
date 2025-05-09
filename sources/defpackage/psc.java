package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.model.HiHearRateUpMetaData;
import com.huawei.hihealth.data.model.HiHeartRateData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AggregateOptionBuilder;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class psc {
    public static void a(String str, int i, final ResponseCallback<List<prw>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "queryModelUnitByDetail callback is null");
            return;
        }
        LogUtil.a("HealthHeartRate_HeartRateHistoryHelper", "queryModelUnitByDetail detailKey=", str, ",detailAggregate=", Integer.valueOf(i));
        AggregateOptionBuilder aggregateOptionBuilder = new AggregateOptionBuilder();
        aggregateOptionBuilder.c(0L, System.currentTimeMillis());
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(aggregateOptionBuilder.a(AggregateOptionBuilder.DataGroupUnit.NONE, str, i));
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: psf
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public final void onResult(SparseArray sparseArray, int i2, int i3) {
                psc.dsL_(ResponseCallback.this, sparseArray, i2, i3);
            }
        });
    }

    static /* synthetic */ void dsL_(ResponseCallback responseCallback, SparseArray sparseArray, int i, int i2) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "queryModelUnitByDetail dataListArray is empty");
            responseCallback.onResponse(-1, null);
            return;
        }
        List list = (List) sparseArray.get(0);
        if (koq.b(list)) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "queryModelUnitByDetail dataList is empty");
            responseCallback.onResponse(-1, null);
        } else {
            SparseArray sparseArray2 = new SparseArray();
            dsK_(list, sparseArray2);
            responseCallback.onResponse(0, dsM_(sparseArray2));
        }
    }

    private static List<prw> dsM_(SparseArray<prw> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (sparseArray.size() == 0) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "sortHeartRateData monthDataList is empty");
            return arrayList;
        }
        int size = sparseArray.size();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < size; i++) {
            prw valueAt = sparseArray.valueAt(i);
            if (valueAt != null) {
                arrayList2.add(Integer.valueOf(sparseArray.keyAt(i)));
                b(valueAt.a());
            }
        }
        if (koq.b(arrayList2)) {
            return arrayList;
        }
        Collections.sort(arrayList2, new Comparator() { // from class: pse
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return psc.c((Integer) obj, (Integer) obj2);
            }
        });
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(sparseArray.get(((Integer) it.next()).intValue()));
        }
        return arrayList;
    }

    static /* synthetic */ int c(Integer num, Integer num2) {
        return num2.intValue() - num.intValue();
    }

    private static void dsK_(List<HiHealthData> list, SparseArray<prw> sparseArray) {
        HiHearRateUpMetaData hiHearRateUpMetaData;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (hiHearRateUpMetaData = (HiHearRateUpMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiHearRateUpMetaData.class)) != null) {
                pru pruVar = new pru();
                pruVar.a(hiHearRateUpMetaData.getMinHeartRate());
                pruVar.c(hiHearRateUpMetaData.getMaxHeartRate());
                pruVar.d(hiHealthData.getStartTime());
                pruVar.e(hiHealthData.getEndTime());
                e(hiHearRateUpMetaData.getDetails(), pruVar);
                long startTime = hiHealthData.getStartTime();
                int e = gic.e((Object) HiDateUtil.e(new Date(startTime), "yyyyMM"));
                prw prwVar = sparseArray.get(e);
                if (prwVar == null) {
                    prwVar = new prw();
                }
                List<pru> a2 = prwVar.a();
                if (koq.b(a2)) {
                    a2 = new ArrayList<>();
                }
                a2.add(pruVar);
                prwVar.c(false);
                prwVar.c(startTime);
                prwVar.e(a2);
                sparseArray.put(e, prwVar);
            }
        }
    }

    private static void e(List<HiHeartRateData> list, pru pruVar) {
        if (koq.b(list)) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "setHeartRateDetail warningList is empty");
            return;
        }
        ArrayList<HeartRateDetailData> arrayList = new ArrayList<>(16);
        for (HiHeartRateData hiHeartRateData : list) {
            HeartRateDetailData heartRateDetailData = new HeartRateDetailData();
            heartRateDetailData.setTime(hiHeartRateData.getTimestamp());
            heartRateDetailData.setValue(hiHeartRateData.getHeartRate());
            arrayList.add(heartRateDetailData);
        }
        pruVar.d(arrayList);
    }

    private static void b(List<pru> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthHeartRate_HeartRateHistoryHelper", "compare dayList is empty");
        } else {
            Collections.sort(list, new Comparator() { // from class: psd
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return psc.a((pru) obj, (pru) obj2);
                }
            });
        }
    }

    static /* synthetic */ int a(pru pruVar, pru pruVar2) {
        if (pruVar == null || pruVar2 == null) {
            return 0;
        }
        return (int) (pruVar2.e() - pruVar.e());
    }
}
