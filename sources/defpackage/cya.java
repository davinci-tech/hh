package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cya {
    public cys c(cys cysVar) {
        if (b(cysVar)) {
            return null;
        }
        a(cysVar);
        return cysVar;
    }

    private boolean b(cys cysVar) {
        if (cysVar == null) {
            return true;
        }
        long f = cysVar.f() * 1000;
        if (f < 1590940800000L) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, dataStartTime < TIME_STAMP_MILLISECOND_MIN:", Long.valueOf(f));
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (f > currentTimeMillis) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, dataStartTime > currentTime:", Long.valueOf(f));
            return true;
        }
        int d = cysVar.d();
        long f2 = (cysVar.f() + d) * 1000;
        if (f2 > currentTimeMillis + 3600000) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, dataEndTime > currentTime + ONE_HOUR_MILLISECOND:", Long.valueOf(f2));
            return true;
        }
        if (d > 64800) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, ElapsedTime() > ELAPSED_TIME_MAX:", Integer.valueOf(d));
            return true;
        }
        if (cysVar.h() < 10) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, TotalSkip() < TOTAL_SKIP_MIN:", Integer.valueOf(cysVar.h()));
            return true;
        }
        if (d > 0) {
            if ((cysVar.h() / d) * 60 > 480) {
                LogUtil.a("PDROPE_HistoryDataCleaner", "need delete,", Integer.valueOf(cysVar.h() / d));
                return true;
            }
            if (cysVar.c() <= cysVar.h() || cysVar.c() == 65535) {
                return false;
            }
            LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, MaxContinueSkip():", Integer.valueOf(cysVar.c()), " > TotalSkip:", Integer.valueOf(cysVar.h()));
            return true;
        }
        LogUtil.a("PDROPE_HistoryDataCleaner", "need delete, elapsedTime exception");
        return true;
    }

    private void a(cys cysVar) {
        if (cysVar.c() < 0 && cysVar.c() != 65535) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "correctData, MaxContinueSkip:", Integer.valueOf(cysVar.c()));
            cysVar.d(65535);
        }
        if (cysVar.d() < 0) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "correctData, ElapsedTime:", Integer.valueOf(cysVar.d()));
            cysVar.c(0);
        }
        if (cysVar.i() < 0) {
            LogUtil.a("PDROPE_HistoryDataCleaner", "correctData, TotalEnergy", Integer.valueOf(cysVar.i()));
            cysVar.g(0);
        }
        if (cysVar.b() >= 0 || cysVar.b() == 65535) {
            return;
        }
        LogUtil.a("PDROPE_HistoryDataCleaner", "correctData, InterruptTimes", Integer.valueOf(cysVar.b()));
        cysVar.b(65535);
    }
}
