package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RidePower;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SkippingSpeed;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StepRateData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackAltitudeData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackPullFreqData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSpeedData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSwolfData;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import defpackage.ffs;
import defpackage.jec;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class HwExerciseAdviceBean {
    private int trackMaxPace = 0;
    private long trackTotalPace = 0;
    private int trackMaxStep = 0;
    private int trackTotalStep = 0;
    private long trackTotalHeart = 0;
    private int trackTotalCount = 0;
    private int trackHeartTotalCount = 0;
    private int trackSummaryTotalStep = 0;
    private int stepLastIndex = 0;
    private long trackTotalTime = 0;
    private int powerAccount = 0;
    private int trackMinuteStep = 0;
    private int trackMinuteStepCount = 0;
    private long trackMinuteStepTimestamp = 0;
    private ArrayList<HeartRateData> heartRateList = new ArrayList<>(16);
    private ArrayList<HeartRateData> invalidHeartRateList = new ArrayList<>(16);
    private ArrayList<StepRateData> stepList = new ArrayList<>(16);
    private ArrayList<TrackAltitudeData> altitudeList = new ArrayList<>(16);
    private ArrayList<TrackSwolfData> swolfList = new ArrayList<>(16);
    private ArrayList<TrackPullFreqData> pullFrequentList = new ArrayList<>(16);
    private ArrayList<TrackSpeedData> speedList = new ArrayList<>(16);
    private ArrayList<SkippingSpeed> frequencyList = new ArrayList<>(16);
    private ArrayList<RidePower> ridePowerList = new ArrayList<>(16);
    private ArrayList<StepRateData> cadenceList = new ArrayList<>(16);
    private ArrayList<ffs> postureDataList = new ArrayList<>(16);
    private boolean isSupportNewStep = HwWorkoutServiceManager.getInstance().isSupportNewStep();
    private String sportId = "gps_maptracking_" + jec.c(new Date(), "yyyyMMddHHmmssSSS");
    private List<LinkedHashMap<String, String>> extraDataList = new ArrayList(16);

    public int getTrackMaxPace() {
        return this.trackMaxPace;
    }

    public void setTrackMaxPace(int i) {
        this.trackMaxPace = i;
    }

    public long getTrackTotalPace() {
        return this.trackTotalPace;
    }

    public void setTrackTotalPace(long j) {
        this.trackTotalPace = j;
    }

    public int getTrackMaxStep() {
        return this.trackMaxStep;
    }

    public void setTrackMaxStep(int i) {
        this.trackMaxStep = i;
    }

    public int getTrackTotalStep() {
        return this.trackTotalStep;
    }

    public void setTrackTotalStep(int i) {
        this.trackTotalStep = i;
    }

    public long getTrackTotalHeart() {
        return this.trackTotalHeart;
    }

    public void setTrackTotalHeart(long j) {
        this.trackTotalHeart = j;
    }

    public int getTrackTotalCount() {
        return this.trackTotalCount;
    }

    public void setTrackTotalCount(int i) {
        this.trackTotalCount = i;
    }

    public int getTrackHeartTotalCount() {
        return this.trackHeartTotalCount;
    }

    public void setTrackHeartTotalCount(int i) {
        this.trackHeartTotalCount = i;
    }

    public int getTrackSummaryTotalStep() {
        return this.trackSummaryTotalStep;
    }

    public void setTrackSummaryTotalStep(int i) {
        this.trackSummaryTotalStep = i;
    }

    public int getStepLastIndex() {
        return this.stepLastIndex;
    }

    public void setStepLastIndex(int i) {
        this.stepLastIndex = i;
    }

    public long getTrackTotalTime() {
        return this.trackTotalTime;
    }

    public void setTrackTotalTime(long j) {
        this.trackTotalTime = j;
    }

    public int getPowerAccount() {
        return this.powerAccount;
    }

    public void setPowerAccount(int i) {
        this.powerAccount = i;
    }

    public int getTrackMinuteStep() {
        return this.trackMinuteStep;
    }

    public void setTrackMinuteStep(int i) {
        this.trackMinuteStep = i;
    }

    public int getTrackMinuteStepCount() {
        return this.trackMinuteStepCount;
    }

    public void setTrackMinuteStepCount(int i) {
        this.trackMinuteStepCount = i;
    }

    public long getTrackMinuteStepTimestamp() {
        return this.trackMinuteStepTimestamp;
    }

    public void setTrackMinuteStepTimestamp(long j) {
        this.trackMinuteStepTimestamp = j;
    }

    public ArrayList<HeartRateData> getHeartRateList() {
        return this.heartRateList;
    }

    public void setHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.heartRateList = arrayList;
    }

    public ArrayList<HeartRateData> getInvalidHeartRateList() {
        return this.invalidHeartRateList;
    }

    public void setInvalidHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.invalidHeartRateList = arrayList;
    }

    public ArrayList<StepRateData> getStepList() {
        return this.stepList;
    }

    public void setStepList(ArrayList<StepRateData> arrayList) {
        this.stepList = arrayList;
    }

    public ArrayList<TrackAltitudeData> getAltitudeList() {
        return this.altitudeList;
    }

    public void setAltitudeList(ArrayList<TrackAltitudeData> arrayList) {
        this.altitudeList = arrayList;
    }

    public ArrayList<TrackSwolfData> getSwolfList() {
        return this.swolfList;
    }

    public void setSwolfList(ArrayList<TrackSwolfData> arrayList) {
        this.swolfList = arrayList;
    }

    public ArrayList<TrackPullFreqData> getPullFrequentList() {
        return this.pullFrequentList;
    }

    public void setPullFrequentList(ArrayList<TrackPullFreqData> arrayList) {
        this.pullFrequentList = arrayList;
    }

    public ArrayList<TrackSpeedData> getSpeedList() {
        return this.speedList;
    }

    public void setSpeedList(ArrayList<TrackSpeedData> arrayList) {
        this.speedList = arrayList;
    }

    public ArrayList<SkippingSpeed> getFrequencyList() {
        return this.frequencyList;
    }

    public void setFrequencyList(ArrayList<SkippingSpeed> arrayList) {
        this.frequencyList = arrayList;
    }

    public ArrayList<RidePower> getRidePowerList() {
        return this.ridePowerList;
    }

    public void setRidePowerList(ArrayList<RidePower> arrayList) {
        this.ridePowerList = arrayList;
    }

    public ArrayList<StepRateData> getCadenceList() {
        return this.cadenceList;
    }

    public void setCadenceList(ArrayList<StepRateData> arrayList) {
        this.cadenceList = arrayList;
    }

    public ArrayList<ffs> getPostureDataList() {
        return this.postureDataList;
    }

    public boolean isSupportNewStep() {
        return this.isSupportNewStep;
    }

    public void setSupportNewStep(boolean z) {
        this.isSupportNewStep = z;
    }

    public String getSportId() {
        return this.sportId;
    }

    public void setSportId(String str) {
        this.sportId = str;
    }

    public List<LinkedHashMap<String, String>> getExtraDataList() {
        return this.extraDataList;
    }
}
