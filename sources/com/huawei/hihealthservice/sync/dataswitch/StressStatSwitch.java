package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.StressBasic;
import com.huawei.hwcloudmodel.model.unite.StressTotal;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class StressStatSwitch {

    /* renamed from: a, reason: collision with root package name */
    private Context f4218a;

    public StressStatSwitch(Context context) {
        this.f4218a = context.getApplicationContext();
    }

    public List<igo> e(List<StressTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<StressTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> d = d(it.next(), i);
            if (d != null && !d.isEmpty()) {
                arrayList.addAll(d);
            }
        }
        return arrayList;
    }

    public List<igo> d(StressTotal stressTotal, int i) {
        if (stressTotal.fetchDeviceCode() != null && stressTotal.fetchDeviceCode().longValue() != 0) {
            LogUtil.h("HiH_StressStatSwitch", "the StressTotal's deviceCode should be 0, deviceCode is ", stressTotal.fetchDeviceCode());
            return null;
        }
        if (stressTotal.fetchRecordDay() == null) {
            LogUtil.h("HiH_StressStatSwitch", "the StressTotal's recordDay should not be null, recordDay is ", stressTotal.fetchDeviceCode());
            return null;
        }
        ikv a2 = ikr.b(this.f4218a).a(0, i, 0);
        if (a2 == null) {
            return null;
        }
        List<igo> d = d(stressTotal.fetchStressBasic());
        if (d != null && !d.isEmpty()) {
            int intValue = stressTotal.fetchRecordDay().intValue();
            int b = a2.b();
            String fetchTimeZone = stressTotal.fetchTimeZone();
            long longValue = stressTotal.fetchGenerateTime().longValue();
            for (igo igoVar : d) {
                igoVar.b(b);
                igoVar.e(intValue);
                igoVar.b(fetchTimeZone);
                igoVar.g(1);
                igoVar.c(2034);
                igoVar.a(longValue);
            }
        }
        return d;
    }

    private List<igo> d(StressBasic stressBasic) {
        if (stressBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        int intValue = stressBasic.fetchMaxScore().intValue();
        int intValue2 = stressBasic.fetchMinScore().intValue();
        int intValue3 = stressBasic.fetchMeanScore().intValue();
        int intValue4 = stressBasic.fetchLastScore().intValue();
        int intValue5 = stressBasic.fetchMeasureCount().intValue();
        if (intValue > 0) {
            arrayList.add(iup.d(44305, intValue, 17));
        }
        if (intValue2 > 0) {
            arrayList.add(iup.d(44304, intValue2, 17));
        }
        if (intValue3 > 0) {
            arrayList.add(iup.d(44306, intValue3, 17));
        }
        if (intValue4 > 0) {
            arrayList.add(iup.d(44307, intValue4, 17));
        }
        if (intValue5 > 0) {
            arrayList.add(iup.d(44308, intValue5, 16));
        }
        return arrayList;
    }

    public List<StressTotal> a(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            StressTotal stressTotal = new StressTotal();
            stressTotal.configTimeZone(hiHealthData.getTimeZone());
            stressTotal.configGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            stressTotal.configRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            stressTotal.configDataSource(2);
            stressTotal.configDeviceCode(0L);
            StressBasic d = d(hiHealthData);
            if (d != null) {
                stressTotal.configStressBasic(d);
                arrayList.add(stressTotal);
            }
        }
        return arrayList;
    }

    private StressBasic d(HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("stress_score_last");
        int i2 = hiHealthData.getInt("stress_score_max");
        int i3 = hiHealthData.getInt("stress_score_avg");
        int i4 = hiHealthData.getInt("stress_score_count");
        int i5 = hiHealthData.getInt("stress_score_min");
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0 || i5 <= 0) {
            LogUtil.a("HiH_StressStatSwitch", "error stress stat, some stat <= 0");
            return null;
        }
        StressBasic stressBasic = new StressBasic();
        stressBasic.configLastScore(Integer.valueOf(i));
        stressBasic.configMaxScore(Integer.valueOf(i2));
        stressBasic.configMeanScore(Integer.valueOf(i3));
        stressBasic.configMeasureCount(Integer.valueOf(i4));
        stressBasic.configMinScore(Integer.valueOf(i5));
        return stressBasic;
    }
}
