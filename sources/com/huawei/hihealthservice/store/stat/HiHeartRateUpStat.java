package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiHearRateUpMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ijj;
import defpackage.ikr;
import defpackage.iks;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiHeartRateUpStat extends HiStatCommon {
    private ikr b;
    private ijj c;
    private iks d;

    public HiHeartRateUpStat(Context context) {
        super(context);
        this.b = ikr.b(this.mContext);
        this.d = iks.e();
        this.c = ijj.a(this.mContext);
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        int userId = hiHealthData.getUserId();
        int e = this.b.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("Debug_HiHeartRateUpStat", "HiHeartRateUpStat() statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.d.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiHeartRateUpStat", "HiHeartRateUpStat() statClients <= 0");
            return false;
        }
        return a(a2, e, userId, hiHealthData);
    }

    private boolean a(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        int type = hiHealthData.getType();
        List<HiHealthData> c = this.c.c(list, t, f, type);
        if (c == null || c.isEmpty()) {
            LogUtil.a("Debug_HiHeartRateUpStat", "saveStat datas is null or empty");
            return false;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        for (HiHealthData hiHealthData2 : c) {
            i3 = (int) (i3 + ((hiHealthData2.getEndTime() - hiHealthData2.getStartTime()) / 1000));
            HiHearRateUpMetaData hiHearRateUpMetaData = (HiHearRateUpMetaData) HiJsonUtil.e(hiHealthData2.getMetaData(), HiHearRateUpMetaData.class);
            if (i5 < hiHearRateUpMetaData.getMaxHeartRate()) {
                i5 = hiHearRateUpMetaData.getMaxHeartRate();
            }
            if (i6 > hiHearRateUpMetaData.getMinHeartRate()) {
                i6 = hiHearRateUpMetaData.getMinHeartRate();
            }
            i4++;
        }
        if (i5 == Integer.MIN_VALUE) {
            i5 = 0;
        }
        int i7 = i6 != Integer.MAX_VALUE ? i6 : 0;
        igo igoVar = new igo();
        igoVar.b(i);
        igoVar.h(8);
        igoVar.j(i2);
        igoVar.d(t);
        igoVar.g(hiHealthData.getSyncStatus());
        if (type == 2101) {
            igoVar.c(47001);
            a(igoVar, i5, 47003);
            a(igoVar, i7, 47002);
            a(igoVar, i3, 47004);
            a(igoVar, i4, 47005);
            return true;
        }
        if (type == 2102) {
            igoVar.c(47051);
            a(igoVar, i5, 47053);
            a(igoVar, i7, 47052);
            a(igoVar, i3, 47054);
            a(igoVar, i4, 47055);
            return true;
        }
        LogUtil.h("Debug_HiHeartRateUpStat", "saveOneStat is other condition");
        return true;
    }

    private boolean a(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiHeartRateUpStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
