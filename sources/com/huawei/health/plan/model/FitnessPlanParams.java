package com.huawei.health.plan.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.TreeSet;

/* loaded from: classes8.dex */
public class FitnessPlanParams implements Parcelable {
    public static final Parcelable.Creator<FitnessPlanParams> CREATOR = new Parcelable.Creator<FitnessPlanParams>() { // from class: com.huawei.health.plan.model.FitnessPlanParams.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: arV_, reason: merged with bridge method [inline-methods] */
        public FitnessPlanParams createFromParcel(Parcel parcel) {
            return new FitnessPlanParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FitnessPlanParams[] newArray(int i) {
            return new FitnessPlanParams[i];
        }
    };
    private static final String TAG = "Suggestion_FitnessPlanParams";
    private long mBeginDate;
    private int mDifficulty;
    private TreeSet<Integer> mExcludedDate;
    private int mGender;
    private int mTimes;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FitnessPlanParams() {
    }

    protected FitnessPlanParams(Parcel parcel) {
        this.mBeginDate = parcel.readLong();
        this.mType = parcel.readInt();
        this.mGender = parcel.readInt();
        this.mDifficulty = parcel.readInt();
        this.mTimes = parcel.readInt();
        this.mExcludedDate = (TreeSet) parcel.readSerializable();
    }

    public long acquireBeginDate() {
        return this.mBeginDate;
    }

    public void setBeginDate(long j) {
        this.mBeginDate = j;
    }

    public int acquireType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getGender() {
        return this.mGender;
    }

    public void setGender(int i) {
        this.mGender = i;
    }

    public int acquireDifficulty() {
        return this.mDifficulty;
    }

    public void setDifficulty(int i) {
        this.mDifficulty = i;
    }

    public int acquireTimes() {
        return this.mTimes;
    }

    public void setTimes(int i) {
        this.mTimes = i;
    }

    public TreeSet<Integer> acquireExcludedDate() {
        TreeSet<Integer> treeSet = this.mExcludedDate;
        if (treeSet != null) {
            return (TreeSet) treeSet.clone();
        }
        return null;
    }

    public void setExcludedDate(TreeSet<Integer> treeSet) {
        this.mExcludedDate = treeSet;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeLong(this.mBeginDate);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mGender);
        parcel.writeInt(this.mDifficulty);
        parcel.writeInt(this.mTimes);
        parcel.writeSerializable(this.mExcludedDate);
    }
}
