package com.huawei.phoneservice.feedbackcommon.photolibrary.internal.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.feedbackcommon.photolibrary.MimeType;

/* loaded from: classes5.dex */
public class MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaItem> CREATOR = new e();
    private static final String TAG = "MediaItem---";
    private String attachId;
    private long bucketId;
    private long dateAddedTime;
    private long duration;
    private String fileName;
    private String filePath;
    private int mediaHeight;
    private long mediaId;
    private int mediaWidth;
    private String mimeType;
    private long parentBucketId;
    private String parentFolderName;
    private String realPath;
    private long size;
    private String uri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mimeType);
        parcel.writeString(this.attachId);
        parcel.writeLong(this.size);
        parcel.writeLong(this.duration);
        parcel.writeString(this.filePath);
        parcel.writeString(this.fileName);
        parcel.writeInt(this.mediaWidth);
        parcel.writeInt(this.mediaHeight);
        parcel.writeLong(this.bucketId);
        parcel.writeLong(this.mediaId);
        parcel.writeString(this.parentFolderName);
        parcel.writeLong(this.parentBucketId);
        parcel.writeString(this.realPath);
        parcel.writeLong(this.dateAddedTime);
        parcel.writeString(this.uri);
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setRealPath(String str) {
        this.realPath = str;
    }

    public void setParentFolderName(String str) {
        this.parentFolderName = str;
    }

    public void setParentBucketId(long j) {
        this.parentBucketId = j;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public void setMediaWidth(int i) {
        this.mediaWidth = i;
    }

    public void setMediaId(long j) {
        this.mediaId = j;
    }

    public void setMediaHeight(int i) {
        this.mediaHeight = i;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setDateAddedTime(long j) {
        this.dateAddedTime = j;
    }

    public void setBucketId(long j) {
        this.bucketId = j;
    }

    public void setAttachId(String str) {
        this.attachId = str;
    }

    public boolean isVideo() {
        return MimeType.isVideo(this.mimeType);
    }

    public boolean isImage() {
        return MimeType.c(this.mimeType);
    }

    public int hashCode() {
        try {
            String str = this.mimeType;
            r0 = ((str != null ? str.hashCode() + 31 : 1) * 31) + Long.valueOf(this.size).hashCode();
            return (r0 * 31) + Long.valueOf(this.duration).hashCode();
        } catch (Exception e2) {
            i.c(TAG, e2.getMessage());
            return r0;
        }
    }

    public String getUri() {
        return this.uri;
    }

    public long getSize() {
        return this.size;
    }

    public String getRealPath() {
        return this.realPath;
    }

    public String getParentFolderName() {
        return this.parentFolderName;
    }

    public long getParentBucketId() {
        return this.parentBucketId;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getMediaWidth() {
        return this.mediaWidth;
    }

    public long getMediaId() {
        return this.mediaId;
    }

    public int getMediaHeight() {
        return this.mediaHeight;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getDuration() {
        return this.duration;
    }

    public long getDateAddedTime() {
        return this.dateAddedTime;
    }

    public long getBucketId() {
        return this.bucketId;
    }

    public String getAttachId() {
        return this.attachId;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        String str = this.mimeType;
        return ((str != null && str.equals(mediaItem.mimeType)) || (this.mimeType == null && mediaItem.mimeType == null)) && this.duration == mediaItem.duration && this.size == mediaItem.size;
    }

    public MediaItem(String str, String str2, String str3, long j) {
        this.uri = str;
        this.filePath = str2;
        this.mimeType = str3;
        this.size = j;
    }

    public MediaItem(String str, String str2, long j, long j2, String str3, String str4) {
        this.mimeType = TextUtils.isEmpty(str) ? MimeType.getMimeType(str2) : str;
        this.size = j;
        this.duration = j2;
        this.attachId = str3;
        this.filePath = str2;
        this.uri = str4;
    }

    public MediaItem(String str, String str2, long j) {
        this.filePath = str;
        this.mimeType = str2;
        this.size = j;
    }

    class e implements Parcelable.Creator<MediaItem> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public MediaItem[] newArray(int i) {
            return new MediaItem[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: cfn_, reason: merged with bridge method [inline-methods] */
        public MediaItem createFromParcel(Parcel parcel) {
            return new MediaItem(parcel, null);
        }

        e() {
        }
    }

    /* synthetic */ MediaItem(Parcel parcel, e eVar) {
        this(parcel);
    }

    private MediaItem(Parcel parcel) {
        this.mimeType = parcel.readString();
        this.attachId = parcel.readString();
        this.size = parcel.readLong();
        this.duration = parcel.readLong();
        this.filePath = parcel.readString();
        this.fileName = parcel.readString();
        this.mediaWidth = parcel.readInt();
        this.mediaHeight = parcel.readInt();
        this.bucketId = parcel.readLong();
        this.mediaId = parcel.readLong();
        this.parentFolderName = parcel.readString();
        this.parentBucketId = parcel.readLong();
        this.realPath = parcel.readString();
        this.dateAddedTime = parcel.readLong();
        this.uri = parcel.readString();
    }

    public MediaItem() {
    }
}
