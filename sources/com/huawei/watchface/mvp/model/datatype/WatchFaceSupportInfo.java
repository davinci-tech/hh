package com.huawei.watchface.mvp.model.datatype;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.watchface.utils.CommonUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class WatchFaceSupportInfo implements Parcelable {
    public static final Parcelable.Creator<WatchFaceSupportInfo> CREATOR = new Parcelable.Creator<WatchFaceSupportInfo>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceSupportInfo createFromParcel(Parcel parcel) {
            return new WatchFaceSupportInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceSupportInfo[] newArray(int i) {
            return new WatchFaceSupportInfo[i];
        }
    };
    private static final int LIST_DEFAULT_LENGTH = 20;
    public static final int WATCH_FACE_INFO_TYPE_HWT = 1;
    private boolean isSupportNewAlbum;
    private boolean isSupportOneTouchAbility;
    private boolean isSupportSync;
    private int mCommonErrorCode;
    private List<ScreenInfo> mCompatibleList;
    private WatchResourcesInfo mCurrentInfo;
    private boolean mIsSupportSort;
    private int mIsWearSupport;
    private List<WatchResourcesInfo> mNoPresetList;
    private String mOtherWatchFaceVersion;
    private int mPortraitFieldMode;
    private int mPowerLevel;
    private List<WatchResourcesInfo> mPresetList;
    private List<ScreenInfo> mPurchasedList;
    private int mSetTimeIndividually;
    private int mSupportCapability;
    private String mWatchFaceMaxVersion;
    private int mWatchHeight;
    private String mWatchScreen;
    private List<Integer> mWatchSupportFileType;
    private int mWatchWidth;
    private int mWatchfaceMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceSupportInfo() {
        this.mPresetList = new ArrayList(20);
        this.mNoPresetList = new ArrayList(20);
        this.mCurrentInfo = new WatchResourcesInfo();
        this.mCompatibleList = new ArrayList(20);
        this.mPurchasedList = new ArrayList(20);
        this.mWatchfaceMode = -1;
        this.isSupportNewAlbum = false;
    }

    protected WatchFaceSupportInfo(Parcel parcel) {
        this.mPresetList = new ArrayList(20);
        this.mNoPresetList = new ArrayList(20);
        this.mCurrentInfo = new WatchResourcesInfo();
        this.mCompatibleList = new ArrayList(20);
        this.mPurchasedList = new ArrayList(20);
        this.mWatchfaceMode = -1;
        this.isSupportNewAlbum = false;
        this.mWatchFaceMaxVersion = parcel.readString();
        this.mWatchWidth = parcel.readInt();
        this.mWatchHeight = parcel.readInt();
        this.mIsSupportSort = parcel.readByte() != 0;
        this.mOtherWatchFaceVersion = parcel.readString();
        ArrayList arrayList = new ArrayList(20);
        this.mWatchSupportFileType = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.mPresetList = CommonUtils.a(WatchResourcesInfo.CREATOR, parcel);
        this.mNoPresetList = CommonUtils.a(WatchResourcesInfo.CREATOR, parcel);
        this.mCurrentInfo = (WatchResourcesInfo) parcel.readParcelable(ContentValues.class.getClassLoader());
        this.mCompatibleList = CommonUtils.a(ScreenInfo.CREATOR, parcel);
        this.mPurchasedList = CommonUtils.a(ScreenInfo.CREATOR, parcel);
        this.mSupportCapability = parcel.readInt();
        this.mPowerLevel = parcel.readInt();
        this.mIsWearSupport = parcel.readInt();
        this.mPortraitFieldMode = parcel.readInt();
        this.mSetTimeIndividually = parcel.readInt();
        this.mCommonErrorCode = parcel.readInt();
        this.isSupportSync = parcel.readByte() != 0;
        this.isSupportOneTouchAbility = parcel.readByte() != 0;
        this.mWatchfaceMode = parcel.readInt();
    }

    public static WatchFaceSupportInfo createInstance() {
        WatchFaceSupportInfo watchFaceSupportInfo = new WatchFaceSupportInfo();
        watchFaceSupportInfo.setWatchFaceHeight(466);
        watchFaceSupportInfo.setWatchFaceWidth(466);
        watchFaceSupportInfo.setWatchFaceSort(false);
        watchFaceSupportInfo.setWatchFaceMaxVersion("1.1");
        watchFaceSupportInfo.setOtherWatchFaceVersion("1.1");
        return watchFaceSupportInfo;
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

    public List<ScreenInfo> getCompatibleList() {
        return this.mCompatibleList;
    }

    public void setCompatibleList(List<ScreenInfo> list) {
        this.mCompatibleList = list;
    }

    public List<ScreenInfo> getPurchasedList() {
        return this.mPurchasedList;
    }

    public void setPurchasedList(List<ScreenInfo> list) {
        this.mPurchasedList = list;
    }

    public int getSupportCapability() {
        return this.mSupportCapability;
    }

    public void setSupportCapability(int i) {
        this.mSupportCapability = i;
    }

    public int getPowerLevel() {
        return this.mPowerLevel;
    }

    public void setPowerLevel(int i) {
        this.mPowerLevel = i;
    }

    public int getIsWearSupport() {
        return this.mIsWearSupport;
    }

    public void setIsWearSupport(int i) {
        this.mIsWearSupport = i;
    }

    public int getPortraitFieldMode() {
        return this.mPortraitFieldMode;
    }

    public void setPortraitFieldMode(int i) {
        this.mPortraitFieldMode = i;
    }

    public boolean isPortraitModeSupported() {
        return CommonUtils.E() && this.mPortraitFieldMode == 1;
    }

    public int getSetTimeIndividually() {
        return this.mSetTimeIndividually;
    }

    public void setSetTimeIndividually(int i) {
        this.mSetTimeIndividually = i;
    }

    public int getCommonErrorCode() {
        return this.mCommonErrorCode;
    }

    public void setCommonErrorCode(int i) {
        this.mCommonErrorCode = i;
    }

    public boolean isIsSupportSync() {
        return this.isSupportSync;
    }

    public void setIsSupportSync(boolean z) {
        this.isSupportSync = z;
    }

    public boolean isSupportOneTouchAbility() {
        return this.isSupportOneTouchAbility;
    }

    public void setSupportOneTouchAbility(boolean z) {
        this.isSupportOneTouchAbility = z;
    }

    public boolean isSupportNewAlbum() {
        return this.isSupportNewAlbum;
    }

    public void setSupportNewAlbum(boolean z) {
        this.isSupportNewAlbum = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mWatchFaceMaxVersion);
        parcel.writeInt(this.mWatchWidth);
        parcel.writeInt(this.mWatchHeight);
        parcel.writeByte(this.mIsSupportSort ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mOtherWatchFaceVersion);
        parcel.writeList(this.mWatchSupportFileType);
        CommonUtils.a(parcel, this.mPresetList);
        CommonUtils.a(parcel, this.mNoPresetList);
        parcel.writeParcelable(this.mCurrentInfo, i);
        CommonUtils.a(parcel, this.mCompatibleList);
        CommonUtils.a(parcel, this.mPurchasedList);
        parcel.writeInt(this.mSupportCapability);
        parcel.writeInt(this.mPowerLevel);
        parcel.writeInt(this.mIsWearSupport);
        parcel.writeInt(this.mPortraitFieldMode);
        parcel.writeInt(this.mSetTimeIndividually);
        parcel.writeInt(this.mCommonErrorCode);
        parcel.writeByte(this.isSupportSync ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSupportOneTouchAbility ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mWatchfaceMode);
    }

    public String toString() {
        return "WatchFaceInfo{WatchFace_MaxVersion=" + this.mWatchFaceMaxVersion + ", WatchFace_Width=" + this.mWatchWidth + ", WatchFace_Height=" + this.mWatchHeight + ", WatchFace_Screen=" + this.mWatchScreen + ", WatchFace_Sort=" + this.mIsSupportSort + ", WatchFace_SupportFileType=" + this.mWatchSupportFileType + ", watchFace_OtherWatchFace_Version=" + this.mOtherWatchFaceVersion + ", mPresetList=" + this.mPresetList.size() + ", mNoPresetList=" + this.mNoPresetList.size() + ", mCurrentInfo=" + this.mCurrentInfo.toString() + ", mCompatibleList=" + this.mCompatibleList.size() + ", mPurchasedList=" + this.mPurchasedList.size() + ", mSupportCapability=" + this.mSupportCapability + ", mPowerLevel=" + this.mPowerLevel + ", mPortraitFieldMode=" + this.mPortraitFieldMode + ", mSetTimeIndividually=" + this.mSetTimeIndividually + ", mIsWearSupport=" + this.mIsSupportSort + ", mCommonErrorCode=" + this.mCommonErrorCode + ", isSupportSync=" + this.isSupportSync + ", isSupportNewAlbum=" + this.isSupportNewAlbum + ", isSupportOneTouchAbility=" + this.isSupportOneTouchAbility + ", mWatchfaceMode=" + this.mWatchfaceMode + "}";
    }

    public int getWatchfaceMode() {
        return this.mWatchfaceMode;
    }

    public void setWatchfaceMode(int i) {
        this.mWatchfaceMode = i;
    }
}
