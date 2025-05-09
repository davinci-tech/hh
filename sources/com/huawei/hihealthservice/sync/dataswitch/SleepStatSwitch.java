package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.SleepBasic;
import com.huawei.hwcloudmodel.model.unite.SleepTotal;
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
public class SleepStatSwitch {
    private Context c;

    public SleepStatSwitch(Context context) {
        this.c = context.getApplicationContext();
    }

    public List<igo> d(List<SleepTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<SleepTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> b = b(it.next(), i);
            if (b != null && !b.isEmpty()) {
                arrayList.addAll(b);
            }
        }
        return arrayList;
    }

    public List<igo> b(SleepTotal sleepTotal, int i) {
        if (sleepTotal.getDeviceCode().longValue() != 0) {
            LogUtil.h("Debug_SleepStatSwitch", "the sportTotal's deviceCode should be 0, deviceCode is ", sleepTotal.getDeviceCode());
            return null;
        }
        ikv a2 = ikr.b(this.c).a(0, i, 0);
        if (a2 == null) {
            return null;
        }
        List<igo> b = b(sleepTotal.getSleepBasic());
        if (b != null && !b.isEmpty()) {
            int intValue = sleepTotal.getRecordDay().intValue();
            int b2 = a2.b();
            String timeZone = sleepTotal.getTimeZone();
            long longValue = sleepTotal.getGenerateTime() != null ? sleepTotal.getGenerateTime().longValue() : 0L;
            for (igo igoVar : b) {
                igoVar.b(b2);
                igoVar.e(intValue);
                igoVar.b(timeZone);
                igoVar.g(1);
                igoVar.c(22000);
                igoVar.a(longValue);
            }
        }
        return b;
    }

    private List<igo> b(SleepBasic sleepBasic) {
        if (sleepBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(7);
        int intValue = sleepBasic.getDeepDuration().intValue();
        int intValue2 = sleepBasic.getLightDuration().intValue();
        int intValue3 = sleepBasic.getAwakeDuration().intValue();
        int intValue4 = sleepBasic.getAwakeTimes().intValue();
        int intValue5 = sleepBasic.getTotalDuration().intValue();
        Long loadFallAsleepTime = sleepBasic.loadFallAsleepTime();
        Long loadWakeupTime = sleepBasic.loadWakeupTime();
        if (intValue > 0) {
            arrayList.add(iup.d(44001, intValue * 60, 13));
        }
        if (intValue2 > 0) {
            arrayList.add(iup.d(44002, intValue2 * 60, 13));
        }
        if (intValue3 > 0) {
            arrayList.add(iup.d(44003, intValue3 * 60, 13));
        }
        if (intValue4 > 0) {
            arrayList.add(iup.d(44005, intValue4, 16));
        }
        if (intValue5 > 0) {
            arrayList.add(iup.d(44004, intValue5 * 60, 13));
        }
        if (loadFallAsleepTime != null && 0 < loadFallAsleepTime.longValue()) {
            arrayList.add(iup.d(44008, loadFallAsleepTime.longValue(), 5));
        }
        if (loadWakeupTime != null && 0 < loadWakeupTime.longValue()) {
            arrayList.add(iup.d(44009, loadWakeupTime.longValue(), 5));
        }
        return arrayList;
    }

    public List<SleepTotal> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            SleepTotal sleepTotal = new SleepTotal();
            sleepTotal.setTimeZone(hiHealthData.getTimeZone());
            sleepTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            sleepTotal.setDataSource(2);
            sleepTotal.setGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            SleepBasic a2 = a(hiHealthData);
            if (a2 != null) {
                sleepTotal.setSleepBasic(a2);
                arrayList.add(sleepTotal);
            }
        }
        return arrayList;
    }

    private SleepBasic a(HiHealthData hiHealthData) {
        long j = hiHealthData.getLong("stat_sleep_start_time");
        long j2 = hiHealthData.getLong("stat_sleep_end_time");
        if (j < 1 || j2 < 1) {
            LogUtil.h("Debug_SleepStatSwitch", "getSleepBasic sleep data error ! hiHealthData is ", hiHealthData);
            return null;
        }
        SleepBasic sleepBasic = new SleepBasic();
        sleepBasic.setStartTime(Long.valueOf(j));
        sleepBasic.setEndTime(Long.valueOf(j2));
        sleepBasic.setDeepDuration(Integer.valueOf(hiHealthData.getInt("stat_sleep_deep_duration") / 60));
        sleepBasic.setLightDuration(Integer.valueOf(hiHealthData.getInt("stat_sleep_shallow_duration") / 60));
        sleepBasic.setAwakeDuration(Integer.valueOf(hiHealthData.getInt("stat_sleep_wake_duration") / 60));
        sleepBasic.setAwakeTimes(Integer.valueOf(hiHealthData.getInt("stat_sleep_wake_count")));
        sleepBasic.setTotalDuration(Integer.valueOf(hiHealthData.getInt("stat_sleep_duration_sum") / 60));
        long j3 = hiHealthData.getLong("stat_sleep_regular_start_time");
        if (0 != j3) {
            sleepBasic.saveFallAsleepTime(Long.valueOf(j3));
        }
        long j4 = hiHealthData.getLong("stat_sleep_regular_end_time");
        if (0 != j4) {
            sleepBasic.saveWakeupTime(Long.valueOf(j4));
        }
        return sleepBasic;
    }
}
