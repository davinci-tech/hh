package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ifl;
import defpackage.igo;
import defpackage.ijo;
import defpackage.ikr;
import defpackage.iks;
import defpackage.iwg;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiNewStressStat extends HiStatCommon {

    /* renamed from: a, reason: collision with root package name */
    private ikr f4211a;
    private iks b;
    private ijo c;

    public HiNewStressStat(Context context) {
        super(context);
        this.f4211a = ikr.b(this.mContext);
        this.b = iks.e();
        this.c = ijo.b(this.mContext);
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        int userId = hiHealthData.getUserId();
        int e = this.f4211a.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("Debug_HiNewStressStat", "stat() statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.b.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiNewStressStat", "stat() statClients <= 0 day");
            return false;
        }
        return b(a2, e, userId, hiHealthData);
    }

    private boolean b(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        List<HiHealthData> b = b(t, f, list);
        List<HiHealthData> d = d(t, f, list);
        boolean d2 = HiCommonUtil.d(b);
        boolean d3 = HiCommonUtil.d(d);
        if (d2 || d3) {
            LogUtil.h("Debug_HiNewStressStat", "saveScoreStat query stat is empty!");
            return iwg.d(HiDateUtil.c(t), i, 2034, HiHealthDataType.i());
        }
        igo igoVar = new igo();
        igoVar.d(t);
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(2034);
        igoVar.b(i);
        HiHealthData hiHealthData2 = d.get(0);
        a(igoVar, hiHealthData2.getDouble("stress_score_max"), 44305);
        a(igoVar, hiHealthData2.getDouble("stress_score_min"), 44304);
        a(igoVar, hiHealthData2.getDouble("stress_score_avg"), 44306);
        a(igoVar, b.get(0).getValue(), 44307);
        return a(igoVar, hiHealthData2.getDouble("stress_score_count"), 44308);
    }

    private List<HiHealthData> b(long j, long j2, List<Integer> list) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        return this.c.d(hiDataReadOption, 2034, list, (ifl) null);
    }

    private List<HiHealthData> d(long j, long j2, List<Integer> list) {
        return this.c.b(list, j, j2, 3, 2034, new String[]{"stress_score_count", "stress_score_max", "stress_score_min", "stress_score_avg"}, new int[]{2, 4, 5, 3}, 0);
    }

    private boolean a(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiNewStressStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        if (i == 44306) {
            d = Math.round(d);
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
