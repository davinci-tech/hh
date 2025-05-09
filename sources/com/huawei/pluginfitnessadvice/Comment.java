package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class Comment implements Parcelable {
    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() { // from class: com.huawei.pluginfitnessadvice.Comment.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clL_, reason: merged with bridge method [inline-methods] */
        public Comment createFromParcel(Parcel parcel) {
            return new Comment(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Comment[] newArray(int i) {
            return new Comment[i];
        }
    };
    private static final String TAG = "Comment";

    @SerializedName("content")
    private String mContent;

    @SerializedName("id")
    private String mId;

    @SerializedName("length")
    private int mLength;

    @SerializedName("name")
    private String mName;

    @SerializedName("playType")
    private String mPlayType;

    @SerializedName("playValue")
    private String mPlayValue;

    @SerializedName("time")
    private float mTime;

    @SerializedName("type")
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Comment() {
    }

    protected Comment(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Comment in == null");
            return;
        }
        this.mId = parcel.readString();
        this.mType = parcel.readInt();
        this.mName = parcel.readString();
        this.mContent = parcel.readString();
        this.mLength = parcel.readInt();
        this.mTime = parcel.readFloat();
        this.mPlayType = parcel.readString();
        this.mPlayValue = parcel.readString();
    }

    public String getId() {
        return this.mId;
    }

    public void saveId(String str) {
        this.mId = str;
    }

    public int getType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public String acquireName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public String acquireContent() {
        return this.mContent;
    }

    public void saveContent(String str) {
        this.mContent = str;
    }

    public int getLength() {
        return this.mLength;
    }

    public void saveLength(int i) {
        this.mLength = i;
    }

    public float acquireTime() {
        return this.mTime;
    }

    public void saveTime(float f) {
        this.mTime = f;
    }

    public void savePlayType(String str) {
        this.mPlayType = str;
    }

    public String acquirePlayType() {
        return this.mPlayType;
    }

    public void savePlayValue(String str) {
        this.mPlayValue = str;
    }

    public String acquirePlayValue() {
        return this.mPlayValue;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mName);
        parcel.writeString(this.mContent);
        parcel.writeInt(this.mLength);
        parcel.writeFloat(this.mTime);
        parcel.writeString(this.mPlayType);
        parcel.writeString(this.mPlayValue);
    }
}
