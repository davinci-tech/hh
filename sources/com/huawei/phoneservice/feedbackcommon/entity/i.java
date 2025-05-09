package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class i implements Serializable {
    private static final long serialVersionUID = -8764490769713094093L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("responseDesc")
    private String f8321a;

    @SerializedName("responseCode")
    private String b;

    @SerializedName("responseData")
    private p c;

    public String b() {
        return this.f8321a;
    }

    public p d() {
        return this.c;
    }

    public String a() {
        return this.b;
    }
}
