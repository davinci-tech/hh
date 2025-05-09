package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ad implements Serializable {
    private static final long serialVersionUID = -8045680007893341280L;

    @SerializedName("fileMd5")
    private String b = "";

    @SerializedName("fileSize")
    private long d;

    @SerializedName("fileSha256")
    private String e;

    public void d(String str) {
        this.e = str;
    }

    public void a(long j) {
        this.d = j;
    }
}
