package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginachievement.report.bean.AnnualReportStepResult;
import health.compact.a.Utils;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mdb {
    private static boolean b(int i, int i2, int i3) {
        return (i == 0 && i2 == 0 && i3 != 2) ? false : true;
    }

    private static int e(Context context, PluginAchieveAdapter pluginAchieveAdapter, SingleDayRecord singleDayRecord) {
        return (context == null || pluginAchieveAdapter == null || singleDayRecord == null) ? -1 : 0;
    }

    public static void a(final Context context, PluginAchieveAdapter pluginAchieveAdapter, final SingleDayRecord singleDayRecord) {
        if (Utils.o() && e(context, pluginAchieveAdapter, singleDayRecord) != -1) {
            pluginAchieveAdapter.readBestStepDayOfYear(context, 0L, mle.d(false, System.currentTimeMillis()), new AchieveCallback() { // from class: mdb.5
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    mdb.b(context, singleDayRecord, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void b(Context context, SingleDayRecord singleDayRecord, T t) {
        if (!(t instanceof AnnualReportStepResult)) {
            LogUtil.h("PLGACHIEVE_SingleRecordRule", "saveBestStepData Class NotFound!");
            return;
        }
        AnnualReportStepResult annualReportStepResult = (AnnualReportStepResult) t;
        int value = annualReportStepResult.getValue();
        long timestamp = annualReportStepResult.getTimestamp();
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "achieveReport maxStep time == ", Long.valueOf(timestamp));
        singleDayRecord.setSteps(value);
        singleDayRecord.setStepsDate(timestamp);
        meh.c(context).e(singleDayRecord);
    }

    public static void b(final Context context, PluginAchieveAdapter pluginAchieveAdapter, final SingleDayRecord singleDayRecord) {
        if (Utils.o() && e(context, pluginAchieveAdapter, singleDayRecord) != -1) {
            long d = mle.d(false, System.currentTimeMillis());
            long d2 = d(singleDayRecord, 0L);
            LogUtil.a("PLGACHIEVE_SingleRecordRule", "dealRunData getRunSyncTime startTime ", Long.valueOf(d2));
            pluginAchieveAdapter.getTrackListData(context, d2, d, 258, new AchieveCallback() { // from class: mdb.1
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    if (!koq.e(obj, HiHealthData.class)) {
                        LogUtil.h("PLGACHIEVE_SingleRecordRule", "computeRunLongestData is null");
                        return;
                    }
                    List list = (List) obj;
                    if (!koq.b(list)) {
                        mdb.c(context, singleDayRecord, (List<HiHealthData>) list);
                    } else {
                        LogUtil.h("PLGACHIEVE_SingleRecordRule", "computeRunLongestData dataInfo isEmpty!");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, SingleDayRecord singleDayRecord, List<HiHealthData> list) {
        int i = 0;
        long j = 0;
        long j2 = 0;
        float f = 0.0f;
        for (HiHealthData hiHealthData : list) {
            HiTrackMetaData e = e(hiHealthData.getMetaData());
            if (e != null && mfg.b(e.getSportType())) {
                int totalDistance = e.getTotalDistance();
                if (totalDistance > i) {
                    j = hiHealthData.getStartTime();
                    i = totalDistance;
                }
                float bestPace = e.getBestPace();
                if (bestPace >= 0.001d && (bestPace < f || j2 == 0)) {
                    j2 = hiHealthData.getStartTime();
                    f = bestPace;
                }
            }
        }
        if (e()) {
            singleDayRecord.saveDistance(mlg.d(i));
            singleDayRecord.setDistanceDate(j);
            singleDayRecord.saveMatchSpeed((int) (f + 0.5d));
            singleDayRecord.setMatchSpeedDate(j2);
            LogUtil.h("PLGACHIEVE_SingleRecordRule", "insertSingleRecord setSpDelete clear result");
            mct.b(BaseApplication.getContext(), "_syncDeleteData", "");
        } else {
            long j3 = i;
            if (singleDayRecord.acquireDistance() < mlg.d(j3)) {
                singleDayRecord.saveDistance(mlg.d(j3));
                singleDayRecord.setDistanceDate(j);
            }
            if ((f < singleDayRecord.acquireMatchSpeed() && f > 0.0f) || singleDayRecord.acquireMatchSpeed() == 0) {
                singleDayRecord.saveMatchSpeed((int) (f + 0.5d));
                singleDayRecord.setMatchSpeedDate(j2);
            }
        }
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "achieveReport maxRunDisTime == ", Long.valueOf(j), " BestPaceTime == ", Long.valueOf(j2));
        meh.c(context).e(singleDayRecord);
    }

    private static HiTrackMetaData e(String str) {
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
            if (b(hiTrackMetaData.getAbnormalTrack(), hiTrackMetaData.getDuplicated(), hiTrackMetaData.getSportDataSource())) {
                return null;
            }
            return hiTrackMetaData;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PLGACHIEVE_SingleRecordRule", "trackMetaData is error");
            return null;
        }
    }

    public static long d(SingleDayRecord singleDayRecord, long j) {
        if (e()) {
            return j;
        }
        long distanceDate = singleDayRecord.getDistanceDate();
        long matchSpeedDate = singleDayRecord.getMatchSpeedDate();
        if (distanceDate > 0 && matchSpeedDate > 0) {
            distanceDate = Math.min(distanceDate, matchSpeedDate);
        } else if (distanceDate <= 0) {
            if (matchSpeedDate <= 0) {
                return j;
            }
            distanceDate = matchSpeedDate;
        }
        return distanceDate - 2592000000L;
    }

    private static boolean e() {
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "ReportDeleteResult1 syncDeleteInfo == ", mct.b(BaseApplication.getContext(), "_syncDeleteData"));
        return !TextUtils.isEmpty(r0);
    }

    public static void d(final Context context, PluginAchieveAdapter pluginAchieveAdapter, final SingleDayRecord singleDayRecord) {
        if (Utils.o() && e(context, pluginAchieveAdapter, singleDayRecord) != -1) {
            long d = mle.d(false, System.currentTimeMillis());
            LogUtil.a("PLGACHIEVE_SingleRecordRule", "computeBestRopeData startTime ", 0L);
            pluginAchieveAdapter.fetchSequenceDataBySportType(context, 0L, d, 283, new AchieveCallback() { // from class: mdb.3
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    mdb.a(context, obj, singleDayRecord);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void a(Context context, T t, SingleDayRecord singleDayRecord) {
        if (koq.e(t, HiHealthData.class)) {
            List list = (List) t;
            if (koq.b(list)) {
                LogUtil.h("PLGACHIEVE_SingleRecordRule", "SkipRecords records from HiHealth is 0! ");
            } else {
                d(context, (List<HiHealthData>) list, singleDayRecord);
            }
        }
    }

    private static void d(Context context, List<HiHealthData> list, SingleDayRecord singleDayRecord) {
        Map<String, String> extendTrackMap;
        TrackData trackData = new TrackData();
        TrackData trackData2 = new TrackData();
        TrackData trackData3 = new TrackData();
        TrackData trackData4 = new TrackData();
        long j = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (HiHealthData hiHealthData : list) {
            HiTrackMetaData d = d(hiHealthData.getMetaData());
            if (d != null && d.getAbnormalTrack() == 0 && d.getDuplicated() == 0 && d.getSportDataSource() != 2 && (extendTrackMap = d.getExtendTrackMap()) != null && extendTrackMap.size() != 0) {
                String str = extendTrackMap.get("skipNum");
                if (i < mfg.b(str)) {
                    i = mfg.b(str);
                    trackData = mgx.e(context).c(hiHealthData, d);
                }
                String str2 = extendTrackMap.get("maxSkippingTimes");
                if (i2 < mfg.b(str2)) {
                    i2 = mfg.b(str2);
                    trackData2 = mgx.e(context).c(hiHealthData, d);
                }
                String str3 = extendTrackMap.get("skipSpeed");
                if (i3 < mfg.b(str3)) {
                    i3 = mfg.b(str3);
                    trackData3 = mgx.e(context).c(hiHealthData, d);
                }
                long totalTime = d.getTotalTime();
                if (j < totalTime) {
                    trackData4 = mgx.e(context).c(hiHealthData, d);
                    j = totalTime;
                }
            }
        }
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "computeSkipTrackInfo skipNum ", Integer.valueOf(i), " maxSkippingTimes ", Integer.valueOf(i2), " skipSpeed ", Integer.valueOf(i3), " totalTime ", Long.valueOf(j));
        e(singleDayRecord, trackData, meh.c(context));
        a(singleDayRecord, trackData2, meh.c(context));
        c(singleDayRecord, trackData3, meh.c(context));
        b(singleDayRecord, trackData4, meh.c(context));
    }

    private static HiTrackMetaData d(String str) {
        try {
            return (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.h("PLGACHIEVE_SingleRecordRule", "trackMetaData is error");
            return null;
        }
    }

    private static void b(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        long totalTime = trackData.getTotalTime() / 1000;
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "dealRopeDurationType originalTime ", Long.valueOf(totalTime));
        mka c = mlg.c(singleDayRecord.acquireBestRopeDuration());
        if (c == null) {
            b(trackData, singleDayRecord, mehVar, 15, totalTime);
        } else if (totalTime > c.b()) {
            d(trackData, mehVar, singleDayRecord, 15, totalTime);
        }
    }

    private static void c(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int skipSpeed = trackData.getSkipSpeed();
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "dealRopeSpeedType originalNum ", Integer.valueOf(skipSpeed));
        mka c = mlg.c(singleDayRecord.acquireBestRopeSpeed());
        if (c == null) {
            b(trackData, singleDayRecord, mehVar, 14, skipSpeed);
            return;
        }
        long j = skipSpeed;
        if (j > c.b()) {
            d(trackData, mehVar, singleDayRecord, 14, j);
        }
    }

    private static void e(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int acquireTrackNum = trackData.acquireTrackNum();
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "dealRopeSingCountType originalNum ", Integer.valueOf(acquireTrackNum));
        mka c = mlg.c(singleDayRecord.acquireBestRopeSingCount());
        if (c == null) {
            b(trackData, singleDayRecord, mehVar, 12, acquireTrackNum);
            return;
        }
        long j = acquireTrackNum;
        if (j > c.b()) {
            d(trackData, mehVar, singleDayRecord, 12, j);
        }
    }

    private static void a(SingleDayRecord singleDayRecord, TrackData trackData, meh mehVar) {
        if (trackData == null || !mfg.d(trackData.acquireType())) {
            return;
        }
        int maxSkippingTimes = trackData.getMaxSkippingTimes();
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "dealRopeContinuousCountType originalNum ", Integer.valueOf(maxSkippingTimes));
        mka c = mlg.c(singleDayRecord.acquireBestRopeContinuousCount());
        if (c == null) {
            b(trackData, singleDayRecord, mehVar, 13, maxSkippingTimes);
            return;
        }
        long j = maxSkippingTimes;
        if (j > c.b()) {
            d(trackData, mehVar, singleDayRecord, 13, j);
        }
    }

    private static void d(TrackData trackData, meh mehVar, SingleDayRecord singleDayRecord, int i, long j) {
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "updateReportRope type:", Integer.valueOf(i), " num ", Long.valueOf(j));
        mka mkaVar = new mka();
        mkaVar.b(j);
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.d(mlg.b(i, singleDayRecord), mkaVar)));
    }

    private static void b(TrackData trackData, SingleDayRecord singleDayRecord, meh mehVar, int i, long j) {
        LogUtil.a("PLGACHIEVE_SingleRecordRule", "refreshRopeMotionData type:", Integer.valueOf(i), " num ", Long.valueOf(j));
        mka mkaVar = new mka();
        mkaVar.b(j);
        mkaVar.d(trackData.acquireTrackTime());
        mkaVar.a(trackData.acquireEndTime());
        mkaVar.d(0);
        mkaVar.e(0L);
        mehVar.e(mlg.e(i, singleDayRecord, mlg.a(mkaVar) == null ? "" : mlg.c(mlg.a(mkaVar))));
    }
}
