package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: classes9.dex */
public class ac {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("headers")
    private Map<String, String> f8315a;

    @SerializedName("uploadUrl")
    private String c;

    @SerializedName("method")
    private String d;

    @SerializedName("objectId")
    private String e;

    public String toString() {
        return "UploadInfoList{uploadUrl='" + this.c + "', method='" + this.d + "', headers=" + this.f8315a + ", objectId='" + this.e + "'}";
    }

    public String b() {
        return this.c;
    }

    public String a() {
        return this.d;
    }

    public Map<String, String> c() {
        return this.f8315a;
    }
}
