package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class MusicStruct implements Parcelable {
    public static final Parcelable.Creator<MusicStruct> CREATOR = new Parcelable.Creator<MusicStruct>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MusicStruct[] newArray(int i) {
            return new MusicStruct[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bHE_, reason: merged with bridge method [inline-methods] */
        public MusicStruct createFromParcel(Parcel parcel) {
            MusicStruct musicStruct = new MusicStruct();
            if (parcel == null) {
                return musicStruct;
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt2 = parcel.readInt();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            int readInt5 = parcel.readInt();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            int readInt6 = parcel.readInt();
            int readInt7 = parcel.readInt();
            String readString9 = parcel.readString();
            String readString10 = parcel.readString();
            String readString11 = parcel.readString();
            musicStruct.setMusicName(readString);
            musicStruct.setMusicSinger(readString2);
            musicStruct.setMusicIndex(readInt);
            musicStruct.setFileName(readString3);
            musicStruct.setFileId(readInt2);
            musicStruct.setMusicAlbum(readString4);
            musicStruct.setMusicId(readString5);
            musicStruct.setMusicType(readInt3);
            musicStruct.setMusicCopyright(readInt4);
            musicStruct.setMusicEncryption(readInt5);
            musicStruct.setMusicKey(readString6);
            musicStruct.setMusicIv(readString7);
            musicStruct.setAccountName(readString8);
            musicStruct.setMusicAppType(readInt6);
            musicStruct.setMusicDuration(readInt7);
            musicStruct.setMusicFolder(readString9);
            musicStruct.setMusicAlbumId(readString10);
            musicStruct.setPackageName(readString11);
            return musicStruct;
        }
    };
    private String mAccountName;
    private int mFileId;
    private String mFileName;
    private String mMusicAlbum;
    private String mMusicAlbumId;
    private String mMusicFolder;
    private String mMusicId;
    private int mMusicIndex;
    private String mMusicIv;
    private String mMusicKey;
    private String mMusicName;
    private String mMusicSinger;
    private String mPackageName;
    private int mMusicType = -1;
    private int mMusicCopyright = -1;
    private int mMusicEncryption = -1;
    private int mMusicAppType = -1;
    private int mMusicDuration = -1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getMusicName() {
        return (String) jdy.d(this.mMusicName);
    }

    public void setMusicName(String str) {
        this.mMusicName = (String) jdy.d(str);
    }

    public String getMusicSinger() {
        return (String) jdy.d(this.mMusicSinger);
    }

    public void setMusicSinger(String str) {
        this.mMusicSinger = (String) jdy.d(str);
    }

    public int getMusicIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicIndex))).intValue();
    }

    public void setMusicIndex(int i) {
        this.mMusicIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getFileName() {
        return (String) jdy.d(this.mFileName);
    }

    public void setFileName(String str) {
        this.mFileName = (String) jdy.d(str);
    }

    public int getFileId() {
        return ((Integer) jdy.d(Integer.valueOf(this.mFileId))).intValue();
    }

    public void setFileId(int i) {
        this.mFileId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getMusicAlbum() {
        return (String) jdy.d(this.mMusicAlbum);
    }

    public void setMusicAlbum(String str) {
        this.mMusicAlbum = (String) jdy.d(str);
    }

    public String getMusicId() {
        return (String) jdy.d(this.mMusicId);
    }

    public void setMusicId(String str) {
        this.mMusicId = (String) jdy.d(str);
    }

    public int getMusicType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicType))).intValue();
    }

    public void setMusicType(int i) {
        this.mMusicType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMusicCopyright() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicCopyright))).intValue();
    }

    public void setMusicCopyright(int i) {
        this.mMusicCopyright = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMusicEncryption() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicEncryption))).intValue();
    }

    public void setMusicEncryption(int i) {
        this.mMusicEncryption = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getMusicKey() {
        return (String) jdy.d(this.mMusicKey);
    }

    public void setMusicKey(String str) {
        this.mMusicKey = (String) jdy.d(str);
    }

    public String getMusicIv() {
        return (String) jdy.d(this.mMusicIv);
    }

    public void setMusicIv(String str) {
        this.mMusicIv = (String) jdy.d(str);
    }

    public String getAccountName() {
        return (String) jdy.d(this.mAccountName);
    }

    public void setAccountName(String str) {
        this.mAccountName = (String) jdy.d(str);
    }

    public int getMusicAppType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicAppType))).intValue();
    }

    public void setMusicAppType(int i) {
        this.mMusicAppType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMusicDuration() {
        return ((Integer) jdy.d(Integer.valueOf(this.mMusicDuration))).intValue();
    }

    public void setMusicDuration(int i) {
        this.mMusicDuration = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getMusicFolder() {
        return (String) jdy.d(this.mMusicFolder);
    }

    public void setMusicFolder(String str) {
        this.mMusicFolder = (String) jdy.d(str);
    }

    public String getMusicAlbumId() {
        return (String) jdy.d(this.mMusicAlbumId);
    }

    public void setMusicAlbumId(String str) {
        this.mMusicAlbumId = (String) jdy.d(str);
    }

    public String getPackageName() {
        return (String) jdy.d(this.mPackageName);
    }

    public void setPackageName(String str) {
        this.mPackageName = (String) jdy.d(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mMusicName);
        parcel.writeString(this.mMusicSinger);
        parcel.writeInt(this.mMusicIndex);
        parcel.writeString(this.mFileName);
        parcel.writeInt(this.mFileId);
        parcel.writeString(this.mMusicAlbum);
        parcel.writeString(this.mMusicId);
        parcel.writeInt(this.mMusicType);
        parcel.writeInt(this.mMusicCopyright);
        parcel.writeInt(this.mMusicEncryption);
        parcel.writeString(this.mMusicKey);
        parcel.writeString(this.mMusicIv);
        parcel.writeString(this.mAccountName);
        parcel.writeInt(this.mMusicAppType);
        parcel.writeInt(this.mMusicDuration);
        parcel.writeString(this.mMusicFolder);
        parcel.writeString(this.mMusicAlbumId);
        parcel.writeString(this.mPackageName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("[ musicName:");
        sb.append(this.mMusicName);
        sb.append(" musicSinger:");
        sb.append(this.mMusicSinger);
        sb.append(" musicIndex:");
        sb.append(this.mMusicIndex);
        sb.append(" fileName:");
        sb.append(this.mFileName);
        sb.append(" MusicAlbum:");
        sb.append(this.mMusicAlbum);
        sb.append(" MusicId:");
        sb.append(this.mMusicId);
        sb.append(" MusicType:");
        sb.append(this.mMusicType);
        sb.append(" MusicCopyright:");
        sb.append(this.mMusicCopyright);
        sb.append(" MusicEncryption:");
        sb.append(this.mMusicEncryption);
        sb.append(" MusicKey:");
        sb.append(this.mMusicKey);
        sb.append(" MusicIv:");
        sb.append(this.mMusicIv);
        sb.append(" AccountName:");
        sb.append(this.mAccountName);
        sb.append(" MusicAppType:");
        sb.append(this.mMusicAppType);
        sb.append(" MusicDuration:");
        sb.append(this.mMusicDuration);
        sb.append(" MusicFolder:");
        sb.append(this.mMusicFolder);
        sb.append(" MusicAlbumId:");
        sb.append(this.mMusicAlbumId);
        sb.append(" PackageName:");
        sb.append(this.mPackageName);
        sb.append("]");
        return sb.toString();
    }
}
