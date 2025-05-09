package com.huawei.hwcommonmodel.datatypes;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class MusicInfo implements Parcelable {
    public static final Parcelable.Creator<MusicInfo> CREATOR = new Parcelable.Creator<MusicInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.MusicInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFk_, reason: merged with bridge method [inline-methods] */
        public MusicInfo createFromParcel(Parcel parcel) {
            return new MusicInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public MusicInfo[] newArray(int i) {
            return new MusicInfo[i];
        }
    };
    private int mCurrentVolume;
    private int mMaxVolume;
    private int mPlayState;
    private String mSingerName;
    private String mSongName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected MusicInfo(Parcel parcel) {
        this.mSingerName = parcel.readString();
        this.mSongName = parcel.readString();
        this.mPlayState = parcel.readInt();
        this.mMaxVolume = parcel.readInt();
        this.mCurrentVolume = parcel.readInt();
    }

    public MusicInfo() {
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public String getSongName() {
        return this.mSongName;
    }

    public void setSongName(String str) {
        this.mSongName = str;
    }

    public int getPlayState() {
        return this.mPlayState;
    }

    public void setPlayState(int i) {
        this.mPlayState = i;
    }

    public int getMaxVolume() {
        return this.mMaxVolume;
    }

    public void setMaxVolume(int i) {
        this.mMaxVolume = i;
    }

    public int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public void setCurrentVolume(int i) {
        this.mCurrentVolume = i;
    }

    public String toString() {
        return "MusicInfo{mSingerName=" + this.mSingerName + ", mSongName=" + this.mSongName + ", mPlayState=" + this.mPlayState + ", mMaxVolume=" + this.mMaxVolume + ", mCurrentVolume=" + this.mCurrentVolume + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSingerName);
        parcel.writeString(this.mSongName);
        parcel.writeInt(this.mPlayState);
        parcel.writeInt(this.mMaxVolume);
        parcel.writeInt(this.mCurrentVolume);
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("mSingerName", getSingerName());
        bundle.putString("mSongName", getSongName());
        bundle.putInt("mPlayState", getPlayState());
        bundle.putInt("mMaxVolume", getMaxVolume());
        bundle.putInt("mCurrentVolume", getCurrentVolume());
        return bundle;
    }
}
