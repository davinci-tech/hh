package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;

/* loaded from: classes7.dex */
public class WiseContentHeaders implements Parcelable {
    public static final Parcelable.Creator<WiseContentHeaders> CREATOR = new Parcelable.Creator<WiseContentHeaders>() { // from class: com.huawei.watchface.mvp.model.datatype.WiseContentHeaders.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WiseContentHeaders createFromParcel(Parcel parcel) {
            return new WiseContentHeaders(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WiseContentHeaders[] newArray(int i) {
            return new WiseContentHeaders[i];
        }
    };

    @SerializedName("Authorization")
    private String authorization;

    @SerializedName("x-amz-client-request-id")
    private String clientRequestId;
    private String connection;

    @SerializedName("Content-Type")
    private String contentType;

    @SerializedName("Host")
    private String host;

    @SerializedName(FeedbackWebConstants.CONTENT_MD5)
    private String md5Content;

    @SerializedName("x-amz-date")
    private String requestTime;

    @SerializedName("x-amz-content-sha256")
    private String sha256Value;

    @SerializedName(FeedbackWebConstants.USER_AGENT)
    private String userAgent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(String str) {
        this.authorization = str;
    }

    public String getSha256Value() {
        return this.sha256Value;
    }

    public void setSha256Value(String str) {
        this.sha256Value = str;
    }

    public String getClientRequestsId() {
        return this.clientRequestId;
    }

    public void setClientRequestsId(String str) {
        this.clientRequestId = str;
    }

    public String getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(String str) {
        this.requestTime = str;
    }

    public String getConnection() {
        return this.connection;
    }

    public void setConnection(String str) {
        this.connection = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getMd5Content() {
        return this.md5Content;
    }

    public void setMd5Content(String str) {
        this.md5Content = str;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.authorization);
        parcel.writeString(this.sha256Value);
        parcel.writeString(this.clientRequestId);
        parcel.writeString(this.requestTime);
        parcel.writeString(this.connection);
        parcel.writeString(this.host);
        parcel.writeString(this.md5Content);
        parcel.writeString(this.contentType);
        parcel.writeString(this.userAgent);
    }

    protected WiseContentHeaders(Parcel parcel) {
        this.authorization = parcel.readString();
        this.sha256Value = parcel.readString();
        this.clientRequestId = parcel.readString();
        this.requestTime = parcel.readString();
        this.connection = parcel.readString();
        this.host = parcel.readString();
        this.md5Content = parcel.readString();
        this.contentType = parcel.readString();
        this.userAgent = parcel.readString();
    }
}
