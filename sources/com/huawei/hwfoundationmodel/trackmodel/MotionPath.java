package com.huawei.hwfoundationmodel.trackmodel;

import com.huawei.health.device.model.RecordAction;
import com.huawei.hwsportmodel.CommonSegment;
import defpackage.ffn;
import defpackage.ffs;
import defpackage.ixt;
import defpackage.knw;
import defpackage.kny;
import defpackage.knz;
import defpackage.koa;
import defpackage.kob;
import defpackage.koc;
import defpackage.kod;
import defpackage.koe;
import defpackage.kof;
import defpackage.kog;
import defpackage.koh;
import defpackage.koi;
import defpackage.kol;
import defpackage.kom;
import defpackage.koq;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MotionPath implements Serializable {
    public static final String ACT_TAG = "tp=act";
    public static final String ALTITUDE_TAG = "tp=alt";
    public static final String BRITISH_PACE_MAP_TAG = "tp=b-p-m";
    public static final String CADENCE_TAG = "tp=cad";
    public static final String CAL_TAG = "tp=cal";
    public static final String CONTENT_KEY = ";k=";
    public static final String CONTENT_VALUE = "v=";
    public static final String COURSE_ACTION_LIST_TAG = "tp=r-c-a";
    private static final String DETAIL_NULL = "DETAIL_NULL";
    public static final String EXTEND_TAG = "tp=ext";
    public static final String FIELD_SEPARATOR = ";";
    public static final String HEART_RATE_LIST_TAG = "tp=h-r";
    public static final String HEART_RATE_RECOVERY_LIST_TAG = "tp=r-r-h";
    public static final String HRA_TAG = "tp=h_r_a";
    public static final String INTERVAL_BRITISH_PACE_MAP_TAG = "tp=pm-b";
    public static final String INTERVAL_NORMAL_PACE_MAP_TAG = "tp=pm-n";
    public static final String INVALID_HEART_RATE_LIST_TAG = "tp=i-h-r";
    public static final String JUMP_TAG = "tp=jump";
    public static final String LBS_DATA_MAP_TAG = "tp=lbs";
    public static final String MARK_POINT_TAG = "tp=mk";
    public static final String MSG_TAG = "tp=msg";
    public static final String PACE_MAP_TAG = "tp=p-m";
    public static final String PADDLE_TAG = "tp=pad";
    public static final String POWER_TAG = "tp=pow";
    public static final String PULL_FREQ_TAG = "tp=p-f";
    public static final String REAL_TIME_SPEED_TAG = "tp=rs";
    public static final String RESISTANCE_TAG = "tp=res";
    public static final String RUNNING_POSTURE_TAG = "tp=rp";
    public static final String SKIP_SPEED_TAG = "tp=scp";
    public static final String SPO2_TAG = "tp=spo2";
    public static final String STEP_RATE_LIST_TAG = "tp=s-r";
    public static final String SWOLF_TAG = "tp=swf";
    private static final String TAG = "Track_MotionPath";
    public static final String WEIGHT_TAG = "tp=wei";
    private static final long serialVersionUID = -1773594779542639469L;
    private ArrayList<knz> mAltitudeList;
    private Map<Integer, Float> mBritishIntervalPaceMap;
    private Map<Integer, Float> mBritishPaceMap;
    private List<kod> mExtendDetailList;
    private List<kny> mHeartRateAreaList;
    private ArrayList<HeartRateData> mHeartRateList;
    private List<HeartRateData> mHeartRecoveryRateList;
    private ArrayList<HeartRateData> mInvalidHeartRateList;
    private List<ixt> mJumpDataList;
    private Map<Long, double[]> mLbsDataMap;
    private ArrayList<MarkPoint> mMarkPointList;
    private List<koa> mMessageList;
    private Map<Integer, Float> mNormalIntervalPaceMap;
    private Map<Integer, Float> mPaceMap;
    private List<knw> mPaddleFrequencyList;
    private List<koc> mPowerList;
    private List<kog> mPullFreqList;
    private List<koh> mRealTimePaceList;
    private List<koi> mRealTimeSpeedList;
    private List<Integer> mResistanceList;
    private List<ffn> mRidePostureDataList;
    private ArrayList<ffs> mRunningPostureList;
    private List<CommonSegment> mSegmentDataList;
    private List<kob> mSkippingSpeedList;
    private List<koe> mSpeedList;
    private List<kof> mSpo2List;
    private ArrayList<StepRateData> mStepRateList;
    private List<kol> mSwolfList;
    private List<kom> mWeightList;

    public List<koa> getMessageList() {
        return this.mMessageList;
    }

    public void setMessageList(List<koa> list) {
        this.mMessageList = list;
    }

    public List<kod> getExtendDetailList() {
        return this.mExtendDetailList;
    }

    public void setExtendDetailList(List<kod> list) {
        this.mExtendDetailList = list;
    }

    public List<Integer> requestResistanceList() {
        return this.mResistanceList;
    }

    public void saveResistanceList(List<Integer> list) {
        this.mResistanceList = list;
    }

    public List<knw> requestPaddleFrequencyList() {
        return this.mPaddleFrequencyList;
    }

    public List<kob> requestSkippingSpeedList() {
        return this.mSkippingSpeedList;
    }

    public void saveSkippingSpeed(List<kob> list) {
        this.mSkippingSpeedList = list;
    }

    public void savePaddleFrequencyList(List<knw> list) {
        this.mPaddleFrequencyList = list;
    }

    public List<ffn> requestRidePostureDataList() {
        return this.mRidePostureDataList;
    }

    public void saveRidePostureDataList(List<ffn> list) {
        this.mRidePostureDataList = list;
    }

    public List<koc> requestPowerList() {
        return this.mPowerList;
    }

    public void savePowerList(List<koc> list) {
        this.mPowerList = list;
    }

    public List<kom> requestWeightList() {
        return this.mWeightList;
    }

    public void saveWeightList(List<kom> list) {
        this.mWeightList = list;
    }

    public Map<Long, double[]> requestLbsDataMap() {
        return this.mLbsDataMap;
    }

    public void saveLbsDataMap(Map<Long, double[]> map) {
        this.mLbsDataMap = map;
    }

    public Map<Integer, Float> requestPaceMap() {
        return this.mPaceMap;
    }

    public void savePaceMap(Map<Integer, Float> map) {
        this.mPaceMap = map;
    }

    public Map<Integer, Float> requestBritishPaceMap() {
        return this.mBritishPaceMap;
    }

    public void saveBritishPaceMap(Map<Integer, Float> map) {
        this.mBritishPaceMap = map;
    }

    public ArrayList<HeartRateData> requestHeartRateList() {
        return this.mHeartRateList;
    }

    public ArrayList<HeartRateData> requestInvalidHeartRateList() {
        return this.mInvalidHeartRateList;
    }

    public void saveHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.mHeartRateList = arrayList;
    }

    public void saveInvalidHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.mInvalidHeartRateList = arrayList;
    }

    public ArrayList<StepRateData> requestStepRateList() {
        return this.mStepRateList;
    }

    public void saveStepRateList(ArrayList<StepRateData> arrayList) {
        this.mStepRateList = arrayList;
    }

    public void saveNormalIntervalPaceMap(Map<Integer, Float> map) {
        this.mNormalIntervalPaceMap = map;
    }

    public Map<Integer, Float> requestBritishIntervalPaceMap() {
        return this.mBritishIntervalPaceMap;
    }

    public void saveBritishIntervalPaceMap(Map<Integer, Float> map) {
        this.mBritishIntervalPaceMap = map;
    }

    public ArrayList<ffs> requestRunningPostureList() {
        return this.mRunningPostureList;
    }

    public void saveRunningPostureList(ArrayList<ffs> arrayList) {
        this.mRunningPostureList = arrayList;
    }

    public ArrayList<knz> requestAltitudeList() {
        return this.mAltitudeList;
    }

    public void saveAltitudeList(ArrayList<knz> arrayList) {
        this.mAltitudeList = arrayList;
    }

    public ArrayList<MarkPoint> requestMarkPointList() {
        return this.mMarkPointList;
    }

    public void saveMarkPointList(ArrayList<MarkPoint> arrayList) {
        this.mMarkPointList = arrayList;
    }

    public List<ixt> requestJumpDataList() {
        return this.mJumpDataList;
    }

    public void saveJumpDataList(List<ixt> list) {
        this.mJumpDataList = list;
    }

    public List<kof> requestSpo2List() {
        return this.mSpo2List;
    }

    public void saveSpo2List(List<kof> list) {
        this.mSpo2List = list;
    }

    public List<CommonSegment> requestSegmentList() {
        return this.mSegmentDataList;
    }

    public void saveSegmentList(List<CommonSegment> list) {
        this.mSegmentDataList = list;
    }

    public <E> void saveCommonSegmentList(List<? extends CommonSegment> list) {
        if (list == null) {
            return;
        }
        List<CommonSegment> list2 = this.mSegmentDataList;
        if (list2 == null) {
            this.mSegmentDataList = new ArrayList();
        } else {
            list2.clear();
        }
        this.mSegmentDataList.addAll(list);
    }

    public List<HeartRateData> requestHeartRecoveryRateList() {
        return this.mHeartRecoveryRateList;
    }

    public void saveHeartRecoveryRateList(List<HeartRateData> list) {
        this.mHeartRecoveryRateList = list;
    }

    public List<kny> getHeartRateAreaList() {
        return this.mHeartRateAreaList;
    }

    public void setHeartRateAreaList(List<kny> list) {
        this.mHeartRateAreaList = list;
    }

    public String toString() {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        lbsDataToString(arrayList, stringBuffer);
        paceDataToString(arrayList, stringBuffer, this.mPaceMap, PACE_MAP_TAG);
        paceDataToString(arrayList, stringBuffer, this.mBritishPaceMap, BRITISH_PACE_MAP_TAG);
        heartRateToString(arrayList);
        invalidHeartRateToString(arrayList);
        stepRateToString(arrayList, stringBuffer);
        altitudeListToString(arrayList);
        markPointListToString(arrayList);
        spo2ListToString(arrayList);
        swolfListToString(arrayList);
        pullFreqListToString(arrayList);
        speedPaceListListToString(arrayList);
        runningPostureListToString(arrayList);
        jumpDataListToString(arrayList);
        powerListToString(arrayList);
        weightListToString(arrayList);
        cadenceListToString(arrayList);
        resistanceListToString(arrayList);
        paddleListToString(arrayList);
        skippingSpeedToString(arrayList);
        segmentDataListToString(arrayList);
        heartRecoveryRateToString(arrayList);
        listToString(arrayList, this.mExtendDetailList);
        listToString(arrayList, this.mMessageList);
        listToString(arrayList, this.mHeartRateAreaList);
        if (arrayList.size() == 0) {
            return DETAIL_NULL;
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer2.append(it.next());
        }
        return stringBuffer2.toString();
    }

    private void listToString(ArrayList<String> arrayList, List<? extends Serializable> list) {
        if (koq.b(list)) {
            return;
        }
        for (Serializable serializable : list) {
            if (serializable != null) {
                arrayList.add(serializable.toString());
            }
        }
    }

    private void heartRecoveryRateToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mHeartRecoveryRateList != null) {
            stringBuffer.setLength(0);
            for (HeartRateData heartRateData : this.mHeartRecoveryRateList) {
                stringBuffer.append(STEP_RATE_LIST_TAG).append(CONTENT_KEY).append(heartRateData.acquireTime()).append(";").append(CONTENT_VALUE).append(heartRateData.acquireHeartRate()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void segmentDataListToString(ArrayList<String> arrayList) {
        if (koq.b(this.mSegmentDataList)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(120);
        for (CommonSegment commonSegment : this.mSegmentDataList) {
            if (commonSegment != null) {
                commonSegment.toTrackString(stringBuffer);
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void stepRateToString(ArrayList<String> arrayList, StringBuffer stringBuffer) {
        if (this.mStepRateList != null) {
            stringBuffer.setLength(0);
            Iterator<StepRateData> it = this.mStepRateList.iterator();
            while (it.hasNext()) {
                StepRateData next = it.next();
                stringBuffer.append(STEP_RATE_LIST_TAG).append(CONTENT_KEY).append(next.acquireTime()).append(";").append(CONTENT_VALUE).append(next.acquireStepRate()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void paceDataToString(ArrayList<String> arrayList, StringBuffer stringBuffer, Map<Integer, Float> map, String str) {
        if (map != null) {
            for (Map.Entry<Integer, Float> entry : map.entrySet()) {
                stringBuffer.append(str).append(CONTENT_KEY).append(entry.getKey()).append(";").append(CONTENT_VALUE).append(entry.getValue()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void lbsDataToString(ArrayList<String> arrayList, StringBuffer stringBuffer) {
        Map<Long, double[]> map = this.mLbsDataMap;
        if (map != null) {
            for (Map.Entry<Long, double[]> entry : map.entrySet()) {
                try {
                    stringBuffer.append(LBS_DATA_MAP_TAG).append(CONTENT_KEY).append(entry.getKey()).append(";").append("lat=").append(entry.getValue()[0]).append(";").append("lon=").append(entry.getValue()[1]).append(";").append("alt=").append(entry.getValue()[2]).append(";").append(RecordAction.ACT_COST_TIME_TAG).append(entry.getValue()[3]).append(";").append(System.lineSeparator());
                    arrayList.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                } catch (IndexOutOfBoundsException unused) {
                    LogUtil.d(TAG, "IndexOutOfBoundsException toString has error");
                } catch (NumberFormatException unused2) {
                    LogUtil.d(TAG, "NumberFormatException toString has error");
                }
            }
        }
    }

    private void heartRateToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<HeartRateData> arrayList2 = this.mHeartRateList;
        if (arrayList2 == null) {
            LogUtil.c(TAG, "heartRateToString() mHeartRateList is null");
            return;
        }
        Iterator<HeartRateData> it = arrayList2.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            if (next == null) {
                LogUtil.c(TAG, "heartRateToString() heartRateData is null");
            } else {
                stringBuffer.append(HEART_RATE_LIST_TAG).append(CONTENT_KEY).append(next.acquireTime()).append(";").append(CONTENT_VALUE).append(next.acquireHeartRate()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void invalidHeartRateToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<HeartRateData> arrayList2 = this.mInvalidHeartRateList;
        if (arrayList2 == null) {
            LogUtil.c(TAG, "invalidHeartRateToString() mInvalidHeartRateList is null");
            return;
        }
        Iterator<HeartRateData> it = arrayList2.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            if (next == null) {
                LogUtil.c(TAG, "invalidHeartRateToString() invalidHeartRateData is null");
            } else {
                stringBuffer.append(INVALID_HEART_RATE_LIST_TAG).append(CONTENT_KEY).append(next.acquireTime()).append(";").append(CONTENT_VALUE).append(next.acquireHeartRate()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void altitudeListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<knz> arrayList2 = this.mAltitudeList;
        if (arrayList2 == null) {
            ReleaseLogUtil.c(TAG, "mAltitudeList is null");
            return;
        }
        Iterator<knz> it = arrayList2.iterator();
        while (it.hasNext()) {
            knz next = it.next();
            if (next == null) {
                ReleaseLogUtil.c(TAG, "altitudeData is null");
            } else {
                stringBuffer.append(ALTITUDE_TAG).append(CONTENT_KEY).append(next.acquireTime()).append(";").append(CONTENT_VALUE).append(next.c()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void markPointListToString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList<MarkPoint> arrayList2 = this.mMarkPointList;
        if (arrayList2 == null) {
            ReleaseLogUtil.c(TAG, "mAltitudeList is null");
            return;
        }
        Iterator<MarkPoint> it = arrayList2.iterator();
        while (it.hasNext()) {
            MarkPoint next = it.next();
            if (next == null) {
                ReleaseLogUtil.c(TAG, "markPoint is null");
            } else {
                sb.append(MARK_POINT_TAG);
                sb.append("no");
                sb.append(next.b());
                sb.append(";");
                sb.append(RecordAction.ACT_TARGET_TYPE_TAG);
                sb.append(next.j());
                sb.append(";");
                sb.append("c=");
                sb.append(next.e());
                sb.append(";");
                sb.append(RecordAction.ACT_COST_TIME_TAG);
                sb.append(next.acquireTime());
                sb.append(";");
                sb.append("lng=");
                sb.append(next.a());
                sb.append(";");
                sb.append("lat=");
                sb.append(next.d());
                sb.append(";");
                sb.append("m=");
                sb.append(next.c());
                sb.append(";");
                sb.append(System.lineSeparator());
                arrayList.add(sb.toString());
                sb.setLength(0);
            }
        }
    }

    private void spo2ListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<kof> list = this.mSpo2List;
        if (list == null) {
            return;
        }
        for (kof kofVar : list) {
            stringBuffer.append(SPO2_TAG).append(CONTENT_KEY).append(kofVar.acquireTime()).append(";").append(CONTENT_VALUE).append(kofVar.b()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void jumpDataListToString(ArrayList<String> arrayList) {
        if (this.mJumpDataList == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (ixt ixtVar : this.mJumpDataList) {
            stringBuffer.append(JUMP_TAG).append(CONTENT_KEY).append(ixtVar.b()).append(";").append("h=").append(ixtVar.c()).append(";").append(RecordAction.ACT_COST_TIME_TAG).append(ixtVar.e()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void runningPostureListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<ffs> arrayList2 = this.mRunningPostureList;
        if (arrayList2 != null) {
            Iterator<ffs> it = arrayList2.iterator();
            while (it.hasNext()) {
                ffs next = it.next();
                stringBuffer.append(RUNNING_POSTURE_TAG).append(CONTENT_KEY).append(next.g()).append(";").append("gct=").append(next.b()).append(";").append("gia=").append(next.e()).append(";").append("sa=").append(next.i()).append(";").append("ee=").append(next.a()).append(";").append("fsp=").append(next.d()).append(";").append("wsp=").append(next.h()).append(";").append("hsp=").append(next.c()).append(";").append("aht=").append(next.l()).append(";").append("htr=").append(next.o()).append(";").append("ap=").append(next.m()).append(";").append("vo=").append(next.t()).append(";").append("gtb=").append(next.n()).append(";").append("vr=").append(next.r()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void powerListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<koc> list = this.mPowerList;
        if (list == null) {
            return;
        }
        for (koc kocVar : list) {
            stringBuffer.append(POWER_TAG).append(CONTENT_KEY).append(kocVar.acquireTime()).append(";").append(CONTENT_VALUE).append(kocVar.b()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void weightListToString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        List<kom> list = this.mWeightList;
        if (list == null) {
            return;
        }
        for (kom komVar : list) {
            sb.append(WEIGHT_TAG);
            sb.append(CONTENT_KEY);
            sb.append(komVar.acquireTime());
            sb.append(";");
            sb.append(CONTENT_VALUE);
            sb.append(komVar.d());
            sb.append(";");
            sb.append(System.lineSeparator());
            arrayList.add(sb.toString());
            sb.setLength(0);
        }
    }

    private void cadenceListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<ffn> list = this.mRidePostureDataList;
        if (list == null) {
            return;
        }
        for (ffn ffnVar : list) {
            stringBuffer.append(CADENCE_TAG).append(CONTENT_KEY).append(ffnVar.acquireTime()).append(";").append(CONTENT_VALUE).append(ffnVar.e()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void resistanceListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<Integer> list = this.mResistanceList;
        if (list == null) {
            return;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(RESISTANCE_TAG).append(CONTENT_KEY).append(it.next()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void paddleListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<knw> list = this.mPaddleFrequencyList;
        if (list == null) {
            return;
        }
        for (knw knwVar : list) {
            stringBuffer.append(PADDLE_TAG).append(CONTENT_KEY).append(knwVar.acquireTime()).append(";").append(CONTENT_VALUE).append(knwVar.b()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    private void skippingSpeedToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<kob> list = this.mSkippingSpeedList;
        if (list == null) {
            return;
        }
        for (kob kobVar : list) {
            stringBuffer.append(SKIP_SPEED_TAG).append(CONTENT_KEY).append(kobVar.acquireTime()).append(";").append(CONTENT_VALUE).append(kobVar.c()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
    }

    public boolean isValidHeartRateList() {
        ArrayList<HeartRateData> arrayList = this.mHeartRateList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isValidInvalidHeartRateList() {
        ArrayList<HeartRateData> arrayList = this.mInvalidHeartRateList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isValidPaceMap() {
        Map<Integer, Float> map = this.mPaceMap;
        return map != null && map.size() > 0;
    }

    public boolean isValidCadenceData() {
        if (this.mRidePostureDataList == null) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mRidePostureDataList.size(); i2++) {
            if (this.mRidePostureDataList.get(i2) != null) {
                i += this.mRidePostureDataList.get(i2).e();
            }
        }
        return i != 0 && this.mRidePostureDataList.size() > 0;
    }

    public boolean isValidPaddleData() {
        List<knw> list = this.mPaddleFrequencyList;
        return list != null && list.size() > 0;
    }

    public boolean isValidPowerData() {
        if (!koq.c(this.mPowerList)) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mPowerList.size(); i2++) {
            if (this.mPowerList.get(i2) != null) {
                i += this.mPowerList.get(i2).b();
            }
        }
        if (i == 0) {
            return false;
        }
        return koq.c(this.mPowerList);
    }

    public boolean isValidWeightData() {
        if (!koq.c(this.mWeightList)) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mWeightList.size(); i2++) {
            if (this.mWeightList.get(i2) != null) {
                i += this.mWeightList.get(i2).d();
            }
        }
        if (i == 0) {
            return false;
        }
        return koq.c(this.mWeightList);
    }

    public boolean isValidSkippingData() {
        return koq.c(this.mSkippingSpeedList);
    }

    public boolean isValidLbsDataMap() {
        Map<Long, double[]> map = this.mLbsDataMap;
        return map != null && map.size() > 0;
    }

    public boolean isValidStepRateList() {
        if (this.mStepRateList == null) {
            LogUtil.d(TAG, "MotionPath,isValidStepRateList");
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mStepRateList.size(); i2++) {
            if (this.mStepRateList.get(i2) != null) {
                i += this.mStepRateList.get(i2).acquireStepRate();
            }
        }
        return i != 0 && this.mStepRateList.size() > 0;
    }

    public boolean isValidRunningPostureList() {
        ArrayList<ffs> arrayList = this.mRunningPostureList;
        return arrayList != null && arrayList.size() > 0;
    }

    public Map<Integer, Float> localePaceMap() {
        if (UnitUtil.h()) {
            return this.mBritishPaceMap;
        }
        return this.mPaceMap;
    }

    public boolean isValidAltitudeList() {
        ArrayList<knz> arrayList = this.mAltitudeList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isValidSpo2List() {
        return koq.c(this.mSpo2List);
    }

    public List<kol> requestSwolfList() {
        return this.mSwolfList;
    }

    public void saveSwolfList(List<kol> list) {
        this.mSwolfList = list;
    }

    public List<kog> requestPullFreqList() {
        return this.mPullFreqList;
    }

    public void savePullFreqList(List<kog> list) {
        this.mPullFreqList = list;
    }

    public List<koh> requestRealTimePaceList() {
        return this.mRealTimePaceList;
    }

    public List<koi> requestRealTimeSpeedList() {
        return this.mRealTimeSpeedList;
    }

    public List<koe> requestSpeedList() {
        return this.mSpeedList;
    }

    public void saveSpeedList(List<koe> list) {
        this.mSpeedList = list;
        if (isValidSpeedList()) {
            int size = this.mSpeedList.size();
            ArrayList arrayList = new ArrayList(size);
            ArrayList arrayList2 = new ArrayList(size);
            for (koe koeVar : this.mSpeedList) {
                if (koeVar != null) {
                    arrayList.add(koeVar.d());
                    arrayList2.add(koeVar.e());
                }
            }
            this.mRealTimePaceList = new CopyOnWriteArrayList(arrayList);
            this.mRealTimeSpeedList = new CopyOnWriteArrayList(arrayList2);
        }
    }

    private void swolfListToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        List<kol> list2 = this.mSwolfList;
        if (list2 != null) {
            for (kol kolVar : list2) {
                stringBuffer.append(SWOLF_TAG).append(CONTENT_KEY).append(kolVar.acquireTime()).append(";").append(CONTENT_VALUE).append(kolVar.c()).append(";").append(System.lineSeparator());
                list.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void pullFreqListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<kog> list = this.mPullFreqList;
        if (list != null) {
            for (kog kogVar : list) {
                stringBuffer.append(PULL_FREQ_TAG).append(CONTENT_KEY).append(kogVar.acquireTime()).append(";").append(CONTENT_VALUE).append(kogVar.d()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    private void speedPaceListListToString(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<koe> list = this.mSpeedList;
        if (list == null) {
            LogUtil.e(TAG, "speedPaceListListToString mSpeedList == null");
            return;
        }
        for (koe koeVar : list) {
            if (koeVar == null) {
                LogUtil.e(TAG, "speedPaceListListToString speed data is null");
            } else {
                stringBuffer.append(REAL_TIME_SPEED_TAG).append(CONTENT_KEY).append(koeVar.acquireTime()).append(";").append(CONTENT_VALUE).append(koeVar.a()).append(";").append(System.lineSeparator());
                arrayList.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
    }

    public boolean isValidSwolfList() {
        boolean z = false;
        if (koq.b(this.mSwolfList)) {
            return false;
        }
        Iterator<kol> it = this.mSwolfList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            kol next = it.next();
            if (next != null && next.c() > 0) {
                break;
            }
        }
        LogUtil.d(TAG, "mSwolfList size = ", Integer.valueOf(this.mSwolfList.size()), " isAll zero or minus", Boolean.valueOf(z));
        return !z;
    }

    public boolean isValidPullFreqList() {
        boolean z = false;
        if (koq.b(this.mPullFreqList)) {
            return false;
        }
        Iterator<kog> it = this.mPullFreqList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            kog next = it.next();
            if (next != null && next.d() > 0) {
                break;
            }
        }
        LogUtil.d(TAG, "mPullFreqList size = ", Integer.valueOf(this.mPullFreqList.size()), " isAll zero or minus", Boolean.valueOf(z));
        return !z;
    }

    public boolean isValidRealtimePaceList() {
        List<koh> list = this.mRealTimePaceList;
        return list != null && list.size() > 0;
    }

    public boolean isValidSpeedList() {
        boolean z = false;
        if (koq.b(this.mSpeedList)) {
            return false;
        }
        Iterator<koe> it = this.mSpeedList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            koe next = it.next();
            if (next != null && next.a() > 0.0f) {
                break;
            }
        }
        LogUtil.d(TAG, "speedList size = ", Integer.valueOf(this.mSpeedList.size()), " isAll zero ", Boolean.valueOf(z));
        return !z;
    }
}
