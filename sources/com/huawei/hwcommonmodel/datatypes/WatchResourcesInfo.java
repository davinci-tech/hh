package com.huawei.hwcommonmodel.datatypes;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class WatchResourcesInfo implements Parcelable {
    public static final Parcelable.Creator<WatchResourcesInfo> CREATOR = new Parcelable.Creator<WatchResourcesInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.WatchResourcesInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFq_, reason: merged with bridge method [inline-methods] */
        public WatchResourcesInfo createFromParcel(Parcel parcel) {
            return new WatchResourcesInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public WatchResourcesInfo[] newArray(int i) {
            return new WatchResourcesInfo[i];
        }
    };
    private int mFailedNum;
    private String mWatchBrief;
    private String mWatchId;
    private String mWatchName;
    private int mWatchSize;
    private int mWatchType;
    private String mWatchVersion;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchResourcesInfo() {
    }

    protected WatchResourcesInfo(Parcel parcel) {
        this.mWatchId = parcel.readString();
        this.mWatchVersion = parcel.readString();
        this.mWatchType = parcel.readInt();
        this.mWatchName = parcel.readString();
        this.mWatchBrief = parcel.readString();
        this.mWatchSize = parcel.readInt();
        this.mFailedNum = parcel.readInt();
    }

    public String getWatchInfoId() {
        return this.mWatchId;
    }

    public void setWatchInfoId(String str) {
        this.mWatchId = str;
    }

    public String getWatchInfoVersion() {
        return this.mWatchVersion;
    }

    public void setWatchInfoVersion(String str) {
        this.mWatchVersion = str;
    }

    public int getWatchInfoType() {
        return this.mWatchType;
    }

    public void setWatchInfoType(int i) {
        this.mWatchType = i;
    }

    public String getWatchInfoName() {
        return this.mWatchName;
    }

    public void setWatchInfoName(String str) {
        this.mWatchName = str;
    }

    public String getWatchInfoBrief() {
        return this.mWatchBrief;
    }

    public void setWatchInfoBrief(String str) {
        this.mWatchBrief = str;
    }

    public int getWatchInfoSize() {
        return this.mWatchSize;
    }

    public void setWatchInfoSize(int i) {
        this.mWatchSize = i;
    }

    public int getFailedNum() {
        return this.mFailedNum;
    }

    public void setFailedNum(int i) {
        this.mFailedNum = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mWatchId);
        parcel.writeString(this.mWatchVersion);
        parcel.writeInt(this.mWatchType);
        parcel.writeString(this.mWatchName);
        parcel.writeString(this.mWatchBrief);
        parcel.writeInt(this.mWatchSize);
        parcel.writeInt(this.mFailedNum);
    }

    public String toString() {
        return "WatchResourcesInfo{WatchInfo_Id=" + this.mWatchId + ", WatchInfo_Version=" + this.mWatchVersion + ", WatchInfo_Type=" + this.mWatchType + ", WatchInfo_Name=" + this.mWatchName + ", WatchInfo_Brief=" + this.mWatchBrief + ", WatchInfo_Size=" + this.mWatchSize + ", mFailedNum=" + this.mFailedNum + "}";
    }
}
