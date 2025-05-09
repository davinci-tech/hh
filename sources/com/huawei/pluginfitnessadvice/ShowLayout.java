package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.tencent.connect.share.QzonePublish;

/* loaded from: classes6.dex */
public class ShowLayout implements Parcelable {
    public static final Parcelable.Creator<ShowLayout> CREATOR = new Parcelable.Creator<ShowLayout>() { // from class: com.huawei.pluginfitnessadvice.ShowLayout.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmc_, reason: merged with bridge method [inline-methods] */
        public ShowLayout createFromParcel(Parcel parcel) {
            return new ShowLayout(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ShowLayout[] newArray(int i) {
            return new ShowLayout[i];
        }
    };

    @SerializedName("imageJumpUrl")
    private String mImageJumpUrl;

    @SerializedName("imageUrl")
    private String mImageUrl;

    @SerializedName("type")
    private int mType;

    @SerializedName("videoCoverUrl")
    private String mVideoCoverUrl;

    @SerializedName(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE)
    private long mVideoSize;

    @SerializedName("videoUrl")
    private String mVideoUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ShowLayout(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mImageUrl = parcel.readString();
        this.mImageJumpUrl = parcel.readString();
        this.mVideoUrl = parcel.readString();
        this.mVideoSize = parcel.readLong();
        this.mVideoCoverUrl = parcel.readString();
    }

    private ShowLayout(b bVar) {
        this.mType = bVar.f8476a;
        this.mImageUrl = bVar.d;
        this.mImageJumpUrl = bVar.e;
        this.mVideoUrl = bVar.j;
        this.mVideoSize = bVar.c;
        this.mVideoCoverUrl = bVar.b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mImageJumpUrl);
        parcel.writeString(this.mVideoUrl);
        parcel.writeLong(this.mVideoSize);
        parcel.writeString(this.mVideoCoverUrl);
    }

    /* loaded from: classes9.dex */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private int f8476a;
        private String b;
        private long c;
        private String d;
        private String e;
        private String j;
    }

    public int getType() {
        return this.mType;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getImageJumpUrl() {
        return this.mImageJumpUrl;
    }

    public String getVideoUrl() {
        return this.mVideoUrl;
    }

    public long getVideoSize() {
        return this.mVideoSize;
    }

    public String getVideoCoverUrl() {
        return this.mVideoCoverUrl;
    }

    public String toString() {
        return "ShowLayout{mType=" + this.mType + ", mImageUrl='" + this.mImageUrl + "', mImageJumpUrl='" + this.mImageJumpUrl + "', mVideoUrl='" + this.mVideoUrl + "', mVideoSize=" + this.mVideoSize + ", mVideoCoverUrl='" + this.mVideoCoverUrl + "'}";
    }
}
