package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.ifl;
import defpackage.igo;
import defpackage.iiy;
import defpackage.ikr;
import defpackage.iks;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiExerciseIntensityStat extends HiStatCommon {
    private iiy b;
    private ikr c;
    private iks e;

    public HiExerciseIntensityStat(Context context) {
        super(context);
        this.b = iiy.e(this.mContext);
        this.c = ikr.b(this.mContext);
        this.e = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        int userId = hiHealthData.getUserId();
        int e = this.c.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("HiH_HiExerciseIntensityStat", "stat() statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.e.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiExerciseIntensityStat", "stat() statClients <= 0");
            return false;
        }
        return a(a2, e, userId, hiHealthData);
    }

    private boolean a(List<Integer> list, int i, int i2, HiHealthData hiHealthData) {
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(t, f);
        List<HiHealthData> d = this.b.d(hiDataReadOption, 7, list, (ifl) null);
        if (d == null || d.isEmpty()) {
            return false;
        }
        boolean a2 = a(a(i, i2, hiHealthData, t, d), d.size(), 47101);
        if (a(hiHealthData)) {
            LogUtil.a("Debug_HiExerciseIntensityStat", "saveUserStat() send intensity change broadcast");
            HiBroadcastUtil.c(this.mContext, 8);
        }
        return a2;
    }

    private boolean a(HiHealthData hiHealthData) {
        return hiHealthData.getDay() == ((long) HiDateUtil.c(System.currentTimeMillis())) && (hiHealthData.getDeviceUuid() != null && !FoundationCommonUtil.getAndroidId(this.mContext).equals(hiHealthData.getDeviceUuid())) && (hiHealthData.getType() > 47100 && hiHealthData.getType() < 47199);
    }

    private igo a(int i, int i2, HiHealthData hiHealthData, long j, List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (it.hasNext()) {
            switch (it.next().getIntValue()) {
                case 1:
                    i3++;
                    break;
                case 2:
                    i4++;
                    break;
                case 3:
                    i5++;
                    break;
                case 4:
                    i6++;
                    break;
                case 5:
                    i7++;
                    break;
                case 6:
                    i8++;
                    break;
                case 7:
                    i9++;
                    break;
                case 8:
                    i10++;
                    break;
            }
        }
        igo igoVar = new igo();
        c(i, i2, hiHealthData, j, igoVar);
        b(i3, i4, i5, i6, igoVar);
        d(i7, i8, i9, igoVar);
        a(igoVar, i10, 47109);
        return igoVar;
    }

    private void d(int i, int i2, int i3, igo igoVar) {
        a(igoVar, i, 47106);
        a(igoVar, i2, 47107);
        a(igoVar, i3, 47108);
    }

    private void b(int i, int i2, int i3, int i4, igo igoVar) {
        a(igoVar, i, 47102);
        a(igoVar, i2, 47103);
        a(igoVar, i3, 47104);
        a(igoVar, i4, 47105);
    }

    private void c(int i, int i2, HiHealthData hiHealthData, long j, igo igoVar) {
        igoVar.d(j);
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(2034);
        igoVar.b(i);
        igoVar.h(15);
    }

    private boolean a(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiExerciseIntensityStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
