package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class VideoModel implements Parcelable {
    public static final Parcelable.Creator<VideoModel> CREATOR = new Parcelable.Creator<VideoModel>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bab_, reason: merged with bridge method [inline-methods] */
        public VideoModel createFromParcel(Parcel parcel) {
            return new VideoModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public VideoModel[] newArray(int i) {
            return new VideoModel[i];
        }
    };
    private static final String TAG = "Track_VideoModel";
    private int mDuration;
    private long mId;
    private boolean mIsSelected;
    private long mSize;
    private String mThumbnailPath;
    private String mVideoPath;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VideoModel(long j, String str) {
        this.mId = j;
        this.mVideoPath = str;
    }

    protected VideoModel(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Video in == null");
            return;
        }
        this.mId = parcel.readLong();
        this.mVideoPath = parcel.readString();
        this.mThumbnailPath = parcel.readString();
        this.mSize = parcel.readLong();
        this.mDuration = parcel.readInt();
        this.mIsSelected = parcel.readInt() > 0;
    }

    public String getThumbnailPath() {
        return this.mThumbnailPath;
    }

    public VideoModel setThumbnailPath(String str) {
        this.mThumbnailPath = str;
        return this;
    }

    public long getSize() {
        return this.mSize;
    }

    public VideoModel setSize(long j) {
        this.mSize = j;
        return this;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public VideoModel setDuration(int i) {
        this.mDuration = i;
        return this;
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public void setIsSelected(boolean z) {
        this.mIsSelected = z;
    }

    public void setVideoPath(String str) {
        this.mVideoPath = str;
    }

    public String getVideoPath() {
        return this.mVideoPath;
    }

    public long getId() {
        return this.mId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel parcel is null");
            return;
        }
        parcel.writeLong(this.mId);
        parcel.writeString(this.mVideoPath);
        parcel.writeString(this.mThumbnailPath);
        parcel.writeLong(this.mSize);
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mIsSelected ? 1 : -1);
    }
}
