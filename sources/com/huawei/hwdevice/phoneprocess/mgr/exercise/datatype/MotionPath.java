package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwsportmodel.CommonSegment;
import defpackage.ffs;
import defpackage.ixt;
import defpackage.kof;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class MotionPath implements Serializable {
    private static final String BRITISH_PACE_MAP_TAG = "tp=b-p-m";
    private static final String CADENCE_RATE_LIST_TAG = "tp=cad";
    private static final String CONTENT_KEY = ";k=";
    private static final String CONTENT_VALUE = "v=";
    private static final String EQUAL_SIGN = "=";
    private static final String EXTRAS_DATA_TAG = "tp=ext";
    private static final String FIELD_SEPARATOR = ";";
    private static final String HEART_RATE_LIST_TAG = "tp=h-r";
    private static final String HEART_RATE_RECOVERY_LIST_TAG = "tp=r-r-h";
    private static final String INTERVAL_BRITISH_PACE_MAP_TAG = "tp=pm-b";
    private static final String INTERVAL_NORMAL_PACE_MAP_TAG = "tp=pm-n";
    private static final String INVALID_HEART_RATE_LIST_TAG = "tp=i-h-r";
    private static final String JUMP_TAG = "tp=jump";
    private static final String LBS_DATA_MAP_TAG = "tp=lbs";
    private static final String PACE_MAP_TAG = "tp=p-m";
    private static final String PADDLE_RATE_LIST_TAG = "tp=pad";
    private static final String RIDE_POWER_TAG = "tp=pow";
    private static final String RUNNING_POSTURE_TAG = "tp=rp";
    private static final String SKIPPING_SPEED_DATA_TAG = "tp=scp";
    private static final String SPO2_TAG = "tp=spo2";
    private static final String STEP_RATE_LIST_TAG = "tp=s-r";
    private static final String STRING_CARRIAGE_CHARACTER = System.lineSeparator();
    private static final String TRACK_ALTITUDE_TAG = "tp=alti";
    private static final String TRACK_PULL_FREQ_DATA_TAG = "tp=p-f";
    private static final String TRACK_SPEED_DATA_TAG = "tp=rs";
    private static final String TRACK_SWOLF_DATA_TAG = "tp=swf";
    private static final String VERSION = "version=1001";
    private static final long serialVersionUID = -1773594779542639469L;
    private ArrayList<TrackAltitudeData> altitudeList;
    private Map<Integer, Float> britishIntervalPaceMap;
    private Map<Integer, Float> britishPaceMap;
    private ArrayList<StepRateData> cadenceRateList;
    private ArrayList<HeartRateData> heartRateList;
    private ArrayList<HeartRateData> invalidHeartRateList;
    private Map<Long, double[]> lbsDataMap;
    private List<CommonSegment> mCommonSegments;
    private List<LinkedHashMap<String, String>> mExtraDataList;
    private List<ixt> mJumpDataList;
    private ArrayList<TrackPullFreqData> mPullFreqList;
    private List<RidePower> mRidePowerList;
    private List<SkippingSpeed> mSkippingSpeedList;
    private ArrayList<TrackSpeedData> mSpeedList;
    private List<kof> mSpo2List;
    private ArrayList<TrackSwolfData> mSwolfList;
    private Map<Integer, Float> normalIntervalPaceMap;
    private Map<Integer, Float> paceMap;
    private ArrayList<StepRateData> paddleRateList;
    private ArrayList<HeartRateData> recoveryHeartRateList;
    private ArrayList<ffs> runningPostureList;
    private ArrayList<StepRateData> stepRateList;

    @SerializedName("actionSummary")
    private String mActionSummary = "";
    private String mDivingIncidentData = "";
    private StringBuffer mSequenceDetailData = new StringBuffer();

    public StringBuffer getSequenceDetailData() {
        return this.mSequenceDetailData;
    }

    public void setExtraDataList(List<LinkedHashMap<String, String>> list) {
        this.mExtraDataList = list;
    }

    public String getDivingIncidentData() {
        return this.mDivingIncidentData;
    }

    public void setDivingIncidentData(String str) {
        this.mDivingIncidentData = str;
    }

    public List<RidePower> getRidePowerList() {
        return this.mRidePowerList;
    }

    public void setRidePowerList(List<RidePower> list) {
        this.mRidePowerList = list;
    }

    public List<CommonSegment> getCommonSegments() {
        return this.mCommonSegments;
    }

    public void setCommonSegments(List<CommonSegment> list) {
        this.mCommonSegments = list;
    }

    public void setActionSummary(String str) {
        this.mActionSummary = str;
    }

    public Map<Long, double[]> getLbsDataMap() {
        return this.lbsDataMap;
    }

    public void setLbsDataMap(Map<Long, double[]> map) {
        this.lbsDataMap = map;
    }

    public Map<Integer, Float> getPaceMap() {
        return this.paceMap;
    }

    public void setPaceMap(Map<Integer, Float> map) {
        this.paceMap = map;
    }

    public Map<Integer, Float> getBritishPaceMap() {
        return this.britishPaceMap;
    }

    public void setBritishPaceMap(Map<Integer, Float> map) {
        this.britishPaceMap = map;
    }

    public ArrayList<HeartRateData> getHeartRateList() {
        return this.heartRateList;
    }

    public void setHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.heartRateList = arrayList;
    }

    public void setRecoveryHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.recoveryHeartRateList = arrayList;
    }

    public ArrayList<HeartRateData> getRecoveryHeartRateList() {
        return this.recoveryHeartRateList;
    }

    public ArrayList<HeartRateData> getInvalidHeartRateList() {
        return this.invalidHeartRateList;
    }

    public void setInvalidHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.invalidHeartRateList = arrayList;
    }

    public ArrayList<StepRateData> getStepRateList() {
        return this.stepRateList;
    }

    public void setStepRateList(ArrayList<StepRateData> arrayList) {
        this.stepRateList = arrayList;
    }

    public ArrayList<StepRateData> getPaddleRateList() {
        return this.paddleRateList;
    }

    public void setPaddleRateList(ArrayList<StepRateData> arrayList) {
        this.paddleRateList = arrayList;
    }

    public ArrayList<StepRateData> getCadenceRateList() {
        return this.cadenceRateList;
    }

    public void setCadenceRateList(ArrayList<StepRateData> arrayList) {
        this.cadenceRateList = arrayList;
    }

    public Map<Integer, Float> getNormalIntervalPaceMap() {
        return this.normalIntervalPaceMap;
    }

    public void setNormalIntervalPaceMap(Map<Integer, Float> map) {
        this.normalIntervalPaceMap = map;
    }

    public Map<Integer, Float> getBritishIntervalPaceMap() {
        return this.britishIntervalPaceMap;
    }

    public void setBritishIntervalPaceMap(Map<Integer, Float> map) {
        this.britishIntervalPaceMap = map;
    }

    public ArrayList<TrackSpeedData> getSpeedList() {
        return this.mSpeedList;
    }

    public void setSpeedList(ArrayList<TrackSpeedData> arrayList) {
        this.mSpeedList = arrayList;
    }

    public ArrayList<TrackAltitudeData> getAltitudeList() {
        return this.altitudeList;
    }

    public void setAltitudeList(ArrayList<TrackAltitudeData> arrayList) {
        this.altitudeList = arrayList;
    }

    public ArrayList<TrackSwolfData> getSwolfList() {
        return this.mSwolfList;
    }

    public void saveJumpDataList(List<ixt> list) {
        this.mJumpDataList = list;
    }

    public void setSwolfList(ArrayList<TrackSwolfData> arrayList) {
        this.mSwolfList = arrayList;
    }

    public ArrayList<ffs> requestRunningPostureList() {
        return this.runningPostureList;
    }

    public void saveRunningPostureList(ArrayList<ffs> arrayList) {
        this.runningPostureList = arrayList;
    }

    public ArrayList<TrackPullFreqData> getPullFreqList() {
        return this.mPullFreqList;
    }

    public void setPullFreqList(ArrayList<TrackPullFreqData> arrayList) {
        this.mPullFreqList = arrayList;
    }

    public List<kof> requestSpo2List() {
        return this.mSpo2List;
    }

    public void saveSpo2List(List<kof> list) {
        this.mSpo2List = list;
    }

    public List<SkippingSpeed> getSkippingSpeedList() {
        return this.mSkippingSpeedList;
    }

    public void setSkippingSpeedList(List<SkippingSpeed> list) {
        this.mSkippingSpeedList = list;
    }

    public String toString() {
        String str = appendLbsString() + appendPaceString() + appendBritishPaceString() + appendHeartRateString() + appendRecoveryHeartRateString() + appendInvalidHeartRateString() + appendStepRateString() + appendNormalIntervalPaceString() + appendBritishIntervalPaceString() + appendAltitudeString() + appendSwolfString() + spo2ListToStr() + appendPullFreqString() + appendSpeedListString() + appendSkippingSpeedListString() + appendRidePowerListString() + runningPostureListToStr() + jumpDataListToString() + appendPaddleRateString() + appendCadenceRateString() + commonSectionToString() + addNewExtraDataToString();
        if (!TextUtils.isEmpty(this.mDivingIncidentData)) {
            str = str + this.mDivingIncidentData + STRING_CARRIAGE_CHARACTER;
        }
        if (!TextUtils.isEmpty(this.mSequenceDetailData.toString())) {
            str = str + this.mSequenceDetailData.toString() + STRING_CARRIAGE_CHARACTER;
        }
        if (TextUtils.isEmpty(this.mActionSummary)) {
            return str;
        }
        return str + "actionSummary=" + this.mActionSummary + STRING_CARRIAGE_CHARACTER;
    }

    private String appendRidePowerListString() {
        if (this.mRidePowerList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (RidePower ridePower : this.mRidePowerList) {
            stringBuffer.append("tp=pow;k=").append(ridePower.acquireTime()).append(";v=").append(ridePower.getPower()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendLbsString() {
        if (this.lbsDataMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (Map.Entry<Long, double[]> entry : this.lbsDataMap.entrySet()) {
            stringBuffer.append("tp=lbs;k=").append(entry.getKey()).append(";lat=").append(entry.getValue()[0]).append(";lon=").append(entry.getValue()[1]).append(";alt=").append(entry.getValue()[2]).append(";t=").append(entry.getValue()[3]).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendPaceString() {
        if (this.paceMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (Map.Entry<Integer, Float> entry : this.paceMap.entrySet()) {
            stringBuffer.append("tp=p-m;k=").append(entry.getKey()).append(";v=").append(entry.getValue()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendBritishPaceString() {
        if (this.britishPaceMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (Map.Entry<Integer, Float> entry : this.britishPaceMap.entrySet()) {
            stringBuffer.append("tp=b-p-m;k=").append(entry.getKey()).append(";v=").append(entry.getValue()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendHeartRateString() {
        if (this.heartRateList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<HeartRateData> it = this.heartRateList.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            stringBuffer.append("tp=h-r;k=").append(next.acquireTime()).append(";v=").append(next.acquireHeartRate()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendRecoveryHeartRateString() {
        if (this.recoveryHeartRateList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<HeartRateData> it = this.recoveryHeartRateList.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            stringBuffer.append("tp=r-r-h;k=").append(next.acquireTime()).append(";v=").append(next.acquireHeartRate()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendInvalidHeartRateString() {
        if (this.invalidHeartRateList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<HeartRateData> it = this.invalidHeartRateList.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            stringBuffer.append("tp=i-h-r;k=").append(next.acquireTime()).append(";v=").append(next.acquireHeartRate()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendStepRateString() {
        if (this.stepRateList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<StepRateData> it = this.stepRateList.iterator();
        while (it.hasNext()) {
            StepRateData next = it.next();
            stringBuffer.append("tp=s-r;k=").append(next.getTime()).append(";v=").append(next.getStepRate()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendPaddleRateString() {
        if (this.paddleRateList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        Iterator<StepRateData> it = this.paddleRateList.iterator();
        while (it.hasNext()) {
            StepRateData next = it.next();
            sb.append("tp=pad;k=");
            sb.append(next.getTime());
            sb.append(";v=");
            sb.append(next.getStepRate());
            sb.append(";");
            sb.append(STRING_CARRIAGE_CHARACTER);
        }
        return sb.toString();
    }

    private String appendCadenceRateString() {
        if (this.cadenceRateList == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        Iterator<StepRateData> it = this.cadenceRateList.iterator();
        while (it.hasNext()) {
            StepRateData next = it.next();
            sb.append("tp=cad;k=");
            sb.append(next.getTime());
            sb.append(";v=");
            sb.append(next.getStepRate());
            sb.append(";");
            sb.append(STRING_CARRIAGE_CHARACTER);
        }
        return sb.toString();
    }

    private String appendNormalIntervalPaceString() {
        if (this.normalIntervalPaceMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (Map.Entry<Integer, Float> entry : this.normalIntervalPaceMap.entrySet()) {
            stringBuffer.append("tp=pm-n;k=").append(entry.getKey()).append(";v=").append(entry.getValue()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendBritishIntervalPaceString() {
        if (this.britishIntervalPaceMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (Map.Entry<Integer, Float> entry : this.britishIntervalPaceMap.entrySet()) {
            stringBuffer.append("tp=pm-b;k=").append(entry.getKey()).append(";v=").append(entry.getValue()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendAltitudeString() {
        if (this.altitudeList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<TrackAltitudeData> it = this.altitudeList.iterator();
        while (it.hasNext()) {
            TrackAltitudeData next = it.next();
            stringBuffer.append("tp=alti;k=").append(next.getTime()).append(";v=").append(next.getAltitude()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendSwolfString() {
        if (this.mSwolfList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<TrackSwolfData> it = this.mSwolfList.iterator();
        while (it.hasNext()) {
            TrackSwolfData next = it.next();
            stringBuffer.append("tp=swf;k=").append(next.getTime()).append(";v=").append(next.getSwolf()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendPullFreqString() {
        if (this.mPullFreqList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<TrackPullFreqData> it = this.mPullFreqList.iterator();
        while (it.hasNext()) {
            TrackPullFreqData next = it.next();
            stringBuffer.append("tp=p-f;k=").append(next.getTime()).append(";v=").append(next.getPullFreq()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendSpeedListString() {
        if (this.mSpeedList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<TrackSpeedData> it = this.mSpeedList.iterator();
        while (it.hasNext()) {
            TrackSpeedData next = it.next();
            stringBuffer.append("tp=rs;k=").append(next.getTime()).append(";v=").append(next.getRealTimeSpeed()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String appendSkippingSpeedListString() {
        if (this.mSkippingSpeedList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (SkippingSpeed skippingSpeed : this.mSkippingSpeedList) {
            stringBuffer.append("tp=scp;k=").append(skippingSpeed.acquireTime()).append(";v=").append(skippingSpeed.getSpeed()).append(";").append(STRING_CARRIAGE_CHARACTER);
        }
        return stringBuffer.toString();
    }

    private String spo2ListToStr() {
        if (this.mSpo2List == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (kof kofVar : this.mSpo2List) {
            stringBuffer.append("tp=spo2;k=").append(kofVar.acquireTime()).append(";v=").append(kofVar.b()).append(";").append(System.lineSeparator());
        }
        return stringBuffer.toString();
    }

    private String runningPostureListToStr() {
        StringBuffer stringBuffer = new StringBuffer(0);
        ArrayList<ffs> arrayList = this.runningPostureList;
        if (arrayList != null) {
            Iterator<ffs> it = arrayList.iterator();
            while (it.hasNext()) {
                ffs next = it.next();
                stringBuffer.append("tp=rp").append(";k=").append(next.g()).append(";").append("gct=").append(next.b()).append(";").append("gia=").append(next.e()).append(";").append("sa=").append(next.i()).append(";").append("ee=").append(next.a()).append(";").append("fsp=").append(next.d()).append(";").append("wsp=").append(next.h()).append(";").append("hsp=").append(next.c()).append(";").append("aht=").append(next.l()).append(";").append("htr=").append(next.o()).append(";");
                addNewRunningPostureData(next, stringBuffer);
                stringBuffer.append(STRING_CARRIAGE_CHARACTER);
            }
        }
        return stringBuffer.toString();
    }

    private void addNewRunningPostureData(ffs ffsVar, StringBuffer stringBuffer) {
        LinkedHashMap<String, String> j = ffsVar.j();
        if (j.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : j.entrySet()) {
            stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
    }

    private String jumpDataListToString() {
        if (this.mJumpDataList == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (ixt ixtVar : this.mJumpDataList) {
            stringBuffer.append("tp=jump;k=").append(ixtVar.b()).append(";h=").append(ixtVar.c()).append(";t=").append(ixtVar.e()).append(";").append(System.lineSeparator());
        }
        return stringBuffer.toString();
    }

    private String commonSectionToString() {
        if (this.mCommonSegments == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<CommonSegment> it = this.mCommonSegments.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toTrackString());
        }
        return stringBuffer.toString();
    }

    private String addNewExtraDataToString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        List<LinkedHashMap<String, String>> list = this.mExtraDataList;
        if (list != null && !list.isEmpty()) {
            Iterator<LinkedHashMap<String, String>> it = this.mExtraDataList.iterator();
            while (it.hasNext()) {
                getNewExtraData(stringBuffer, it.next());
            }
        }
        return stringBuffer.toString();
    }

    private void getNewExtraData(StringBuffer stringBuffer, LinkedHashMap<String, String> linkedHashMap) {
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            return;
        }
        stringBuffer.append("tp=ext").append(";k=").append(linkedHashMap.get("time")).append(";");
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            if (!"time".equals(entry.getKey())) {
                stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            }
        }
        stringBuffer.append(STRING_CARRIAGE_CHARACTER);
    }

    public boolean validHeartRateList() {
        ArrayList<HeartRateData> arrayList = this.heartRateList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean validPaceMap() {
        Map<Integer, Float> map = this.paceMap;
        return map != null && map.size() > 0;
    }

    public boolean validBritishPaceMap() {
        Map<Integer, Float> map = this.britishPaceMap;
        return map != null && map.size() > 0;
    }

    public boolean validLbsDataMap() {
        Map<Long, double[]> map = this.lbsDataMap;
        return map != null && map.size() > 0;
    }

    public boolean validStepRateList() {
        ArrayList<StepRateData> arrayList = this.stepRateList;
        return arrayList != null && arrayList.size() > 0;
    }

    public Map<Integer, Float> localePaceMap() {
        if (UnitUtil.h()) {
            return this.britishPaceMap;
        }
        return this.paceMap;
    }

    public boolean validRunningPostureList() {
        ArrayList<ffs> arrayList = this.runningPostureList;
        return arrayList != null && arrayList.size() > 0;
    }
}
