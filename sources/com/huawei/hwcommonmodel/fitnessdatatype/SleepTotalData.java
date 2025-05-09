package com.huawei.hwcommonmodel.fitnessdatatype;

import defpackage.jdy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class SleepTotalData implements Serializable {
    public static final int MANUAL_DATA = 5;
    public static final int NORMAL_DATA = 4;
    public static final int PHONE_DATA = 3;
    public static final int PILLOW_DATA = 2;
    public static final int WEAR_CORE_DATA = 1;
    private int lastMonthPhoneSuggestFallTime;
    private int lastMonthPhoneSuggestTotalSleepTime;
    private int lastMonthPhoneSuggestWakeTime;
    private int lastWeekPhoneSuggestFallTime;
    private int lastWeekPhoneSuggestTotalSleepTime;
    private int lastWeekPhoneSuggestWakeTime;
    private int mAvgCommonDeepSleepTime;
    private int mAvgCommonShallowSleepTime;
    private int mAvgCommonSleepTime;
    private int mAvgManualBedTime;
    private int mAvgManualRisingTime;
    private int mAvgManualSleepTime;
    private int mAvgManualWakeTime;
    private int mAvgPhoneDeepSleepTime;
    private int mAvgPhoneShallowSleepTime;
    private int mBreathQualityData;
    private int mCommonFallTime;
    private int mCommonWakeUpTime;
    private long mCoreSleepFallTime;
    private long mCoreSleepWakeupTime;
    private int mDeepSleepPart;
    private int mDeepSleepTime;
    private int mDeivceCategory;
    private String mDeviceId;
    private int mDeviceType;
    private String mEnvNoiseMetaData;
    private String mEnvNoiseSeqData;
    private int mFallTime;
    private boolean mHasPhoneCore;
    private boolean mHasPillowCore;
    private boolean mHasWearCore;
    private boolean mHasWearCoreData;
    private boolean mIsCommon;
    private boolean mIsCoreSleep;
    private boolean mIsHasPhoneData;
    private boolean mIsHybrid;
    private boolean mIsManual;
    private boolean mIsPhone;
    private boolean mIsPillow;
    private int mLastMonthTotalSleepTime;
    private int mLastWeekTotalSleepTime;
    private int mManualBedTime;
    private int mManualOnTime;
    private float mManualSleepEfficiency;
    private int mNewWeekMonthYearType;
    private int mNightTotalSleepTime;
    private int mNoonSleepTime;
    private long mPhoneEndTime;
    private long mPhoneStartTime;
    private int mScore;
    private int mShallowSleepTime;
    private Date mSleepDay;
    private long mSleepDayTime;
    private int mSleepEndReason;
    private String mSleepLevel;
    private long mSleepMonitorEndTime;
    private long mSleepMonitorStartTime;
    private int mSlumberTime;
    private int mSnoreFreq;
    private int mTimePeriodType;
    private int mTypeNum;
    private double mValidData;
    private int mWakeUpTime;
    private int mWakeupDuration;
    private int mWakeupTimes;
    private int phoneSuggestFallTime;
    private int phoneSuggestTotalSleepTime;
    private int phoneSuggestWakeTime;
    private int yearCommonSleepCount;
    private int yearCommonSleepTime;
    private int yearCoreCount;
    private int yearDailyCoreDeepSleepTime;
    private int yearDailyCoreRemSleepTime;
    private int yearDailyCoreShallowSleepTime;
    private int yearDailyCoreSleepTime;
    private int yearDailyManualBedTime;
    private float yearDailyManualSleepEfficiency;
    private int yearDailyManualSleepTime;
    private int yearDailyPhoneSleepTime;
    private int yearManualCount;
    private int yearPhoneCount;
    private int yearTotalCoreSleepTime;
    private int yearTotalDailyTime;
    private int yearTotalManualBedTime;
    private int yearTotalManualSleepTime;
    private int yearTotalPhoneSleepTime;
    private Map<String, Object> mUiDataMap = new ConcurrentHashMap();
    private List<Map<String, Long>> mNoonMapList = new ArrayList();
    private Map<Integer, Integer> mTypeNumMap = new ConcurrentHashMap();
    private int mDailyManualBedTime = 0;
    private int mDailyManualSleepTime = 0;
    private int mLastManualAvgBedTime = 0;
    private int mLastManualAvgRisingTime = 0;
    private int mLastManualAvgSleepTime = 0;
    private int mLastManualAvgWakeTime = 0;
    private int mDailyManualBedScore = 0;
    private int mDailyManualRisingScore = 0;
    private int mAvgPhoneBedTime = 0;
    private int mAvgPhoneRisingTime = 0;
    private int mDailyPhoneBedTime = 0;
    private int mDailyPhoneRisingScore = 0;
    private int mDailyPhoneBedScore = 0;
    private float mPhoneSleepEfficiency = 0.0f;
    private float mYearDailyPhoneSleepEfficiency = 0.0f;
    private int mYearDailyPhoneBedTime = 0;
    private int mYearDailyPhoneLatencyTime = 0;
    private int mAvgPhoneLatencyTime = 0;
    private int mDayPhoneLatencyTime = 0;
    private int mYearDailyManualLatencyTime = 0;
    private int mAvgManualLatencyTime = 0;
    private int mDayManualLatencyTime = 0;
    private int mManualDayScore = 0;
    private boolean mManualIsNotOnThree = false;
    private boolean mManualIsOnlyBed = false;
    private int mLastWeekManualBedTime = 0;
    private int mLastWeekManualOnTime = 0;
    private int mLastMonthManualBedTime = 0;
    private int mLastMonthManualOnTime = 0;
    private int mManualFallTime = -1;
    private int mPhoneFallTime = 0;
    private int mManualWakeupTime = -1;
    private long mManualGoBedTime = 0;
    private long mManualGetUpTime = 0;
    private int mManualSleepLatency = 0;
    private String mTimeZone = "";
    private List<String> mNoonSleepList = new ArrayList();
    private int mPhoneGoBedTime = -1;
    private int mPhoneGetupTime = -1;
    private int mPhoneSleepLatency = -1;
    private int mLastWeekFallTime = 0;
    private int mLastWeekWakeUpTime = 0;
    private int mLastMonthFallTime = 0;
    private int mLastMonthWakeUpTime = 0;
    private int mDailyDaySleepTime = 0;
    private int mDailyTotalSleepTime = 0;
    private int mDailyDeepSleepTime = 0;
    private int mDailyShallowSleepTime = 0;
    private int mDailySleepPart = 0;
    private int mDailyWakeupTimes = 0;
    private int mDailySlumberTime = 0;
    private int mDailyScore = 0;
    private int mDailyFallTime = 0;
    private int mDailyWakeUpTime = 0;
    private int mDailyFallScore = 0;
    private int mDailyWakeUpScore = 0;
    private int mDailyBreathQuality = 0;
    private int mDailyNoonSleepTime = 0;
    private int mDeepSleepRatenum = 0;
    private int mLightSleepRatenum = 0;
    private int mSlumSleepRatenum = 0;
    private String mSuggestTitle = "";
    private String mSuggestContent = "";
    private String mNoonStartTime = "";
    private String mNoonEndTime = "";
    private String mRecommendId = "";
    private int mType = 255;

    public long getManualGoBedTime() {
        return this.mManualGoBedTime;
    }

    public void setManualGoBedTime(long j) {
        this.mManualGoBedTime = j;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public int getManualSleepLatency() {
        return this.mManualSleepLatency;
    }

    public void setManualSleepLatency(int i) {
        this.mManualSleepLatency = i;
    }

    public long getManualGetUpTime() {
        return this.mManualGetUpTime;
    }

    public void setManualGetUpTime(long j) {
        this.mManualGetUpTime = j;
    }

    public float getPhoneSleepEfficiency() {
        return this.mPhoneSleepEfficiency;
    }

    public void setPhoneSleepEfficiency(float f) {
        this.mPhoneSleepEfficiency = f;
    }

    public float getYearDailyPhoneSleepEfficiency() {
        return this.mYearDailyPhoneSleepEfficiency;
    }

    public void setYearDailyPhoneSleepEfficiency(float f) {
        this.mYearDailyPhoneSleepEfficiency = f;
    }

    public int getYearDailyPhoneBedTime() {
        return this.mYearDailyPhoneBedTime;
    }

    public void setYearDailyPhoneBedTime(int i) {
        this.mYearDailyPhoneBedTime = i;
    }

    public int getYearDailyPhoneLatencyTime() {
        return this.mYearDailyPhoneLatencyTime;
    }

    public void setYearDailyPhoneLatencyTime(int i) {
        this.mYearDailyPhoneLatencyTime = i;
    }

    public int getAvgPhoneLatencyTime() {
        return this.mAvgPhoneLatencyTime;
    }

    public void setAvgPhoneLatencyTime(int i) {
        this.mAvgPhoneLatencyTime = i;
    }

    public int getDayPhoneLatencyTime() {
        return this.mDayPhoneLatencyTime;
    }

    public void setDayPhoneLatencyTime(int i) {
        this.mDayPhoneLatencyTime = i;
    }

    public int getYearDailyManualLatencyTime() {
        return this.mYearDailyManualLatencyTime;
    }

    public void setYearDailyManualLatencyTime(int i) {
        this.mYearDailyManualLatencyTime = i;
    }

    public int getAvgManualLatencyTime() {
        return this.mAvgManualLatencyTime;
    }

    public void setAvgManualLatencyTime(int i) {
        this.mAvgManualLatencyTime = i;
    }

    public int getDayManualLatencyTime() {
        return this.mDayManualLatencyTime;
    }

    public void setDayManualLatencyTime(int i) {
        this.mDayManualLatencyTime = i;
    }

    public int getAvgPhoneBedTime() {
        return this.mAvgPhoneBedTime;
    }

    public void setAvgPhoneBedTime(int i) {
        this.mAvgPhoneBedTime = i;
    }

    public int getAvgPhoneRisingTime() {
        return this.mAvgPhoneRisingTime;
    }

    public void setAvgPhoneRisingTime(int i) {
        this.mAvgPhoneRisingTime = i;
    }

    public int getDailyPhoneBedTime() {
        return this.mDailyPhoneBedTime;
    }

    public void setDailyPhoneBedTime(int i) {
        this.mDailyPhoneBedTime = i;
    }

    public int getDailyPhoneRisingScore() {
        return this.mDailyPhoneRisingScore;
    }

    public void setDailyPhoneRisingScore(int i) {
        this.mDailyPhoneRisingScore = i;
    }

    public int getDailyPhoneBedScore() {
        return this.mDailyPhoneBedScore;
    }

    public void setDailyPhoneBedScore(int i) {
        this.mDailyPhoneBedScore = i;
    }

    public Map getUiDataMap() {
        return this.mUiDataMap;
    }

    public void setUiDataMap(Map map) {
        this.mUiDataMap = map;
    }

    public int getWeekMonthYearType() {
        return this.mNewWeekMonthYearType;
    }

    public void setNewWeekMonthYearType(int i) {
        this.mNewWeekMonthYearType = i;
    }

    public int getYearTotalCoreSleepTime() {
        return this.yearTotalCoreSleepTime;
    }

    public void setYearTotalCoreSleepTime(int i) {
        this.yearTotalCoreSleepTime = i;
    }

    public int getYearTotalManualSleepTime() {
        return this.yearTotalManualSleepTime;
    }

    public int getYearTotalDailyTime() {
        return this.yearTotalDailyTime;
    }

    public int getYearCommonSleepTime() {
        return this.yearCommonSleepTime;
    }

    public void setYearCommonSleepTime(int i) {
        this.yearCommonSleepTime = i;
    }

    public void setYearTotalDailyTime(int i) {
        this.yearTotalDailyTime = i;
    }

    public void setYearTotalManualSleepTime(int i) {
        this.yearTotalManualSleepTime = i;
    }

    public int getYearTotalManualBedTime() {
        return this.yearTotalManualBedTime;
    }

    public void setYearTotalManualBedTime(int i) {
        this.yearTotalManualBedTime = i;
    }

    public int getYearTotalPhoneSleepTime() {
        return this.yearTotalPhoneSleepTime;
    }

    public void setYearTotalPhoneSleepTime(int i) {
        this.yearTotalPhoneSleepTime = i;
    }

    public int getYearDailyCoreSleepTime() {
        return this.yearDailyCoreSleepTime;
    }

    public void setYearDailyCoreSleepTime(int i) {
        this.yearDailyCoreSleepTime = i;
    }

    public int getYearDailyManualSleepTime() {
        return this.yearDailyManualSleepTime;
    }

    public void setYearDailyManualSleepTime(int i) {
        this.yearDailyManualSleepTime = i;
    }

    public int getYearDailyManualBedTime() {
        return this.yearDailyManualBedTime;
    }

    public void setYearDailyManualBedTime(int i) {
        this.yearDailyManualBedTime = i;
    }

    public float getYearDailyManualSleepEfficiency() {
        return this.yearDailyManualSleepEfficiency;
    }

    public void setYearDailyManualSleepEfficiency(float f) {
        this.yearDailyManualSleepEfficiency = f;
    }

    public int getYearDailyPhoneSleepTime() {
        return this.yearDailyPhoneSleepTime;
    }

    public void setYearDailyPhoneSleepTime(int i) {
        this.yearDailyPhoneSleepTime = i;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public int getSleepEndReason() {
        return this.mSleepEndReason;
    }

    public boolean isHasWearCoreData() {
        return this.mHasWearCoreData;
    }

    public void setHasWearCoreData(boolean z) {
        this.mHasWearCoreData = z;
    }

    public boolean hasWearCore() {
        return this.mHasWearCore;
    }

    public void setHasWearCore(boolean z) {
        this.mHasWearCore = z;
    }

    public boolean hasPillowCore() {
        return this.mHasPillowCore;
    }

    public void setHasPillowCore(boolean z) {
        this.mHasPillowCore = z;
    }

    public boolean hasPhoneCore() {
        return this.mHasPhoneCore;
    }

    public void setHasPhoneCore(boolean z) {
        this.mHasPhoneCore = z;
    }

    public void setSleepEndReason(int i) {
        this.mSleepEndReason = i;
    }

    public void setScore(int i) {
        this.mScore = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void setValidData(double d) {
        this.mValidData = ((Double) jdy.d(Double.valueOf(d))).doubleValue();
    }

    public int getScore() {
        return ((Integer) jdy.d(Integer.valueOf(this.mScore))).intValue();
    }

    public double getValidData() {
        return ((Double) jdy.d(Double.valueOf(this.mValidData))).doubleValue();
    }

    public void setFallTime(int i) {
        this.mFallTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getCommonFallTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mCommonFallTime))).intValue();
    }

    public void setCommonFallTime(int i) {
        this.mCommonFallTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getFallTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mFallTime))).intValue();
    }

    public void setWakeUpTime(int i) {
        this.mWakeUpTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWakeUpTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWakeUpTime))).intValue();
    }

    public void setCommonWakeUpTime(int i) {
        this.mCommonWakeUpTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getCommonWakeUpTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mCommonWakeUpTime))).intValue();
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
    }

    public String getSleepLevel() {
        return this.mSleepLevel;
    }

    public void setSleepLevel(String str) {
        this.mSleepLevel = str;
    }

    public int getDeivceCategory() {
        return this.mDeivceCategory;
    }

    public void setDeivceCategory(int i) {
        this.mDeivceCategory = i;
    }

    public void setType(int i) {
        this.mType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mType))).intValue();
    }

    public boolean isHybrid() {
        return this.mIsHybrid;
    }

    public void setIsHybrid(boolean z) {
        this.mIsHybrid = z;
    }

    public int getTotalSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mNightTotalSleepTime))).intValue();
    }

    public void setTotalSleepTime(int i) {
        this.mNightTotalSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getLastMonthTotalSleepTime() {
        return this.mLastMonthTotalSleepTime;
    }

    public void setLastMonthTotalSleepTime(int i) {
        this.mLastMonthTotalSleepTime = i;
    }

    public int getLastMonthFallTime() {
        return this.mLastMonthFallTime;
    }

    public void setLastMonthFallTime(int i) {
        this.mLastMonthFallTime = i;
    }

    public int getLastMonthWakeUpTime() {
        return this.mLastMonthWakeUpTime;
    }

    public void setLastMonthWakeUpTime(int i) {
        this.mLastMonthWakeUpTime = i;
    }

    public int getLastDailyTotalSleepTime() {
        return this.mLastWeekTotalSleepTime;
    }

    public void setLastWeekTotalSleepTime(int i) {
        this.mLastWeekTotalSleepTime = i;
    }

    public List<Map<String, Long>> getNoonMapList() {
        return this.mNoonMapList;
    }

    public void setNoonMapList(List<Map<String, Long>> list) {
        this.mNoonMapList = list;
    }

    public float getManualSleepEfficiency() {
        return this.mManualSleepEfficiency;
    }

    public void setManualSleepEfficiency(float f) {
        this.mManualSleepEfficiency = f;
    }

    public int getLastDailyFallTime() {
        return this.mLastWeekFallTime;
    }

    public void setLastWeekFallTime(int i) {
        this.mLastWeekFallTime = i;
    }

    public int getTypeNum() {
        return this.mTypeNum;
    }

    public void setTypeNum(int i) {
        this.mTypeNum = i;
    }

    public int getLastDailyWakeUpTime() {
        return this.mLastWeekWakeUpTime;
    }

    public void setLastWeekWakeUpTime(int i) {
        this.mLastWeekWakeUpTime = i;
    }

    public boolean isHasPhoneData() {
        return this.mIsHasPhoneData;
    }

    public void setIsHasPhoneData(boolean z) {
        this.mIsHasPhoneData = z;
    }

    public String getEnvNoiseSeqData() {
        return this.mEnvNoiseSeqData;
    }

    public void setEnvNoiseSeqData(String str) {
        this.mEnvNoiseSeqData = str;
    }

    public long getSleepMonitorEndTime() {
        return this.mSleepMonitorEndTime;
    }

    public void setSleepMonitorEndTime(long j) {
        this.mSleepMonitorEndTime = j;
    }

    public String getEnvNoiseMetaData() {
        return this.mEnvNoiseMetaData;
    }

    public void setEnvNoiseMetaData(String str) {
        this.mEnvNoiseMetaData = str;
    }

    public long getSleepMonitorStartTime() {
        return this.mSleepMonitorStartTime;
    }

    public void setSleepMonitorStartTime(long j) {
        this.mSleepMonitorStartTime = j;
    }

    public void setDeepSleepTime(int i) {
        this.mDeepSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeepSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDeepSleepTime))).intValue();
    }

    public void setShallowSleepTime(int i) {
        this.mShallowSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getShallowSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mShallowSleepTime))).intValue();
    }

    public void setNoonSleepTime(int i) {
        this.mNoonSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getNoonSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mNoonSleepTime))).intValue();
    }

    public List<String> getNoonSleepList() {
        return this.mNoonSleepList;
    }

    public void setNoonSleepList(List<String> list) {
        this.mNoonSleepList = list;
    }

    public void setSlumberSleepTime(int i) {
        this.mSlumberTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSlumberSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mSlumberTime))).intValue();
    }

    public void setWakeupTimes(int i) {
        this.mWakeupTimes = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWakeupTimes() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWakeupTimes))).intValue();
    }

    public void setWakeupDuration(int i) {
        this.mWakeupDuration = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWakeupDuration() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWakeupDuration))).intValue();
    }

    public void setDeepSleepPart(int i) {
        this.mDeepSleepPart = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeepSleepPart() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDeepSleepPart))).intValue();
    }

    public void setSnoreFreq(int i) {
        this.mSnoreFreq = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSnoreFreq() {
        return ((Integer) jdy.d(Integer.valueOf(this.mSnoreFreq))).intValue();
    }

    public void setBreathQualityData(int i) {
        this.mBreathQualityData = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyManualBedScore() {
        return this.mDailyManualBedScore;
    }

    public void setDailyManualBedScore(int i) {
        this.mDailyManualBedScore = i;
    }

    public int getDailyManualRisingScore() {
        return this.mDailyManualRisingScore;
    }

    public void setDailyManualRisingScore(int i) {
        this.mDailyManualRisingScore = i;
    }

    public int getBreathQualityData() {
        return ((Integer) jdy.d(Integer.valueOf(this.mBreathQualityData))).intValue();
    }

    public int getAvgManualBedTime() {
        return this.mAvgManualBedTime;
    }

    public void setAvgManualBedTime(int i) {
        this.mAvgManualBedTime = i;
    }

    public int getAvgManualRisingTime() {
        return this.mAvgManualRisingTime;
    }

    public void setAvgManualRisingTime(int i) {
        this.mAvgManualRisingTime = i;
    }

    public int getAvgManualSleepTime() {
        return this.mAvgManualSleepTime;
    }

    public void setAvgManualSleepTime(int i) {
        this.mAvgManualSleepTime = i;
    }

    public int getAvgManualWakeTime() {
        return this.mAvgManualWakeTime;
    }

    public void setAvgManualWakeTime(int i) {
        this.mAvgManualWakeTime = i;
    }

    public int getmLastManualAvgBedTime() {
        return this.mLastManualAvgBedTime;
    }

    public void setmLastManualAvgBedTime(int i) {
        this.mLastManualAvgBedTime = i;
    }

    public int getmLastManualAvgRisingTime() {
        return this.mLastManualAvgRisingTime;
    }

    public void setmLastManualAvgRisingTime(int i) {
        this.mLastManualAvgRisingTime = i;
    }

    public int getmLastManualAvgSleepTime() {
        return this.mLastManualAvgSleepTime;
    }

    public int getLastWeekPhoneSuggestTotalSleepTime() {
        return this.lastWeekPhoneSuggestTotalSleepTime;
    }

    public void setLastWeekPhoneSuggestTotalSleepTime(int i) {
        this.lastWeekPhoneSuggestTotalSleepTime = i;
    }

    public int getLastWeekPhoneSuggestFallTime() {
        return this.lastWeekPhoneSuggestFallTime;
    }

    public void setLastWeekPhoneSuggestFallTime(int i) {
        this.lastWeekPhoneSuggestFallTime = i;
    }

    public int getLastWeekPhoneSuggestWakeTime() {
        return this.lastWeekPhoneSuggestWakeTime;
    }

    public void setLastWeekPhoneSuggestWakeTime(int i) {
        this.lastWeekPhoneSuggestWakeTime = i;
    }

    public int getLastMonthPhoneSuggestTotalSleepTime() {
        return this.lastMonthPhoneSuggestTotalSleepTime;
    }

    public void setLastMonthPhoneSuggestTotalSleepTime(int i) {
        this.lastMonthPhoneSuggestTotalSleepTime = i;
    }

    public int getLastMonthPhoneSuggestFallTime() {
        return this.lastMonthPhoneSuggestFallTime;
    }

    public void setLastMonthPhoneSuggestFallTime(int i) {
        this.lastMonthPhoneSuggestFallTime = i;
    }

    public int getLastMonthPhoneSuggestWakeTime() {
        return this.lastMonthPhoneSuggestWakeTime;
    }

    public void setLastMonthPhoneSuggestWakeTime(int i) {
        this.lastMonthPhoneSuggestWakeTime = i;
    }

    public void setmLastManualAvgSleepTime(int i) {
        this.mLastManualAvgSleepTime = i;
    }

    public int getmLastManualAvgWakeTime() {
        return this.mLastManualAvgWakeTime;
    }

    public void setmLastManualAvgWakeTime(int i) {
        this.mLastManualAvgWakeTime = i;
    }

    public long getSleepDayTime() {
        return ((Long) jdy.d(Long.valueOf(this.mSleepDayTime))).longValue();
    }

    public void setSleepDayTime(long j) {
        this.mSleepDayTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getPhoneSuggestTotalSleepTime() {
        return this.phoneSuggestTotalSleepTime;
    }

    public void setPhoneSuggestTotalSleepTime(int i) {
        this.phoneSuggestTotalSleepTime = i;
    }

    public int getPhoneSuggestFallTime() {
        return this.phoneSuggestFallTime;
    }

    public void setPhoneSuggestFallTime(int i) {
        this.phoneSuggestFallTime = i;
    }

    public int getPhoneSuggestWakeTime() {
        return this.phoneSuggestWakeTime;
    }

    public void setPhoneSuggestWakeTime(int i) {
        this.phoneSuggestWakeTime = i;
    }

    public long getCoreSleepFallTime() {
        return ((Long) jdy.d(Long.valueOf(this.mCoreSleepFallTime))).longValue();
    }

    public void setCoreSleepWakeupTime(long j) {
        this.mCoreSleepWakeupTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long getCoreSleepWakeupTime() {
        return ((Long) jdy.d(Long.valueOf(this.mCoreSleepWakeupTime))).longValue();
    }

    public int getDailyDaySleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyDaySleepTime))).intValue();
    }

    public void setDailyDaySleepTime(int i) {
        this.mDailyDaySleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyTotalSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyTotalSleepTime))).intValue();
    }

    public void setDailyTotalSleepTime(int i) {
        this.mDailyTotalSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyDeepSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyDeepSleepTime))).intValue();
    }

    public void setDailyDeepSleepTime(int i) {
        this.mDailyDeepSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyShallowSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyShallowSleepTime))).intValue();
    }

    public void setDailyShallowSleepTime(int i) {
        this.mDailyShallowSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailySleepPart() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailySleepPart))).intValue();
    }

    public void setDailySleepPart(int i) {
        this.mDailySleepPart = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyWakeupTimes() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyWakeupTimes))).intValue();
    }

    public void setDailyWakeupTimes(int i) {
        this.mDailyWakeupTimes = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailySlumberTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailySlumberTime))).intValue();
    }

    public void setDailySlumberTime(int i) {
        this.mDailySlumberTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyScore() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyScore))).intValue();
    }

    public void setDailyScore(int i) {
        this.mDailyScore = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyFallTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyFallTime))).intValue();
    }

    public void setDailyFallTime(int i) {
        this.mDailyFallTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyWakeUpTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyWakeUpTime))).intValue();
    }

    public void setDailyWakeUpTime(int i) {
        this.mDailyWakeUpTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyFallScore() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyFallScore))).intValue();
    }

    public void setDailyFallScore(int i) {
        this.mDailyFallScore = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyWakeUpScore() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyWakeUpScore))).intValue();
    }

    public void setDailyWakeUpScore(int i) {
        this.mDailyWakeUpScore = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyBreathQuality() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyBreathQuality))).intValue();
    }

    public void setDailyBreathQuality(int i) {
        this.mDailyBreathQuality = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDailyNoonSleepTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDailyNoonSleepTime))).intValue();
    }

    public void setDailyNoonSleepTime(int i) {
        this.mDailyNoonSleepTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public Map<Integer, Integer> getTypeNumMap() {
        return this.mTypeNumMap;
    }

    public void setTypeNumMap(Map<Integer, Integer> map) {
        this.mTypeNumMap = map;
    }

    public long getPhoneStartTime() {
        return this.mPhoneStartTime;
    }

    public void setPhoneStartTime(long j) {
        this.mPhoneStartTime = j;
    }

    public int getPhoneSleepLatency() {
        return this.mPhoneSleepLatency;
    }

    public void setPhoneSleepLatency(int i) {
        this.mPhoneSleepLatency = i;
    }

    public long getPhoneEndTime() {
        return this.mPhoneEndTime;
    }

    public int getPhoneGoBedTime() {
        return this.mPhoneGoBedTime;
    }

    public void setPhoneGoBedTime(int i) {
        this.mPhoneGoBedTime = i;
    }

    public int getPhoneGetupTime() {
        return this.mPhoneGetupTime;
    }

    public void setPhoneGetupTime(int i) {
        this.mPhoneGetupTime = i;
    }

    public int getManualWakeUpTime() {
        return this.mManualWakeupTime;
    }

    public void setManualWakeUpTime(int i) {
        this.mManualWakeupTime = i;
    }

    public void setPhoneEndTime(long j) {
        this.mPhoneEndTime = j;
    }

    public int getYearCommonSleepCount() {
        return this.yearCommonSleepCount;
    }

    public void setYearCommonSleepCount(int i) {
        this.yearCommonSleepCount = i;
    }

    public void setCoreSleepFallTime(long j) {
        this.mCoreSleepFallTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getDeepSleepRateNum() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDeepSleepRatenum))).intValue();
    }

    public void setDeepSleepRateNum(int i) {
        this.mDeepSleepRatenum = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getLightSleepRateNum() {
        return ((Integer) jdy.d(Integer.valueOf(this.mLightSleepRatenum))).intValue();
    }

    public void setLightSleepRateNum(int i) {
        this.mLightSleepRatenum = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSlumSleepRateNum() {
        return ((Integer) jdy.d(Integer.valueOf(this.mSlumSleepRatenum))).intValue();
    }

    public void setSlumSleepRateNum(int i) {
        this.mSlumSleepRatenum = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPhoneFallTime() {
        return this.mPhoneFallTime;
    }

    public void setPhoneFallTime(int i) {
        this.mPhoneFallTime = i;
    }

    public int getManualFallTime() {
        return this.mManualFallTime;
    }

    public void setManualFallTime(int i) {
        this.mManualFallTime = i;
    }

    public int getmLastWeekManualBedTime() {
        return this.mLastWeekManualBedTime;
    }

    public void setmLastWeekManualBedTime(int i) {
        this.mLastWeekManualBedTime = i;
    }

    public int getmLastWeekManualOnTime() {
        return this.mLastWeekManualOnTime;
    }

    public void setmLastWeekManualOnTime(int i) {
        this.mLastWeekManualOnTime = i;
    }

    public int getmLastMonthManualBedTime() {
        return this.mLastMonthManualBedTime;
    }

    public void setmLastMonthManualBedTime(int i) {
        this.mLastMonthManualBedTime = i;
    }

    public int getmLastMonthManualOnTime() {
        return this.mLastMonthManualOnTime;
    }

    public void setmLastMonthManualOnTime(int i) {
        this.mLastMonthManualOnTime = i;
    }

    public int getYearDailyCoreDeepSleepTime() {
        return this.yearDailyCoreDeepSleepTime;
    }

    public void setYearDailyCoreDeepSleepTime(int i) {
        this.yearDailyCoreDeepSleepTime = i;
    }

    public int getYearDailyCoreShallowSleepTime() {
        return this.yearDailyCoreShallowSleepTime;
    }

    public void setYearDailyCoreShallowSleepTime(int i) {
        this.yearDailyCoreShallowSleepTime = i;
    }

    public int getYearDailyCoreRemSleepTime() {
        return this.yearDailyCoreRemSleepTime;
    }

    public void setYearDailyCoreRemSleepTime(int i) {
        this.yearDailyCoreRemSleepTime = i;
    }

    public boolean isManualIsNotOnThree() {
        return this.mManualIsNotOnThree;
    }

    public void setManualIsNotOnThree(boolean z) {
        this.mManualIsNotOnThree = z;
    }

    public boolean isManualIsOnlyBed() {
        return this.mManualIsOnlyBed;
    }

    public void setManualIsOnlyBed(boolean z) {
        this.mManualIsOnlyBed = z;
    }

    public int getManualDayScore() {
        return this.mManualDayScore;
    }

    public void setManualDayScore(int i) {
        this.mManualDayScore = i;
    }

    public boolean isCommon() {
        return this.mIsCommon;
    }

    public void setIsCommon(boolean z) {
        this.mIsCommon = z;
    }

    public boolean isManual() {
        return this.mIsManual;
    }

    public void setIsManual(boolean z) {
        this.mIsManual = z;
    }

    public String getSuggestTitle() {
        return (String) jdy.d(this.mSuggestTitle);
    }

    public void setSuggestTitle(String str) {
        this.mSuggestTitle = (String) jdy.d(str);
    }

    public String getSuggestContent() {
        return (String) jdy.d(this.mSuggestContent);
    }

    public void setSuggestContent(String str) {
        this.mSuggestContent = (String) jdy.d(str);
    }

    public void setRecommendId(String str) {
        this.mRecommendId = (String) jdy.d(str);
    }

    public String getNoonStartTime() {
        return (String) jdy.d(this.mNoonStartTime);
    }

    public void setNoonStartTime(String str) {
        this.mNoonStartTime = (String) jdy.d(str);
    }

    public String getNoonEndTime() {
        return (String) jdy.d(this.mNoonEndTime);
    }

    public void setNoonEndTime(String str) {
        this.mNoonEndTime = (String) jdy.d(str);
    }

    public boolean getIsCoreSleep() {
        return this.mIsCoreSleep;
    }

    public void setIsCoreSleep(boolean z) {
        this.mIsCoreSleep = z;
    }

    public int getTimePeriodType() {
        return this.mTimePeriodType;
    }

    public void setTimePeriodType(int i) {
        this.mTimePeriodType = i;
    }

    public Date getSleepDay() {
        return (Date) jdy.d(this.mSleepDay);
    }

    public void setSleepDay(Date date) {
        this.mSleepDay = (Date) jdy.d(date);
    }

    public int getYearPhoneCount() {
        return this.yearPhoneCount;
    }

    public void setYearPhoneCount(int i) {
        this.yearPhoneCount = i;
    }

    public int getYearCoreCount() {
        return this.yearCoreCount;
    }

    public void setYearCoreCount(int i) {
        this.yearCoreCount = i;
    }

    public int getYearManualCount() {
        return this.yearManualCount;
    }

    public void setYearManualCount(int i) {
        this.yearManualCount = i;
    }

    public boolean isDataValid() {
        return (getNoonSleepTime() == 0 && getShallowSleepTime() == 0 && getDeepSleepTime() == 0 && getSlumberSleepTime() == 0 && getTotalSleepTime() == 0 && getPhoneSuggestTotalSleepTime() == 0 && getManualBedTime() == 0 && getDailyNoonSleepTime() == 0 && getDailyShallowSleepTime() == 0 && getDailyDeepSleepTime() == 0 && getDailySlumberTime() == 0 && getDailyManualBedTime() == 0 && getAvgCommonDeepSleepTime() == 0 && getAvgCommonShallowSleepTime() == 0 && getAvgPhoneShallowSleepTime() == 0 && getAvgPhoneDeepSleepTime() == 0) ? false : true;
    }

    public boolean isValidScoringData() {
        return (getScore() != 0 || !getSuggestTitle().isEmpty() || !getSuggestContent().isEmpty() || getDeepSleepTime() != 0 || getShallowSleepTime() != 0 || getSlumberSleepTime() != 0 || getManualBedTime() != 0) || (getDailyScore() != 0 || getDailyDeepSleepTime() != 0 || getDailyShallowSleepTime() != 0 || getDailySlumberTime() != 0) || (getDailyManualBedTime() != 0) || (getAvgPhoneDeepSleepTime() != 0 || getAvgPhoneShallowSleepTime() != 0) || (getAvgCommonDeepSleepTime() != 0 || getAvgCommonShallowSleepTime() != 0);
    }

    public boolean isValidRingChartData() {
        return (getDeepSleepTime() == 0 && getShallowSleepTime() == 0 && getSlumberSleepTime() == 0 && getDailyDeepSleepTime() == 0 && getDailyShallowSleepTime() == 0 && getDailySlumberTime() == 0 && getAvgPhoneShallowSleepTime() == 0 && getAvgPhoneDeepSleepTime() == 0 && getAvgCommonShallowSleepTime() == 0 && getAvgCommonDeepSleepTime() == 0) ? false : true;
    }

    public boolean isPhone() {
        return this.mIsPhone;
    }

    public void setIsPhone(boolean z) {
        this.mIsPhone = z;
    }

    public boolean isPillow() {
        return this.mIsPillow;
    }

    public void setIsPillow(boolean z) {
        this.mIsPillow = z;
    }

    public int getManualBedTime() {
        return this.mManualBedTime;
    }

    public void setManualBedTime(int i) {
        this.mManualBedTime = i;
    }

    public int getManualOnTime() {
        return this.mManualOnTime;
    }

    public void setManualOnTime(int i) {
        this.mManualOnTime = i;
    }

    public int getDailyManualBedTime() {
        return this.mDailyManualBedTime;
    }

    public void setDailyManualBedTime(int i) {
        this.mDailyManualBedTime = i;
    }

    public int getDailyManualSleepTime() {
        return this.mDailyManualSleepTime;
    }

    public void setDailyManualSleepTime(int i) {
        this.mDailyManualSleepTime = i;
    }

    public int getAvgCommonDeepSleepTime() {
        return this.mAvgCommonDeepSleepTime;
    }

    public void setAvgCommonDeepSleepTime(int i) {
        this.mAvgCommonDeepSleepTime = i;
    }

    public int getAvgCommonSleepTime() {
        return this.mAvgCommonSleepTime;
    }

    public void setAvgCommonSleepTime(int i) {
        this.mAvgCommonSleepTime = i;
    }

    public int getAvgCommonShallowSleepTime() {
        return this.mAvgCommonShallowSleepTime;
    }

    public void setAvgCommonShallowSleepTime(int i) {
        this.mAvgCommonShallowSleepTime = i;
    }

    public int getAvgPhoneDeepSleepTime() {
        return this.mAvgPhoneDeepSleepTime;
    }

    public void setAvgPhoneDeepSleepTime(int i) {
        this.mAvgPhoneDeepSleepTime = i;
    }

    public int getAvgPhoneShallowSleepTime() {
        return this.mAvgPhoneShallowSleepTime;
    }

    public void setAvgPhoneShallowSleepTime(int i) {
        this.mAvgPhoneShallowSleepTime = i;
    }

    public int getDeepSleepTimePercent() {
        int i = this.mNightTotalSleepTime;
        if (i == 0) {
            return 0;
        }
        return Math.round((this.mDeepSleepTime / i) * 100.0f);
    }

    public int getShallowSleepTimePercent() {
        int i = this.mNightTotalSleepTime;
        if (i == 0) {
            return 0;
        }
        return Math.round((this.mShallowSleepTime / i) * 100.0f);
    }

    public String toString() {
        return "SleepTotalData{mUiDataMap=" + this.mUiDataMap + ", mNoonMapList=" + this.mNoonMapList + ", mTypeNumMap=" + this.mTypeNumMap + ", mType=" + this.mType + ", mDeepSleepTime=" + this.mDeepSleepTime + ", mManualBedTime=" + this.mManualBedTime + ", mManualOnTime=" + this.mManualOnTime + ", mManualSleepEfficiency=" + this.mManualSleepEfficiency + ", mNoonSleepTime=" + this.mNoonSleepTime + ", yearPhoneCount=" + this.yearPhoneCount + ", yearCoreCount=" + this.yearCoreCount + ", yearManualCount=" + this.yearManualCount + ", mAvgManualBedTime=" + this.mAvgManualBedTime + ", mAvgManualRisingTime=" + this.mAvgManualRisingTime + ", mAvgManualSleepTime=" + this.mAvgManualSleepTime + ", mAvgManualWakeTime=" + this.mAvgManualWakeTime + ", mDailyManualBedTime=" + this.mDailyManualBedTime + ", mDailyManualSleepTime=" + this.mDailyManualSleepTime + ", mLastManualAvgBedTime=" + this.mLastManualAvgBedTime + ", mLastManualAvgRisingTime=" + this.mLastManualAvgRisingTime + ", mLastManualAvgSleepTime=" + this.mLastManualAvgSleepTime + ", mLastManualAvgWakeTime=" + this.mLastManualAvgWakeTime + ", mDailyManualBedScore=" + this.mDailyManualBedScore + ", mDailyManualRisingScore=" + this.mDailyManualRisingScore + ", mAvgPhoneBedTime=" + this.mAvgPhoneBedTime + ", mAvgPhoneRisingTime=" + this.mAvgPhoneRisingTime + ", mDailyPhoneBedTime=" + this.mDailyPhoneBedTime + ", mDailyPhoneRisingScore=" + this.mDailyPhoneRisingScore + ", mDailyPhoneBedScore=" + this.mDailyPhoneBedScore + ", mPhoneSleepEfficiency=" + this.mPhoneSleepEfficiency + ", mYearDailyPhoneSleepEfficiency=" + this.mYearDailyPhoneSleepEfficiency + ", mYearDailyPhoneBedTime=" + this.mYearDailyPhoneBedTime + ", mYearDailyPhoneLatencyTime=" + this.mYearDailyPhoneLatencyTime + ", mAvgPhoneLatencyTime=" + this.mAvgPhoneLatencyTime + ", mDayPhoneLatencyTime=" + this.mDayPhoneLatencyTime + ", mYearDailyManualLatencyTime=" + this.mYearDailyManualLatencyTime + ", mAvgManualLatencyTime=" + this.mAvgManualLatencyTime + ", mDayManualLatencyTime=" + this.mDayManualLatencyTime + ", mManualDayScore=" + this.mManualDayScore + ", mManualIsNotOnThree=" + this.mManualIsNotOnThree + ", mManualIsOnlyBed=" + this.mManualIsOnlyBed + ", mLastWeekManualBedTime=" + this.mLastWeekManualBedTime + ", mLastWeekManualOnTime=" + this.mLastWeekManualOnTime + ", mLastMonthManualBedTime=" + this.mLastMonthManualBedTime + ", mLastMonthManualOnTime=" + this.mLastMonthManualOnTime + ", mManualFallTime=" + this.mManualFallTime + ", mPhoneFallTime=" + this.mPhoneFallTime + ", mManualWakeupTime=" + this.mManualWakeupTime + ", mManualGoBedTime=" + this.mManualGoBedTime + ", mManualGetUpTime=" + this.mManualGetUpTime + ", mManualSleepLatency=" + this.mManualSleepLatency + ", mTimeZone='" + this.mTimeZone + "', mNoonSleepList=" + this.mNoonSleepList + ", mShallowSleepTime=" + this.mShallowSleepTime + ", mSlumberTime=" + this.mSlumberTime + ", mWakeupTimes=" + this.mWakeupTimes + ", mWakeupDuration=" + this.mWakeupDuration + ", mDeepSleepPart=" + this.mDeepSleepPart + ", mSnoreFreq=" + this.mSnoreFreq + ", mPhoneStartTime=" + this.mPhoneStartTime + ", mPhoneEndTime=" + this.mPhoneEndTime + ", mPhoneGoBedTime=" + this.mPhoneGoBedTime + ", mPhoneGetupTime=" + this.mPhoneGetupTime + ", mPhoneSleepLatency=" + this.mPhoneSleepLatency + ", mIsPhone=" + this.mIsPhone + ", mIsPillow=" + this.mIsPillow + ", mIsManual=" + this.mIsManual + ", mIsCommon=" + this.mIsCommon + ", mBreathQualityData=" + this.mBreathQualityData + ", mLastWeekTotalSleepTime=" + this.mLastWeekTotalSleepTime + ", mLastWeekFallTime=" + this.mLastWeekFallTime + ", mLastWeekWakeUpTime=" + this.mLastWeekWakeUpTime + ", mLastMonthTotalSleepTime=" + this.mLastMonthTotalSleepTime + ", mLastMonthFallTime=" + this.mLastMonthFallTime + ", mLastMonthWakeUpTime=" + this.mLastMonthWakeUpTime + ", mIsHasPhoneData=" + this.mIsHasPhoneData + ", mHasWearCoreData=" + this.mHasWearCoreData + ", mHasWearCore=" + this.mHasWearCore + ", mHasPillowCore=" + this.mHasPillowCore + ", mHasPhoneCore=" + this.mHasPhoneCore + ", mScore=" + this.mScore + ", mFallTime=" + this.mFallTime + ", mTypeNum=" + this.mTypeNum + ", mCommonFallTime=" + this.mCommonFallTime + ", mWakeUpTime=" + this.mWakeUpTime + ", mCommonWakeUpTime=" + this.mCommonWakeUpTime + ", mNightTotalSleepTime=" + this.mNightTotalSleepTime + ", phoneSuggestTotalSleepTime=" + this.phoneSuggestTotalSleepTime + ", phoneSuggestFallTime=" + this.phoneSuggestFallTime + ", phoneSuggestWakeTime=" + this.phoneSuggestWakeTime + ", lastWeekPhoneSuggestTotalSleepTime=" + this.lastWeekPhoneSuggestTotalSleepTime + ", lastWeekPhoneSuggestFallTime=" + this.lastWeekPhoneSuggestFallTime + ", lastWeekPhoneSuggestWakeTime=" + this.lastWeekPhoneSuggestWakeTime + ", lastMonthPhoneSuggestTotalSleepTime=" + this.lastMonthPhoneSuggestTotalSleepTime + ", lastMonthPhoneSuggestFallTime=" + this.lastMonthPhoneSuggestFallTime + ", lastMonthPhoneSuggestWakeTime=" + this.lastMonthPhoneSuggestWakeTime + ", mEnvNoiseSeqData='" + this.mEnvNoiseSeqData + "', mSleepMonitorStartTime=" + this.mSleepMonitorStartTime + ", mSleepMonitorEndTime=" + this.mSleepMonitorEndTime + ", mEnvNoiseMetaData='" + this.mEnvNoiseMetaData + "', mValidData=" + this.mValidData + ", mDeviceType=" + this.mDeviceType + ", mDeviceId='" + this.mDeviceId + "', mDeivceCategory=" + this.mDeivceCategory + ", mSleepLevel='" + this.mSleepLevel + "', mSleepDayTime=" + this.mSleepDayTime + ", mCoreSleepFallTime=" + this.mCoreSleepFallTime + ", mCoreSleepWakeupTime=" + this.mCoreSleepWakeupTime + ", mDailyDaySleepTime=" + this.mDailyDaySleepTime + ", mDailyTotalSleepTime=" + this.mDailyTotalSleepTime + ", mDailyDeepSleepTime=" + this.mDailyDeepSleepTime + ", mDailyShallowSleepTime=" + this.mDailyShallowSleepTime + ", mDailySleepPart=" + this.mDailySleepPart + ", mDailyWakeupTimes=" + this.mDailyWakeupTimes + ", mDailySlumberTime=" + this.mDailySlumberTime + ", mDailyScore=" + this.mDailyScore + ", mDailyFallTime=" + this.mDailyFallTime + ", mDailyWakeUpTime=" + this.mDailyWakeUpTime + ", mDailyFallScore=" + this.mDailyFallScore + ", mDailyWakeUpScore=" + this.mDailyWakeUpScore + ", mDailyBreathQuality=" + this.mDailyBreathQuality + ", mDailyNoonSleepTime=" + this.mDailyNoonSleepTime + ", mDeepSleepRatenum=" + this.mDeepSleepRatenum + ", mLightSleepRatenum=" + this.mLightSleepRatenum + ", mSlumSleepRatenum=" + this.mSlumSleepRatenum + ", mSuggestTitle='" + this.mSuggestTitle + "', mSuggestContent='" + this.mSuggestContent + "', mNoonStartTime='" + this.mNoonStartTime + "', mNoonEndTime='" + this.mNoonEndTime + "', mRecommendId='" + this.mRecommendId + "', mIsCoreSleep=" + this.mIsCoreSleep + ", mTimePeriodType=" + this.mTimePeriodType + ", mSleepDay=" + this.mSleepDay + ", mSleepEndReason=" + this.mSleepEndReason + ", mNewWeekMonthYearType=" + this.mNewWeekMonthYearType + ", yearTotalCoreSleepTime=" + this.yearTotalCoreSleepTime + ", yearTotalManualSleepTime=" + this.yearTotalManualSleepTime + ", yearTotalManualBedTime=" + this.yearTotalManualBedTime + ", yearTotalPhoneSleepTime=" + this.yearTotalPhoneSleepTime + ", yearCommonSleepTime=" + this.yearCommonSleepTime + ", yearCommonSleepCount=" + this.yearCommonSleepCount + ", mAvgCommonDeepSleepTime=" + this.mAvgCommonDeepSleepTime + ", mAvgCommonShallowSleepTime=" + this.mAvgCommonShallowSleepTime + ", mAvgCommonSleepTime=" + this.mAvgCommonSleepTime + ", mAvgPhoneDeepSleepTime=" + this.mAvgPhoneDeepSleepTime + ", mAvgPhoneShallowSleepTime=" + this.mAvgPhoneShallowSleepTime + ", yearDailyCoreSleepTime=" + this.yearDailyCoreSleepTime + ", yearDailyCoreDeepSleepTime=" + this.yearDailyCoreDeepSleepTime + ", yearDailyCoreShallowSleepTime=" + this.yearDailyCoreShallowSleepTime + ", yearDailyCoreRemSleepTime=" + this.yearDailyCoreRemSleepTime + ", yearDailyManualSleepTime=" + this.yearDailyManualSleepTime + ", yearDailyManualBedTime=" + this.yearDailyManualBedTime + ", yearDailyManualSleepEfficiency=" + this.yearDailyManualSleepEfficiency + ", yearDailyPhoneSleepTime=" + this.yearDailyPhoneSleepTime + ", yearTotalDailyTime=" + this.yearTotalDailyTime + ", mIsHybrid=" + this.mIsHybrid + '}';
    }
}
