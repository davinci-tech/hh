package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.CommonSectionInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.hwsportmodel.TrackFreeDivingSegment;
import com.huawei.hwsportmodel.TrackGolfCourseSegment;
import defpackage.kwp;
import defpackage.kwq;
import defpackage.kwr;
import defpackage.kws;
import defpackage.kwu;
import defpackage.kwv;
import java.util.List;

/* loaded from: classes5.dex */
public class HwExerciseCommonSegmentUtils {
    private static final String TAG = "HwExerciseCommonSegmentUtils";

    private static boolean isHandleParameterEmpty(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        return list == null || commonSectionInfo == null;
    }

    private HwExerciseCommonSegmentUtils() {
    }

    public static void handleRunSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleRunSegment parameter is abnormal");
            return;
        }
        kwu kwuVar = new kwu();
        kwuVar.f(commonSectionInfo.getSectionNum());
        kwuVar.d(commonSectionInfo.getSectionTime());
        kwuVar.e(commonSectionInfo.getSectionDistance());
        kwuVar.j(commonSectionInfo.getSectionPace());
        kwuVar.h(commonSectionInfo.getSectionHeartRate());
        kwuVar.e(commonSectionInfo.getSectionCadence());
        kwuVar.g(commonSectionInfo.getSectionStepLength());
        kwuVar.b(commonSectionInfo.getSectionTotalRise());
        kwuVar.c(commonSectionInfo.getSectionTotalDescend());
        kwuVar.b(commonSectionInfo.getSectionGroundContactTime());
        kwuVar.c(commonSectionInfo.getSectionGroundImpactAcceleration());
        kwuVar.i(commonSectionInfo.getSectionSwingAngle());
        kwuVar.a(commonSectionInfo.getSectionEversionExcursion());
        kwuVar.l(commonSectionInfo.getSectionIntervalTrainingType());
        list.add(kwuVar);
        LogUtil.c(TAG, "saveRunningSegment data: ", commonSectionInfo.toString());
    }

    public static void handleGolfSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleGolfSegment parameter is abnormal");
            return;
        }
        kws kwsVar = new kws();
        kwsVar.e(commonSectionInfo.getSectionNum());
        kwsVar.c(commonSectionInfo.getSectionBackSwingTime());
        kwsVar.b(commonSectionInfo.getSectionDownSwingTime());
        kwsVar.d(commonSectionInfo.getSectionHeadSpeed());
        kwsVar.a(commonSectionInfo.getSectionSwingTempo());
        list.add(kwsVar);
        LogUtil.c(TAG, "saveGolfSegment data: ", commonSectionInfo.toString());
    }

    public static void handleSkiSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleSkiSegment parameter is abnormal");
            return;
        }
        kwv kwvVar = new kwv();
        kwvVar.e(commonSectionInfo.getSectionNum());
        kwvVar.g(commonSectionInfo.getSectionTime());
        kwvVar.b(commonSectionInfo.getSectionDistance());
        kwvVar.b(commonSectionInfo.getSectionMaxSpeed());
        kwvVar.d(commonSectionInfo.getSectionAvgSpeed());
        kwvVar.e(commonSectionInfo.getSectionStartGpsPointIndex());
        kwvVar.a(commonSectionInfo.getSectionEndGpsPointIndex());
        kwvVar.c(commonSectionInfo.getSectionCableStartGpsPointIndex());
        kwvVar.d(commonSectionInfo.getSectionCableEndGpsPointIndex());
        kwvVar.a(commonSectionInfo.getSectionSlopeDegree());
        kwvVar.c(commonSectionInfo.getSectionSlopePercent());
        list.add(kwvVar);
        LogUtil.c(TAG, "saveSkiSegment data: ", commonSectionInfo.toString());
    }

    public static void handleExerciseSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleExerciseSegment parameter is abnormal");
            return;
        }
        kwr kwrVar = new kwr();
        kwrVar.a(commonSectionInfo.getSectionActionId());
        kwrVar.a(commonSectionInfo.getSectionActionType());
        kwrVar.d(commonSectionInfo.getSectionActionResultValue());
        kwrVar.b(commonSectionInfo.getSectionActionTargetValue());
        kwrVar.h(commonSectionInfo.getSectionStrengthType());
        kwrVar.e(commonSectionInfo.getSectionStrengthStatistics());
        kwrVar.i(commonSectionInfo.getSectionStrengthUpper());
        kwrVar.c(commonSectionInfo.getSectionStrengthLower());
        list.add(kwrVar);
        LogUtil.c(TAG, "saveExerciseSegment data: ", commonSectionInfo.toString());
    }

    public static void handleCyclingSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleCyclingSegment parameter is abnormal");
            return;
        }
        kwq kwqVar = new kwq();
        kwqVar.c(commonSectionInfo.getSectionNum());
        kwqVar.b(commonSectionInfo.getSectionTime());
        kwqVar.a(commonSectionInfo.getSectionDistance());
        kwqVar.b(commonSectionInfo.getSectionHeartRate());
        kwqVar.e(commonSectionInfo.getSectionTotalRise());
        kwqVar.d(commonSectionInfo.getSectionTotalDescend());
        kwqVar.a(commonSectionInfo.getSectionIntervalTrainingType());
        kwqVar.e(commonSectionInfo.getSectionAvgSpeed());
        kwqVar.d(commonSectionInfo.getSectionAvgCadence());
        list.add(kwqVar);
    }

    public static void handleOwnTrainingSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleOwnTrainingSegment parameter is abnormal");
            return;
        }
        kwp kwpVar = new kwp();
        kwpVar.c(commonSectionInfo.getSectionNum());
        kwpVar.d(commonSectionInfo.getSectionTime());
        kwpVar.d(commonSectionInfo.getSectionHeartRate());
        kwpVar.b(commonSectionInfo.getSectionIntervalTrainingType());
        list.add(kwpVar);
    }

    public static void handleDivingOrBreathSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleDivingOrBreathSegment parameter is abnormal");
            return;
        }
        TrackFreeDivingSegment trackFreeDivingSegment = new TrackFreeDivingSegment();
        trackFreeDivingSegment.setSectionNum(commonSectionInfo.getSectionNum());
        trackFreeDivingSegment.setSectionDivingMaxDepth(commonSectionInfo.getSectionDivingMaxDepth());
        trackFreeDivingSegment.setSectionDivingUnderWaterTime(commonSectionInfo.getSectionDivingUnderwaterTime());
        trackFreeDivingSegment.setSectionDivingBreakTime(commonSectionInfo.getSectionDivingBreakTime());
        trackFreeDivingSegment.setMap(commonSectionInfo.getCommonFieldMap());
        list.add(trackFreeDivingSegment);
    }

    public static void handleGolfCourseSegment(List<CommonSegment> list, CommonSectionInfo commonSectionInfo) {
        if (isHandleParameterEmpty(list, commonSectionInfo)) {
            LogUtil.h(TAG, "handleGolfCourseSegment parameter is abnormal");
            return;
        }
        TrackGolfCourseSegment trackGolfCourseSegment = new TrackGolfCourseSegment();
        trackGolfCourseSegment.setSectionHoleSeq(commonSectionInfo.getSectionNum());
        trackGolfCourseSegment.setSectionHoleId(commonSectionInfo.getSectionHoleId());
        trackGolfCourseSegment.setSectionPar(commonSectionInfo.getSectionPar());
        trackGolfCourseSegment.setSectionHoleScore(commonSectionInfo.getSectionHoleScore());
        trackGolfCourseSegment.setSectionHolePutts(commonSectionInfo.getSectionHolePutts());
        trackGolfCourseSegment.setSectionHolePenalty(commonSectionInfo.getSectionHolePenalty());
        trackGolfCourseSegment.setSectionHoleFairwayHits(commonSectionInfo.getSectionHoleHits());
        trackGolfCourseSegment.setSectionHandicap(commonSectionInfo.getSectionHandicap());
        trackGolfCourseSegment.setSectionValidTracks(commonSectionInfo.getSectionValidTracks());
        trackGolfCourseSegment.setSectionGolfShotTracks(commonSectionInfo.getSectionTrackStruct());
        list.add(trackGolfCourseSegment);
    }
}
