package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("patchSize")
    private String f8317a;

    @SerializedName("patchNum")
    private String c;

    @SerializedName("patchVer")
    private String d;

    public String toString() {
        return "UploadPatchPolicyList{patchVer='" + this.d + "', patchNum='" + this.c + "', patchSize='" + this.f8317a + "'}";
    }

    public String b() {
        return this.c;
    }
}
