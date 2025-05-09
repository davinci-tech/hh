package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class PhotoModel implements Parcelable {
    public static final Parcelable.Creator<PhotoModel> CREATOR = new Parcelable.Creator<PhotoModel>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: aZX_, reason: merged with bridge method [inline-methods] */
        public PhotoModel createFromParcel(Parcel parcel) {
            return new PhotoModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public PhotoModel[] newArray(int i) {
            return new PhotoModel[i];
        }
    };
    private static final int DEFAULT_HASH_CODE = 31;
    private static final String TAG = "PhotoModel";
    private int mHeight;
    private boolean mIsSelected;
    private final String mPath;
    private final long mRowId;
    private long mSize;
    private long mTime;
    private final String mType;
    private int mWidth;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PhotoModel(long j, String str, String str2) {
        this.mRowId = j;
        this.mPath = str;
        this.mType = str2;
    }

    protected PhotoModel(Parcel parcel) {
        this.mRowId = parcel.readLong();
        this.mPath = parcel.readString();
        this.mType = parcel.readString();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mSize = parcel.readLong();
        this.mTime = parcel.readLong();
        this.mIsSelected = parcel.readInt() > 0;
    }

    public long getRowId() {
        return this.mRowId;
    }

    public String getPath() {
        return this.mPath;
    }

    public String getType() {
        return this.mType;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public PhotoModel setWidth(int i) {
        this.mWidth = i;
        return this;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public PhotoModel setHeight(int i) {
        this.mHeight = i;
        return this;
    }

    public long getSize() {
        return this.mSize;
    }

    public PhotoModel setSize(long j) {
        this.mSize = j;
        return this;
    }

    public long getTime() {
        return this.mTime;
    }

    public PhotoModel setTime(long j) {
        this.mTime = j;
        return this;
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public PhotoModel setSelected(boolean z) {
        this.mIsSelected = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.b(TAG, "writeToParcel parcel is null");
            return;
        }
        parcel.writeLong(this.mRowId);
        parcel.writeString(this.mPath);
        parcel.writeString(this.mType);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
        parcel.writeLong(this.mSize);
        parcel.writeLong(this.mTime);
        parcel.writeInt(this.mIsSelected ? 1 : -1);
    }

    public String toString() {
        return "PhotoModel{mRowId='" + this.mRowId + "', mType=" + this.mType + "', mWidth=" + this.mWidth + "', mHeight=" + this.mHeight + "', mSize=" + this.mSize + ", mTime=" + this.mTime + '}';
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PhotoModel)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return ((PhotoModel) obj).mRowId == this.mRowId;
        }
        return ((PhotoModel) obj).mPath.equals(this.mPath);
    }

    public int hashCode() {
        return (((((int) this.mRowId) * 31) + this.mWidth) * 31) + this.mHeight;
    }
}
