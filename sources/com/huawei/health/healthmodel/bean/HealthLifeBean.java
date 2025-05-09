package com.huawei.health.healthmodel.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtils;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class HealthLifeBean implements Parcelable {
    public static final Parcelable.Creator<HealthLifeBean> CREATOR = new Parcelable.Creator<HealthLifeBean>() { // from class: com.huawei.health.healthmodel.bean.HealthLifeBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZB_, reason: merged with bridge method [inline-methods] */
        public HealthLifeBean createFromParcel(Parcel parcel) {
            return new HealthLifeBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HealthLifeBean[] newArray(int i) {
            return new HealthLifeBean[i];
        }
    };
    private static final String TAG = "HealthLife_HealthLifeBean";

    @SerializedName("addStatus")
    private int mAddStatus;

    @SerializedName("challengeId")
    private int mChallengeId;

    @SerializedName("isComplete")
    private int mComplete;

    @SerializedName("condition")
    private String mCondition;

    @SerializedName("continueDays")
    private int mContinueDays;

    @SerializedName("dataSource")
    private int mDataSource;

    @SerializedName("extendInfo")
    private String mExtendInfo;

    @SerializedName("huid")
    private String mHuid;

    @SerializedName("id")
    private int mId;

    @SerializedName("isUpdated")
    private boolean mIsUpdated;

    @SerializedName("lastTarget")
    private String mLastTarget;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int mRecordDay;

    @SerializedName("reserve")
    private String mReserve;

    @SerializedName("isRest")
    private int mRest;

    @SerializedName("result")
    private String mResult;

    @SerializedName("syncStatus")
    private int mSyncStatus;

    @SerializedName("target")
    private String mTarget;

    @SerializedName(ParsedFieldTag.TASK_TYPE)
    private int mTaskType;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("timestamp")
    private long mTimestamp;

    @SerializedName("type")
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthLifeBean() {
        this.mHuid = "";
        this.mTimeZone = "";
        this.mResult = "";
        this.mTarget = "";
        this.mLastTarget = "";
    }

    protected HealthLifeBean(Parcel parcel) {
        this.mHuid = "";
        this.mTimeZone = "";
        this.mResult = "";
        this.mTarget = "";
        this.mLastTarget = "";
        if (parcel == null) {
            return;
        }
        this.mHuid = parcel.readString();
        this.mReserve = parcel.readString();
        this.mCondition = parcel.readString();
        this.mTimeZone = parcel.readString();
        this.mResult = parcel.readString();
        this.mTarget = parcel.readString();
        this.mLastTarget = parcel.readString();
        this.mTimestamp = parcel.readLong();
        this.mId = parcel.readInt();
        this.mRecordDay = parcel.readInt();
        this.mSyncStatus = parcel.readInt();
        this.mTaskType = parcel.readInt();
        this.mDataSource = parcel.readInt();
        this.mChallengeId = parcel.readInt();
        this.mType = parcel.readInt();
        this.mRest = parcel.readInt();
        this.mComplete = parcel.readInt();
        this.mAddStatus = parcel.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            this.mIsUpdated = parcel.readBoolean();
        } else {
            this.mIsUpdated = parcel.readInt() != 0;
        }
        this.mExtendInfo = parcel.readString();
        this.mContinueDays = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mHuid);
        parcel.writeString(this.mReserve);
        parcel.writeString(this.mCondition);
        parcel.writeString(this.mTimeZone);
        parcel.writeString(this.mResult);
        parcel.writeString(this.mTarget);
        parcel.writeString(this.mLastTarget);
        parcel.writeLong(this.mTimestamp);
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mRecordDay);
        parcel.writeInt(this.mSyncStatus);
        parcel.writeInt(this.mTaskType);
        parcel.writeInt(this.mDataSource);
        parcel.writeInt(this.mChallengeId);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mRest);
        parcel.writeInt(this.mComplete);
        parcel.writeInt(this.mAddStatus);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.mIsUpdated);
        } else {
            parcel.writeInt(this.mIsUpdated ? 1 : 0);
        }
        parcel.writeString(this.mExtendInfo);
        parcel.writeInt(this.mContinueDays);
    }

    public String getHuid() {
        return this.mHuid;
    }

    public void setHuid(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mHuid = str;
    }

    public String getReserve() {
        return this.mReserve;
    }

    public void setReserve(String str) {
        this.mReserve = str;
    }

    public String getCondition() {
        return this.mCondition;
    }

    public void setCondition(String str) {
        this.mCondition = str;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mTimeZone = str;
    }

    public String getResult() {
        return this.mResult;
    }

    public void setResult(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mResult = str;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public void setTarget(String str) {
        if (this.mId == 3 && CommonUtils.h(str) > 90) {
            String d = DfxUtils.d(Thread.currentThread(), null);
            ReleaseLogUtil.a(TAG, "setTarget target ", str, " stackTraceInfo ", d);
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(TAG, d);
        } else {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.mTarget = str;
        }
    }

    public String getLastTarget() {
        return this.mLastTarget;
    }

    public void setLastTarget(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.mLastTarget = str;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(int i) {
        this.mRecordDay = i;
    }

    public int getSyncStatus() {
        return this.mSyncStatus;
    }

    public void setSyncStatus(int i) {
        this.mSyncStatus = i;
    }

    public int getTaskType() {
        return this.mTaskType;
    }

    public void setTaskType(int i) {
        this.mTaskType = i;
    }

    public int getDataSource() {
        return this.mDataSource;
    }

    public void setDataSource(int i) {
        this.mDataSource = i;
    }

    public int getChallengeId() {
        return this.mChallengeId;
    }

    public void setChallengeId(int i) {
        this.mChallengeId = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getRest() {
        return this.mRest;
    }

    public void setRest(int i) {
        this.mRest = i;
    }

    public int getComplete() {
        return this.mComplete;
    }

    public void setComplete(int i) {
        this.mComplete = i;
    }

    public int getAddStatus() {
        return this.mAddStatus;
    }

    public void setAddStatus(int i) {
        this.mAddStatus = i;
    }

    public boolean getIsUpdated() {
        return this.mIsUpdated;
    }

    public void setIsUpdated(boolean z) {
        this.mIsUpdated = z;
    }

    public String getExtendInfo() {
        return this.mExtendInfo;
    }

    public void setExtendInfo(String str) {
        this.mExtendInfo = str;
    }

    public void setContinueDays(int i) {
        this.mContinueDays = i;
    }

    public int getContinueDays() {
        return this.mContinueDays;
    }

    public String toString() {
        return "HealthLifeBean{mHuid=" + this.mHuid + ", mReserve=" + this.mReserve + ", mCondition=" + this.mCondition + ", mTimeZone=" + this.mTimeZone + ", mResult=" + this.mResult + ", mTarget=" + this.mTarget + ", mLastTarget=" + this.mLastTarget + ", mTimestamp=" + this.mTimestamp + ", mId=" + this.mId + ", mRecordDay=" + this.mRecordDay + ", mSyncStatus=" + this.mSyncStatus + ", mTaskType=" + this.mTaskType + ", mDataSource=" + this.mDataSource + ", mChallengeId=" + this.mChallengeId + ", mType=" + this.mType + ", mRest=" + this.mRest + ", mComplete=" + this.mComplete + ", mAddStatus=" + this.mAddStatus + ", mIsUpdated=" + this.mIsUpdated + ", mExtendInfo=" + this.mExtendInfo + "}";
    }
}
