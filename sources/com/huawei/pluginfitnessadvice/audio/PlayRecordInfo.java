package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class PlayRecordInfo implements Parcelable {
    public static final Parcelable.Creator<PlayRecordInfo> CREATOR = new Parcelable.Creator<PlayRecordInfo>() { // from class: com.huawei.pluginfitnessadvice.audio.PlayRecordInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmk_, reason: merged with bridge method [inline-methods] */
        public PlayRecordInfo createFromParcel(Parcel parcel) {
            return new PlayRecordInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public PlayRecordInfo[] newArray(int i) {
            return new PlayRecordInfo[i];
        }
    };

    @SerializedName("audioId")
    private String mAudioId;

    @SerializedName("count")
    private int mCompleteCount;

    @SerializedName("duration")
    private long mDuration;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long mModifyTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayRecordInfo() {
    }

    protected PlayRecordInfo(Parcel parcel) {
        this.mAudioId = parcel.readString();
        this.mDuration = parcel.readLong();
        this.mCompleteCount = parcel.readInt();
        this.mModifyTime = parcel.readLong();
    }

    public String getAudioId() {
        return this.mAudioId;
    }

    public void setAudioId(String str) {
        this.mAudioId = str;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public int getCompleteCount() {
        return this.mCompleteCount;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public void setCompleteCount(int i) {
        this.mCompleteCount = i;
    }

    public long getModifyTime() {
        return this.mModifyTime;
    }

    public void setModifyTime(long j) {
        this.mModifyTime = j;
    }

    public String toString() {
        return "PlayRecordInfo{mAudioId='" + this.mAudioId + "', mDuration=" + this.mDuration + ", mCompleteCount=" + this.mCompleteCount + ", mModifyTime=" + this.mModifyTime + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAudioId);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(this.mCompleteCount);
        parcel.writeLong(this.mModifyTime);
    }
}
