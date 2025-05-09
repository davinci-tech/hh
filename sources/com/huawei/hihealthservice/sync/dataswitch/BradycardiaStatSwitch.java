package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.BradycardiaAlarmBasic;
import com.huawei.hwcloudmodel.model.unite.BradycardiaAlarmTotal;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class BradycardiaStatSwitch {
    private Context e;

    public BradycardiaStatSwitch(Context context) {
        if (context != null) {
            this.e = context.getApplicationContext();
        }
    }

    public List<igo> c(List<BradycardiaAlarmTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<BradycardiaAlarmTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> b = b(it.next(), i);
            if (b != null && !b.isEmpty()) {
                arrayList.addAll(b);
            }
        }
        return arrayList;
    }

    public List<igo> b(BradycardiaAlarmTotal bradycardiaAlarmTotal, int i) {
        if (bradycardiaAlarmTotal.getDeviceCode() != null && bradycardiaAlarmTotal.getDeviceCode().longValue() != 0) {
            LogUtil.h("HiH_BradycardiaStatSwitch", "the ExerciseIntensityTotal's deviceCode should be 0, deviceCode is ", bradycardiaAlarmTotal.getDeviceCode());
            return null;
        }
        ikv a2 = ikr.b(this.e).a(0, i, 0);
        if (a2 == null) {
            LogUtil.h("HiH_BradycardiaStatSwitch", "cloudBradycardiaStatToLocal statClient is null");
            return null;
        }
        List<igo> e = e(bradycardiaAlarmTotal.getBradycardiaAlarmBasic());
        if (koq.b(e)) {
            LogUtil.h("HiH_BradycardiaStatSwitch", "cloudBradycardiaStatToLocal localStats is null or empty");
            return e;
        }
        int intValue = bradycardiaAlarmTotal.getRecordDay().intValue();
        int b = a2.b();
        String timeZone = bradycardiaAlarmTotal.getTimeZone();
        long longValue = bradycardiaAlarmTotal.getGenerateTime().longValue();
        for (igo igoVar : e) {
            igoVar.b(b);
            igoVar.e(intValue);
            igoVar.b(timeZone);
            igoVar.g(1);
            igoVar.c(47051);
            igoVar.a(longValue);
        }
        return e;
    }

    private List<igo> e(BradycardiaAlarmBasic bradycardiaAlarmBasic) {
        if (bradycardiaAlarmBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(4);
        int intValue = bradycardiaAlarmBasic.getDuration().intValue();
        int intValue2 = bradycardiaAlarmBasic.getMaxHeartRate().intValue();
        int intValue3 = bradycardiaAlarmBasic.getMinHeartRate().intValue();
        int intValue4 = bradycardiaAlarmBasic.getAlarmTimes().intValue();
        if (intValue > 0) {
            arrayList.add(iup.d(47054, intValue, 15));
        }
        if (intValue2 > 0) {
            arrayList.add(iup.d(47053, intValue2, 8));
        }
        if (intValue3 > 0) {
            arrayList.add(iup.d(47052, intValue3, 8));
        }
        if (intValue4 > 0) {
            arrayList.add(iup.d(47055, intValue4, 16));
        }
        return arrayList;
    }

    public List<BradycardiaAlarmTotal> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            BradycardiaAlarmTotal bradycardiaAlarmTotal = new BradycardiaAlarmTotal();
            bradycardiaAlarmTotal.setTimeZone(hiHealthData.getTimeZone());
            bradycardiaAlarmTotal.setGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            bradycardiaAlarmTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            bradycardiaAlarmTotal.setDataSource(2);
            bradycardiaAlarmTotal.setDeviceCode(0L);
            BradycardiaAlarmBasic c = c(hiHealthData);
            if (c != null) {
                bradycardiaAlarmTotal.setBradycardiaAlarmBasic(c);
                arrayList.add(bradycardiaAlarmTotal);
            }
        }
        return arrayList;
    }

    private BradycardiaAlarmBasic c(HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("heart_rate_bradycardia_duration");
        int i2 = hiHealthData.getInt("heart_rate_bradycardia_max");
        int i3 = hiHealthData.getInt("heart_rate_bradycardia_min");
        int i4 = hiHealthData.getInt("heart_rate_bradycardia_alarmtimes");
        if (i <= 0 || i2 <= 0 || i3 <= 0 || i4 <= 0) {
            LogUtil.a("HiH_BradycardiaStatSwitch", "error HeartRateRise stat, some stat <= 0");
            return null;
        }
        BradycardiaAlarmBasic bradycardiaAlarmBasic = new BradycardiaAlarmBasic();
        bradycardiaAlarmBasic.setDuration(Integer.valueOf(i));
        bradycardiaAlarmBasic.setMaxHeartRate(Integer.valueOf(i2));
        bradycardiaAlarmBasic.setMinHeartRate(Integer.valueOf(i3));
        bradycardiaAlarmBasic.setAlarmTimes(Integer.valueOf(i4));
        return bradycardiaAlarmBasic;
    }
}
