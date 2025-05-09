package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes5.dex */
public class ShareInfo {
    private String description;

    @a
    private String iconUrl;

    @a
    private String shareUrl;
    private String title;

    public void d(String str) {
        this.shareUrl = str;
    }

    public String d() {
        return this.shareUrl;
    }

    public void c(String str) {
        this.description = str;
    }

    public String c() {
        return this.description;
    }

    public void b(String str) {
        this.title = str;
    }

    public String b() {
        return this.title;
    }

    public void a(String str) {
        this.iconUrl = str;
    }

    public String a() {
        return this.iconUrl;
    }
}
