package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WorkoutRecordStatistic {
    private static final int ABNORMAL_DATA = -1;

    @SerializedName("extendList")
    private List<StatisticExtendDataStruct> extendList;
    private int highestBloodOxygen;
    private int lowestBloodOxygen;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECOVERY_HEART_RATE)
    private ArrayList<HeartRateData> recoveryHeartRateList;
    private List<TriathlonStruct> triathlonStructList;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_DATE_INFO)
    private long workoutDateInfo;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_EPOC)
    private int workoutEpoc;

    @SerializedName(HwExerciseConstants.JSON_NAME_ETRAINING_EFFECT)
    private int workoutEtrainingEffect;

    @SerializedName(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION)
    private long workoutExerciseDuration;

    @SerializedName(HwExerciseConstants.JSON_NAME_EXERCISE_ID)
    private String workoutExerciseId;

    @SerializedName(HwExerciseConstants.JSON_NAME_PEAK_MAX)
    private int workoutHrPeakMax;

    @SerializedName(HwExerciseConstants.JSON_NAME_PEAK_MIN)
    private int workoutHrPeakMin;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_LOAD_PEAK)
    private int workoutLoadPeak;

    @SerializedName(HwExerciseConstants.JSON_NAME_MAX_MET)
    private int workoutMaxMet;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECORD_CALORIE)
    private int workoutRecordCalorie;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE)
    private int workoutRecordDistance;

    @SerializedName(HwExerciseConstants.JSON_NAME_END_TIME)
    private long workoutRecordEndTime;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)
    private int workoutRecordId;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_SPEED)
    private int workoutRecordSpeed;

    @SerializedName(HwExerciseConstants.JSON_NAME_START_TIME)
    private long workoutRecordStartTime;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STATUS)
    private int workoutRecordStatus;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECORD_STEP)
    private long workoutRecordStep;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_TOTAL_TIME)
    private long workoutRecordTotalTime;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECOVERY_TIME)
    private int workoutRecoveryTime;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE)
    private int workoutType;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_CLIMB)
    private float workoutClimb = -1.0f;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_TYPE)
    private int swimType = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES)
    private int swimPullTimes = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_PULL_RATE)
    private int swimPullRate = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH)
    private int swimPoolLength = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_TRIP_TIMES)
    private int swimTripTimes = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF)
    private int swimAvgSwolf = -1;

    @SerializedName("accumulative_drop_height")
    private int accumulativeDropHeight = -1;

    @SerializedName("highest_altitude")
    private int highestAltitude = -1;

    @SerializedName("lowest_altitude")
    private int lowestAltitude = -1;

    @SerializedName("swolf_base_km")
    private int swolfBaseKm = -1;

    @SerializedName("swolf_base_mile")
    private int swolfBaseMile = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_ANAEROBIC_TRAINING_EFFECT)
    private int anaerobicTrainingEffect = -1;

    @SerializedName("half_marathon_time")
    private int halfMarathonTime = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_TOTAL_MARATHON_TIME)
    private int totalMarathonTime = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_RECORD_FLAG)
    private int recordFlag = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_HEART_RATE_TYPE)
    private int workoutHeartRateType = -1;

    @SerializedName("mActiveDuration")
    private int activeDuration = -1;

    @SerializedName("mJumpTimes")
    private int jumpTimes = -1;

    @SerializedName("mMaxJumpHeight")
    private int maxJumpHeight = -1;

    @SerializedName("mMaxJumpDuration")
    private int maxJumpDuration = -1;

    @SerializedName("mMaxRunSpeed")
    private int maxRunSpeed = -1;

    @SerializedName("mRunScore")
    private int runScore = -1;

    @SerializedName("mMoveScore")
    private int moveScore = -1;

    @SerializedName("mJumpScore")
    private int jumpScore = -1;

    @SerializedName("mTotalScore")
    private int totalScore = -1;

    @SerializedName("mExplosiveScore")
    private int explosiveScore = -1;

    @SerializedName("mIntenseScore")
    private int intenseScore = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_COUNT)
    private int golfSwingCount = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_BACK_SWING_TIME)
    private int golfBackSwingTime = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_DOWN_SWING_TIME)
    private int golfDownSwingTime = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_SPEED)
    private int golfSwingSpeed = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_MAX_SWING_SPEED)
    private int golfMaxSwingSpeed = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_GOLF_SWING_TEMPO)
    private int golfSwingTempo = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_MAX_SLOPE_DEGREE)
    private int skiMaxSlopeDegree = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_MAX_SLOPE_PERCENT)
    private int skiMaxSlopePercent = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_TOTAL_TIME)
    private long skiTotalTime = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_SKI_TOTAL_DISTANCE)
    private int skiTotalDistance = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE)
    private int temperature = 127;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_WEATHER)
    private int weather = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_TARGET_PERCENT)
    private int targetPercent = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_EXERCISE_LEVEL)
    private int exerciseLevel = -1;
    private int rompedPaceZoneMinValue = -1;
    private int marathonPaceZoneMinValue = -1;
    private int lacticAcidPaceZoneMinValue = -1;
    private int anaerobicPaceZoneMinValue = -1;
    private int maxOxygenPaceZoneMinValue = -1;
    private int maxOxygenPaceZoneMaxValue = -1;
    private long rompedTime = -1;
    private long marathonTime = -1;
    private long lacticAcidTime = -1;
    private long anaerobicTime = -1;
    private long maxOxygenTime = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_LONGEST_STREAK)
    private long longestStreak = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRIPPED)
    private long tripped = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_ALG_TYPE)
    private int algType = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_PLAN_ID)
    private String planId = "";

    @SerializedName(HwExerciseConstants.JSON_NAME_COURSE_NAME)
    private String courseName = "";

    @SerializedName(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME)
    private int planCourseTime = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_COURSE_TARGET_TYPE)
    private int courseTargetType = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_COURSE_TARGET_VALUE)
    private int courseTargetValue = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)
    private int trainingPoints = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_TRAINING_EXPERIENCE)
    private int trainingExperience = -1;

    @SerializedName(HwExerciseConstants.JSON_NAME_COURSE_MODIFIED_TIME)
    private long courseModifiedTime = -1;

    public void setExtendList(List<StatisticExtendDataStruct> list) {
        this.extendList = list;
    }

    public void setRecoveryHeartRateList(ArrayList<HeartRateData> arrayList) {
        this.recoveryHeartRateList = arrayList;
    }

    public int getRompedPaceZoneMinValue() {
        return this.rompedPaceZoneMinValue;
    }

    public void setRompedPaceZoneMinValue(int i) {
        this.rompedPaceZoneMinValue = i;
    }

    public int getMarathonPaceZoneMinValue() {
        return this.marathonPaceZoneMinValue;
    }

    public void setMarathonPaceZoneMinValue(int i) {
        this.marathonPaceZoneMinValue = i;
    }

    public int getLacticAcidPaceZoneMinValue() {
        return this.lacticAcidPaceZoneMinValue;
    }

    public void setLacticAcidPaceZoneMinValue(int i) {
        this.lacticAcidPaceZoneMinValue = i;
    }

    public int getAnaerobicPaceZoneMinValue() {
        return this.anaerobicPaceZoneMinValue;
    }

    public void setAnaerobicPaceZoneMinValue(int i) {
        this.anaerobicPaceZoneMinValue = i;
    }

    public int getMaxOxygenPaceZoneMinValue() {
        return this.maxOxygenPaceZoneMinValue;
    }

    public void setMaxOxygenPaceZoneMinValue(int i) {
        this.maxOxygenPaceZoneMinValue = i;
    }

    public int getMaxOxygenPaceZoneMaxValue() {
        return this.maxOxygenPaceZoneMaxValue;
    }

    public void setMaxOxygenPaceZoneMaxValue(int i) {
        this.maxOxygenPaceZoneMaxValue = i;
    }

    public long getRompedTime() {
        return this.rompedTime;
    }

    public void setRompedTime(long j) {
        this.rompedTime = j;
    }

    public long getMarathonTime() {
        return this.marathonTime;
    }

    public void setMarathonTime(long j) {
        this.marathonTime = j;
    }

    public long getLacticAcidTime() {
        return this.lacticAcidTime;
    }

    public void setLacticAcidTime(long j) {
        this.lacticAcidTime = j;
    }

    public long getAnaerobicTime() {
        return this.anaerobicTime;
    }

    public void setAnaerobicTime(long j) {
        this.anaerobicTime = j;
    }

    public long getMaxOxygenTime() {
        return this.maxOxygenTime;
    }

    public void setMaxOxygenTime(long j) {
        this.maxOxygenTime = j;
    }

    public long getLongestStreak() {
        return this.longestStreak;
    }

    public void setLongestStreak(long j) {
        this.longestStreak = j;
    }

    public long getTripped() {
        return this.tripped;
    }

    public void setTripped(long j) {
        this.tripped = j;
    }

    public int getAlgType() {
        return this.algType;
    }

    public void setAlgType(int i) {
        this.algType = i;
    }

    public int getTargetPercent() {
        return this.targetPercent;
    }

    public void setTargetPercent(int i) {
        this.targetPercent = i;
    }

    public int getExerciseLevel() {
        return this.exerciseLevel;
    }

    public void setExerciseLevel(int i) {
        this.exerciseLevel = i;
    }

    public int getAnaerobicTrainingEffect() {
        return this.anaerobicTrainingEffect;
    }

    public void setAnaerobicTrainingEffect(int i) {
        this.anaerobicTrainingEffect = i;
    }

    public int getHalfMarathonTime() {
        return this.halfMarathonTime;
    }

    public void setHalfMarathonTime(int i) {
        this.halfMarathonTime = i;
    }

    public int getTotalMarathonTime() {
        return this.totalMarathonTime;
    }

    public void setTotalMarathonTime(int i) {
        this.totalMarathonTime = i;
    }

    public int getSwolfBaseKm() {
        return this.swolfBaseKm;
    }

    public void setSwolfBaseKm(int i) {
        this.swolfBaseKm = i;
    }

    public int getSwolfBaseMile() {
        return this.swolfBaseMile;
    }

    public void setSwolfBaseMile(int i) {
        this.swolfBaseMile = i;
    }

    public int getAccumulativeDropHeight() {
        return this.accumulativeDropHeight;
    }

    public void setAccumulativeDropHeight(int i) {
        this.accumulativeDropHeight = i;
    }

    public int getHighestAltitude() {
        return this.highestAltitude;
    }

    public void setHighestAltitude(int i) {
        this.highestAltitude = i;
    }

    public int getLowestAltitude() {
        return this.lowestAltitude;
    }

    public void setLowestAltitude(int i) {
        this.lowestAltitude = i;
    }

    public int getSwimAvgSwolf() {
        return this.swimAvgSwolf;
    }

    public void setSwimAvgSwolf(int i) {
        this.swimAvgSwolf = i;
    }

    public int getSwimPoolLength() {
        return this.swimPoolLength;
    }

    public void setSwimPoolLength(int i) {
        this.swimPoolLength = i;
    }

    public int getSwimPullRate() {
        return this.swimPullRate;
    }

    public void setSwimPullRate(int i) {
        this.swimPullRate = i;
    }

    public int getSwimPullTimes() {
        return this.swimPullTimes;
    }

    public void setSwimPullTimes(int i) {
        this.swimPullTimes = i;
    }

    public int getSwimTripTimes() {
        return this.swimTripTimes;
    }

    public void setSwimTripTimes(int i) {
        this.swimTripTimes = i;
    }

    public int getSwimType() {
        return this.swimType;
    }

    public void setSwimType(int i) {
        this.swimType = i;
    }

    public int getWorkoutRecordId() {
        return this.workoutRecordId;
    }

    public void setWorkoutRecordId(int i) {
        this.workoutRecordId = i;
    }

    public int getWorkoutRecordStatus() {
        return this.workoutRecordStatus;
    }

    public void setWorkoutRecordStatus(int i) {
        this.workoutRecordStatus = i;
    }

    public long getWorkoutRecordStartTime() {
        return this.workoutRecordStartTime;
    }

    public void setWorkoutRecordStartTime(long j) {
        this.workoutRecordStartTime = j;
    }

    public long getWorkoutRecordEndTime() {
        return this.workoutRecordEndTime;
    }

    public void setWorkoutRecordEndTime(long j) {
        this.workoutRecordEndTime = j;
    }

    public int getWorkoutRecordCalorie() {
        return this.workoutRecordCalorie;
    }

    public void setWorkoutRecordCalorie(int i) {
        this.workoutRecordCalorie = i;
    }

    public int getWorkoutRecordDistance() {
        return this.workoutRecordDistance;
    }

    public void setWorkoutRecordDistance(int i) {
        this.workoutRecordDistance = i;
    }

    public long getWorkoutRecordStep() {
        return this.workoutRecordStep;
    }

    public void setWorkoutRecordStep(long j) {
        this.workoutRecordStep = j;
    }

    public long getWorkoutRecordTotalTime() {
        return this.workoutRecordTotalTime;
    }

    public void setWorkoutRecordTotalTime(long j) {
        this.workoutRecordTotalTime = j;
    }

    public int getWorkoutRecordSpeed() {
        return this.workoutRecordSpeed;
    }

    public void setWorkoutRecordSpeed(int i) {
        this.workoutRecordSpeed = i;
    }

    public float getWorkoutClimb() {
        return this.workoutClimb;
    }

    public void setWorkoutClimb(long j) {
        this.workoutClimb = j;
    }

    public int getWorkoutHrPeakMax() {
        return this.workoutHrPeakMax;
    }

    public void setWorkoutHrPeakMax(int i) {
        this.workoutHrPeakMax = i;
    }

    public int getWorkoutHrPeakMin() {
        return this.workoutHrPeakMin;
    }

    public void setWorkoutHrPeakMin(int i) {
        this.workoutHrPeakMin = i;
    }

    public int getWorkoutLoadPeak() {
        return this.workoutLoadPeak;
    }

    public void setWorkoutLoadPeak(int i) {
        this.workoutLoadPeak = i;
    }

    public int getWorkoutEtrainingEffect() {
        return this.workoutEtrainingEffect;
    }

    public void setWorkoutEtrainingEffect(int i) {
        this.workoutEtrainingEffect = i;
    }

    public int getWorkoutEpoc() {
        return this.workoutEpoc;
    }

    public void setWorkoutEpoc(int i) {
        this.workoutEpoc = i;
    }

    public int getWorkoutMaxMet() {
        return this.workoutMaxMet;
    }

    public void setWorkoutMaxMet(int i) {
        this.workoutMaxMet = i;
    }

    public int getWorkoutRecoveryTime() {
        return this.workoutRecoveryTime;
    }

    public void setWorkoutRecoveryTime(int i) {
        this.workoutRecoveryTime = i;
    }

    public long getWorkoutExerciseDuration() {
        return this.workoutExerciseDuration;
    }

    public void setWorkoutExerciseDuration(long j) {
        this.workoutExerciseDuration = j;
    }

    public long getWorkoutDateInfo() {
        return this.workoutDateInfo;
    }

    public void setWorkoutDateInfo(long j) {
        this.workoutDateInfo = j;
    }

    public void setWorkoutType(int i) {
        this.workoutType = i;
    }

    public int getWorkoutType() {
        return this.workoutType;
    }

    public int getRecordFlag() {
        return this.recordFlag;
    }

    public void setRecordFlag(int i) {
        this.recordFlag = i;
    }

    public int getWorkoutHeartRateType() {
        return this.workoutHeartRateType;
    }

    public void setWorkoutHeartRateType(int i) {
        this.workoutHeartRateType = i;
    }

    public String getWorkoutExerciseId() {
        return this.workoutExerciseId;
    }

    public void setWorkoutExerciseId(String str) {
        this.workoutExerciseId = str;
    }

    public int getHighestBloodOxygen() {
        return this.highestBloodOxygen;
    }

    public void setHighestBloodOxygen(int i) {
        this.highestBloodOxygen = i;
    }

    public int getLowestBloodOxygen() {
        return this.lowestBloodOxygen;
    }

    public void setLowestBloodOxygen(int i) {
        this.lowestBloodOxygen = i;
    }

    public List<TriathlonStruct> getTriathlonStructList() {
        return this.triathlonStructList;
    }

    public void setTriathlonStructList(List<TriathlonStruct> list) {
        this.triathlonStructList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("WorkoutRecordStatistic{,workout_record_id=").append(this.workoutRecordId).append(",workout_record_status=").append(this.workoutRecordStatus).append(",workout_record_start_time=").append(this.workoutRecordStartTime).append(", workout_record_end_time=").append(this.workoutRecordEndTime).append(", workout_record_calorie=").append(this.workoutRecordCalorie).append(", workout_record_distance=").append(this.workoutRecordDistance).append(", workout_record_step=").append(this.workoutRecordStep).append(", workout_record_total_time=").append(this.workoutRecordTotalTime).append(", workout_record_speed=").append(this.workoutRecordSpeed).append(", workout_climb=").append(this.workoutClimb).append(", workout_HrABS_peak_max=").append(this.workoutHrPeakMax).append(", workout_HrABS_peak_min=").append(this.workoutHrPeakMin).append(", workout_load_peak=").append(this.workoutLoadPeak).append(", workout_etraining_effect=").append(this.workoutEtrainingEffect).append(", workout_Epoc=").append(this.workoutEpoc).append(", workout_maxMET=");
        stitchingStringBuffer1(stringBuffer);
        stitchingStringBuffer2(stringBuffer);
        stitchingStringBufferThree(stringBuffer);
        return stringBuffer.toString();
    }

    private void stitchingStringBuffer1(StringBuffer stringBuffer) {
        stringBuffer.append(this.workoutMaxMet).append(", workout_recovery_time=").append(this.workoutRecoveryTime).append(", workout_exercise_duration=").append(this.workoutExerciseDuration).append(", workout_date_Info=").append(this.workoutDateInfo).append(", workout_type=").append(this.workoutType).append(", swim_type=").append(this.swimType).append(", swim_pull_times=").append(this.swimPullTimes).append(", swim_pull_rate=").append(this.swimPullRate).append(", swim_pool_length=").append(this.swimPoolLength).append(", swim_trip_times=").append(this.swimTripTimes).append(", swim_avg_swolf=").append(this.swimAvgSwolf).append(", accumulative_drop_height=").append(this.accumulativeDropHeight).append(", highest_altitude=").append(this.highestAltitude).append(", lowest_altitude=").append(this.lowestAltitude).append(", swolf_base_km=").append(this.swolfBaseKm).append(", swolf_base_mile=").append(this.swolfBaseMile).append(", anaerobic_training_effect=");
    }

    private void stitchingStringBuffer2(StringBuffer stringBuffer) {
        stringBuffer.append(this.anaerobicTrainingEffect).append(", half_marathon_time=").append(this.halfMarathonTime).append(", total_marathon_time=").append(this.totalMarathonTime).append(", record_flag=").append(this.recordFlag).append(", workout_heart_rate_type=").append(this.workoutHeartRateType).append(", workout_exercise_id='").append(this.workoutExerciseId).append(", triathlonStructList=").append(this.triathlonStructList).append(", active duration ='").append(this.activeDuration).append(", jump times ='").append(this.jumpTimes).append(", max jump height ='").append(this.maxJumpHeight).append(", max jump duration ='").append(this.maxJumpDuration).append(", maxRunSpeed ='").append(this.maxRunSpeed).append(", runScore ='").append(this.runScore).append(", moveScore ='").append(this.moveScore).append(", jumpScore ='").append(this.jumpScore).append(", totalScore ='").append(this.totalScore).append(", explosiveScore ='").append(this.explosiveScore).append(", intenseScore ='").append(this.intenseScore).append(", highestBloodOxygen=").append(this.highestBloodOxygen).append(", lowestBloodOxygen=").append(this.lowestBloodOxygen);
    }

    private void stitchingStringBufferThree(StringBuffer stringBuffer) {
        stringBuffer.append(", golf_swing_count=").append(this.golfSwingCount).append(", golf_back_swing_time=").append(this.golfBackSwingTime).append(", golf_down_swing_time=").append(this.golfDownSwingTime).append(", golf_swing_speed=").append(this.golfSwingSpeed).append(", golf_max_swing_speed=").append(this.golfMaxSwingSpeed).append(", golf_swing_tempo=").append(this.golfSwingTempo).append(", ski_max_slope_degree=").append(this.skiMaxSlopeDegree).append(", ski_max_slope_percent=").append(this.skiMaxSlopePercent).append(", ski_total_time=").append(this.skiTotalTime).append(", ski_total_distance=").append(this.skiTotalDistance).append(", temperature=").append(this.temperature).append(", weather=").append(this.weather).append(", algType=").append(this.algType).append(", planId=").append(this.planId).append(", courseName=").append(this.courseName).append(", planCourseTime=").append(this.planCourseTime).append(", courseTargetType=").append(this.courseTargetType).append(", courseTargetValue=").append(this.courseTargetValue).append(", trainingPoints=").append(this.trainingPoints).append(", trainingExperience=").append(this.trainingExperience).append(", courseModifiedTime=").append(this.courseModifiedTime).append(", recoveryHeartRateList=").append(this.recoveryHeartRateList).append(", extendList=").append(this.extendList).append("}");
    }

    public void setActiveDuration(int i) {
        this.activeDuration = i;
    }

    public void setJumpTimes(int i) {
        this.jumpTimes = i;
    }

    public void setMaxJumpHeight(int i) {
        this.maxJumpHeight = i;
    }

    public void setMaxJumpDuration(int i) {
        this.maxJumpDuration = i;
    }

    public void setMaxRunSpeed(int i) {
        this.maxRunSpeed = i;
    }

    public void setRunScore(int i) {
        this.runScore = i;
    }

    public void setMoveScore(int i) {
        this.moveScore = i;
    }

    public void setJumpScore(int i) {
        this.jumpScore = i;
    }

    public void setTotalScore(int i) {
        this.totalScore = i;
    }

    public void setExplosiveScore(int i) {
        this.explosiveScore = i;
    }

    public void setIntenseScore(int i) {
        this.intenseScore = i;
    }

    public void setGolfSwingCount(int i) {
        this.golfSwingCount = i;
    }

    public void setGolfBackSwingTime(int i) {
        this.golfBackSwingTime = i;
    }

    public void setGolfDownSwingTime(int i) {
        this.golfDownSwingTime = i;
    }

    public void setGolfSwingSpeed(int i) {
        this.golfSwingSpeed = i;
    }

    public void setGolfMaxSwingSpeed(int i) {
        this.golfMaxSwingSpeed = i;
    }

    public void setGolfSwingTempo(int i) {
        this.golfSwingTempo = i;
    }

    public void setSkiMaxSlopeDegree(int i) {
        this.skiMaxSlopeDegree = i;
    }

    public void setSkiMaxSlopePercent(int i) {
        this.skiMaxSlopePercent = i;
    }

    public void setSkiTotalTime(long j) {
        this.skiTotalTime = j;
    }

    public void setSkiTotalDistance(int i) {
        this.skiTotalDistance = i;
    }

    public void setTemperature(int i) {
        this.temperature = i;
    }

    public void setWeather(int i) {
        this.weather = i;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setCourseName(String str) {
        this.courseName = str;
    }

    public void setPlanCourseTime(int i) {
        this.planCourseTime = i;
    }

    public void setCourseTargetType(int i) {
        this.courseTargetType = i;
    }

    public void setCourseTargetValue(int i) {
        this.courseTargetValue = i;
    }

    public void setTrainingPoints(int i) {
        this.trainingPoints = i;
    }

    public void setTrainingExperience(int i) {
        this.trainingExperience = i;
    }

    public void setCourseModifiedTime(long j) {
        this.courseModifiedTime = j;
    }
}
