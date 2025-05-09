package com.huawei.ui.main.stories.fitness.activity.heartrate.helper;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class WarningListData implements Parcelable {
    public static final Parcelable.Creator<WarningListData> CREATOR = new Parcelable.Creator<WarningListData>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.helper.WarningListData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dsP_, reason: merged with bridge method [inline-methods] */
        public WarningListData createFromParcel(Parcel parcel) {
            return new WarningListData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public WarningListData[] newArray(int i) {
            return new WarningListData[i];
        }
    };
    private String mDate;
    private String mTime;
    private String mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WarningListData() {
    }

    protected WarningListData(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDate = parcel.readString();
        this.mTime = parcel.readString();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public String getDateTime() {
        return this.mDate + " " + this.mTime;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mDate);
            parcel.writeString(this.mTime);
        }
    }
}
