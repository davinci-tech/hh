package com.huawei.basichealthmodel.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class TaskConfigBean implements Parcelable {
    public static final Parcelable.Creator<TaskConfigBean> CREATOR = new Parcelable.Creator<TaskConfigBean>() { // from class: com.huawei.basichealthmodel.bean.TaskConfigBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: jP_, reason: merged with bridge method [inline-methods] */
        public TaskConfigBean createFromParcel(Parcel parcel) {
            return new TaskConfigBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public TaskConfigBean[] newArray(int i) {
            return new TaskConfigBean[i];
        }
    };
    private String mDefaultAlarmTime;
    private String mDefaultGoal;
    private String mDefaultTask;
    private ConfigDetailBean mDetailEntity;
    private int mFinishType;
    private int mGoalCycleDays;
    private int mGoalCycleType;
    private String mGoalMax;
    private String mGoalMin;
    private int mId;
    private boolean mIsSupportOversea;
    private String mName;
    private String mNameResourceId;
    private String mSupportAppMaxVersion;
    private String mSupportAppMinVersion;
    private String mSupportTask;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TaskConfigBean() {
    }

    private TaskConfigBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mId = parcel.readInt();
        this.mType = parcel.readInt();
        this.mFinishType = parcel.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            this.mIsSupportOversea = parcel.readBoolean();
        } else {
            this.mIsSupportOversea = parcel.readInt() != 0;
        }
        this.mName = parcel.readString();
        this.mNameResourceId = parcel.readString();
        this.mSupportAppMinVersion = parcel.readString();
        this.mSupportAppMaxVersion = parcel.readString();
        this.mGoalCycleType = parcel.readInt();
        this.mGoalCycleDays = parcel.readInt();
        this.mDefaultGoal = parcel.readString();
        this.mGoalMin = parcel.readString();
        this.mGoalMax = parcel.readString();
        this.mSupportTask = parcel.readString();
        this.mDefaultTask = parcel.readString();
        this.mDefaultAlarmTime = parcel.readString();
        this.mDetailEntity = (ConfigDetailBean) parcel.readParcelable(ConfigDetailBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mFinishType);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.mIsSupportOversea);
        } else {
            parcel.writeInt(this.mIsSupportOversea ? 1 : 0);
        }
        parcel.writeString(this.mName);
        parcel.writeString(this.mNameResourceId);
        parcel.writeString(this.mSupportAppMinVersion);
        parcel.writeString(this.mSupportAppMaxVersion);
        parcel.writeInt(this.mGoalCycleType);
        parcel.writeInt(this.mGoalCycleDays);
        parcel.writeString(this.mDefaultGoal);
        parcel.writeString(this.mGoalMin);
        parcel.writeString(this.mGoalMax);
        parcel.writeString(this.mSupportTask);
        parcel.writeString(this.mDefaultTask);
        parcel.writeString(this.mDefaultAlarmTime);
        parcel.writeParcelable(this.mDetailEntity, i);
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getGoalCycleType() {
        return this.mGoalCycleType;
    }

    public int getGoalCycleDays() {
        return this.mGoalCycleDays;
    }

    public String getDefaultGoal() {
        return this.mDefaultGoal;
    }

    public ConfigDetailBean getDetailEntity() {
        return this.mDetailEntity;
    }

    public String getSupportTask() {
        return this.mSupportTask;
    }

    public String getDefaultTask() {
        return this.mDefaultTask;
    }

    public String getDefaultAlarmTime() {
        return this.mDefaultAlarmTime;
    }
}
