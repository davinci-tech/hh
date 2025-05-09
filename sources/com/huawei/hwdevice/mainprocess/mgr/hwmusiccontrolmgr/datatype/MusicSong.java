package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.jds;
import defpackage.pf;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class MusicSong implements Parcelable {
    private static final int ASCII_SMALL_A = 97;
    public static final Parcelable.Creator<MusicSong> CREATOR = new Parcelable.Creator<MusicSong>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bHD_, reason: merged with bridge method [inline-methods] */
        public MusicSong createFromParcel(Parcel parcel) {
            return new MusicSong(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public MusicSong[] newArray(int i) {
            return new MusicSong[i];
        }
    };
    private static final int RADIX_DECIMAL = 10;
    public static final int SORT_ORDER_BIG = 1;
    public static final int SORT_ORDER_EQUAL = -1;
    public static final int SORT_ORDER_SMALL = -1;
    public static final String SORT_TYPE_ALBUM = "album";
    public static final String SORT_TYPE_FOLDER = "folder";
    public static final String SORT_TYPE_SINGER = "singer";
    public static final String SORT_TYPE_SONG = "song";
    public static final String UNSTANDARD_LETTER_OF_SONG_NAME = "#";
    private String mAccountName;
    private String mAlbumName;
    private int mChannelCount;
    private long mDuration;
    private String mFileName;
    private String mFirstLetter;
    private String mMimeType;
    private String mMusicAlbumId;
    private int mMusicAppType;
    private int mMusicCopyRight;
    private int mMusicDuration;
    private int mMusicEncryption;
    private String mMusicFolder;
    private String mMusicId;
    private String mMusicIv;
    private String mMusicKey;
    private int mMusicSourceType;
    private int mMusicType;
    private int mOnLineMusicCompletedFlag;
    private String mPackageName;
    private int mSampleRate;
    private String mSongFilePath;
    private String mSongIndex;
    private String mSongName;
    private String mSongSingerName;
    private long mSongSize;
    private long mSourceId;
    private String mSuffix;
    private int mSyncFailedErrorCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicSong() {
        this.mMusicAppType = 0;
        this.mFileName = "";
        this.mSyncFailedErrorCode = 0;
        this.mMusicSourceType = 0;
        this.mOnLineMusicCompletedFlag = 0;
        this.mSuffix = "";
        this.mSampleRate = 0;
        this.mMimeType = "";
        this.mFirstLetter = getFirstLetter(SORT_TYPE_SONG);
    }

    public MusicSong(Parcel parcel) {
        this.mMusicAppType = 0;
        this.mFileName = "";
        this.mSyncFailedErrorCode = 0;
        this.mMusicSourceType = 0;
        this.mOnLineMusicCompletedFlag = 0;
        this.mSuffix = "";
        this.mSampleRate = 0;
        this.mMimeType = "";
        this.mMusicDuration = parcel.readInt();
        this.mMusicCopyRight = parcel.readInt();
        this.mMusicType = parcel.readInt();
        this.mMusicAppType = parcel.readInt();
        this.mMusicId = parcel.readString();
        this.mAccountName = parcel.readString();
        this.mMusicIv = parcel.readString();
        this.mMusicKey = parcel.readString();
        this.mSongIndex = parcel.readString();
        this.mSongName = parcel.readString();
        this.mSongSingerName = parcel.readString();
        this.mSongFilePath = parcel.readString();
        this.mSongSize = parcel.readLong();
        this.mAlbumName = parcel.readString();
        this.mFileName = parcel.readString();
        this.mFirstLetter = parcel.readString();
        this.mSyncFailedErrorCode = parcel.readInt();
        this.mSourceId = parcel.readLong();
        this.mMusicEncryption = parcel.readInt();
        this.mMusicSourceType = parcel.readInt();
        this.mOnLineMusicCompletedFlag = parcel.readInt();
        this.mSuffix = parcel.readString();
        this.mMusicFolder = parcel.readString();
        this.mMusicAlbumId = parcel.readString();
        this.mPackageName = parcel.readString();
        this.mSampleRate = parcel.readInt();
        this.mMimeType = parcel.readString();
        this.mDuration = parcel.readLong();
        this.mChannelCount = parcel.readInt();
    }

    public static c getSortByNameInstance() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class c implements Comparator, Serializable {
        private static final long serialVersionUID = -3362383176034179661L;
        private String e = null;

        c() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if ((obj == null && obj2 == null) || obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            String firstLetter = obj instanceof MusicSong ? ((MusicSong) obj).getFirstLetter(this.e) : "";
            String firstLetter2 = obj2 instanceof MusicSong ? ((MusicSong) obj2).getFirstLetter(this.e) : "";
            if ("#".equals(firstLetter) || "#".equals(firstLetter2)) {
                return (("#".equals(firstLetter) || !"#".equals(firstLetter2)) && "#".equals(firstLetter) && !"#".equals(firstLetter2)) ? 1 : -1;
            }
            return firstLetter.compareTo(firstLetter2);
        }
    }

    public int getMusicDuration() {
        return this.mMusicDuration;
    }

    public void setMusicDuration(int i) {
        this.mMusicDuration = i;
    }

    public int getMusicCopyRight() {
        return this.mMusicCopyRight;
    }

    public void setMusicCopyRight(int i) {
        this.mMusicCopyRight = i;
    }

    public int getMusicType() {
        return this.mMusicType;
    }

    public void setMusicType(int i) {
        this.mMusicType = i;
    }

    public int getMusicAppType() {
        return this.mMusicAppType;
    }

    public void setMusicAppType(int i) {
        this.mMusicAppType = i;
    }

    public String getMusicId() {
        return this.mMusicId;
    }

    public void setMusicId(String str) {
        this.mMusicId = str;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public void setSuffix(String str) {
        this.mSuffix = str;
    }

    public int getOnLineMusicCompletedFlag() {
        return this.mOnLineMusicCompletedFlag;
    }

    public void setOnLineMusicCompletedFlag(int i) {
        this.mOnLineMusicCompletedFlag = i;
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public void setAccountName(String str) {
        this.mAccountName = str;
    }

    public String getMusicIv() {
        return this.mMusicIv;
    }

    public void setMusicIv(String str) {
        this.mMusicIv = str;
    }

    public String getMusicKey() {
        return this.mMusicKey;
    }

    public void setMusicKey(String str) {
        this.mMusicKey = str;
    }

    public String getSongIndex() {
        return TextUtils.isEmpty(this.mSongIndex) ? "0" : this.mSongIndex;
    }

    public void setSongIndex(String str) {
        this.mSongIndex = str;
    }

    public String getSongName() {
        return this.mSongName;
    }

    public void setSongName(String str) {
        this.mSongName = str;
    }

    public String getSongSingerName() {
        return this.mSongSingerName;
    }

    public void setSongSingerName(String str) {
        this.mSongSingerName = str;
    }

    public String getSongFilePath() {
        return this.mSongFilePath;
    }

    public void setSongFilePath(String str) {
        this.mSongFilePath = str;
    }

    public long getSongSize() {
        return this.mSongSize;
    }

    public void setSongSize(long j) {
        this.mSongSize = j;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public void setAlbumName(String str) {
        this.mAlbumName = str;
    }

    public String getFileName() {
        return TextUtils.isEmpty(this.mFileName) ? this.mSongName : this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public long getSourceId() {
        return this.mSourceId;
    }

    public void setSourceId(long j) {
        this.mSourceId = j;
    }

    public int getSyncFailedErrorCode() {
        return this.mSyncFailedErrorCode;
    }

    public void setSyncFailedErrorCode(int i) {
        this.mSyncFailedErrorCode = i;
    }

    public int getMusicEncryption() {
        return this.mMusicEncryption;
    }

    public void setMusicEncryption(int i) {
        this.mMusicEncryption = i;
    }

    public int getMusicSourceType() {
        return this.mMusicSourceType;
    }

    public void setMusicSourceType(int i) {
        this.mMusicSourceType = i;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    public Long getDuration() {
        return Long.valueOf(this.mDuration);
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public int getChannelCount() {
        return this.mChannelCount;
    }

    public void setChannelCount(int i) {
        this.mChannelCount = i;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public String getFirstLetter(String str) {
        String d;
        if (SORT_TYPE_SINGER.equals(str)) {
            d = pf.d(this.mSongSingerName, "");
        } else if (SORT_TYPE_ALBUM.equals(str)) {
            d = pf.d(this.mAlbumName, "");
        } else if (SORT_TYPE_FOLDER.equals(str)) {
            d = pf.d(this.mFileName, "");
        } else {
            d = pf.d(this.mSongName, "");
        }
        if (TextUtils.isEmpty(this.mSongName)) {
            return "#";
        }
        String upperCase = d.substring(0, 1).toUpperCase(Locale.ENGLISH);
        return upperCase.matches("[A-Z]") ? upperCase : upperCase.matches("[0-9]") ? String.valueOf((char) (jds.c(upperCase, 10) + 97)) : "#";
    }

    public String getMusicFolder() {
        return this.mMusicFolder;
    }

    public void setMusicFolder(String str) {
        this.mMusicFolder = str;
    }

    public String getMusicAlbumId() {
        return this.mMusicAlbumId;
    }

    public void setMusicAlbumId(String str) {
        this.mMusicAlbumId = str;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMusicDuration);
        parcel.writeInt(this.mMusicCopyRight);
        parcel.writeInt(this.mMusicType);
        parcel.writeInt(this.mMusicAppType);
        parcel.writeString(this.mMusicId);
        parcel.writeString(this.mAccountName);
        parcel.writeString(this.mMusicIv);
        parcel.writeString(this.mMusicKey);
        parcel.writeString(this.mSongIndex);
        parcel.writeString(this.mSongName);
        parcel.writeString(this.mSongSingerName);
        parcel.writeString(this.mSongFilePath);
        parcel.writeLong(this.mSongSize);
        parcel.writeString(this.mAlbumName);
        parcel.writeString(this.mFileName);
        parcel.writeString(this.mFirstLetter);
        parcel.writeInt(this.mSyncFailedErrorCode);
        parcel.writeLong(this.mSourceId);
        parcel.writeInt(this.mMusicEncryption);
        parcel.writeInt(this.mMusicSourceType);
        parcel.writeInt(this.mOnLineMusicCompletedFlag);
        parcel.writeString(this.mSuffix);
        parcel.writeString(this.mMusicFolder);
        parcel.writeString(this.mMusicAlbumId);
        parcel.writeString(this.mPackageName);
        parcel.writeInt(this.mSampleRate);
        parcel.writeString(this.mMimeType);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(this.mChannelCount);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicSong)) {
            return false;
        }
        MusicSong musicSong = (MusicSong) obj;
        if (getFileName() == null) {
            return false;
        }
        if (getSongSingerName() == null) {
            return getFileName().equals(musicSong.getFileName());
        }
        return getFileName().equals(musicSong.getFileName()) && getSongSingerName().equals(musicSong.getSongSingerName());
    }

    public int hashCode() {
        return getFileName().hashCode();
    }
}
