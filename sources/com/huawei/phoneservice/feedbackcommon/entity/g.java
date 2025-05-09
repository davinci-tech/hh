package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes9.dex */
public class g implements Serializable {
    private static final long serialVersionUID = -8764490769713094093L;

    @SerializedName("responseData")
    private s b;

    @SerializedName("responseCode")
    private String c;

    @SerializedName("responseDesc")
    private String e;

    public s a() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
