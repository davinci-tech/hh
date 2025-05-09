package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwsportmodel.GolfShotTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class CommonSectionInfo {
    private static final int DEFAULT_VALUE = -1;
    private int mSectionNum = -1;
    private long mSectionTime = -1;
    private long mSectionDistance = -1;
    private int mSectionPace = -1;
    private int mSectionHeartRate = -1;
    private int mSectionCadence = -1;
    private int mSectionStepLength = -1;
    private long mSectionTotalRise = -1;
    private long mSectionTotalDescend = -1;
    private int mSectionGroundContactTime = -1;
    private int mSectionGroundImpactAcceleration = -1;
    private int mSectionSwingAngle = -1;
    private int mSectionEversionExcursion = -1;
    private int mSectionBackSwingTime = -1;
    private int mSectionDownSwingTime = -1;
    private int mSectionHeadSpeed = -1;
    private int mSectionSwingTempo = -1;
    private int mSectionMaxSpeed = -1;
    private int mSectionAvgSpeed = -1;
    private long mSectionStartGpsPointIndex = -1;
    private long mSectionEndGpsPointIndex = -1;
    private long mSectionCableStartGpsPointIndex = -1;
    private long mSectionCableEndGpsPointIndex = -1;
    private int mSectionSlopeDegree = -1;
    private int mSectionSlopePercent = -1;
    private String mSectionActionId = "";
    private int mSectionActionType = -1;
    private int mSectionActionResultValue = -1;
    private int mSectionActionTargetValue = -1;
    private int mSectionAvgCadence = -1;
    private int mSectionIntervalTrainingType = -1;
    private int mSectionStrengthType = -1;
    private int mSectionStrengthStatistics = -1;
    private int mSectionStrengthUpper = -1;
    private int mSectionStrengthLower = -1;
    private int mSectionDivingMaxDepth = -1;
    private int mSectionDivingUnderwaterTime = -1;
    private int mSectionDivingBreakTime = -1;
    private long mSectionHoleId = -1;
    private int mSectionPar = -1;
    private int mSectionHoleScore = -1;
    private int mSectionHolePutts = -1;
    private int mSectionHolePenalty = -1;
    private int mSectionHoleHits = -1;
    private int mSectionHandicap = -1;
    private int mSectionValidTracks = -1;
    private List<GolfShotTrack> mSectionTrackStructList = new ArrayList(16);
    private Map<String, List<String>> mCommonFieldMap = new HashMap(16);

    public int getSectionNum() {
        return this.mSectionNum;
    }

    public void setSectionNum(int i) {
        this.mSectionNum = i;
    }

    public long getSectionTime() {
        return this.mSectionTime;
    }

    public void setSectionTime(long j) {
        this.mSectionTime = j;
    }

    public long getSectionDistance() {
        return this.mSectionDistance;
    }

    public void setSectionDistance(long j) {
        this.mSectionDistance = j;
    }

    public int getSectionPace() {
        return this.mSectionPace;
    }

    public void setSectionPace(int i) {
        this.mSectionPace = i;
    }

    public int getSectionHeartRate() {
        return this.mSectionHeartRate;
    }

    public void setSectionHeartRate(int i) {
        this.mSectionHeartRate = i;
    }

    public int getSectionCadence() {
        return this.mSectionCadence;
    }

    public void setSectionCadence(int i) {
        this.mSectionCadence = i;
    }

    public int getSectionStepLength() {
        return this.mSectionStepLength;
    }

    public void setSectionStepLength(int i) {
        this.mSectionStepLength = i;
    }

    public long getSectionTotalRise() {
        return this.mSectionTotalRise;
    }

    public void setSectionTotalRise(long j) {
        this.mSectionTotalRise = j;
    }

    public long getSectionTotalDescend() {
        return this.mSectionTotalDescend;
    }

    public void setSectionTotalDescend(long j) {
        this.mSectionTotalDescend = j;
    }

    public int getSectionGroundContactTime() {
        return this.mSectionGroundContactTime;
    }

    public void setSectionGroundContactTime(int i) {
        this.mSectionGroundContactTime = i;
    }

    public int getSectionGroundImpactAcceleration() {
        return this.mSectionGroundImpactAcceleration;
    }

    public void setSectionGroundImpactAcceleration(int i) {
        this.mSectionGroundImpactAcceleration = i;
    }

    public int getSectionSwingAngle() {
        return this.mSectionSwingAngle;
    }

    public void setSectionSwingAngle(int i) {
        this.mSectionSwingAngle = i;
    }

    public int getSectionEversionExcursion() {
        return this.mSectionEversionExcursion;
    }

    public void setSectionEversionExcursion(int i) {
        this.mSectionEversionExcursion = i;
    }

    public int getSectionBackSwingTime() {
        return this.mSectionBackSwingTime;
    }

    public void setSectionBackSwingTime(int i) {
        this.mSectionBackSwingTime = i;
    }

    public int getSectionDownSwingTime() {
        return this.mSectionDownSwingTime;
    }

    public void setSectionDownSwingTime(int i) {
        this.mSectionDownSwingTime = i;
    }

    public int getSectionHeadSpeed() {
        return this.mSectionHeadSpeed;
    }

    public void setSectionHeadSpeed(int i) {
        this.mSectionHeadSpeed = i;
    }

    public int getSectionSwingTempo() {
        return this.mSectionSwingTempo;
    }

    public void setSectionSwingTempo(int i) {
        this.mSectionSwingTempo = i;
    }

    public int getSectionMaxSpeed() {
        return this.mSectionMaxSpeed;
    }

    public void setSectionMaxSpeed(int i) {
        this.mSectionMaxSpeed = i;
    }

    public int getSectionAvgSpeed() {
        return this.mSectionAvgSpeed;
    }

    public void setSectionAvgSpeed(int i) {
        this.mSectionAvgSpeed = i;
    }

    public long getSectionStartGpsPointIndex() {
        return this.mSectionStartGpsPointIndex;
    }

    public void setSectionStartGpsPointIndex(long j) {
        this.mSectionStartGpsPointIndex = j;
    }

    public long getSectionEndGpsPointIndex() {
        return this.mSectionEndGpsPointIndex;
    }

    public void setSectionEndGpsPointIndex(long j) {
        this.mSectionEndGpsPointIndex = j;
    }

    public long getSectionCableStartGpsPointIndex() {
        return this.mSectionCableStartGpsPointIndex;
    }

    public void setSectionCableStartGpsPointIndex(long j) {
        this.mSectionCableStartGpsPointIndex = j;
    }

    public long getSectionCableEndGpsPointIndex() {
        return this.mSectionCableEndGpsPointIndex;
    }

    public void setSectionCableEndGpsPointIndex(long j) {
        this.mSectionCableEndGpsPointIndex = j;
    }

    public int getSectionSlopeDegree() {
        return this.mSectionSlopeDegree;
    }

    public void setSectionSlopeDegree(int i) {
        this.mSectionSlopeDegree = i;
    }

    public int getSectionSlopePercent() {
        return this.mSectionSlopePercent;
    }

    public void setSectionSlopePercent(int i) {
        this.mSectionSlopePercent = i;
    }

    public String getSectionActionId() {
        return this.mSectionActionId;
    }

    public void setSectionActionId(String str) {
        this.mSectionActionId = str;
    }

    public int getSectionActionType() {
        return this.mSectionActionType;
    }

    public void setSectionActionType(int i) {
        this.mSectionActionType = i;
    }

    public int getSectionActionResultValue() {
        return this.mSectionActionResultValue;
    }

    public void setSectionActionResultValue(int i) {
        this.mSectionActionResultValue = i;
    }

    public int getSectionActionTargetValue() {
        return this.mSectionActionTargetValue;
    }

    public void setSectionActionTargetValue(int i) {
        this.mSectionActionTargetValue = i;
    }

    public int getSectionAvgCadence() {
        return this.mSectionAvgCadence;
    }

    public void setSectionAvgCadence(int i) {
        this.mSectionAvgCadence = i;
    }

    public int getSectionIntervalTrainingType() {
        return this.mSectionIntervalTrainingType;
    }

    public void setSectionIntervalTrainingType(int i) {
        this.mSectionIntervalTrainingType = i;
    }

    public int getSectionStrengthType() {
        return this.mSectionStrengthType;
    }

    public void setSectionStrengthType(int i) {
        this.mSectionStrengthType = i;
    }

    public int getSectionStrengthStatistics() {
        return this.mSectionStrengthStatistics;
    }

    public void setSectionStrengthStatistics(int i) {
        this.mSectionStrengthStatistics = i;
    }

    public int getSectionStrengthUpper() {
        return this.mSectionStrengthUpper;
    }

    public void setSectionStrengthUpper(int i) {
        this.mSectionStrengthUpper = i;
    }

    public int getSectionStrengthLower() {
        return this.mSectionStrengthLower;
    }

    public void setSectionStrengthLower(int i) {
        this.mSectionStrengthLower = i;
    }

    public int getSectionDivingMaxDepth() {
        return this.mSectionDivingMaxDepth;
    }

    public void setSectionDivingMaxDepth(int i) {
        this.mSectionDivingMaxDepth = i;
    }

    public int getSectionDivingUnderwaterTime() {
        return this.mSectionDivingUnderwaterTime;
    }

    public void setSectionDivingUnderwaterTime(int i) {
        this.mSectionDivingUnderwaterTime = i;
    }

    public int getSectionDivingBreakTime() {
        return this.mSectionDivingBreakTime;
    }

    public void setSectionDivingBreakTime(int i) {
        this.mSectionDivingBreakTime = i;
    }

    public long getSectionHoleId() {
        return this.mSectionHoleId;
    }

    public void setSectionHoleId(long j) {
        this.mSectionHoleId = j;
    }

    public int getSectionPar() {
        return this.mSectionPar;
    }

    public void setSectionPar(int i) {
        this.mSectionPar = i;
    }

    public int getSectionHoleScore() {
        return this.mSectionHoleScore;
    }

    public void setSectionHoleScore(int i) {
        this.mSectionHoleScore = i;
    }

    public int getSectionHolePutts() {
        return this.mSectionHolePutts;
    }

    public void setSectionHolePutts(int i) {
        this.mSectionHolePutts = i;
    }

    public int getSectionHolePenalty() {
        return this.mSectionHolePenalty;
    }

    public void setSectionHolePenalty(int i) {
        this.mSectionHolePenalty = i;
    }

    public int getSectionHoleHits() {
        return this.mSectionHoleHits;
    }

    public void setSectionHoleHits(int i) {
        this.mSectionHoleHits = i;
    }

    public int getSectionHandicap() {
        return this.mSectionHandicap;
    }

    public void setSectionHandicap(int i) {
        this.mSectionHandicap = i;
    }

    public int getSectionValidTracks() {
        return this.mSectionValidTracks;
    }

    public void setSectionValidTracks(int i) {
        this.mSectionValidTracks = i;
    }

    public List<GolfShotTrack> getSectionTrackStruct() {
        return this.mSectionTrackStructList;
    }

    public void setSectionTrackStruct(List<GolfShotTrack> list) {
        this.mSectionTrackStructList = list;
    }

    public Map<String, List<String>> getCommonFieldMap() {
        return this.mCommonFieldMap;
    }

    public String toString() {
        return "CommonSectionInfo{mSectionNum=" + this.mSectionNum + ", mSectionTime=" + this.mSectionTime + ", mSectionDistance=" + this.mSectionDistance + ", mSectionPace=" + this.mSectionPace + ", mSectionHeartRate=" + this.mSectionHeartRate + ", mSectionCadence=" + this.mSectionCadence + ", mSectionStepLength=" + this.mSectionStepLength + ", mSectionTotalRise=" + this.mSectionTotalRise + ", mSectionTotalDescend=" + this.mSectionTotalDescend + ", mSectionGroundContactTime=" + this.mSectionGroundContactTime + ", mSectionGroundImpactAcceleration=" + this.mSectionGroundImpactAcceleration + ", mSectionSwingAngle=" + this.mSectionSwingAngle + ", mSectionEversionExcursion=" + this.mSectionEversionExcursion + ", mSectionBackSwingTime=" + this.mSectionBackSwingTime + ", mSectionDownSwingTime=" + this.mSectionDownSwingTime + ", mSectionHeadSpeed=" + this.mSectionHeadSpeed + ", mSectionSwingTempo=" + this.mSectionSwingTempo + ", mSectionMaxSpeed=" + this.mSectionMaxSpeed + ", mSectionAvgSpeed=" + this.mSectionAvgSpeed + ", mSectionStartGpsPointIndex=" + this.mSectionStartGpsPointIndex + ", mSectionEndGpsPointIndex=" + this.mSectionEndGpsPointIndex + ", mSectionCableStartGpsPointIndex=" + this.mSectionCableStartGpsPointIndex + ", mSectionCableEndGpsPointIndex=" + this.mSectionCableEndGpsPointIndex + ", mSectionSlopeDegree=" + this.mSectionSlopeDegree + ", mSectionSlopePercent=" + this.mSectionSlopePercent + ", mSectionActionId=" + this.mSectionActionId + ", mSectionActionType=" + this.mSectionActionType + ", mSectionActionResultValue=" + this.mSectionActionResultValue + ", mSectionActionTargetValue=" + this.mSectionActionTargetValue + ", mSectionAvgCadence=" + this.mSectionAvgCadence + ", mSectionIntervalTrainingType=" + this.mSectionIntervalTrainingType + ", mSectionStrengthType=" + this.mSectionStrengthType + ", mSectionStrengthStatistics=" + this.mSectionStrengthStatistics + ", mSectionStrengthUpper=" + this.mSectionStrengthUpper + ", mSectionStrengthLower=" + this.mSectionStrengthLower + ", mSectionDivingMaxDepth=" + this.mSectionDivingMaxDepth + ", mSectionDivingUnderwaterTime=" + this.mSectionDivingUnderwaterTime + ", mSectionDivingBreakTime=" + this.mSectionDivingBreakTime + ", mSectionHoleId=" + this.mSectionHoleId + ", mSectionPar=" + this.mSectionPar + ", mSectionHoleScore=" + this.mSectionHoleScore + ", mSectionHolePutts=" + this.mSectionHolePutts + ", mSectionHolePenalty=" + this.mSectionHolePenalty + ", mSectionHoleHits=" + this.mSectionHoleHits + ", mSectionHandicap=" + this.mSectionHandicap + ", mSectionValidTracks=" + this.mSectionValidTracks + ", mSectionTrackStructList=" + this.mSectionTrackStructList + ", mCommonFieldMap=" + HiJsonUtil.e(this.mCommonFieldMap) + '}';
    }
}
