package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ijo;
import defpackage.ikr;
import defpackage.iks;
import defpackage.iwg;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiStressAndRelaxtionStat extends HiStatCommon {
    private ijo b;
    private ikr d;
    private iks e;

    public HiStressAndRelaxtionStat(Context context) {
        super(context);
        this.d = ikr.b(this.mContext);
        this.e = iks.e();
        this.b = ijo.b(this.mContext);
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        LogUtil.a("Debug_HiStressAndRelaxtionStat", "stat()");
        return c(hiHealthData);
    }

    private boolean c(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        int userId = hiHealthData.getUserId();
        int e = this.d.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("Debug_HiStressAndRelaxtionStat", "statStressAndRelaxationDataByUser()  statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.e.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiStressAndRelaxtionStat", "statStressAndRelaxationDataByUser()  statClients <= 0");
            return false;
        }
        return a(a2, e, userId, hiHealthData) & b(a2, e, userId, hiHealthData);
    }

    private boolean b(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        String[] strArr = {"stress_score_max", "stress_score_min"};
        int[] iArr = {4, 5};
        List<HiHealthData> b = this.b.b(list, t, f, 3, 2019, strArr, iArr, 0);
        List<HiHealthData> b2 = this.b.b(list, t, f, 3, 2020, strArr, iArr, 0);
        igo igoVar = new igo();
        igoVar.d(t);
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(2020);
        igoVar.h(17);
        igoVar.b(i);
        return b(b, b2, igoVar);
    }

    private boolean a(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        igo igoVar = new igo();
        igoVar.d(t);
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(2021);
        igoVar.h(16);
        igoVar.b(i);
        List<HiHealthData> e = this.b.e(list, t, f, 2021);
        if (koq.b(e)) {
            return iwg.a(igoVar, HiHealthDataType.f());
        }
        long j = 0;
        int i3 = 0;
        for (HiHealthData hiHealthData2 : e) {
            j += (hiHealthData2.getEndTime() - hiHealthData2.getStartTime()) / 1000;
            i3++;
        }
        return b(j, i3, igoVar);
    }

    private boolean b(List<HiHealthData> list, List<HiHealthData> list2, igo igoVar) {
        double d;
        double d2;
        if (HiCommonUtil.d(list) && HiCommonUtil.d(list2)) {
            LogUtil.h("Debug_HiStressAndRelaxtionStat", "saveStressStat()  statDatas is null");
            return iwg.a(igoVar, HiHealthDataType.n());
        }
        if (!HiCommonUtil.d(list)) {
            HiHealthData hiHealthData = list.get(0);
            d = hiHealthData.getDouble("stress_score_max");
            d2 = hiHealthData.getDouble("stress_score_min");
            if (!HiCommonUtil.d(list2)) {
                HiHealthData hiHealthData2 = list2.get(0);
                if (d <= hiHealthData2.getDouble("stress_score_max")) {
                    d = hiHealthData2.getDouble("stress_score_max");
                }
                if (d2 >= hiHealthData2.getDouble("stress_score_min")) {
                    d2 = hiHealthData2.getDouble("stress_score_min");
                }
            }
        } else {
            HiHealthData hiHealthData3 = list2.get(0);
            d = hiHealthData3.getDouble("stress_score_max");
            d2 = hiHealthData3.getDouble("stress_score_min");
        }
        return c(igoVar, d, 44399) && c(igoVar, d2, 44300);
    }

    private boolean b(long j, int i, igo igoVar) {
        if (i == 0 || 0 == j) {
            LogUtil.h("Debug_HiStressAndRelaxtionStat", "saveRelaxationStat() toatltime or totalcount is 0");
            return false;
        }
        boolean c = c(igoVar, i, 44303);
        igoVar.h(15);
        return c && c(igoVar, ((double) j) / 60.0d, 44302);
    }

    private boolean c(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiStressAndRelaxtionStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
