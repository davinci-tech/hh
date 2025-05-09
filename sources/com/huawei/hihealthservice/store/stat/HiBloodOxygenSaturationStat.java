package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ifl;
import defpackage.igo;
import defpackage.ikr;
import defpackage.iks;
import defpackage.ivu;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiBloodOxygenSaturationStat extends HiStatCommon {
    private ikr b;
    private iks d;

    public HiBloodOxygenSaturationStat(Context context) {
        super(context, "hihealth_sensitive.db");
        this.b = ikr.b(this.mContext);
        this.d = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        LogUtil.c("Debug_HiBloodOxygenSaturationStat", "stat()");
        return d(hiHealthData);
    }

    private boolean d(HiHealthData hiHealthData) {
        int userId = hiHealthData.getUserId();
        int e = this.b.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("Debug_HiBloodOxygenSaturationStat", "statHeartRateDataByUser()  statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.d.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiBloodOxygenSaturationStat", "statHeartRateDataByUser()  statClients <= 0");
            return false;
        }
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        igo igoVar = new igo();
        igoVar.d(t);
        igoVar.j(userId);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(47200);
        igoVar.h(18);
        igoVar.b(e);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(t);
        hiDataReadOption.setEndTime(f);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        return c(ivu.d(this.mContext, 2103).b(hiDataReadOption, 2103, a2, (ifl) null), igoVar) && e(ivu.b(this.mContext, 2103).b(a2, t, f, 3, 2103, new String[]{"avgBloodOxygenSaturation", "maxBloodOxygenSaturation", "minBloodOxygenSaturation"}, new int[]{3, 4, 5}, 0), igoVar);
    }

    private boolean c(List<HiHealthData> list, igo igoVar) {
        if (HiCommonUtil.d(list)) {
            LogUtil.h("Debug_HiBloodOxygenSaturationStat", "saveLastBloodOxygenSaturationStat() statLastDatas are null");
            return false;
        }
        return d(igoVar, list.get(0).getValue(), 47204);
    }

    private boolean e(List<HiHealthData> list, igo igoVar) {
        if (HiCommonUtil.d(list)) {
            LogUtil.h("Debug_HiBloodOxygenSaturationStat", "saveBloodOxygenSaturationStat()  statDatas are null");
            return false;
        }
        return d(igoVar, list.get(0).getDouble("avgBloodOxygenSaturation"), 47203) && d(igoVar, list.get(0).getDouble("maxBloodOxygenSaturation"), 47201) && d(igoVar, list.get(0).getDouble("minBloodOxygenSaturation"), 47202);
    }

    private boolean d(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiBloodOxygenSaturationStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
