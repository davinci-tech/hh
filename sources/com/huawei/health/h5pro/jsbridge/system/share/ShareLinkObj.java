package com.huawei.health.h5pro.jsbridge.system.share;

import com.google.gson.annotations.SerializedName;
import com.tencent.open.SocialConstants;

/* loaded from: classes8.dex */
public class ShareLinkObj extends ShareObj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(SocialConstants.PARAM_APP_DESC)
    public String f2424a;

    @SerializedName("iconUrl")
    public String b;

    @SerializedName("title")
    public String d;

    @SerializedName("url")
    public String e;

    public void setUrl(String str) {
        this.e = str;
    }

    public void setTitle(String str) {
        this.d = str;
    }

    public void setIconUrl(String str) {
        this.b = str;
    }

    public void setDesc(String str) {
        this.f2424a = str;
    }

    public String getUrl() {
        return this.e;
    }

    public String getTitle() {
        return this.d;
    }

    public String getIconUrl() {
        return this.b;
    }

    public String getDesc() {
        return this.f2424a;
    }
}
