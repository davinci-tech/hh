package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.bean.AnnualReportCycle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes6.dex */
public class mhm {
    public static AnnualReportCycle e(List<HiHealthData> list) {
        LogUtil.a("PLGACHIEVE_CycleCalRule", "enter computeCycleTrackInfo");
        HiTrackMetaData hiTrackMetaData = null;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_CycleCalRule", "computeCycleTrackInfo dataInfos is empty");
            return null;
        }
        HashSet hashSet = new HashSet(16);
        int i = 0;
        long j = 0;
        int i2 = 0;
        int i3 = 0;
        for (HiHealthData hiHealthData : list) {
            try {
                hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("PLGACHIEVE_CycleCalRule", "computeCycleTrackInfo trackMetaData is error!");
            }
            if (hiTrackMetaData != null) {
                int totalDistance = hiTrackMetaData.getTotalDistance();
                if (!mhu.b(hiTrackMetaData)) {
                    i2 += totalDistance;
                    i3++;
                    hashSet.add(Long.valueOf(mht.a(hiHealthData)));
                }
                if (!mhu.c(hiTrackMetaData)) {
                    long startTime = hiHealthData.getStartTime();
                    if (totalDistance >= i) {
                        i = totalDistance;
                        j = startTime;
                    }
                }
            }
        }
        int c = mgx.c(new ArrayList(hashSet));
        LogUtil.a("PLGACHIEVE_CycleCalRule", "maxCycleContinuousDays reachDays == ", Integer.valueOf(c));
        return d(i, j, i2, i3, c);
    }

    private static AnnualReportCycle d(int i, long j, int i2, int i3, int i4) {
        AnnualReportCycle annualReportCycle = new AnnualReportCycle();
        annualReportCycle.saveMaxDistance(i);
        annualReportCycle.saveMaxDistanceDay(j);
        annualReportCycle.saveNumberOfTimes(i3);
        annualReportCycle.saveTotalDistance(i2);
        annualReportCycle.setMaxCycleContinuousDays(i4);
        return annualReportCycle;
    }
}
