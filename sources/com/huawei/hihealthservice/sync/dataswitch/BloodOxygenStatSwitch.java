package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.BloodOxygenSaturationBasic;
import com.huawei.hwcloudmodel.model.unite.BloodOxygenSaturationTotal;
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
public class BloodOxygenStatSwitch {
    private Context c;

    public BloodOxygenStatSwitch(Context context) {
        this.c = context.getApplicationContext();
    }

    public List<igo> b(List<BloodOxygenSaturationTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<BloodOxygenSaturationTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> d = d(it.next(), i);
            if (d != null && !d.isEmpty()) {
                arrayList.addAll(d);
            }
        }
        return arrayList;
    }

    public List<igo> d(BloodOxygenSaturationTotal bloodOxygenSaturationTotal, int i) {
        List<igo> e;
        if (bloodOxygenSaturationTotal.getDeviceCode().longValue() != 0) {
            LogUtil.h("Debug_BloodOxygenStatSwitch", "the bloodOxygenSaturationTotal's deviceCode should be 0, deviceCode is ", bloodOxygenSaturationTotal.getDeviceCode());
            return null;
        }
        ikv a2 = ikr.b(this.c).a(0, i, 0);
        if (a2 == null || (e = e(bloodOxygenSaturationTotal.getBloodOxygenSaturationBasic())) == null || e.isEmpty()) {
            return null;
        }
        int intValue = bloodOxygenSaturationTotal.getRecordDay().intValue();
        int b = a2.b();
        String timeZone = bloodOxygenSaturationTotal.getTimeZone();
        long longValue = bloodOxygenSaturationTotal.getGenerateTime().longValue();
        for (igo igoVar : e) {
            igoVar.b(b);
            igoVar.e(intValue);
            igoVar.b(timeZone);
            igoVar.g(1);
            igoVar.c(47200);
            igoVar.a(longValue);
        }
        return e;
    }

    private List<igo> e(BloodOxygenSaturationBasic bloodOxygenSaturationBasic) {
        if (bloodOxygenSaturationBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(4);
        double maxSaturation = bloodOxygenSaturationBasic.getMaxSaturation();
        double minSaturation = bloodOxygenSaturationBasic.getMinSaturation();
        double avgSaturation = bloodOxygenSaturationBasic.getAvgSaturation();
        double lastSaturation = bloodOxygenSaturationBasic.getLastSaturation();
        if (maxSaturation > 0.0d) {
            arrayList.add(iup.d(47201, maxSaturation, 18));
        }
        if (minSaturation > 0.0d) {
            arrayList.add(iup.d(47202, minSaturation, 18));
        }
        if (avgSaturation > 0.0d) {
            arrayList.add(iup.d(47203, avgSaturation, 18));
        }
        if (lastSaturation > 0.0d) {
            arrayList.add(iup.d(47204, lastSaturation, 18));
        }
        return arrayList;
    }

    public List<BloodOxygenSaturationTotal> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            BloodOxygenSaturationTotal bloodOxygenSaturationTotal = new BloodOxygenSaturationTotal();
            bloodOxygenSaturationTotal.setTimeZone(hiHealthData.getTimeZone());
            bloodOxygenSaturationTotal.setGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            bloodOxygenSaturationTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            bloodOxygenSaturationTotal.setDataSource(2);
            bloodOxygenSaturationTotal.setDeviceCode(0L);
            bloodOxygenSaturationTotal.setBloodOxygenSaturationBasic(e(hiHealthData));
            arrayList.add(bloodOxygenSaturationTotal);
        }
        return arrayList;
    }

    private BloodOxygenSaturationBasic e(HiHealthData hiHealthData) {
        BloodOxygenSaturationBasic bloodOxygenSaturationBasic = new BloodOxygenSaturationBasic();
        bloodOxygenSaturationBasic.setMaxSaturation(hiHealthData.getDouble("maxBloodOxygenSaturation"));
        bloodOxygenSaturationBasic.setMinSaturation(hiHealthData.getDouble("minBloodOxygenSaturation"));
        bloodOxygenSaturationBasic.setAvgSaturation(hiHealthData.getDouble("avgBloodOxygenSaturation"));
        bloodOxygenSaturationBasic.setLastSaturation(hiHealthData.getDouble("lastBloodOxygenSaturation"));
        return bloodOxygenSaturationBasic;
    }
}
