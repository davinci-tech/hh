package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class MusicPageStruct implements Parcelable {
    public static final Parcelable.Creator<MusicPageStruct> CREATOR = new Parcelable.Creator<MusicPageStruct>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicPageStruct.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bHC_, reason: merged with bridge method [inline-methods] */
        public MusicPageStruct createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            MusicPageStruct musicPageStruct = new MusicPageStruct();
            musicPageStruct.setMusicStartFrameIndex(parcel.readInt());
            musicPageStruct.setMusicEndFrameIndex(parcel.readInt());
            musicPageStruct.setMusicPageCount(parcel.readInt());
            musicPageStruct.setMusicPageHashCode(parcel.readInt());
            return musicPageStruct;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MusicPageStruct[] newArray(int i) {
            return new MusicPageStruct[i];
        }
    };
    private int mMusicEndFrameIndex;
    private int mMusicPageCount;
    private int mMusicPageHashCode;
    private int mMusicStartFrameIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getMusicStartFrameIndex() {
        return this.mMusicStartFrameIndex;
    }

    public void setMusicStartFrameIndex(int i) {
        this.mMusicStartFrameIndex = i;
    }

    public int getMusicEndFrameIndex() {
        return this.mMusicEndFrameIndex;
    }

    public void setMusicEndFrameIndex(int i) {
        this.mMusicEndFrameIndex = i;
    }

    public int getMusicPageCount() {
        return this.mMusicPageCount;
    }

    public void setMusicPageCount(int i) {
        this.mMusicPageCount = i;
    }

    public int getMusicPageHashCode() {
        return this.mMusicPageHashCode;
    }

    public void setMusicPageHashCode(int i) {
        this.mMusicPageHashCode = i;
    }

    public String toString() {
        return "MusicPageStruct{mMusicStartFrameIndex=" + this.mMusicStartFrameIndex + ", mMusicEndFrameIndex=" + this.mMusicEndFrameIndex + ", mMusicPageCount=" + this.mMusicPageCount + ", mMusicPageHashCode=" + this.mMusicPageHashCode + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMusicStartFrameIndex);
        parcel.writeInt(this.mMusicEndFrameIndex);
        parcel.writeInt(this.mMusicPageCount);
        parcel.writeInt(this.mMusicPageHashCode);
    }
}
