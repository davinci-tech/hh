package com.huawei.hwcommonmodel.datatypes;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WatchFaceInfo implements Parcelable {
    public static final Parcelable.Creator<WatchFaceInfo> CREATOR = new Parcelable.Creator<WatchFaceInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.WatchFaceInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFp_, reason: merged with bridge method [inline-methods] */
        public WatchFaceInfo createFromParcel(Parcel parcel) {
            return new WatchFaceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public WatchFaceInfo[] newArray(int i) {
            return new WatchFaceInfo[i];
        }
    };
    private static final int LIST_DEFAULT_LENGTH = 20;
    public static final int WATCH_FACE_INFO_TYPE_HWT = 1;
    private WatchResourcesInfo mCurrentInfo;
    private boolean mIsSupportSort;
    private List<WatchResourcesInfo> mNoPresetList;
    private String mOtherWatchFaceVersion;
    private List<WatchResourcesInfo> mPresetList;
    private String mWatchFaceMaxVersion;
    private int mWatchHeight;
    private String mWatchScreen;
    private List<Integer> mWatchSupportFileType;
    private int mWatchWidth;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceInfo() {
        this.mPresetList = new ArrayList(20);
        this.mNoPresetList = new ArrayList(20);
        this.mCurrentInfo = new WatchResourcesInfo();
    }

    protected WatchFaceInfo(Parcel parcel) {
        this.mPresetList = new ArrayList(20);
        this.mNoPresetList = new ArrayList(20);
        this.mCurrentInfo = new WatchResourcesInfo();
        this.mWatchFaceMaxVersion = parcel.readString();
        this.mWatchWidth = parcel.readInt();
        this.mWatchHeight = parcel.readInt();
        this.mIsSupportSort = parcel.readByte() != 0;
        this.mOtherWatchFaceVersion = parcel.readString();
        ArrayList arrayList = new ArrayList(20);
        this.mWatchSupportFileType = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.mPresetList = parcel.createTypedArrayList(WatchResourcesInfo.CREATOR);
        this.mNoPresetList = parcel.createTypedArrayList(WatchResourcesInfo.CREATOR);
        this.mCurrentInfo = (WatchResourcesInfo) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    public String getWatchFaceMaxVersion() {
        return this.mWatchFaceMaxVersion;
    }

    public void setWatchFaceMaxVersion(String str) {
        this.mWatchFaceMaxVersion = str;
    }

    public int getWatchFaceWidth() {
        return this.mWatchWidth;
    }

    public void setWatchFaceWidth(int i) {
        this.mWatchWidth = i;
    }

    public int getWatchFaceHeight() {
        return this.mWatchHeight;
    }

    public void setWatchFaceHeight(int i) {
        this.mWatchHeight = i;
    }

    public String getWatchFaceScreen() {
        return String.valueOf(this.mWatchHeight) + "*" + String.valueOf(this.mWatchWidth);
    }

    public void setWatchFaceScreen(String str) {
        this.mWatchScreen = str;
    }

    public boolean isWatchFaceSort() {
        return this.mIsSupportSort;
    }

    public void setWatchFaceSort(boolean z) {
        this.mIsSupportSort = z;
    }

    public String getOtherWatchFaceVersion() {
        return this.mOtherWatchFaceVersion;
    }

    public void setOtherWatchFaceVersion(String str) {
        this.mOtherWatchFaceVersion = str;
    }

    public List<Integer> getWatchFaceSupportFileType() {
        return this.mWatchSupportFileType;
    }

    public void setWatchFaceSupportFileType(List<Integer> list) {
        this.mWatchSupportFileType = list;
    }

    public List<WatchResourcesInfo> getPresetList() {
        return this.mPresetList;
    }

    public void setPresetList(List<WatchResourcesInfo> list) {
        this.mPresetList = list;
    }

    public List<WatchResourcesInfo> getNoPresetList() {
        return this.mNoPresetList;
    }

    public void setNoPresetList(List<WatchResourcesInfo> list) {
        this.mNoPresetList = list;
    }

    public WatchResourcesInfo getCurrentInfo() {
        return this.mCurrentInfo;
    }

    public void setCurrentInfo(WatchResourcesInfo watchResourcesInfo) {
        this.mCurrentInfo = watchResourcesInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mWatchFaceMaxVersion);
        parcel.writeInt(this.mWatchWidth);
        parcel.writeInt(this.mWatchHeight);
        parcel.writeByte(this.mIsSupportSort ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mOtherWatchFaceVersion);
        parcel.writeList(this.mWatchSupportFileType);
        parcel.writeTypedList(this.mPresetList);
        parcel.writeTypedList(this.mNoPresetList);
        parcel.writeParcelable(this.mCurrentInfo, i);
    }

    public String toString() {
        return "WatchFaceInfo{WatchFace_MaxVersion=" + this.mWatchFaceMaxVersion + ", WatchFace_Width=" + this.mWatchWidth + ", WatchFace_Height=" + this.mWatchHeight + ", WatchFace_Screen=" + this.mWatchScreen + ", WatchFace_Sort=" + getWatchFaceScreen() + ", WatchFace_SupportFileType=" + this.mWatchSupportFileType + ", watchFace_OtherWatchFace_Version=" + this.mOtherWatchFaceVersion + ", mPresetList=" + this.mPresetList.size() + ", mNoPresetList=" + this.mNoPresetList.size() + ", mCurrentInfo=" + this.mCurrentInfo.toString() + "}";
    }
}
