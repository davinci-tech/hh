package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("uploadInfoList")
    private List<ac> f8328a;

    @SerializedName("resCode")
    private int b;

    @SerializedName("fileUniqueFlag")
    private String c;

    @SerializedName("reason")
    private String d;

    @SerializedName("currentTime")
    private String e;

    @SerializedName("patchPolicyList")
    private c g;

    @SerializedName("policy")
    private String j;

    public String toString() {
        return "UploadInfoResponse{reason='" + this.d + "', resCode=" + this.b + ", fileUniqueFlag='" + this.c + "', currentTime='" + this.e + "', uploadInfoList=" + this.f8328a + ", policy='" + this.j + "', patchPolicyList=" + this.g + '}';
    }

    public List<ac> c() {
        return this.f8328a;
    }

    public int a() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String d() {
        return this.c;
    }
}
