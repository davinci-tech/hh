package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.jdy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class NegotiationData implements Parcelable {
    public static final Parcelable.Creator<NegotiationData> CREATOR = new Parcelable.Creator<NegotiationData>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public NegotiationData[] newArray(int i) {
            return new NegotiationData[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bHF_, reason: merged with bridge method [inline-methods] */
        public NegotiationData createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            NegotiationData negotiationData = new NegotiationData();
            negotiationData.setStorageSpace(readInt);
            negotiationData.setMaxMusicNumber(readInt2);
            negotiationData.setMusicFormatList(parcel.createStringArrayList());
            negotiationData.setMusicCount(parcel.readInt());
            negotiationData.setMusicPageStructList(parcel.createTypedArrayList(MusicPageStruct.CREATOR));
            return negotiationData;
        }
    };
    private List<String> mMusicFormatList;
    private int mStorageSpace;
    private List<TypeStruct> mTypeStructList;
    private List<Integer> mMp3SampleRateList = new ArrayList(16);
    private List<Integer> mAacSampleRateList = new ArrayList(16);
    private boolean mIsAacSupportAdts = false;
    private int mMaxMusicNumber = -1;
    private int mMaxFolderNumber = -1;
    private int mMusicQuality = -1;
    private int mMusicType = -1;
    private int mMusicCount = -1;
    private List<MusicPageStruct> mMusicPageStructList = new ArrayList(16);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<String> getMusicFormatList() {
        return (List) jdy.d(this.mMusicFormatList);
    }

    public void setMusicFormatList(List<String> list) {
        this.mMusicFormatList = (List) jdy.d(list);
    }

    public List<TypeStruct> getTypeStructList() {
        return (List) jdy.d(this.mTypeStructList);
    }

    public void setTypeStructList(List<TypeStruct> list) {
        this.mTypeStructList = (List) jdy.d(list);
    }

    public int getStorageSpace() {
        return ((Integer) jdy.d(Integer.valueOf(this.mStorageSpace))).intValue();
    }

    public void setStorageSpace(int i) {
        this.mStorageSpace = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMaxMusicNumber() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMaxMusicNumber))).intValue();
    }

    public void setMaxMusicNumber(int i) {
        this.mMaxMusicNumber = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMaxFolderNumber() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMaxFolderNumber))).intValue();
    }

    public void setMaxFolderNumber(int i) {
        this.mMaxFolderNumber = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMusicQuality() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicQuality))).intValue();
    }

    public void setMusicQuality(int i) {
        this.mMusicQuality = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void setMusicType(int i) {
        this.mMusicType = i;
    }

    public int getMusicType() {
        return this.mMusicType;
    }

    public int getMusicCount() {
        return this.mMusicCount;
    }

    public void setMusicCount(int i) {
        this.mMusicCount = i;
    }

    public List<MusicPageStruct> getMusicPageStructList() {
        return this.mMusicPageStructList;
    }

    public void setMusicPageStructList(List<MusicPageStruct> list) {
        this.mMusicPageStructList = list;
    }

    public void setMp3SampleRate(List<Integer> list) {
        this.mMp3SampleRateList = list;
    }

    public List<Integer> getMp3SampleRate() {
        return this.mMp3SampleRateList;
    }

    public void setAacSampleRate(List<Integer> list) {
        this.mAacSampleRateList = list;
    }

    public List<Integer> getAacSampleRate() {
        return this.mAacSampleRateList;
    }

    public void setAacSupportAdts(boolean z) {
        this.mIsAacSupportAdts = z;
    }

    public boolean getAacSupportAdts() {
        return this.mIsAacSupportAdts;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mStorageSpace);
        parcel.writeInt(this.mMaxMusicNumber);
        parcel.writeStringList(this.mMusicFormatList);
        parcel.writeInt(this.mMusicCount);
        parcel.writeTypedList(this.mMusicPageStructList);
    }

    public static class TypeStruct {
        private int musicBitrate;
        private int musicChannelCount;
        private int musicEncode;
        private int musicEncryptedBitrate;
        private int musicType;
        private String sampleRate;

        public int getMusicType() {
            return this.musicType;
        }

        public void setMusicType(int i) {
            this.musicType = i;
        }

        public String getSampleRate() {
            return this.sampleRate;
        }

        public void setSampleRate(String str) {
            this.sampleRate = str;
        }

        public int getMusicEncode() {
            return this.musicEncode;
        }

        public void setMusicEncode(int i) {
            this.musicEncode = i;
        }

        public int getMusicBitrate() {
            return this.musicBitrate;
        }

        public void setMusicBitrate(int i) {
            this.musicBitrate = i;
        }

        public int getMusicChannelCount() {
            return this.musicChannelCount;
        }

        public void setMusicChannelCount(int i) {
            this.musicChannelCount = i;
        }

        public int getMusicEncryptedBitrate() {
            return this.musicEncryptedBitrate;
        }

        public void setMusicEncryptedBitrate(int i) {
            this.musicEncryptedBitrate = i;
        }
    }
}
