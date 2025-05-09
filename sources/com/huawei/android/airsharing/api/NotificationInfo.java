package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.xa;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NotificationInfo implements Parcelable {
    public static final int STATE_BUFFERING = 4;
    public static final int STATE_PAUSE = 3;
    public static final int STATE_PLAY = 2;
    public static final int STATE_STOP = 1;
    private static final String TAG = "NotificationInfo";
    private float mPlayRate;
    public static final Parcelable.Creator<NotificationInfo> CREATOR = new Parcelable.Creator<NotificationInfo>() { // from class: com.huawei.android.airsharing.api.NotificationInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public NotificationInfo[] newArray(int i) {
            return new NotificationInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dN_, reason: merged with bridge method [inline-methods] */
        public NotificationInfo createFromParcel(Parcel parcel) {
            NotificationInfo notificationInfo = new NotificationInfo();
            notificationInfo.setPlayerState(parcel.readInt());
            notificationInfo.setDuration(parcel.readInt());
            notificationInfo.setMinSystemVolume(parcel.readInt());
            notificationInfo.setMaxSystemVolume(parcel.readInt());
            notificationInfo.setCurrentSystemVolume(parcel.readInt());
            notificationInfo.setErrorCode(parcel.readInt());
            notificationInfo.setErrorDescription(parcel.readString());
            notificationInfo.setIndexInList(parcel.readInt());
            notificationInfo.setCurrPosition(parcel.readInt());
            notificationInfo.setAudioTrack(parcel.readString());
            notificationInfo.setCustomData(parcel.readString());
            notificationInfo.setBrightness(parcel.readInt());
            notificationInfo.setSubtitleTrack(parcel.readString());
            ArrayList arrayList = new ArrayList();
            parcel.readList(arrayList, null);
            notificationInfo.setAudioTracks(arrayList);
            ArrayList arrayList2 = new ArrayList();
            parcel.readList(arrayList2, null);
            notificationInfo.setSubtitleTracks(arrayList2);
            notificationInfo.setVideoDefinition((CastVideoDefinition) parcel.readParcelable(CastVideoDefinition.class.getClassLoader()));
            ArrayList arrayList3 = new ArrayList();
            parcel.readList(arrayList3, CastVideoDefinition.class.getClassLoader());
            notificationInfo.setVideoDefinitionList(arrayList3);
            notificationInfo.setLoadStatus(parcel.readInt());
            notificationInfo.setIsMute(parcel.readBoolean());
            notificationInfo.setPlayRate(parcel.readFloat());
            notificationInfo.setRepeatMode(parcel.readString());
            return notificationInfo;
        }
    };
    private static xa sLog = xa.c();
    private int mPlayerState = -1;
    private int mDuration = 0;
    private int mMinSystemVolume = 0;
    private int mMaxSystemVolume = 0;
    private int mCurrentSystemVolume = 0;
    private int mErrorCode = 0;
    private String mErrorDescription = null;
    private int mIndexInList = 0;
    private int mCurrPosition = 0;
    private String mAudioTrack = null;
    private String mCustomData = null;
    private int mBrightness = 0;
    private String mSubtitleTrack = null;
    private List<String> mAudioTracks = null;
    private List<String> mSubtitleTracks = null;
    private CastVideoDefinition mVideoDefinition = null;
    private List<CastVideoDefinition> mVideoDefinitionList = null;
    private int mLoadStatus = 0;
    private boolean mIsMute = false;
    private String mRepeatMode = null;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<String> getSubtitleTracks() {
        return this.mSubtitleTracks;
    }

    public void setSubtitleTracks(List<String> list) {
        this.mSubtitleTracks = list;
    }

    public List<String> getAudioTracks() {
        return this.mAudioTracks;
    }

    public void setAudioTracks(List<String> list) {
        this.mAudioTracks = list;
    }

    public String getString(String str) {
        if (!TextUtils.isEmpty(str)) {
            return null;
        }
        sLog.d(TAG, "getString key is null or empty");
        return null;
    }

    public float getFloat(String str) {
        if (!TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        sLog.d(TAG, "getFloat key is null or empty");
        return 0.0f;
    }

    private String getSubtitleTrack() {
        return this.mSubtitleTrack;
    }

    public void setSubtitleTrack(String str) {
        this.mSubtitleTrack = str;
    }

    private int getBrightness() {
        return this.mBrightness;
    }

    public void setBrightness(int i) {
        this.mBrightness = i;
    }

    private String getCustomData() {
        return this.mCustomData;
    }

    public void setCustomData(String str) {
        this.mCustomData = str;
    }

    private String getAudioTrack() {
        return this.mAudioTrack;
    }

    public void setAudioTrack(String str) {
        this.mAudioTrack = str;
    }

    public int getCurrPosition() {
        return this.mCurrPosition;
    }

    public void setCurrPosition(int i) {
        this.mCurrPosition = i;
    }

    public int getIndexInList() {
        return this.mIndexInList;
    }

    public void setIndexInList(int i) {
        this.mIndexInList = i;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }

    public void setErrorDescription(String str) {
        this.mErrorDescription = str;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getCurrentSystemVolume() {
        return this.mCurrentSystemVolume;
    }

    public void setCurrentSystemVolume(int i) {
        this.mCurrentSystemVolume = i;
    }

    public int getMaxSystemVolume() {
        return this.mMaxSystemVolume;
    }

    public void setMaxSystemVolume(int i) {
        this.mMaxSystemVolume = i;
    }

    public int getMinSystemVolume() {
        return this.mMinSystemVolume;
    }

    public void setMinSystemVolume(int i) {
        this.mMinSystemVolume = i;
    }

    public int getPlayerState() {
        return this.mPlayerState;
    }

    public void setPlayerState(int i) {
        this.mPlayerState = i;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public CastVideoDefinition getVideoDefinition() {
        return this.mVideoDefinition;
    }

    public void setVideoDefinition(CastVideoDefinition castVideoDefinition) {
        this.mVideoDefinition = castVideoDefinition;
    }

    public List<CastVideoDefinition> getVideoDefinitionList() {
        return this.mVideoDefinitionList;
    }

    public void setVideoDefinitionList(List<CastVideoDefinition> list) {
        this.mVideoDefinitionList = list;
    }

    public int getLoadStatus() {
        return this.mLoadStatus;
    }

    public void setLoadStatus(int i) {
        this.mLoadStatus = i;
    }

    public boolean getIsMute() {
        return this.mIsMute;
    }

    public void setIsMute(boolean z) {
        this.mIsMute = z;
    }

    public float getPlayRate() {
        return this.mPlayRate;
    }

    public void setPlayRate(float f) {
        this.mPlayRate = f;
    }

    public String getRepeatMode() {
        return this.mRepeatMode;
    }

    public void setRepeatMode(String str) {
        this.mRepeatMode = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mPlayerState);
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mMinSystemVolume);
        parcel.writeInt(this.mMaxSystemVolume);
        parcel.writeInt(this.mCurrentSystemVolume);
        parcel.writeInt(this.mErrorCode);
        parcel.writeString(this.mErrorDescription);
        parcel.writeInt(this.mIndexInList);
        parcel.writeInt(this.mCurrPosition);
        parcel.writeString(this.mAudioTrack);
        parcel.writeString(this.mCustomData);
        parcel.writeInt(this.mBrightness);
        parcel.writeString(this.mSubtitleTrack);
        parcel.writeList(this.mAudioTracks);
        parcel.writeList(this.mSubtitleTracks);
        parcel.writeParcelable(this.mVideoDefinition, 0);
        parcel.writeList(this.mVideoDefinitionList);
        parcel.writeInt(this.mLoadStatus);
        parcel.writeBoolean(this.mIsMute);
        parcel.writeFloat(this.mPlayRate);
        parcel.writeString(this.mRepeatMode);
    }
}
