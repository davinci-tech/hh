package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.secure.android.common.util.SafeString;

/* loaded from: classes7.dex */
public class WatchResourcesInfo implements Parcelable {
    public static final Parcelable.Creator<WatchResourcesInfo> CREATOR = new Parcelable.Creator<WatchResourcesInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchResourcesInfo createFromParcel(Parcel parcel) {
            return new WatchResourcesInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchResourcesInfo[] newArray(int i) {
            return new WatchResourcesInfo[i];
        }
    };
    public static final int VIP_FREE_MARKER = 5;
    private int mFailedNum;
    private boolean mIsVipFreeWatchFace = false;
    private String mWatchBrief;
    private int mWatchExpandType;
    private String mWatchId;
    private String mWatchName;
    private String mWatchScreen;
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
        this.mWatchScreen = parcel.readString();
        this.mWatchExpandType = parcel.readInt();
    }

    public boolean isVipFreeWatchFace() {
        return this.mIsVipFreeWatchFace;
    }

    public void setVipFreeWatchFace(boolean z) {
        this.mIsVipFreeWatchFace = z;
    }

    public String getWatchInfoId() {
        return this.mWatchId;
    }

    public String markDownVipFreeWatchFace(String str) {
        String markDownHiTopId = markDownHiTopId(str);
        setVipFreeWatchFace(!markDownHiTopId.equals(str));
        return markDownHiTopId;
    }

    public static String markDownHiTopId(String str) {
        int parseInt;
        if (str == null || str.isEmpty()) {
            return str;
        }
        String substring = SafeString.substring(str, 0, 1);
        if (!isNumeric(substring) || (parseInt = Integer.parseInt(substring)) <= 5) {
            return str;
        }
        return (parseInt - 5) + SafeString.substring(str, 1);
    }

    public String markUpVipFreeWatchFace(String str) {
        return !this.mIsVipFreeWatchFace ? str : markUpHiTopId(str);
    }

    public static String markUpHiTopId(String str) {
        int parseInt;
        if (str == null || str.isEmpty()) {
            return str;
        }
        String substring = SafeString.substring(str, 0, 1);
        if (!isNumeric(substring) || (parseInt = Integer.parseInt(substring)) >= 5) {
            return str;
        }
        return (parseInt + 5) + SafeString.substring(str, 1);
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
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

    public String getWatchScreen() {
        return this.mWatchScreen;
    }

    public void setWatchScreen(String str) {
        this.mWatchScreen = str;
    }

    public int getWatchExpandType() {
        return this.mWatchExpandType;
    }

    public void setWatchExpandType(int i) {
        this.mWatchExpandType = i;
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
        parcel.writeString(this.mWatchScreen);
        parcel.writeInt(this.mWatchExpandType);
    }

    public String toString() {
        return "WatchResourcesInfo{WatchInfo_Id=" + this.mWatchId + ", WatchInfo_Version=" + this.mWatchVersion + ", WatchInfo_Type=" + this.mWatchType + ", WatchInfo_Name=" + this.mWatchName + ", WatchInfo_Brief=" + this.mWatchBrief + ", WatchInfo_Size=" + this.mWatchSize + ", mFailedNum=" + this.mFailedNum + ", mWatchScreen=" + this.mWatchScreen + ", mWatchExpandType=" + this.mWatchExpandType + "}";
    }
}
