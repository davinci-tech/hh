package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.ifl;
import defpackage.igo;
import defpackage.ijt;
import defpackage.ikr;
import defpackage.iks;
import defpackage.isq;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes7.dex */
public class HiSportStat extends HiStatCommon {
    private static final String[] d = {"step", "distance", "calorie", "altitude_offset"};

    /* renamed from: a, reason: collision with root package name */
    private ijt f4212a;
    private iks b;
    private ikr c;
    private int e;
    private int f;
    private int i;

    public HiSportStat(Context context) {
        super(context);
        this.f4212a = ijt.b();
        this.c = ikr.b(this.mContext);
        this.b = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long e = HiDateUtil.e(hiHealthData.getStartTime(), TimeZone.getDefault());
        long b = HiDateUtil.b(hiHealthData.getStartTime(), TimeZone.getDefault());
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(e);
        hiAggregateOption.setEndTime(b);
        hiAggregateOption.setType(new int[]{2, 3, 4, 5});
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(1);
        this.f = hiHealthData.getSyncStatus();
        this.i = hiHealthData.getUserId();
        c(hiAggregateOption, e);
        e(hiHealthData);
        if (d(hiHealthData)) {
            LogUtil.c("Debug_HiSportStat", "stat() send  stepSum change broadcast");
            HiBroadcastUtil.i(this.mContext);
            HiBroadcastUtil.c(this.mContext, 1);
        }
        LogUtil.c("Debug_HiSportStat", "stat() totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    private boolean d(HiHealthData hiHealthData) {
        return (hiHealthData.getDay() == ((long) HiDateUtil.c(System.currentTimeMillis())) && (hiHealthData.getDeviceUuid() != null && !FoundationCommonUtil.getAndroidId(this.mContext).equals(hiHealthData.getDeviceUuid()))) || (40054 == hiHealthData.getType());
    }

    private void e(HiHealthData hiHealthData) {
        LogUtil.c("Debug_HiSportStat", "saveDeviceDayStepsSum() enter");
        isq.a(hiHealthData);
    }

    private boolean c(HiAggregateOption hiAggregateOption, long j) {
        int e = this.c.e(0, this.i, 0);
        this.e = e;
        if (e <= 0) {
            return false;
        }
        List<Integer> a2 = this.b.a(this.i);
        if (HiCommonUtil.d(a2)) {
            return true;
        }
        e(hiAggregateOption, a2, j);
        return true;
    }

    private boolean e(HiAggregateOption hiAggregateOption, List<Integer> list, long j) {
        hiAggregateOption.setAlignType(20001);
        List<HiHealthData> h = this.f4212a.h(list, hiAggregateOption);
        hiAggregateOption.setGroupUnitType(8);
        e(h, this.f4212a.a(list, hiAggregateOption), j);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAlignType(20002);
        f(this.f4212a.e(list, hiAggregateOption), j);
        hiAggregateOption.setAlignType(20003);
        e(this.f4212a.e(list, hiAggregateOption), j);
        hiAggregateOption.setAlignType(20005);
        d(this.f4212a.e(list, hiAggregateOption), j);
        hiAggregateOption.setAlignType(20004);
        b(this.f4212a.e(list, hiAggregateOption), j);
        hiAggregateOption.setAlignType(20007);
        h(this.f4212a.e(list, hiAggregateOption), j);
        hiAggregateOption.setAlignType(20010);
        c(this.f4212a.e(list, hiAggregateOption), j);
        HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
        hiAggregateOption2.setStartTime(hiAggregateOption.getStartTime());
        hiAggregateOption2.setEndTime(hiAggregateOption.getEndTime());
        hiAggregateOption2.setType(new int[]{20001});
        hiAggregateOption2.setConstantsKey(new String[]{"duration"});
        hiAggregateOption2.setGroupUnitType(3);
        hiAggregateOption2.setAggregateType(1);
        a(this.f4212a.c(list, hiAggregateOption2, (ifl) null), j);
        return true;
    }

    private boolean e(List<HiHealthData> list, List<HiHealthData> list2, long j) {
        if (HiCommonUtil.d(list) || HiCommonUtil.d(list2)) {
            LogUtil.h("Debug_HiSportStat", "saveAllSessionStat()  statDatas or deviceDatas is null");
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        String[] strArr = d;
        double d2 = hiHealthData.getDouble(strArr[0]);
        double d3 = hiHealthData.getDouble(strArr[1]);
        char c = 2;
        double d4 = hiHealthData.getDouble(strArr[2]);
        double d5 = hiHealthData.getDouble(strArr[3]);
        boolean b = b(hiHealthData);
        double d6 = d2;
        for (HiHealthData hiHealthData2 : list2) {
            String[] strArr2 = d;
            double d7 = hiHealthData2.getDouble(strArr2[0]);
            double d8 = hiHealthData2.getDouble(strArr2[1]);
            double d9 = hiHealthData2.getDouble(strArr2[c]);
            double d10 = hiHealthData2.getDouble(strArr2[3]);
            if (b) {
                LogUtil.a("HiH_HiSportStat", "saveSessionStat device Stat");
            }
            if (d6 < d7) {
                d6 = d7;
            }
            if (d3 < d8) {
                d3 = d8;
            }
            if (d4 < d9) {
                d4 = d9;
            }
            if (d5 < d10) {
                d5 = d10;
            }
            c = 2;
        }
        int c2 = HiDateUtil.c(hiHealthData.getStartTime());
        a(j, d6, c2);
        c(j, d3, c2);
        d(j, d4, c2);
        b(j, d5, c2);
        return true;
    }

    private boolean b(HiHealthData hiHealthData) {
        if (HiDateUtil.c(hiHealthData.getStartTime()) != HiDateUtil.c(System.currentTimeMillis())) {
            return false;
        }
        LogUtil.a("HiH_HiSportStat", "isLogFlag saveSessionStat merge");
        return true;
    }

    private void b(long j, double d2, int i) {
        LogUtil.a("Debug_HiSportStat", "saveAllSessionStat()  altitude change = ", Boolean.valueOf(c(j, SmartMsgConstant.MSG_TYPE_RIDE_USER, isq.e(isq.e(this.mContext, this.i, i, SmartMsgConstant.MSG_TYPE_RIDE_USER), d2, this.f), d2, 4)));
    }

    private void d(long j, double d2, int i) {
        LogUtil.a("Debug_HiSportStat", "saveAllSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40003, isq.e(isq.e(this.mContext, this.i, i, 40003), d2, this.f), d2, 3)));
    }

    private void c(long j, double d2, int i) {
        LogUtil.a("Debug_HiSportStat", "saveAllSessionStat()  distance change = ", Boolean.valueOf(c(j, 40004, isq.e(isq.e(this.mContext, this.i, i, 40004), d2, this.f), d2, 2)));
    }

    private void a(long j, double d2, int i) {
        LogUtil.a("Debug_HiSportStat", "saveAllSessionStat()  step change = ", Boolean.valueOf(c(j, 40002, isq.e(isq.e(this.mContext, this.i, i, 40002), d2, this.f), d2, 1)));
    }

    private boolean f(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            LogUtil.h("Debug_HiSportStat", "saveWalkSessionStat()  statDatas is null");
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        int i = this.f;
        String[] strArr = d;
        LogUtil.c("Debug_HiSportStat", "saveWalkSessionStat()  step change = ", Boolean.valueOf(c(j, 40011, i, hiHealthData.getDouble(strArr[0]), 1)));
        LogUtil.c("Debug_HiSportStat", "saveWalkSessionStat()  distance change = ", Boolean.valueOf(c(j, 40031, this.f, hiHealthData.getDouble(strArr[1]), 2)));
        LogUtil.c("Debug_HiSportStat", "saveWalkSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40021, this.f, hiHealthData.getDouble(strArr[2]), 3)));
        return true;
    }

    private boolean e(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        int i = this.f;
        String[] strArr = d;
        LogUtil.c("Debug_HiSportStat", "saveRunSessionStat()  step change = ", Boolean.valueOf(c(j, 40012, i, hiHealthData.getDouble(strArr[0]), 1)));
        LogUtil.c("Debug_HiSportStat", "saveRunSessionStat()  distance change = ", Boolean.valueOf(c(j, 40032, this.f, hiHealthData.getDouble(strArr[1]), 2)));
        LogUtil.c("Debug_HiSportStat", "saveRunSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40022, this.f, hiHealthData.getDouble(strArr[2]), 3)));
        return true;
    }

    private boolean d(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        int i = this.f;
        String[] strArr = d;
        LogUtil.c("Debug_HiSportStat", "saveCycleSessionStat()  distance change = ", Boolean.valueOf(c(j, 40033, i, hiHealthData.getDouble(strArr[1]), 2)));
        LogUtil.c("Debug_HiSportStat", "saveCycleSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40023, this.f, hiHealthData.getDouble(strArr[2]), 3)));
        return true;
    }

    private boolean b(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        int i = this.f;
        String[] strArr = d;
        LogUtil.c("Debug_HiSportStat", "saveClimbSessionStat()  step change = ", Boolean.valueOf(c(j, 40013, i, hiHealthData.getDouble(strArr[0]), 1)));
        LogUtil.c("Debug_HiSportStat", "saveClimbSessionStat()  distance change = ", Boolean.valueOf(c(j, 40034, this.f, hiHealthData.getDouble(strArr[1]), 2)));
        LogUtil.c("Debug_HiSportStat", "saveClimbSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40024, this.f, hiHealthData.getDouble(strArr[2]), 3)));
        return true;
    }

    private boolean h(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        LogUtil.a("Debug_HiSportStat", "saveSwimSessionStat()  statDatas = ", list);
        int i = this.f;
        String[] strArr = d;
        LogUtil.c("Debug_HiSportStat", "saveSwimSessionStat()  distance change = ", Boolean.valueOf(c(j, 40035, i, hiHealthData.getDouble(strArr[1]), 2)));
        LogUtil.c("Debug_HiSportStat", "saveSwimSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40025, this.f, hiHealthData.getDouble(strArr[2]), 3)));
        return true;
    }

    private boolean c(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        HiHealthData hiHealthData = list.get(0);
        LogUtil.a("Debug_HiSportStat", "saveFitnessSessionStat()  statDatas = ", list);
        LogUtil.c("Debug_HiSportStat", "saveFitnessSessionStat()  calorie change = ", Boolean.valueOf(c(j, 40054, this.f, hiHealthData.getDouble(d[2]), 3)));
        return true;
    }

    private boolean a(List<HiHealthData> list, long j) {
        if (HiCommonUtil.d(list)) {
            return false;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        for (HiHealthData hiHealthData : list) {
            switch (hiHealthData.getType()) {
                case 20002:
                    d2 = hiHealthData.getDouble("duration");
                    break;
                case 20003:
                    d3 = hiHealthData.getDouble("duration");
                    break;
                case 20004:
                    d5 = hiHealthData.getDouble("duration");
                    break;
                case 20005:
                    d4 = hiHealthData.getDouble("duration");
                    break;
                case 20007:
                    d6 = hiHealthData.getDouble("duration");
                    break;
            }
        }
        b(40041, j, d2);
        b(40042, j, d3);
        b(40043, j, d4);
        b(40044, j, d5);
        b(40045, j, d6);
        b(SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER, j, d2 + d3 + d4 + d5 + d6);
        return true;
    }

    private void b(int i, long j, double d2) {
        LogUtil.c("Debug_HiSportStat", "saveDurationSessionStat() ", Integer.valueOf(i), " change = ", Boolean.valueOf(c(j, i, this.f, d2, 13)));
    }

    private boolean c(long j, int i, int i2, double d2, int i3) {
        if (d2 < 1.0E-6d) {
            LogUtil.h("Debug_HiSportStat", "saveOneSportStat()  statValue is ", Double.valueOf(d2));
            return false;
        }
        if (d2 == 0.0d && !a().contains(Integer.valueOf(i))) {
            return false;
        }
        igo igoVar = new igo();
        igoVar.d(j);
        igoVar.c(20001);
        igoVar.b(this.e);
        igoVar.j(this.i);
        igoVar.g(i2);
        igoVar.h(i3);
        igoVar.d(i);
        igoVar.a(d2);
        return this.mDataStatManager.a(igoVar);
    }

    private List<Integer> a() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(40011);
        arrayList.add(40031);
        arrayList.add(40021);
        arrayList.add(40012);
        arrayList.add(40032);
        arrayList.add(40022);
        arrayList.add(40013);
        arrayList.add(40034);
        arrayList.add(40024);
        arrayList.add(40033);
        arrayList.add(40023);
        arrayList.add(40035);
        arrayList.add(40025);
        return arrayList;
    }
}
