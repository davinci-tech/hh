package com.huawei.profile.coordinator.http;

/* loaded from: classes6.dex */
public class ProfileHttpResponse {
    private long downloadLength;
    private String responseBody;
    private int responseCode;
    private String responseMessage;

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String str) {
        this.responseMessage = str;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public long getDownloadLength() {
        return this.downloadLength;
    }

    public void setDownloadLength(long j) {
        this.downloadLength = j;
    }
}
