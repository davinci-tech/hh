package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TrackData;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class mfd {
    public static void a(TrackData trackData, meh mehVar, Context context) {
        if (TextUtils.isEmpty(mct.b(context, "personal"))) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "flag is null");
            return;
        }
        mcz d = mehVar.d(2, new HashMap(2));
        SingleDayRecord singleDayRecord = d instanceof SingleDayRecord ? (SingleDayRecord) d : null;
        if (singleDayRecord == null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "singleDayRecord is null");
            return;
        }
        int acquireType = trackData.acquireType();
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "trackType is ", Integer.valueOf(acquireType));
        if (mfg.b(acquireType)) {
            g(singleDayRecord, trackData, mehVar);
            return;
        }
        if (mfg.a(acquireType)) {
            e(singleDayRecord, trackData, mehVar);
        } else if (mfg.g(acquireType)) {
            h(singleDayRecord, trackData, mehVar);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealSurmountEvent ", Integer.valueOf(acquireType));
        }
    }

    private static void e(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter dealCycleType");
        float acquireDistance = trackData.acquireDistance();
        float acquireBestPace = trackData.acquireBestPace();
        mka c = mlg.c(singleDayRecord.acquireBestCycleDistance());
        if (c != null && trackData.acquireTrackTime() != c.e()) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            if (mlg.d(mlg.d(acquireDistance), c.b() * 1.0d) > 0) {
                c(trackData, mehVar, singleDayRecord, 10);
            }
        } else {
            e(trackData, singleDayRecord, mehVar, 10);
        }
        if (acquireDistance < 1.0f) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "distance < 1");
            return;
        }
        double d = mlg.d(acquireBestPace);
        if (0.0d == d) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "bestPace is 0");
            return;
        }
        mkc d2 = mlg.d(singleDayRecord.acquireBestCycleSpeed());
        if (d2 != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealCycleType compareDouble bestPace.");
            if (mlg.e(d, d2.b()) < 0) {
                c(trackData, acquireBestPace, mehVar, singleDayRecord, 11);
                return;
            }
            return;
        }
        b(acquireBestPace * 1.0d, singleDayRecord, trackData, mehVar, 11);
    }

    private static void g(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter dealRunType");
        float acquireDistance = trackData.acquireDistance();
        mka c = mlg.c(singleDayRecord.acquireBestRunDistance());
        if (c != null && trackData.acquireTrackTime() != c.e()) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            if (mlg.d(mlg.d(acquireDistance), c.b() * 1.0d) > 0) {
                c(trackData, mehVar, singleDayRecord, 3);
            }
        } else {
            e(trackData, singleDayRecord, mehVar, 3);
        }
        float acquireBestPace = trackData.acquireBestPace();
        double d = mlg.d(acquireBestPace);
        if (0.0d == d) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "No bestPace.");
            return;
        }
        mkc d2 = mlg.d(singleDayRecord.acquireBestRunPace());
        if (d2 != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRunType compareDouble bestPace.");
            if (mlg.e(d, d2.b()) < 0) {
                c(trackData, acquireBestPace, mehVar, singleDayRecord, 4);
            }
        } else {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRunType bestMotionPaceOne == null.");
            b(d, singleDayRecord, trackData, mehVar, 4);
        }
        if (acquireDistance < 3.0d) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "distance < THREE_KM");
            return;
        }
        Map<Double, Double> acquirePartTimeMap = trackData.acquirePartTimeMap();
        if (acquirePartTimeMap == null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "map is null");
        } else {
            if (d(singleDayRecord, acquirePartTimeMap, trackData, mehVar)) {
                return;
            }
            c(singleDayRecord, acquirePartTimeMap, trackData, mehVar);
        }
    }

    private static boolean d(SingleDayRecord singleDayRecord, Map<Double, Double> map, TrackData trackData, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter acquireBestRunPace.");
        Double valueOf = Double.valueOf(3.0d);
        if (map.get(valueOf) != null) {
            double doubleValue = map.get(valueOf).doubleValue();
            mkc d = mlg.d(singleDayRecord.acquireBestRun3KMPace());
            if (d != null) {
                LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRunType acquireBestRun3KMPace.");
                if (mlg.e(doubleValue, d.b()) < 0) {
                    c(trackData, doubleValue, mehVar, singleDayRecord, 5);
                }
            } else {
                b(doubleValue, singleDayRecord, trackData, mehVar, 5);
            }
            if (map.get(Double.valueOf(5.0d)) != null) {
                double doubleValue2 = map.get(Double.valueOf(5.0d)).doubleValue();
                mkc d2 = mlg.d(singleDayRecord.acquireBestRun5KMPace());
                if (d2 != null) {
                    LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRunType acquireBestRun5KMPace.");
                    if (mlg.e(doubleValue2, d2.b()) < 0) {
                        c(trackData, doubleValue2, mehVar, singleDayRecord, 6);
                    }
                } else {
                    b(doubleValue2, singleDayRecord, trackData, mehVar, 6);
                }
                if (map.get(Double.valueOf(10.0d)) != null) {
                    double doubleValue3 = map.get(Double.valueOf(10.0d)).doubleValue();
                    mkc d3 = mlg.d(singleDayRecord.acquireBestRun10KMPace());
                    if (d3 != null) {
                        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRunType acquireBestRun10KMPace.");
                        if (mlg.e(doubleValue3, d3.b()) >= 0) {
                            return false;
                        }
                        c(trackData, doubleValue3, mehVar, singleDayRecord, 7);
                        return false;
                    }
                    b(doubleValue3, singleDayRecord, trackData, mehVar, 7);
                    return false;
                }
                LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "BestMotionPace TEN_KM is null.");
                return true;
            }
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "BestMotionPace FIVE_KM is null.");
            return true;
        }
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "BestMotionPace THREE_KM is null.");
        return true;
    }

    private static boolean c(SingleDayRecord singleDayRecord, Map<Double, Double> map, TrackData trackData, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter dealMarathonType.");
        Double valueOf = Double.valueOf(21.0975d);
        if (map.get(valueOf) == null) {
            return true;
        }
        double doubleValue = map.get(valueOf).doubleValue();
        mkc d = mlg.d(singleDayRecord.acquireBestRunHMPace());
        if (d != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(d.e()));
            if (mlg.e(doubleValue, d.b()) < 0 && d.e() != trackData.acquireTrackTime()) {
                c(trackData, doubleValue, mehVar, singleDayRecord, 8);
            }
        } else {
            b(doubleValue, singleDayRecord, trackData, mehVar, 8);
        }
        if (map.get(Double.valueOf(42.195d)) == null) {
            return true;
        }
        double doubleValue2 = map.get(Double.valueOf(42.195d)).doubleValue();
        mkc d2 = mlg.d(singleDayRecord.acquireBestRunFMPace());
        if (d2 != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(d2.e()));
            if (mlg.e(doubleValue2, d2.b()) >= 0 || d2.e() == trackData.acquireTrackTime()) {
                return false;
            }
            c(trackData, doubleValue2, mehVar, singleDayRecord, 9);
            return false;
        }
        b(doubleValue2, singleDayRecord, trackData, mehVar, 9);
        return false;
    }

    private static void h(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter dealWalkType");
        float acquireDistance = trackData.acquireDistance();
        mka c = mlg.c(singleDayRecord.acquireBestWalkDistance());
        if (c != null && trackData.acquireTrackTime() != c.e()) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            if (mlg.d(acquireDistance * 1.0d, c.b() * 1.0d) > 0) {
                c(trackData, mehVar, singleDayRecord, 1);
                return;
            }
            return;
        }
        e(trackData, singleDayRecord, mehVar, 1);
    }

    public static void b(Context context, ArrayList<TrackData> arrayList) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "Enter dealRopeType");
        if (context == null || koq.b(arrayList)) {
            return;
        }
        meh c = meh.c(context.getApplicationContext());
        if (TextUtils.isEmpty(mct.b(context, "personal"))) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "flag is null");
            return;
        }
        mcz d = c.d(2, new HashMap(2));
        SingleDayRecord singleDayRecord = d instanceof SingleDayRecord ? (SingleDayRecord) d : null;
        if (singleDayRecord == null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "singleDayRecord is null");
            return;
        }
        TrackData trackData = arrayList.get(0);
        TrackData trackData2 = arrayList.get(0);
        TrackData trackData3 = arrayList.get(0);
        TrackData trackData4 = arrayList.get(0);
        Iterator<TrackData> it = arrayList.iterator();
        while (it.hasNext()) {
            TrackData next = it.next();
            if (mfg.d(next.acquireType())) {
                if (next.acquireTrackNum() > trackData.acquireTrackNum()) {
                    trackData = next;
                }
                if (next.getMaxSkippingTimes() > trackData2.getMaxSkippingTimes()) {
                    trackData2 = next;
                }
                if (next.getSkipSpeed() > trackData3.getSkipSpeed()) {
                    trackData3 = next;
                }
                if (next.getTotalTime() > trackData4.getTotalTime()) {
                    trackData4 = next;
                }
            }
        }
        b(singleDayRecord, trackData, c);
        d(singleDayRecord, trackData2, c);
        c(singleDayRecord, trackData3, c);
        a(singleDayRecord, trackData4, c);
    }

    private static void a(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        long totalTime = trackData.getTotalTime() / 1000;
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeDurationType originalTime ", Long.valueOf(totalTime));
        mka c = mlg.c(singleDayRecord.acquireBestRopeDuration());
        if (c != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            if (totalTime > c.b()) {
                d(trackData, mehVar, singleDayRecord, 15, totalTime);
                return;
            }
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeSpeedType bestMotion is null.");
        d(trackData, singleDayRecord, mehVar, 15, totalTime);
    }

    private static void c(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int skipSpeed = trackData.getSkipSpeed();
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeSpeedType originalNum ", Integer.valueOf(skipSpeed));
        mka c = mlg.c(singleDayRecord.acquireBestRopeSpeed());
        if (c != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            long j = skipSpeed;
            if (j > c.b()) {
                d(trackData, mehVar, singleDayRecord, 14, j);
                return;
            }
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeSpeedType bestMotion is null.");
        d(trackData, singleDayRecord, mehVar, 14, skipSpeed);
    }

    private static void b(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int acquireTrackNum = trackData.acquireTrackNum();
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeSingCountType originalNum ", Integer.valueOf(acquireTrackNum));
        mka c = mlg.c(singleDayRecord.acquireBestRopeSingCount());
        if (c != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            long j = acquireTrackNum;
            if (j > c.b()) {
                d(trackData, mehVar, singleDayRecord, 12, j);
                return;
            }
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeSingCountType bestMotion is null.");
        d(trackData, singleDayRecord, mehVar, 12, acquireTrackNum);
    }

    private static void d(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int maxSkippingTimes = trackData.getMaxSkippingTimes();
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeContinuousCountType originalNum ", Integer.valueOf(maxSkippingTimes));
        mka c = mlg.c(singleDayRecord.acquireBestRopeContinuousCount());
        if (c != null) {
            LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "acquireTrackTime:", Long.valueOf(trackData.acquireTrackTime()), " bestMotionStartTime:", Long.valueOf(c.e()));
            long j = maxSkippingTimes;
            if (j > c.b()) {
                d(trackData, mehVar, singleDayRecord, 13, j);
                return;
            }
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "dealRopeContinuousCountType bestMotion is null.");
        d(trackData, singleDayRecord, mehVar, 13, maxSkippingTimes);
    }

    private static void c(TrackData trackData, meh mehVar, SingleDayRecord singleDayRecord, int i) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal showReportDistanceDialog type:", Integer.valueOf(i), " value ", Float.valueOf(trackData.acquireDistance()));
        if (e(trackData.acquireTrackTime())) {
            LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "showReportPaceDialog  exceeds 30 days, time ", Long.valueOf(trackData.acquireTrackTime()));
            return;
        }
        mka mkaVar = new mka();
        mkaVar.b((long) trackData.acquireDistance());
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.d(mlg.b(i, singleDayRecord), mkaVar)));
        String valueOf = String.valueOf(mlg.d((long) trackData.acquireDistance()));
        if (UnitUtil.h()) {
            valueOf = String.valueOf(mlg.e((long) trackData.acquireDistance()));
        }
        mehVar.d(i, valueOf, trackData.acquireTrackTime());
    }

    private static void c(TrackData trackData, double d, meh mehVar, SingleDayRecord singleDayRecord, int i) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal showReportPaceDialog type:", Integer.valueOf(i), " pace ", Double.valueOf(d));
        if (mlg.e(d, 0.0d) <= 0) {
            LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal showReportPaceDialog pace <= 0, return.");
            return;
        }
        if (e(trackData.acquireTrackTime())) {
            LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "showReportPaceDialog  exceeds 30 days, time ", Long.valueOf(trackData.acquireTrackTime()));
            return;
        }
        mkc mkcVar = new mkc();
        mkcVar.a(d);
        mkcVar.a(trackData.acquireTrackTime());
        mkcVar.b(trackData.acquireEndTime());
        mkcVar.a(0);
        mkcVar.d(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.d(mlg.b(i, singleDayRecord), mkcVar)));
        if (mlg.e(i)) {
            mehVar.d(i, mlg.a(d), trackData.acquireTrackTime());
        } else {
            mehVar.d(i, mlg.d((int) (d + 0.5d)), trackData.acquireTrackTime());
        }
    }

    private static boolean e(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "showReportDialog trackTime ", Long.valueOf(j), " interval ", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis <= 2592000000L) {
            return false;
        }
        LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "showReportDialog the trackTime exceeds 30 days, return!");
        return true;
    }

    private static void d(TrackData trackData, SingleDayRecord singleDayRecord, meh mehVar, int i, long j) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal refreshRopeMotionData type:", Integer.valueOf(i), " num ", Long.valueOf(j));
        mka mkaVar = new mka();
        mkaVar.b(j);
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.a(mkaVar) == null ? "" : mlg.c(mlg.a(mkaVar))));
    }

    private static void d(TrackData trackData, meh mehVar, SingleDayRecord singleDayRecord, int i, long j) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal showReportRopeDialog type:", Integer.valueOf(i), " num ", Long.valueOf(j));
        if (e(trackData.acquireTrackTime())) {
            LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "showReportRopeDialog  exceeds 30 days, time ", Long.valueOf(trackData.acquireTrackTime()));
            return;
        }
        mka mkaVar = new mka();
        mkaVar.b(j);
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.d(mlg.b(i, singleDayRecord), mkaVar)));
        mehVar.d(i, String.valueOf(j), trackData.acquireTrackTime());
    }

    private static void e(TrackData trackData, SingleDayRecord singleDayRecord, meh mehVar, int i) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal refreshMotionDistance type:", Integer.valueOf(i), " value ", Float.valueOf(trackData.acquireDistance()));
        mka mkaVar = new mka();
        mkaVar.b((long) trackData.acquireDistance());
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.a(mkaVar) == null ? "" : mlg.c(mlg.a(mkaVar))));
    }

    private static void b(double d, SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar, int i) {
        LogUtil.a("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal refreshMotionPaceData type:", Integer.valueOf(i), " pace ", Double.valueOf(d));
        if (mlg.e(d, 0.0d) <= 0) {
            LogUtil.h("PLGACHIEVE_AchieveSurmountRecordService", "myAchieveLocal refreshMotionPaceData pace <= 0, return.");
            return;
        }
        mkc mkcVar = new mkc();
        mkcVar.a(d);
        mkcVar.a(trackData.acquireTrackTime());
        mkcVar.b(trackData.acquireEndTime());
        mkcVar.a(0);
        mkcVar.d(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.a(mkcVar) == null ? "" : mlg.c(mlg.a(mkcVar))));
    }
}
