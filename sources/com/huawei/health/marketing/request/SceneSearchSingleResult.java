package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class SceneSearchSingleResult implements Parcelable {
    public static final Parcelable.Creator<SceneSearchSingleResult> CREATOR = new Parcelable.Creator<SceneSearchSingleResult>() { // from class: com.huawei.health.marketing.request.SceneSearchSingleResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneSearchSingleResult createFromParcel(Parcel parcel) {
            return new SceneSearchSingleResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneSearchSingleResult[] newArray(int i) {
            return new SceneSearchSingleResult[i];
        }
    };

    @SerializedName("cdnUrl")
    private String cdnUrl;

    @SerializedName("fumNum")
    private String funNum;

    @SerializedName("groupNum")
    private String groupNum;

    @SerializedName("highlightTitle")
    private String highlightTitle;

    @SerializedName("prodId")
    private String prodId;

    @SerializedName("resourceId")
    private long resourceId;

    @SerializedName("resourceType")
    private String resourceType;

    @SerializedName("summary")
    private String summary;

    @SerializedName("title")
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SceneSearchSingleResult(Parcel parcel) {
        this.resourceType = parcel.readString();
        this.resourceId = parcel.readLong();
        this.title = parcel.readString();
        this.highlightTitle = parcel.readString();
        this.cdnUrl = parcel.readString();
        this.groupNum = parcel.readString();
        this.funNum = parcel.readString();
        this.prodId = parcel.readString();
        this.summary = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resourceType);
        parcel.writeLong(this.resourceId);
        parcel.writeString(this.title);
        parcel.writeString(this.highlightTitle);
        parcel.writeString(this.cdnUrl);
        parcel.writeString(this.groupNum);
        parcel.writeString(this.funNum);
        parcel.writeString(this.prodId);
        parcel.writeString(this.summary);
    }

    public String toString() {
        return "{\"resourceType\":\"" + this.resourceType + "\",\"resourceId\":" + this.resourceId + ",\"title\":\"" + this.title + "\",\"highlightTitle\":\"" + this.highlightTitle + "\",\"cdnUrl\":\"" + this.cdnUrl + "\",\"groupNum\":\"" + this.groupNum + "\",\"funNum\":\"" + this.funNum + "\",\"prodId\":\"" + this.prodId + "\",\"summary\":\"" + this.summary + "\"}";
    }

    public String getHighlightTitle() {
        return this.highlightTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCdnUrl() {
        return this.cdnUrl;
    }
}
