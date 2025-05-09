package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.plan.model.intplan.StatInfoBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import defpackage.moa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class Plan extends PlanInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<Plan> CREATOR = new Parcelable.Creator<Plan>() { // from class: com.huawei.basefitnessadvice.model.Plan.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Plan createFromParcel(Parcel parcel) {
            return new Plan(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Plan[] newArray(int i) {
            return new Plan[i];
        }
    };
    private static final String TAG = "Suggestion_Plan";

    @SerializedName("calorie")
    private float mCalorie;

    @SerializedName("createTime")
    private long mCreateTime;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("difficulty")
    private int mDifficulty;

    @SerializedName("distance")
    private float mDistance;

    @SerializedName("endDate")
    private String mEndDate;

    @SerializedName("endTime")
    private long mEndTime;

    @SerializedName("excludedDates")
    private TreeSet<Integer> mExcludedDates;

    @SerializedName("exeDate")
    private List<Integer> mExeDate;

    @SerializedName(ParsedFieldTag.GOAL)
    private int mGoal;

    @SerializedName("groupType")
    private int mGroupType;

    @SerializedName("introduction")
    private Introduction mIntroduction;

    @SerializedName("latestClockInTime")
    private long mLatestClockInTime;

    @SerializedName("modified")
    private long mModified;

    @SerializedName("name")
    private String mName;

    @SerializedName("pbBeforePlan")
    private int mPbBeforePlan;

    @SerializedName("pbCurrent")
    private int mPbCurrent;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("planCategory")
    private int mPlanCategory;

    @SerializedName("id")
    private String mPlanId;

    @SerializedName("planType")
    private int mPlanTempId;

    @SerializedName("planWeekDataList")
    private List<moa> mPlanWeekDataList;

    @SerializedName("remindTime")
    private int mRemindTime;

    @SerializedName("runDate")
    private List<Integer> mRunDate;

    @SerializedName(Constants.START_DATE)
    private String mStartDate;

    @SerializedName("startTime")
    private long mStartTime;

    @SerializedName("statistics")
    private List<StatInfoBean> mStatistics;

    @SerializedName("targetTime")
    private int mTargetTime;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("days")
    private int mTotalDays;

    @SerializedName("trainLevel")
    private int mTrainLevel;

    @SerializedName("type")
    private int mType;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("weekCount")
    private int mWeekCount;

    @SerializedName("weekTimes")
    private int mWeekTimes;

    @SerializedName("weekTrainingDays")
    private int mWeekTrainingDays;

    @SerializedName("workoutCount")
    private int mWorkoutCount;

    @SerializedName("workouts")
    private List<PlanWorkout> mWorkouts;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public int getType() {
        return 5;
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public void setLabels(List<String> list) {
    }

    public Plan() {
    }

    protected Plan(Parcel parcel) {
        this.mPlanId = parcel.readString();
        this.mType = parcel.readInt();
        this.mName = parcel.readString();
        this.mDescription = parcel.readString();
        this.mWeekCount = parcel.readInt();
        this.mStartDate = parcel.readString();
        this.mEndDate = parcel.readString();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mTimeZone = parcel.readString();
        this.mRunDate = new ArrayList();
        this.mExeDate = new ArrayList();
        this.mPlanWeekDataList = new ArrayList();
        parcel.readList(this.mRunDate, Integer.class.getClassLoader());
        parcel.readList(this.mExeDate, Integer.class.getClassLoader());
        parcel.readList(this.mPlanWeekDataList, moa.class.getClassLoader());
        this.mTotalDays = parcel.readInt();
        this.mWorkoutCount = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mVersion = parcel.readString();
        this.mModified = parcel.readLong();
        this.mCreateTime = parcel.readLong();
        this.mDifficulty = parcel.readInt();
        this.mPicture = parcel.readString();
        this.mDistance = parcel.readFloat();
        this.mGoal = parcel.readInt();
        this.mExcludedDates = (TreeSet) parcel.readSerializable();
        this.mWeekTimes = parcel.readInt();
        this.mLatestClockInTime = parcel.readLong();
        this.mWeekTrainingDays = parcel.readInt();
        this.mPlanCategory = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.mWorkouts = arrayList;
        parcel.readList(arrayList, PlanWorkout.class.getClassLoader());
        this.mTargetTime = parcel.readInt();
        this.mPbBeforePlan = parcel.readInt();
        this.mPbCurrent = parcel.readInt();
        this.mGroupType = parcel.readInt();
        this.mRemindTime = parcel.readInt();
        this.mTransactionStatus = parcel.readInt();
        this.mPriceTagBeanList = new ArrayList();
        parcel.readList(this.mPriceTagBeanList, PriceTagBean.class.getClassLoader());
        this.mCommodityFlag = parcel.readInt();
        this.mCornerImgDisplay = parcel.readInt();
        this.mTrainLevel = parcel.readInt();
        this.mPlanTempId = parcel.readInt();
        ArrayList arrayList2 = new ArrayList();
        this.mStatistics = arrayList2;
        parcel.readList(arrayList2, StatInfoBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPlanId);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDescription);
        parcel.writeInt(this.mWeekCount);
        parcel.writeString(this.mStartDate);
        parcel.writeString(this.mEndDate);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeString(this.mTimeZone);
        parcel.writeList(this.mRunDate);
        parcel.writeList(this.mExeDate);
        parcel.writeList(this.mPlanWeekDataList);
        parcel.writeInt(this.mTotalDays);
        parcel.writeInt(this.mWorkoutCount);
        parcel.writeFloat(this.mCalorie);
        parcel.writeString(this.mVersion);
        parcel.writeLong(this.mModified);
        parcel.writeLong(this.mCreateTime);
        parcel.writeInt(this.mDifficulty);
        parcel.writeString(this.mPicture);
        parcel.writeFloat(this.mDistance);
        parcel.writeInt(this.mGoal);
        parcel.writeSerializable(this.mExcludedDates);
        parcel.writeInt(this.mWeekTimes);
        parcel.writeLong(this.mLatestClockInTime);
        parcel.writeInt(this.mWeekTrainingDays);
        parcel.writeInt(this.mPlanCategory);
        parcel.writeList(this.mWorkouts);
        parcel.writeInt(this.mTargetTime);
        parcel.writeInt(this.mPbBeforePlan);
        parcel.writeInt(this.mPbCurrent);
        parcel.writeInt(this.mGroupType);
        parcel.writeInt(this.mRemindTime);
        parcel.writeInt(this.mTransactionStatus);
        parcel.writeList(this.mPriceTagBeanList);
        parcel.writeInt(this.mCommodityFlag);
        parcel.writeInt(this.mCornerImgDisplay);
        parcel.writeInt(this.mTrainLevel);
        parcel.writeInt(this.mPlanTempId);
        parcel.writeList(this.mStatistics);
    }

    @Override // com.huawei.basefitnessadvice.model.PlanInfo
    public List<String> getLabels() {
        return Collections.emptyList();
    }

    public String acquireId() {
        return this.mPlanId;
    }

    public void saveId(String str) {
        this.mPlanId = str;
    }

    public int acquireTempId() {
        return this.mPlanTempId;
    }

    public void saveTempId(int i) {
        this.mPlanTempId = i;
    }

    public String acquireName() {
        return this.mName;
    }

    public void putName(String str) {
        this.mName = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public List<PlanWorkout> acquireWorkouts() {
        List<PlanWorkout> list = this.mWorkouts;
        return list != null ? list : new ArrayList();
    }

    public final void saveWorkouts(List<PlanWorkout> list) {
        this.mWorkouts = list;
    }

    public int getDays() {
        return this.mTotalDays;
    }

    public void saveDays(int i) {
        this.mTotalDays = i;
    }

    public int getWorkoutCount() {
        return this.mWorkoutCount;
    }

    public void saveWorkoutCount(int i) {
        this.mWorkoutCount = i;
    }

    public String acquireStartDate() {
        return this.mStartDate;
    }

    public void saveStartDate(String str) {
        this.mStartDate = str;
    }

    public String getEndDate() {
        return this.mEndDate;
    }

    public void saveEndDate(String str) {
        this.mEndDate = str;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public List<Integer> getRunDate() {
        List<Integer> list = this.mRunDate;
        return list != null ? list : new ArrayList();
    }

    public final void setRunDate(List<Integer> list) {
        this.mRunDate = list;
    }

    public List<Integer> getExeDate() {
        List<Integer> list = this.mExeDate;
        return list != null ? list : new ArrayList();
    }

    public final void setExeDate(List<Integer> list) {
        this.mExeDate = list;
    }

    public List<moa> getPlanWeekDataList() {
        List<moa> list = this.mPlanWeekDataList;
        return list != null ? list : new ArrayList();
    }

    public void setPlanWeekDataList(List<moa> list) {
        this.mPlanWeekDataList = list;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void saveCalorie(float f) {
        this.mCalorie = f;
    }

    public long getModified() {
        return this.mModified;
    }

    public void setModified(long j) {
        this.mModified = j;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void setVersion(String str) {
        this.mVersion = str;
    }

    public int getWeekCount() {
        return this.mWeekCount;
    }

    public void saveWeekCount(int i) {
        this.mWeekCount = i;
    }

    public int acquireType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public void setGroupType(int i) {
        this.mGroupType = i;
    }

    public int getDifficulty() {
        return this.mDifficulty;
    }

    public void setDifficulty(int i) {
        this.mDifficulty = i;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public void savePicture(String str) {
        this.mPicture = str;
    }

    public float getDistance() {
        return this.mDistance;
    }

    public void setDistance(float f) {
        this.mDistance = f;
    }

    public int acquireGoal() {
        return this.mGoal;
    }

    public void setGoal(int i) {
        this.mGoal = i;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void saveCreateTime(long j) {
        this.mCreateTime = j;
    }

    public TreeSet<Integer> acquireExcludedDates() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        TreeSet<Integer> treeSet2 = this.mExcludedDates;
        if (treeSet2 == null) {
            LogUtil.h(TAG, "excludedDates == null");
            return treeSet;
        }
        return (TreeSet) treeSet2.clone();
    }

    public final void setExcludedDates(TreeSet<Integer> treeSet) {
        this.mExcludedDates = treeSet;
    }

    public int acquireWeekTimes() {
        return this.mWeekTimes;
    }

    public void setWeekTimes(int i) {
        this.mWeekTimes = i;
    }

    public int getRemindTime() {
        return this.mRemindTime;
    }

    public void setRemindTime(int i) {
        this.mRemindTime = i;
    }

    public long getLatestClockInTime() {
        return this.mLatestClockInTime;
    }

    public void setLatestClockInTime(long j) {
        this.mLatestClockInTime = j;
    }

    public void setWeekTrainingDays(int i) {
        this.mWeekTrainingDays = i;
    }

    public int getWeekTrainingDays() {
        return this.mWeekTrainingDays;
    }

    public void setTargetTime(int i) {
        this.mTargetTime = i;
    }

    public int getTargetTime() {
        return this.mTargetTime;
    }

    public void setPbBeforePlan(int i) {
        this.mPbBeforePlan = i;
    }

    public int getPbBeforePlan() {
        return this.mPbBeforePlan;
    }

    public void setPbCurrent(int i) {
        this.mPbCurrent = i;
    }

    public int getPbCurrent() {
        return this.mPbCurrent;
    }

    public int getTrainLevel() {
        return this.mTrainLevel;
    }

    public void setTrainLevel(int i) {
        this.mTrainLevel = i;
    }

    public static Parcelable.Creator<Plan> getCreator() {
        return CREATOR;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public Object clone() {
        try {
            if (!(super.clone() instanceof Plan)) {
                return new Plan();
            }
            Plan plan = (Plan) super.clone();
            if (this.mWorkouts != null) {
                ArrayList arrayList = new ArrayList();
                for (PlanWorkout planWorkout : this.mWorkouts) {
                    if (planWorkout != null && (planWorkout.clone() instanceof PlanWorkout)) {
                        arrayList.add((PlanWorkout) planWorkout.clone());
                    }
                }
                plan.saveWorkouts(arrayList);
            }
            if (this.mRunDate != null) {
                plan.setRunDate(new ArrayList(this.mRunDate));
            }
            if (this.mExeDate != null) {
                plan.setExeDate(new ArrayList(this.mExeDate));
            }
            if (this.mPlanWeekDataList != null) {
                ArrayList arrayList2 = new ArrayList();
                for (moa moaVar : this.mPlanWeekDataList) {
                    if (moaVar != null && (moaVar.clone() instanceof moa)) {
                        arrayList2.add((moa) moaVar.clone());
                    }
                }
                plan.setPlanWeekDataList(arrayList2);
            }
            TreeSet<Integer> treeSet = this.mExcludedDates;
            if (treeSet != null) {
                plan.setExcludedDates((TreeSet) treeSet.clone());
            }
            return plan;
        } catch (CloneNotSupportedException e) {
            health.compact.a.LogUtil.e(TAG, "clone failed", e.getMessage());
            throw new AssertionError();
        }
    }

    public void setPlanCategory(int i) {
        this.mPlanCategory = i;
    }

    public int getPlanCategory() {
        return this.mPlanCategory;
    }

    public Introduction acquireIntroduction() {
        return this.mIntroduction;
    }

    public void saveIntroduction(Introduction introduction) {
        this.mIntroduction = introduction;
    }

    public List<StatInfoBean> getStatistics() {
        return this.mStatistics;
    }

    public void setStatistics(List<StatInfoBean> list) {
        this.mStatistics = list;
    }
}
