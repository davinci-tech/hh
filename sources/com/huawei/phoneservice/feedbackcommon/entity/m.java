package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class m implements Serializable {
    private static final long serialVersionUID = -8764490769713094093L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("responseData")
    private b f8325a;

    @SerializedName("responseCode")
    private String b;

    @SerializedName("responseDesc")
    private String c;

    public b d() {
        return this.f8325a;
    }

    public String b() {
        return this.b;
    }
}
