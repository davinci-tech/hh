package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resCode")
    private int f8316a;

    @SerializedName("fileUniqueFlag")
    private String b;

    @SerializedName("currentTime")
    private String c;

    @SerializedName("uploadInfoList")
    private List<ac> d;

    @SerializedName("reason")
    private String e;

    @SerializedName("policy")
    private String f;

    @SerializedName("patchPolicyList")
    private c i;

    public String toString() {
        return "UploadInfoResponse{reason='" + this.e + "', resCode=" + this.f8316a + ", fileUniqueFlag='" + this.b + "', currentTime='" + this.c + "', uploadInfoList=" + this.d + ", policy='" + this.f + "', patchPolicyList=" + this.i + '}';
    }

    public List<ac> d() {
        return this.d;
    }

    public int b() {
        return this.f8316a;
    }

    public String a() {
        return this.e;
    }

    public c e() {
        return this.i;
    }

    public String c() {
        return this.b;
    }
}
