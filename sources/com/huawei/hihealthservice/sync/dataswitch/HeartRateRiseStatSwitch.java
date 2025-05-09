package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.HeartRateRiseAlarmBasic;
import com.huawei.hwcloudmodel.model.unite.HeartRateRiseAlarmTotal;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HeartRateRiseStatSwitch {
    private Context e;

    public HeartRateRiseStatSwitch(Context context) {
        this.e = context.getApplicationContext();
    }

    public List<igo> c(List<HeartRateRiseAlarmTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<HeartRateRiseAlarmTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> e = e(it.next(), i);
            if (e != null && !e.isEmpty()) {
                arrayList.addAll(e);
            }
        }
        return arrayList;
    }

    public List<igo> e(HeartRateRiseAlarmTotal heartRateRiseAlarmTotal, int i) {
        if (heartRateRiseAlarmTotal.getDeviceCode() != null && heartRateRiseAlarmTotal.getDeviceCode().longValue() != 0) {
            LogUtil.c("HiH_HeartRateRiseStatSwitch", "the ExerciseIntensityTotal's deviceCode should be 0, deviceCode is ", heartRateRiseAlarmTotal.getDeviceCode());
            return null;
        }
        ikv a2 = ikr.b(this.e).a(0, i, 0);
        if (a2 == null) {
            LogUtil.c("HiH_HeartRateRiseStatSwitch", "cloudHeartRateRiseToLocal statClient is null");
            return null;
        }
        List<igo> d = d(heartRateRiseAlarmTotal.getHeartRateRiseAlarmBasic());
        if (koq.b(d)) {
            LogUtil.c("HiH_HeartRateRiseStatSwitch", "cloudHeartRateRiseToLocal localStats is null or empty");
            return null;
        }
        int intValue = heartRateRiseAlarmTotal.getRecordDay().intValue();
        int b = a2.b();
        String timeZone = heartRateRiseAlarmTotal.getTimeZone();
        long longValue = heartRateRiseAlarmTotal.getGenerateTime().longValue();
        for (igo igoVar : d) {
            igoVar.b(b);
            igoVar.e(intValue);
            igoVar.b(timeZone);
            igoVar.g(1);
            igoVar.c(47001);
            igoVar.a(longValue);
        }
        return d;
    }

    private List<igo> d(HeartRateRiseAlarmBasic heartRateRiseAlarmBasic) {
        if (heartRateRiseAlarmBasic == null) {
            LogUtil.c("HiH_HeartRateRiseStatSwitch", "getLocalHeartRateRiseBasic basic is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(4);
        Integer duration = heartRateRiseAlarmBasic.getDuration();
        Integer maxHeartRate = heartRateRiseAlarmBasic.getMaxHeartRate();
        Integer minHeartRate = heartRateRiseAlarmBasic.getMinHeartRate();
        Integer alarmTimes = heartRateRiseAlarmBasic.getAlarmTimes();
        if (duration != null && duration.intValue() > 0) {
            arrayList.add(iup.d(47004, duration.intValue(), 15));
        }
        if (maxHeartRate != null && maxHeartRate.intValue() > 0) {
            arrayList.add(iup.d(47003, maxHeartRate.intValue(), 8));
        }
        if (minHeartRate != null && minHeartRate.intValue() > 0) {
            arrayList.add(iup.d(47002, minHeartRate.intValue(), 8));
        }
        if (alarmTimes != null && alarmTimes.intValue() > 0) {
            arrayList.add(iup.d(47005, alarmTimes.intValue(), 16));
        }
        return arrayList;
    }

    public List<HeartRateRiseAlarmTotal> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            HeartRateRiseAlarmTotal heartRateRiseAlarmTotal = new HeartRateRiseAlarmTotal();
            heartRateRiseAlarmTotal.setTimeZone(hiHealthData.getTimeZone());
            heartRateRiseAlarmTotal.setGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            heartRateRiseAlarmTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            heartRateRiseAlarmTotal.setDataSource(2);
            heartRateRiseAlarmTotal.setDeviceCode(0L);
            HeartRateRiseAlarmBasic a2 = a(hiHealthData);
            if (a2 != null) {
                heartRateRiseAlarmTotal.setHeartRateRiseAlarmBasic(a2);
                arrayList.add(heartRateRiseAlarmTotal);
            }
        }
        return arrayList;
    }

    private HeartRateRiseAlarmBasic a(HiHealthData hiHealthData) {
        HeartRateRiseAlarmBasic heartRateRiseAlarmBasic = new HeartRateRiseAlarmBasic();
        int i = hiHealthData.getInt("heart_rate_rise_duration");
        int i2 = hiHealthData.getInt("heart_rate_rise_max");
        int i3 = hiHealthData.getInt("heart_rate_rise_min");
        int i4 = hiHealthData.getInt("heart_rate_rise_alarmtimes");
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0) {
            LogUtil.d("HiH_HeartRateRiseStatSwitch", "error HeartRateRise stat, some stat <= 0");
            return null;
        }
        heartRateRiseAlarmBasic.setDuration(Integer.valueOf(i));
        heartRateRiseAlarmBasic.setMaxHeartRate(Integer.valueOf(i2));
        heartRateRiseAlarmBasic.setMinHeartRate(Integer.valueOf(i3));
        heartRateRiseAlarmBasic.setAlarmTimes(Integer.valueOf(i4));
        return heartRateRiseAlarmBasic;
    }
}
