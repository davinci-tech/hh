package com.huawei.healthcloud.plugintrack.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.util.Objects;

/* loaded from: classes4.dex */
public class SportBeat implements Parcelable {
    public static final int BEAT_CHANGE = 1;
    public static final int BETA_AUDIO_FOUR = 3;
    public static final int BETA_AUDIO_ONE = 0;
    public static final int BETA_AUDIO_THREE = 2;
    public static final int BETA_AUDIO_TWO = 1;
    public static final Parcelable.Creator<SportBeat> CREATOR = new Parcelable.Creator<SportBeat>() { // from class: com.huawei.healthcloud.plugintrack.model.SportBeat.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: aVJ_, reason: merged with bridge method [inline-methods] */
        public SportBeat createFromParcel(Parcel parcel) {
            return new SportBeat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SportBeat[] newArray(int i) {
            return new SportBeat[i];
        }
    };
    public static final int PLAY_IDLE = 0;
    public static final int PLAY_PAUSE = 2;
    public static final int SWITCH_CLOSE = 0;
    public static final int SWITCH_OPEN = 1;

    @SerializedName(PresenterUtils.AUDIO)
    private int mAudio;

    @SerializedName("frequency")
    private int mFrequency;

    @SerializedName("isOpenSwitch")
    private int mIsOpen;
    private int mPlayStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SportBeat(boolean z, int i, int i2) {
        this.mPlayStatus = 0;
        this.mIsOpen = z ? 1 : 0;
        this.mFrequency = i;
        this.mAudio = i2;
    }

    public SportBeat(Parcel parcel) {
        this.mPlayStatus = 0;
        this.mIsOpen = parcel.readInt();
        this.mFrequency = parcel.readInt();
        this.mAudio = parcel.readInt();
        this.mPlayStatus = parcel.readInt();
    }

    public boolean isOpen() {
        return this.mIsOpen == 1;
    }

    public void setIsOpen(boolean z) {
        this.mIsOpen = z ? 1 : 0;
    }

    public int getFrequency() {
        return this.mFrequency;
    }

    public void setFrequency(int i) {
        this.mFrequency = i;
    }

    public int getAudio() {
        return this.mAudio;
    }

    public void setAudio(int i) {
        this.mAudio = i;
    }

    public int getPlayStatus() {
        return this.mPlayStatus;
    }

    public void setPlayStatus(int i) {
        this.mPlayStatus = i;
    }

    public String toString() {
        return "SportBeat{mIsOpen=" + this.mIsOpen + ", mFrequency=" + this.mFrequency + ", mAudio=" + this.mAudio + ", mPlayStatus=" + this.mPlayStatus + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SportBeat sportBeat = (SportBeat) obj;
        return this.mIsOpen == sportBeat.mIsOpen && this.mFrequency == sportBeat.mFrequency && this.mAudio == sportBeat.mAudio;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mIsOpen), Integer.valueOf(this.mFrequency), Integer.valueOf(this.mAudio));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIsOpen);
        parcel.writeInt(this.mFrequency);
        parcel.writeInt(this.mAudio);
        parcel.writeInt(this.mPlayStatus);
    }
}
