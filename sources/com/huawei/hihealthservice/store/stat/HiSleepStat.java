package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ijn;
import defpackage.ikr;
import defpackage.iks;
import defpackage.ivq;
import defpackage.iwg;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HiSleepStat extends HiStatCommon {
    private iks b;
    private ikr c;
    private ijn d;

    private double d(double d, double d2) {
        double d3 = d + d2;
        if (d3 > 86400.0d) {
            return 86400.0d;
        }
        return d3;
    }

    public HiSleepStat(Context context) {
        super(context);
        this.d = ijn.a(context);
        this.c = ikr.b(this.mContext);
        this.b = iks.e();
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int userId = hiHealthData.getUserId();
        long l = HiDateUtil.l(hiHealthData.getStartTime());
        long o = HiDateUtil.o(hiHealthData.getStartTime());
        igo igoVar = new igo();
        igoVar.d(HiDateUtil.r(hiHealthData.getStartTime()));
        igoVar.j(userId);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(hiHealthData.getType());
        boolean a2 = a(l, o, igoVar, userId);
        ReleaseLogUtil.e("Debug_HiSleepStat", "stat() totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", date=", Integer.valueOf(igoVar.e()));
        return a2;
    }

    private boolean a(long j, long j2, igo igoVar, int i) {
        int e = this.c.e(0, i, 0);
        igoVar.b(e);
        if (e <= 0) {
            LogUtil.h("Debug_HiSleepStat", "statSleepDataByUser() statClient <= 0 userID = ", Integer.valueOf(i));
            return false;
        }
        List<Integer> a2 = this.b.a(i);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiSleepStat", "statSleepDataByUser() statClients is null userID = ", Integer.valueOf(i));
            return false;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setSortOrder(0);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(22001);
        arrayList.add(22002);
        return a(igoVar, this.d.a(hiDataReadOption, a2, arrayList));
    }

    private boolean a(igo igoVar, List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            LogUtil.h("Debug_HiSleepStat", "statSleepData() sleepDatas is null");
            return iwg.a(igoVar, HiHealthDataType.k());
        }
        int size = list.size();
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (true) {
            if (i >= size) {
                break;
            }
            HiHealthData hiHealthData = list.get(i);
            int type = hiHealthData.getType();
            long startTime = hiHealthData.getStartTime();
            long endTime = hiHealthData.getEndTime();
            if (type == 22001) {
                d += (endTime - startTime) / 1000.0d;
            } else if (type == 22002) {
                d2 += (endTime - startTime) / 1000.0d;
            }
            if (i >= size - 1) {
                LogUtil.c("Debug_HiSleepStat", "statSleepData break i = " + i);
                break;
            }
            i++;
            long startTime2 = list.get(i).getStartTime() - endTime;
            if (startTime2 > 180000 && startTime2 < 1800000) {
                d3 += startTime2 / 1000.0d;
                i2++;
            }
        }
        c(igoVar, d, d2, d3);
        d(igoVar, list, size);
        e(igoVar, list);
        c(igoVar, i2, 44005, 16);
        return true;
    }

    private void c(igo igoVar, double d, double d2, double d3) {
        a(igoVar, d, d2, d3, d(d, d2));
    }

    private void d(igo igoVar, List<HiHealthData> list, int i) {
        long startTime = list.get(0).getStartTime();
        long endTime = list.get(i - 1).getEndTime();
        c(igoVar, startTime, 44006, 5);
        c(igoVar, endTime, 44007, 5);
    }

    private void e(igo igoVar, List<HiHealthData> list) {
        Map<String, Long> e = ivq.e(list);
        if (e == null) {
            return;
        }
        a(igoVar, CommonUtil.b(String.valueOf(e.get("core_sleep_start_time_key")), 0L), CommonUtil.b(String.valueOf(e.get("core_sleep_end_time_key")), 0L));
    }

    private void a(igo igoVar, double d, double d2, double d3, double d4) {
        c(igoVar, d4, 44004, 13);
        c(igoVar, d, 44001, 13);
        c(igoVar, d2, 44002, 13);
        c(igoVar, d3, 44003, 13);
    }

    private void a(igo igoVar, long j, long j2) {
        if (j > 0) {
            c(igoVar, j, 44008, 5);
        }
        if (j2 > 0) {
            c(igoVar, j2, 44009, 5);
        }
    }

    private boolean c(igo igoVar, double d, int i, int i2) {
        igoVar.a(d);
        igoVar.d(i);
        igoVar.h(i2);
        return this.mDataStatManager.a(igoVar);
    }
}
