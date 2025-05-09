package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.igo;
import defpackage.iit;
import defpackage.iiu;
import defpackage.ikr;
import defpackage.iks;
import defpackage.iwj;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class HiConfigDataStat extends HiStatCommon {

    /* renamed from: a, reason: collision with root package name */
    private iiu f4208a;
    private ikr b;
    private int c;
    private iks d;
    private iit e;
    private String j;

    public HiConfigDataStat(Context context) {
        super(context);
        this.b = ikr.b(this.mContext);
        this.d = iks.e();
        this.e = iit.d();
        this.f4208a = iiu.c();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        int a2;
        if (hiHealthData == null) {
            return false;
        }
        LogUtil.c("Debug_HiConfigDataStat", "stat() day = ", Long.valueOf(hiHealthData.getDay()));
        Integer d = HiConfigDataType.d(hiHealthData.getType());
        int intValue = HiConfigDataType.d(d.intValue(), 0) instanceof Integer ? ((Integer) HiConfigDataType.d(d.intValue(), 0)).intValue() : 0;
        Integer num = HiConfigDataType.d(d.intValue(), 1) instanceof Integer ? (Integer) HiConfigDataType.d(d.intValue(), 1) : null;
        Integer num2 = HiConfigDataType.d(d.intValue(), 7) instanceof Integer ? (Integer) HiConfigDataType.d(d.intValue(), 7) : null;
        if (num == null || num2 == null || (a2 = HiConfigDataType.a(hiHealthData.getType())) < 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        String[] strArr = {"dataType"};
        HiAggregateOption d2 = d(a2, t, HiDateUtil.f(hiHealthData.getStartTime()), new int[]{intValue}, strArr);
        int syncStatus = hiHealthData.getSyncStatus();
        int userId = hiHealthData.getUserId();
        igo d3 = d(intValue, t, syncStatus, userId);
        int e = this.b.e(0, userId, 0);
        if (e <= 0) {
            return false;
        }
        List<Integer> a3 = this.d.a(userId);
        d3.b(e);
        this.j = iwj.a(num.intValue());
        String a4 = iwj.a(num2.intValue());
        this.c = hiHealthData.getType();
        c(d2, d3, strArr, a3, a4);
        if (hiHealthData.getDay() == HiDateUtil.c(System.currentTimeMillis()) && hiHealthData.getDeviceUuid() != null && !FoundationCommonUtil.getAndroidId(this.mContext).equals(hiHealthData.getDeviceUuid())) {
            LogUtil.c("Debug_HiConfigDataStat", "data.getDay == currentTimeMillis");
        }
        LogUtil.c("Debug_HiConfigDataStat", "stat() totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    private igo d(int i, long j, int i2, int i3) {
        igo igoVar = new igo();
        igoVar.d(j);
        igoVar.j(i3);
        igoVar.g(i2);
        igoVar.c(i);
        igoVar.h(16);
        return igoVar;
    }

    private HiAggregateOption d(int i, long j, long j2, int[] iArr, String[] strArr) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(i);
        return hiAggregateOption;
    }

    private boolean c(HiAggregateOption hiAggregateOption, igo igoVar, String[] strArr, List<Integer> list, String str) {
        if (HiCommonUtil.d(list)) {
            return true;
        }
        return b(hiAggregateOption, list, strArr, igoVar, str);
    }

    private boolean b(HiAggregateOption hiAggregateOption, List<Integer> list, String[] strArr, igo igoVar, String str) {
        List<HiHealthData> a2 = this.e.a(this.j, list, hiAggregateOption);
        if (a2 == null || a2.size() == 0) {
            return false;
        }
        return a(a2.get(0).getDouble(strArr[0]), igoVar, str);
    }

    private boolean a(double d, igo igoVar, String str) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiConfigDataStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(this.c));
            return false;
        }
        igoVar.a(d);
        igoVar.d(this.c);
        return this.f4208a.d(str, igoVar);
    }
}
