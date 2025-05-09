package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class Video implements Parcelable {
    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() { // from class: com.huawei.pluginfitnessadvice.Video.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmf_, reason: merged with bridge method [inline-methods] */
        public Video createFromParcel(Parcel parcel) {
            return new Video(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Video[] newArray(int i) {
            return new Video[i];
        }
    };
    private static final String TAG = "Video";

    @SerializedName("actionCount")
    private int mActionCount;

    @SerializedName("during")
    private int mDuration;

    @SerializedName(RecommendConstants.FILE_ID)
    private String mFileId;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int mGender;

    @SerializedName("id")
    private String mId;

    @SerializedName("identification")
    private String mIdentification;

    @SerializedName("isLongVideo")
    private int mIsLongVideo;

    @SerializedName("length")
    private int mLength;

    @SerializedName("logoImgUrl")
    private String mLogoImgUrl;

    @SerializedName("name")
    private String mName;

    @SerializedName("thumbnail")
    private String mThumbnail;

    @SerializedName("type")
    private int mType;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("videoSegmentList")
    private List<VideoSegment> mVideoSegmentList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Video() {
    }

    protected Video(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Video inParcel == null");
            return;
        }
        this.mId = parcel.readString();
        this.mUrl = parcel.readString();
        this.mType = parcel.readInt();
        this.mIdentification = parcel.readString();
        this.mLength = parcel.readInt();
        this.mName = parcel.readString();
        this.mThumbnail = parcel.readString();
        this.mGender = parcel.readInt();
        this.mActionCount = parcel.readInt();
        this.mDuration = parcel.readInt();
        this.mLogoImgUrl = parcel.readString();
        this.mIsLongVideo = parcel.readInt();
        this.mFileId = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.mVideoSegmentList = arrayList;
        parcel.readList(arrayList, VideoSegment.class.getClassLoader());
    }

    public int getIsLongVideo() {
        return this.mIsLongVideo;
    }

    public void setIsLongVideo(int i) {
        this.mIsLongVideo = i;
    }

    public String getFileId() {
        return this.mFileId;
    }

    public void setFileId(String str) {
        this.mFileId = str;
    }

    public List<VideoSegment> getVideoSegmentList() {
        return this.mVideoSegmentList;
    }

    public void setVideoSegmentList(List<VideoSegment> list) {
        this.mVideoSegmentList = list;
    }

    public String getLogoImgUrl() {
        return this.mLogoImgUrl;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeString(this.mUrl);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mIdentification);
        parcel.writeInt(this.mLength);
        parcel.writeString(this.mName);
        parcel.writeString(this.mThumbnail);
        parcel.writeInt(this.mGender);
        parcel.writeInt(this.mActionCount);
        parcel.writeInt(this.mDuration);
        parcel.writeString(this.mLogoImgUrl);
        parcel.writeInt(this.mIsLongVideo);
        parcel.writeString(this.mFileId);
        parcel.writeList(this.mVideoSegmentList);
    }

    public void saveLogoImgUrl(String str) {
        this.mLogoImgUrl = str;
    }

    public String getName() {
        return this.mName;
    }

    public String getId() {
        return this.mId;
    }

    public void saveId(String str) {
        this.mId = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void saveUrl(String str) {
        this.mUrl = str;
    }

    public int getType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public String getIdentification() {
        return this.mIdentification;
    }

    public void saveIdentification(String str) {
        this.mIdentification = str;
    }

    public int getLength() {
        return this.mLength;
    }

    public void saveLength(int i) {
        this.mLength = i;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public String getThumbnail() {
        return this.mThumbnail;
    }

    public void saveThumbnail(String str) {
        this.mThumbnail = str;
    }

    public int getGender() {
        return this.mGender;
    }

    public void saveGender(int i) {
        this.mGender = i;
    }

    public int getActionCount() {
        return this.mActionCount;
    }

    public void saveActionCount(int i) {
        this.mActionCount = i;
    }

    public int getDuring() {
        return this.mDuration;
    }

    public void saveDuring(int i) {
        this.mDuration = i;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
