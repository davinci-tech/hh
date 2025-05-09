package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class HealthLifeStatistic implements Parcelable {
    public static final Parcelable.Creator<HealthLifeStatistic> CREATOR = new Parcelable.Creator<HealthLifeStatistic>() { // from class: com.huawei.health.healthmodel.bean.HealthLifeStatistic.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZC_, reason: merged with bridge method [inline-methods] */
        public HealthLifeStatistic createFromParcel(Parcel parcel) {
            return new HealthLifeStatistic(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HealthLifeStatistic[] newArray(int i) {
            return new HealthLifeStatistic[i];
        }
    };

    @SerializedName(ParsedFieldTag.KAKA_CONSECUTIVE_DAYS)
    private int mConDays;

    @SerializedName("hasCurrentDay")
    private Boolean mHasToday;

    @SerializedName("id")
    private int mId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.mId;
    }

    public int getConDays() {
        return this.mConDays;
    }

    public void addConDays() {
        this.mConDays++;
    }

    public boolean hasToday() {
        return this.mHasToday.booleanValue();
    }

    public void setHasToday(boolean z) {
        this.mHasToday = Boolean.valueOf(z);
    }

    public HealthLifeStatistic() {
    }

    protected HealthLifeStatistic(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mId = parcel.readInt();
        this.mConDays = parcel.readInt();
        this.mHasToday = Boolean.valueOf(parcel.readInt() == 1);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mConDays);
        parcel.writeInt(this.mHasToday.booleanValue() ? 1 : 0);
    }

    public String toString() {
        return "HealthLifeStatistic{mId=" + this.mId + ", mConDays=" + this.mConDays + ", mHasToday=" + this.mHasToday + '}';
    }
}
