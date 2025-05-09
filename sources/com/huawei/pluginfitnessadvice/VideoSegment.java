package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class VideoSegment implements Parcelable {
    public static final Parcelable.Creator<VideoSegment> CREATOR = new Parcelable.Creator<VideoSegment>() { // from class: com.huawei.pluginfitnessadvice.VideoSegment.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmg_, reason: merged with bridge method [inline-methods] */
        public VideoSegment createFromParcel(Parcel parcel) {
            return new VideoSegment(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public VideoSegment[] newArray(int i) {
            return new VideoSegment[i];
        }
    };
    private static final String TAG = "VideoSegment";

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("endTime")
    private long mEndTime;

    @SerializedName("startTime")
    private long mStartTime;

    @SerializedName("thumbnail")
    private String mThumbnail;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VideoSegment() {
    }

    private VideoSegment(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "VideoSegment inParcel is null");
            return;
        }
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mDuration = parcel.readInt();
        this.mThumbnail = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "VideoSegment destParcel is null");
            return;
        }
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeInt(this.mDuration);
        parcel.writeString(this.mThumbnail);
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public String getThumbnail() {
        return this.mThumbnail;
    }

    public void setThumbnail(String str) {
        this.mThumbnail = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
