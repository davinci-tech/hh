package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.MenstrualBasic;
import com.huawei.hwcloudmodel.model.unite.MenstrualTotal;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class MenstrualStatSwitch {
    private Context b;

    public MenstrualStatSwitch(Context context) {
        if (context != null) {
            this.b = context.getApplicationContext();
        } else {
            this.b = BaseApplication.getContext();
        }
    }

    public List<MenstrualTotal> c(List<HiHealthData> list) {
        if (koq.b(list)) {
            return new ArrayList(1);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            MenstrualTotal menstrualTotal = new MenstrualTotal();
            menstrualTotal.setTimeZone(hiHealthData.getTimeZone());
            menstrualTotal.setGenerateTime(Long.valueOf(hiHealthData.getLong("modified_time")));
            menstrualTotal.setRecordDay(Integer.valueOf(HiDateUtil.c(hiHealthData.getStartTime())));
            menstrualTotal.setDataSource(2);
            menstrualTotal.setDeviceCode(0L);
            menstrualTotal.setMenstrualBasic(e(hiHealthData));
            arrayList.add(menstrualTotal);
        }
        return arrayList;
    }

    private MenstrualBasic e(HiHealthData hiHealthData) {
        MenstrualBasic menstrualBasic = new MenstrualBasic();
        menstrualBasic.setStatus(Integer.valueOf(hiHealthData.getInt("menstrual_status")));
        menstrualBasic.setSubStatus(Integer.valueOf(hiHealthData.getInt("menstrual_sub_status")));
        menstrualBasic.setDysmenorrhea(Integer.valueOf(hiHealthData.getInt("menstrual_dysmenorrhea")));
        if (menstrualBasic.getDysmenorrhea().intValue() > 10) {
            menstrualBasic.setDysmenorrhea(3);
        }
        menstrualBasic.setMenstrualQuantity(Integer.valueOf(hiHealthData.getInt("menstrual_quantity")));
        if (menstrualBasic.getMenstrualQuantity().intValue() > 10) {
            menstrualBasic.setMenstrualQuantity(3);
        }
        menstrualBasic.setPhysicalStatus(Integer.valueOf(hiHealthData.getInt("menstrual_physical")));
        return menstrualBasic;
    }

    public List<igo> b(MenstrualTotal menstrualTotal, int i) {
        if (menstrualTotal == null || menstrualTotal.getDeviceCode().longValue() != 0) {
            LogUtil.h("MenstrualStatSwitch", "cloudMenstrualStatToLocal menstrualTotal null or deviceCode not 0");
            return null;
        }
        ikv a2 = ikr.b(this.b).a(0, i, 0);
        if (a2 == null) {
            return null;
        }
        List<igo> c = c(menstrualTotal.getMenstrualBasic());
        if (koq.b(c)) {
            return null;
        }
        int intValue = menstrualTotal.getRecordDay().intValue();
        int b = a2.b();
        String timeZone = menstrualTotal.getTimeZone();
        long longValue = menstrualTotal.getGenerateTime().longValue();
        for (igo igoVar : c) {
            igoVar.b(b);
            igoVar.e(intValue);
            igoVar.b(timeZone);
            igoVar.g(1);
            igoVar.a(longValue);
        }
        return c;
    }

    private List<igo> c(MenstrualBasic menstrualBasic) {
        if (menstrualBasic == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        int intValue = menstrualBasic.getStatus().intValue();
        Integer subStatus = menstrualBasic.getSubStatus();
        int intValue2 = menstrualBasic.getDysmenorrhea().intValue();
        int intValue3 = menstrualBasic.getMenstrualQuantity().intValue();
        int intValue4 = menstrualBasic.getPhysicalStatus().intValue();
        if (intValue >= 0) {
            arrayList.add(iup.d(47401, intValue, 0));
        }
        if (subStatus != null && subStatus.intValue() >= 0) {
            arrayList.add(iup.d(47405, subStatus.intValue(), 0));
        }
        if (intValue2 >= 0) {
            arrayList.add(iup.d(47403, intValue2, 0));
        }
        if (intValue3 >= 0) {
            arrayList.add(iup.d(47402, intValue3, 0));
        }
        if (intValue4 >= 0) {
            arrayList.add(iup.d(47404, intValue4, 0));
        }
        return arrayList;
    }
}
