package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class LongVideoInfo implements Parcelable {
    public static final Parcelable.Creator<LongVideoInfo> CREATOR = new Parcelable.Creator<LongVideoInfo>() { // from class: com.huawei.pluginfitnessadvice.LongVideoInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: clW_, reason: merged with bridge method [inline-methods] */
        public LongVideoInfo createFromParcel(Parcel parcel) {
            return new LongVideoInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public LongVideoInfo[] newArray(int i) {
            return new LongVideoInfo[i];
        }
    };
    private static final String TAG = "LongVideoInfo";

    @SerializedName("resultCode")
    private int mResultCode;

    @SerializedName("resultDesc")
    private String mResultDesc;

    @SerializedName("subtitlesUrl")
    private String mSubtitlesUrl;

    @SerializedName("url")
    private String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LongVideoInfo() {
    }

    private LongVideoInfo(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Video inParcel == null");
            return;
        }
        this.mResultCode = parcel.readInt();
        this.mResultDesc = parcel.readString();
        this.mUrl = parcel.readString();
        this.mSubtitlesUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeInt(this.mResultCode);
        parcel.writeString(this.mResultDesc);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mSubtitlesUrl);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public String getResultDesc() {
        return this.mResultDesc;
    }

    public void setResultDesc(String str) {
        this.mResultDesc = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getSubtitlesUrl() {
        return this.mSubtitlesUrl;
    }

    public void setSubtitlesUrl(String str) {
        this.mSubtitlesUrl = str;
    }
}
