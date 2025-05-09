package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ijj;
import defpackage.ikr;
import defpackage.iks;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiPpgStat extends HiStatCommon {
    private ijj b;
    private ikr d;
    private iks e;

    public HiPpgStat(Context context) {
        super(context);
        this.d = ikr.b(this.mContext);
        this.e = iks.e();
        this.b = ijj.a(this.mContext);
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        int userId = hiHealthData.getUserId();
        int e = this.d.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("HiH_HiPpgStat", "statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.e.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiPpgStat", "HiPpgStat() statClients <= 0");
            return false;
        }
        return e(a2, e, userId, hiHealthData);
    }

    private boolean e(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        List<HiHealthData> c = this.b.c(list, t, HiDateUtil.f(hiHealthData.getStartTime()), hiHealthData.getType());
        if (koq.b(c)) {
            LogUtil.h("HiH_HiPpgStat", "saveStat datas is null or empty");
            return false;
        }
        int[] d = d(c);
        int[] g = HiHealthDataType.g();
        if (d.length != g.length) {
            LogUtil.h("HiH_HiPpgStat", "saveStat length difference");
            return false;
        }
        igo igoVar = new igo();
        igoVar.b(i);
        igoVar.h(16);
        igoVar.j(i2);
        igoVar.d(t);
        igoVar.g(hiHealthData.getSyncStatus());
        for (int i3 = 0; i3 < g.length; i3++) {
            b(igoVar, d[i3], g[i3]);
        }
        return true;
    }

    private int[] d(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (it.hasNext()) {
            int intValue = it.next().getIntValue();
            if (intValue == 1) {
                i++;
            } else if (intValue == 2) {
                i2++;
            } else if (intValue == 3) {
                i3++;
            } else if (intValue == 4) {
                i4++;
            } else if (intValue == 5) {
                i5++;
            }
        }
        return new int[]{i, i2, i3, i4, i5};
    }

    private boolean b(igo igoVar, double d, int i) {
        if (Double.compare(d, 1.0E-6d) <= 0) {
            LogUtil.h("HiH_HiPpgStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
