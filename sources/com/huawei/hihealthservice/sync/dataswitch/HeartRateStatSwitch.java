package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.HeartRateBasic;
import com.huawei.hwcloudmodel.model.unite.HeartRateTotal;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HeartRateStatSwitch {
    private Context e;

    public HeartRateStatSwitch(Context context) {
        this.e = context.getApplicationContext();
    }

    public List<igo> d(List<HeartRateTotal> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<HeartRateTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> c = c(it.next(), i);
            if (c != null && !c.isEmpty()) {
                arrayList.addAll(c);
            }
        }
        return arrayList;
    }

    public List<igo> c(HeartRateTotal heartRateTotal, int i) {
        List<igo> d;
        if (heartRateTotal.getDeviceCode() != 0) {
            LogUtil.h("Debug_HeartRateStatSwitch", "the heartRateTotal's deviceCode should be 0, deviceCode is ", Long.valueOf(heartRateTotal.getDeviceCode()));
            return null;
        }
        ikv a2 = ikr.b(this.e).a(0, i, 0);
        if (a2 == null || (d = d(heartRateTotal.getHeartRateBasic())) == null || d.isEmpty()) {
            return null;
        }
        int recordDay = heartRateTotal.getRecordDay();
        int b = a2.b();
        String timeZone = heartRateTotal.getTimeZone();
        long generateTime = heartRateTotal.getGenerateTime();
        for (igo igoVar : d) {
            igoVar.b(b);
            igoVar.e(recordDay);
            igoVar.b(timeZone);
            igoVar.g(1);
            igoVar.c(46015);
            igoVar.a(generateTime);
        }
        return d;
    }

    private List<igo> d(HeartRateBasic heartRateBasic) {
        if (heartRateBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        int maxHeartRate = heartRateBasic.getMaxHeartRate();
        int minHeartRate = heartRateBasic.getMinHeartRate();
        int avgRestingHeartRate = heartRateBasic.getAvgRestingHeartRate();
        int lastHeartRate = heartRateBasic.getLastHeartRate();
        int lastRestHeartRate = heartRateBasic.getLastRestHeartRate();
        if (maxHeartRate > 0) {
            arrayList.add(iup.d(46016, maxHeartRate, 8));
        }
        if (minHeartRate > 0) {
            arrayList.add(iup.d(46017, minHeartRate, 8));
        }
        if (avgRestingHeartRate > 0) {
            arrayList.add(iup.d(46018, avgRestingHeartRate, 8));
        }
        if (lastHeartRate > 0) {
            arrayList.add(iup.d(46019, lastHeartRate, 8));
        }
        if (lastRestHeartRate > 0) {
            arrayList.add(iup.d(46020, lastRestHeartRate, 8));
        }
        return arrayList;
    }

    public List<HeartRateTotal> e(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            HeartRateTotal heartRateTotal = new HeartRateTotal();
            heartRateTotal.setTimeZone(hiHealthData.getTimeZone());
            heartRateTotal.setGenerateTime(hiHealthData.getLong("modified_time"));
            heartRateTotal.setRecordDay(HiDateUtil.c(hiHealthData.getStartTime()));
            heartRateTotal.setDataSource(2);
            heartRateTotal.setDeviceCode(0L);
            HeartRateBasic e = e(hiHealthData);
            if (e != null) {
                heartRateTotal.setHeartRateBasic(e);
                arrayList.add(heartRateTotal);
            }
        }
        return arrayList;
    }

    private HeartRateBasic e(HiHealthData hiHealthData) {
        HeartRateBasic heartRateBasic = new HeartRateBasic();
        heartRateBasic.setMaxHeartRate(hiHealthData.getInt("maxHeartRate"));
        heartRateBasic.setMinHeartRate(hiHealthData.getInt("minHeartRate"));
        heartRateBasic.setAvgRestingHeartRate((int) Math.round(hiHealthData.getDouble("avgRestingHeartRate")));
        heartRateBasic.setLastHeartRate(hiHealthData.getInt("lastHeartRate"));
        heartRateBasic.setLastRestHeartRate(hiHealthData.getInt("lastRestHeartRate"));
        return heartRateBasic;
    }
}
