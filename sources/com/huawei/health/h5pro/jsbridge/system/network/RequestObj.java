package com.huawei.health.h5pro.jsbridge.system.network;

import com.google.gson.annotations.SerializedName;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.util.Map;

/* loaded from: classes8.dex */
public class RequestObj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("body")
    public Map<String, Object> f2412a;

    @SerializedName("url")
    public String b;

    @SerializedName("requestType")
    public String c = RequestType.GET.requestType;

    @SerializedName("headers")
    public Map<String, String> d;

    @SerializedName("requestBody")
    public String e;

    public void setRequestBody(String str) {
        this.e = str;
    }

    public void setHeaders(Map<String, String> map) {
        this.d = map;
    }

    public void setBody(Map<String, Object> map) {
        this.f2412a = map;
    }

    public String getUrl() {
        return this.b;
    }

    public String getRequestType() {
        return this.c;
    }

    public enum RequestType {
        POST("POST"),
        GET("GET"),
        DELETE(ProfileRequestConstants.DELETE_TYPE),
        PUT(ProfileRequestConstants.PUT_TYPE);

        public final String requestType;

        RequestType(String str) {
            this.requestType = str;
        }
    }

    public String getRequestBody() {
        return this.e;
    }

    public Map<String, String> getHeaders() {
        return this.d;
    }

    public Map<String, Object> getBody() {
        return this.f2412a;
    }
}
