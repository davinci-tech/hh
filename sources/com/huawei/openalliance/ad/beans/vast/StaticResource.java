package com.huawei.openalliance.ad.beans.vast;

import com.huawei.openalliance.ad.annotations.a;

/* loaded from: classes5.dex */
public class StaticResource {
    private String creativeType;

    @a
    private String url;

    public void b(String str) {
        this.url = str;
    }

    public String b() {
        return this.url;
    }

    public void a(String str) {
        this.creativeType = str;
    }

    public String a() {
        return this.creativeType;
    }
}
